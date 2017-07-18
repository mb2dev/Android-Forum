package com.projet.esgi.android_forum.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.projet.esgi.android_forum.Constants;
import com.projet.esgi.android_forum.R;

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
    private LinearLayout header;
    private ImageView img;

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
        header = (LinearLayout) findViewById(R.id.linear_header_dialog);
        img = (ImageView) findViewById(R.id.img_header_dialog);

        if(type.equals(Constants.TYPE_TOPIC) || type.equals(Constants.TYPE_POST)){
            btnAdd.setTextColor(ContextCompat.getColor(context,R.color.colorTopic));
            btnCancel.setTextColor(ContextCompat.getColor(context,R.color.colorTopic));
            LayerDrawable bgDrawable = (LayerDrawable)header.getBackground();
            GradientDrawable shape = (GradientDrawable)   bgDrawable.findDrawableByLayerId(R.id.item_shape);
            shape.setColor(ContextCompat.getColor(context,R.color.colorTopic));
            img.setBackgroundResource(R.drawable.topic);
            ViewGroup.LayoutParams params = img.getLayoutParams();
            params.height = 150;
            params.width = 150;
        }

        else if(type.equals(Constants.TYPE_NEWS) || type.equals(Constants.TYPE_COMMENT)){
            btnAdd.setTextColor(ContextCompat.getColor(context, R.color.colorNews));
            btnCancel.setTextColor(ContextCompat.getColor(context, R.color.colorNews));
            LayerDrawable bgDrawable = (LayerDrawable)header.getBackground();
            GradientDrawable shape = (GradientDrawable)   bgDrawable.findDrawableByLayerId(R.id.item_shape);
            shape.setColor(ContextCompat.getColor(context,R.color.colorNews));
            img.setBackgroundResource(R.drawable.news);
            ViewGroup.LayoutParams params = img.getLayoutParams();
            params.height = 150;
            params.width = 150;
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
