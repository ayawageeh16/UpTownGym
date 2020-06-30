package com.uptown.gym.trainee.model.workoutplan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class WorkoutPlan implements Parcelable {

    @SerializedName("weekNumber")
    @Expose
    private int weekNumber;
    @SerializedName("workouts")
    @Expose
    private List<Workouts> workouts = null;


    protected WorkoutPlan(Parcel in) {
        weekNumber = in.readInt();
        workouts = in.createTypedArrayList(Workouts.CREATOR);
    }

    public static final Creator<WorkoutPlan> CREATOR = new Creator<WorkoutPlan>() {
        @Override
        public WorkoutPlan createFromParcel(Parcel in) {
            return new WorkoutPlan(in);
        }

        @Override
        public WorkoutPlan[] newArray(int size) {
            return new WorkoutPlan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(weekNumber);
        dest.writeTypedList(workouts);
    }
}
