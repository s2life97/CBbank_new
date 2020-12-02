package com.saleskit.cbbank.features.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.home.ProductDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainProductDetailActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private List<ProductDetail.DataBean.DetailInformationsBean> list = new ArrayList<>();
    private ProductInfoAdapter productInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product_detail);
        ButterKnife.bind(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        list = (List<ProductDetail.DataBean.DetailInformationsBean>) intent.getSerializableExtra(Constants.PRODUCT_TYPE);
        String title = intent.getStringExtra(Constants.PRODUCT_NAME);
        tvTitle.setText(title);
        setupUI();
    }

    private void setupUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMain.setLayoutManager(linearLayoutManager);

        productInfoAdapter = new ProductInfoAdapter(this, list);
        rvMain.setAdapter(productInfoAdapter);

    }
}
