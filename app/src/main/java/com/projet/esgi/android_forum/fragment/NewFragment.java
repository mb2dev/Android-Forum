package com.projet.esgi.android_forum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.esgi.android_forum.Adapter.OnItemClickListener;
import com.projet.esgi.android_forum.Adapter.MyAdapterNew;
import com.projet.esgi.android_forum.Constants;
import com.projet.esgi.android_forum.DetailActivity;
import com.projet.esgi.android_forum.Dialog.CustomDialog;
import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.service.api.NewsService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.ArrayList;
import java.util.List;


public class NewFragment extends Fragment implements OnItemClickListener,  INotifyFragment<News> {

    private RecyclerView recyclerView;
    private List<News> newList = new ArrayList<>();
    public CustomDialog.myOnClickListener myListener;
    MyAdapterNew mAdapter;
    private  CustomDialog mydialog;
    private NewsService newsService;



    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);
        newsService = new NewsService();
        newsService.list(new IServiceResultListener<List<News>>() {
            @Override
            public void onResult(ServiceResult<List<News>> result) {
                System.out.println("result " + result.getData());
                newList = result.getData();
                recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mAdapter = new MyAdapterNew(newList);
                recyclerView.setAdapter(mAdapter);
                mAdapter.setClickListener(NewFragment.this);
            }
        });
        return view;
    }

    @Override
    public void onClick(final View view, final int position)
    {
        System.out.println("click " + view.getId() + ' '+ R.id.btn_update );

        if(view.getId() == R.id.btn_delete){
            newsService.delete(newList.get(position), new IServiceResultListener<Boolean>() {
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
            System.out.println("on Update");
            final News newsSelected = newList.get(position);

            myListener = new CustomDialog.myOnClickListener() {
                @Override
                public void onButtonCancel(View v) {
                    mydialog.dismiss();
                }

                @Override
                public void onButtonUpdate(String title, String content) {
                    newsSelected.setTitle(title);
                    newsSelected.setContent(content);
                    newsService.update(newsSelected, new IServiceResultListener<Boolean>() {
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
            mydialog = new CustomDialog(getActivity(), myListener, Constants.TYPE_NEWS,newsSelected.getTitle(), newsSelected.getContent());
            mydialog.show();
        }else{
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("ID", newList.get(position).get_id());
            intent.putExtra("TYPE", "comment");
            startActivity(intent);
        }
    }

    @Override
    public void notifyAddItem(News news) {
        mAdapter.addItem(news);
    }
}
