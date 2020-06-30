package com.uptown.gym.trainee.model.workoutplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanDayWorkoutResponse {

    @SerializedName("dayOfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("trainingTechnique")
    @Expose
    private String trainingTechnique;
    @SerializedName("weekNumber")
    @Expose
    private String weekNumber;
}
