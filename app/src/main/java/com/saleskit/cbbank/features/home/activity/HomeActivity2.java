package com.saleskit.cbbank.features.home.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.MvpStarterApplication;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Datajson;
import com.saleskit.cbbank.features.account.LoginEnActivity;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.add_new_customer.model.Category;
import com.saleskit.cbbank.features.database.DaoSession;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.database.OptionItem;
import com.saleskit.cbbank.features.database.OptionItemDao;
import com.saleskit.cbbank.features.database.OptionItemGroupName;
import com.saleskit.cbbank.features.flowone.ProductDetailActivity;
import com.saleskit.cbbank.features.home.HomeMvpView;
import com.saleskit.cbbank.features.home.MainViewpagerAdapter;
import com.saleskit.cbbank.features.home.NotificationListActivity;
import com.saleskit.cbbank.features.home.Product;
import com.saleskit.cbbank.features.home.fragment.EnterpriseProductFragment;
import com.saleskit.cbbank.features.home.fragment.Home2Fragment;
import com.saleskit.cbbank.features.home.fragment.HomeMainFragment;
import com.saleskit.cbbank.features.home.fragment.SearchFragment;
import com.saleskit.cbbank.features.home.fragment.SlideMenuFragment;
import com.saleskit.cbbank.features.kpi.KpiActivity;
import com.saleskit.cbbank.features.news.NewActivity;
import com.saleskit.cbbank.features.services.NotiEvent;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Token;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeActivity2 extends BaseActivity implements HomeMvpView {
    private static final String TAG = "HomeActivity2";
    public static TextView tvName;
    private TextView tvPhoneNumber;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.iv_filter)
    ImageView ivFilter;
    @BindView(R.id.rl_kpi_main)
    RelativeLayout rlKpiMain;
    @BindView(R.id.iv_menu_home)
    ImageView ivMenuHome;
    @BindView(R.id.rl_search)
    LinearLayout rlSearch;
    @BindView(R.id.ll_option_end)
    LinearLayout llOptionEnd;
    @BindView(R.id.rl_head_main)
    RelativeLayout rlHeadMain;
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    SlideMenuFragment menuFragment;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    SlideMenuFragment fragmentMenuHomeMain;
    @BindView(R.id.iv_pre)
    ImageView ivPre;
    @BindView(R.id.tv_tilte_main)
    TextView tvTilteMain;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.fl_main_pager)
    FrameLayout flMainPager;
    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;
    @BindView(R.id.iv_shut_down)
    ImageView ivShutDown;
    @BindView(R.id.iv_menu_search)
    ImageView ivMenuSearch;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.rl_search_frag)
    RelativeLayout rlSearchFrag;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rl_head_search)
    RelativeLayout rlHeadSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_account_balance)
    TextView tvAccountBalance;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_noti)
    ImageView ivNoti;
    @BindView(R.id.tv_number_noti)
    TextView tvNumberNoti;
    private boolean isOpened = true;
    private List<Fragment> fragments = new ArrayList<>();
    private MainViewpagerAdapter mainViewpagerAdapter;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private List<Product.DataBean> ownProducts = new ArrayList<>();
    private List<Product.DataBean> enterPriseProducts = new ArrayList<>();
    private List<Product.DataBean> hdProducts = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce = false;
    private String token;
    private TextView tvError;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = HawkHelper.getInstance(this).getToken();//        tvName.setText(token.getFullName());
        initSlideMenu();
        getData();
        setupPopUp();
        setupUI();
        setupDialog();
        tvName = findViewById(R.id.tv_name);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
//        getData();
//        getDataForCustomerProfile();
//        }

    }

    private void getData() {
        showLoading();
        ownProducts.clear();
        enterPriseProducts.clear();
        hdProducts.clear();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        Call<Product> call = apiInterface.getAllproduct(token);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                hideLoading();
                if (response.code() == 200) {
                    List<Product.DataBean> datas = response.body().getData();
                    for (Product.DataBean dataBean : datas) {
                        if ((dataBean.getProductType() == 1 || dataBean.getProductType() == 3)
                                && dataBean.getCustomerType() == 1) {
                            ownProducts.add(dataBean);
                        }
                        if ((dataBean.getProductType() == 1 || dataBean.getProductType() == 3)
                                && dataBean.getCustomerType() == 2) {
                            enterPriseProducts.add(dataBean);
                        }
                        if (dataBean.getProductType() == 2) {
                            hdProducts.add(dataBean);

                        }
                    }
                    HawkHelper.getInstance(HomeActivity2.this).saveListProduct(ownProducts);
                    HawkHelper.getInstance(HomeActivity2.this).saveListHDProduct(hdProducts);
                    HawkHelper.getInstance(HomeActivity2.this).saveListEnterpriseProduct(enterPriseProducts);
                    initFragment();
                    getDateViewPager();
                } else {
                    initFragment();
                    getDateViewPager();

                    Toast.makeText(HomeActivity2.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable throwable) {
                hideLoading();
                initFragment();
                getDateViewPager();

            }
        });
    }

    private void getDateViewPager() {
        mainViewpagerAdapter = new MainViewpagerAdapter(getSupportFragmentManager());
        vpMain.setAdapter(mainViewpagerAdapter);
        ivNext.setOnClickListener(v -> {
            if (vpMain.getCurrentItem() == 0) {
                tvTilteMain.setText(getString(R.string.huydong_product));
                vpMain.setCurrentItem(1);
            } else {
                tvTilteMain.setText("Sản phẩm doanh nghiệp");
                vpMain.setCurrentItem(2);
            }
        });
        ivPre.setOnClickListener(v -> {
            if (vpMain.getCurrentItem() == 1) {
                tvTilteMain.setText(getString(R.string.vay_product));
                vpMain.setCurrentItem(0);
            } else {
                tvTilteMain.setText(getString(R.string.huydong_product));
                vpMain.setCurrentItem(1);
            }
        });
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected:  " + position);
                switch (position) {
                    case 0:
                        tvTilteMain.setText(getString(R.string.vay_product));
                        break;
                    case 1:
                        tvTilteMain.setText(getString(R.string.huydong_product));
                        break;
                    case 2:
                        tvTilteMain.setText("Sản phẩm cho vay doanh nghiệp");
                        break;
                }
                EventBus.getDefault().postSticky(new ChangePageEvent(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        circleIndicator.setViewPager(vpMain);
        mainViewpagerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private void setupDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_label_editor, null);
        dialogBuilder.setView(dialogView);
        tvError = dialogView.findViewById(R.id.tv_error);
        tvError.setText("User hiện tại không có chức vụ nào, phiên đăng nhập kết thúc!");
        alertDialog = dialogBuilder.create();
    }

    private void initFragment() {
        fragments.add(HomeMainFragment.getInstance());
        fragments.add(Home2Fragment.getInstance());
        fragments.add(EnterpriseProductFragment.newInstance());
    }

    private void setupUI() {
        ivNoti.setOnClickListener(v -> {
            Intent i = new Intent(this, NotificationListActivity.class);
            startActivity(i);
        });

        ivMenuHome.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        ivMenuSearch.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        ivMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        ivShutDown.setOnClickListener(v -> {
            logOut();
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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().postSticky(new SearchQueryEvent(s.toString()));
                        ivClose.setVisibility(View.VISIBLE);
                        if (etSearch.getText().length() == 0) {
                            ivClose.setVisibility(View.GONE);
                        } else {
                            ivClose.setVisibility(View.VISIBLE);
                        }

                    }
                }, 100);
            }
        });
        ivClose.setOnClickListener(v -> {
            ivClose.setVisibility(View.GONE);
            etSearch.setText("");
            EventBus.getDefault().postSticky(new DeleteEvent());
//                            EventBus.getDefault().postSticky(new SearchQueryEvent(""));
        });
        tvCancel.setOnClickListener(v -> {
            EventBus.getDefault().postSticky(new BackEvent());
            EventBus.getDefault().postSticky(new ChangePageEvent(vpMain.getCurrentItem()));
        });
        rlSearch.setOnClickListener(v -> {
            isOpened = false;
            SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager()
                    .findFragmentByTag(SearchFragment.class.toString());
            if (searchFragment != null && searchFragment.isVisible()) {
                return;
            }
            EventBus.getDefault().postSticky(new ChangePageEvent(vpMain.getCurrentItem()));
            AppUtil.openFragment(getSupportFragmentManager(), R.id.fl_main,
                    SearchFragment.newInstance(), SearchFragment.class.toString());
            rlHeadMain.setVisibility(View.GONE);
            rlHeadSearch.setVisibility(View.VISIBLE);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtil.isNetworkConnected(this)) {
            Toast.makeText(this, "Không có kết nối Internet!", Toast.LENGTH_SHORT).show();
        } else {
            getNumberMess();
            menuFragment.getMenuInterestTable();
            menuFragment.ggetEmployeeInfo();
        }

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        EventBus.getDefault().postSticky(new ChangePageEvent(vpMain.getCurrentItem()));
    }

    private void getNumberMess() {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<Datajson> call = apiInterface.getCountUnreadMess(HawkHelper.getInstance(this).getToken());
        call.enqueue(new Callback<Datajson>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Datajson> call, Response<Datajson> response) {
                if (response.code() == 200) {
                    int number = Integer.parseInt(response.body().getData());
                    if (number == 0) {
                        tvNumberNoti.setVisibility(View.GONE);
                    } else if (number > 0 && number < 100) {
                        tvNumberNoti.setVisibility(View.VISIBLE);
                        tvNumberNoti.setText("" + number);
                    } else {
                        tvNumberNoti.setText("99+");
                    }
                } else if (response.code() == 401) {
                    Toast.makeText(HomeActivity2.this, "Đăng nhập đã hết hạn, hãy đăng nhập lại!", Toast.LENGTH_SHORT).show();
                    logOut();
                }
            }

            @Override
            public void onFailure(Call<Datajson> call, Throwable t) {

            }
        });
    }

    private void setupPopUp() {
        ivFilter.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.getMenu().findItem(R.id.menu_filter).setTitle(Html.fromHtml("<font color='#145aa2'>Bộ lọc</font>"));
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_moth:
                        Toast.makeText(this, "dfsd", Toast.LENGTH_SHORT).show();
                        checkItemCheked(item);
                        break;
                    /*case R.id.menu_year:
                        checkItemCheked(item);
                        break;*/
                    case R.id.menu_quater:
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
        return R.layout.activity_home2;
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

    private void initSlideMenu() {
        menuFragment = (SlideMenuFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentMenu_home_main);
        menuFragment.setListener(this);
    }

    @Override
    public void onHomeClick() {
        if (isOpened) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            EventBus.getDefault().postSticky(new BackEvent());
            drawerLayout.closeDrawer(GravityCompat.START);
        }
//        Intent i = new Intent(this, RoomKpiActivity.class);
//        startActivity(i);
//        overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//        KpiFragment kpiFragment = (KpiFragment) getSupportFragmentManager()
//                .findFragmentByTag(KpiFragment.class.toString());
//        if (kpiFragment != null && kpiFragment.isVisible()) {
//            return;
//        }
//
//        AppUtil.openFragment(getSupportFragmentManager(), R.id.fl_main,
//                KpiFragment.newInstance(), KpiFragment.class.toString());
//        KpiFragment.newInstance().setListener(this);
//        rlHeadMain.setVisibility(View.GONE);
//        rlKpiMain.setVisibility(View.VISIBLE);
//        rlKpiMain.setBackgroundColor(getResources().getColor(R.color.blue_kpi));

    }

    @Override
    public void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onDestroyKpiView() {
        rlHeadMain.setVisibility(View.VISIBLE);
        rlKpiMain.setVisibility(View.GONE);
    }

    @Override
    public void openNewScreen() {
        Intent i = new Intent(this, NewActivity.class);
        startActivity(i);
    }

    @Override
    public void openCustomerScreen() {

    }

    @Override
    public void onLogOut() {
//        DaoSession daoSession = ((MvpStarterApplication) getApplication()).getDaoSession();
//        OptionItemDao optionItemDao = daoSession.getOptionItemDao();
//        optionItemDao.deleteAll();
//
        logOut();

    }

    private void logOut() {
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseMessaging.getInstance().unsubscribeFromTopic("article");

        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<MessageBean> call = apiInterface.doLogOut("Bearer " + HawkHelper.getInstance(this).getTokenModel().getToken());
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.code() == 200) {
                    Hawk.deleteAll();
                    Intent i = new Intent(HomeActivity2.this, LoginEnActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String mess = jObjError.getString("Message");
                        Timber.e(mess);
                        Toast.makeText(HomeActivity2.this, mess, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Timber.e(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });
//        HawkHelper.getInstance(this).saveAuthorToken("");
//        HawkHelper.getInstance(this).putLoginStatus(false);
//        HawkHelper.getInstance(this).putLoginStatus(false);

    }

    @Override
    public void onKPIClick() {
        Intent i = new Intent(this, KpiActivity.class);
        i.putExtra(Constants.SCREEN_TYPE, Constants.HOME_TYPE);
        i.putExtra(Constants.KPI_PERSON, HawkHelper.getInstance(this).getTokenModel().getUserName());
        startActivity(i);
    }

    @Override
    public void onShowUserError() {
        Toast.makeText(this, "User hiện tại không có chức vụ nào, phiên đăng nhập kết thúc! ", Toast.LENGTH_SHORT).show();
        logOut();
    }

    @Subscribe(sticky = true)
    public void onDeleteKpiScreen(String s) {
        if (s.equals(Constants.DELETE_EVENT)) {
            rlHeadSearch.setVisibility(View.GONE);
            rlHeadMain.setVisibility(View.VISIBLE);
            isOpened = true;
        }
    }

    @Subscribe(sticky = true)
    public void onReceiveNoti(NotiEvent notiEvent) {
        getNumberMess();
    }

    @Override
    public void onBackPressed() {
        if (!SearchFragment.openSearch) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Nhấn back 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else {
            super.onBackPressed();
        }
    }
}
