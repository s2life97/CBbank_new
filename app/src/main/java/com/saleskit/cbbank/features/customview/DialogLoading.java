package com.saleskit.cbbank.features.customview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.saleskit.cbbank.R;
import com.bumptech.glide.Glide;

public class DialogLoading extends AlertDialog {
    private Runnable runnable;
    private Handler handler;
    public DialogLoading(Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading, null);
        ImageView aviImageView = view.findViewById(R.id.avi);
        setView(view);
        Glide.with(context).load(R.drawable.ic_loading).into(aviImageView);
        setCancelable(true);
        runnable = () -> {
            if (isShowing())
                dismiss();
        };
        handler = new Handler();
        handler.postDelayed(runnable, 15000);
    }

    public void cancelDismiss() {
        if (handler != null && runnable != null)
            handler.removeCallbacks(runnable);
    }


    public void setTimeout(int mili) {
        new Handler().postDelayed(() -> {
            if (isShowing())
                dismiss();
        }, mili);
    }

}