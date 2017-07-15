package com.projet.esgi.android_forum;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.projet.esgi.android_forum.fragment.CommentFragment;
import com.projet.esgi.android_forum.fragment.NewFragment;
import com.projet.esgi.android_forum.fragment.PostFragment;
import com.projet.esgi.android_forum.fragment.TopicFragment;
import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.model.Post;
import com.projet.esgi.android_forum.service.api.CommentService;
import com.projet.esgi.android_forum.service.api.NewsService;
import com.projet.esgi.android_forum.service.api.PostService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import butterknife.BindView;

public class DetailActivity extends AppCompatActivity {


    TranslucentBarManager translucentBarManager;
    FloatingActionButton floatingActionButton;
    Dialog dialog;
    ProgressDialog pd;
    Button btnAdd;
    Button btnCancel;
    ImageView img;
    LinearLayout header;
    EditText editTitle;
    EditText editDescription;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        pd = new ProgressDialog(DetailActivity.this);
        initActionBar();

        String type = getIntent().getStringExtra("TYPE");
        id = getIntent().getStringExtra("ID");

        System.out.println("type" + type + " " + id);

        translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this,R.color.colorTopic);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.action);
        floatingActionButton.bringToFront();

        dialog = new Dialog(DetailActivity.this);
        dialog.setContentView(R.layout.custom_dialog);

        btnAdd = (Button) dialog.findViewById(R.id.btn_add) ;
        btnCancel = (Button) dialog.findViewById(R.id.btn_cancel) ;
        header = (LinearLayout) dialog.findViewById(R.id.linear_header_dialog);
        img = (ImageView) dialog.findViewById(R.id.img_header_dialog);
        editTitle= (EditText) dialog.findViewById(R.id.dialog_title) ;
        editDescription = (EditText) dialog.findViewById(R.id.dialog_description);


        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("ID", id);


        if(type.equals("comment")){
            setThemeApp( R.color.colorNews, R.drawable.ic_news);
            initAddComment();
            CommentFragment frag = new CommentFragment();
            frag.setArguments(bundle);
            ft.replace(R.id.main_fragment, frag);
            ft.commitAllowingStateLoss();
        }else if(type.equals("post")){
            setThemeApp( R.color.colorTopic, R.drawable.ic_topic);
            initAddPost();
            PostFragment frag = new PostFragment();
            frag.setArguments(bundle);
            ft.replace(R.id.main_fragment, frag);
            ft.commitAllowingStateLoss();
        }
        setLoading();
    }

    private void setThemeApp(int color, int drawableImg) {
        translucentBarManager.translucent(DetailActivity.this, color);
        floatingActionButton.show();
        LayerDrawable bgDrawable = (LayerDrawable) header.getBackground();
        GradientDrawable shape = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.item_shape);
        shape.setColor(ContextCompat.getColor(getApplicationContext(), color));
        img.setBackgroundResource(drawableImg);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), color)));
        btnAdd.setTextColor(ContextCompat.getColor(getApplicationContext(), color));
        btnCancel.setTextColor(ContextCompat.getColor(getApplicationContext(),color));
    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void setLoading(){
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCanceledOnTouchOutside(false);
        pd.setTitle("Log in");
        pd.setMessage("Loading  .........");
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));
        pd.setIndeterminate(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }






    private void initAddPost(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PostService postService = new PostService();
                        final Post post = new Post();
                        post.setTitle(editTitle.getText().toString());
                        post.setContent(editDescription.getText().toString());
                        post.setTopic(id);
                        pd.show();

                        postService.create(post, new IServiceResultListener<String>() {
                            @Override
                            public void onResult(ServiceResult<String> result) {
                                if(result.getError()!=null){
                                    System.out.println("error " + result.getError());
                                }
                                else {
                                    PostFragment postFragment = (PostFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
                                    postFragment.notifyAddItem(post);
                                    System.out.println("result " + result.getData());
                                }
                                pd.dismiss();
                                dialog.dismiss();

                            }
                        });

                    }
                });
                dialog.show();
            }
        });
    }

    private void initAddComment(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CommentService commentService = new CommentService();
                        final Comment comment = new Comment();
                        comment.setTitle(editTitle.getText().toString());
                        comment.setContent(editDescription.getText().toString());
                        comment.setNews(id);
                        pd.show();

                        commentService.create(comment, new IServiceResultListener<String>() {
                            @Override
                            public void onResult(ServiceResult<String> result) {
                                if(result.getError()!=null){
                                    System.out.println("error " + result.getError());
                                }
                                else {
                                    CommentFragment commentFragment = (CommentFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
                                    commentFragment.notifyAddItem(comment);
                                    System.out.println("result " + result.getData());
                                }
                                pd.dismiss();
                                dialog.dismiss();

                            }
                        });
                    }
                });
                dialog.show();
            }
        });
    }

}
