package com.projet.esgi.android_forum.offline;

import io.realm.RealmObject;

/**
 * Created by Gabriel on 30/06/2017.
 */

public class SynchroRequest extends RealmObject {
    public String clazz;
    public String primaryKey;
    public String method;
}
