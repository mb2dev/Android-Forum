package com.projet.esgi.android_forum.model;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.rfabstract.Exclude;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Mickael on 28/06/2017.
 */



public class Comment extends PersistedModel implements RealmModel {

    @PrimaryKey
    private String _id;
    private String title;
    private String content;
    private String news;
    private Date date;

    public Comment(){

    }

    public Comment(String title, String content) {
        this(UUID.randomUUID().toString(), title, content);
    }

    public Comment(String id, String title, String content){
        super(id);
        this.title = title;
        this.content = content;
        //this.news = new RealmList<News>();
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

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }


/* public RealmList<News> getNews() {
        return news;
    }

    public void setNews(RealmList<News> news) {
        this.news = news;
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getModelNameForUrlPath() {
        return "comments";
    }
}
