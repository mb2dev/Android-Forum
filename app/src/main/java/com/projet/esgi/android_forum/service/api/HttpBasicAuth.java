package com.projet.esgi.android_forum.service.api;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Gabriel on 30/06/2017.
 */

public class HttpBasicAuth implements Interceptor {

    private static String token = null;

    // TODO : persists token (in sharedPreferences or Realm)
    @Nullable
    public static String getToken(){ return token; }
    public static void setToken(String t){ token = t; }

//    private String username;
//    private String password;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setCredentials(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        System.out.println("BODY" + bodyToString(chain.request().body()));


        // If the request already have an authorization (eg. Basic auth), do nothing
        if (request.header("Authorization") == null && getToken() != null) {
//            String credentials = Credentials.basic(username, password);
//            request = request.newBuilder()
//                    .addHeader("Authorization", credentials)
//                    .build();
            request = request.newBuilder()
                        .addHeader("Authorization", "Bearer "+getToken())
                        .build();
        }
        System.out.println("Token "+ request.header("Authorization"));
        return chain.proceed(request);
    }

    public static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
