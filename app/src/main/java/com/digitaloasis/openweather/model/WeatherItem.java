package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherItem implements Serializable {

    @SerializedName("id")
    @Expose
    int id = 0;

    @SerializedName("main")
    @Expose
    String main = "";

    @SerializedName("description")
    @Expose
    String description = "";

    @SerializedName("icon")
    @Expose
    String icon = "";

    public WeatherItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
