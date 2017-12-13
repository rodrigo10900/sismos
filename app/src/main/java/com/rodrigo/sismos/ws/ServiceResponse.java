package com.rodrigo.sismos.ws;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rodrigo on 13/12/17.
 */

public class ServiceResponse {

    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("metadata")
    private MetaData metaData;
    @Expose
    @SerializedName("features")
    private ArrayList<Features> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public ArrayList<Features> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Features> features) {
        this.features = features;
    }
}
