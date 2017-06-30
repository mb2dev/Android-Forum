package com.projet.esgi.android_forum.service.retrofit;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projet.esgi.android_forum.Constants;
import com.projet.esgi.android_forum.service.api.HttpBasicAuth;
import com.projet.esgi.android_forum.service.rfabstract.Exclude;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gabriel on 28/06/2017.
 */

public class Session {
    private static Retrofit retrofit;
    private static Gson gson;
    private static OkHttpClient client;
    public static final String TOKEN = null;

    public static Retrofit getDefault(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    private static GsonBuilder getDefaultGsonBuilder(){
        GsonBuilder defaultGsonBuilder = new GsonBuilder();
        defaultGsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(Exclude.class) != null && f.getAnnotation(Exclude.class).serialize();
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });

        defaultGsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(Exclude.class) != null && f.getAnnotation(Exclude.class).deserialize();
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });

        defaultGsonBuilder.setDateFormat("yyyy-MM-dd");
        return defaultGsonBuilder;
    }

    private static Gson getGson(){
        if (gson == null) {
            GsonBuilder builder = getDefaultGsonBuilder();
            gson = builder.create();
        }
        return gson;
    }

    private static OkHttpClient getClient(){
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpBasicAuth())
                    .build();
        }
        return client;
    }
}
