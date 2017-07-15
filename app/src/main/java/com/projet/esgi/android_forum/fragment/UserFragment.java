package com.projet.esgi.android_forum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.esgi.android_forum.Adapter.ItemClickListener;
import com.projet.esgi.android_forum.Adapter.MyAdapterUser;
import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.api.UserService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mickael on 08/07/2017.
 */

public class UserFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private List<User> userList = new ArrayList<>();
    private MyAdapterUser mAdapter;



    public UserFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.topic_fragment, container, false);

        final UserService userService = new UserService();

        userService.list(new IServiceResultListener<List<User>>() {
            @Override
            public void onResult(ServiceResult<List<User>> result) {
                if(result.getError()!=null){
                    System.out.println("error " + result.getError());
                }
                else{
                    System.out.println("result " + result.getData());
                    userList = result.getData();

                    recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mAdapter = new MyAdapterUser(userList);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setClickListener(UserFragment.this);
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view, int position) {
        System.out.println("Position : "+ position);
    }
}
