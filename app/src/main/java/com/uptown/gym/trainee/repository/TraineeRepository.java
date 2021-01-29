package com.uptown.gym.trainee.repository;


import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTestsResponse;
import com.uptown.gym.trainee.model.inbody.InBodies;
import com.uptown.gym.trainee.model.inbody.InBody;
import com.uptown.gym.trainee.model.trainee.Trainee;
import com.uptown.gym.trainee.retrofit.TraineeClient;
import com.uptown.gym.trainee.retrofit.utils.ApiResponse;
import com.uptown.gym.trainee.retrofit.utils.ApiResponseListener;
import com.uptown.gym.trainee.retrofit.utils.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraineeRepository {

    private TraineeClient traineeClient;

    public TraineeRepository() {
        traineeClient = getTraineeClient();
    }

    private TraineeClient getTraineeClient() {
        if (traineeClient == null) {
            traineeClient = RetrofitClient.getInstance().create(TraineeClient.class);
            return traineeClient;
        } else {
            return traineeClient;
        }
    }

    // ************ Trainees ****************//

    public MutableLiveData<MainResponse<Trainee>> findTraineeById(long id) {
        MutableLiveData<MainResponse<Trainee>> allTrainees = new MutableLiveData<>();
        traineeClient.findUserById(id, "TRAINEE").enqueue(new ApiResponse(new ApiResponseListener<MainResponse<Trainee>>() {
            @Override
            public void onSuccess(MainResponse<Trainee> response) {
                allTrainees.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<Trainee> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                allTrainees.postValue(mainResponse);
            }
        }));

        return allTrainees;
    }


    // ************ FitnessTest ****************//

    public MutableLiveData<MainResponse<FitnessTestsResponse>> findAllFitnessTests(long traineeId) {
        MutableLiveData<MainResponse<FitnessTestsResponse>> fitnessTests = new MutableLiveData<>();
        traineeClient.findAllFitnessTests(traineeId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<FitnessTestsResponse>>() {
            @Override
            public void onSuccess(MainResponse<FitnessTestsResponse> response) {
                fitnessTests.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<FitnessTestsResponse> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                fitnessTests.postValue(mainResponse);
            }
        }));
        return fitnessTests;
    }

    public MutableLiveData<MainResponse<FitnessTest>> findFitnessTestById(long traineeId, long fitnessTestId) {
        MutableLiveData<MainResponse<FitnessTest>> fitnessTestModel = new MutableLiveData<>();
        traineeClient.findFitnessTestById(traineeId, fitnessTestId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<FitnessTest>>() {
            @Override
            public void onSuccess(MainResponse<FitnessTest> response) {
                fitnessTestModel.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<FitnessTest> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                fitnessTestModel.postValue(mainResponse);
            }
        }));
        return fitnessTestModel;
    }

    // ************ InBody ****************//

    public MutableLiveData<InBody> findInBodyById(long traineeId, long inBodyId) {
        MutableLiveData<InBody> inBodyModel = new MutableLiveData<>();
        Call<InBody> call = traineeClient.findInBodyById(traineeId, inBodyId);

        call.enqueue(new Callback<InBody>() {
            @Override
            public void onResponse(@NotNull Call<InBody> call, @NotNull Response<InBody> response) {
                if (response.body() != null) {
                    inBodyModel.setValue(response.body());
                } else {
                    inBodyModel.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<InBody> call, Throwable t) {
                inBodyModel.setValue(null);
            }
        });
        return inBodyModel;
    }

    public MutableLiveData<MainResponse<InBodies>> findAllInBody(long traineeId) {
        MutableLiveData<MainResponse<InBodies>> inBodies = new MutableLiveData<>();
        traineeClient.findAllInBodies(traineeId).enqueue(new ApiResponse(new ApiResponseListener<MainResponse<InBodies>>() {
            @Override
            public void onSuccess(MainResponse<InBodies> response) {
                inBodies.postValue(response);
            }

            @Override
            public void onFailure(String message) {
                MainResponse<InBodies> mainResponse = new MainResponse<>();
                mainResponse.setMessage(message);
                mainResponse.setResponse(null);
                inBodies.postValue(mainResponse);
            }
        }));

        return inBodies;
    }

}
