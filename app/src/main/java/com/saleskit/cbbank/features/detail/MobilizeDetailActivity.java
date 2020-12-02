package com.saleskit.cbbank.features.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.customer.CustomerListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MobilizeDetailActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.bt_creat_mobi)
    Button btCreat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilize_detail);
        ButterKnife.bind(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btCreat.setOnClickListener(v -> {
            Intent i = new Intent(this, CustomerListActivity.class);
            i.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_HD);
            startActivity(i);
        });
    }
}
