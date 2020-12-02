package com.saleskit.cbbank.features.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.EmployeeInfomation;
import com.saleskit.cbbank.features.account.InterestMenu;
import com.saleskit.cbbank.features.account.LoginEnActivity;
import com.saleskit.cbbank.features.appointment.AppointListActivity;
import com.saleskit.cbbank.features.base.BaseFragment;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.home.BlxActivity;
import com.saleskit.cbbank.features.home.HomeMvpView;
import com.saleskit.cbbank.features.home.InterestTableAdapter;
import com.saleskit.cbbank.features.home.ReportViewActivity;
import com.saleskit.cbbank.features.home.activity.HomeActivity2;
import com.saleskit.cbbank.features.kpi.RoomKpiActivity;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.features.personal.InfoPersonActivity;
import com.saleskit.cbbank.features.tun.TunActivity;
import com.saleskit.cbbank.injection.component.FragmentComponent;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Token;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlideMenuFragment extends BaseFragment {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_postion)
    TextView tvPostion;
    @BindView(R.id.bt_product)
    LinearLayout btProduct;
    @BindView(R.id.bt_lookup)
    LinearLayout btLookup;
    @BindView(R.id.bt_new)
    LinearLayout btNew;
    @BindView(R.id.bt_tracking)
    LinearLayout btTracking;
    @BindView(R.id.bt_kpi)
    LinearLayout btKpi;
    @BindView(R.id.iv_100)
    ImageView iv100;
    @BindView(R.id.bt_manage)
    RelativeLayout btManage;
    @BindView(R.id.bt_log_out)
    LinearLayout btLogOut;
    @BindView(R.id.bt_close)
    ImageView btClose;
    @BindView(R.id.rl_person)
    RelativeLayout rlPerson;
    @BindView(R.id.tv_work_result)
    TextView tvWorkResult;
    @BindView(R.id.tv_rework_customer)
    TextView tvRework;
    @BindView(R.id.tv_work_schedule)
    TextView tvWorkSchedule;
    @BindView(R.id.iv_back_slide)
    ImageView ivBack;
    @BindView(R.id.iv_employee_manger)
    ImageView ivEmployeeManger;
    @BindView(R.id.ll_employee_manager)
    LinearLayout llEmployeeManager;
    @BindView(R.id.iv_blx)
    ImageView ivBlx;
    @BindView(R.id.iv_arrow_blx)
    ImageView ivArrowBlx;
    @BindView(R.id.bt_blx)
    RelativeLayout btBlx;
    @BindView(R.id.rv_interest_table)
    RecyclerView rvInterest;
    @BindView(R.id.iv_report_home)
    ImageView ivReportHome;
    @BindView(R.id.iv_arrow_report)
    ImageView ivArrowReport;
    @BindView(R.id.bt_report)
    RelativeLayout btReport;
    @BindView(R.id.rv_report)
    RecyclerView rvReport;
    private HomeMvpView homeMvpView;
    private DisplayMetrics metrics;
    private Unbinder unbinder;
    private Token.DataBean token;
    private EmployeeInfomation.DataBean employee;
    private List<EmployeeInfomation.DataBean.PositionBean> positionBeans = new ArrayList<>();
    private boolean isBlxOpen, isEmloyessOpen = false;
    private boolean isReportOpen = false;
    private boolean showEmployeeManger = false;
    private InterestTableAdapter interestTableAdapter;
    private String author;
    private List<InterestMenu.DataBean> interestDataBeans = new ArrayList<>();
    private List<InterestMenu.DataBean> reportbeans = new ArrayList<>();
    private boolean sameRegion = true;
    private boolean sameBranch = true;
    private boolean sameDepartment = true;
    private boolean isLeaderAll = false;
    private boolean isLeaderBranch = false;
    private boolean isLeaderRegion = false;
    private boolean isLeaderReBr = false;
    private boolean isLeaderBrDe = false;
    private boolean isLeaderReDe = false;
    private boolean isLeaderDepart = false;
    private boolean isLeader = false;
    private boolean isSaleViewAllKPI = false;

    public SlideMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slide_menu, container, false);
        metrics = new DisplayMetrics();
        FragmentActivity activity = getActivity();

        if (activity != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            view.setLayoutParams(new LinearLayout.LayoutParams(metrics.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        token = HawkHelper.getInstance(getContext()).getTokenModel();

        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvInterest.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvReport.setLayoutManager(linearLayoutManager1);
        getReportData();
        author = HawkHelper.getInstance(getContext()).getToken();
//        ggetEmployeeInfo();
//        getMenuInterestTable();
        tvName.setText(token.getFullName());

        setupUI();
        return view;
    }

    // Lấy list báo cáo
    private void getReportData() {
        reportbeans.clear();
        OnItemClicklistener onItemClicklistenerReport = position -> {
            EmployeeInfomation.DataBean dataBeanM = HawkHelper.getInstance(getContext()).getEmployeeInfo();
            if (dataBeanM != null) {
                Intent i = new Intent(getContext(), ReportViewActivity.class);
                if (position == 0) {
                    i.putExtra(Constants.REPORT_TYPE, false);
                } else {
                    i.putExtra(Constants.REPORT_TYPE, true);
                }
                startActivity(i);
            }
        };
        reportbeans.add(new InterestMenu.DataBean("Báo cáo của TTDDVKH"));
        reportbeans.add(new InterestMenu.DataBean("Báo cáo lệch user thụ hưởng"));
        interestTableAdapter = new InterestTableAdapter(reportbeans, getContext(), onItemClicklistenerReport);
        rvReport.setAdapter(interestTableAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_slide_menu;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }

    // lấy info user và phân quyền và setup click event của chức năng trên slide menu
    public void ggetEmployeeInfo() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(getContext()).create(APIWebservices.class);
        Call<EmployeeInfomation> call = apiInterface.getEmployeeInfo(author);
        call.enqueue(new Callback<EmployeeInfomation>() {
            @Override
            public void onResponse(Call<EmployeeInfomation> call, Response<EmployeeInfomation> response) {
                hideLoading();
                if (response.code() == 200) {
                    EmployeeInfomation.DataBean dataBean = response.body().getData();
                    HawkHelper.getInstance(getContext()).saveEmployeeInfo(dataBean);
                    isSaleViewAllKPI = dataBean.isIsSaleViewAllKPI();
                    HomeActivity2.tvName.setText(dataBean.getFullName());
                    ;
                    positionBeans = dataBean.getPosition();


                    if (positionBeans.size() == 0 && !isSaleViewAllKPI) {
                        homeMvpView.onShowUserError();
                        return;
                    }

                    if (positionBeans.size() != 0) {

                        for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                            if (positionBean.isIsLeaderDepartment()) {
                                isLeaderDepart = true;
                                showEmployeeManger = true;
                                break;
                            }

                        }
                        for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                            if (positionBean.isIsLeaderRegion()) {
                                showEmployeeManger = true;
                                isLeaderRegion = true;
                                break;
                            }

                        }
                        for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                            if (positionBean.isIsLeaderBranch()) {
                                showEmployeeManger = true;
                                isLeaderBranch = true;
                                break;
                            }

                        }
                        if (showEmployeeManger || isSaleViewAllKPI) {
                            btManage.setVisibility(View.VISIBLE);
                        } else {
                            btManage.setVisibility(View.GONE);
                        }
                        for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                            if (positionBean.isIsLeaderDepartment() || positionBean.isIsLeaderRegion() || positionBean.isIsLeaderBranch()) {
                                isLeader = true;
                                break;
                            }

                        }
                        if (!isLeader) {
                            tvPostion.setText(positionBeans.get(0).getPositionTitleName());
                        }
                        if (isLeader) {
                            HashMap<String, Integer> positions = new HashMap<>();
                            for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                                int count = 0;
                                if (positionBean.isIsLeaderDepartment()) count += 100;
                                if (positionBean.isIsLeaderBranch()) count += 101;
                                if (positionBean.isIsLeaderRegion()) count += 102;
                                positions.put(positionBean.getPositionTitleName(), count);
                            }
                            String positionName =
                                    Collections.max(positions.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
                            tvPostion.setText(positionName);
                        }

                    } else {

                    }

                    tvWorkResult.setOnClickListener(v -> {
                        if (isSaleViewAllKPI) {
                            Intent i = new Intent(getContext(), RoomKpiActivity.class);
                            i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                            i.putExtra(Constants.LOCAL_TIME, true);
                            startActivity(i);
                        } else {
                            if (positionBeans.size() == 1) {
                                if (positionBeans.get(0).isIsLeaderRegion()) {
                                    Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                    i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                                    i.putExtra(Constants.LOCAL_TIME, true);
                                    startActivity(i);
                                } else if (!positionBeans.get(0).isIsLeaderRegion() && positionBeans.get(0).isIsLeaderBranch()) {
                                    Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                    i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
                                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getBranchId());
                                    i.putExtra(Constants.LOCAL_TIME, true);
                                    startActivity(i);

                                } else if (!positionBeans.get(0).isIsLeaderRegion() && !positionBeans.get(0).isIsLeaderBranch()
                                        && positionBeans.get(0).isIsLeaderDepartment()) {
                                    Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                    i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
                                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getDepartmentId());
                                    i.putExtra(Constants.LOCAL_TIME, true);
                                    startActivity(i);
                                }
                            } else {
                                int size = positionBeans.size();
                                if (isLeaderRegion) {
                                    for (int i = 0; i < size; i++) {
                                        String origin = positionBeans.get(0).getRegionId();
                                        if (!origin.equals(positionBeans.get(i).getRegionId())) {
                                            sameRegion = false;
                                            break;
                                        }
                                    }
                                    if (!sameRegion) {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                                        startActivity(i);
                                        return;
                                    } else {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                                        startActivity(i);
                                        return;
                                    }
                                }

                                if (isLeaderBranch) {
                                    for (int i = 0; i < size; i++) {
                                        String origin = positionBeans.get(0).getRegionId();
                                        if (!origin.equals(positionBeans.get(i).getRegionId())) {
                                            sameRegion = false;
                                            break;
                                        }
                                    }
                                    if (!sameRegion) {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                                        startActivity(i);
                                        return;
                                    }

                                    for (int i = 0; i < size; i++) {
                                        String origin = positionBeans.get(0).getBranchId();
                                        if (!origin.equals(positionBeans.get(i).getBranchId())) {
                                            sameBranch = false;
                                            break;
                                        }
                                    }
                                    if (!sameBranch) {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        startActivity(i);
                                        return;

                                    } else {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getBranchId());
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        startActivity(i);
                                        return;
                                    }
                                }

                                if (isLeaderDepart) {
                                    for (int i = 0; i < size; i++) {
                                        String origin = positionBeans.get(0).getRegionId();
                                        if (!origin.equals(positionBeans.get(i).getRegionId())) {
                                            sameRegion = false;
                                            break;
                                        }
                                    }
                                    if (!sameRegion) {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                                        startActivity(i);
                                        return;
                                    }

                                    for (int i = 0; i < size; i++) {
                                        String origin = positionBeans.get(0).getBranchId();
                                        if (!origin.equals(positionBeans.get(i).getBranchId())) {
                                            sameBranch = false;
                                            break;
                                        }
                                    }
                                    if (!sameBranch) {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        startActivity(i);
                                        return;

                                    }

                                    for (int i = 0; i < size; i++) {
                                        String origin = positionBeans.get(0).getDepartmentId();
                                        if (!origin.equals(positionBeans.get(i).getDepartmentId())) {
                                            sameDepartment = false;
                                            break;
                                        }
                                    }
                                    if (!sameDepartment) {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getBranchId());
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        startActivity(i);
                                        return;
                                    } else {
                                        Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                        i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
                                        i.putExtra(Constants.KPI_ID, positionBeans.get(0).getDepartmentId());
                                        i.putExtra(Constants.LOCAL_TIME, true);
                                        startActivity(i);
                                        return;
                                    }
                                }
                                if (sameDepartment && sameRegion && sameBranch) {
                                    Intent i = new Intent(getContext(), RoomKpiActivity.class);
                                    i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
                                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getDepartmentId());
                                    i.putExtra(Constants.LOCAL_TIME, true);
                                    startActivity(i);
                                    return;
                                }


                            }


                        }
                    });
                    btLookup.setOnClickListener(v -> {
                        if (isSaleViewAllKPI || !isLeader) {
                            Intent i = new Intent(getContext(), TunActivity.class);
                            i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                            i.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_HOME);
//                                for(EmployeeInfomation.DataBean.PositionBean positionBean: positionBeans){
//                                    if(positionBean.isIsLeaderRegion()){
                            startActivity(i);
                        } else {
                            changeScreen(positionBeans, Constants.CUSTOMER_HOME);
                        }


                    });

                    tvWorkSchedule.setOnClickListener(v -> {
                        if (isSaleViewAllKPI) {
                            Intent i = new Intent(getContext(), TunActivity.class);
                            i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                            i.putExtra(Constants.CUSTOMER_TYPE, Constants.WORK_SCHEDULE);
                            startActivity(i);
                        } else {
                            changeScreen(positionBeans, Constants.WORK_SCHEDULE);
                        }
                    });
                    tvRework.setOnClickListener(v -> {
                        if (isSaleViewAllKPI) {
                            Intent i = new Intent(getContext(), TunActivity.class);
                            i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                            i.putExtra(Constants.CUSTOMER_TYPE, Constants.REWORK_TYPE);
                            startActivity(i);
                        } else {
                            changeScreen(positionBeans, Constants.REWORK_TYPE);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<EmployeeInfomation> call, Throwable t) {
            }
        });
    }

    private void changeScreen
            (List<EmployeeInfomation.DataBean.PositionBean> positionBeans, String type) {
        if (positionBeans.size() == 1) {
            if (positionBeans.get(0).isIsLeaderRegion()) {
                Intent i = new Intent(getContext(), TunActivity.class);
                i.putExtra(Constants.CUSTOMER_TYPE, type);
                i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                startActivity(i);
                return;
            } else if (!positionBeans.get(0).isIsLeaderRegion() && positionBeans.get(0).isIsLeaderBranch()) {
                Intent i = new Intent(getContext(), TunActivity.class);
                i.putExtra(Constants.CUSTOMER_TYPE, type);
                i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
                i.putExtra(Constants.KPI_ID, positionBeans.get(0).getBranchId());
                startActivity(i);
                return;
//                    finish();
            } else if (!positionBeans.get(0).isIsLeaderRegion() && !positionBeans.get(0).isIsLeaderBranch()
                    && positionBeans.get(0).isIsLeaderDepartment()) {
                Intent i = new Intent(getContext(), TunActivity.class);
                i.putExtra(Constants.CUSTOMER_TYPE, type);
                i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
                i.putExtra(Constants.KPI_ID, positionBeans.get(0).getDepartmentId());
                startActivity(i);
                return;
//                    finish();
            }
        } else {
            int size = positionBeans.size();
            if (isLeaderRegion) {
                for (int i = 0; i < size; i++) {
                    String origin = positionBeans.get(0).getRegionId();
                    if (!origin.equals(positionBeans.get(i).getRegionId())) {
                        sameRegion = false;
                        break;
                    }
                }
                if (!sameRegion) {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                    startActivity(i);
                    return;
                } else {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                    startActivity(i);
                    return;
                }
            }
            if (isLeaderBranch) {
                for (int i = 0; i < size; i++) {
                    String origin = positionBeans.get(0).getRegionId();
                    if (!origin.equals(positionBeans.get(i).getRegionId())) {
                        sameRegion = false;
                        break;
                    }
                }
                if (!sameRegion) {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                    startActivity(i);
                    return;
                }
                for (int i = 0; i < size; i++) {
                    String origin = positionBeans.get(0).getBranchId();
                    if (!origin.equals(positionBeans.get(i).getBranchId())) {
                        sameBranch = false;
                        break;
                    }
                }
                if (!sameBranch) {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                    startActivity(i);
                    return;

                } else {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getBranchId());
                    startActivity(i);
                    return;
                }
            }

            if (isLeaderDepart) {
                for (int i = 0; i < size; i++) {
                    String origin = positionBeans.get(0).getRegionId();
                    if (!origin.equals(positionBeans.get(i).getRegionId())) {
                        sameRegion = false;
                        break;
                    }
                }
                if (!sameRegion) {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                    startActivity(i);
                    return;
                }
                for (int i = 0; i < size; i++) {
                    String origin = positionBeans.get(0).getBranchId();
                    if (!origin.equals(positionBeans.get(i).getBranchId())) {
                        sameBranch = false;
                        break;
                    }
                }
                if (!sameBranch) {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getRegionId());
                    startActivity(i);
                    return;

                }
                for (int i = 0; i < size; i++) {
                    String origin = positionBeans.get(0).getDepartmentId();
                    if (!origin.equals(positionBeans.get(i).getDepartmentId())) {
                        sameDepartment = false;
                        break;
                    }
                }
                if (!sameDepartment) {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getBranchId());
                    startActivity(i);
                    return;
                } else {
                    Intent i = new Intent(getContext(), TunActivity.class);
                    i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
                    i.putExtra(Constants.CUSTOMER_TYPE, type);
                    i.putExtra(Constants.KPI_ID, positionBeans.get(0).getDepartmentId());
                    startActivity(i);
                    return;
                }
            }
            if (sameDepartment && sameRegion && sameBranch) {
                Intent i = new Intent(getContext(), TunActivity.class);
                i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
                i.putExtra(Constants.CUSTOMER_TYPE, type);
//                                    for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
//                                        if (positionBean.isIsLeaderBranch()) {
                i.putExtra(Constants.KPI_ID, positionBeans.get(0).getDepartmentId());
                startActivity(i);
                return;
            }


        }

        Intent i = new Intent(getContext(), TunActivity.class);
        i.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_HOME);
        i.putExtra(Constants.KPI_TYPE, Constants.NORMAL);
        startActivity(i);
        return;
    }

    public void getMenuInterestTable() {
        APIWebservices apiInterface = NetworkUtil.getCBclient(getContext()).create(APIWebservices.class);
        Call<InterestMenu> call = apiInterface.getInterestMenu(author);
        call.enqueue(new Callback<InterestMenu>() {
            @Override
            public void onResponse(Call<InterestMenu> call, Response<InterestMenu> response) {
                if (response.code() == 200) {
                    interestDataBeans.clear();
                    interestDataBeans = response.body().getData();
                    OnItemClicklistener onItemClicklistener = position -> {
                        Intent i = new Intent(getContext(), BlxActivity.class);
                        i.putExtra(Constants.CODE, interestDataBeans.get(position).getInterestRateTableId());
                        startActivity(i);
                    };
                    interestTableAdapter = new InterestTableAdapter(interestDataBeans, getContext(), onItemClicklistener);
                    rvInterest.setAdapter(interestTableAdapter);
                }
            }

            @Override
            public void onFailure(Call<InterestMenu> call, Throwable t) {

            }
        });
    }

    private void setupUI() {
        btTracking.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AppointListActivity.class);
            i.putExtra(Constants.APPOINT_TYPE, Constants.HOME_TYPE);
            startActivity(i);
            return;
        });
        rlPerson.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), InfoPersonActivity.class);
            startActivity(i);
        });
        ivBack.setOnClickListener(v -> {
            homeMvpView.onHomeClick();
        });
        btKpi.setOnClickListener(v -> {
            homeMvpView.onKPIClick();
        });
        btClose.setOnClickListener(v -> {
            homeMvpView.closeDrawer();
        });
        btNew.setOnClickListener(v -> {
            homeMvpView.openNewScreen();

        });


        btProduct.setOnClickListener(v -> {
            homeMvpView.onHomeClick();
//            Intent i = new Intent(getContext(), HomeActivity2.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(i);
//            getActivity().overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
        });
        btLogOut.setOnClickListener(v -> homeMvpView.onLogOut());

        btManage.setOnClickListener(v -> {
            isEmloyessOpen = !isEmloyessOpen;
            if (!isEmloyessOpen) {
                Glide.with(getContext()).load(R.drawable.ic_menu_down).into(ivEmployeeManger);
                llEmployeeManager.setVisibility(View.GONE);
            } else {
                Glide.with(getContext()).load(R.drawable.ic_menu_up).into(ivEmployeeManger);
                llEmployeeManager.setVisibility(View.VISIBLE);
            }
        });

        btBlx.setOnClickListener(v -> {
            isBlxOpen = !isBlxOpen;
            if (!isBlxOpen) {
                Glide.with(getContext()).load(R.drawable.ic_menu_down).into(ivArrowBlx);
                rvInterest.setVisibility(View.GONE);
            } else {
                Glide.with(getContext()).load(R.drawable.ic_menu_up).into(ivArrowBlx);
                rvInterest.setVisibility(View.VISIBLE);
            }
        });

        btReport.setOnClickListener(v -> {
            isReportOpen = !isReportOpen;
            if (!isReportOpen) {
                Glide.with(getContext()).load(R.drawable.ic_menu_down).into(ivArrowReport);
                rvReport.setVisibility(View.GONE);
            } else {
                Glide.with(getContext()).load(R.drawable.ic_menu_up).into(ivArrowReport);
                rvReport.setVisibility(View.VISIBLE);
            }
        });

    }

    public void setListener(HomeMvpView homeMvpView) {
        this.homeMvpView = homeMvpView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
