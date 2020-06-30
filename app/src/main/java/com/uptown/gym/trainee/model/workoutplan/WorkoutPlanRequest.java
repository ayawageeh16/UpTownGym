package com.uptown.gym.trainee.model.workoutplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WorkoutPlanRequest {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("fitnessLevel")
    @Expose
    private String fitnessLevel;
    @SerializedName("name")
    @Expose
    private String name;
}
