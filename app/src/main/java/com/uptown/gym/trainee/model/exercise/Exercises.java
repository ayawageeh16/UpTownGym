package com.uptown.gym.trainee.model.exercise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exercises extends PagingResponse {

    @SerializedName("content")
    @Expose
    private List<Exercise> content = null;
}
