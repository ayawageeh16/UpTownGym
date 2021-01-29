package com.uptown.gym.trainee.viewmodel;

import android.app.Application;

import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTestsResponse;
import com.uptown.gym.trainee.model.inbody.InBodies;
import com.uptown.gym.trainee.model.inbody.InBody;
import com.uptown.gym.trainee.model.trainee.Trainee;
import com.uptown.gym.trainee.repository.TraineeRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TraineeViewModel extends AndroidViewModel {

    private final TraineeRepository traineeRepository;

    public TraineeViewModel(@NonNull Application application) {
        super(application);
        traineeRepository = new TraineeRepository();
    }


    public MutableLiveData<MainResponse<Trainee>> findTraineeById(long id) {
        return traineeRepository.findTraineeById(id);
    }

    public MutableLiveData<MainResponse<FitnessTestsResponse>> findALLFitnessTests(long traineeId) {
        return traineeRepository.findAllFitnessTests(traineeId);
    }

    public MutableLiveData<MainResponse<InBodies>> findAllInbodies(long traineeId) {
        return traineeRepository.findAllInBody(traineeId);
    }

}
