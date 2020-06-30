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
public class Workouts implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("weekNumber")
    @Expose
    private String weekNumber;
    @SerializedName("dayOfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("trainingTechnique")
    @Expose
    private String trainingTechnique;

    protected Workouts(Parcel in) {
        id = in.readLong();
        weekNumber = in.readString();
        dayOfWeek = in.readString();
        name = in.readString();
        trainingTechnique = in.readString();
    }

    public static final Creator<Workouts> CREATOR = new Creator<Workouts>() {
        @Override
        public Workouts createFromParcel(Parcel in) {
            return new Workouts(in);
        }

        @Override
        public Workouts[] newArray(int size) {
            return new Workouts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(weekNumber);
        dest.writeString(dayOfWeek);
        dest.writeString(name);
        dest.writeString(trainingTechnique);
    }
}
