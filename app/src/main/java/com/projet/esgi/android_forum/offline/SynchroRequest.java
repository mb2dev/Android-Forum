package com.projet.esgi.android_forum.offline;

import io.realm.RealmObject;

/**
 * Created by Gabriel on 30/06/2017.
 */

public class SynchroRequest extends RealmObject {
    public String clazz;
    public String primaryKey;
    public String request;
    public String _id;
    public String method;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getRequest() {
        return request;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
