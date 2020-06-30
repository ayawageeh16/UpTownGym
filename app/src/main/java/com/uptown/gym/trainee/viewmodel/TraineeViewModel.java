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

    public MutableLiveData<MainResponse<FitnessTest>> findFitnessTestById(long traineeId, long fitnessTestId) {
        return traineeRepository.findFitnessTestById(traineeId, fitnessTestId);
    }

    public MutableLiveData<InBody> findInBodyById(long traineeId, long inBodyId) {
        return traineeRepository.findInBodyById(traineeId, inBodyId);
    }

    public MutableLiveData<MainResponse<InBodies>> findAllInbodies(long traineeId) {
        return traineeRepository.findAllInBody(traineeId);
    }

    public MutableLiveData<Boolean> enrollTraineeToProgram(long activeWorkoutPlanId, long traineeId) {
        return traineeRepository.enrollTraineeToProgram(activeWorkoutPlanId, traineeId);
    }
}
