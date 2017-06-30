package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.model.Topic;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IRFTopicService {
    @POST("topics/")
    Call<ResponseBody> create(@Body Topic model);

    @GET("topics/{id}")
    Call<Topic> read(@Path(value = "id", encoded = true) String id);

    @DELETE("topics/{id}")
    Call<ResponseBody> delete(@Path(value = "id", encoded = true) String id);

    @GET("topics/")
    Call<List<Topic>> list();

    @PUT("topics/{id}")
    Call<ResponseBody> update(@Path(value = "id", encoded = true) String id, @Body Topic model);
}
