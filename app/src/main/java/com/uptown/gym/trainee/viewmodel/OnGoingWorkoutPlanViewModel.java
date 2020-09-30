package com.uptown.gym.trainee.viewmodel;

import android.app.Application;

import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTime;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTimesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlansResponse;
import com.uptown.gym.trainee.repository.OnGoingWorkoutPlanRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class OnGoingWorkoutPlanViewModel extends AndroidViewModel {


    private final OnGoingWorkoutPlanRepository onGoingWorkoutPlanRepository;

    public OnGoingWorkoutPlanViewModel(@NonNull Application application) {
        super(application);
        onGoingWorkoutPlanRepository = new OnGoingWorkoutPlanRepository();
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> findAllOnGoingWorkoutPlansByGender(long userId) {
        return onGoingWorkoutPlanRepository.findAllOnGoingWorkoutPlansByGender(userId);
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> findActiveProgramsByTarget(String target) {
        return onGoingWorkoutPlanRepository.findAllOnGoingWorkoutPlansByTarget(target);
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> findActiveProgramsByTargetAndGender(String gender, String target) {
        return onGoingWorkoutPlanRepository.findAllOnGoingWorkoutPlansByGenderAndTarget(gender, target);
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlan>> findOnGoingWorkoutPlanById(long workoutPlanId, long ongoingWorkoutPlanId) {
        return onGoingWorkoutPlanRepository.findOnGoingWorkoutPlanById(workoutPlanId, ongoingWorkoutPlanId);
    }


    public MutableLiveData<MainResponse<OnGoingWorkoutPlanDayTimesResponse>> findAllOnGoingWorkoutPlanDayTime(long workoutPlanId, long ongoingWorkoutPlanId, long workoutId) {
        return onGoingWorkoutPlanRepository.findAllOnGoingWorkoutPlanDayTime(workoutPlanId, ongoingWorkoutPlanId, workoutId);
    }

}
