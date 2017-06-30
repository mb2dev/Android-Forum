package com.projet.esgi.android_forum.service.rfabstract;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IGenericService<T> {
    void create(T model, final IServiceResultListener<String> resultListener);
    void read(String modelID, final IServiceResultListener<T> resultListener);
    void delete(String modelID, final IServiceResultListener<Boolean> resultListener);
    void list(final IServiceResultListener<List<T>> models);
    void update(String modelID, final IServiceResultListener<Boolean> resultListener);
}
