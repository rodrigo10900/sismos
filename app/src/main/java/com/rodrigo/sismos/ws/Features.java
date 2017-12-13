package com.rodrigo.sismos.ws;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rodrigo on 13/12/17.
 */

public class Features implements Parcelable {

    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("properties")
    private Properties properties;
    @Expose
    @SerializedName("geometry")
    private Geometry geometry;

    public Features() {
    }

    protected Features(Parcel in) {
        type = in.readString();
        id = in.readString();
        properties = in.readParcelable(Properties.class.getClassLoader());
        geometry = in.readParcelable(Geometry.class.getClassLoader());
    }

    public static final Creator<Features> CREATOR = new Creator<Features>() {
        @Override
        public Features createFromParcel(Parcel in) {
            return new Features(in);
        }

        @Override
        public Features[] newArray(int size) {
            return new Features[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(id);
        parcel.writeParcelable(properties, i);
        parcel.writeParcelable(geometry, i);
    }
}
