package com.projet.esgi.android_forum.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.projet.esgi.android_forum.R;

import butterknife.BindView;

/**
 * Created by Mickael on 04/07/2017.
 */


public class CustomDialog extends Dialog {


    //@BindView(R.id.text_dialog) TextView text;


    public CustomDialog(Context context) {
        super(context);
        setContentView(R.layout.custom_dialog);

    }
}
