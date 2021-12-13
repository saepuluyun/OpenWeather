package com.digitaloasis.openweather.network.servicesutils;

import com.digitaloasis.openweather.model.CurrentWeatherItem;
import com.digitaloasis.openweather.model.ForecastWeatherItem;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String openWeatherAPIKey = "405cb89456f002c401b30dda0b196e25";

    // Current Weather API
    @GET("weather?appid=" + openWeatherAPIKey)
    Observable<CurrentWeatherItem> getDataCurrentWeather(
            @Query("q") String city_name,
            @Query("lang") String language,
            @Query("units") String units

    );

    // Forecast Weather API
    @GET("forecast?cnt=16&appid=" + openWeatherAPIKey)
    Observable<ForecastWeatherItem> getDataForecastWeather(
            @Query("q") String city_name,
            @Query("lang") String language,
            @Query("units") String units

    );
}
