package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.service.retrofit.IRFNewsService;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.INewsService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public class NewsService implements IGenericService<News>, INewsService {

    private IRFNewsService mRfService;
    private IRFNewsService getmRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFNewsService.class);
        return mRfService;
    }

    @Override
    public void create(News model, final IServiceResultListener<String> resultListener) {

    }

    @Override
    public void read(String modelID, final IServiceResultListener<News> resultListener) {

    }

    @Override
    public void delete(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }

    @Override
    public void list(final IServiceResultListener<List<News>> models) {

    }

    @Override
    public void update(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }
}
