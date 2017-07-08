package com.projet.esgi.android_forum;

import android.app.Dialog;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.projet.esgi.android_forum.Adapter.ItemClickListener;
import com.projet.esgi.android_forum.Dialog.CustomDialog;
import com.projet.esgi.android_forum.fragment.BlankFragment;
import com.projet.esgi.android_forum.fragment.TopicFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class listActivity extends AppCompatActivity implements ItemClickListener {


    BottomBar bottomBar;
    TranslucentBarManager translucentBarManager;
    FloatingActionButton floatingActionButton;
    Dialog dialog;

    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    ImageView img;
    @BindView(R.id.linear_header_dialog)
    LinearLayout header;
    EditText editTitle;
    EditText editDescription;


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
        translucentBarManager.translucent(this,R.color.colorTopic);

        //Binding
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.action);
        floatingActionButton.bringToFront();
        dialog = new Dialog(listActivity.this);
        dialog.setContentView(R.layout.custom_dialog);


        btnAdd = (Button) dialog.findViewById(R.id.btn_add) ;
        btnCancel = (Button) dialog.findViewById(R.id.btn_cancel) ;
        header = (LinearLayout) dialog.findViewById(R.id.linear_header_dialog);
        img = (ImageView) dialog.findViewById(R.id.img_header_dialog);
        editTitle= (EditText) dialog.findViewById(R.id.dialog_title) ;
        editDescription = (EditText) dialog.findViewById(R.id.dialog_description) ;


        setThemeApp( R.color.colorTopic, R.drawable.ic_topic);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_topic) {
                    final FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = supportFragmentManager.beginTransaction();
                    ft.replace(R.id.main_fragment, new TopicFragment());
                    ft.commitAllowingStateLoss();
                    setThemeApp( R.color.colorTopic, R.drawable.ic_topic);

                }
                else if (tabId == R.id.tab_news) {
                    final FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = supportFragmentManager.beginTransaction();
                    ft.replace(R.id.main_fragment, new BlankFragment());
                    ft.commitAllowingStateLoss();
                    setThemeApp( R.color.colorNews, R.drawable.ic_news);

                }
                else if (tabId == R.id.tab_profile) {
                    translucentBarManager.translucent(listActivity.this, R.color.colorProfil);
                    floatingActionButton.hide();
                    //floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorProfil)));

                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //btnAdd.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorTopic));
               // btnCancel.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorTopic));


                // set the custom dialog components - text, image and button
                /*TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.ic_launcher);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/

                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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


    private void setThemeApp(int color, int drawableImg){
        translucentBarManager.translucent(listActivity.this, color);
        floatingActionButton.show();

        LayerDrawable bgDrawable = (LayerDrawable)header.getBackground();
        GradientDrawable shape = (GradientDrawable)   bgDrawable.findDrawableByLayerId(R.id.item_shape);
        shape.setColor(ContextCompat.getColor(getApplicationContext(),color));


        img.setBackgroundResource(drawableImg);


        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),color)));
        btnAdd.setTextColor(ContextCompat.getColor(getApplicationContext(),color));
        btnCancel.setTextColor(ContextCompat.getColor(getApplicationContext(),color));
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
