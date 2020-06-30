package com.uptown.gym.trainee.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Exercise implements Parcelable {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("exerciseType")
    @Expose
    private String exerciseType;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reps")
    @Expose
    private Integer reps;
    @SerializedName("rest")
    @Expose
    private String rest;
    @SerializedName("sets")
    @Expose
    private Integer sets;
    @SerializedName("workoutExerciseId")
    @Expose
    private long workoutExerciseId;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;


    protected Exercise(Parcel in) {
        description = in.readString();
        exerciseType = in.readString();
        id = in.readLong();
        name = in.readString();
        if (in.readByte() == 0) {
            reps = null;
        } else {
            reps = in.readInt();
        }
        rest = in.readString();
        if (in.readByte() == 0) {
            sets = null;
        } else {
            sets = in.readInt();
        }
        workoutExerciseId = in.readLong();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(exerciseType);
        dest.writeLong(id);
        dest.writeString(name);
        if (reps == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(reps);
        }
        dest.writeString(rest);
        if (sets == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sets);
        }
        dest.writeLong(workoutExerciseId);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
