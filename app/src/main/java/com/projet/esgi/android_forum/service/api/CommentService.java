package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.retrofit.IRFCommentService;
import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.retrofit.RFHelper;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.ICommentService;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceException;
import com.projet.esgi.android_forum.service.rfabstract.ServiceExceptionType;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.List;
import java.util.Map;

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
    private IRFCommentService getRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFCommentService.class);
        return mRfService;
    }

    private RFHelper<Comment> mRFHelper;
    private RFHelper<Comment> getRFHelper(){
        if(mRFHelper == null)
            mRFHelper = new RFHelper<Comment>();
        return mRFHelper;
    }

    @Override
    public void create(Comment model, final IServiceResultListener<String> resultListener) {
        getRFHelper().getDefaultCreate(getRfService().create(model), resultListener);

    }

    @Override
    public void read(Comment model, final IServiceResultListener<Comment> resultListener) {
        getRFHelper().getDefaultRead(getRfService().read(""+model.getId()), resultListener);
    }

    @Override
    public void delete(Comment model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultDelete(getRfService().delete(""+model.getId()), resultListener);
    }

    @Override
    public void list(final IServiceResultListener<List<Comment>> resultListener) {
        getRFHelper().getDefaultList(getRfService().list(), resultListener);
    }

    public void listCriteria(String criteria, final IServiceResultListener<List<Comment>> resultListener) {
        getRFHelper().getDefaultList(getRfService().listCriteria(criteria), resultListener);
    }

    @Override
    public void update(Comment model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultUpdate(getRfService().update(""+model.get_id(), model), resultListener);
    }

    public void search(Map<String, String> queries, final IServiceResultListener<List<Comment>> resultListener){

    }
}
