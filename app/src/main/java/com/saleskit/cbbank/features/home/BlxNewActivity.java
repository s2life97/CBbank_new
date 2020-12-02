package com.saleskit.cbbank.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.InterestMenu;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class BlxNewActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private BlxLoginAdapter blxLoginAdapter;
    private List<InterestMenu.DataBean> list = new ArrayList<>();
    private List<InterestMenu.DataBean> interestDataBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blx_new);
        ButterKnife.bind(this);
        btnBack.setOnClickListener(v -> finish());
        setupUI();
    }
    private void setupUI() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMain.setLayoutManager(gridLayoutManager);
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<InterestMenu> call = apiInterface.getInterestMenu("Bea");
        call.enqueue(new Callback<InterestMenu>() {
            @Override
            public void onResponse(Call<InterestMenu> call, Response<InterestMenu> response) {
                if (response.code() == 200) {
                    interestDataBeans.clear();
                    interestDataBeans = response.body().getData();
                    OnItemClicklistener onItemClicklistener = position -> {
                        Intent i = new Intent(BlxNewActivity.this, BlxActivity.class);
                        i.putExtra(Constants.CODE, interestDataBeans.get(position).getInterestRateTableId());
                        startActivity(i);
                    };
                    blxLoginAdapter = new BlxLoginAdapter(BlxNewActivity.this, interestDataBeans, onItemClicklistener);
                    rvMain.setAdapter(blxLoginAdapter);
                }
            }

            @Override
            public void onFailure(Call<InterestMenu> call, Throwable t) {
            }
        });

    }
}
