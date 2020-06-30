package com.uptown.gym.trainee.model.trainee;

import android.os.Parcel;
import android.os.Parcelable;

public class NewTraineesDTO implements Parcelable {

    private NewTrainees newTrainees;
    private int code;

    public NewTraineesDTO() {
    }

    private NewTraineesDTO(Parcel in) {
        newTrainees = in.readParcelable(NewTrainees.class.getClassLoader());
        code = in.readInt();
    }

    public static final Creator<NewTraineesDTO> CREATOR = new Creator<NewTraineesDTO>() {
        @Override
        public NewTraineesDTO createFromParcel(Parcel in) {
            return new NewTraineesDTO(in);
        }

        @Override
        public NewTraineesDTO[] newArray(int size) {
            return new NewTraineesDTO[size];
        }
    };

    public NewTrainees getNewTrainees() {
        return newTrainees;
    }

    public void setNewTrainees(NewTrainees newTrainees) {
        this.newTrainees = newTrainees;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(newTrainees, i);
        parcel.writeInt(code);
    }
}
