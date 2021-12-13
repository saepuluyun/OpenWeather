package com.digitaloasis.openweather.network.services.currentweather;

import com.digitaloasis.openweather.model.CurrentWeatherItem;

public interface IViewCurrentWeather {

    void onSuccessGetCurrentWeather(CurrentWeatherItem currentWeatherItem);
    void onError(String title, String message);

}
