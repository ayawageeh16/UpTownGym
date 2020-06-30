package com.uptown.gym.trainee.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Pageable {

    @SerializedName("sort")
    @Expose
    private Sort sort;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("paged")
    @Expose
    private Boolean paged;
    @SerializedName("unpaged")
    @Expose
    private Boolean unpaged;

}
