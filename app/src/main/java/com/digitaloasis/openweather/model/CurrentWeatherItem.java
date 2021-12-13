package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherItem implements Serializable {


    @SerializedName("coord")
    @Expose
    CoordItem coord = new CoordItem();

    @SerializedName("weather")
    @Expose
    List<WeatherItem> weather = new ArrayList<>();

    @SerializedName("base")
    @Expose
    String base = "";

    @SerializedName("main")
    @Expose
    MainItem main = new MainItem();

    @SerializedName("visibility")
    @Expose
    int visibility = 0;

    @SerializedName("wind")
    @Expose
    WindItem wind = new WindItem();

    @SerializedName("clouds")
    @Expose
    CloudsItem clouds = new CloudsItem();

    @SerializedName("dt")
    @Expose
    long dt = 0;

    @SerializedName("sys")
    @Expose
    SysItem sys = new SysItem();

    @SerializedName("timezone")
    @Expose
    long timezone = 0;

    @SerializedName("id")
    @Expose
    long id = 0;

    @SerializedName("name")
    @Expose
    String name = "";

    @SerializedName("cod")
    @Expose
    int cod = 0;

    @SerializedName("pop")
    @Expose
    float pop = 0;

    @SerializedName("rain")
    @Expose
    RainItem rain = new RainItem();

    @SerializedName("dt_txt")
    @Expose
    String dt_txt = "";

    public CurrentWeatherItem() {

    }

    public CoordItem getCoord() {
        return coord;
    }

    public void setCoord(CoordItem coord) {
        this.coord = coord;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherItem> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainItem getMain() {
        return main;
    }

    public void setMain(MainItem main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public WindItem getWind() {
        return wind;
    }

    public void setWind(WindItem wind) {
        this.wind = wind;
    }

    public CloudsItem getClouds() {
        return clouds;
    }

    public void setClouds(CloudsItem clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public SysItem getSys() {
        return sys;
    }

    public void setSys(SysItem sys) {
        this.sys = sys;
    }

    public long getTimezone() {
        return timezone;
    }

    public void setTimezone(long timezone) {
        this.timezone = timezone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
