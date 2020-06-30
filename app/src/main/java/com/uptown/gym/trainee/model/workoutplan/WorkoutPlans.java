package com.uptown.gym.trainee.model.workoutplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WorkoutPlans extends PagingResponse {
    @SerializedName("content")
    @Expose
    private List<WorkoutPlanResponse> content = null;
}
