package com.uptown.gym.trainee.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Sort_ {

    @SerializedName("sorted")
    @Expose
    private Boolean sorted;
    @SerializedName("unsorted")
    @Expose
    private Boolean unsorted;
    @SerializedName("empty")
    @Expose
    private Boolean empty;

}
