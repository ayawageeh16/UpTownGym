package com.uptown.gym.trainee.repository;


import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.exercise.WorkoutExercisesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.FitnessSessionResponse;
import com.uptown.gym.trainee.model.workoutplan.Category;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlansResponse;
import com.uptown.gym.trainee.model.workoutplan.Workouts;
import com.uptown.gym.trainee.retrofit.WorkoutPlanClient;
import com.uptown.gym.trainee.retrofit.utils.ApiResponse;
import com.uptown.gym.trainee.retrofit.utils.ApiResponseListener;
import com.uptown.gym.trainee.retrofit.utils.RetrofitClient;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class WorkoutPlanRepository {


    private static WorkoutPlanClient workoutPlanClient;

    public WorkoutPlanRepository() {
        if (workoutPlanClient == null) {
            workoutPlanClient = RetrofitClient.getInstance().create(WorkoutPlanClient.class);
        }
    }

    public MutableLiveData<MainResponse<List<Workouts>>> findAllWorkouts(long workoutId, int weekNumber) {
        MutableLiveData<MainResponse<List<Workouts>>> workoutsResponse = new MutableLiveData<>();
        workoutPlanClient.findAllWorkouts(workoutId, weekNumber)
                .enqueue(new ApiResponse(new ApiResponseListener<MainResponse<List<Workouts>>>() {
                    @Override
                    public void onSuccess(MainResponse<List<Workouts>> response) {
                        workoutsResponse.postValue(response);
                    }

                    @Override
                    public void onFailure(String message) {
                        MainResponse<List<Workouts>> mainResponse = new MainResponse<>();
                        mainResponse.setMessage(message);
                        mainResponse.setResponse(null);
                        workoutsResponse.postValue(mainResponse);
                    }
                }));

        return workoutsResponse;
    }

    public MutableLiveData<MainResponse<List<WorkoutPlansResponse>>> findAllWorkoutPlansRequest() {
        MutableLiveData<MainResponse<List<WorkoutPlansResponse>>> workoutPlansResponse = new MutableLiveData<>();
        workoutPlanClient.findAllWorkoutPlans().enqueue(new ApiResponse(new ApiResponseListener<MainResponse<List<WorkoutPlansResponse>>>() {
            @Override
            public void onSuccess(MainResponse<List<WorkoutPlansResponse>> response) {
                workoutPlansResponse.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<List<WorkoutPlansResponse>> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                workoutPlansResponse.postValue(mainResponse);
            }
        }));

        return workoutPlansResponse;
    }

    public MutableLiveData<MainResponse<List<WorkoutPlansResponse>>> findAllWorkoutPlansByCategory(String category) {
        Category queryCategory;
        switch (category) {
            case "Get Fit":
                queryCategory = Category.GET_FIT;
                break;
            case "Weight Loss":
                queryCategory = Category.WEIGHT_LOSS;
                break;
            case "Gain Strength":
                queryCategory = Category.GAIN_STRENGTH;
                break;
            case "Performance":
                queryCategory = Category.PERFORMANCE;
                break;
            default:
                queryCategory = Category.BUILD_MUSCLE;
                break;
        }

        MutableLiveData<MainResponse<List<WorkoutPlansResponse>>> workoutPlansResponse = new MutableLiveData<>();
        workoutPlanClient.findAllWorkoutPlansByCategory(queryCategory).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<List<WorkoutPlansResponse>>>() {
            @Override
            public void onSuccess(MainResponse<List<WorkoutPlansResponse>> response) {
                workoutPlansResponse.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<List<WorkoutPlansResponse>> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                workoutPlansResponse.postValue(mainResponse);
            }
        }));

        return workoutPlansResponse;
    }

    public MutableLiveData<MainResponse<WorkoutPlanResponse>> findWorkoutPlanByIdRequest(long workoutPlanId) {
        MutableLiveData<MainResponse<WorkoutPlanResponse>> workoutPlanResponse = new MutableLiveData<>();
        workoutPlanClient.findWorkoutPlanById(workoutPlanId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<WorkoutPlanResponse>>() {
            @Override
            public void onSuccess(MainResponse<WorkoutPlanResponse> response) {
                workoutPlanResponse.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<WorkoutPlanResponse> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                workoutPlanResponse.postValue(mainResponse);
            }
        }));

        return workoutPlanResponse;
    }

    public MutableLiveData<MainResponse<List<WorkoutExercisesResponse>>> findAllWorkoutExercises(long workoutId) {
        MutableLiveData<MainResponse<List<WorkoutExercisesResponse>>> workoutExercisesResponse = new MutableLiveData<>();
        workoutPlanClient.findAllWorkoutExercises(workoutId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<List<WorkoutExercisesResponse>>>() {
            @Override
            public void onSuccess(MainResponse<List<WorkoutExercisesResponse>> response) {
                workoutExercisesResponse.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<List<WorkoutExercisesResponse>> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                workoutExercisesResponse.postValue(mainResponse);
            }
        }));

        return workoutExercisesResponse;
    }


    // ****** Fitness Session ****** //

    public MutableLiveData<MainResponse<FitnessSessionResponse>> createFitnessSession(long ongoingWorkoutPlanId,
                                                                                      long ongoingWorkoutPlanDayTimeId,
                                                                                      long userId) {
        MutableLiveData<MainResponse<FitnessSessionResponse>> createFitnessSessionResponse = new MutableLiveData<>();
        workoutPlanClient.createFitnessSession(ongoingWorkoutPlanId, ongoingWorkoutPlanDayTimeId, userId).
                enqueue(new ApiResponse(new ApiResponseListener<MainResponse<FitnessSessionResponse>>() {
                    @Override
                    public void onSuccess(MainResponse<FitnessSessionResponse> response) {
                        createFitnessSessionResponse.postValue(response);
                    }

                    @Override
                    public void onFailure(String message) {
                        MainResponse<FitnessSessionResponse> mainResponse = new MainResponse<>();
                        mainResponse.setMessage(message);
                        mainResponse.setResponse(null);
                        createFitnessSessionResponse.postValue(mainResponse);
                    }
                }));

        return createFitnessSessionResponse;
    }
}
