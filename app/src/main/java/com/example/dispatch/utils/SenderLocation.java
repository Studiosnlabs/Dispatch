package com.example.dispatch.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseGeoPoint;

public class SenderLocation implements Parcelable {
    protected SenderLocation(Parcel in) {
        ParseGeoPoint senderLocation;

    }

    public static final Creator<SenderLocation> CREATOR = new Creator<SenderLocation>() {
        @Override
        public SenderLocation createFromParcel(Parcel in) {
            return new SenderLocation(in);
        }

        @Override
        public SenderLocation[] newArray(int size) {
            return new SenderLocation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
