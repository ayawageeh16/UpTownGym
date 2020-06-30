package com.uptown.gym.trainee.model.inbody;

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
public class InBody implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("creationTime")
    @Expose
    private long creationTime;
    @SerializedName("bodyScore")
    @Expose
    private float bodyScore;
    @SerializedName("fatControl")
    @Expose
    private float fatControl;
    @SerializedName("fatLevel")
    @Expose
    private float fatLevel;
    @SerializedName("fatPercentage")
    @Expose
    private float fatPercentage;
    @SerializedName("fatWeight")
    @Expose
    private float fatWeight;
    @SerializedName("height")
    @Expose
    private float height;
    @SerializedName("metabolicRate")
    @Expose
    private float metabolicRate;
    @SerializedName("muscleControl")
    @Expose
    private float muscleControl;
    @SerializedName("muscleMass")
    @Expose
    private float muscleMass;
    @SerializedName("obesityDegree")
    @Expose
    private float obesityDegree;
    @SerializedName("targetWeight")
    @Expose
    private float targetWeight;
    @SerializedName("waistHipRatio")
    @Expose
    private float waistHipRatio;
    @SerializedName("weight")
    @Expose
    private float weight;
    @SerializedName("weightControl")
    @Expose
    private float weightControl;


    public static final Creator<InBody> CREATOR = new Creator<InBody>() {
        @Override
        public InBody createFromParcel(Parcel in) {
            return new InBody(in);
        }

        @Override
        public InBody[] newArray(int size) {
            return new InBody[size];
        }
    };

    protected InBody(Parcel in) {
        id = in.readLong();
        creationTime = in.readLong();
        bodyScore = in.readFloat();
        fatControl = in.readFloat();
        fatLevel = in.readFloat();
        fatPercentage = in.readFloat();
        fatWeight = in.readFloat();
        height = in.readFloat();
        metabolicRate = in.readFloat();
        muscleControl = in.readFloat();
        muscleMass = in.readFloat();
        obesityDegree = in.readFloat();
        targetWeight = in.readFloat();
        waistHipRatio = in.readFloat();
        weight = in.readFloat();
        weightControl = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(creationTime);
        dest.writeFloat(bodyScore);
        dest.writeFloat(fatControl);
        dest.writeFloat(fatLevel);
        dest.writeFloat(fatPercentage);
        dest.writeFloat(fatWeight);
        dest.writeFloat(height);
        dest.writeFloat(metabolicRate);
        dest.writeFloat(muscleControl);
        dest.writeFloat(muscleMass);
        dest.writeFloat(obesityDegree);
        dest.writeFloat(targetWeight);
        dest.writeFloat(waistHipRatio);
        dest.writeFloat(weight);
        dest.writeFloat(weightControl);
    }
}
