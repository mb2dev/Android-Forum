package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.model.Comment;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface ICommentService {
    void create(Comment model, final IServiceResultListener<String> resultListener);
    void read(String modelID, final IServiceResultListener<Comment> resultListener);
    void delete(String modelID, final IServiceResultListener<Boolean> resultListener);
    void list(final IServiceResultListener<List<Comment>> models);
    void update(String modelID, final IServiceResultListener<Boolean> resultListener);
}
