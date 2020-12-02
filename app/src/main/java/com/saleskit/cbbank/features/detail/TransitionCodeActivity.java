package com.saleskit.cbbank.features.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.home.activity.HomeActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionCodeActivity extends AppCompatActivity {

    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.bt_back)
    Button btBack;
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_code);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        code =intent.getStringExtra(Constants.CODE);
        tvCode.setText(code);
        btBack.setOnClickListener(v -> {
            Intent i = new Intent(TransitionCodeActivity.this, HomeActivity2.class);
            startActivity(i);
            finish();
        });
    }
}
