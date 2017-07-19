package com.projet.esgi.android_forum.service.rfabstract;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IGenericService<T extends IPersistedModel> {
    void create(T model, final IServiceResultListener<String> resultListener);
    void read(T model, final IServiceResultListener<T> resultListener);
    void delete(T model, final IServiceResultListener<Boolean> resultListener);
    void list(final IServiceResultListener<List<T>> resultListener);
    void update(T model, final IServiceResultListener<Boolean> resultListener);
}
