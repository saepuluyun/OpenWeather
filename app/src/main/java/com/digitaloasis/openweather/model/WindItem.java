package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WindItem implements Serializable {

    @SerializedName("speed")
    @Expose
    float speed = 0;

    @SerializedName("deg")
    @Expose
    int deg = 0;

    @SerializedName("gust")
    @Expose
    float gust = 0;

    public WindItem() {

    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }
}
