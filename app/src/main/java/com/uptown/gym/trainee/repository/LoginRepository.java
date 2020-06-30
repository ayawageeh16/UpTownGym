package com.uptown.gym.trainee.repository;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.uptown.gym.trainee.model.trainer.TrainerDTO;
import com.uptown.gym.trainee.model.trainer.TrainerLoginDTO;
import com.uptown.gym.trainee.model.trainer.User;
import com.uptown.gym.trainee.retrofit.TrainerClient;
import com.uptown.gym.trainee.retrofit.utils.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {


    public LoginRepository(Application application) {
    }

    public MutableLiveData<TrainerDTO> login(TrainerLoginDTO trainer) {
        MutableLiveData<TrainerDTO> trainerLogin = new MutableLiveData<>();

        TrainerClient trainerClient = RetrofitClient.getInstance().create(TrainerClient.class);
        Call<User> call = trainerClient.login(trainer);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                Log.i("login success", new Gson().toJson(response.body()) + response.code());

                if (response != null) {
                    TrainerDTO dto = new TrainerDTO();

                    if (response.body() != null) {
                        dto.setErrorCode(0);
                        dto.setTrainer(response.body());
                        trainerLogin.setValue(dto);
                    } else {
                        trainerLogin.setValue(null);
                    }

                    if (response.code() == 400) {
                        dto.setErrorCode(400);
                        dto.setTrainer(null);
                        trainerLogin.setValue(dto);
                    }


                } else {
                    trainerLogin.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                Log.i("login error", String.valueOf(t.getMessage()));
                trainerLogin.setValue(null);
            }
        });

        return trainerLogin;
    }
}
