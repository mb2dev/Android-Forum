package com.projet.esgi.android_forum;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.projet.esgi.android_forum.fragment.NewFragment;
import com.projet.esgi.android_forum.fragment.TopicFragment;
import com.projet.esgi.android_forum.fragment.UserFragment;
import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.service.api.NewsService;
import com.projet.esgi.android_forum.service.api.TopicService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;


public class ListActivity extends AppCompatActivity {

    BottomBar bottomBar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initActionBar();
        pd = new ProgressDialog(ListActivity.this);


        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.replace(R.id.main_fragment, new TopicFragment());
        ft.commitAllowingStateLoss();

        translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this,R.color.colorTopic);

        //Binding
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.action);
        floatingActionButton.bringToFront();
        dialog = new Dialog(ListActivity.this);
        dialog.setContentView(R.layout.custom_dialog);




        btnAdd = (Button) dialog.findViewById(R.id.btn_add) ;
        btnCancel = (Button) dialog.findViewById(R.id.btn_cancel) ;
        header = (LinearLayout) dialog.findViewById(R.id.linear_header_dialog);
        img = (ImageView) dialog.findViewById(R.id.img_header_dialog);
        editTitle= (EditText) dialog.findViewById(R.id.dialog_title) ;
        editDescription = (EditText) dialog.findViewById(R.id.dialog_description);






        setThemeApp(R.color.colorTopic, R.drawable.ic_topic);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_topic) {
                    final FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = supportFragmentManager.beginTransaction();
                    ft.replace(R.id.main_fragment, new TopicFragment());
                    ft.commitAllowingStateLoss();
                    setThemeApp( R.color.colorTopic, R.drawable.ic_topic);
                    addTopic();
                }
                else if (tabId == R.id.tab_news) {
                    final FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = supportFragmentManager.beginTransaction();
                    ft.replace(R.id.main_fragment, new NewFragment());
                    ft.commitAllowingStateLoss();
                    setThemeApp( R.color.colorNews, R.drawable.ic_news);
                    addNew();

                }
                else if (tabId == R.id.tab_profile) {
                    translucentBarManager.translucent(ListActivity.this, R.color.colorProfil);
                    floatingActionButton.hide();
                    btnAdd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorProfil));
                    btnCancel.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorProfil));
                    FragmentTransaction ft = supportFragmentManager.beginTransaction();
                    ft.replace(R.id.main_fragment, new UserFragment());
                    ft.commitAllowingStateLoss();


                }
            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        setLoading();

    }


    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }


    private void setThemeApp(int color, int drawableImg){
        translucentBarManager.translucent(ListActivity.this, color);
        floatingActionButton.show();
        LayerDrawable bgDrawable = (LayerDrawable)header.getBackground();
        GradientDrawable shape = (GradientDrawable)   bgDrawable.findDrawableByLayerId(R.id.item_shape);
        shape.setColor(ContextCompat.getColor(getApplicationContext(),color));
        img.setBackgroundResource(drawableImg);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),color)));
        btnAdd.setTextColor(ContextCompat.getColor(getApplicationContext(),color));
        btnCancel.setTextColor(ContextCompat.getColor(getApplicationContext(),color));
    }

    private void addTopic(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("ADDNEW");
                        TopicService topicService = new TopicService();
                        final Topic topic = new Topic();
                        topic.setTitle(editTitle.getText().toString());
                        topic.setContent(editDescription.getText().toString());
                        pd.show();
                        topicService.create(topic, new IServiceResultListener<String>() {
                            @Override
                            public void onResult(ServiceResult<String> result) {
                                System.out.println("ADDNEWResult");
                                if(result.getError()!=null){
                                    System.out.println("error " + result.getError());
                                }
                                else{
                                    TopicFragment topicFragment = (TopicFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
                                    topicFragment.notifyAddItem(topic);
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

    private void addNew(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NewsService newsService = new NewsService();
                        final News news = new News();
                        news.setTitle(editTitle.getText().toString());
                        news.setContent(editDescription.getText().toString());
                        pd.show();

                        newsService.create(news, new IServiceResultListener<String>() {
                            @Override
                            public void onResult(ServiceResult<String> result) {

                                if(result.getError()!=null){
                                    System.out.println("error " + result.getError());
                                }
                                else{
                                    NewFragment newsFragment = (NewFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
                                    newsFragment.notifyAddItem(news);
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

    public void setLoading(){
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCanceledOnTouchOutside(false);
        pd.setTitle("Log in");
        pd.setMessage("Loading  .........");
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));
        pd.setIndeterminate(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_log_out) {
            SharedPreferences settings = getSharedPreferences("User", 0);

            SharedPreferences.Editor editor = settings.edit();
            editor.putString("token", "");
            editor.commit();
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
