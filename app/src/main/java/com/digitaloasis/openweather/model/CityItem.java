package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CityItem implements Serializable {

    @SerializedName("id")
    @Expose
    int id = 0;

    @SerializedName("name")
    @Expose
    String name = "";

    @SerializedName("coord")
    @Expose
    CoordItem coord = new CoordItem();

    @SerializedName("country")
    @Expose
    String country = "";

    @SerializedName("timezone")
    @Expose
    long timezone = 0;

    @SerializedName("sunrise")
    @Expose
    long sunrise = 0;

    @SerializedName("sunset")
    @Expose
    long sunset = 0;

    public CityItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordItem getCoord() {
        return coord;
    }

    public void setCoord(CoordItem coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getTimezone() {
        return timezone;
    }

    public void setTimezone(long timezone) {
        this.timezone = timezone;
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
}
