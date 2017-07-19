package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;

import java.util.UUID;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Gabriel on 30/06/2017.
 */


public abstract class PersistedModel implements IRFGeneric, RealmModel, IPersistedModel {

    @Exclude(serialize = true, deserialize = false)
    protected String id;
    public String getId(){
        return this.id;
    }
    public void setId(String id){ this.id = id; }

    public PersistedModel(){
        id = UUID.randomUUID().toString();
    }

    public PersistedModel(String id){
        this.id = id;
    }

    @Override
    public String getModelNameForUrlPath() {
        return null;
    }
}
