package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.model.News;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface INewsService {
    void create(News model, final IServiceResultListener<String> resultListener);
    void read(String modelID, final IServiceResultListener<News> resultListener);
    void delete(String modelID, final IServiceResultListener<Boolean> resultListener);
    void list(final IServiceResultListener<List<News>> models);
    void update(String modelID, final IServiceResultListener<Boolean> resultListener);
}
