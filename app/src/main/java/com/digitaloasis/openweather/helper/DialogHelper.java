package com.digitaloasis.openweather.helper;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitaloasis.openweather.R;

public class DialogHelper {

    private Context context;

    public DialogHelper(Context context) {
        this.context = context;
    }

    public void dialogError(String title, String msgError){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.component_dialog_error);
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.custom_bg_radius_10dp_white));

        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvBody = dialog.findViewById(R.id.tvBody);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        tvTitle.setText(title);
        tvBody.setText(msgError);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void getToast(String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}
