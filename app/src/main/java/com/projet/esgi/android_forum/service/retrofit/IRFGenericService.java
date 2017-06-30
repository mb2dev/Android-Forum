package com.projet.esgi.android_forum.service.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IRFGenericService<T> {
    @POST("users/")
    Call<ResponseBody> create(@Body T model);

    @GET("{path}")
    Call<T> read(@Path(value = "path", encoded = true) String path);
}
