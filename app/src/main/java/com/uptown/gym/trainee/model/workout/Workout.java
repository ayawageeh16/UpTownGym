package com.uptown.gym.trainee.model.workout;

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
public class Workout implements Parcelable {
    @SerializedName("dayOfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("trainingTechnique")
    @Expose
    private String trainingTechnique;
    @SerializedName("weekNumber")
    @Expose
    private String weekNumber;

    protected Workout(Parcel in) {
        dayOfWeek = in.readString();
        id = in.readLong();
        name = in.readString();
        trainingTechnique = in.readString();
        weekNumber = in.readString();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dayOfWeek);
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(trainingTechnique);
        dest.writeString(weekNumber);
    }
}
