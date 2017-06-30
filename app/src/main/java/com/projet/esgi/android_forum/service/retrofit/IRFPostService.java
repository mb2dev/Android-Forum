package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.model.Post;

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

public interface IRFPostService {
    @POST("posts/")
    Call<ResponseBody> create(@Body Post model);

    @GET("posts/{id}")
    Call<Post> read(@Path(value = "id", encoded = true) String id);

    @DELETE("posts/{id}")
    Call<ResponseBody> delete(@Path(value = "id", encoded = true) String id);

    @GET("posts/")
    Call<List<Post>> list();

    @PUT("posts/{id}")
    Call<ResponseBody> update(@Path(value = "id", encoded = true) String id, @Body Post model);
}
