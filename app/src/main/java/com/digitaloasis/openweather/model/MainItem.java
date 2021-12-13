package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainItem implements Serializable {

    @SerializedName("temp")
    @Expose
    float temp = 0;

    @SerializedName("feels_like")
    @Expose
    float feels_like = 0;

    @SerializedName("temp_min")
    @Expose
    float temp_min = 0;

    @SerializedName("temp_max")
    @Expose
    float temp_max = 0;

    @SerializedName("pressure")
    @Expose
    int pressure = 0;

    @SerializedName("humidity")
    @Expose
    int humidity = 0;

    @SerializedName("sea_level")
    @Expose
    int sea_level = 0;

    @SerializedName("grnd_level")
    @Expose
    int grnd_level = 0;

    @SerializedName("temp_kf")
    @Expose
    float temp_kf = 0;

    public MainItem() {

    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(float feels_like) {
        this.feels_like = feels_like;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getSea_level() {
        return sea_level;
    }

    public void setSea_level(int sea_level) {
        this.sea_level = sea_level;
    }

    public int getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(int grnd_level) {
        this.grnd_level = grnd_level;
    }

    public float getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(float temp_kf) {
        this.temp_kf = temp_kf;
    }
}
