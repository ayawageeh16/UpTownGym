package com.uptown.gym.trainee.model.fitnesstest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FitnessTestsResponse extends PagingResponse {

    @SerializedName("content")
    @Expose
    private List<FitnessTest> content = null;
}
