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

    // ****** Trainees ****** //

    @GET("api/v1/users")
    Call<TraineesResponse> findAllUsers(@Query("accountType") String accountType,
                                        @Query("fitnessTest") boolean fitnessTest,
                                        @Query("inBody") boolean inBody);

    @GET("api/v1/users/{userId}")
    Call<Trainee> findUserById(@Path("userId") long userId, @Query("accountType") String accountType);

    @GET("api/v1/users")
    Call<TraineesResponse> filterAllUsersByMembership(@Query("accountType") String accountType,
                                                      @Query("type") Membership type,
                                                      @Query("fitnessTest") boolean fitnessTest,
                                                      @Query("inBody") boolean inBody);

    @GET("api/v1/users")
    Call<TraineesResponse> filterAllUsersByGender(@Query("accountType") String accountType,
                                                  @Query("gender") String gender,
                                                  @Query("fitnessTest") boolean fitnessTest,
                                                  @Query("inBody") boolean inBody);

    @GET("api/v1/users")
    Call<TraineesResponse> filterAllUsersByGenderAndMembership(@Query("accountType") String accountType,
                                                               @Query("type") Membership type,
                                                               @Query("gender") String gender,
                                                               @Query("fitnessTest") boolean fitnessTest,
                                                               @Query("inBody") boolean inBody);


    // ****** New Trainees ****** //

    @GET("api/v1/users")
    Call<NewTrainees> findALLNewTraineesByMembership(@Query("accountType") String accountType,
                                                     @Query("type") String type,
                                                     @Query("fitnessTest") boolean fitnessTest,
                                                     @Query("inBody") boolean inBody);


    // ****** Fitness test ****** //

    @POST("api/v1/users/{userId}/fitness-tests")
    Call<FitnessTest> createFitnessTest(@Path("userId") long traineeId, @Body FitnessTest fitnessTest);

    @GET("api/v1/users/{userId}/fitness-tests")
    Call<FitnessTestsResponse> findAllFitnessTests(@Path("userId") long userId);

    @GET("api/v1/users/{userId}/fitness-tests/{fitnessTestId}")
    Call<FitnessTest> findFitnessTestById(@Path("userId") long userId, @Path("fitnessTestId") long fitnessTestId);

    @PUT("api/v1/users/{userId}/fitness-tests/{fitnessTestId}")
    Call<FitnessTest> updateFitnessTest(@Path("userId") long userId, @Path("fitnessTestId") long fitnessTestId, @Body FitnessTest fitnessTest);

    // ****** InBody ****** //

    @POST("api/v1/users/{userId}/in-bodies")
    Call<InBody> createInBody(@Path("userId") long userId, @Body InBody inBody);

    @GET("api/v1/users/{userId}/in-bodies")
    Call<InBodies> findAllInBodies(@Path("userId") long userId);

    @GET("api/v1/users/{userId}/in-bodies/{inBodyId}")
    Call<InBody> findInBodyById(@Path("userId") long userId, @Path("inBodyId") long inBodyId);

    @PUT("api/v1/users/{userId}/in-bodies/{inBodyId}")
    Call<InBody> updateInBody(@Path("userId") long userId, @Path("inBodyId") long inBodyId, @Body InBody inBody);


    // ****** Enrollment ****** //

    @POST("api/v1/ongoing-workout-plans/{ongoingWorkoutPlanId}/users/{userId}")
    Call<Void> enrollTraineeToOnGoingWorkoutPlan(@Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId, @Path("userId") long userId);

}
