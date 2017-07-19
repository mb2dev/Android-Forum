package com.projet.esgi.android_forum.offline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.fragment.TopicFragment;
import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.service.api.TopicService;
import com.projet.esgi.android_forum.service.retrofit.RFHelper;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * Created by Mickael on 19/07/2017.
 */

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                System.out.println("Internet YAY");
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(final Realm realm) {
                        List<SynchroRequest> sync = realm.where(SynchroRequest.class).findAll();
                        for (SynchroRequest syncObject: sync) {
                            IGenericService service;
                            System.out.println("OKKK" + syncObject.getClazz()+' ' + Topic.class);
                           if(syncObject.getClazz().equals(Topic.class.toString())){
                               service = new TopicService();
                               System.out.println("OK2");
                               Topic top =  realm.where(Topic.class).equalTo("_id", syncObject.get_id()).findFirst();
                               System.out.print("top :" +top.toString());

                               Topic top2 = new Topic();
                               top2.setTitle("TT");
                               top2.setContent("TT");


                               service.create(top2, new IServiceResultListener<String>() {
                                   @Override
                                   public void onResult(ServiceResult<String> result) {
                                       System.out.println("ADDNEWResult");
                                       if(result.getError()!=null){
                                           System.out.println("error " + result.getError());
                                       }
                                       else{

                                           System.out.println("result " + result.getData());
                                       }
                                   }

                        });
                               //service.create();

                           }
                        }

                    }
                });
            } else if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {
                System.out.println("No Internet");
            }
        }
    }

}
