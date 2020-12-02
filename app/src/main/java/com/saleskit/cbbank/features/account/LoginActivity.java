package com.saleskit.cbbank.features.account;

import android.app.KeyguardManager;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.bumptech.glide.Glide;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.inject.Inject;

import androidx.appcompat.widget.SwitchCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class LoginActivity extends BaseActivity  {
    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.swNotify)
    SwitchCompat swNotify;
    @BindView(R.id.rl_sw)
    RelativeLayout rlSw;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.iv_finger_sprint)
    ImageView ivFingerSprint;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    private KeyGenerator keyGenerator;
    private static final String KEY_NAME = "yourKey";
    private boolean isEnabled;
    private FingerprintManager.CryptoObject cryptoObject;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private Cipher cipher;
    private KeyStore keyStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.bg_login).into(ivBg);

    }

    @Override
    protected int getLayout() {

        return R.layout.activity_login;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }



    @Override
    protected void onStart() {
        super.onStart();
        setupSwitch();
        isEnabled = HawkHelper.getInstance(this).getSwitchStatus();
        swNotify.setChecked(isEnabled);
        if (isEnabled) {
            tvLanguage.setVisibility(View.GONE);
        } else {
            tvLanguage.setVisibility(View.VISIBLE);
        }
    }

    private void setupSwitch() {
        swNotify.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvLanguage.setVisibility(View.GONE);
                Intent i = new Intent(LoginActivity.this, LoginEnActivity.class);
                startActivity(i);
            } else {
                tvLanguage.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick(R.id.iv_finger_sprint)
    public void onViewClicked() {
//
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideLoading();
    }
}
