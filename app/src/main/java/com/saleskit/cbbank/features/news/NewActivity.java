package com.saleskit.cbbank.features.news;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewActivity extends BaseActivity implements OnItemClicklistener {
    private static final String TAG = "NewActivity";
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rv_new)
    RecyclerView rvNew;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    ClearableEditText etSearch;
    @BindView(R.id.appbar_main)
    AppBarLayout appbarMain;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private String token;
    private List<News.DataBean> originList = new ArrayList<>();
    private List<News.DataBean> searchNewList = new ArrayList<>();
    private NewAdapter newAdapter;
    int page = 1;
    int totalPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        setupSwipe();
        token = HawkHelper.getInstance(this).getToken();
        showLoading();
        page = 1;
        rvNew.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition = recyclerView.getChildCount() == 0 ? 0 : recyclerView.getChildAt(0).getTop();
                swAll.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvNew.setLayoutManager(linearLayoutManager);
        getData();
        ivBack.setOnClickListener(v -> finish());
        ivSearch.setOnClickListener(v -> {
            tvResult.setVisibility(View.GONE);
            showLoading();
            page=1;
            getData();
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
                if (s.toString().isEmpty()) {
                    page = 1;
                    getData();
                }

            }
        });

    }

    private void setupSwipe() {
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            page=1;
            getData();
        });
    }

    private void getData() {
        tvResult.setVisibility(View.GONE);
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(etSearch.getText().toString())) {
            option.put("searchKey", etSearch.getText().toString());
        }
        Call<News> call = apiInterface.getAllNews(token, option);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                hideLoading();
                if (response.code() == 200) {
                    swAll.setRefreshing(false);
                    List<News.DataBean> list = response.body().getData();
                    int totalItems = response.body().getTotalRecords();
                    totalPages  = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        totalPages += 1;
                    }
                    if (page == 1) {
                        originList.clear();
                    }
                    originList.addAll(list);
                    if(originList.size()==0){
                        tvResult.setVisibility(View.VISIBLE);
                    }
                    if (newAdapter == null) {
                        newAdapter = new NewAdapter(originList, NewActivity.this, NewActivity.this::onItemClick, rvNew);
                        rvNew.setAdapter(newAdapter);
                    } else {
                        newAdapter.notifyDataSetChanged();
                    }

                    newAdapter.setLoaded();

                    newAdapter.setOnLoadMoreListener(() -> {
                        if (page > totalPages && totalPages > 0) return;
                        getData();
                    });
                    page++;

                } else {
                    Toast.makeText(NewActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable throwable) {
                hideLoading();
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_new;
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
    public void onItemClick(int position) {
        overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
    }
}
