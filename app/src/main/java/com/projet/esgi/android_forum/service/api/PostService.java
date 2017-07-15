package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.model.Post;
import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.retrofit.IRFPostService;
import com.projet.esgi.android_forum.service.retrofit.RFHelper;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.IPostService;
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
public class PostService implements IGenericService<Post> {

    private IRFPostService mRfService;
    private IRFPostService getRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFPostService.class);
        return mRfService;
    }

    private RFHelper<Post> mRFHelper;
    private RFHelper<Post> getRFHelper(){
        if(mRFHelper == null)
            mRFHelper = new RFHelper<Post>();
        return mRFHelper;
    }

    @Override
    public void create(Post model, final IServiceResultListener<String> resultListener) {
        getRFHelper().getDefaultCreate(getRfService().create(model), resultListener);
    }

    @Override
    public void read(Post model, final IServiceResultListener<Post> resultListener) {
        getRFHelper().getDefaultRead(getRfService().read(""+model.getId()), resultListener);
    }

    @Override
    public void delete(Post model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultDelete(getRfService().delete(""+model.get_id()), resultListener);
    }

    @Override
    public void list(final IServiceResultListener<List<Post>> resultListener) {
        getRFHelper().getDefaultList(getRfService().list(), resultListener);
    }

    @Override
    public void update(Post model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultUpdate(getRfService().update(""+model.get_id(), model), resultListener);
    }

    public void listCriteria(String criteria, final IServiceResultListener<List<Post>> resultListener) {
        getRFHelper().getDefaultList(getRfService().listCriteria(criteria), resultListener);
    }


}
