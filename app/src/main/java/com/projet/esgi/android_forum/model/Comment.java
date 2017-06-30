package com.projet.esgi.android_forum.model;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.rfabstract.Exclude;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Mickael on 28/06/2017.
 */

public class Comment extends PersistedModel implements RealmModel {

    private String title;
    private String content;
    private String news;
    private String date;

    public Comment(){

    }

    public Comment(int id, String title, String content, String news, String data){
        super(id);
        this.title = title;
        this.content = content;
        this.news = news;
        this.date = data;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getModelNameForUrlPath() {
        return "comments";
    }
}
