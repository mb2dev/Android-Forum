package com.projet.esgi.android_forum.model;

import io.realm.RealmObject;

/**
 * Created by Mickael on 28/06/2017.
 */

public class Topic extends RealmObject {

    private int id;
    private String title;
    private String content;
    private String date;

    public Topic(){

    }

    public Topic(int id,String title, String content, String date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

