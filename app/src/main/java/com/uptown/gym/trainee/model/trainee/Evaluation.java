package com.uptown.gym.trainee.model.trainee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Evaluation {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("exerciseId")
    @Expose
    private long exerciseId;
    @SerializedName("score")
    @Expose
    private Integer score;
}
