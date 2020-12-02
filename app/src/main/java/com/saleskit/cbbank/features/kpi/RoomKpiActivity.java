package com.saleskit.cbbank.features.kpi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.KPI;
import com.saleskit.cbbank.features.account.KPIJson;
import com.saleskit.cbbank.features.account.KpiEmplyee;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.google.android.material.appbar.AppBarLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class
RoomKpiActivity extends BaseActivity implements KpiView, OnFilterClick {
    private static final String TAG = "RoomKpiActivity";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_filter)
    ImageView ivFilter;
    @BindView(R.id.et_search)
    ClearableEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.appbar_main)
    AppBarLayout appbarMain;
    @BindView(R.id.rv_month)
    RecyclerView rvMonth;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    @BindView(R.id.iv_revert)
    ImageView ivRevert;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.ll_drop)
    LinearLayout llDrop;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.cd_main)
    CoordinatorLayout cdMain;
    @BindView(R.id.ns_main)
    ScrollView nsMain;
    @BindView(R.id.fl_month)
    FrameLayout flMonth;
    @BindView(R.id.layoutParent)
    RelativeLayout layoutParent;
    private String type = "";
    private String id = "";
    //    @BindView(R.id.ll_drop)
//    LinearLayout llDrop;
    public static int choosenYear, choosenMonth, choosenQuater;
    private List<DateFilter> months = new ArrayList<>();
    private List<DateFilter> years = new ArrayList<>();
    private List<DateFilter> quarters = new ArrayList<>();
    private List<DateFilter> dateFilters = new ArrayList<>();
    private int page = 1;
    private int totalPages;
    private MonthAdapter monthAdapter;
    private List<KpiPersonModel> kpiPersonModels = new ArrayList<>();
    @Inject
    KpiPresent kpiPresent;
    private KpiPersonAdapter kpiPersonAdapter;
    private int currentYear;
    private int currentMonth;
    private String token;
    private int currentQuarter;
    private MonthAdapter.FilterType filterType = MonthAdapter.FilterType.MONTH;
    private ArrayList<KPIJson.DataBean> kpiList = new ArrayList<>();
    private boolean setLocalTime = false;

    private GestureDetector gestureDetector;
    private View.OnTouchListener gestureListener;
    OnSwipeTouchListener onSwipeTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_kpi);
        ButterKnife.bind(this);
        setupUI();
        token = HawkHelper.getInstance(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra(Constants.KPI_ID);
            type = intent.getStringExtra(Constants.KPI_TYPE);
            Calendar now = Calendar.getInstance();
            currentYear = now.get(Calendar.YEAR);
            currentMonth = now.get(Calendar.MONTH) + 1;
            currentQuarter = getQuarterByMonth(currentMonth);
            setLocalTime = intent.getBooleanExtra(Constants.LOCAL_TIME, false);
            if (type.equals(Constants.NORMAL)) {
                choosenYear = currentYear;
                choosenMonth = currentMonth;
                choosenQuater = currentQuarter;
                setupFilterModel(currentMonth, currentQuarter, currentYear);
                applyFilter(months, MonthAdapter.FilterType.MONTH, 3);
            } else {
                if (setLocalTime) {
                    choosenYear = currentYear;
                    choosenMonth = currentMonth;
                    choosenQuater = currentQuarter;
                    setupFilterModel(currentMonth, currentQuarter, currentYear);
                    applyFilter(months, MonthAdapter.FilterType.MONTH, 3);
                } else {
                    filterType = (MonthAdapter.FilterType) intent.getSerializableExtra(Constants.FILTER_TYPE);
                    setupFilterModel(choosenMonth, choosenQuater, choosenYear);
                    switch (filterType) {
                        case YEAR:
                            dateFilters = years;
                            choosenYear = years.get(3).getYear();
                            choosenMonth = years.get(3).getMonth();
                            choosenQuater = years.get(3).getQuarter();
                            break;
                        case QUARTER:
                            dateFilters = quarters;
                            choosenYear = quarters.get(3).getYear();
                            choosenMonth = quarters.get(3).getMonth();
                            choosenQuater = quarters.get(3).getQuarter();
                            break;
                        case MONTH:
                            dateFilters = months;
                            choosenYear = months.get(3).getYear();
                            choosenMonth = months.get(3).getMonth();
                            choosenQuater = months.get(3).getQuarter();
                            break;
                    }
                    applyFilter(dateFilters, filterType, 3);
                }
            }

            switch (type) {
                case Constants.DEPARTMENT:
                    tvName.setText("Phòng");
                    getDepartmentData();
//                    kpiPresent.getAllDepartmentByMonth(token, this, swAll, currentMonth, currentYear,
//                            etSearch.getText().toString(), id, page);
                    break;
                case Constants.EMPLOYEE:
                    tvName.setText("Họ và tên");
                    getEmployeeData();
//                    kpiPresent.getEmployeeByMonth(token, this, swAll, currentMonth, currentYear, etSearch.getText().toString(), id, page);
                    break;
                case Constants.BRANCH:
//                    getData();
                    tvName.setText("Chi nhánh");
                    getBranchData();
//                    kpiPresent.getBranchByMoth(token, this, swAll,
//                            currentMonth, currentYear, etSearch.getText().toString(), id, page);
                    break;
                default:
                    tvName.setText("Vùng");
                    kpiPresent.getRegion(token, this, swAll, currentMonth, currentYear, etSearch.getText().toString(), page);
                    break;
            }
        } else {
            getData();
        }
        ivBack.setOnClickListener(v -> finish());
        setupSwipe(type);
    }

    private void getInfo(String type) {
        switch (type) {
            case Constants.DEPARTMENT:
                getDepartmentData();
                break;
            case Constants.EMPLOYEE:
                getEmployeeData();
                break;
            case Constants.BRANCH:
                getBranchData();
                break;
            default:
                getRegionData();
                break;
        }
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
            getInfo(type);

        });
    }

    private void getEmployeeData() {
        if (filterType == MonthAdapter.FilterType.YEAR) {
            kpiPresent.getEmployeeByYear(token, this, swAll, choosenYear, etSearch.getText().toString(), id, page);
        } else if (filterType == MonthAdapter.FilterType.MONTH) {
            kpiPresent.getEmployeeByMonth(token, this, swAll, choosenMonth,
                    choosenYear, etSearch.getText().toString(), id, page);
        } else {
            kpiPresent.getEmployeeByQuater(token, this, swAll, choosenQuater, choosenYear,
                    etSearch.getText().toString(), id, page);
        }
    }

    private void getDepartmentData() {
        if (filterType == MonthAdapter.FilterType.YEAR) {
            kpiPresent.getDepartMentByYear(token, this, swAll, choosenYear, etSearch.getText().toString(), id, page);
        } else if (filterType == MonthAdapter.FilterType.MONTH) {
            kpiPresent.getAllDepartmentByMonth(token, this, swAll, choosenMonth,
                    choosenYear, etSearch.getText().toString(), id, page);
        } else {
            kpiPresent.getDepartmentByQuater(token, this, swAll, choosenQuater, choosenYear,
                    etSearch.getText().toString(), id, page);
        }
    }

    private void getBranchData() {
        if (filterType == MonthAdapter.FilterType.YEAR) {
            kpiPresent.getBranchByYear(token, this, swAll, choosenYear, etSearch.getText().toString(), id, page);
        } else if (filterType == MonthAdapter.FilterType.MONTH) {
            kpiPresent.getBranchByMoth(token, this, swAll, choosenMonth,
                    choosenYear, etSearch.getText().toString(), id, page);
        } else {
            kpiPresent.getBranchByQuater(token, this, swAll, choosenQuater, choosenYear,
                    etSearch.getText().toString(), id, page);
        }
    }

    private void getRegionData() {
        if (filterType == MonthAdapter.FilterType.YEAR) {
            kpiPresent.getRegionByYear(token, this, swAll, choosenYear, etSearch.getText().toString(), page);
        } else if (filterType == MonthAdapter.FilterType.MONTH) {
            kpiPresent.getRegion(token, this, swAll, choosenMonth,
                    choosenYear, etSearch.getText().toString(), page);
        } else {
            kpiPresent.getRegionByQuater(token, this, swAll, choosenQuater, choosenYear,
                    etSearch.getText().toString(), page);
        }
    }

    private void getData() {

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupUI() {
        ivSearch.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                page = 1;
                getInfo(type);
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
                tvResult.setVisibility(View.GONE);
                if (s.toString().isEmpty()) {
                    page = 1;
                    getInfo(type);
                }
            }
        });

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
                        applyFilter(months, MonthAdapter.FilterType.MONTH, 3);
                        choosenYear = months.get(3).getYear();
                        choosenQuater = months.get(3).getQuarter();
                        choosenMonth = months.get(3).getMonth();
                        checkItemCheked(item);
                        popupMenu.dismiss();
                        page = 1;
                        getInfo(type);
                        break;
                    /*case R.id.menu_year:
                        filterType = MonthAdapter.FilterType.YEAR;
                        applyFilter(years, MonthAdapter.FilterType.YEAR, 3);
                        choosenYear = years.get(3).getYear();
                        choosenQuater = 4;
                        choosenMonth = 12;
                        checkItemCheked(item);
                        popupMenu.dismiss();
                        page = 1;
                        getInfo(type);
                        break;*/
                    case R.id.menu_quater:
                        filterType = MonthAdapter.FilterType.QUARTER;
                        applyFilter(quarters, MonthAdapter.FilterType.QUARTER, 3);
                        choosenYear = quarters.get(3).getYear();
                        choosenQuater = quarters.get(3).getQuarter();
                        choosenMonth = choosenQuater * 3;
                        checkItemCheked(item);
                        popupMenu.dismiss();
                        page = 1;
                        getInfo(type);
                        break;

                }
                return false;
            });
            popupMenu.show();
        });
//        rvDetail.setNestedScrollingEnabled(false);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvMonth.setLayoutManager(gridLayoutManager);

        LinearLayoutManager kpiLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDetail.setLayoutManager(kpiLinearLayoutManager);

        onSwipeTouchListener = new OnSwipeTouchListener(this) {

            public void onSwipeRight() {

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
                    choosenMonth = months.get(3).getMonth();
                    choosenQuater = months.get(3).getQuarter();
                    choosenYear = months.get(3).getYear();
                    getDataByMonth();

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
                    getDataByQuater();

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
                    choosenYear = years.get(3).getYear();
                    getDataByYear();
                }
                monthAdapter.selectedPos = 3;
                monthAdapter.notifyDataSetChanged();

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
                    choosenMonth = months.get(3).getMonth();
                    choosenQuater = months.get(3).getQuarter();
                    choosenYear = months.get(3).getYear();
                    getDataByMonth();
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
                    choosenQuater = quarters.get(3).getQuarter();
                    choosenYear = quarters.get(3).getYear();
                    getDataByQuater();
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
                    choosenYear = years.get(3).getYear();
                    getDataByYear();
                }

                monthAdapter.notifyDataSetChanged();
            }
        };
        nsMain.setOnTouchListener(onSwipeTouchListener);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        super.dispatchTouchEvent(ev);
        return onSwipeTouchListener.getGestureDetector().onTouchEvent(ev);
    }

    private void getDataByMonth() {
        page = 1;
        switch (type) {
            case Constants.DEPARTMENT:
                kpiPresent.getAllDepartmentByMonth(token, RoomKpiActivity.this, swAll, choosenMonth, choosenYear,
                        etSearch.getText().toString(), id, page);
                break;
            case Constants.EMPLOYEE:
                kpiPresent.getEmployeeByMonth(token, RoomKpiActivity.this, swAll, choosenMonth, choosenYear, etSearch.getText().toString(), id, page);
                break;
            case Constants.BRANCH:
//                    getData();
                kpiPresent.getBranchByMoth(token, RoomKpiActivity.this, swAll, choosenMonth,
                        choosenYear, etSearch.getText().toString(), id, page);
                break;
            default:
                kpiPresent.getRegion(token, RoomKpiActivity.this, swAll, choosenMonth, choosenYear, etSearch.getText().toString(), page);
                break;
        }

    }

    private void getDataByQuater() {
        page = 1;
        switch (type) {
            case Constants.DEPARTMENT:
                kpiPresent.getDepartmentByQuater(token, RoomKpiActivity.this, swAll, choosenQuater, choosenYear,
                        etSearch.getText().toString(), id, page);
                break;
            case Constants.EMPLOYEE:
                kpiPresent.getEmployeeByQuater(token, RoomKpiActivity.this, swAll, choosenQuater, choosenYear, etSearch.getText().toString(), id, page);
                break;
            case Constants.BRANCH:
//                    getData();
                kpiPresent.getBranchByQuater(token, RoomKpiActivity.this, swAll, choosenQuater,
                        choosenYear, etSearch.getText().toString(), id, page);
                break;
            default:
                kpiPresent.getRegionByQuater(token, RoomKpiActivity.this, swAll, choosenQuater, choosenYear, etSearch.getText().toString(), page);
                break;
        }
    }

    private void getDataByYear() {
        page = 1;
        switch (type) {
            case Constants.DEPARTMENT:
                kpiPresent.getDepartMentByYear(token, RoomKpiActivity.this, swAll, choosenYear,
                        etSearch.getText().toString(), id, page);
                break;
            case Constants.EMPLOYEE:
                kpiPresent.getEmployeeByYear(token, RoomKpiActivity.this, swAll, choosenYear, etSearch.getText().toString(), id, page);
                break;
            case Constants.BRANCH:
//                    getData();
                kpiPresent.getBranchByYear(token, RoomKpiActivity.this, swAll,
                        choosenYear, etSearch.getText().toString(), id, page);
                break;
            default:
                kpiPresent.getRegionByYear(token, RoomKpiActivity.this, swAll, choosenYear, etSearch.getText().toString(), page);
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

    private int getQuarterByMonth(int month) {
        switch (month) {
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

    private void applyFilter(List<DateFilter> dateFilters, MonthAdapter.FilterType filterType, int position) {
        monthAdapter = new MonthAdapter(dateFilters, this, filterType, this::onFilterClick);
        monthAdapter.selectedPos = position;
        monthAdapter.notifyDataSetChanged();
        rvMonth.setAdapter(monthAdapter);
        new Handler().postDelayed(() -> rvMonth.scrollToPosition(position), 200);
    }

    private void checkItemCheked(MenuItem item) {
        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_room_kpi;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        kpiPresent.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        kpiPresent.detachView();
    }

    @Override
    public void onPercentClick() {

    }

    @Override
    public void showAllBranch(List<KpiBranch.DataBean.ResultsBean> list, int totalPages) {
        this.totalPages = totalPages;
        if (page == 1) {
            kpiList.clear();
        }
        OnItemClicklistener onItemClicklistener = position -> {
            Intent i = new Intent(RoomKpiActivity.this, RoomKpiActivity.class);
            i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
            i.putExtra(Constants.KPI_ID, kpiList.get(position).getJobTitle());
            i.putExtra(Constants.FILTER_TYPE, filterType);

            if (filterType == MonthAdapter.FilterType.MONTH)
                i.putExtra(Constants.FILER_MODEL, (Serializable) months);
            else if (filterType == MonthAdapter.FilterType.YEAR) {
                i.putExtra(Constants.FILER_MODEL, (Serializable) years);
            } else {
                i.putExtra(Constants.FILER_MODEL, (Serializable) quarters);
            }
            startActivity(i);

        };
        if (list.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        for (KpiBranch.DataBean.ResultsBean dataBean : list) {
            kpiList.add(new KPIJson.DataBean(dataBean.getBranchName(), dataBean.getBranchId(), dataBean.getPercentKpi(), dataBean.isShow()));
        }
        Log.e(TAG, "showRegionList: " + kpiList.size());
        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, true, rvDetail);
            kpiPersonAdapter.setHasStableIds(true);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }
        kpiPersonAdapter.setLoaded();
        page++;
        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            getBranchData();
        });
//        ivRevert.setOnClickListener(v -> {
//                    Collections.reverse(list);
//                    kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, true, rvDetail);
//                    rvDetail.setAdapter(kpiPersonAdapter);
//                }
//        );
    }


    @Override
    public void showRegionList(List<KPI.DataBean.ResultsBean> list, int totalPages) {
        this.totalPages = totalPages;
        if (page == 1) {
            kpiList.clear();
        }
        Log.e(TAG, "showRegionList: total " + totalPages);
        OnItemClicklistener onItemClicklistener = position -> {
            Intent i = new Intent(RoomKpiActivity.this, RoomKpiActivity.class);
            i.putExtra(Constants.KPI_TYPE, Constants.BRANCH);
            i.putExtra(Constants.KPI_ID, kpiList.get(position).getJobTitle());
            i.putExtra(Constants.FILTER_TYPE, filterType);
            if (filterType == MonthAdapter.FilterType.MONTH)
                i.putExtra(Constants.FILER_MODEL, (Serializable) months);
            else if (filterType == MonthAdapter.FilterType.YEAR) {
                i.putExtra(Constants.FILER_MODEL, (Serializable) years);
            } else {
                i.putExtra(Constants.FILER_MODEL, (Serializable) quarters);
            }
            startActivity(i);

        };
        for (KPI.DataBean.ResultsBean dataBean : list) {
            kpiList.add(new KPIJson.DataBean(dataBean.getRegionName(), dataBean.getRegionId(), dataBean.getPercentKpi(), dataBean.isShow()));
        }
        if (kpiList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        Log.e(TAG, "showRegionList: " + kpiList.size());
        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, true, rvDetail);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }
        kpiPersonAdapter.setLoaded();
        page++;
        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            getRegionData();
        });
//        ivRevert.setOnClickListener(v -> {
//                    Collections.reverse(list);
//                    kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, true, rvDetail);
//                    rvDetail.setAdapter(kpiPersonAdapter);
//                }
//        );
    }

    @Override
    public void showAllDepartment(List<KpiDepartment.DataBean.ResultsBean> list, int totalPages) {
        this.totalPages = totalPages;
        if (page == 1) {
            kpiList.clear();
        }
        Log.e(TAG, "showDepartList: total " + totalPages);
        OnItemClicklistener onItemClicklistener = position -> {
            Intent i = new Intent(RoomKpiActivity.this, RoomKpiActivity.class);
            i.putExtra(Constants.KPI_TYPE, Constants.EMPLOYEE);
            i.putExtra(Constants.KPI_ID, kpiList.get(position).getJobTitle());
            i.putExtra(Constants.FILTER_TYPE, filterType);
            if (filterType == MonthAdapter.FilterType.MONTH)
                i.putExtra(Constants.FILER_MODEL, (Serializable) months);
            else if (filterType == MonthAdapter.FilterType.YEAR) {
                i.putExtra(Constants.FILER_MODEL, (Serializable) years);
            } else {
                i.putExtra(Constants.FILER_MODEL, (Serializable) quarters);
            }
            startActivity(i);

        };

        for (KpiDepartment.DataBean.ResultsBean dataBean : list) {
            kpiList.add(new KPIJson.DataBean(dataBean.getDepartmentName(), dataBean.getDepartmentId(), dataBean.getPercentKpi(), dataBean.isIsShow()));
        }
        if (kpiList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        Log.e(TAG, "showDepartList: " + kpiList.size());
        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, true, rvDetail);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }
        kpiPersonAdapter.setLoaded();
        page++;
        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            getDepartmentData();
        });
//        ivRevert.setOnClickListener(v -> {
//                    Collections.reverse(list);
//                    kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, true, rvDetail);
//                    rvDetail.setAdapter(kpiPersonAdapter);
//                }
//        );
    }

    @Override
    public void showAllEmployee(List<KpiEmplyee.DataBean.ResultsBean> list, int pages) {
        this.totalPages = pages;
        if (page == 1) {
            kpiList.clear();
        }
        OnItemClicklistener onItemClicklistener = position -> {
            Intent i = new Intent(RoomKpiActivity.this, KpiActivity.class);
            i.putExtra(Constants.KPI_TYPE, Constants.DEPARTMENT);
            i.putExtra(Constants.KPI_PERSON, kpiList.get(position).getId());
            i.putExtra(Constants.FILTER_TYPE, filterType);
            i.putExtra(Constants.SCREEN_TYPE, Constants.KPI_TYPE);

            i.putExtra(Constants.CHOSEN_MONTH, choosenMonth);
            i.putExtra(Constants.CHOSEN_QUARTER, choosenQuater);
            i.putExtra(Constants.CHOSEN_YEAR, choosenYear);

            startActivity(i);

        };

        for (KpiEmplyee.DataBean.ResultsBean dataBean : list) {
            kpiList.add(new KPIJson.DataBean(dataBean.getFullName(), dataBean.getUserName(), dataBean.getJobTitleName(), dataBean.getPercentKpi(), true));
        }
        if (kpiList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        if (kpiPersonAdapter == null) {
            kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, true, onItemClicklistener, true, rvDetail);
            rvDetail.setAdapter(kpiPersonAdapter);
        } else {
            kpiPersonAdapter.notifyDataSetChanged();
        }
        kpiPersonAdapter.setLoaded();
        page++;
        kpiPersonAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            getEmployeeData();
        });
//        ivRevert.setOnClickListener(v -> {
//                    Collections.reverse(list);
//                    kpiPersonAdapter = new KpiPersonAdapter(this, kpiList, false, onItemClicklistener, true, rvDetail);
//                    rvDetail.setAdapter(kpiPersonAdapter);
//                }
//        );
    }


    @Override
    public void onFilterClick(int position, MonthAdapter.FilterType filterType) {
        page = 1;
        this.filterType = filterType;
        if (filterType == MonthAdapter.FilterType.YEAR) {
            choosenYear = years.get(position).getYear();
//            for (int i = 0; i < 4; i++) {
//                months.get(i).setYear(currentYear);
//            }
//            for (int i = 0; i < 4; i++) {
//                quarters.get(i).setYear(currentYear);
//            }
            switch (type) {
                case Constants.DEPARTMENT:
                    kpiPresent.getDepartMentByYear(token, this, swAll, choosenYear,
                            etSearch.getText().toString(), id, page);
                    break;
                case Constants.EMPLOYEE:
                    kpiPresent.getEmployeeByYear(token, this, swAll, choosenYear, etSearch.getText().toString(), id, page);
                    break;
                case Constants.BRANCH:
//                    getData();
                    kpiPresent.getBranchByYear(token, this, swAll,
                            choosenYear, etSearch.getText().toString(), id, page);
                    break;
                default:
                    kpiPresent.getRegionByYear(token, this, swAll, years.get(position).getYear(), etSearch.getText().toString(), page);
                    break;
            }
        } else if (filterType == MonthAdapter.FilterType.MONTH) {
            choosenMonth = months.get(position).getMonth();
            choosenQuater = months.get(position).getQuarter();
            choosenYear = months.get(position).getYear();
//            kpiPresent.getRegion(token, this, swAll, months.get(position).getMonth(),
//                    months.get(position).getYear(), etSearch.getText().toString());
            switch (type) {
                case Constants.DEPARTMENT:
                    kpiPresent.getAllDepartmentByMonth(token, this, swAll, choosenMonth, choosenYear,
                            etSearch.getText().toString(), id, page);
                    break;
                case Constants.EMPLOYEE:
                    kpiPresent.getEmployeeByMonth(token, this, swAll, choosenMonth, choosenYear, etSearch.getText().toString(), id, page);
                    break;
                case Constants.BRANCH:
                    kpiPresent.getBranchByMoth(token, this, swAll, choosenMonth,
                            choosenYear, etSearch.getText().toString(), id, page);
                    break;
                default:
                    kpiPresent.getRegion(token, this, swAll, choosenMonth, choosenYear, etSearch.getText().toString(), page);
                    break;
            }
        } else {
            choosenQuater = quarters.get(position).getQuarter();
            choosenYear = quarters.get(position).getYear();
            switch (type) {
                case Constants.DEPARTMENT:
                    kpiPresent.getDepartmentByQuater(token, this, swAll, choosenQuater, choosenYear,
                            etSearch.getText().toString(), id, page);
                    break;
                case Constants.EMPLOYEE:
                    kpiPresent.getEmployeeByQuater(token, this, swAll, choosenQuater, choosenYear, etSearch.getText().toString(), id, page);
                    break;
                case Constants.BRANCH:
                    kpiPresent.getBranchByQuater(token, this, swAll, choosenQuater,
                            choosenYear, etSearch.getText().toString(), id, page);
                    break;
                default:
                    kpiPresent.getRegionByQuater(token, this, swAll, choosenQuater, choosenYear, etSearch.getText().toString(), page);
                    break;
            }
        }
    }
}
