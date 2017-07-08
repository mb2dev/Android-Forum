package com.projet.esgi.android_forum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.esgi.android_forum.Adapter.ItemClickListener;
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

public class TopicFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private MyAdapterTopic mAdapter;

    private List<Topic> topicList = new ArrayList<>();


    public TopicFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.topic_fragment, container, false);

        final TopicService topicService = new TopicService();

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
    public void onClick(View view, int position)
    {

        System.out.println("Position : "+ position);
    }
}
