package com.uptown.gym.trainee.model.ongoingworkoutplan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OnGoingWorkoutPlanDayTime implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("dayTime")
    @Expose
    private long dayTime;
    @SerializedName("workoutId")
    @Expose
    private long workoutId;

    protected OnGoingWorkoutPlanDayTime(Parcel in) {
        id = in.readLong();
        dayTime = in.readLong();
        workoutId = in.readLong();
    }

    public static final Creator<OnGoingWorkoutPlanDayTime> CREATOR = new Creator<OnGoingWorkoutPlanDayTime>() {
        @Override
        public OnGoingWorkoutPlanDayTime createFromParcel(Parcel in) {
            return new OnGoingWorkoutPlanDayTime(in);
        }

        @Override
        public OnGoingWorkoutPlanDayTime[] newArray(int size) {
            return new OnGoingWorkoutPlanDayTime[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(dayTime);
        dest.writeLong(workoutId);
    }
}
