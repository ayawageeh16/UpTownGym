package com.uptown.gym.trainee.repository;

import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTimesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlansResponse;
import com.uptown.gym.trainee.retrofit.OnGoingWorkoutPlanClient;
import com.uptown.gym.trainee.retrofit.utils.ApiResponse;
import com.uptown.gym.trainee.retrofit.utils.ApiResponseListener;
import com.uptown.gym.trainee.retrofit.utils.RetrofitClient;

import androidx.lifecycle.MutableLiveData;

public class OnGoingWorkoutPlanRepository {

    private OnGoingWorkoutPlanClient onGoingWorkoutPlanClient;

    public OnGoingWorkoutPlanRepository() {
        onGoingWorkoutPlanClient = getOnGoingWorkoutPlanClient();
    }

    private OnGoingWorkoutPlanClient getOnGoingWorkoutPlanClient() {
        if (onGoingWorkoutPlanClient == null) {
            onGoingWorkoutPlanClient = RetrofitClient.getInstance().create(OnGoingWorkoutPlanClient.class);
            return onGoingWorkoutPlanClient;
        } else {
            return onGoingWorkoutPlanClient;
        }
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> findAllOnGoingWorkoutPlansByGender(long userId) {
        MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> allOnGoingWorkoutPlans = new MutableLiveData<>();
        onGoingWorkoutPlanClient.findAllOnGoingWorkoutPlans(userId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<OnGoingWorkoutPlansResponse>>() {
            @Override
            public void onSuccess(MainResponse<OnGoingWorkoutPlansResponse> response) {
                allOnGoingWorkoutPlans.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<OnGoingWorkoutPlansResponse> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                allOnGoingWorkoutPlans.postValue(mainResponse);
            }
        }));
        return allOnGoingWorkoutPlans;
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlan>> findOnGoingWorkoutPlanById(long workoutPlanId, long onGoingWorkoutPlanId) {
        MutableLiveData<MainResponse<OnGoingWorkoutPlan>> onGoingWorkoutPlan = new MutableLiveData<>();
        onGoingWorkoutPlanClient.findOnGoingWorkoutPlanById(workoutPlanId, onGoingWorkoutPlanId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<OnGoingWorkoutPlan>>() {
            @Override
            public void onSuccess(MainResponse<OnGoingWorkoutPlan> response) {
                onGoingWorkoutPlan.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<OnGoingWorkoutPlan> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                onGoingWorkoutPlan.postValue(mainResponse);
            }
        }));
        return onGoingWorkoutPlan;
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlanDayTimesResponse>> findAllOnGoingWorkoutPlanDayTime(long workoutPlanId, long ongoingWorkoutPlanId, long workoutId) {
        MutableLiveData<MainResponse<OnGoingWorkoutPlanDayTimesResponse>> allOnGoingWorkoutPlanDayTimes = new MutableLiveData<>();
        onGoingWorkoutPlanClient.findAllOnGoingWorkoutPlanDayTimes(workoutPlanId, ongoingWorkoutPlanId, workoutId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<OnGoingWorkoutPlanDayTimesResponse>>() {
            @Override
            public void onSuccess(MainResponse<OnGoingWorkoutPlanDayTimesResponse> response) {
                allOnGoingWorkoutPlanDayTimes.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<OnGoingWorkoutPlanDayTimesResponse> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                allOnGoingWorkoutPlanDayTimes.postValue(mainResponse);
            }
        }));
        return allOnGoingWorkoutPlanDayTimes;
    }
}
