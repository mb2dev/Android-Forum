package com.projet.esgi.android_forum.model;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Mickael on 28/06/2017.
 */

public class News extends PersistedModel implements RealmModel {

    private  String title;
    private String content;
    private String date;

    public News(){

    }

    public News(int id, String title, String content, String date){
        super(id);
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getModelNameForUrlPath() {
        return "news";
    }
}
