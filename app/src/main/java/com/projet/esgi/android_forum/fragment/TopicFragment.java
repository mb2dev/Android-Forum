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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mickael on 02/07/2017.
 */

public class TopicFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private List<String> cities = new ArrayList<>();


    public TopicFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.topic_fragment, container, false);

        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");
        cities.add("bonjour");


        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyAdapter mAdapter = new MyAdapter(cities);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        System.out.println("Position : "+ position);
    }
}
