package com.projet.esgi.android_forum.model;

import io.realm.RealmObject;

/**
 * Created by Mickael on 28/06/2017.
 */

public class Post extends RealmObject {

    private int id;
    private String title;
    private String content;
    private String date;
    private String topic;

    public Post(){

    }


    public Post(int id,String title, String content, String date, String topic){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.topic = topic;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
