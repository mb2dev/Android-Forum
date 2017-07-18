package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.retrofit.IRFTopicService;
import com.projet.esgi.android_forum.service.retrofit.RFHelper;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ITopicService;
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
public class TopicService implements IGenericService<Topic> {

    private IRFTopicService mRfService;
    private IRFTopicService getRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFTopicService.class);
        return mRfService;
    }

    private RFHelper<Topic> mRFHelper;
    private RFHelper<Topic> getRFHelper(){
        if(mRFHelper == null)
            mRFHelper = new RFHelper<Topic>();
        return mRFHelper;
    }

    @Override
    public void create(Topic model, final IServiceResultListener<String> resultListener) {
        getRFHelper().getDefaultCreate(getRfService().create(model), resultListener);
    }

    @Override
    public void read(Topic model, final IServiceResultListener<Topic> resultListener) {
        getRFHelper().getDefaultRead(getRfService().read(""+model.get_id()), resultListener);
    }

    @Override
    public void delete(Topic model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultDelete(getRfService().delete(""+model.get_id()), resultListener,model.get_id(),Topic.class);
    }

    @Override
    public void list(final IServiceResultListener<List<Topic>> resultListener) {
        getRFHelper().getDefaultList(getRfService().list(), resultListener, Topic.class);
    }

    @Override
    public void update(Topic model, final IServiceResultListener<Boolean> resultListener) {
        getRFHelper().getDefaultUpdate(getRfService().update(""+model.get_id(), model), resultListener);
    }
}
