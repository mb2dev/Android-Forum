package com.projet.esgi.android_forum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projet.esgi.android_forum.Adapter.ItemClickListener;
import com.projet.esgi.android_forum.Adapter.MyAdapterTopic;
import com.projet.esgi.android_forum.Constant;
import com.projet.esgi.android_forum.DetailActivity;
import com.projet.esgi.android_forum.Dialog.CustomDialog;
import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.service.api.TopicService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mickael on 02/07/2017.
 */

public class TopicFragment extends Fragment implements ItemClickListener, INotifyFragment<Topic> {

    private RecyclerView recyclerView;
    private MyAdapterTopic mAdapter;
    public CustomDialog.myOnClickListener myListener;
    private List<Topic> topicList = new ArrayList<>();
    private  CustomDialog mydialog;
    private TopicService topicService;
    private Button btnUpdate, btnDelete;


    public TopicFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.topic_fragment, container, false);


        topicService = new TopicService();
        topicService.list(new IServiceResultListener<List<Topic>>() {
            @Override
            public void onResult(ServiceResult<List<Topic>> result) {
                if(result.getError()!=null){
                    System.out.println("error " + result.getError());
                }
                else{
                    System.out.println("result " + result.getData());
                    topicList = result.getData();
                    System.out.println("result " +  topicList.get(0).getTitle());
                    recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mAdapter = new MyAdapterTopic(topicList);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setClickListener(TopicFragment.this);
                }
            }
        });



        return view;

    }

    @Override
    public void onClick(final View view, final int position)
    {
        if(view.getId() == R.id.btn_delete){
            topicService.delete(topicList.get(position), new IServiceResultListener<Boolean>() {
                @Override
                public void onResult(ServiceResult<Boolean> result) {
                    if(result.getError()!=null){
                        System.out.println("error : " + result.getError().getMessage());
                    }
                    mAdapter.removeAt(position);
                }
            });
        }
        else if (view.getId() ==R.id.btn_update){
            final Topic topicSelected = topicList.get(position);

            myListener = new CustomDialog.myOnClickListener() {
                @Override
                public void onButtonCancel(View v) {
                    mydialog.dismiss();
                }

                @Override
                public void onButtonUpdate(String title, String content) {
                    topicSelected.setTitle(title);
                    topicSelected.setContent(content);
                    topicService.update(topicSelected, new IServiceResultListener<Boolean>() {
                        @Override
                        public void onResult(ServiceResult<Boolean> result) {
                            if(result.getError()!=null){
                                System.out.println("error " + result.getError());
                            }
                            else{
                                System.out.println("result " + result.getData());
                            }
                            mAdapter.notifyDataSetChanged();
                            mydialog.dismiss();
                        }
                    });
                }
            };
            mydialog = new CustomDialog(getActivity(), myListener, Constant.TYPE_TOPIC,topicSelected.getTitle(),topicSelected.getContent());
            mydialog.show();
        }
        else{
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("ID", topicList.get(position).get_id());
            intent.putExtra("TYPE", "post");
            startActivity(intent);
        }
    }

    @Override
    public void notifyAddItem(Topic topic) {
        mAdapter.addItem(topic);
    }
}
