package com.uptown.gym.trainee.model.workoutplan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class WorkoutPlanResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("fitnessLevel")
    @Expose
    private String fitnessLevel;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("workouts")
    @Expose
    private List<WorkoutPlan> workouts = null;

    protected WorkoutPlanResponse(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readInt();
        }
        fitnessLevel = in.readString();
        category = in.readString();
    }

    public static final Creator<WorkoutPlanResponse> CREATOR = new Creator<WorkoutPlanResponse>() {
        @Override
        public WorkoutPlanResponse createFromParcel(Parcel in) {
            return new WorkoutPlanResponse(in);
        }

        @Override
        public WorkoutPlanResponse[] newArray(int size) {
            return new WorkoutPlanResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        if (duration == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(duration);
        }
        dest.writeString(fitnessLevel);
        dest.writeString(category);
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
