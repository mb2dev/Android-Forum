package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IRFAuthService {
    @POST("auth/login")
    Call<String> login(@Field(value="email") String email, @Field(value="password") String password);

    @POST("auth/subscribe")
    Call<ResponseBody> subscribe(@Body User user);
}
