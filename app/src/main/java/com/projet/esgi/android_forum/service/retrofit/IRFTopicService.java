package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.model.Topic;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IRFTopicService {
    @POST("users/")
    Call<ResponseBody> create(@Body Topic model);

    @GET("{path}")
    Call<Topic> read(@Path(value = "path", encoded = true) String path);
}
