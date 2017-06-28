package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IRFUserService {
    @POST("users/")
    Call<ResponseBody> create(@Body User user);

    @GET("{path}")
    Call<User> read(@Path(value = "path", encoded = true) String path);
}
