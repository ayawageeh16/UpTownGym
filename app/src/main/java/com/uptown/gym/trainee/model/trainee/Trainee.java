
package com.uptown.gym.trainee.model.trainee;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.model.inbody.InBody;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Trainee implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("gender")
    @Expose
    private String gender = new String();
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("dateOfBirth")
    @Expose
    private long dateOfBirth;
    @SerializedName("fitnessTests")
    @Expose
    private List<FitnessTest> fitnessTests = null;
    @SerializedName("inBodies")
    @Expose
    private List<InBody> inBodies = null;
    @SerializedName("age")
    @Expose
    private Integer age;


    protected Trainee(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        gender = in.readString();
        phoneNumber = in.readString();
        dateOfBirth = in.readLong();
        fitnessTests = in.createTypedArrayList(FitnessTest.CREATOR);
        inBodies = in.createTypedArrayList(InBody.CREATOR);
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
    }

    public static final Creator<Trainee> CREATOR = new Creator<Trainee>() {
        @Override
        public Trainee createFromParcel(Parcel in) {
            return new Trainee(in);
        }

        @Override
        public Trainee[] newArray(int size) {
            return new Trainee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(gender);
        dest.writeString(phoneNumber);
        dest.writeLong(dateOfBirth);
        dest.writeTypedList(fitnessTests);
        dest.writeTypedList(inBodies);
        if (age == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(age);
        }
    }
}
