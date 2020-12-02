package com.saleskit.cbbank.features.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.saleskit.cbbank.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerDetailActivity extends AppCompatActivity {


    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.tv_date_birth)
    TextView tvDateBirth;
    @BindView(R.id.bt_gender)
    LinearLayout btGender;
    @BindView(R.id.tv_identity)
    TextView tvIdentity;
    @BindView(R.id.tv_date_provide)
    TextView tvDateProvide;
    @BindView(R.id.bt_pick_edcation)
    LinearLayout btPickEdcation;
    @BindView(R.id.bt_pick_interest)
    LinearLayout btPickInterest;
    @BindView(R.id.bt_pick_room)
    LinearLayout btPickRoom;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.bt_complete)
    Button btComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        ButterKnife.bind(this);
        setupUi();
    }

    private void setupUi() {
        btnBack.setOnClickListener(v -> onBackPressed());
        btComplete.setOnClickListener(v -> {
            Intent i = new Intent(CustomerDetailActivity.this, TransitionCodeActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
        });
    }
}
