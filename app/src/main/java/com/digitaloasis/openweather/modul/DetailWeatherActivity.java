package com.digitaloasis.openweather.modul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.digitaloasis.openweather.R;
import com.digitaloasis.openweather.helper.DateHelper;
import com.digitaloasis.openweather.model.CurrentWeatherItem;
import com.squareup.picasso.Picasso;

public class DetailWeatherActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack,
            ivShare,
            ivStatus;

    private TextView tvDay,
            tvDate,
            tvMainTemp,
            tvMinTemp,
            tvStatus,
            tvHumidity,
            tvPressure,
            tvWind,
            tvLocation,
            tvWindProgress;

    private ProgressBar pbWind;

    private Toolbar toolbar;

    private CurrentWeatherItem currentWeatherItem;

    private String URL_TO_SHARE = "https://digitaloasis.co.id/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);

        if (getIntent() != null){
            currentWeatherItem = (CurrentWeatherItem) getIntent().getSerializableExtra("data");
        }

        // Initial
        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.inflateMenu(R.menu.more_detail);
        }

        // Set Content
        setContent();

        //Onclicked
        ivBack.setOnClickListener(this);
        ivShare.setOnClickListener(this);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.menu_refresh) {
                    finish();
                    System.exit(0);
                } else {
                    finish();
                    System.exit(0);
                }

                return false;
            }
        });
    }

    public void init(){
        // Image View
        ivBack = findViewById(R.id.ivBack);
        ivShare = findViewById(R.id.ivShare);
        ivStatus = findViewById(R.id.ivStatus);

        // Text View
        tvDay = findViewById(R.id.tvDay);
        tvDate = findViewById(R.id.tvDate);
        tvMainTemp = findViewById(R.id.tvMainTemp);
        tvMinTemp = findViewById(R.id.tvMinTemp);
        tvStatus = findViewById(R.id.tvStatus);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvPressure = findViewById(R.id.tvPressure);
        tvWind = findViewById(R.id.tvWind);
        tvLocation = findViewById(R.id.tvLocation);
        tvWindProgress = findViewById(R.id.tvWindProgress);

        // Progress Bar
        pbWind = findViewById(R.id.pbWind);

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
    }

    public void setContent(){
        int temp = (int) currentWeatherItem.getMain().getTemp();
        int minTemp = (int) currentWeatherItem.getMain().getTemp_min();
        int wind = (int) currentWeatherItem.getWind().getSpeed();

        tvMainTemp.setText(String.valueOf(temp) + "\u00B0");
        tvMinTemp.setText(String.valueOf(minTemp) + "\u00B0");
        tvStatus.setText(currentWeatherItem.getWeather().get(0).getMain());
        tvDay.setText(DateHelper.getDayWeekTextFromTimeMillis(currentWeatherItem.getDt()));
        tvDate.setText(DateHelper.getDateStringFromTimeMillis(currentWeatherItem.getDt()));
        tvHumidity.setText(currentWeatherItem.getMain().getHumidity() + " %");
        tvPressure.setText(currentWeatherItem.getMain().getPressure() + " hPa");
        tvWind.setText(wind + " km/h NE");
        tvWindProgress.setText(String.valueOf(wind));
        tvLocation.setText(currentWeatherItem.getName());

        pbWind.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));

        // Condition if data size 0
        if (currentWeatherItem.getWeather().size() != 0){
            tvStatus.setText(currentWeatherItem.getWeather().get(0).getMain());

            Picasso.get()
                    .load("http://openweathermap.org/img/w/" + currentWeatherItem.getWeather().get(0).getIcon() + ".png")
                    .centerCrop()
                    .fit()
                    .into(ivStatus);

        } else {
            tvStatus.setText("-");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ivBack){
            onBackPressed();
        } else if (v == ivShare){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);

            //change the type of data you need to share,
            //for image use "image/*"
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, URL_TO_SHARE);
            startActivity(Intent.createChooser(intent, "Share"));
        }
    }
}