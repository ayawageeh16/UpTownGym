package com.uptown.gym.trainee.model.base;

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
public class PagingResponse implements Parcelable {

    @SerializedName("pageable")
    @Expose
    private Pageable pageable;
    @SerializedName("totalElements")
    @Expose
    private Integer totalElements;
    @SerializedName("last")
    @Expose
    private Boolean last;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("first")
    @Expose
    private Boolean first;
    @SerializedName("sort")
    @Expose
    private Sort_ sort;
    @SerializedName("numberOfElements")
    @Expose
    private Integer numberOfElements;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("empty")
    @Expose
    private Boolean empty;

    private PagingResponse(Parcel in) {
        if (in.readByte() == 0) {
            totalElements = null;
        } else {
            totalElements = in.readInt();
        }
        byte tmpLast = in.readByte();
        last = tmpLast == 0 ? null : tmpLast == 1;
        if (in.readByte() == 0) {
            totalPages = null;
        } else {
            totalPages = in.readInt();
        }
        byte tmpFirst = in.readByte();
        first = tmpFirst == 0 ? null : tmpFirst == 1;
        if (in.readByte() == 0) {
            numberOfElements = null;
        } else {
            numberOfElements = in.readInt();
        }
        if (in.readByte() == 0) {
            size = null;
        } else {
            size = in.readInt();
        }
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readInt();
        }
        byte tmpEmpty = in.readByte();
        empty = tmpEmpty == 0 ? null : tmpEmpty == 1;
    }

    public static final Creator<PagingResponse> CREATOR = new Creator<PagingResponse>() {
        @Override
        public PagingResponse createFromParcel(Parcel in) {
            return new PagingResponse(in);
        }

        @Override
        public PagingResponse[] newArray(int size) {
            return new PagingResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (totalElements == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalElements);
        }
        parcel.writeByte((byte) (last == null ? 0 : last ? 1 : 2));
        if (totalPages == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalPages);
        }
        parcel.writeByte((byte) (first == null ? 0 : first ? 1 : 2));
        if (numberOfElements == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numberOfElements);
        }
        if (size == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(size);
        }
        if (number == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(number);
        }
        parcel.writeByte((byte) (empty == null ? 0 : empty ? 1 : 2));
    }
}
