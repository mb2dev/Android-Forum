package com.projet.esgi.android_forum.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.esgi.android_forum.Adapter.OnItemClickListener;
import com.projet.esgi.android_forum.Adapter.MyAdapterPost;
import com.projet.esgi.android_forum.Constants;
import com.projet.esgi.android_forum.Dialog.CustomDialog;
import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.model.Post;
import com.projet.esgi.android_forum.service.api.PostService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mickael on 11/07/2017.
 */

public class PostFragment extends Fragment implements OnItemClickListener, INotifyFragment<Post> {

    private RecyclerView recyclerView;
    private MyAdapterPost mAdapter;
    String idTopic;
    public CustomDialog.myOnClickListener myListener;
    private CustomDialog mydialog;
    private PostService  postService;

    private List<Post> postList = new ArrayList<>();


    public PostFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null){
            idTopic = arguments.getString("ID");
            System.out.println("idTopic " + idTopic);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.topic_fragment, container, false);


        postService = new PostService();
        //System.out.println("idTopic criteria  " + createQuery());
        postService.listCriteria(idTopic, new IServiceResultListener<List<Post>>() {
            @Override
            public void onResult(ServiceResult<List<Post>> result) {
                System.out.println("result " + result.getData());
                postList = result.getData();
                recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mAdapter = new MyAdapterPost(postList);
                recyclerView.setAdapter(mAdapter);
                mAdapter.setClickListener(PostFragment.this);
            }
        });

        return view;
    }



    @Override
    public void onClick(final View view, final int position)
    {
        System.out.println("click " + view.getId() + ' '+ R.id.btn_update );

        if(view.getId() == R.id.btn_delete){
           postService.delete(postList.get(position), new IServiceResultListener<Boolean>() {
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
            final Post PostSelected = postList.get(position);

            myListener = new CustomDialog.myOnClickListener() {
                @Override
                public void onButtonCancel(View v) {
                    mydialog.dismiss();
                }

                @Override
                public void onButtonUpdate(String title, String content) {
                    PostSelected.setTitle(title);
                    PostSelected.setContent(content);
                    postService.update(  PostSelected, new IServiceResultListener<Boolean>() {
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
            mydialog = new CustomDialog(getActivity(), myListener, Constants.TYPE_POST,PostSelected.getTitle(), PostSelected.getContent());
            mydialog.show();
        }
    }

    @Override
    public void notifyAddItem(Post post) {
        mAdapter.addItem(post);
    }
}
