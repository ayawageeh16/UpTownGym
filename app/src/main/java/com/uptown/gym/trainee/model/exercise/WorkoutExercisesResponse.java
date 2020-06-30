package com.uptown.gym.trainee.model.exercise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutExercisesResponse extends PagingResponse {

    @SerializedName("exerciseType")
    @Expose
    private String exerciseType;
    @SerializedName("exercises")
    @Expose
    private Exercises exercises;

}
