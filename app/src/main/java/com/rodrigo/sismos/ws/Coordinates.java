package com.rodrigo.sismos.ws;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rodrigo on 13/12/17.
 */

public class Coordinates implements Parcelable {

    @Expose
    @SerializedName("0")
    private double latitude;
    @Expose
    @SerializedName("1")
    private double longitude;
    @Expose
    @SerializedName("2")
    private double data;

    public Coordinates() {
    }

    protected Coordinates(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        data = in.readDouble();
    }

    public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeDouble(data);
    }
}
