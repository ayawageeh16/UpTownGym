package com.uptown.gym.trainee.retrofit.utils;

public interface ApiResponseListener<T> {

    void onSuccess(T response);

    void onFailure(String message);
}
