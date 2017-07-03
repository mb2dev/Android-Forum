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


public class BlankFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private List<String> cities = new ArrayList<>();



    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");
        cities.add("au revoir");



        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyAdapter mAdapter = new MyAdapter(cities);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        System.out.println("ok");
    }
}
