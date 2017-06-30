package com.projet.esgi.android_forum.service.api;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gabriel on 30/06/2017.
 */

public class HttpBasicAuth implements Interceptor {

    private static String token = null;

    @Nullable
    public static String getToken(){ return token; }
    public static void setToken(String t){ token = t; }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // If the request already have an authorization (eg. Basic auth), do nothing
        if (request.header("Authorization") == null && getToken() != null) {
//            String credentials = Credentials.basic(username, password);
//            request = request.newBuilder()
//                    .addHeader("Authorization", credentials)
//                    .build();
            request = request.newBuilder()
                        .addHeader("Authorization", getToken())
                        .build();
        }
        return chain.proceed(request);
    }
}
