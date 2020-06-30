package com.uptown.gym.trainee.model.trainee;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TraineeClassDTO implements Parcelable {

    @SerializedName("traineesIds")
    @Expose
    private List<Long> traineesIds = null;

    private TraineeClassDTO(Parcel in) {
    }

    public static final Creator<TraineeClassDTO> CREATOR = new Creator<TraineeClassDTO>() {
        @Override
        public TraineeClassDTO createFromParcel(Parcel in) {
            return new TraineeClassDTO(in);
        }

        @Override
        public TraineeClassDTO[] newArray(int size) {
            return new TraineeClassDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
