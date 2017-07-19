package com.projet.esgi.android_forum.service.api;

import com.google.gson.JsonObject;
import com.projet.esgi.android_forum.ConnectivityStatus;
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

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

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
        getRFHelper().getDefaultCreate(getRfService().create(model), resultListener, model, Comment.class);

    }

    @Override
    public void read(Comment model, final IServiceResultListener<Comment> resultListener) {
        getRFHelper().getDefaultRead(getRfService().read(""+model.get_id()), resultListener);
    }

    @Override
    public void delete(Comment model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultDelete(getRfService().delete(""+model.get_id()), resultListener, model.get_id(), Comment.class);
    }

    @Override
    public void list(final IServiceResultListener<List<Comment>> resultListener) {
        getRFHelper().getDefaultList(getRfService().list(), resultListener, Comment.class);
    }

    public void listCriteria(final String criteria, final IServiceResultListener<List<Comment>> resultListener) {
        final ServiceResult<List<Comment>> result = new ServiceResult<>();
        if (!ConnectivityStatus.sharedIntance.isNetworkAvailable()) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    List<Comment> commenTResult = realm.where(Comment.class).equalTo("news",criteria).findAll();
                    result.setData(commenTResult);
                    if(resultListener != null)
                        resultListener.onResult(result);

                }
            });
        }
        else{
            getRFHelper().getDefaultList(getRfService().listCriteria(createQuery(criteria)), resultListener, Comment.class);
        }

    }

    @Override
    public void update(Comment model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultUpdate(getRfService().update(""+model.get_id(), model), resultListener,model,Comment.class);
    }

    public void search(Map<String, String> queries, final IServiceResultListener<List<Comment>> resultListener){

    }

    public String createQuery(String idNew){
        JsonObject json = new JsonObject();
        JsonObject where = new JsonObject();
        where.addProperty("news", idNew);
        json.add("where",where);
        return json.toString();
    }
}
