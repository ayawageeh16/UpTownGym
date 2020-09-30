package com.uptown.gym.trainee.repository;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.trainer.TrainerDTO;
import com.uptown.gym.trainee.model.trainer.TrainerLoginDTO;
import com.uptown.gym.trainee.model.trainer.User;
import com.uptown.gym.trainee.retrofit.TrainerClient;
import com.uptown.gym.trainee.retrofit.utils.ApiResponse;
import com.uptown.gym.trainee.retrofit.utils.ApiResponseListener;
import com.uptown.gym.trainee.retrofit.utils.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {


    public LoginRepository(Application application) {
    }

    public MutableLiveData<MainResponse<User>> login(TrainerLoginDTO trainer) {
        MutableLiveData<MainResponse<User>> trainerLogin = new MutableLiveData<>();

        TrainerClient trainerClient = RetrofitClient.getInstance().create(TrainerClient.class);
        Call<User> call = trainerClient.login(trainer);

        call.enqueue(new ApiResponse(new ApiResponseListener<MainResponse<User>>() {
            @Override
            public void onSuccess(MainResponse<User> response) {
                trainerLogin.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<User> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                trainerLogin.postValue(mainResponse);

            }
        }));
        return trainerLogin;
    }
}
