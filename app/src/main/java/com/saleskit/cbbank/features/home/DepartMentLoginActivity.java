package com.saleskit.cbbank.features.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DepartMentLoginActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
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
    @BindView(R.id.tv_result)
    TextView tvResult;
    private BranchAdapter branchAdapter;
    private List<DepartmentResponse.DataBean> list = new ArrayList<>();
    private List<BranchResponse.DataBean> branchs = new ArrayList<>();
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depart_ment_login);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra(Constants.BRANCH_ID);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMain.setLayoutManager(linearLayoutManager);
        getData();
        btnBack.setOnClickListener(v -> finish());
        ivSearch.setOnClickListener(v -> getData());
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
                    getData();
                }
            }
        });
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(this::getData);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_depart_ment_login;
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

    private void getData() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("pageSize", "100");
        map.put("parentId", id);
        if (!TextUtils.isEmpty(etSearch.getText().toString())) {
            map.put("searchKey", etSearch.getText().toString());
        }
        Call<DepartmentResponse> call1 = apiInterface.getDepartmentByid(Constants.DEFAULT_AUTHOR, map);
        call1.enqueue(new Callback<DepartmentResponse>() {
            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                hideLoading();
                swAll.setRefreshing(false);
                if (response.body() != null && response.code() == 200) {
                    list.clear();
                    branchs.clear();
                    list = response.body().getData();
                    if (list.size() == 0) {
                        tvResult.setVisibility(View.VISIBLE);
                    }else {
                        tvResult.setVisibility(View.GONE);
                    }
                    OnItemClicklistener onItemClicklistener = position -> {
                    };
                    for (DepartmentResponse.DataBean dataBean : list) {
                        branchs.add(new BranchResponse.DataBean(dataBean.getName(), dataBean.getAddress(),
                                dataBean.getPhoneNumber(), dataBean.getFaxNumber()));
                    }
                    branchAdapter = new BranchAdapter(DepartMentLoginActivity.this, branchs, onItemClicklistener);
                    rvMain.setAdapter(branchAdapter);
                } else {
                    Toast.makeText(DepartMentLoginActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
            }
        });
    }
}
