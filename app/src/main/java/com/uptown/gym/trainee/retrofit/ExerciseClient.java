package com.uptown.gym.trainee.retrofit;

import com.uptown.gym.trainee.model.exercise.Exercise;
import com.uptown.gym.trainee.model.exercise.Exercises;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ExerciseClient {

    /**
     * API to create a new exercise
     *
     * @param request used to get the request body
     * @return Call<Exercise>
     */
    @POST(value = "api/v1/exercises")
    Call<Exercise> createExercise(@Body Exercise request);

    /**
     * API to find a specific exercise
     *
     * @param exerciseId used to get the exercise-Id
     * @return Call<Exercise>
     */
    @GET(value = "api/v1/exercises/{exerciseId}")
    Call<Exercise> findExerciseById(@Path("exerciseId") final long exerciseId);

    /**
     * API to find all exercises
     *
     * @return Call<Exercises>
     */
    @GET(value = "api/v1/exercises")
    Call<Exercises> findAllExercises();

    /**
     * API to update a specific exercise
     *
     * @param exerciseId used to get the exercise-Id
     * @param request    used to get the request body
     * @return Call<Exercise>
     */
    @PUT(value = "api/v1/exercises/{exerciseId}")
    Call<Exercise> updateExercise(@Path("exerciseId") final long exerciseId, @Body Exercise request);

    /**
     * API to delete a specific exercise
     *
     * @param exerciseId used to get the exercise-Id
     * @return Call<Void>
     */
    @DELETE(value = "api/v1/exercises/{exerciseId}")
    Call<Void> deleteExercise(@Path("exerciseId") final long exerciseId);

    @Multipart
    @POST("api/v1/exercises/{exerciseId}/upload")
    Call<Exercise> uploadExerciseImage(@Path("exerciseId") long exerciseId, @Part MultipartBody.Part file);

}
