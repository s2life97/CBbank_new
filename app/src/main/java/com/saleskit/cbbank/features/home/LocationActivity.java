package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Datajson;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_atm)
    TextView tvAtm;
    @BindView(R.id.tv_branch)
    TextView tvBranch;
    @BindView(R.id.et_search)
    ClearableEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.appbar_main)
    AppBarLayout appbarMain;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    private List<AtmResponse.DataBean> atms = new ArrayList<>();
    private List<BranchResponse.DataBean> branchs = new ArrayList<>();
    private AtmAdapter atmAdapter;
    private BranchAdapter branchAdapter;
    private LocationType locationType = LocationType.ATM;

    private enum LocationType {
        ATM,
        BRANCH
    }

    private LocationType type = LocationType.ATM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        setupUi();
        getDataAtm();
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            if (type == LocationType.ATM) {
                getDataAtm();
            } else {
                getDataBranch();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    if (type == LocationType.ATM) {
                        getDataAtm();
                    } else {
                        getDataBranch();
                    }
                }
            }
        });
        ivSearch.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                if (type == LocationType.ATM) {
                    getDataAtm();
                } else {
                    getDataBranch();
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_location;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {

    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }

    private void getDataBranch() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("pageSize", "100");
        if (!TextUtils.isEmpty(etSearch.getText().toString())) {
            map.put("searchKey", etSearch.getText().toString());
        }
        Call<BranchResponse> call1 = apiInterface.getAllBranch(Constants.DEFAULT_AUTHOR, map);
        call1.enqueue(new Callback<BranchResponse>() {
            @Override
            public void onResponse(Call<BranchResponse> call, Response<BranchResponse> response) {
                hideLoading();
                swAll.setRefreshing(false);
                if (response.body() != null && response.code() == 200) {
                    branchs.clear();
                    branchs = response.body().getData();
                    OnItemClicklistener onItemClicklistener = position -> {
                        Intent intent = new Intent(LocationActivity.this, DepartMentLoginActivity.class);
                        intent.putExtra(Constants.BRANCH_ID, String.valueOf(branchs.get(position).getTransactionLocationId()));
                        startActivity(intent);

                    };
                    branchAdapter = new BranchAdapter(LocationActivity.this, branchs, onItemClicklistener);
                    rvMain.setAdapter(branchAdapter);
                } else {
                    Toast.makeText(LocationActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BranchResponse> call, Throwable t) {

            }
        });
    }

    private void getDataAtm() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("pageSize", "100");
        if (!TextUtils.isEmpty(etSearch.getText().toString())) {
            map.put("searchKey", etSearch.getText().toString());
        }
        Call<AtmResponse> call = apiInterface.getAllAtm(Constants.DEFAULT_AUTHOR, map);
        call.enqueue(new Callback<AtmResponse>() {
            @Override
            public void onResponse(Call<AtmResponse> call, Response<AtmResponse> response) {
                hideLoading();
                swAll.setRefreshing(false);
                if (response.body() != null && response.code() == 200) {
                    atms.clear();
                    atms = response.body().getData();
                    atmAdapter = new AtmAdapter(LocationActivity.this, atms);
                    rvMain.setAdapter(atmAdapter);
                } else {
                    Toast.makeText(LocationActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AtmResponse> call, Throwable t) {

            }
        });

    }

    private void setupUi() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMain.setLayoutManager(linearLayoutManager);
        btnBack.setOnClickListener(v -> finish());
        tvAtm.setOnClickListener(v -> {
            type = LocationType.ATM;
            AppUtil.setBackgroundForView(v, LocationActivity.this, R.drawable.rounded_place);
            AppUtil.setBackgroundForView(tvBranch, LocationActivity.this, R.drawable.button_trans_location);
            getDataAtm();
        });
        tvBranch.setOnClickListener(v -> {
            type = LocationType.BRANCH;
            AppUtil.setBackgroundForView(v, LocationActivity.this, R.drawable.rounded_place);
            AppUtil.setBackgroundForView(tvAtm, LocationActivity.this, R.drawable.button_trans_location);
            getDataBranch();
        });

    }
}
