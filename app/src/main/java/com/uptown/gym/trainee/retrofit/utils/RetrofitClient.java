package com.uptown.gym.trainee.retrofit.utils;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.uptown.gym.trainee.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static Retrofit retrofitClient;

    // create Logger
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS);


    public static Retrofit getInstance() {
        if (retrofitClient != null) {
            return retrofitClient;
        } else {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpBuilder.build())
                    .build();
            return retrofitClient;
        }
    }
}
