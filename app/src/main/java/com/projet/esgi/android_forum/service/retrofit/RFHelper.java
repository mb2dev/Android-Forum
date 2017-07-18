package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.Adapter.MyAdapterTopic;
import com.projet.esgi.android_forum.ConnectivityStatus;
import com.projet.esgi.android_forum.MainActivity;
import com.projet.esgi.android_forum.fragment.TopicFragment;
import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceException;
import com.projet.esgi.android_forum.service.rfabstract.ServiceExceptionType;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import java.util.Collection;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gabriel on 30/06/2017.
 */

public class RFHelper<T extends RealmModel> {

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

    public void getDefaultList(Call<List<T>> call, final IServiceResultListener<List<T>> resultListener, final Class<T> clazz) {
        if (!ConnectivityStatus.sharedIntance.isNetworkAvailable()) {
            System.out.println("HERE NOT GOOD");
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    ServiceResult<List<T>> result = new ServiceResult<>();
                    List<T> find = realm.copyFromRealm(realm.where(clazz).findAll());
                    result.setData(find);
                    if(resultListener != null)
                        resultListener.onResult(result);
                }
            });
        }
        else{
            System.out.println("GOOD");
            call.enqueue(new Callback<List<T>>() {
                @Override
                public void onResponse(Call<List<T>> call, final Response<List<T>> response) {
                    ServiceResult<List<T>> result = new ServiceResult<>();
                    if(response.code() == 200) {
                        result.setData(response.body());

                        //save object
                        Realm realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction(){
                            @Override
                            public void execute(Realm realm) {
                                realm.insertOrUpdate((Collection<? extends RealmModel>) response.body());
                            }
                        });
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

    public void getDefaultDelete(Call<ResponseBody> call, final IServiceResultListener<Boolean> resultListener, final String _id, final Class<T> clazz) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(final Call<ResponseBody> call, Response<ResponseBody> response) {
                final ServiceResult<Boolean> result = new ServiceResult<>();
                if(response.code() == 204) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            System.out.println("remove"+ _id);
                            RealmResults<T> rows = realm.where(clazz).equalTo("_id",_id).findAll();
                            rows.deleteAllFromRealm();

                        }
                    });
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
