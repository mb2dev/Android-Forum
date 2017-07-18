package com.projet.esgi.android_forum.model;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Mickael on 28/06/2017.
 */

public class News extends RealmObject {

    @PrimaryKey
    private String _id;
    private  String title;
    private String content;
    private Date date;

    public News(){

    }

    public News(String title, String content) {
        this(UUID.randomUUID().toString(), title, content);
    }

    public News(String id, String title, String content){
       // super(id);
        this.title = title;
        this.content = content;
        this.date = new Date();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    /*@Override
    public String getModelNameForUrlPath() {
        return "news";
    }*/
}
