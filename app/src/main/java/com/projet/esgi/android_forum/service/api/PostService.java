package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.Post;
import com.projet.esgi.android_forum.service.retrofit.IRFPostService;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.IPostService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public class PostService implements IGenericService<Post> {

    private IRFPostService mRfService;
    private IRFPostService getmRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFPostService.class);
        return mRfService;
    }

    @Override
    public void create(Post model, final IServiceResultListener<String> resultListener) {

    }

    @Override
    public void read(String modelID, final IServiceResultListener<Post> resultListener) {

    }

    @Override
    public void delete(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }

    @Override
    public void list(final IServiceResultListener<List<Post>> models) {

    }

    @Override
    public void update(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }
}
