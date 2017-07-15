package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.model.Comment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IRFCommentService {
    @POST("comments/")
    Call<ResponseBody> create(@Body Comment model);

    @GET("comments/{id}")
    Call<Comment> read(@Path(value = "id", encoded = true) String id);

    @DELETE("comments/{id}")
    Call<ResponseBody> delete(@Path(value = "id", encoded = true) String id);

    @GET("comments/")
    Call<List<Comment>> list();

    @GET("comments/")
    Call<List<Comment>> listCriteria(@Query("criteria") String criteria);

    @PUT("comments/{id}")
    Call<ResponseBody> update(@Path(value = "id", encoded = true) String id, @Body Comment model);
}
