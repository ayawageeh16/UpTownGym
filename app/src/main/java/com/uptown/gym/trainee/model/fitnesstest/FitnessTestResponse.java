package com.uptown.gym.trainee.model.fitnesstest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FitnessTestResponse implements Parcelable {

    @SerializedName("cardiacEfficiency")
    @Expose
    private String cardiacEfficiency;
    @SerializedName("flexibility")
    @Expose
    private String flexibility;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("medicalHistory")
    @Expose
    private String medicalHistory;
    @SerializedName("stability")
    @Expose
    private String stability;
    @SerializedName("strength")
    @Expose
    private String strength;

    private FitnessTestResponse(Parcel in) {
        cardiacEfficiency = in.readString();
        flexibility = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        medicalHistory = in.readString();
        stability = in.readString();
        strength = in.readString();
    }

    public static final Creator<FitnessTestResponse> CREATOR = new Creator<FitnessTestResponse>() {
        @Override
        public FitnessTestResponse createFromParcel(Parcel in) {
            return new FitnessTestResponse(in);
        }

        @Override
        public FitnessTestResponse[] newArray(int size) {
            return new FitnessTestResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardiacEfficiency);
        parcel.writeString(flexibility);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(medicalHistory);
        parcel.writeString(stability);
        parcel.writeString(strength);
    }
}
