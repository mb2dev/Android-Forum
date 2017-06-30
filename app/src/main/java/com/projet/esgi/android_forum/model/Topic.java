package com.projet.esgi.android_forum.model;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Mickael on 28/06/2017.
 */

public class Topic extends PersistedModel implements RealmModel {

    private String title;
    private String content;
    private Date date;

    public Topic(){

    }

    public Topic(String title, String content, Date date) {
        this(UUID.randomUUID().toString(), title, content, date);
    }

    public Topic(String id, String title, String content, Date date){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getModelNameForUrlPath() {
        return "topics";
    }
}

