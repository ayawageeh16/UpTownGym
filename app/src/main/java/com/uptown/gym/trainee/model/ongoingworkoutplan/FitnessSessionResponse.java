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
public class FitnessSessionResponse implements Parcelable {

    @SerializedName("creationTime")
    @Expose
    private long creationTime;
    @SerializedName("id")
    @Expose
    private long id;

    protected FitnessSessionResponse(Parcel in) {
        creationTime = in.readLong();
        id = in.readLong();
    }

    public static final Creator<FitnessSessionResponse> CREATOR = new Creator<FitnessSessionResponse>() {
        @Override
        public FitnessSessionResponse createFromParcel(Parcel in) {
            return new FitnessSessionResponse(in);
        }

        @Override
        public FitnessSessionResponse[] newArray(int size) {
            return new FitnessSessionResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(creationTime);
        dest.writeLong(id);
    }
}
