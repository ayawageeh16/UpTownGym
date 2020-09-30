package com.uptown.gym.trainee.viewmodel;

import android.app.Application;

import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.trainer.TrainerDTO;
import com.uptown.gym.trainee.model.trainer.TrainerLoginDTO;
import com.uptown.gym.trainee.model.trainer.User;
import com.uptown.gym.trainee.repository.LoginRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {
    private final LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application);
    }

    public MutableLiveData<MainResponse<User>> login(TrainerLoginDTO trainer) {
        return loginRepository.login(trainer);
    }
}
