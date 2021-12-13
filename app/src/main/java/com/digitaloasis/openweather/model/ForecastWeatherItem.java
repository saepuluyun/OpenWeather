package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ForecastWeatherItem implements Serializable {

    @SerializedName("cod")
    @Expose
    int cod = 0;

    @SerializedName("message")
    @Expose
    int message = 0;

    @SerializedName("cnt")
    @Expose
    int cnt = 0;

    @SerializedName("list")
    @Expose
    List<CurrentWeatherItem> list = new ArrayList<>();

    @SerializedName("city")
    @Expose
    CityItem city = new CityItem();

    public ForecastWeatherItem() {

    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<CurrentWeatherItem> getList() {
        return list;
    }

    public void setList(List<CurrentWeatherItem> list) {
        this.list = list;
    }

    public CityItem getCity() {
        return city;
    }

    public void setCity(CityItem city) {
        this.city = city;
    }
}
