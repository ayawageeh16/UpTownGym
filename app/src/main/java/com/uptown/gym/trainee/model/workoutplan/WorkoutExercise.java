package com.uptown.gym.trainee.model.workoutplan;

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
public class WorkoutExercise implements Parcelable {

    @SerializedName("countX")
    @Expose
    private int countX;
    @SerializedName("countY")
    @Expose
    private int countY;
    @SerializedName("exerciseType")
    @Expose
    private String exerciseType;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("rest")
    @Expose
    private String rest;

    private WorkoutExercise(Parcel in) {
        countX = in.readInt();
        countY = in.readInt();
        exerciseType = in.readString();
        id = in.readLong();
        rest = in.readString();
    }

    public static final Creator<WorkoutExercise> CREATOR = new Creator<WorkoutExercise>() {
        @Override
        public WorkoutExercise createFromParcel(Parcel in) {
            return new WorkoutExercise(in);
        }

        @Override
        public WorkoutExercise[] newArray(int size) {
            return new WorkoutExercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(countX);
        dest.writeInt(countY);
        dest.writeString(exerciseType);
        dest.writeLong(id);
        dest.writeString(rest);
    }
}
