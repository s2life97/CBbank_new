package com.saleskit.cbbank.features.kpi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.KpiEmplyee;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.home.fragment.SlideMenuFragment;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.TunCircularProgressIndicator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KpiActivity extends BaseActivity implements OnFilterClick, DetailKpiView {
    @BindView(R.id.pb_media_progress_quater)
    ProgressBar pbMediaProgress;
    @BindView(R.id.rl_test)
    RelativeLayout rlTest;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_kpi_main)
    RelativeLayout rlKpiMain;
    @BindView(R.id.rv_percent)
    RecyclerView rvPercent;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_job_title)
    TextView tvJobTitle;
    @BindView(R.id.iv_filter)
    ImageView ivFilter;
    @BindView(R.id.circular_progress_own)
    TunCircularProgressIndicator circularProgressOwn;
    @BindView(R.id.tv_target_hd)
    TextView tvTargetHd;
    @BindView(R.id.tv_result_hd)
    TextView tvResultHd;
    @BindView(R.id.tv_target_own)
    TextView tvTargetOwn;
    @BindView(R.id.tv_result_own)
    TextView tvResultOwn;
    @BindView(R.id.tv_amout_ballance)
    TextView tvAmoutBallance;
    @BindView(R.id.tv_sum_amout)
    TextView tvSumAmout;
    @BindView(R.id.tv_quater_name)
    TextView tvQuaterName;
    @BindView(R.id.tv_kpi_quater)
    TextView tvKpiQuater;
    @BindView(R.id.tv_kpi_year)
    TextView tvKpiYear;
    @BindView(R.id.pb_media_progress_quater_year)
    ProgressBar pbMediaProgressQuaterYear;
    @BindView(R.id.tv_year_name)
    TextView tvYearName;
    @BindView(R.id.ll_main)
    LinearLayout llManin;
    private List<String> percentList = new ArrayList<>();
    private PercentAdapter percentAdapter;
    @Inject
    KpiPresent kpiPresent;
    @BindView(R.id.circular_progress_hd)
    TunCircularProgressIndicator circularProgressHd;
    @BindView(R.id.circular_progress_td)
    TunCircularProgressIndicator circularProgressTd;
    @BindView(R.id.sv_main)
    ScrollView scMain;
    @BindView(R.id.ll_kpi)
    LinearLayout llKpi;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    @BindView(R.id.showHuyDong)
    LinearLayout showHuyDong;
    @BindView(R.id.showTinDung)
    LinearLayout showTinDung;
    @BindView(R.id.showNoQuaHan)
    LinearLayout showNoQuaHan;


    private String type;
    //    @BindView(R.id.iv_filter)
//    ImageView ivFilter;
    SlideMenuFragment menuFragment;
    private CustomerAdapter customerAdapter;
    private List<CustomerModel> customerModels = new ArrayList<>();
    private KpiEmplyee.DataBean.ResultsBean employee;
    private int choosenYear, choosenMonth, choosenQuater;
    private List<DateFilter> months = new ArrayList<>();
    private List<DateFilter> years = new ArrayList<>();
    private List<DateFilter> quarters = new ArrayList<>();
    private int currentYear;
    private int currentMonth;
    private String token;
    private int currentQuarter;
    private String userName;
    private int postion = 3;
    private List<Double> percents = new ArrayList<>();
    private List<DateFilter> dateFilters = new ArrayList<>();
    private boolean renewModel;
    @Inject
    KpiPresenter kpiPresenter;
    //private MonthAdapter.FilterType filterType = MonthAdapter.FilterType.MONTH;
    private MonthAdapter.FilterType filterType = MonthAdapter.FilterType.QUARTER;
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpi);
        token = HawkHelper.getInstance(this).getToken();
        ButterKnife.bind(this);
        Intent i = getIntent();
        userName = i.getStringExtra(Constants.KPI_PERSON);
        initSlideMenu();
        setupUI();
        type = i.getStringExtra(Constants.SCREEN_TYPE);
        if (type.equals(Constants.HOME_TYPE)) {
            setupTime();
        } else {
            Calendar now = Calendar.getInstance();
            currentYear = now.get(Calendar.YEAR);
            currentMonth = now.get(Calendar.MONTH) + 1;
            currentQuarter = getQuarterByMonth(currentMonth);
            filterType = (MonthAdapter.FilterType) i.getSerializableExtra(Constants.FILTER_TYPE);
            choosenMonth = i.getIntExtra(Constants.CHOSEN_MONTH, -1);
            choosenQuater = i.getIntExtra(Constants.CHOSEN_QUARTER, -1);
            choosenYear = i.getIntExtra(Constants.CHOSEN_YEAR, -1);
            setupFilterModel(choosenMonth, choosenQuater, choosenYear);
            switch (filterType) {
                case YEAR:
                    dateFilters.clear();
                    dateFilters.addAll(years);
                    choosenYear = years.get(3).getYear();
                    choosenMonth = 12;
                    choosenQuater = years.get(3).getQuarter();
                    break;
                case QUARTER:
                    dateFilters.clear();
                    dateFilters.addAll(quarters);
                    choosenYear = quarters.get(3).getYear();
                    choosenMonth = quarters.get(3).getMonth();
                    choosenQuater = quarters.get(3).getQuarter();
                    break;
                case MONTH:
                    dateFilters.clear();
                    dateFilters.addAll(months);
                    choosenYear = months.get(3).getYear();
                    choosenMonth = months.get(3).getMonth();
                    choosenQuater = months.get(3).getQuarter();
                    break;
            }
            applyFilter(true, filterType);
        }


        setupPopupMenu();
    }

    private void setupTime() {
        Calendar now = Calendar.getInstance();
        choosenYear = currentYear = now.get(Calendar.YEAR);
        choosenMonth = currentMonth = now.get(Calendar.MONTH) + 1;
        choosenQuater = currentQuarter = getQuarterByMonth(currentMonth);
//        getDataByYear();
        setupFilterModel(currentMonth, currentQuarter, currentYear);
        //applyFilter(false, MonthAdapter.FilterType.MONTH);
        applyFilter(false, MonthAdapter.FilterType.QUARTER);
        dateFilters.clear();
        dateFilters.addAll(months);
    }


    private void setColorForProgressBar(ProgressBar progressBar, double percentKpi) {
        progressBar.setProgress((int) (percentKpi));
        if (percentKpi > 100) {
            AppUtil.setProgressDrawable(progressBar, this, R.drawable.progress_bg_green);
        } else if (percentKpi >= 30) {
            AppUtil.setProgressDrawable(progressBar, this, R.drawable.progress_bg_blueocean);
        } else if (percentKpi >= 15) {
            AppUtil.setProgressDrawable(progressBar, this, R.drawable.progress_bg_black);
        } else if (percentKpi > 0) {
            AppUtil.setProgressDrawable(progressBar, this, R.drawable.progress_bg_red);
        } else {
            AppUtil.setProgressDrawable(progressBar, this, R.drawable.progress_bg_red);
        }
    }

    private void applyFilter(boolean renewModel, MonthAdapter.FilterType filterType) {
        this.renewModel = renewModel;
        this.filterType = filterType;
        if (percentAdapter != null) percentAdapter.setFilterType(filterType);

        switch (filterType) {
            case YEAR:
                kpiPresenter.getDetailEmployeeByYear(renewModel, this, token, userName, choosenYear);
                break;
            case MONTH:
                kpiPresenter.getDetailEmployeeByMonth(renewModel, this, token, userName, choosenYear, choosenMonth);
                showHuyDong.setVisibility(View.GONE);
                showTinDung.setVisibility(View.GONE);
                showNoQuaHan.setVisibility(View.VISIBLE);
                break;
            case QUARTER:
                kpiPresenter.getDetailEmployeeByQuater(renewModel, this, token, userName, choosenYear, choosenQuater);
                showHuyDong.setVisibility(View.VISIBLE);
                showTinDung.setVisibility(View.VISIBLE);
                showNoQuaHan.setVisibility(View.GONE);
                break;
        }
    }

    private void setupFilterModel(int currentMonth, int currentQuarter, int currentYear) {
        months.clear();
        quarters.clear();
        years.clear();

        if (currentMonth < 4) {
            if (currentMonth == 3) {
                months.add(new DateFilter(currentYear - 1, 4, 12));
            } else if (currentMonth == 2) {
                months.add(new DateFilter(currentYear - 1, 4, 11));
                months.add(new DateFilter(currentYear - 1, 4, 12));
            } else if (currentMonth == 1) {
                months.add(new DateFilter(currentYear - 1, 4, 10));
                months.add(new DateFilter(currentYear - 1, 4, 11));
                months.add(new DateFilter(currentYear - 1, 4, 12));
            }
            for (int i = currentMonth; i > 0; i--) {
                months.add(new DateFilter(
                        currentYear,
                        getQuarterByMonth(currentMonth - i + 1),
                        currentMonth - i + 1));
            }
        } else {
            for (int i = 4; i > 0; i--) {
                months.add(new DateFilter(
                        currentYear,
                        getQuarterByMonth(currentMonth - i + 1),
                        currentMonth - i + 1));
            }
        }

        if (currentQuarter < 4) {
            if (currentQuarter == 3) {
                quarters.add(new DateFilter(currentYear - 1, 4, -1));
            } else if (currentQuarter == 2) {
                quarters.add(new DateFilter(currentYear - 1, 3, -1));
                quarters.add(new DateFilter(currentYear - 1, 4, -1));
            } else if (currentQuarter == 1) {
                quarters.add(new DateFilter(currentYear - 1, 2, -1));
                quarters.add(new DateFilter(currentYear - 1, 3, -1));
                quarters.add(new DateFilter(currentYear - 1, 4, -1));
            }
            for (int i = currentQuarter; i > 0; i--) {
                quarters.add(new DateFilter(currentYear, currentQuarter - i + 1, -1));
            }
        } else {
            for (int i = 4; i > 0; i--) {
                quarters.add(new DateFilter(currentYear, currentQuarter - i + 1, -1));
            }
        }

        for (int i = 4; i > 0; i--) {
            years.add(new DateFilter(currentYear - i + 1, -1, -1));
        }
    }

    private int getQuarterByMonth(int currentMonth) {
        switch (currentMonth) {
            case 1:
            case 2:
            case 3:
                return 1;
            case 4:
            case 5:
            case 6:
                return 2;
            case 7:
            case 8:
            case 9:
                return 3;
            case 10:
            case 11:
            case 12:
                return 4;
        }
        return -1;
    }

    private void initSlideMenu() {
        circularProgressHd.setFillColorWithOpacity(getResources().getColor(R.color.white_10));
        circularProgressOwn.setFillColorWithOpacity(getResources().getColor(R.color.white_10));
        circularProgressTd.setFillColorWithOpacity(getResources().getColor(R.color.white_10));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupUI() {

//        tvName.setText(employee.getFullName());
//        tvJobTitle.setText(employee.getJobTitleName());
//        tvTargetHd.setText(String.valueOf(NumberFormat.getNumberInstance().format(employee.getTargetFDFN())));
//        tvResultHd.setText(String.valueOf(NumberFormat.getNumberInstance().format(employee.getFDFN())));
//        tvResultOwn.setText(String.valueOf(NumberFormat.getNumberInstance().format(employee.getLN())));
//        tvTargetOwn.setText(String.valueOf(NumberFormat.getNumberInstance().format(employee.getTargetLN())));
//        tvAmoutBallance.setText(String.valueOf(NumberFormat.getNumberInstance().format(employee.getLoanOutstandingBalance())));
//        tvSumAmout.setText(String.valueOf(NumberFormat.getNumberInstance().format(employee.getOverdueDebt())));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rvPercent.setLayoutManager(mLayoutManager);
        ivBack.setOnClickListener(v -> {
            finish();
        });

        scMain.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                showLoading();
                if (filterType == MonthAdapter.FilterType.MONTH) {
                    List<DateFilter> newMonths = new ArrayList<>();

                    for (DateFilter dateFilter : months) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, dateFilter.getYear());
                        calendar.set(Calendar.MONTH, dateFilter.getMonth() - 1);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        calendar.add(Calendar.MONTH, -4);

                        DateFilter newDateFilter = new DateFilter(
                                calendar.get(Calendar.YEAR),
                                getQuarterByMonth(calendar.get(Calendar.MONTH) + 1),
                                calendar.get(Calendar.MONTH) + 1);
                        newMonths.add(newDateFilter);
                    }

                    months.clear();
                    months.addAll(newMonths);
                    dateFilters.clear();
                    dateFilters.addAll(months);
                    choosenMonth = months.get(3).getMonth();
                    choosenQuater = months.get(3).getQuarter();
                    choosenYear = months.get(3).getYear();
                    kpiPresenter.getDetailEmployeeByMonth(true, KpiActivity.this, token, userName, months.get(3).getYear(), months.get(3).getMonth());
                }

                if (filterType == MonthAdapter.FilterType.QUARTER) {
                    List<DateFilter> newQuarter = new ArrayList<>();

                    for (DateFilter dateFilter : quarters) {
                        DateFilter newDateFilter = new DateFilter(
                                dateFilter.getYear() - 1,
                                dateFilter.getQuarter(),
                                dateFilter.getMonth());
                        newQuarter.add(newDateFilter);
                    }

                    quarters.clear();
                    quarters.addAll(newQuarter);
                    choosenQuater = quarters.get(3).getQuarter();
                    choosenYear = quarters.get(3).getYear();
                    dateFilters.clear();
                    dateFilters.addAll(quarters);

                    kpiPresenter.getDetailEmployeeByQuater(true, KpiActivity.this, token, userName, quarters.get(3).getYear(), quarters.get(3).getQuarter());
                }

                if (filterType == MonthAdapter.FilterType.YEAR) {
                    List<DateFilter> newYears = new ArrayList<>();

                    for (DateFilter dateFilter : years) {
                        DateFilter newDateFilter = new DateFilter(
                                dateFilter.getYear() - 4,
                                dateFilter.getQuarter(),
                                dateFilter.getMonth());
                        newYears.add(newDateFilter);
                    }

                    years.clear();
                    years.addAll(newYears);
                    dateFilters.clear();
                    dateFilters.addAll(years);
                    choosenYear = years.get(3).getYear();
                    kpiPresenter.getDetailEmployeeByYear(true, KpiActivity.this, token, userName, years.get(3).getYear());
                }
//                Toast.makeText(KpiActivity.this, " " + choosenMonth + " year " + choosenYear, Toast.LENGTH_SHORT).show();
                percentAdapter.notifyDataSetChanged();
            }

            public void onSwipeLeft() {
                if ((filterType == MonthAdapter.FilterType.MONTH
                        && months.get(3).getMonth() == Calendar.getInstance().get(Calendar.MONTH) + 1
                        && months.get(3).getQuarter() == getQuarterByMonth(months.get(3).getMonth())
                        && months.get(3).getYear() == Calendar.getInstance().get(Calendar.YEAR))
                        || (filterType == MonthAdapter.FilterType.QUARTER
                        && quarters.get(3).getQuarter() == getQuarterByMonth(Calendar.getInstance().get(Calendar.MONTH))
                        && quarters.get(3).getYear() == Calendar.getInstance().get(Calendar.YEAR))
                        || (filterType == MonthAdapter.FilterType.YEAR
                        && years.get(3).getYear() == Calendar.getInstance().get(Calendar.YEAR)))
                    return;

                showLoading();
                if (filterType == MonthAdapter.FilterType.MONTH) {
                    List<DateFilter> newMonths = new ArrayList<>();

                    for (DateFilter dateFilter : months) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, dateFilter.getYear());
                        calendar.set(Calendar.MONTH, dateFilter.getMonth() - 1);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        calendar.add(Calendar.MONTH, 4);

                        if (calendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis())
                            break;

                        DateFilter newDateFilter = new DateFilter(
                                calendar.get(Calendar.YEAR),
                                getQuarterByMonth(calendar.get(Calendar.MONTH) + 1),
                                calendar.get(Calendar.MONTH) + 1);
                        newMonths.add(newDateFilter);
                    }

                    if (newMonths.size() < 4) {
                        newMonths.addAll(0, months.subList(newMonths.size(), 4));
                    }
                    months.clear();
                    months.addAll(newMonths);
                    dateFilters.clear();
                    dateFilters.addAll(months);
                    kpiPresenter.getDetailEmployeeByMonth(true, KpiActivity.this, token, userName, months.get(3).getYear(), months.get(3).getMonth());

                    choosenMonth = months.get(3).getMonth();
                    choosenQuater = months.get(3).getQuarter();
                    choosenYear = months.get(3).getYear();
                }

                if (filterType == MonthAdapter.FilterType.QUARTER) {
                    List<DateFilter> newQuarter = new ArrayList<>();

                    for (DateFilter dateFilter : quarters) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, dateFilter.getYear());
                        calendar.set(Calendar.MONTH, dateFilter.getQuarter() * 3 - 2);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        calendar.add(Calendar.YEAR, 1);

                        if (calendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis())
                            break;

                        DateFilter newDateFilter = new DateFilter(
                                dateFilter.getYear() + 1,
                                dateFilter.getQuarter(),
                                dateFilter.getMonth());
                        newQuarter.add(newDateFilter);
                    }

                    if (newQuarter.size() < 4) {
                        newQuarter.addAll(0, quarters.subList(newQuarter.size(), 4));
                    }
                    quarters.clear();
                    quarters.addAll(newQuarter);
                    dateFilters.clear();
                    dateFilters.addAll(quarters);
                    kpiPresenter.getDetailEmployeeByQuater(true, KpiActivity.this, token, userName, quarters.get(3).getYear(), quarters.get(3).getQuarter());

                    choosenQuater = months.get(3).getQuarter();
                    choosenYear = months.get(3).getYear();
                }

                if (filterType == MonthAdapter.FilterType.YEAR) {
                    List<DateFilter> newYears = new ArrayList<>();

                    for (DateFilter dateFilter : years) {

                        if (dateFilter.getYear() + 4 > currentYear) break;

                        DateFilter newDateFilter = new DateFilter(
                                dateFilter.getYear() + 4,
                                dateFilter.getQuarter(),
                                dateFilter.getMonth());
                        newYears.add(newDateFilter);
                    }

                    if (newYears.size() < 4) {
                        newYears.addAll(0, years.subList(newYears.size(), 4));
                    }
                    years.clear();
                    years.addAll(newYears);
                    dateFilters.clear();
                    dateFilters.addAll(years);
                    kpiPresenter.getDetailEmployeeByYear(true, KpiActivity.this, token, userName, years.get(3).getYear());

                    choosenYear = months.get(3).getYear();
                }

                percentAdapter.notifyDataSetChanged();
            }

            public void onSwipeBottom() {
            }

        });

    }

    private void setupPopupMenu() {
        ivFilter.setOnClickListener(v -> {

            //filter by year before
            if (filterType == MonthAdapter.FilterType.YEAR) {
                if (choosenYear == currentYear) {
                    setupFilterModel(currentMonth, currentQuarter, choosenYear);
                } else {
                    setupFilterModel(12, 4, choosenYear);
                }
            }

            //filter by quarter before
            if (filterType == MonthAdapter.FilterType.QUARTER) {
                //get the last month of chosenQuarter
                choosenMonth = choosenQuater * 3;
                setupFilterModel(choosenMonth, choosenQuater, choosenYear);
            }

            //filter by month before
            if (filterType == MonthAdapter.FilterType.MONTH) {
                setupFilterModel(choosenMonth, getQuarterByMonth(choosenMonth), choosenYear);
            }

            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.getMenu().findItem(R.id.menu_filter).setTitle(Html.fromHtml("<font color='#145aa2'>Bộ lọc</font>"));
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_moth:
                        filterType = MonthAdapter.FilterType.MONTH;
                        applyFilter(true, MonthAdapter.FilterType.MONTH);
                        dateFilters.clear();
                        dateFilters.addAll(months);
                        choosenYear = dateFilters.get(3).getYear();
                        choosenQuater = dateFilters.get(3).getQuarter();
                        choosenMonth = dateFilters.get(3).getMonth();
                        popupMenu.dismiss();
                        llKpi.setVisibility(View.VISIBLE);
                        checkItemCheked(item);
                        break;
                    /*case R.id.menu_year:
                        filterType = MonthAdapter.FilterType.YEAR;
                        llKpi.setVisibility(View.GONE);
                        applyFilter(true, MonthAdapter.FilterType.YEAR);
                        dateFilters.clear();
                        dateFilters.addAll(years);
                        choosenYear = dateFilters.get(3).getYear();
                        choosenQuater = 4;
                        choosenMonth = 12;
                        popupMenu.dismiss();
                        checkItemCheked(item);
                        break;*/
                    case R.id.menu_quater:
                        llKpi.setVisibility(View.GONE);
                        filterType = MonthAdapter.FilterType.QUARTER;
                        applyFilter(true, MonthAdapter.FilterType.QUARTER);
                        dateFilters.clear();
                        dateFilters.addAll(quarters);
                        choosenYear = dateFilters.get(3).getYear();
                        choosenQuater = dateFilters.get(3).getQuarter();
                        choosenMonth = choosenQuater * 3;
                        popupMenu.dismiss();
                        checkItemCheked(item);
                        break;

                }
                return false;
            });
            popupMenu.show();
        });
    }

    private void checkItemCheked(MenuItem item) {
        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_kpi;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        kpiPresenter.attachView(this);

    }

    @Override
    protected void detachPresenter() {
        kpiPresenter.detachView();
    }

    @OnClick(R.id.iv_filter)
    public void onViewClicked() {

    }

    @Override
    public void onFilterClick(int position, MonthAdapter.FilterType filterType) {
        this.postion = position;
        if (filterType == MonthAdapter.FilterType.YEAR) {
            tvYearName.setText("KPI cả năm " + years.get(position).getYear());
            choosenYear = years.get(position).getYear();
            kpiPresenter.getDetailEmployeeByYear(false, this, token, userName, choosenYear);
        } else if (filterType == MonthAdapter.FilterType.MONTH) {
            choosenMonth = months.get(position).getMonth();
            choosenQuater = months.get(position).getQuarter();
            choosenYear = months.get(position).getYear();
            tvQuaterName.setText("KPI quý " + getQuarterByMonth(choosenMonth));
            kpiPresenter.getDetailEmployeeByMonth(false, this, token, userName, choosenYear, choosenMonth);
        } else {
            tvQuaterName.setText("KPI quý " + quarters.get(position).getQuarter());
            choosenQuater = quarters.get(position).getQuarter();
            choosenYear = quarters.get(position).getYear();
            kpiPresenter.getDetailEmployeeByQuater(false, this, token, userName, choosenYear, choosenQuater);
        }
    }

    @Override
    public void showDetailEmployee(boolean rewnewModel, QuaterDetailEmployee.DataBean dataBean) {
        if (dataBean.getCurrent() != null) {
            llData.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);

            tvName.setText(dataBean.getCurrent().getFullName());
            tvJobTitle.setText(dataBean.getCurrent().getJobTitleName());
            tvTargetHd.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getTargetFDFN())));
            tvResultOwn.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getLN())));
            tvTargetOwn.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getTargetLN())));
            tvAmoutBallance.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getLoanOutstandingBalance())));
            tvSumAmout.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getOverdueDebt())));
            tvResultHd.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getFDFN())));
            tvKpiYear.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getPercentKpi())));


            circularProgressHd.setProgress(dataBean.getCurrent().getPercentFDFN(), 100);
            circularProgressTd.setProgress(dataBean.getCurrent().getPercentLN(), 100);
            circularProgressOwn.setProgress(dataBean.getCurrent().getPercentOverdueDebt(), 100);

            setupProgressBar(circularProgressHd, dataBean.getCurrent().getPercentFDFN());
            setupProgressBar(circularProgressTd, dataBean.getCurrent().getPercentLN());
            setupProgressBar(circularProgressOwn, dataBean.getCurrent().getPercentOverdueDebt());
        } else {
            llData.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }

        if (rewnewModel) {
            percents.clear();
            for (QuaterDetailEmployee.DataBean.ListPercentKpiQuarterBean listPercentKpiQuarterBean : dataBean.getListPercentKpiQuarter()) {
                percents.add(listPercentKpiQuarterBean.getPercentKpi());
            }
            Collections.reverse(percents);
            if (percentAdapter != null) {
                percentAdapter.selectedPos = 3;
                percentAdapter.notifyDataSetChanged();
            } else {
                percentAdapter = new PercentAdapter(percents, dateFilters, this, filterType, KpiActivity.this::onFilterClick);
                rvPercent.setAdapter(percentAdapter);
            }
            new Handler().postDelayed(() -> rvPercent.scrollToPosition(3), 200);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showMonthDetailEmployee(boolean rewnewModel, MonthDetailEmployee.DataBean dataBean) {
        if (dataBean.getCurrent() != null) {
            llData.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);

            tvName.setText(dataBean.getCurrent().getFullName());
            tvJobTitle.setText(dataBean.getCurrent().getJobTitleName());
            tvTargetHd.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getTargetFDFN())));
            tvResultOwn.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getLN())));
            tvTargetOwn.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getTargetLN())));
            tvAmoutBallance.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getLoanOutstandingBalance())));
            tvSumAmout.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getOverdueDebt())));
            tvResultHd.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getFDFN())));
            tvKpiYear.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getPercentKpi())));
            String quater = "";
            switch (getQuarterByMonth(choosenMonth)) {
                case 1:
                    quater = "I";
                    break;
                case 2:
                    quater = "II";
                    break;
                case 3:
                    quater = "III";
                    break;
                case 4:
                    quater = "IV";
                    break;
            }
            llKpi.setVisibility(View.VISIBLE);
            tvYearName.setText("KPI cả năm " + choosenYear);
            tvQuaterName.setText("KPI quý " + quater);
            tvKpiYear.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getPercentKpiYear())) + " %");
            setColorForProgressBar(pbMediaProgressQuaterYear, dataBean.getCurrent().getPercentKpiYear());

            tvKpiQuater.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getPercentKpiQuarter())) + " %");
            setColorForProgressBar(pbMediaProgress, dataBean.getCurrent().getPercentKpiQuarter());

            circularProgressHd.setProgress(dataBean.getCurrent().getPercentFDFN(), 100);
            circularProgressTd.setProgress(dataBean.getCurrent().getPercentLN(), 100);
            circularProgressOwn.setProgress(dataBean.getCurrent().getPercentOverdueDebt(), 100);

            setupProgressBar(circularProgressHd, dataBean.getCurrent().getPercentFDFN());
            setupProgressBar(circularProgressTd, dataBean.getCurrent().getPercentLN());
            setupProgressBar(circularProgressOwn, dataBean.getCurrent().getPercentOverdueDebt());
        } else {
            llData.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }

        if (firstTime) {
            percents.clear();
            for (MonthDetailEmployee.DataBean.ListPercentKpiMonthBean listPercentKpiMonthBean : dataBean.getListPercentKpiMonth()) {
                percents.add(listPercentKpiMonthBean.getPercentKpi());
            }
            Collections.reverse(percents);
            if (percentAdapter != null) {
                percentAdapter.selectedPos = 3;
                percentAdapter.notifyDataSetChanged();
            } else {
                percentAdapter = new PercentAdapter(percents, dateFilters, this, filterType, KpiActivity.this::onFilterClick);

                rvPercent.setAdapter(percentAdapter);
            }
            new Handler().postDelayed(() -> rvPercent.scrollToPosition(3), 200);
        }

        if (rewnewModel) {
            percents.clear();
            for (MonthDetailEmployee.DataBean.ListPercentKpiMonthBean listPercentKpiQuarterBean : dataBean.getListPercentKpiMonth()) {
                percents.add(listPercentKpiQuarterBean.getPercentKpi());
            }
            Collections.reverse(percents);
            percentAdapter.selectedPos = 3;
            percentAdapter.notifyDataSetChanged();
            new Handler().postDelayed(() -> rvPercent.scrollToPosition(3), 200);
        }

        firstTime = false;
    }

    @Override
    public void showDetailYearEmployee(boolean rewnewModel, YearDetailEmployee.DataBean dataBean) {
        if (dataBean.getCurrent() != null) {
            llData.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);

            tvName.setText(dataBean.getCurrent().getFullName());
            tvJobTitle.setText(dataBean.getCurrent().getJobTitleName());
            tvTargetHd.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getTargetFDFN())));
            tvResultOwn.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getLN())));
            tvTargetOwn.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getTargetLN())));
            tvAmoutBallance.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getLoanOutstandingBalance())));
            tvSumAmout.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getOverdueDebt())));
            tvResultHd.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getFDFN())));
            tvKpiYear.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getCurrent().getPercentKpi())));


            circularProgressHd.setProgress(dataBean.getCurrent().getPercentFDFN(), 100);
            circularProgressTd.setProgress(dataBean.getCurrent().getPercentLN(), 100);
            circularProgressOwn.setProgress(dataBean.getCurrent().getPercentOverdueDebt(), 100);

            setupProgressBar(circularProgressHd, dataBean.getCurrent().getPercentFDFN());
            setupProgressBar(circularProgressTd, dataBean.getCurrent().getPercentLN());
            setupProgressBar(circularProgressOwn, dataBean.getCurrent().getPercentOverdueDebt());
        } else {
            llData.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }

        if (rewnewModel) {
            percents.clear();
            for (YearDetailEmployee.DataBean.ListPercentKpiYearBean listPercentKpiQuarterBean : dataBean.getListPercentKpiYear()) {
                percents.add(listPercentKpiQuarterBean.getPercentKpi());
            }
            Collections.reverse(percents);
            if (percentAdapter != null) {
                percentAdapter.selectedPos = 3;
                percentAdapter.notifyDataSetChanged();
            } else {
                percentAdapter = new PercentAdapter(percents, dateFilters, this, filterType, KpiActivity.this::onFilterClick);
                rvPercent.setAdapter(percentAdapter);
            }
            new Handler().postDelayed(() -> rvPercent.scrollToPosition(3), 200);
        }
    }


    public void setupProgressBar(TunCircularProgressIndicator circularProgress, double percent) {
        circularProgress.setProgressTextAdapter(new PatternProgressTextAdapter());
        circularProgress.setProgress(percent, 100);

        if (percent > 100) {
            circularProgress.setFillBackgroundEnabled(false);
            circularProgress.setTextColor(getResources().getColor(R.color.black));
            circularProgress.setProgressColor(getResources().getColor(R.color.green));
            circularProgress.setProgressBackgroundColor(getResources().getColor(R.color.gray_process));
            circularProgress.setDotColor(getResources().getColor(R.color.green));
            circularProgress.setFillColorWithOpacity(getResources().getColor(R.color.green_20));
        } else if (percent >= 30 && percent <= 100) {
            circularProgress.setFillBackgroundEnabled(false);
            circularProgress.setTextColor(getResources().getColor(R.color.black));
            circularProgress.setProgressBackgroundColor(getResources().getColor(R.color.gray_process));
            circularProgress.setProgressColor(getResources().getColor(R.color.blue_ocean));
            circularProgress.setFillColorWithOpacity(getResources().getColor(R.color.blue_ocean_20));
            circularProgress.setDotColor(getResources().getColor(R.color.blue_ocean));
        } else if (percent >= 15 && percent < 30) {
            circularProgress.setFillBackgroundEnabled(false);
            circularProgress.setTextColor(getResources().getColor(R.color.black));
            circularProgress.setProgressColor(getResources().getColor(R.color.black));
            circularProgress.setProgressBackgroundColor(getResources().getColor(R.color.gray_process));
            circularProgress.setFillColorWithOpacity(getResources().getColor(R.color.black_20));
            circularProgress.setDotColor(getResources().getColor(R.color.black));
        } else if (percent > 0 && percent < 15) {
            circularProgress.setFillBackgroundEnabled(false);
            circularProgress.setTextColor(getResources().getColor(R.color.black));
            circularProgress.setProgressColor(getResources().getColor(R.color.pink));
            circularProgress.setDotColor(getResources().getColor(R.color.pink));
            circularProgress.setProgressBackgroundColor(getResources().getColor(R.color.gray_process));
            circularProgress.setFillColorWithOpacity(getResources().getColor(R.color.pink_20));

        } else if (percent == 0) {
            circularProgress.setFillBackgroundEnabled(false);
            circularProgress.setTextColor(getResources().getColor(R.color.black));
            circularProgress.setProgressColor(getResources().getColor(R.color.gray_process));
            circularProgress.setProgressBackgroundColor(getResources().getColor(R.color.gray_process));
            circularProgress.setFillColorWithOpacity(getResources().getColor(R.color.red_20));
            circularProgress.setDotColor(getResources().getColor(R.color.gray_process));

        } else if (percent < 0) {
            circularProgress.setFillBackgroundEnabled(false);
            circularProgress.setTextColor(getResources().getColor(R.color.black));
            circularProgress.setProgressColor(getResources().getColor(R.color.red));
            circularProgress.setProgressBackgroundColor(getResources().getColor(R.color.red));
            circularProgress.setFillColorWithOpacity(getResources().getColor(R.color.red_20));
            circularProgress.setDotColor(getResources().getColor(R.color.red));
        }
    }

    class PatternProgressTextAdapter implements CircularProgressIndicator.ProgressTextAdapter {

        @NonNull
        @Override
        public String formatText(double currentProgress) {
            return NumberFormat.getInstance(Locale.US).format(currentProgress) + "%";
        }
    }
}
