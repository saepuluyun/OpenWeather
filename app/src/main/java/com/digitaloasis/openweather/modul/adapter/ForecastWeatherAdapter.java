package com.digitaloasis.openweather.modul.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.digitaloasis.openweather.R;
import com.digitaloasis.openweather.helper.DateHelper;
import com.digitaloasis.openweather.model.CurrentWeatherItem;
import com.digitaloasis.openweather.model.ForecastWeatherItem;
import com.digitaloasis.openweather.modul.DetailWeatherActivity;
import com.digitaloasis.openweather.modul.HomeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForecastWeatherAdapter extends RecyclerView.Adapter<ForecastWeatherAdapter.MyViewHolder> {

    private List<CurrentWeatherItem> mModelList;
    private ForecastWeatherItem forecastWeatherItem;
    private Activity activity;


    public ForecastWeatherAdapter(Activity activity, ForecastWeatherItem forecastWeatherItem) {
        mModelList = forecastWeatherItem.getList();
        this.forecastWeatherItem = forecastWeatherItem;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CurrentWeatherItem currentWeatherItem = mModelList.get(position);

        Picasso.get()
                .load("http://openweathermap.org/img/w/" + currentWeatherItem.getWeather().get(0).getIcon() + ".png")
                .centerCrop()
                .fit()
                .into(holder.ivStatus);

        int temp = (int) currentWeatherItem.getMain().getTemp();
        int minTemp = (int) currentWeatherItem.getMain().getTemp_min();
        holder.tvTemp.setText(String.valueOf(temp) + "\u00B0");
        holder.tvMinTemp.setText(String.valueOf(minTemp) + "\u00B0");
        holder.tvStatus.setText(currentWeatherItem.getWeather().get(0).getMain());
        holder.tvDate.setText(DateHelper.getDayWeekTextFromTimeMillis(currentWeatherItem.getDt()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWeatherItem.setName(forecastWeatherItem.getCity().getName());
                Intent i = new Intent(activity, DetailWeatherActivity.class);
                i.putExtra("data", currentWeatherItem);
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelList == null ? 0 : mModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView ivStatus;
        private TextView tvDate,
                tvStatus,
                tvTemp,
                tvMinTemp;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ivStatus = itemView.findViewById(R.id.ivStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStatus =itemView.findViewById(R.id.tvStatus);
            tvTemp =itemView.findViewById(R.id.tvTemp);
            tvMinTemp =itemView.findViewById(R.id.tvMinTemp);
        }
    }
}
