package com.uptown.gym.trainee.retrofit;

import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTime;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTimesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlansResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OnGoingWorkoutPlanClient {


    @GET("api/v1/users/{userId}/ongoing-workout-plans")
    Call<OnGoingWorkoutPlansResponse> findAllOnGoingWorkoutPlansByGender(@Path("userId") long userId);

    @GET("api/v1/ongoing-workout-plans")
    Call<OnGoingWorkoutPlansResponse> findAllOnGoingWorkoutPlansByTarget(@Query("target") String target);

    @GET("api/v1/ongoing-workout-plans")
    Call<OnGoingWorkoutPlansResponse> findAllOnGoingWorkoutPlansByGenderAndTarget(@Query("gender") String gender,
                                                                                  @Query("target") String target);

    @POST("api/v1/workout-plans/{workoutPlanId}/ongoing-workout-plans")
    Call<Void> createOnGoingWorkoutPlan(@Path("workoutPlanId") long workoutPlanId,
                                        @Body OnGoingWorkoutPlan ongoingWorkoutPlan);

    @PUT("api/v1/workout-plans/{workoutPlanId}/ongoing-workout-plans/{ongoingWorkoutPlanId}")
    Call<Void> updateOnGoingWorkoutPlan(@Path("workoutPlanId") long workoutPlanId,
                                        @Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId,
                                        @Body OnGoingWorkoutPlan ongoingWorkoutPlan);

    @DELETE("api/v1/workout-plans/{workoutPlanId}/ongoing-workout-plans/{ongoingWorkoutPlanId}")
    Call<Void> deleteOnGoingWorkoutPlan(@Path("workoutPlanId") long workoutPlanId,
                                        @Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId);

    @GET("api/v1/workout-plans/{workoutPlanId}/ongoing-workout-plans/{ongoingWorkoutPlanId}")
    Call<OnGoingWorkoutPlan> findOnGoingWorkoutPlanById(@Path("workoutPlanId") long workoutPlanId,
                                                        @Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId);

    @POST("api/v1/workout-plans/{workoutPlanId}/ongoing-workout-plans/{ongoingWorkoutPlanId}/ongoing-workout-plan-day-times")
    Call<OnGoingWorkoutPlanDayTime> createOngoingWorkoutPlanDayTime(@Path("workoutPlanId") long workoutPlanId,
                                                                    @Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId,
                                                                    @Body OnGoingWorkoutPlanDayTime onGoingWorkoutPlanDayTime);

    @GET("api/v1/workout-plans/{workoutPlanId}/ongoing-workout-plans/{ongoingWorkoutPlanId}/ongoing-workout-plan-day-times")
    Call<OnGoingWorkoutPlanDayTimesResponse> findAllOnGoingWorkoutPlanDayTimes(@Path("workoutPlanId") long workoutPlanId,
                                                                               @Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId,
                                                                               @Query("workoutId") long workoutId);

    @POST("api/v1/ongoing-workout-plans/{ongoingWorkoutPlanId}/users/{userId}")
    Call<Void> addUserToOnGoingWorkoutPlan(@Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId, @Path("userId") long userId);

}
