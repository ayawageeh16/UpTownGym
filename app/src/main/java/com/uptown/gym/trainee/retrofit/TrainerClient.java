package com.uptown.gym.trainee.retrofit;

import com.uptown.gym.trainee.model.trainer.TrainerLoginDTO;
import com.uptown.gym.trainee.model.trainer.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TrainerClient {

    @POST("api/v1/users")
    Call<User> signUp(@Body User trainer);

    @POST("api/v1/users/login")
    Call<User> login(@Body TrainerLoginDTO trainer);

}
