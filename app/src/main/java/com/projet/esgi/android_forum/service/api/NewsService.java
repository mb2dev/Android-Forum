package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.retrofit.IRFNewsService;
import com.projet.esgi.android_forum.service.retrofit.RFHelper;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.INewsService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceException;
import com.projet.esgi.android_forum.service.rfabstract.ServiceExceptionType;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gabriel on 28/06/2017.
 */

/**
 * @see "https://esgi-2017.herokuapp.com/api/v1/docs/"
 */
public class NewsService implements IGenericService<News> {

    private IRFNewsService mRfService;
    private IRFNewsService getRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFNewsService.class);
        return mRfService;
    }
    private RFHelper<News> mRFHelper;
    private RFHelper<News> getRFHelper(){
        if(mRFHelper == null)
            mRFHelper = new RFHelper<News>();
        return mRFHelper;
    }

    @Override
    public void create(News model, final IServiceResultListener<String> resultListener) {
        getRFHelper().getDefaultCreate(getRfService().create(model), resultListener);
    }

    @Override
    public void read(News model, final IServiceResultListener<News> resultListener) {
        getRFHelper().getDefaultRead(getRfService().read(""+model.get_id()), resultListener);
    }

    @Override
    public void delete(News model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultDelete(getRfService().delete(""+model.get_id()), resultListener, model.get_id(), News.class);
    }

    @Override
    public void list(IServiceResultListener<List<News>> resultListener) {
        getRFHelper().getDefaultList(getRfService().list(), resultListener, News.class);
    }


    public void listCriteria(String criteria, final IServiceResultListener<List<News>> resultListener) {
        getRFHelper().getDefaultList(getRfService().listCriteria(criteria), resultListener, News.class);
    }

    @Override
    public void update(News model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultUpdate(getRfService().update(""+model.get_id(), model), resultListener);
    }



}
