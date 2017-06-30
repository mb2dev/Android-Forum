package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.model.News;

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

public interface IRFNewsService {
    @POST("news/")
    Call<ResponseBody> create(@Body News model);

    @GET("news/{id}")
    Call<News> read(@Path(value = "id", encoded = true) String id);

    @DELETE("news/{id}")
    Call<ResponseBody> delete(@Path(value = "id", encoded = true) String id);

    @GET("news/")
    Call<List<News>> list();

    @PUT("news/{id}")
    Call<ResponseBody> update(@Path(value = "id", encoded = true) String id, @Body News model);
}
