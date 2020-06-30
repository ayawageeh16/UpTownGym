package com.uptown.gym.trainee.model.trainee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassTrainee {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("fitnessSessionId")
    @Expose
    private long fitnessSessionId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("lastName")
    @Expose
    private String lastName;

}
