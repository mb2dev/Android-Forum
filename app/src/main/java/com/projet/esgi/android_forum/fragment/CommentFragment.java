package com.projet.esgi.android_forum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.projet.esgi.android_forum.Adapter.ItemClickListener;
import com.projet.esgi.android_forum.Adapter.MyAdapterComment;
import com.projet.esgi.android_forum.Constants;
import com.projet.esgi.android_forum.Dialog.CustomDialog;
import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.service.api.CommentService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mickael on 11/07/2017.
 */

public class CommentFragment extends Fragment implements ItemClickListener,  INotifyFragment<Comment> {

    private RecyclerView recyclerView;
    private List<Comment> commentList = new ArrayList<>();
    MyAdapterComment mAdapter;
    public CustomDialog.myOnClickListener myListener;
    private String idNew;
    private  CustomDialog mydialog;
    private CommentService commentService;



    public CommentFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if(arguments != null){
            idNew = arguments.getString("ID");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);
        commentService = new CommentService();


        commentService.listCriteria(idNew, new IServiceResultListener<List<Comment>>() {
            @Override
            public void onResult(ServiceResult<List<Comment>> result) {
                System.out.println("result " + result.getData());
                commentList = result.getData();
                recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mAdapter = new MyAdapterComment(commentList);
                recyclerView.setAdapter(mAdapter);
                mAdapter.setClickListener(CommentFragment.this);
            }
        });
        return view;
    }



    @Override
    public void onClick(final View view, final int position)
    {
        System.out.println("click " + view.getId() + ' '+ R.id.btn_update );

        if(view.getId() == R.id.btn_delete){

            commentService.delete(commentList.get(position), new IServiceResultListener<Boolean>() {
                @Override
                public void onResult(ServiceResult<Boolean> result) {
                    if(result.getError()!=null){
                        System.out.println("error : " + result.getError().getMessage());
                    }
                    mAdapter.removeAt(position);
                }
            });
        }
        if (view.getId() ==R.id.btn_update){
            System.out.println("on Update");
            final Comment commentSelected = commentList.get(position);

            myListener = new CustomDialog.myOnClickListener() {
                @Override
                public void onButtonCancel(View v) {
                    mydialog.dismiss();
                }

                @Override
                public void onButtonUpdate(String title, String content) {
                    commentSelected.setTitle(title);
                    commentSelected.setContent(content);
                    commentService.update( commentSelected, new IServiceResultListener<Boolean>() {
                        @Override
                        public void onResult(ServiceResult<Boolean> result) {
                            if(result.getError()!=null){
                                System.out.println("error " + result.getError());
                            }
                            else{
                                System.out.println("result " + result.getData());
                            }

                            mydialog.dismiss();
                        }
                    });
                }
            };
            mydialog = new CustomDialog(getActivity(), myListener, Constants.TYPE_COMMENT,commentSelected.getTitle(), commentSelected.getContent());
            mydialog.show();
        }
    }

    @Override
    public void notifyAddItem(Comment comment) {
        mAdapter.addItem(comment);
    }
}

