package com.uptown.gym.trainee.model.ongoingworkoutplan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlan;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OnGoingWorkoutPlan implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("startTime")
    @Expose
    private long startTime;
    @SerializedName("endTime")
    @Expose
    private long endTime;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("workoutPlan")
    @Expose
    private WorkoutPlanResponse workoutPlan;

    protected OnGoingWorkoutPlan(Parcel in) {
        id = in.readLong();
        gender = in.readString();
        startTime = in.readLong();
        endTime = in.readLong();
        target = in.readString();
        workoutPlan = in.readParcelable(WorkoutPlan.class.getClassLoader());
    }

    public static final Creator<OnGoingWorkoutPlan> CREATOR = new Creator<OnGoingWorkoutPlan>() {
        @Override
        public OnGoingWorkoutPlan createFromParcel(Parcel in) {
            return new OnGoingWorkoutPlan(in);
        }

        @Override
        public OnGoingWorkoutPlan[] newArray(int size) {
            return new OnGoingWorkoutPlan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(gender);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeString(target);
        dest.writeParcelable(workoutPlan, flags);
    }
}
