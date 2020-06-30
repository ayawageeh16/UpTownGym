package com.uptown.gym.trainee.repository;

import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTime;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTimesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlansResponse;
import com.uptown.gym.trainee.retrofit.OnGoingWorkoutPlanClient;
import com.uptown.gym.trainee.retrofit.utils.ApiResponse;
import com.uptown.gym.trainee.retrofit.utils.ApiResponseListener;
import com.uptown.gym.trainee.retrofit.utils.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        onGoingWorkoutPlanClient.findAllOnGoingWorkoutPlansByGender(userId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<OnGoingWorkoutPlansResponse>>() {
            @Override
            public void onSuccess(MainResponse<OnGoingWorkoutPlansResponse> response) {
                allOnGoingWorkoutPlans.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<OnGoingWorkoutPlansResponse> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                allOnGoingWorkoutPlans.postValue(mainResponse);
            }
        }));
        return allOnGoingWorkoutPlans;
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> findAllOnGoingWorkoutPlansByTarget(String target) {
        MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> allOnGoingWorkoutPlans = new MutableLiveData<>();
        onGoingWorkoutPlanClient.findAllOnGoingWorkoutPlansByTarget(target).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<OnGoingWorkoutPlansResponse>>() {
            @Override
            public void onSuccess(MainResponse<OnGoingWorkoutPlansResponse> response) {
                allOnGoingWorkoutPlans.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<OnGoingWorkoutPlansResponse> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                allOnGoingWorkoutPlans.postValue(mainResponse);
            }
        }));
        return allOnGoingWorkoutPlans;
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> findAllOnGoingWorkoutPlansByGenderAndTarget(String gender, String target) {
        MutableLiveData<MainResponse<OnGoingWorkoutPlansResponse>> allOnGoingWorkoutPlans = new MutableLiveData<>();
        onGoingWorkoutPlanClient.findAllOnGoingWorkoutPlansByGenderAndTarget(gender, target).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<OnGoingWorkoutPlansResponse>>() {
            @Override
            public void onSuccess(MainResponse<OnGoingWorkoutPlansResponse> response) {
                allOnGoingWorkoutPlans.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<OnGoingWorkoutPlansResponse> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                allOnGoingWorkoutPlans.postValue(mainResponse);
            }
        }));
        return allOnGoingWorkoutPlans;
    }

    public MutableLiveData<Boolean> createOnGoingWorkoutPlan(long workoutPlanId, OnGoingWorkoutPlan ongoingWorkoutPlan) {
        final MutableLiveData<Boolean> program = new MutableLiveData<>();
        Call<Void> call = onGoingWorkoutPlanClient.createOnGoingWorkoutPlan(workoutPlanId, ongoingWorkoutPlan);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 201) {
                    program.setValue(true);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                program.setValue(false);
            }
        });

        return program;
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
                onGoingWorkoutPlan.postValue(mainResponse);
            }
        }));
        return onGoingWorkoutPlan;
    }

    public MutableLiveData<Boolean> updateOnGoingWorkoutPlan(long workoutPlanId, long onGoingWorkoutPlanId, OnGoingWorkoutPlan ongoingWorkoutPlan) {
        final MutableLiveData<Boolean> updatedOnGoingWorkoutPlan = new MutableLiveData<>();
        Call<Void> call = onGoingWorkoutPlanClient.updateOnGoingWorkoutPlan(workoutPlanId, onGoingWorkoutPlanId, ongoingWorkoutPlan);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 200 || response.code() == 201) {
                    updatedOnGoingWorkoutPlan.postValue(true);
                } else {
                    updatedOnGoingWorkoutPlan.postValue(false);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                updatedOnGoingWorkoutPlan.postValue(null);
            }
        });
        return updatedOnGoingWorkoutPlan;
    }

    public MutableLiveData<Boolean> deleteOnGoingWorkoutPlan(long workoutPlanId, long onGoingWorkoutPlanId) {
        final MutableLiveData<Boolean> isDeleted = new MutableLiveData<>();
        Call<Void> call = onGoingWorkoutPlanClient.deleteOnGoingWorkoutPlan(workoutPlanId, onGoingWorkoutPlanId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 200 || response.code() == 204) {
                    isDeleted.postValue(true);
                } else {
                    isDeleted.postValue(false);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                isDeleted.postValue(null);
            }
        });
        return isDeleted;
    }

    public MutableLiveData<MainResponse<OnGoingWorkoutPlanDayTime>> createOnGoingWorkoutPlanDayTime(long workoutPlanId, long ongoingWorkoutPlanId, OnGoingWorkoutPlanDayTime onGoingWorkoutPlanDayTime) {
        MutableLiveData<MainResponse<OnGoingWorkoutPlanDayTime>> onGoingWorkoutPlanDayTimeResponse = new MutableLiveData<>();
        onGoingWorkoutPlanClient.createOngoingWorkoutPlanDayTime(workoutPlanId, ongoingWorkoutPlanId, onGoingWorkoutPlanDayTime).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<OnGoingWorkoutPlanDayTime>>() {
            @Override
            public void onSuccess(MainResponse<OnGoingWorkoutPlanDayTime> response) {
                onGoingWorkoutPlanDayTimeResponse.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<OnGoingWorkoutPlanDayTime> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                onGoingWorkoutPlanDayTimeResponse.postValue(mainResponse);
            }
        }));
        return onGoingWorkoutPlanDayTimeResponse;
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
                allOnGoingWorkoutPlanDayTimes.postValue(mainResponse);
            }
        }));
        return allOnGoingWorkoutPlanDayTimes;
    }
}
