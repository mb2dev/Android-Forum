package com.projet.esgi.android_forum.service.rfabstract;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IServiceResultListener<T> {
    void onResult(ServiceResult<T> result);
}
