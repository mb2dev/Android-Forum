package com.projet.esgi.android_forum.service.retrofit;

import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

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

/**
 * Retrofit throws :
 * java.lang.IllegalArgumentException: Parameter type must not include a type variable or wildcard: T
 */
public interface IRFGenericService<T extends PersistedModel> {
    @POST("{pathName}/")
    Call<ResponseBody> create(@Path("pathName") String pathName, @Body T model);

    @GET("{pathName}/{id}")
    Call<T> read(@Path("pathName") String pathName, @Path(value = "id", encoded = true) String id);

    @DELETE("{pathName}/{id}")
    Call<ResponseBody> delete(@Path("pathName") String pathName, @Path(value = "id", encoded = true) String id);

    @GET("{pathName}/")
    Call<List<T>> list();

    @PUT("{pathName}/{id}")
    Call<ResponseBody> update(@Path("pathName") String pathName, @Path(value = "id", encoded = true) String id, @Body T model);
}
