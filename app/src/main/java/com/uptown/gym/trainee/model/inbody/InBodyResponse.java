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
public class InBodyResponse implements Parcelable {

    @SerializedName("bodyScore")
    @Expose
    private Integer bodyScore;
    @SerializedName("fatControl")
    @Expose
    private Integer fatControl;
    @SerializedName("fatLevel")
    @Expose
    private Integer fatLevel;
    @SerializedName("fatPercentage")
    @Expose
    private Integer fatPercentage;
    @SerializedName("fatWeight")
    @Expose
    private Integer fatWeight;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("metabolicRate")
    @Expose
    private Integer metabolicRate;
    @SerializedName("muscleControl")
    @Expose
    private Integer muscleControl;
    @SerializedName("muscleMass")
    @Expose
    private String muscleMass;
    @SerializedName("obesityDegree")
    @Expose
    private Integer obesityDegree;
    @SerializedName("targetWeight")
    @Expose
    private Integer targetWeight;
    @SerializedName("waistHipRatio")
    @Expose
    private Integer waistHipRatio;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("weightControl")
    @Expose
    private Integer weightControl;

    private InBodyResponse(Parcel in) {
        if (in.readByte() == 0) {
            bodyScore = null;
        } else {
            bodyScore = in.readInt();
        }
        if (in.readByte() == 0) {
            fatControl = null;
        } else {
            fatControl = in.readInt();
        }
        if (in.readByte() == 0) {
            fatLevel = null;
        } else {
            fatLevel = in.readInt();
        }
        if (in.readByte() == 0) {
            fatPercentage = null;
        } else {
            fatPercentage = in.readInt();
        }
        if (in.readByte() == 0) {
            fatWeight = null;
        } else {
            fatWeight = in.readInt();
        }
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            metabolicRate = null;
        } else {
            metabolicRate = in.readInt();
        }
        if (in.readByte() == 0) {
            muscleControl = null;
        } else {
            muscleControl = in.readInt();
        }
        muscleMass = in.readString();
        if (in.readByte() == 0) {
            obesityDegree = null;
        } else {
            obesityDegree = in.readInt();
        }
        if (in.readByte() == 0) {
            targetWeight = null;
        } else {
            targetWeight = in.readInt();
        }
        if (in.readByte() == 0) {
            waistHipRatio = null;
        } else {
            waistHipRatio = in.readInt();
        }
        if (in.readByte() == 0) {
            weight = null;
        } else {
            weight = in.readInt();
        }
        if (in.readByte() == 0) {
            weightControl = null;
        } else {
            weightControl = in.readInt();
        }
    }

    public static final Creator<InBodyResponse> CREATOR = new Creator<InBodyResponse>() {
        @Override
        public InBodyResponse createFromParcel(Parcel in) {
            return new InBodyResponse(in);
        }

        @Override
        public InBodyResponse[] newArray(int size) {
            return new InBodyResponse[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (bodyScore == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(bodyScore);
        }
        if (fatControl == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fatControl);
        }
        if (fatLevel == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fatLevel);
        }
        if (fatPercentage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fatPercentage);
        }
        if (fatWeight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fatWeight);
        }
        if (height == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(height);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (metabolicRate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(metabolicRate);
        }
        if (muscleControl == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(muscleControl);
        }
        parcel.writeString(muscleMass);
        if (obesityDegree == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(obesityDegree);
        }
        if (targetWeight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(targetWeight);
        }
        if (waistHipRatio == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(waistHipRatio);
        }
        if (weight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(weight);
        }
        if (weightControl == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(weightControl);
        }
    }
}
