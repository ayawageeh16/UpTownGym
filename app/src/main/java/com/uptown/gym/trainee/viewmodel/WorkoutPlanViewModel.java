package com.uptown.gym.trainee.viewmodel;

import android.app.Application;

import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.exercise.WorkoutExercisesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.FitnessSessionResponse;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlansResponse;
import com.uptown.gym.trainee.model.workoutplan.Workouts;
import com.uptown.gym.trainee.repository.WorkoutPlanRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class WorkoutPlanViewModel extends AndroidViewModel {

    private WorkoutPlanRepository workoutPlanRepository;


    public WorkoutPlanViewModel(@NonNull Application application) {
        super(application);
        workoutPlanRepository = new WorkoutPlanRepository();
    }

    public MutableLiveData<MainResponse<List<WorkoutPlansResponse>>> findAllWorkoutPlans() {
        return workoutPlanRepository.findAllWorkoutPlansRequest();
    }

    public MutableLiveData<MainResponse<List<Workouts>>> findAllWorkouts(long workoutId, String weekNumber) {
        return workoutPlanRepository.findAllWorkouts(workoutId, weekNumber);
    }


    public MutableLiveData<MainResponse<List<WorkoutPlansResponse>>> findAllWorkoutPlansByCategory(String category) {
        return workoutPlanRepository.findAllWorkoutPlansByCategory(category);
    }

    public MutableLiveData<MainResponse<WorkoutPlanResponse>> findWorkoutPlanById(long workoutPlanId) {
        return workoutPlanRepository.findWorkoutPlanByIdRequest(workoutPlanId);
    }

    public MutableLiveData<MainResponse<List<WorkoutExercisesResponse>>> findAllWorkoutExercises(long workoutId) {
        return workoutPlanRepository.findAllWorkoutExercises(workoutId);
    }


    // OnGoing WorkoutPlan FitnessSession


    public MutableLiveData<MainResponse<FitnessSessionResponse>> createFitnessSession(long ongoingWorkoutPlanId,
                                                                                      long ongoingWorkoutPlanDayTimeId,
                                                                                      long userId) {
        return workoutPlanRepository.createFitnessSession(ongoingWorkoutPlanId, ongoingWorkoutPlanDayTimeId, userId);
    }


}
