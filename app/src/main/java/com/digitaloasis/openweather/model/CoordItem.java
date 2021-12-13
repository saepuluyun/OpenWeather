package com.digitaloasis.openweather.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordItem implements Serializable {

    @SerializedName("lon")
    @Expose
    float lon = 0;

    @SerializedName("lat")
    @Expose
    float lat = 0;

    public CoordItem() {

    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
