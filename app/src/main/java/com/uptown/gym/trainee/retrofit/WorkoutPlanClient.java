package com.uptown.gym.trainee.retrofit;


import com.uptown.gym.trainee.model.exercise.WorkoutExercisesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.FitnessSessionResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.FitnessSessionTraineesResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.WorkoutPlanTraineesResponse;
import com.uptown.gym.trainee.model.trainee.Evaluation;
import com.uptown.gym.trainee.model.workout.Workout;
import com.uptown.gym.trainee.model.workoutplan.Category;
import com.uptown.gym.trainee.model.workoutplan.PlanDayWorkoutResponse;
import com.uptown.gym.trainee.model.workoutplan.WorkoutExercise;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanRequest;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlans;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlansResponse;
import com.uptown.gym.trainee.model.workoutplan.Workouts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WorkoutPlanClient {

    /**
     * API to create a new workout plan
     *
     * @param request used to get the request body
     * @return WorkoutPlanResponseDTO
     */
    @POST("api/v1/workout-plans")
    Call<WorkoutPlanResponse> createWorkoutPlan(@Body WorkoutPlanRequest request);

    /**
     * API to find a specific workout plan
     *
     * @param workoutPlanId used to get the workout-plan-Id
     * @return Call<WorkoutPlanResponseDTO>
     */
    @GET(value = "api/v1/workout-plans/{workoutPlanId}")
    Call<WorkoutPlanResponse> findWorkoutPlanById(@Path("workoutPlanId") final long workoutPlanId);

    /**
     * API to find all workout plans
     *
     * @return Call<WorkoutPlansResponse>
     */
    @GET(value = "api/v1/workout-plans")
    Call<List<WorkoutPlansResponse>> findAllWorkoutPlans();

    @GET(value = "api/v1/workoutplans")
    Call<WorkoutPlans> findAllWorkoutPlansNames();

    @GET("api/v1/workout-plans/{workoutPlanId}/workouts")
    Call<List<Workouts>> findAllWorkouts(@Path("workoutPlanId") long workoutId, @Query("weekNumber") String weekNumber);


    /**
     * API to find all workout plans filtered by category
     *
     * @return Call<WorkoutPlansResponse>
     */
    @GET(value = "api/v1/workout-plans")
    Call<List<WorkoutPlansResponse>> findAllWorkoutPlansByCategory(@Query("category") Category category);

    /**
     * API to update a specific workout plan
     *
     * @param workoutPlanId used to get the workout-plan-Id
     * @param request       used to get the request body
     * @return Call<WorkoutPlanResponse>
     */
    @PUT(value = "api/v1/workout-plans/{workoutPlanId}")
    Call<WorkoutPlanResponse> updateWorkoutPlan(@Path("workoutPlanId") long workoutPlanId, @Body WorkoutPlanRequest request);

    /**
     * API to delete a specific workout plan
     *
     * @param workoutPlanId used to get the workout-plan-Id
     */
    @DELETE(value = "api/v1/workout-plans/{workoutPlanId}")
    Call<Void> deleteWorkoutPlan(@Path("workoutPlanId") long workoutPlanId);


    // ***** Plan day workout APIs***** //

    /**
     * API to update workout plan day by adding workout name and training technique
     *
     * @param planDayId targeted plan day id
     * @param workout   workout object
     * @return planDayWorkoutResponse
     */
    @PUT("api/v1/workouts/{workoutId}")
    Call<PlanDayWorkoutResponse> updateWorkout(@Path("workoutId") long planDayId, @Body Workout workout);

    /**
     * API to find Workout plan Workout by id
     *
     * @param planDayId targeted Workout plan day id
     * @return Workout Object
     */
    @GET("api/v1/workouts/{workoutId}")
    Call<Workouts> findWorkoutById(@Path("workoutId") long planDayId);


    /**
     * API to create new Workout exercise
     *
     * @param workoutId       targeted workout id
     * @param exerciseId      selected exercise id
     * @param workoutExercise workout exercise object
     * @return void
     */
    @POST("api/v1/workouts/{workoutId}/exercises/{exerciseId}/workout-exercises")
    Call<WorkoutExercise> createWorkoutExercise(@Path("workoutId") long workoutId, @Path("exerciseId") long exerciseId, @Body WorkoutExercise workoutExercise);


    /**
     * API to update workout exercises
     *
     * @param workoutId         targeted workout id
     * @param exerciseId        targeted exercise id
     * @param workoutExerciseId targeted workout exercise id
     * @param workoutExercise   workout exercise object
     * @return WorkoutExercise
     */
    @PUT("api/v1/workouts/{workoutId}/exercises/{exerciseId}/workout-exercises/{workoutExerciseId}")
    Call<WorkoutExercise> updateWorkoutExercise(@Path("workoutId") long workoutId, @Path("exerciseId") long exerciseId, @Path("workoutExerciseId") long workoutExerciseId, @Body WorkoutExercise workoutExercise);


    /**
     * API to delete workout exercises
     *
     * @param workoutId         targeted workoutID
     * @param exerciseId        targeted exercise id
     * @param workoutExerciseId targeted workout exercise id
     * @return Void
     */
    @DELETE("api/v1/workouts/{workoutId}/exercises/{exerciseId}/workout-exercises/{workoutExerciseId}")
    Call<Void> deleteWorkoutExercise(@Path("workoutId") long workoutId, @Path("exerciseId") long exerciseId, @Path("workoutExerciseId") long workoutExerciseId);


    /**
     * API to find all workout exercises
     *
     * @param workoutId targeted workout id
     * @return list<WorkoutExercisesResponse>
     */
    @GET("api/v1/workouts/{workoutId}/exercises")
    Call<List<WorkoutExercisesResponse>> findAllWorkoutExercises(@Path("workoutId") long workoutId);


    //  ****** Fitness Session ******

    @POST("api/v1/ongoing-workout-plans/{ongoingWorkoutPlanId}/active-workout-plan-day-times/{ongoingWorkoutPlanDayTimeId}/users/{userId}/fitness-sessions")
    Call<FitnessSessionResponse> createFitnessSession(@Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId,
                                                      @Path("ongoingWorkoutPlanDayTimeId") long ongoingWorkoutPlanDayTimeId,
                                                      @Path("userId") long userId);

    @GET("api/v1/ongoing-workout-plans/{ongoingWorkoutPlanId}/ongoing-workout-plan-day-times/{ongoingWorkoutPlanDayTimeId}/users")
    Call<FitnessSessionTraineesResponse> findAllDayTimesTrainees(@Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId,
                                                                 @Path("ongoingWorkoutPlanDayTimeId") long ongoingWorkoutPlanDayTimeId);

    @POST("api/v1/fitness-sessions/{fitnessSessionId}/evaluations")
    Call<Evaluation> createEvaluation(@Path("fitnessSessionId") long fitnessSessionId, @Body Evaluation evaluation);

    @GET("api/v1/ongoing-workout-plans/{ongoingWorkoutPlanId}/users")
    Call<WorkoutPlanTraineesResponse> findAllWorkoutPlanTrainees(long ongoingWorkoutPlanId);

    @GET("api/v1/ongoing-workout-plans/{ongoingWorkoutPlanId}/users")
    Call<WorkoutPlanTraineesResponse> findWorkoutPlanTraineeByPhoneNumber(@Path("ongoingWorkoutPlanId") long ongoingWorkoutPlanId);
}
