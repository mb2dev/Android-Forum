package com.projet.esgi.android_forum.service.retrofit;

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
 * Created by Gabriel on 30/06/2017.
 */

public class RFHelper<T> {

    public static String TOKEN = null;

    public void getDefaultCreate(Call<ResponseBody> call,final IServiceResultListener<String> resultListener){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if(response.code() == 201)
                    result.setData(response.headers().get("Location"));
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

    public void getDefaultRead(Call<T> call, final IServiceResultListener<T> resultListener){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                ServiceResult<T> result = new ServiceResult<>();
                if(response.code() == 200)
                    result.setData(response.body());
                else
                    result.setError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                ServiceResult<T> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    public void getDefaultList(Call<List<T>> call, final IServiceResultListener<List<T>> resultListener) {
        call.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                ServiceResult<List<T>> result = new ServiceResult<>();
                if(response.code() == 200) {
                    result.setData(response.body());
                }else {
                    result.setError(new ServiceException(response.code()));
                }
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                ServiceResult<List<T>> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    public void getDefaultUpdate(Call<ResponseBody> call, final IServiceResultListener<Boolean> resultListener) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<Boolean> result = new ServiceResult<>();
                if(response.code() == 200)
                    result.setData(true);
                else
                    result.setError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<Boolean> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    public void getDefaultDelete(Call<ResponseBody> call, final IServiceResultListener<Boolean> resultListener) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<Boolean> result = new ServiceResult<>();
                if(response.code() == 200) {
                    result.setData(true);
                }else {
                    result.setError(new ServiceException(response.code()));
                }
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<Boolean> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }
}
