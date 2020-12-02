package com.saleskit.cbbank.features.appointment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.OndeleteEvent;
import com.saleskit.cbbank.features.account.Appointment;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.CustomerListActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.rx.netmodel.Customer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class AppointListActivity extends BaseActivity implements AppointView, OnItemClicklistener, OndeleteEvent {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    ClearableEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    //    @BindView(R.id.appbar_main)
//    AppBarLayout appbarMain;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.fl_add)
    FloatingActionButton flAdd;
    @BindView(R.id.rv_all_list)
    RecyclerView rvAllList;
    @BindView(R.id.tv_recent)
    TextView tvRecent;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    @BindView(R.id.tv_result)
    TextView tvResult;
    int page = 1;
    int totalPages;
    @BindView(R.id.iv_filter)
    ImageView ivFilter;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_date)
    RelativeLayout rlDate;
    @BindView(R.id.tv_all_appoint)
    TextView tvAllAppoint;
    @BindView(R.id.tv_week_month)
    TextView tvWeekMonth;
    private List<Appointment.DataBean> originList = new ArrayList<>();
    private AppointmentAdapter appointmentAdapter;
    private String token;
    private Customer.DataBean customer;
    private String userName;
    private String customerId;
    private String type;
    private boolean isFromTun = false;
    private boolean onlySee = false;
    private String startDateShow, endDateShow;
    @Inject
    AddAppointPresenter addAppointPresenter;
    private String startDate;
    private String endDate;
    private int status = -1;
    private boolean enableFilter = false;
    private String afterDate;
    private String beforeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_list);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        showLoading();
        Intent i = getIntent();
        customer = (Customer.DataBean) i.getSerializableExtra(Constants.CUSTOMER_ID);
        type = i.getStringExtra(Constants.APPOINT_TYPE);
        token = HawkHelper.getInstance(this).getToken();

        switch (type) {
            case Constants.PERSONAL_TYPE:
                tvNumber.setVisibility(View.GONE);
                ivFilter.setVisibility(View.GONE);
                tvRecent.setVisibility(View.GONE);
                onlySee = true;
                try {
                    userName = i.getStringExtra(Constants.USER_NAME);
                } catch (Exception e) {
                    userName = HawkHelper.getInstance(this).getTokenModel().getUserName();
                }
                customerId = String.valueOf(customer.getCustomerId());
                flAdd.hide();
                break;
            case Constants.HOME_TYPE:
                tvNumber.setVisibility(View.GONE);
                ivFilter.setVisibility(View.GONE);
                flAdd.show();
                userName = HawkHelper.getInstance(this).getTokenModel().getUserName();
                break;
            case Constants.TUN_TYPE:
                setupTime();
                tvNumber.setVisibility(View.VISIBLE);
                ivFilter.setVisibility(View.VISIBLE);
                rlDate.setVisibility(View.VISIBLE);
                tvAllAppoint.setVisibility(View.GONE);
                onlySee = true;
                isFromTun = true;
                tvRecent.setVisibility(View.GONE);
                userName = i.getStringExtra(Constants.USER_ID);
                flAdd.hide();
                break;
        }
        ivBack.setOnClickListener(v -> {
            finish();
        });
        setupSw();
        setupUI();

//        appointmentModels.add(new AppointmentModel("Tương lai"));
//        appointmentModels.add(new AppointmentModel("Hôm nay"));
//        appointmentModels.add(new AppointmentModel("Hôm qua"));
//        appointmentModels.add(new AppointmentModel("Lịch sử"));
//
//        appointmentAdapter = new AppointmentAdapter(appointmentModels, this);
//        rvMain.setAdapter(appointmentAdapter);

        flAdd.setOnClickListener(v -> {
            if (customer != null) {
                Intent intent = new Intent(this, AddApointmentActivity.class);
                intent.putExtra(Constants.CUSTOMER_ID, customer);
                intent.putExtra(Constants.SEE_DETAIL, "");
                startActivity(intent);
            } else {
                Intent intent = new Intent(AppointListActivity.this, CustomerListActivity.class);
                intent.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_APPOINT);
                startActivity(intent);
            }
        });
        ivSearch.setOnClickListener(v -> {
            tvResult.setVisibility(View.GONE);
            page = 1;
            String key = etSearch.getText().toString();
            switch (type) {
                case Constants.PERSONAL_TYPE:
                    tvRecent.setVisibility(View.GONE);
                    rvMain.setVisibility(View.GONE);
//                    addAppointPresenter.getNearestAppointByCustomerId(this, token, userName);
                    addAppointPresenter.getAppointByCustomerId(this, token, page, etSearch.getText().toString(), userName, customerId);
                    break;
                case Constants.HOME_TYPE:
//                    addAppointPresenter.getNearestAppoint(this, token, userName);
                    tvRecent.setVisibility(View.GONE);
                    rvMain.setVisibility(View.GONE);
                    addAppointPresenter.getAllAppoint(this, token, page, etSearch.getText().toString(), userName);
                    break;
                case Constants.TUN_TYPE:
                    if (!enableFilter) {
                        {
                            addAppointPresenter.getFilterAppoint(this, token, startDate, endDate, status, page,
                                    etSearch.getText().toString(), userName);
                        }
                    } else {
                    }
                    break;
            }

        });
        ivFilter.setOnClickListener(v -> {
            Intent intent = new Intent(AppointListActivity.this, FilterAppointActivity.class);
            startActivity(intent);
        });

    }

    @SuppressLint("SetTextI18n")
    private void setupTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        Date currentDateFormat = Calendar.getInstance().getTime();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
//        Date inputDate = new Date(
//                Integer.parseInt(inputDateString.substring(0, 4)) - 1900,
//                Integer.parseInt(inputDateString.substring(5, 7)) - 1,
//                Integer.parseInt(inputDateString.substring(8, 10))
//        );
        Date firstDate =  cal.getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDateFormat);

        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            c.add(Calendar.DATE, 1);
        }

        Date outputEndDateOfWeek = c.getTime();


        @SuppressLint("SimpleDateFormat") SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T00:00:00.000Z' ");
        endDate = spf.format(outputEndDateOfWeek);
        startDate = spf.format(cal.getTime());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
        startDateShow = dateFormatView.format(cal.getTime());
        endDateShow = dateFormatView.format(outputEndDateOfWeek);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDateFormat);
        int weekYear = cal.get(Calendar.WEEK_OF_MONTH);
        tvWeekMonth.setText("Tuần " + weekYear + " - thg " + Integer.parseInt(startDate.substring(5, 7)));
        //test by qk
//        getFirstAndLastDayOfWeek(endDate);
    }

    @SuppressLint("SetTextI18n")
    private void getFirstAndLastDayOfWeek(String inputDateString) {

        Date inputDate = new Date(
                Integer.parseInt(inputDateString.substring(0, 4)) - 1900,
                Integer.parseInt(inputDateString.substring(5, 7)) - 1,
                Integer.parseInt(inputDateString.substring(8, 10))
        );

        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);

        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            c.add(Calendar.DATE, -1);
        }

        Date outputStartDateOfWeek = c.getTime();

        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            c.add(Calendar.DATE, 1);
        }

        Date outputEndDateOfWeek = c.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
        startDateShow = dateFormatView.format(outputStartDateOfWeek.getTime());
        endDateShow = dateFormatView.format(outputEndDateOfWeek.getTime());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T00:00:00.000Z' ");
        endDate = spf.format(outputEndDateOfWeek.getTime());
        startDate = spf.format(outputStartDateOfWeek.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(outputStartDateOfWeek);
        int weekYear = calendar.get(Calendar.WEEK_OF_MONTH);
        tvWeekMonth.setText("Tuần " + weekYear + " - thg " + Integer.parseInt(startDate.substring(5, 7)));
    }

    private void setupUI() {
        page = 1;
//        rvMain.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                int topRowVerticalPosition = recyclerView.getChildCount() == 0 ? 0 : recyclerView.getChildAt(0).getTop();
//                swAll.setEnabled(topRowVerticalPosition >= 0);
//            }
//        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMain.setLayoutManager(linearLayoutManager);
        rvAllList.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvAllList.setLayoutManager(linearLayoutManager1);


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvResult.setVisibility(View.GONE);
                if (s.toString().isEmpty()) {
                    page = 1;
                    switch (type) {
                        case Constants.PERSONAL_TYPE:
//                            tvRecent.setVisibility(View.VISIBLE);
//                            rvMain.setVisibility(View.VISIBLE);
//                            addAppointPresenter.getNearestAppointByCustomerId(AppointListActivity.this, token, userName, customerId);
                            addAppointPresenter.getAppointByCustomerId(AppointListActivity.this, token, page, etSearch.getText().toString(),
                                    userName, customerId);
                            break;
                        case Constants.HOME_TYPE:
                            tvRecent.setVisibility(View.VISIBLE);
                            rvMain.setVisibility(View.VISIBLE);
                            addAppointPresenter.getNearestAppoint(AppointListActivity.this, token, userName);
                            addAppointPresenter.getAllAppoint(AppointListActivity.this, token, page, etSearch.getText().toString(), userName);
                            break;
                        case Constants.TUN_TYPE:
                            if (!enableFilter) {
                                {
                                    addAppointPresenter.getFilterAppoint(AppointListActivity.this, token, startDate, endDate, status, page,
                                            etSearch.getText().toString(), userName);
                                }
                            } else {
                            }
                            break;
                    }
                }
            }
        });
    }

    private void setupSw() {
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            page = 1;
            switch (type) {
                case Constants.PERSONAL_TYPE:
//                    addAppointPresenter.getNearestAppointByCustomerId(this, token, userName, customerId);
                    addAppointPresenter.getAppointByCustomerId(this, token, page, etSearch.getText().toString(), userName, customerId);
                    break;
                case Constants.HOME_TYPE:
                    addAppointPresenter.getNearestAppoint(this, token, userName);
                    addAppointPresenter.getAllAppoint(this, token, page, etSearch.getText().toString(), userName);
                    break;
                case Constants.TUN_TYPE:
                    if (!enableFilter) {
                        {
                            addAppointPresenter.getFilterAppoint(this, token, startDate, endDate, status, page,
                                    etSearch.getText().toString(), userName);
                        }
                    } else {
                    }
                    break;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = 1;
        switch (type) {
            case Constants.PERSONAL_TYPE:
//                addAppointPresenter.getNearestAppointByCustomerId(this, token, userName, customerId);
                addAppointPresenter.getAppointByCustomerId(this, token, page, etSearch.getText().toString(), userName, customerId);
                break;
            case Constants.HOME_TYPE:
                addAppointPresenter.getNearestAppoint(this, token, userName);
                addAppointPresenter.getAllAppoint(this, token, page, etSearch.getText().toString(), userName);
                break;
            case Constants.TUN_TYPE:
                if (!enableFilter) {
                    {
                        addAppointPresenter.getFilterAppoint(this, token, startDate, endDate, status, page,
                                etSearch.getText().toString(), userName);
                    }
                } else {
                }
                break;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_appoint_list;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        addAppointPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        addAppointPresenter.detachView();
    }

    @Override
    public void showResult(Imgage.DataBean image) {

    }

    @Override
    public void showNearestAppoint(List<Appointment.DataBean> list) {
        AppointmentAdapter appointmentAdapterAll = new AppointmentAdapter(AppointListActivity.this, list, AppointListActivity.this,
                AppointListActivity.this, rvMain, onlySee);
        appointmentAdapterAll.setHasStableIds(true);
        rvMain.setAdapter(appointmentAdapterAll);
    }

    @Override
    public void showALlAppoint(List<Appointment.DataBean> list, int totalpages) {
        this.totalPages = totalpages;
        if (page == 1) {
            originList.clear();
        }

        originList.addAll(list);
        if (originList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        if (appointmentAdapter == null) {
            appointmentAdapter = new AppointmentAdapter(AppointListActivity.this, originList, AppointListActivity.this,
                    AppointListActivity.this, rvAllList, onlySee);
            appointmentAdapter.setHasStableIds(true);
            rvAllList.setAdapter(appointmentAdapter);
        } else {
            appointmentAdapter.notifyDataSetChanged();
        }

        appointmentAdapter.setLoaded();
        page++;
        appointmentAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            addAppointPresenter.getAllAppoint(this, token, page, etSearch.getText().toString(), userName);
        });
    }

    @Override
    public void showListSearchedAppoint(List<Appointment.DataBean> list) {
        tvRecent.setVisibility(View.GONE);
        rvMain.setVisibility(View.GONE);
        appointmentAdapter = new AppointmentAdapter(AppointListActivity.this, list, AppointListActivity.this,
                AppointListActivity.this, rvAllList, onlySee);
        appointmentAdapter.setHasStableIds(true);
        rvAllList.setAdapter(appointmentAdapter);

    }

    @Override
    public void hideSwipe() {
        swAll.setRefreshing(false);
    }

    @Override
    public void clearList() {

    }

    @Override
    public void setLoaded() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showFilterAppoint(List<FilterAppointment.DataBean.AppointmentBelongPeriodsBean> list,
                                  int pages, FilterAppointment.DataBean dataBean, int totalItems) {
        this.totalPages = pages;
        if (page == 1) {
            originList.clear();
        }
        if (list.size() == 0) {
            tvWeekMonth.setVisibility(View.GONE);
        } else {
            tvWeekMonth.setVisibility(View.VISIBLE);
        }
        if (dataBean.getAppointmentAfterPeriod() != null) {
            ivRight.setVisibility(View.VISIBLE);
            afterDate = dataBean.getAppointmentAfterPeriod().getAppointmentTime();

        } else {
            ivRight.setVisibility(View.GONE);
        }
        if (dataBean.getAppointmentBeforePeriod() != null) {
            ivLeft.setVisibility(View.VISIBLE);
            beforeDate = dataBean.getAppointmentBeforePeriod().getAppointmentTime();
            ivLeft.setOnClickListener(v -> {

            });
        } else {
            ivLeft.setVisibility(View.GONE);
        }
        ivRight.setOnClickListener(v -> {
            getFirstAndLastDayOfWeek(afterDate);
            page = 1;
            addAppointPresenter.getFilterAppoint(AppointListActivity.this, token, startDate, endDate, status, page,
                    etSearch.getText().toString(), userName);
        });

        ivLeft.setOnClickListener(v -> {
            getFirstAndLastDayOfWeek(beforeDate);
            page = 1;
            addAppointPresenter.getFilterAppoint(AppointListActivity.this, token, startDate, endDate, status, page,
                    etSearch.getText().toString(), userName);
        });
        for (FilterAppointment.DataBean.AppointmentBelongPeriodsBean periodsBean : list) {
            originList.add(new Appointment.DataBean(periodsBean.getAppointmentId(), periodsBean.getCustomerName(),
                    periodsBean.getIdentityNumber(), periodsBean.getPhoneNumber(), periodsBean.getEmail(),
                    periodsBean.getAppointmentAddress(), periodsBean.getExpectedProduct(), periodsBean.getDescription(),
                    periodsBean.getAppointmentTime(), periodsBean.getResultStatus(), periodsBean.getResultStatusString(),
                    periodsBean.getResultDescription()));
        }
        if (originList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        tvNumber.setText(Html.fromHtml(("Tổng số " + ("<font color=#f25559>" + totalItems + "</font> ") + "cuộc hẹn (" + startDateShow + " - " + endDateShow + ")")));
        if (appointmentAdapter == null) {
            appointmentAdapter = new AppointmentAdapter(AppointListActivity.this, originList, AppointListActivity.this,
                    AppointListActivity.this, rvAllList, onlySee);
            appointmentAdapter.setHasStableIds(true);
            rvAllList.setAdapter(appointmentAdapter);
        } else {
            appointmentAdapter.notifyDataSetChanged();
        }

        appointmentAdapter.setLoaded();
        page++;
        appointmentAdapter.setOnLoadMoreListener(() -> {
            if (page > totalItems && totalItems > 0) return;
            addAppointPresenter.getFilterAppoint(this, token, startDate, endDate, status,
                    page, etSearch.getText().toString(), userName);
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Subscribe()
    public void onFilterReceive(FilterEvent filterEvent) {
        rlDate.setVisibility(View.GONE);
//        tvWeekMonth.setVisibility(View.GONE);
        page = 1;
        if (!TextUtils.isEmpty(filterEvent.startDate) && !TextUtils.isEmpty(filterEvent.endDate)) {
            startDate = filterEvent.startDate + "T" + "00:00:00.000" + "Z";
            endDate = filterEvent.endDate + "T" + "00:00:00.000" + "Z";
            startDateShow = AppUtil.formatServerTime(filterEvent.startDate);
            endDateShow = AppUtil.formatServerTime(filterEvent.endDate);
        }
        status = filterEvent.resultStatus;
        if (status == 4) {
            status = -1;
        }

//        Date inputDate = new Date(
//                Integer.parseInt(startDate.substring(0, 4)) - 1900,
//                Integer.parseInt(startDate.substring(5, 7)) - 1,
//                Integer.parseInt(startDate.substring(8, 10))
//        );
//        Calendar c = Calendar.getInstance();
//        c.setTime(inputDate);
//        int weekYear = c.get(Calendar.WEEK_OF_MONTH) - 1;
//        tvWeekMonth.setText("Tuần " + weekYear + " - thg " + Integer.parseInt(startDate.substring(5, 7)));
//        addAppointPresenter.getFilterAppoint(this, token, startDate, endDate, status,
//                page, etSearch.getText().toString(), userName);
    }

    @Override
    public void onDelete() {
        if (type.equals(Constants.HOME_TYPE)) {
            showLoading();
            page = 1;
            addAppointPresenter.getNearestAppoint(this, token, userName);
            addAppointPresenter.getAllAppoint(this, token, page, etSearch.getText().toString(), userName);
        }
    }

    @Override
    public void onDeletePosition(int position) {

    }
}
