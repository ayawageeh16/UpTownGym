package com.uptown.gym.trainee.retrofit;

import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTestsResponse;
import com.uptown.gym.trainee.model.inbody.InBodies;
import com.uptown.gym.trainee.model.inbody.InBody;
import com.uptown.gym.trainee.model.trainee.Membership;
import com.uptown.gym.trainee.model.trainee.NewTrainees;
import com.uptown.gym.trainee.model.trainee.Trainee;
import com.uptown.gym.trainee.model.trainee.TraineesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TraineeClient {

    // ****** Trainee ****** //

    @GET("api/v1/users/{userId}")
    Call<Trainee> findUserById(@Path("userId") long userId, @Query("accountType") String accountType);

    // ****** Fitness Tests ****** //

    @GET("api/v1/users/{userId}/fitness-tests")
    Call<FitnessTestsResponse> findAllFitnessTests(@Path("userId") long userId);

    @GET("api/v1/users/{userId}/fitness-tests/{fitnessTestId}")
    Call<FitnessTest> findFitnessTestById(@Path("userId") long userId, @Path("fitnessTestId") long fitnessTestId);

    // ****** InBodies ****** //

    @GET("api/v1/users/{userId}/body-compositions")
    Call<InBodies> findAllInBodies(@Path("userId") long userId);

    @GET("api/v1/users/{userId}/body-compositions/{bodyCompositionId}")
    Call<InBody> findInBodyById(@Path("userId") long userId, @Path("bodyCompositionId") long inBodyId);


    // ****** Enrollment ****** //

}
