package com.digitaloasis.openweather.network.services.forecastwather;

import android.content.Context;

import com.digitaloasis.openweather.R;
import com.digitaloasis.openweather.model.CurrentWeatherItem;
import com.digitaloasis.openweather.model.ForecastWeatherItem;
import com.digitaloasis.openweather.network.services.currentweather.IViewCurrentWeather;
import com.digitaloasis.openweather.network.servicesutils.ApiClient;
import com.digitaloasis.openweather.network.servicesutils.ApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CurrentForecastServices {

    public IViewForecastWeather iView;
    private ApiService apiService;
    private Context context;


    public CurrentForecastServices(Context context, IViewForecastWeather iView) {
        this.context = context;
        this.iView = iView;
    }

    public void onGetForecastWeather(String city_name, String language){
        CompositeDisposable disposable = new CompositeDisposable();
        // call end point api
        apiService = ApiClient.getClient(context).create(ApiService.class);
        disposable.add(
                apiService.getDataForecastWeather(city_name, language, "metric")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .sorted()
                        .subscribeWith(new DisposableObserver<ForecastWeatherItem>(){

                            @Override
                            public void onNext(ForecastWeatherItem response) {
                                // Show response from body when success
                                iView.onSuccessGetForecastWeather(response);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // Show response from body when error
                                if (e instanceof HttpException) {
                                    iView.onError(context.getString(R.string.text_error), context.getString(R.string.text_server_error));
                                }
                            }

                            @Override
                            public void onComplete() {}
                        })
        );
    }

}
