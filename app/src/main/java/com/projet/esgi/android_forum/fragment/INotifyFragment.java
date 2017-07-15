package com.projet.esgi.android_forum.fragment;

import com.projet.esgi.android_forum.model.Topic;

/**
 * Created by Mickael on 15/07/2017.
 */

public  interface INotifyFragment<T> {
    void notifyAddItem(T  t);
}
