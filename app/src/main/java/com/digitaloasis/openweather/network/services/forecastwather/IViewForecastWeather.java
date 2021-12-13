package com.digitaloasis.openweather.network.services.forecastwather;

import com.digitaloasis.openweather.model.ForecastWeatherItem;

public interface IViewForecastWeather {

    void onSuccessGetForecastWeather(ForecastWeatherItem forecastWeatherItem);
    void onError(String title, String message);

}
