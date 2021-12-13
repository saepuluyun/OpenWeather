package com.digitaloasis.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RainItem implements Serializable {

    @SerializedName("3h")
    @Expose
    float h3 = 0;

    public RainItem() {

    }

    public float getH3() {
        return h3;
    }

    public void setH3(float h3) {
        this.h3 = h3;
    }
}
