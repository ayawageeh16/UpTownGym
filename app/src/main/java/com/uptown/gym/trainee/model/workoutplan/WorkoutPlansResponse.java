package com.uptown.gym.trainee.model.workoutplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutPlansResponse extends PagingResponse {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("workoutPlans")
    @Expose
    private WorkoutPlans workoutPlans;
}
