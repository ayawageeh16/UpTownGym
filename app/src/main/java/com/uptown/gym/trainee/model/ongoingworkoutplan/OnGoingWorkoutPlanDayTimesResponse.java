package com.uptown.gym.trainee.model.ongoingworkoutplan;

import android.os.Parcelable;

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
public class OnGoingWorkoutPlanDayTimesResponse extends PagingResponse implements Parcelable {
    @SerializedName("content")
    @Expose
    private List<OnGoingWorkoutPlanDayTime> content = null;
}
