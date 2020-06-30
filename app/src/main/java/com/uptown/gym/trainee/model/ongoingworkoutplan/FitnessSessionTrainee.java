package com.uptown.gym.trainee.model.ongoingworkoutplan;

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
public class FitnessSessionTrainee implements Parcelable {
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("fitnessSessionId")
    @Expose
    private Integer fitnessSessionId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("lastName")
    @Expose
    private String lastName;

    protected FitnessSessionTrainee(Parcel in) {
        firstName = in.readString();
        if (in.readByte() == 0) {
            fitnessSessionId = null;
        } else {
            fitnessSessionId = in.readInt();
        }
        gender = in.readString();
        id = in.readLong();
        lastName = in.readString();
    }

    public static final Creator<FitnessSessionTrainee> CREATOR = new Creator<FitnessSessionTrainee>() {
        @Override
        public FitnessSessionTrainee createFromParcel(Parcel in) {
            return new FitnessSessionTrainee(in);
        }

        @Override
        public FitnessSessionTrainee[] newArray(int size) {
            return new FitnessSessionTrainee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        if (fitnessSessionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fitnessSessionId);
        }
        dest.writeString(gender);
        dest.writeLong(id);
        dest.writeString(lastName);
    }
}
