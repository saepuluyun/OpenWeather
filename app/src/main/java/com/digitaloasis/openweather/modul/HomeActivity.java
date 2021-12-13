package com.digitaloasis.openweather.modul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitaloasis.openweather.R;
import com.digitaloasis.openweather.helper.DateHelper;
import com.digitaloasis.openweather.helper.DialogHelper;
import com.digitaloasis.openweather.helper.GpsHelper;
import com.digitaloasis.openweather.helper.LoadingHelper;
import com.digitaloasis.openweather.model.CurrentWeatherItem;
import com.digitaloasis.openweather.model.ForecastWeatherItem;
import com.digitaloasis.openweather.modul.adapter.ForecastWeatherAdapter;
import com.digitaloasis.openweather.network.services.currentweather.CurrentWeatherServices;
import com.digitaloasis.openweather.network.services.currentweather.IViewCurrentWeather;
import com.digitaloasis.openweather.network.services.forecastwather.CurrentForecastServices;
import com.digitaloasis.openweather.network.services.forecastwather.IViewForecastWeather;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HomeActivity extends AppCompatActivity implements IViewCurrentWeather, IViewForecastWeather, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 101;
    private GpsHelper gpsTracker;
    private List<Address> currentLoc;

    private ImageView ivStatus,
            ivStatusApp;

    private TextView tvDate,
            tvMainTemp,
            tvMinTemp,
            tvLocation,
            tvStatus,
            tvStatusApp;

    private RecyclerView rvForecast;

    private LinearLayout llContent;

    private LinearLayoutManager mLayoutManager;

    private SwipeRefreshLayout swipeRefresh;

    private Toolbar toolbar;

    private CurrentWeatherItem currentWeatherItem;
    private ForecastWeatherAdapter mAdapterForecast;

    private DialogHelper dialogHelper;
    private LoadingHelper loadingHelper;

    private String language = "en", city = "Jakarta";
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initial
        dialogHelper = new DialogHelper(this);
        loadingHelper = new LoadingHelper(this);

        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.inflateMenu(R.menu.more_menu);
        }

        //Swipe Refresh
        swipeRefresh.setOnRefreshListener(HomeActivity.this);
        swipeRefresh.setRefreshing(false);

        // Scheme colors for animation
        swipeRefresh.setColorSchemeColors(
                getResources().getColor(R.color.cyan)
        );

        // Call Api
        loadingHelper.start();
        getCurrentWeather();

        //On clicked
        llContent.setOnClickListener(this);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.menu_refresh) {
                    //call api refresh
                    loadingHelper.start();
                    getCurrentWeather();
                } else {
                    finish();
                    System.exit(0);
                }

                return false;
            }
        });
    }

    private void getCurrentWeather() {

        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        permissionsToRequest = findUnAskedPermissions(permissions);
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0)
                requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

        gpsTracker = new GpsHelper(this);

        if (gpsTracker.canGetLocation()) {

            double longitude = gpsTracker.getLongitude();
            double latitude = gpsTracker.getLatitude();

            Geocoder gcd = new Geocoder(this.getBaseContext(), Locale.getDefault());
            try {
                currentLoc = gcd.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            gpsTracker.showSettingsAlert();
        }

        if(currentLoc != null){
            if (currentLoc.size() > 0) {
                city = currentLoc.get(0).getSubAdminArea();

                if (city.contains("Kota")) {
                    city = city.replace("Kota", "").trim();
                } else if (city.contains("Kabupaten")) {
                    city = city.replace("Kabupaten", "").trim();
                } else if (city.contains("Kab")) {
                    city = city.replace("Kab", "").trim();
                }

                IViewCurrentWeather iView = this;
                CurrentWeatherServices presenter = new CurrentWeatherServices(this, iView);
                presenter.onGetCurrentWeather(city, language);
            } else {
                IViewCurrentWeather iView = this;
                CurrentWeatherServices presenter = new CurrentWeatherServices(this, iView);
                presenter.onGetCurrentWeather(city, language);
            }
        } else {
            IViewCurrentWeather iView = this;
            CurrentWeatherServices presenter = new CurrentWeatherServices(this, iView);
            presenter.onGetCurrentWeather(city, language);
        }
    }

    public void init(){
        // Image View
        ivStatus = findViewById(R.id.ivStatus);
        ivStatusApp = findViewById(R.id.ivStatusApp);

        // Text View
        tvDate = findViewById(R.id.tvDate);
        tvMainTemp = findViewById(R.id.tvMainTemp);
        tvMinTemp = findViewById(R.id.tvMinTemp);
        tvLocation = findViewById(R.id.tvLocation);
        tvStatus = findViewById(R.id.tvStatus);
        tvStatusApp = findViewById(R.id.tvStatusApp);

        //Recycle View
        rvForecast = findViewById(R.id.rvForecast);

        //Linear Layout
        llContent = findViewById(R.id.llContent);

        //Swipe Refresh
        swipeRefresh = findViewById(R.id.swipeRefresh);

        toolbar = findViewById(R.id.toolbar);
    }

    private void setContentCurrent(CurrentWeatherItem currentWeatherItem) {
        this.currentWeatherItem = currentWeatherItem;

        int temp = (int) currentWeatherItem.getMain().getTemp();
        int minTemp = (int) currentWeatherItem.getMain().getTemp_min();

        tvMainTemp.setText(String.valueOf(temp) + "\u00B0");
        tvMinTemp.setText(String.valueOf(minTemp) + "\u00B0");
        tvLocation.setText(currentWeatherItem.getName() + ", " + currentWeatherItem.getSys().getCountry());
        tvDate.setText(DateHelper.getDateFormatStringFromTimeMillis(currentWeatherItem.getDt()));

        // Condition if data size 0
        if (currentWeatherItem.getWeather().size() != 0){
            // First Word Captial
            String capWord = currentWeatherItem.getWeather().get(0).getDescription();
            capWord = capWord.substring(0, 1).toUpperCase() + capWord.substring(1);

            tvStatus.setText(currentWeatherItem.getWeather().get(0).getMain());
            tvStatusApp.setText(capWord);

            Picasso.get()
                    .load("http://openweathermap.org/img/w/" + currentWeatherItem.getWeather().get(0).getIcon() + ".png")
                    .centerCrop()
                    .fit()
                    .into(ivStatus);

            Picasso.get()
                    .load("http://openweathermap.org/img/w/" + currentWeatherItem.getWeather().get(0).getIcon() + ".png")
                    .centerCrop()
                    .fit()
                    .into(ivStatusApp);
        } else {
            tvStatus.setText("-");
            tvStatusApp.setText("-");
        }

    }

    @Override
    public void onSuccessGetCurrentWeather(CurrentWeatherItem currentWeatherItem) {
        // Set content from JSON
        setContentCurrent(currentWeatherItem);

        // Call Api
        IViewForecastWeather iView = this;
        CurrentForecastServices presenter = new CurrentForecastServices(this, iView);
        presenter.onGetForecastWeather(city, language);
    }

    @Override
    public void onSuccessGetForecastWeather(ForecastWeatherItem forecastWeatherItem) {
        // Set content from JSON
        setContentForecast(forecastWeatherItem);
        swipeRefresh.setRefreshing(false);
        loadingHelper.stop();
    }

    private void setContentForecast(ForecastWeatherItem forecastWeatherItem) {
        mAdapterForecast = new ForecastWeatherAdapter(this, forecastWeatherItem);
        rvForecast.setAdapter(mAdapterForecast);
        rvForecast.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rvForecast.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onError(String title, String message) {
        loadingHelper.stop();
        dialogHelper.dialogError(title, message);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        // On Swipe Refresh
        getCurrentWeather();
    }

    @Override
    public void onClick(View v) {
        if (v == llContent){
            Intent i = new Intent(HomeActivity.this, DetailWeatherActivity.class);
            i.putExtra("data", currentWeatherItem);
            startActivity(i);
        }
    }

    /*
     * GEOLOCATION PERMISSION
     * */
    private ArrayList findUnAskedPermissions(ArrayList wanted) {
        ArrayList result = new ArrayList();

        for (Object perm : wanted) {
            if (!hasPermission((String) perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (Object perms : permissionsToRequest) {
                    if (!hasPermission((String) perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale((String) permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions((String[]) permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce == true){
            finish();
            System.exit(0);
        } else {
            doubleBackToExitPressedOnce = true;
            dialogHelper.getToast(getString(R.string.text_click_back_again));
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}