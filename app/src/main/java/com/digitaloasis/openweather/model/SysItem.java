package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SysItem implements Serializable {

    @SerializedName("type")
    @Expose
    int type = 0;

    @SerializedName("id")
    @Expose
    int id = 0;

    @SerializedName("message")
    @Expose
    float message = 0;

    @SerializedName("country")
    @Expose
    String country = "";

    @SerializedName("sunrise")
    @Expose
    long sunrise = 0;

    @SerializedName("sunset")
    @Expose
    long sunset = 0;

    @SerializedName("pod")
    @Expose
    String pod = "";

    public SysItem() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
