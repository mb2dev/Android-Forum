package com.projet.esgi.android_forum.service.api;

import com.google.gson.JsonObject;
import com.projet.esgi.android_forum.ConnectivityStatus;
import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.model.Post;
import com.projet.esgi.android_forum.model.Topic;
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

import io.realm.Realm;
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
        getRFHelper().getDefaultCreate(getRfService().create(model), resultListener, model, Post.class);
    }

    @Override
    public void read(Post model, final IServiceResultListener<Post> resultListener) {
        getRFHelper().getDefaultRead(getRfService().read(""+model.get_id()), resultListener);
    }

    @Override
    public void delete(Post model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultDelete(getRfService().delete(""+model.get_id()), resultListener, model.get_id(), Post.class);
    }

    @Override
    public void list(final IServiceResultListener<List<Post>> resultListener) {
        getRFHelper().getDefaultList(getRfService().list(), resultListener, Post.class);
    }

    @Override
    public void update(Post model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultUpdate(getRfService().update(""+model.get_id(), model), resultListener, model, Post.class);
    }

    public void listCriteria(final String criteria, final IServiceResultListener<List<Post>> resultListener) {
        final ServiceResult<List<Post>> result = new ServiceResult<>();
        if (!ConnectivityStatus.sharedIntance.isNetworkAvailable()) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    List<Post> postResult = realm.where(Post.class).equalTo("topic",criteria).findAll();
                    result.setData(postResult);
                    if(resultListener != null)
                        resultListener.onResult(result);
                }
            });
        }
        else {
            getRFHelper().getDefaultList(getRfService().listCriteria(createQuery(criteria)), resultListener, Post.class);
        }
    }


    public String createQuery(String id){
        JsonObject json = new JsonObject();
        JsonObject where = new JsonObject();
        where.addProperty("topic", id);
        json.add("where",where);
        return json.toString();
    }


}
