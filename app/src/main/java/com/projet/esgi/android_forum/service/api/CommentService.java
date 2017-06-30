package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.service.retrofit.IRFCommentService;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.ICommentService;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public class CommentService implements IGenericService<Comment> {

    private IRFCommentService mRfService;
    private IRFCommentService getmRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFCommentService.class);
        return mRfService;
    }

    @Override
    public void create(Comment model, final IServiceResultListener<String> resultListener) {

    }

    @Override
    public void read(String modelID, final IServiceResultListener<Comment> resultListener) {

    }

    @Override
    public void delete(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }

    @Override
    public void list(final IServiceResultListener<List<Comment>> models) {

    }

    @Override
    public void update(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }
}
