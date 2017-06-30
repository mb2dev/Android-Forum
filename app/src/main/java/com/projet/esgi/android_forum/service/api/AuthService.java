package com.projet.esgi.android_forum.service.api;

import android.util.Log;

import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.retrofit.IRFAuthService;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IAuthService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceException;
import com.projet.esgi.android_forum.service.rfabstract.ServiceExceptionType;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.io.IOException;

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
public class AuthService implements IAuthService {

    private static final String TAG = AuthService.class.getName();

    public static String TOKEN = null;
    private IRFAuthService mRfService;
    private IRFAuthService getmRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFAuthService.class);
        return mRfService;
    }

    @Override
    public void login(String email, String password, final IServiceResultListener<String> resultListener) {
        Call<String> call = getmRfService().login(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if(response.code() == 200) {
                    String token = response.body();
                    result.setData(token);
                    AuthService.TOKEN = token;
                }else {
                    result.setError(new ServiceException(response.code()));
                }
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    @Override
    public void subscribe(User user, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfService().subscribe(user);
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
}
