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


    @GET("api/v1/workout-plans/{workoutPlanId}/workouts")
    Call<List<Workouts>> findAllWorkouts(@Path("workoutPlanId") long workoutId, @Query("weekNumber") int weekNumber);


    /**
     * API to find all workout plans filtered by category
     *
     * @return Call<WorkoutPlansResponse>
     */
    @GET(value = "api/v1/workout-plans")
    Call<List<WorkoutPlansResponse>> findAllWorkoutPlansByCategory(@Query("category") Category category);



    // ***** Plan day workout APIs***** //

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


}
