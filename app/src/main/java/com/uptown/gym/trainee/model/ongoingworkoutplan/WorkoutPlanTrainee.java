package com.uptown.gym.trainee.model.ongoingworkoutplan;

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
public class WorkoutPlanTrainee implements Parcelable {
    @SerializedName("dateOfBirth")
    @Expose
    private Integer dateOfBirth;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("fitnessTests")
    @Expose
    private List<FitnessTest> fitnessTests = null;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("inBodies")
    @Expose
    private List<InBody> inBodies = null;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("membershipStatus")
    @Expose
    private String membershipStatus;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    protected WorkoutPlanTrainee(Parcel in) {
        if (in.readByte() == 0) {
            dateOfBirth = null;
        } else {
            dateOfBirth = in.readInt();
        }
        firstName = in.readString();
        fitnessTests = in.createTypedArrayList(FitnessTest.CREATOR);
        gender = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        inBodies = in.createTypedArrayList(InBody.CREATOR);
        lastName = in.readString();
        membershipStatus = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<WorkoutPlanTrainee> CREATOR = new Creator<WorkoutPlanTrainee>() {
        @Override
        public WorkoutPlanTrainee createFromParcel(Parcel in) {
            return new WorkoutPlanTrainee(in);
        }

        @Override
        public WorkoutPlanTrainee[] newArray(int size) {
            return new WorkoutPlanTrainee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (dateOfBirth == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dateOfBirth);
        }
        dest.writeString(firstName);
        dest.writeTypedList(fitnessTests);
        dest.writeString(gender);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeTypedList(inBodies);
        dest.writeString(lastName);
        dest.writeString(membershipStatus);
        dest.writeString(phoneNumber);
    }
}
