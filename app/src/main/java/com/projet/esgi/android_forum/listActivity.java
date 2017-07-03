package com.projet.esgi.android_forum;

import android.graphics.Color;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.projet.esgi.android_forum.Adapter.ItemClickListener;
import com.projet.esgi.android_forum.fragment.BlankFragment;
import com.projet.esgi.android_forum.fragment.TopicFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;


public class listActivity extends AppCompatActivity implements ItemClickListener {


    BottomBar bottomBar;
    TranslucentBarManager translucentBarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initActionBar();

        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.replace(R.id.main_fragment, new TopicFragment());
        ft.commitAllowingStateLoss();

        translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this,android.R.color.holo_blue_light);



       /* getFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, new TopicFragment())
                .addToBackStack(null)
                .commit();*/


        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_topic) {
                    final FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = supportFragmentManager.beginTransaction();
                    ft.replace(R.id.main_fragment, new TopicFragment());
                    ft.commitAllowingStateLoss();
                    translucentBarManager.translucent(listActivity.this, R.color.colorTopic);
                }
                else if (tabId == R.id.tab_news) {
                    final FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = supportFragmentManager.beginTransaction();
                    ft.replace(R.id.main_fragment, new BlankFragment());
                    ft.commitAllowingStateLoss();

                    translucentBarManager.translucent(listActivity.this, R.color.colorNews);
                }
                else if (tabId == R.id.tab_profile) {
                    translucentBarManager.translucent(listActivity.this, R.color.colorProfil);
                }
            }
        });
    }


    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view, int position) {
        System.out.println("ok : "+ position);
    }
}
