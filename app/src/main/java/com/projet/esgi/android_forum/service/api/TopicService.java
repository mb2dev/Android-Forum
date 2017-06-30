package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.service.retrofit.IRFTopicService;
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

public class TopicService implements IGenericService<Topic> {

    private IRFTopicService mRfService;
    private IRFTopicService getmRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFTopicService.class);
        return mRfService;
    }

    @Override
    public void create(Topic model, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfService().create(model);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if(response.code() == 201)
                    result.setData(response.headers().get("Resourceuri"));
                else
                    result.setError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    @Override
    public void read(String modelID, final IServiceResultListener<Topic> resultListener) {

    }

    @Override
    public void delete(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }

    @Override
    public void list(final IServiceResultListener<List<Topic>> models) {

    }

    @Override
    public void update(String modelID, final IServiceResultListener<Boolean> resultListener) {

    }
}
