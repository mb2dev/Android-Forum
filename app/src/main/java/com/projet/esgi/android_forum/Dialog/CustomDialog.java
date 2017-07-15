package com.projet.esgi.android_forum.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projet.esgi.android_forum.Constant;
import com.projet.esgi.android_forum.R;

import butterknife.BindView;

/**
 * Created by Mickael on 04/07/2017.
 */


public class CustomDialog extends Dialog {

   private  myOnClickListener myclick;
    private Button btnCancel;
    private Button btnAdd;
    private EditText title;
    private EditText content;
    private String type;
    private Context context;

    // This is my interface //
    public interface myOnClickListener {
        void onButtonCancel(View view);
        void onButtonUpdate(String title, String content);
    }


    public CustomDialog(Context context, myOnClickListener myclick, String type, String title, String content) {
        super(context);
        setContentView(R.layout.custom_dialog);
        this.title = (EditText) findViewById(R.id.dialog_title);
        this.content = (EditText) findViewById(R.id.dialog_description);
        this.title.setText(title);
        this.content.setText(content);
        this.myclick = myclick;
        this.context = context;
        this.type = type;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnAdd = (Button) findViewById(R.id.btn_add);

        if(type.equals(Constant.TYPE_TOPIC) || type.equals(Constant.TYPE_POST)){
            btnAdd.setTextColor(ContextCompat.getColor(context,R.color.colorTopic));
            btnCancel.setTextColor(ContextCompat.getColor(context,R.color.colorTopic));
        }
        else if(type.equals(Constant.TYPE_NEWS) || type.equals(Constant.TYPE_COMMENT)){
            btnAdd.setTextColor(ContextCompat.getColor(context, R.color.colorNews));
            btnCancel.setTextColor(ContextCompat.getColor(context, R.color.colorNews));
        }


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclick.onButtonCancel(v);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclick.onButtonUpdate(title.getText().toString(), content.getText().toString());
            }
        });



    }
}
