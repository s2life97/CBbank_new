package com.saleskit.cbbank.features.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.LoginEnActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.bg_splash_main).into(ivBg);
        new Handler().postDelayed(() -> {
            pbLoading.setVisibility(View.GONE);
            Intent i = new Intent(SplashActivity.this, LoginEnActivity.class);
            startActivity(i);
            finish();

        }, 2500);
    }
}
