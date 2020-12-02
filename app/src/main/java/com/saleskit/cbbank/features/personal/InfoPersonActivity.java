package com.saleskit.cbbank.features.personal;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.InfoPersonJson;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoPersonActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_person_name)
    TextView tvPersonName;
    @BindView(R.id.bt_area)
    LinearLayout btArea;
    @BindView(R.id.rv_position)
    RecyclerView rvPosition;
    private String token;
    private PostionAdapter postionAdapter;
    private List<InfoPersonJson.DataBean.PositionBean> positionBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_person);
        ButterKnife.bind(this);
        token = HawkHelper.getInstance(this).getToken();
        showLoading();
        setupUi();
        getData();
    }

    private void setupUi() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvPosition.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_info_person;
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

    private void getData() {
        btnBack.setOnClickListener(v -> {
            finish();
        });
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<InfoPersonJson> call = apiInterface.getInfoPerson(token);
        call.enqueue(new Callback<InfoPersonJson>() {
            @Override
            public void onResponse(Call<InfoPersonJson> call, Response<InfoPersonJson> response) {
                hideLoading();
                if (response.code() == 200) {
                    positionBeans.clear();
                    InfoPersonJson.DataBean dataBean = response.body().getData();
                    positionBeans = dataBean.getPosition();
                    tvPersonName.setText(dataBean.getFullName());
                    postionAdapter = new PostionAdapter(InfoPersonActivity.this, positionBeans);
                    rvPosition.setAdapter(postionAdapter);
                } else {
                    Toast.makeText(InfoPersonActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InfoPersonJson> call, Throwable t) {
                hideLoading();
            }
        });
    }
}
