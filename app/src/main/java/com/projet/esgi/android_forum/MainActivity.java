package com.projet.esgi.android_forum;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.projet.esgi.android_forum.fragment.INotifyFragment;
import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.api.AuthService;
import com.projet.esgi.android_forum.service.api.HttpBasicAuth;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.logIn) Button logIn;
    private EditText email;
    private EditText password;
    private INotifyFragment notifyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Bonjour");
        email= (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        SharedPreferences settings = getSharedPreferences("User", 0);
        String token = settings.getString("token","");
        if(! token.isEmpty()){
            HttpBasicAuth.setToken(token);
            Intent intent = new Intent(MainActivity.this, listActivity.class);
            startActivity(intent);

        }

    }


    @OnClick(R.id.logIn) void Login(View view){
        //User user = new User(email.getText(),logIn.getText());
        AuthService AuthService = new AuthService();
        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCanceledOnTouchOutside(false);
        pd.setTitle("Log in");
        pd.setMessage("Loading  .........");
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));
        pd.setIndeterminate(false);
        pd.show();


        AuthService.login(user, new IServiceResultListener<String>() {
            @Override
            public void onResult(ServiceResult<String> result) {
                pd.dismiss();
                if(result.getError() !=null){
                    System.out.println("ERROR :" + result.getError().getMessage());
                }
                else{
                    SharedPreferences settings = getSharedPreferences("User", 0);

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("token", result.getData());
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, listActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}
