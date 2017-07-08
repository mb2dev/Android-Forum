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
import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.service.api.NewsService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.ArrayList;
import java.util.List;


public class NewFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private List<News> newList = new ArrayList<>();
    MyAdapterNew mAdapter;



    public NewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewFragment newInstance(String param1, String param2) {
        NewFragment fragment = new NewFragment();
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
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);

        NewsService newsService = new NewsService();
        newsService.list(new IServiceResultListener<List<News>>() {
            @Override
            public void onResult(ServiceResult<List<News>> result) {
                if (result.getError() != null) {
                    System.out.println("error " + result.getError());
                } else {
                    System.out.println("result " + result.getData());
                    newList = result.getData();
                    recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mAdapter = new MyAdapterNew(newList);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setClickListener(NewFragment.this);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        System.out.println("ok");
    }
}
