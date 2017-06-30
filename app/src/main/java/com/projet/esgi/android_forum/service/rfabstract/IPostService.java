package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.model.Post;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IPostService {
    void create(Post model, final IServiceResultListener<String> resultListener);
    void read(String modelID, final IServiceResultListener<Post> resultListener);
    void delete(String modelID, final IServiceResultListener<Boolean> resultListener);
    void list(final IServiceResultListener<List<Post>> models);
    void update(String modelID, final IServiceResultListener<Boolean> resultListener);
}
