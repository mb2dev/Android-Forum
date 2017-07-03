package com.projet.esgi.android_forum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.logIn) Button logIn;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText password;
    private int progressStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Bonjour");
    }


    @OnClick(R.id.logIn) void Login(View view){
        System.out.println("Bonjour");
        Intent intent = new Intent(this, listActivity.class);
        startActivity(intent);




        /*final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCanceledOnTouchOutside(false);
        pd.setTitle("Log in");
        pd.setMessage("Loading  .........");
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));
        pd.setIndeterminate(false);
        pd.show();
        // pd.dismiss()

        //
        // Set the progress status zero on each button click
        progressStatus = 0;*/
    }


}
