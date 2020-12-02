package com.saleskit.cbbank.features.tun;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.EmployeeInfomation;
import com.saleskit.cbbank.features.account.EmployeeJson;
import com.saleskit.cbbank.features.account.KPIJson;
import com.saleskit.cbbank.features.account.RegionJson;
import com.saleskit.cbbank.features.account.TunDepartmentJson;
import com.saleskit.cbbank.features.account.TunJson;
import com.saleskit.cbbank.features.appointment.AppointListActivity;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.CustomerListActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.kpi.KpiPersonAdapter;
import com.saleskit.cbbank.features.kpi.MonthAdapter;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TunActivity extends BaseActivity implements TunView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    ClearableEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.appbar_main)
    AppBarLayout appbarMain;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @Inject
    Tunpresenter tunpresenter;
    private String type = "";
    private String id = "";
    private String token;
    private int page = 1;
    //    @BindView(R.id.ll_drop)
//    LinearLayout llDrop;
    private List<String> months = new ArrayList<>();
    private MonthAdapter monthAdapter;
    private KpiPersonAdapter kpiPersonAdapter;
    private int totalPages;
    private String homeType;
    private boolean canRework = false;
    private boolean isLeader = false;
    private boolean isLeaderDepartment = true;
    private ArrayList<KPIJson.DataBean> kpiList = new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private List<EmployeeInfomation.DataBean.PositionBean> positionBeans = new ArrayList<>();
    private List<String> regionIds = new ArrayList<>();
    private boolean sameRegion = true;
    private boolean sameBranch = true;
    private boolean isSaleAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tun);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        token = HawkHelper.getInstance(this).getToken();
        positionBeans = HawkHelper.getInstance(this).getEmployeeInfo().getPosition();
        isSaleAll = HawkHelper.getInstance(this).getEmployeeInfo().isIsSaleViewAllKPI();
        setupUI();
        if (intent != null) {
            homeType = intent.getStringExtra(Constants.CUSTOMER_TYPE);
            id = intent.getStringExtra(Constants.KPI_ID);
            type = intent.getStringExtra(Constants.KPI_TYPE);
            if (positionBeans.size() == 0) {
                isLeader = true;
            } else {
                for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                    if (positionBean.isIsLeaderDepartment() || positionBean.isIsLeaderBranch() || positionBean.isIsLeaderRegion()) {
                        isLeader = true;
                        break;
                    }
                }
            }

            if (!isLeader && !isSaleAll) {
                if (homeType.equals(Constants.WORK_SCHEDULE)) {
                    Intent i = new Intent(TunActivity.this, AppointListActivity.class);
                    i.putExtra(Constants.APPOINT_TYPE, Constants.TUN_TYPE);
                    i.putExtra(Constants.USER_ID, HawkHelper.getInstance(this).getTokenModel().getUserName());
                    startActivity(i);
                    finish();
                } else if (homeType.equals(Constants.CUSTOMER_HOME)) {
                    Intent i = new Intent(this, CustomerListActivity.class);
                    i.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_HOME);
                    i.putExtra(Constants.DEPARTMENT_ID, id);
                    i.putExtra(Constants.CAN_REWORK, canRework);
                    i.putExtra(Constants.USER_NAME, HawkHelper.getInstance(this).getTokenModel().getUserName());
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(TunActivity.this, CustomerListActivity.class);
                    i.putExtra(Constants.CUSTOMER_TYPE, homeType);
                    i.putExtra(Constants.USER_FULL_NAME, HawkHelper.getInstance(this).getTokenModel().getFullName());
                    i.putExtra(Constants.DEPARTMENT_ID, id);
                    i.putExtra(Constants.CAN_REWORK, canRework);
                    i.putExtra(Constants.USER_NAME, HawkHelper.getInstance(this).getTokenModel().getUserName());
                    startActivity(i);
                    finish();
                }
            }


        }


        switch (type) {
            case Constants.DEPARTMENT:
                tvName.setText("Phòng");
                tunpresenter.getAllDepartment(this, swAll, id, page, etSearch.getText().toString(), token);
//                    tunpresenter.getAllDepartment(this, swAll, id, page);
                break;
            case Constants.EMPLOYEE:
                tvName.setText("Họ và tên");
                tunpresenter.getAllEmployee(this, swAll, id, page, etSearch.getText().toString(), token);
//                    tunpresenter.getAllEmployee(this, swAll, id, page);
                break;
            case Constants.BRANCH:
//                    getData();
                tvName.setText("Chi nhánh");
                tunpresenter.getAllBranch(this, swAll, id, page, etSearch.getText().toString(), token);
                break;
            default:
                tvName.setText("Vùng");
                tunpresenter.getRegion(TunActivity.this, swAll, page, etSearch.getText().toString(), token);
                break;
        }

        ivBack.setOnClickListener(v -> finish());
        setupSwipe(type);
    }

    private void setupUI() {
        rvDetail.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        LinearLayoutManager kpiLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDetail.setLayoutManager(kpiLinearLayoutManager);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tun;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        tunpresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        tunpresenter.detachView();
    }

    private void setupSwipe(String type) {
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            page = 1;
            switch (type) {
                case Constants.DEPARTMENT:
                    tunpresenter.getAllDepartment(this, swAll, id, page, etSearch.getText().toString(), token);
                    break;
                case Constants.EMPLOYEE:
                    tunpresenter.getAllEmployee(this, swAll, id, page, etSearch.getText().toString(), token);
                    break;
                case Constants.BRANCH:
//                    tunpresenter.getAllBranch(this, swAll, id);
                    tunpresenter.getAllBranch(this, swAll, id, page, etSearch.getText().toString(), token);
                    break;
                default:
                    tunpresenter.getRegion(TunActivity.this, swAll, page, etSearch.getText().toString(), token);
                    break;
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
                if (TextUtils.isEmpty(s)) {
                    page = 1;
                    switch (type) {
                        case Constants.DEPARTMENT:
                            tunpresenter.getAllDepartment(TunActivity.this, swAll, id, page, etSearch.getText().toString(), token);
                            break;
                        case Constants.EMPLOYEE:
                            tunpresenter.getAllEmployee(TunActivity.this, swAll, id, page, etSearch.getText().toString(), token);
                            break;
                        case Constants.BRANCH:
                            tunpresenter.getAllBranch(TunActivity.this, swAll, id, page, etSearch.getText().toString(), token);
                            break;
                        default:
                            tunpresenter.getRegion(TunActivity.this, swAll, page, etSearch.getText().toString(), token);
                            break;
                    }
                }
            }
        });


        ivSearch.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                page = 1;
                switch (type) {
                    case Constants.DEPARTMENT:
                        tunpresenter.getAllDepartment(TunActivity.this, swAll, id, page, etSearch.getText().toString(), token);
                        break;
                    case Constants.EMPLOYEE:
                        tunpresenter.getAllEmployee(TunActivity.this, swAll, id, page, etSearch.getText().toString(), token);
                        break;
                    case Constants.BRANCH:
                        tunpresenter.getAllBranch(TunActivity.this, swAll, id, page, etSearch.getText().toString(), token);
                        break;
                    default:
                        tunpresenter.getRegion(TunActivity.this, swAll, page, etSearch.getText().toString(), token);
                        break;
                }
            }
        });

    }

    @Override
    public void showAllRegion(List<RegionJson.DataBean.ResultsBean> dataBeans, int totalPages) {
        this.totalPages = totalPages;
        if (page == 1) {
            kpiList.clear();
        }
//        if (page == 1) {
//            kpiList.clear();
//        }
        OnItemClicklistener onItemClicklistener = position -> {
            Intent i = new Intent(TunActivity.this, TunActivity.class);
            i.putExtra(Constants.CUSTOMER_TYPE, homeType);
            i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
            i.putExtra(Constants.KPI_ID, kpiList.get(position).getJobTitle());
            startActivity(i);
        };
        for (RegionJson.DataBean.ResultsBean dataBean : dataBeans) {
            kpiList.add(new KPIJson.DataBean(dataBean.getName(), dataBean.getId(), 0, false));
        }
        if (kpiList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }

        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, false, rvDetail);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }
        kpiPersonAdapter.setLoaded();
        page++;

        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            tunpresenter.getAllBranch(this, swAll, id, page, etSearch.getText().toString(), token);
        });
        kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, false, rvDetail);
        rvDetail.setAdapter(kpiPersonAdapter);
    }

    @Override
    public void showBranchlist(List<TunJson.DataBean.ResultsBean> list, int pages) {
        this.totalPages = pages;
        if (page == 1) {
            kpiList.clear();
        }
        OnItemClicklistener onItemClicklistener = position -> {
            Intent i = new Intent(TunActivity.this, TunActivity.class);
            i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
            i.putExtra(Constants.CUSTOMER_TYPE, homeType);
            i.putExtra(Constants.KPI_ID, kpiList.get(position).getJobTitle());
            startActivity(i);
        };

        for (TunJson.DataBean.ResultsBean dataBean : list) {
            kpiList.add(new KPIJson.DataBean(dataBean.getName(), dataBean.getId(), 0, false));
        }
        if (kpiList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }

        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, false, rvDetail);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }
        kpiPersonAdapter.setLoaded();
        page++;

        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            tunpresenter.getAllBranch(this, swAll, id, page, etSearch.getText().toString(), token);
        });

    }

    @Override
    public void showAllDepartment(List<TunDepartmentJson.DataBean.ResultsBean> list, int pages) {
        this.totalPages = pages;
        if (list.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);

        } else {
            tvResult.setVisibility(View.GONE);
        }
        if (page == 1) {
            kpiList.clear();
        }
        OnItemClicklistener onItemClicklistener = position -> {
            Intent i = new Intent(TunActivity.this, TunActivity.class);
            i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
            i.putExtra(Constants.CUSTOMER_TYPE, homeType);
            i.putExtra(Constants.KPI_ID, kpiList.get(position).getJobTitle());
            startActivity(i);
        };

        for (TunDepartmentJson.DataBean.ResultsBean dataBean : list) {
            kpiList.add(new KPIJson.DataBean(dataBean.getName(), dataBean.getId(), 0, false));
        }
        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, false, rvDetail);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }

        kpiPersonAdapter.setLoaded();
        page++;
        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            tunpresenter.getAllDepartment(this, swAll, id, page, etSearch.getText().toString(), token);
        });
    }

    @Override
    public void showAllEmployee(List<EmployeeJson.DataBean.ResultsBean> list, int pages) {
        this.totalPages = pages;
        if (page == 1) {
            kpiList.clear();
        }
        if (list.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        OnItemClicklistener onItemClicklistener = position -> {
            for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                if (positionBean.isIsLeaderDepartment()) {
                    ids.add(positionBean.getDepartmentId());
                }
            }
            for (String idDepartment : ids) {
                if (idDepartment.equals(id)) {
                    canRework = true;
                    break;
                }
            }
            if (homeType.equals(Constants.WORK_SCHEDULE)) {
                Intent i = new Intent(TunActivity.this, AppointListActivity.class);
                i.putExtra(Constants.APPOINT_TYPE, Constants.TUN_TYPE);
                i.putExtra(Constants.USER_ID, kpiList.get(position).getId());
                startActivity(i);
            } else if (homeType.equals(Constants.CUSTOMER_HOME)) {
                Intent i = new Intent(this, CustomerListActivity.class);
                i.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_HOME);
                i.putExtra(Constants.IS_EMPLOYEE, isLeader);
                i.putExtra(Constants.USER_FULL_NAME, kpiList.get(position).getName());
                i.putExtra(Constants.DEPARTMENT_ID, id);
                i.putExtra(Constants.CAN_REWORK, canRework);
                i.putExtra(Constants.USER_NAME, kpiList.get(position).getId());
                startActivity(i);

            } else {
                Intent i = new Intent(TunActivity.this, CustomerListActivity.class);
                i.putExtra(Constants.CUSTOMER_TYPE, homeType);
                i.putExtra(Constants.IS_EMPLOYEE, isLeader);
                i.putExtra(Constants.USER_FULL_NAME, kpiList.get(position).getName());
                i.putExtra(Constants.DEPARTMENT_ID, id);
                i.putExtra(Constants.CAN_REWORK, canRework);
                i.putExtra(Constants.USER_NAME, kpiList.get(position).getId());
                startActivity(i);
            }

        };

        for (EmployeeJson.DataBean.ResultsBean dataBean : list) {
            kpiList.add(new KPIJson.DataBean(dataBean.getFullName(), dataBean.getPositionTitleName(), dataBean.getUserName()));
        }
        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, true, onItemClicklistener, false, rvDetail);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }


        kpiPersonAdapter.setLoaded();
        page++;
        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            tunpresenter.getAllEmployee(this, swAll, id, page, etSearch.getText().toString(), token);
        });
    }

}
