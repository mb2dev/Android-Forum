package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.service.retrofit.IRFCommentService;
import com.projet.esgi.android_forum.service.retrofit.RFHelper;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.ICommentService;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
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
public class CommentService implements IGenericService<Comment> {

    private IRFCommentService mRfService;
    private IRFCommentService getmRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFCommentService.class);
        return mRfService;
    }

    private RFHelper<Comment> mRFHelper;
    private RFHelper<Comment> getmRFHelper(){
        if(mRFHelper == null)
            mRFHelper = new RFHelper<Comment>();
        return mRFHelper;
    }

    @Override
    public void create(Comment model, final IServiceResultListener<String> resultListener) {
        getmRFHelper().getDefaultCreate(getmRfService().create(model), resultListener);
    }

    @Override
    public void read(String modelID, final IServiceResultListener<Comment> resultListener) {
        getmRFHelper().getDefaultRead(getmRfService().read(modelID), resultListener);
    }

    @Override
    public void delete(String modelID, final IServiceResultListener<Boolean> resultListener) {
        getmRFHelper().getDefaultDelete(getmRfService().delete(modelID), resultListener);
    }

    @Override
    public void list(final IServiceResultListener<List<Comment>> resultListener) {
        getmRFHelper().getDefaultList(getmRfService().list(), resultListener);
    }

    @Override
    public void update(Comment model, final IServiceResultListener<Boolean> resultListener) {
        getmRFHelper().getDefaultUpdate(getmRfService().update(""+model.getId(), model), resultListener);
    }
}
