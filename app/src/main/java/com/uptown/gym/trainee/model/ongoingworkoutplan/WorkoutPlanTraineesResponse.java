package com.uptown.gym.trainee.model.ongoingworkoutplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class WorkoutPlanTraineesResponse extends PagingResponse {

    @SerializedName("content")
    @Expose

    private List<WorkoutPlanTrainee> content = null;
}
