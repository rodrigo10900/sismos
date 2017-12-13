package com.rodrigo.sismos.ws;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rodrigo on 13/12/17.
 */

public class MetaData implements Parcelable {

    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("url")
    private String url;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("status")
    private int status;
    @Expose
    @SerializedName("api")
    private String api;
    @Expose
    @SerializedName("count")
    private int count;

    public MetaData() {
    }

    protected MetaData(Parcel in) {
        type = in.readString();
        url = in.readString();
        title = in.readString();
        status = in.readInt();
        api = in.readString();
        count = in.readInt();
    }

    public static final Creator<MetaData> CREATOR = new Creator<MetaData>() {
        @Override
        public MetaData createFromParcel(Parcel in) {
            return new MetaData(in);
        }

        @Override
        public MetaData[] newArray(int size) {
            return new MetaData[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(url);
        parcel.writeString(title);
        parcel.writeInt(status);
        parcel.writeString(api);
        parcel.writeInt(count);
    }
}
