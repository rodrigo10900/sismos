package com.rodrigo.sismos.ws;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rodrigo on 13/12/17.
 */

public class Properties implements Parcelable {

    @Expose
    @SerializedName("mag")
    private float magnitude;
    @Expose
    @SerializedName("place")
    private String place;
    @Expose
    @SerializedName("time")
    private long time;
    @Expose
    @SerializedName("updated")
    private long updated;
    @Expose
    @SerializedName("tz")
    private int tz;
    @Expose
    @SerializedName("url")
    private String url;
    @Expose
    @SerializedName("detail")
    private String detail;
    @Expose
    @SerializedName("felt")
    private String felt;
    @Expose
    @SerializedName("cdi")
    private String cdi;
    @Expose
    @SerializedName("mmi")
    private String mmi;
    @Expose
    @SerializedName("alert")
    private String alert;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("tsunami")
    private String tsunami;
    @Expose
    @SerializedName("sig")
    private String sig;
    @Expose
    @SerializedName("net")
    private String net;
    @Expose
    @SerializedName("code")
    private String code;
    @Expose
    @SerializedName("ids")
    private String ids;
    @Expose
    @SerializedName("sources")
    private String sources;
    @Expose
    @SerializedName("types")
    private String types;
    @Expose
    @SerializedName("nst")
    private String nst;
    @Expose
    @SerializedName("rms")
    private String rms;
    @Expose
    @SerializedName("gap")
    private String gap;
    @Expose
    @SerializedName("magType")
    private String magType;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("title")
    private String title;

    public Properties() {
    }

    protected Properties(Parcel in) {
        magnitude = in.readFloat();
        place = in.readString();
        time = in.readLong();
        updated = in.readLong();
        tz = in.readInt();
        url = in.readString();
        detail = in.readString();
        felt = in.readString();
        cdi = in.readString();
        mmi = in.readString();
        alert = in.readString();
        status = in.readString();
        tsunami = in.readString();
        sig = in.readString();
        net = in.readString();
        code = in.readString();
        ids = in.readString();
        sources = in.readString();
        types = in.readString();
        nst = in.readString();
        rms = in.readString();
        gap = in.readString();
        magType = in.readString();
        type = in.readString();
        title = in.readString();
    }

    public static final Creator<Properties> CREATOR = new Creator<Properties>() {
        @Override
        public Properties createFromParcel(Parcel in) {
            return new Properties(in);
        }

        @Override
        public Properties[] newArray(int size) {
            return new Properties[size];
        }
    };

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public int getTz() {
        return tz;
    }

    public void setTz(int tz) {
        this.tz = tz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFelt() {
        return felt;
    }

    public void setFelt(String felt) {
        this.felt = felt;
    }

    public String getCdi() {
        return cdi;
    }

    public void setCdi(String cdi) {
        this.cdi = cdi;
    }

    public String getMmi() {
        return mmi;
    }

    public void setMmi(String mmi) {
        this.mmi = mmi;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTsunami() {
        return tsunami;
    }

    public void setTsunami(String tsunami) {
        this.tsunami = tsunami;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getNst() {
        return nst;
    }

    public void setNst(String nst) {
        this.nst = nst;
    }

    public String getRms() {
        return rms;
    }

    public void setRms(String rms) {
        this.rms = rms;
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap;
    }

    public String getMagType() {
        return magType;
    }

    public void setMagType(String magType) {
        this.magType = magType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(magnitude);
        parcel.writeString(place);
        parcel.writeLong(time);
        parcel.writeLong(updated);
        parcel.writeInt(tz);
        parcel.writeString(url);
        parcel.writeString(detail);
        parcel.writeString(felt);
        parcel.writeString(cdi);
        parcel.writeString(mmi);
        parcel.writeString(alert);
        parcel.writeString(status);
        parcel.writeString(tsunami);
        parcel.writeString(sig);
        parcel.writeString(net);
        parcel.writeString(code);
        parcel.writeString(ids);
        parcel.writeString(sources);
        parcel.writeString(types);
        parcel.writeString(nst);
        parcel.writeString(rms);
        parcel.writeString(gap);
        parcel.writeString(magType);
        parcel.writeString(type);
        parcel.writeString(title);
    }
}
