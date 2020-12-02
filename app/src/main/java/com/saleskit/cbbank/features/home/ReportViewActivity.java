package com.saleskit.cbbank.features.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Datajson;
import com.saleskit.cbbank.features.account.EmployeeInfomation;
import com.saleskit.cbbank.features.appointment.CategoryEvent;
import com.saleskit.cbbank.features.appointment.OptionActivity;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Token;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ReportViewActivity extends BaseActivity implements OnItemClicklistener {
    private int PERMISSION_REQUEST_CODE = 0;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.bt_year)
    RelativeLayout btYear;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.bt_month)
    RelativeLayout btMonth;
    @BindView(R.id.tv_ph)
    TextView tvPh;
    @BindView(R.id.bt_ph)
    RelativeLayout btPh;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.bt_region)
    RelativeLayout btRegion;
    @BindView(R.id.tv_branch)
    TextView tvBranch;
    @BindView(R.id.bt_branch)
    RelativeLayout btBranch;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.bt_department)
    RelativeLayout btDepartment;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.bt_user)
    RelativeLayout btUser;
    @BindView(R.id.bt_view_report)
    Button btViewReport;
    @BindView(R.id.fl_end)
    FrameLayout flEnd;
    private ArrayList<String> months = new ArrayList<>();
    private ArrayList<String> years = new ArrayList<>();
    private ArrayList<String> phs = new ArrayList<>();
    private ArrayList<String> regions = new ArrayList<>();
    private ArrayList<String> branchs = new ArrayList<>();
    private ArrayList<String> departments = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private String type;
    private String month, year, user, branch, region, department, ph = "";
    private String initBranch, initRegion, initDepartment = "";
    private int currentYear;
    private int currentMonth;
    private Token.DataBean token;
    private boolean isBeneficiariesDifference;
    private EmployeeInfomation.DataBean dataBean;
    private boolean sameRegion = true;
    private boolean sameBranch = true;
    private boolean sameDepartment = true;
    private boolean isLeaderBranch = false;
    private boolean isLeaderRegion = false;
    private boolean isLeaderDepart = false;
    private boolean isEmployee = true;
    private boolean isSaleAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);
        ButterKnife.bind(this);
        dataBean = HawkHelper.getInstance(this).getEmployeeInfo();
        isSaleAll = dataBean.isIsSaleViewAllKPI();
        Intent intent = getIntent();
        isBeneficiariesDifference = intent.getBooleanExtra(Constants.REPORT_TYPE, false);
        token = HawkHelper.getInstance(this).getTokenModel();
        setupOption();
        creatList();
        setupUI();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_report_view;
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

    private void setupOption() {
//        if (dataBean.isIsSaleViewAllKPI()) {
//            btDepartment.setVisibility(View.VISIBLE);
//            btRegion.setVisibility(View.VISIBLE);
//            btBranch.setVisibility(View.VISIBLE);
//            btUser.setVisibility(View.VISIBLE);
//            return;
//        }
        if (isSaleAll) {
            return;
        }


        for (EmployeeInfomation.DataBean.PositionBean positionBean : dataBean.getPosition()) {
            if (positionBean.isIsLeaderBranch() || positionBean.isIsLeaderRegion() || positionBean.isIsLeaderDepartment()) {
                isEmployee = false;
                break;
            }
        }
        if (isEmployee) {
            btDepartment.setVisibility(View.GONE);
            btRegion.setVisibility(View.GONE);
            btBranch.setVisibility(View.GONE);
            btUser.setVisibility(View.GONE);
            btUser.setEnabled(false);
            return;
        }
        for (EmployeeInfomation.DataBean.PositionBean positionBean : dataBean.getPosition()) {
            if (positionBean.isIsLeaderDepartment()) {
                isLeaderDepart = true;
                break;
            }

        }
        for (EmployeeInfomation.DataBean.PositionBean positionBean : dataBean.getPosition()) {
            if (positionBean.isIsLeaderRegion()) {
                isLeaderRegion = true;
                break;
            }

        }
        for (EmployeeInfomation.DataBean.PositionBean positionBean : dataBean.getPosition()) {
            if (positionBean.isIsLeaderBranch()) {
                isLeaderBranch = true;
                break;
            }

        }

        if (dataBean.getPosition().size() == 1) {
            if (dataBean.getPosition().get(0).isIsLeaderRegion()) {
                btRegion.setVisibility(View.GONE);
                region = initRegion = dataBean.getPosition().get(0).getRegionId();
            } else if (!dataBean.getPosition().get(0).isIsLeaderRegion() && dataBean.getPosition().get(0).isIsLeaderBranch()) {
                btRegion.setVisibility(View.GONE);
                btBranch.setVisibility(View.GONE);
                region = initRegion = dataBean.getPosition().get(0).getRegionId();
                branch = initBranch = dataBean.getPosition().get(0).getBranchId();
            } else if (!dataBean.getPosition().get(0).isIsLeaderRegion() && !dataBean.getPosition().get(0).isIsLeaderBranch()
                    && dataBean.getPosition().get(0).isIsLeaderDepartment()) {
                btRegion.setVisibility(View.GONE);
                btBranch.setVisibility(View.GONE);
                btDepartment.setVisibility(View.GONE);
                region = initRegion = dataBean.getPosition().get(0).getRegionId();
                branch = initBranch = dataBean.getPosition().get(0).getBranchId();
                department = initDepartment = dataBean.getPosition().get(0).getDepartmentId();
            }
        } else {
            int size = dataBean.getPosition().size();
            if (isLeaderRegion) {
                for (int i = 0; i < size; i++) {
                    String origin = dataBean.getPosition().get(0).getRegionId();
                    if (!origin.equals(dataBean.getPosition().get(i).getRegionId())) {
                        sameRegion = false;
                        break;
                    }
                }
                if (!sameRegion) {
                    return;
                } else {
                    btRegion.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                    return;
                }
            }

            if (isLeaderBranch) {
                for (int i = 0; i < size; i++) {
                    String origin = dataBean.getPosition().get(0).getRegionId();
                    if (!origin.equals(dataBean.getPosition().get(i).getRegionId())) {
                        sameRegion = false;
                        break;
                    }
                }
                if (!sameRegion) {
                    return;
                } else {
                    btRegion.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                }

                String originBranch = dataBean.getPosition().get(0).getBranchId();
                for (int i = 0; i < size; i++) {
                    if (!originBranch.equals(dataBean.getPosition().get(i).getBranchId())) {
                        sameBranch = false;
                        break;
                    }
                }
                if (!sameBranch) {
                    btRegion.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                    return;
                } else {
                    btRegion.setVisibility(View.GONE);
                    btBranch.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                    branch = initBranch = dataBean.getPosition().get(0).getBranchId();
                    return;
                }
            }

            if (isLeaderDepart) {
                for (int i = 0; i < size; i++) {
                    String origin = dataBean.getPosition().get(0).getRegionId();
                    if (!origin.equals(dataBean.getPosition().get(i).getRegionId())) {
                        sameRegion = false;
                        break;
                    }
                }
                if (!sameRegion) {
                    return;
                } else {
                    btRegion.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                }

                String originBranch = dataBean.getPosition().get(0).getBranchId();
                for (int i = 0; i < size; i++) {
                    if (!originBranch.equals(dataBean.getPosition().get(i).getBranchId())) {
                        sameBranch = false;
                        break;
                    }
                }
                if (!sameBranch) {
                    btRegion.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                    return;
                } else {
                    btRegion.setVisibility(View.GONE);
                    btBranch.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                    branch = initBranch = dataBean.getPosition().get(0).getBranchId();
                }

                for (int i = 0; i < size; i++) {
                    String origin = dataBean.getPosition().get(0).getDepartmentId();
                    if (!origin.equals(dataBean.getPosition().get(i).getDepartmentId())) {
                        sameDepartment = false;
                        break;
                    }
                }
                if (!sameDepartment) {
                    btBranch.setVisibility(View.GONE);
                    btRegion.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                    branch = initBranch = dataBean.getPosition().get(0).getBranchId();
                } else {
                    btBranch.setVisibility(View.GONE);
                    btRegion.setVisibility(View.GONE);
                    btDepartment.setVisibility(View.GONE);
                    region = initRegion = dataBean.getPosition().get(0).getRegionId();
                    branch = initBranch = dataBean.getPosition().get(0).getBranchId();
                    department = initDepartment = dataBean.getPosition().get(0).getDepartmentId();
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private void creatList() {
        for (int i = 1; i <= 12; i++) {
            months.add("Tháng " + i);
        }

        Calendar now = Calendar.getInstance();
        currentYear = now.get(Calendar.YEAR);
        currentMonth = now.get(Calendar.MONTH) + 1;
        tvYear.setText("Năm " + currentYear);
        tvMonth.setText("Tháng " + currentMonth);
        month = String.valueOf(currentMonth);
        year = String.valueOf(currentYear);
        for (int i = currentYear; i > currentYear - 10; i--) {
            years.add("Năm " + i);
        }
        Collections.reverse(years);

        phs.add("Tiền gửi thanh toán (DD)");
        phs.add("Tiền gửi có kỳ hạn (FD)");
        phs.add("Tiền vay (LN)");

        tvPh.setText("Tiền gửi thanh toán (DD)");
        ph = "DD";
        if (isEmployee) {
            tvUser.setText(token.getFullName());
            user = token.getUserName();
        } else {
            tvUser.setText("Chưa chọn");
            user = "";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setupUI() {
        btViewReport.setOnClickListener(v -> {
            showLoading();
            btViewReport.setEnabled(false);
            new Handler().postDelayed(() -> btViewReport.setEnabled(true), 500);
            if (!isEmployee) {
                if (TextUtils.isEmpty(region)) {
                    Toast.makeText(this, "Vui lòng chọn vùng miền!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(branch)) {
                    Toast.makeText(this, "Vui lòng chọn chi nhánh!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(department)) {
                    Toast.makeText(this, "Vui lòng chọn phòng ban!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(this, "Vui lòng chọn user!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            APIWebservices apiInterface = NetworkUtil.getCBclient(ReportViewActivity.this).create(APIWebservices.class);
            HashMap<String, String> options = new HashMap<>();
            options.put("username", user);
            options.put("year", year);
            options.put("month", month);
            options.put("accountType", ph);
            options.put("isBeneficiariesDifference", String.valueOf(isBeneficiariesDifference));
            Call<Datajson> call = apiInterface.getReportFile(HawkHelper.getInstance(ReportViewActivity.this).getToken(), options);
            call.enqueue(new Callback<Datajson>() {
                @Override
                public void onResponse(Call<Datajson> call, Response<Datajson> response) {
                    hideLoading();
                    if (response.code() == 200) {
                        if (response.body() != null && response.body().getData() != null) {
                            Intent i = new Intent(ReportViewActivity.this, ReportDetailActivity.class);
                            i.putExtra(Constants.REPORT_ID, response.body().getData());
                            startActivity(i);
                        } else {
                            Toast.makeText(ReportViewActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                        }
                    } else try {
                        JSONArray jsonArray = new JSONArray(response.errorBody().string());
                        JSONObject json_object = (JSONObject) jsonArray.get(0);
                        String mess = json_object.getString("errorMessage");
                        Toast.makeText(ReportViewActivity.this, mess, Toast.LENGTH_SHORT).show();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Datajson> call, Throwable t) {

                }
            });


        });

        btnBack.setOnClickListener(v -> finish());
        btMonth.setOnClickListener(v -> {
            type = Constants.Month;
            AppUtil.getDialogwithID(ReportViewActivity.this, months,
                    getString(R.string.months), ReportViewActivity.this, months.size() - 1).show();
        });

        btYear.setOnClickListener(v -> {
            type = Constants.YEAR;
            AppUtil.getDialogwithID(ReportViewActivity.this, years,
                    getString(R.string.years), ReportViewActivity.this, years.size() - 1).show();
        });

        btPh.setOnClickListener(v -> {
            type = Constants.PHS;
            AppUtil.getDialogwithID(ReportViewActivity.this, phs,
                    getString(R.string.phs), ReportViewActivity.this, phs.size() - 1).show();
        });

        btRegion.setOnClickListener(v -> {
            type = Constants.REGION_REPORT;
            Intent i = new Intent(ReportViewActivity.this, OptionActivity.class);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.REGION_REPORT);
            i.putExtra(Constants.CATEGORY_VALUE, -1);
            startActivity(i);
            btRegion.setEnabled(false);
            new Handler().postDelayed(() -> btRegion.setEnabled(true), 1000);
        });

        btBranch.setOnClickListener(v -> {
            if (TextUtils.isEmpty(region)) {
                Toast.makeText(this, "Vui lòng chọn vùng!", Toast.LENGTH_SHORT).show();
                return;
            }
            type = Constants.BRANCH;
            btBranch.setEnabled(false);
            new Handler().postDelayed(() -> btBranch.setEnabled(true), 1000);
            Intent i = new Intent(ReportViewActivity.this, OptionActivity.class);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.BRANCH_REPORT);
            i.putExtra(Constants.REPORT_ID, region);
            i.putExtra(Constants.CATEGORY_VALUE, -1);
            startActivity(i);
        });

        btDepartment.setOnClickListener(v -> {
            if (TextUtils.isEmpty(branch)) {
                Toast.makeText(this, "Vui lòng chọn chi nhánh!", Toast.LENGTH_SHORT).show();
                return;
            }
            type = Constants.DEPARTMENT;
            btDepartment.setEnabled(false);
            new Handler().postDelayed(() -> btDepartment.setEnabled(true), 1000);
            Intent i = new Intent(ReportViewActivity.this, OptionActivity.class);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.DEPARTMENT_REPORT);
            i.putExtra(Constants.REPORT_ID, branch);
            i.putExtra(Constants.CATEGORY_VALUE, -1);
            startActivity(i);
        });

        btUser.setOnClickListener(v -> {
            if (TextUtils.isEmpty(department)) {
                Toast.makeText(this, "Vui lòng chọn phòng ban!", Toast.LENGTH_SHORT).show();
                return;
            }
            type = Constants.EMPLOYEE;
            btUser.setEnabled(false);
            new Handler().postDelayed(() -> btUser.setEnabled(true), 1000);
            Intent i = new Intent(ReportViewActivity.this, OptionActivity.class);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.USER_REPORT);
            i.putExtra(Constants.REPORT_ID, department);
            i.putExtra(Constants.CATEGORY_VALUE, -1);
            startActivity(i);
        });
        tvRefresh.setOnClickListener(v -> {
            tvBranch.setText("Chưa chọn");
            tvDepartment.setText("Chưa chọn");
            tvRegion.setText("Chưa chọn");
            tvYear.setText("Năm " + currentYear);
            tvPh.setText("Tiền gửi thanh toán (DD)");
            tvMonth.setText("Tháng " + currentMonth);
            month = String.valueOf(currentMonth);
            year = String.valueOf(currentYear);
            region = initRegion;
            branch = initBranch;
            department = initDepartment;
            if (isEmployee) {
                tvUser.setText(token.getFullName());
                user = token.getUserName();
            } else {
                tvUser.setText("Chưa chọn");
                user = "";

            }
            ph = "DD";
        });
    }


    @Override
    public void onItemClick(int position) {
        switch (type) {
            case Constants.Month:
                month = String.valueOf(position + 1);
                tvMonth.setText(months.get(position));
                break;
            case Constants.YEAR:
                year = String.valueOf(currentYear - 9 + position);
                tvYear.setText(years.get(position));
                break;
            case Constants.PHS:
                switch (position) {
                    case 0:
                        ph = "DD";
                        break;
                    case 1:
                        ph = "FD";
                        break;
                    case 2:
                        ph = "LN";
                        break;
                }
                tvPh.setText(phs.get(position));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        switch (categoryEvent.category) {
            case Constants.REGION_REPORT:
                tvRegion.setText(categoryEvent.value);
                if (!categoryEvent.categoryId.equals(region)) {
                    branch = "";
                    department = "";
                    tvBranch.setText("Chưa chọn");
                    tvDepartment.setText("Chưa chọn");
                    if (isEmployee) {
                        tvUser.setText(token.getFullName());
                        user = token.getUserName();
                    } else {
                        tvUser.setText("Chưa chọn");
                        user = "";
                    }
                } else {

                }
                region = categoryEvent.categoryId;

                break;
            case Constants.BRANCH_REPORT:
                tvBranch.setText(categoryEvent.value);
                if (categoryEvent.categoryId.equals(branch)) {

                } else {
                    department = "";
                    tvDepartment.setText("Chưa chọn");
                    tvUser.setText("chưa chọn");
                    user = "";
                }
                branch = categoryEvent.categoryId;

                break;
            case Constants.DEPARTMENT_REPORT:
                tvDepartment.setText(categoryEvent.value);
                if (categoryEvent.categoryId.equals(department)) {

                } else {
                    tvUser.setText("Chưa chọn");
                    user = "";
                }
                department = categoryEvent.categoryId;
                break;
            case Constants.USER_REPORT:
                tvUser.setText(categoryEvent.value);
                user = categoryEvent.categoryId;
                break;

        }
    }

}
