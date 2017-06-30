package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.retrofit.IRFUserService;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.IUserService;
import com.projet.esgi.android_forum.service.rfabstract.ServiceException;
import com.projet.esgi.android_forum.service.rfabstract.ServiceExceptionType;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

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
public class UserService implements IUserService {

    private IRFUserService mRfUserService;
    private IRFUserService getmRfService(){
        if(mRfUserService == null)
            mRfUserService = Session.getDefault().create(IRFUserService.class);
        return mRfUserService;
    }

    @Override
    public void create(User model, final IServiceResultListener<String> resultListener) {
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
    public void read(String userID, final IServiceResultListener<User> resultListener) {
        Call<User> call = getmRfService().read(userID);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ServiceResult<User> result = new ServiceResult<>();
                if(response.code() == 200)
                    result.setData(response.body());
                else
                    result.setError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ServiceResult<User> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }
}
