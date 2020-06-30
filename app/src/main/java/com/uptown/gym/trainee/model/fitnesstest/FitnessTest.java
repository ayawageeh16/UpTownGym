package com.uptown.gym.trainee.model.fitnesstest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FitnessTest implements Parcelable {

    @SerializedName("cardiacEfficiency")
    @Expose
    private long cardiacEfficiency;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("coreStability")
    @Expose
    private long coreStability;
    @SerializedName("creationTime")
    @Expose
    private long creationTime;
    @SerializedName("flexibility")
    @Expose
    private long flexibility;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("medicalHistory")
    @Expose
    private long medicalHistory;
    @SerializedName("strength")
    @Expose
    private long strength;

    protected FitnessTest(Parcel in) {
        cardiacEfficiency = in.readLong();
        comment = in.readString();
        coreStability = in.readLong();
        creationTime = in.readLong();
        flexibility = in.readLong();
        id = in.readLong();
        medicalHistory = in.readLong();
        strength = in.readLong();
    }

    public static final Creator<FitnessTest> CREATOR = new Creator<FitnessTest>() {
        @Override
        public FitnessTest createFromParcel(Parcel in) {
            return new FitnessTest(in);
        }

        @Override
        public FitnessTest[] newArray(int size) {
            return new FitnessTest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(cardiacEfficiency);
        dest.writeString(comment);
        dest.writeLong(coreStability);
        dest.writeLong(creationTime);
        dest.writeLong(flexibility);
        dest.writeLong(id);
        dest.writeLong(medicalHistory);
        dest.writeLong(strength);
    }
}
