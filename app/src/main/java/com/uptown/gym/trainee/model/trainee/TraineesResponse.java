package com.uptown.gym.trainee.model.trainee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TraineesResponse extends PagingResponse {

    @SerializedName("content")
    @Expose
    private List<Trainee> trainees = null;
}
