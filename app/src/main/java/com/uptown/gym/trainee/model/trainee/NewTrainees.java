package com.uptown.gym.trainee.model.trainee;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.base.PagingResponse;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewTrainees extends PagingResponse implements Parcelable {

    @SerializedName("content")
    @Expose
    private List<Trainee> content = null;

    NewTrainees(Parcel in) {
        content = in.createTypedArrayList(Trainee.CREATOR);
    }

    public static final Creator<NewTrainees> CREATOR = new Creator<NewTrainees>() {
        @Override
        public NewTrainees createFromParcel(Parcel in) {
            return new NewTrainees(in);
        }

        @Override
        public NewTrainees[] newArray(int size) {
            return new NewTrainees[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(content);
    }
}
