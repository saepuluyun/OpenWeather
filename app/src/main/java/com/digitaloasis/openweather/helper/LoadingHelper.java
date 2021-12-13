package com.digitaloasis.openweather.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.digitaloasis.openweather.R;
import com.kaopiz.kprogresshud.KProgressHUD;

public class LoadingHelper {

    private KProgressHUD loading;
    private Context mContext;

    public LoadingHelper(Context context) {
        this.mContext = context;

        loading = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setBackgroundColor(Color.TRANSPARENT)
                .setCancellable(false)
                .setAnimationSpeed(10)
                .setDimAmount(0.5f);
    }

    public void start(){
        loading.show();
    }

    public void stop(){
        loading.dismiss();
    }

}
