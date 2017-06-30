package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;

/**
 * Created by Gabriel on 30/06/2017.
 */

public abstract class PersistedModel implements IRFGeneric {
    protected int id;
    public int getId(){
        return this.id;
    }
    public void setId(int id){ this.id = id; }

    public PersistedModel(){
        id = -1;
    }

    public PersistedModel(int id){
        this.id = id;
    }
}
