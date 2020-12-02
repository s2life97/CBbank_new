package com.saleskit.cbbank.features.account;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.appointment.AddApointmentActivity;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.add_new_customer.model.Category;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.detail.HDCustomerDetailActivity;
import com.saleskit.cbbank.features.home.BlxNewActivity;
import com.saleskit.cbbank.features.home.LocationActivity;
import com.saleskit.cbbank.features.home.NotiDetailActivity;
import com.saleskit.cbbank.features.home.activity.HomeActivity2;
import com.saleskit.cbbank.features.home.activity.ProductListActivity;
import com.saleskit.cbbank.features.news.DetailNewActivity;
import com.saleskit.cbbank.features.news.NewActivity;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginEnActivity extends BaseActivity implements LoginView {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.et_username_en)
    EditText etUsernameEn;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.tv_user_type)
    Spinner tvUserType;
    @BindView(R.id.ll_main_login)
    LinearLayout llMainLogin;
    @BindView(R.id.bt_interest)
    LinearLayout btInterest;
    @BindView(R.id.bt_product)
    LinearLayout btProduct;
    @BindView(R.id.bt_location)
    LinearLayout btLocation;
    @BindView(R.id.bt_new)
    LinearLayout btNew;
    @BindView(R.id.bt_login_start)
    Button btLoginStart;
    @BindView(R.id.line_1)
    RelativeLayout line1;
    @BindView(R.id.line_2)
    RelativeLayout line2;
    @BindView(R.id.line_3)
    RelativeLayout line3;
    @BindView(R.id.rl_spinner)
    RelativeLayout rlSpinner;

    private boolean loginStatus = false;
    public static String authenToken = "";
    private boolean isShowPass = false;
    @Inject
    LoginPresenter loginPresenter;
    private String token;
    private TextView tvError;
    private boolean canLogin = true;
    private int countTime = 20;
    private String firebaseToken;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_en);
        ButterKnife.bind(this);
        creatDialog();
        setupUI();
        setupMenu();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        return;
                    }
                    String token = task.getResult().getToken();
                    firebaseToken = token;
                });
//        showLoading();
        Glide.with(this).load(R.drawable.bg_login_en).into(ivBg);
        loginStatus = HawkHelper.getInstance(this).getLoginStatus();
        token = HawkHelper.getInstance(this).getToken();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String type = b.getString("type");
            String id = b.getString("forwardId");
            String notificationId = b.getString("notificationId");
            if (type != null && !TextUtils.isEmpty(type)) {
                if (!TextUtils.isEmpty(token)) {
                    Intent i = new Intent(this, HomeActivity2.class);
                    startActivity(i);
                }
                switch (type) {
                    case "1":
                        Intent intent = new Intent(getApplicationContext(), DetailNewActivity.class);
                        intent.putExtra(Constants.ARTICLE, Integer.valueOf(id));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra(Constants.NOTIFY_ID, id);
                        intent.putExtra(Constants.READ_NOTI, false);
                        intent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                        startActivity(intent);
                        break;
                    case "2":
                        Intent intent1 = new Intent(getApplicationContext(), AddApointmentActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.putExtra(Constants.SEE_DETAIL, Constants.FROM_NOTI);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent1.putExtra(Constants.APPOINT_ID, id);
                        intent1.putExtra(Constants.READ_NOTI, false);
                        intent1.putExtra(Constants.NOTIFICATION_ID, notificationId);
                        startActivity(intent1);
                        break;
                    case "3":
                        Intent intent2 = new Intent(getApplicationContext(), HDCustomerDetailActivity.class);
                        intent2.putExtra(Constants.SCREEN_TYPE, true);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent2.putExtra(Constants.CUSTOMER_PROFILEID, Integer.parseInt(id));
                        intent2.putExtra(Constants.READ_NOTI, false);
                        intent2.putExtra(Constants.NOTIFICATION_ID, notificationId);
                        startActivity(intent2);
                        break;
                    default:
                        Intent intent3 = new Intent(getApplicationContext(), NotiDetailActivity.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent3.putExtra(Constants.NOTIFY_ID, Integer.parseInt(id));
                        intent3.putExtra(Constants.READ_NOTI, false);
                        intent3.putExtra(Constants.NOTIFICATION_ID, notificationId);
                        startActivity(intent3);
                }
                finish();
                return;
            }
        }
        if (!TextUtils.isEmpty(token)) {
            Intent i = new Intent(this, HomeActivity2.class);
            startActivity(i);
            finish();
        }
//        if (BuildConfig.DEBUG) {
//            etUsernameEn.setText("thanhnc1");
//            etPassword.setText("123456");
//        }

    }

    private void creatDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_label_editor, null);
        dialogBuilder.setView(dialogView);
        tvError = dialogView.findViewById(R.id.tv_error);
        alertDialog = dialogBuilder.create();


    }

    private void setupMenu() {

    }

    private void getDataForCustomerProfile() {


    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupUI() {
        tvUserType.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                line3.setBackgroundColor(getResources().getColor(R.color.blue));
                line2.setBackgroundColor(getResources().getColor(R.color.blue));
                line1.setBackgroundColor(getResources().getColor(R.color.yellow));
            }
            return false;
        });

        etPassword.setOnFocusChangeListener((v, hasFocus) -> {
            line1.setBackgroundColor(getResources().getColor(R.color.blue));
            line2.setBackgroundColor(getResources().getColor(R.color.blue));
            line3.setBackgroundColor(getResources().getColor(R.color.yellow));
        });
        etPassword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                line2.setBackgroundColor(getResources().getColor(R.color.blue));
                line1.setBackgroundColor(getResources().getColor(R.color.blue));
                line3.setBackgroundColor(getResources().getColor(R.color.yellow));
            }
            return false;
        });
        etUsernameEn.setOnFocusChangeListener((v, hasFocus) -> {
            line3.setBackgroundColor(getResources().getColor(R.color.blue));
            line1.setBackgroundColor(getResources().getColor(R.color.blue));
            line2.setBackgroundColor(getResources().getColor(R.color.yellow));
        });
        etUsernameEn.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                line3.setBackgroundColor(getResources().getColor(R.color.blue));
                line1.setBackgroundColor(getResources().getColor(R.color.blue));
                line2.setBackgroundColor(getResources().getColor(R.color.yellow));
            }
            return false;
        });

        String spinnterItem[];
        spinnterItem = new String[2];
        spinnterItem[0] = "Cán bộ ngân hàng";
        spinnterItem[1] = "Cộng tác viên";
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, spinnterItem);
        tvUserType.setAdapter(arrayAdapter);

        btInterest.setOnClickListener(v -> {
            Intent i = new Intent(LoginEnActivity.this, BlxNewActivity.class);
            startActivity(i);
        });
        btLocation.setOnClickListener(v -> {
            Intent i = new Intent(LoginEnActivity.this, LocationActivity.class);
            startActivity(i);
        });
        btNew.setOnClickListener(v -> {
            Intent i = new Intent(LoginEnActivity.this, NewActivity.class);
            startActivity(i);
        });
        btProduct.setOnClickListener(v -> {
            Intent i = new Intent(LoginEnActivity.this, ProductListActivity.class);
            i.putExtra(Constants.SCREEN_TYPE, Constants.FROM_LOGIN);
            startActivity(i);
        });
        btLoginStart.setOnClickListener(v -> {
            btLoginStart.setVisibility(View.GONE);
            llMainLogin.setVisibility(View.VISIBLE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtil.hideKeyboard(this);

        tvForgotPassword.setOnClickListener(v -> {
            Intent i = new Intent(LoginEnActivity.this, GetPassWordActivity.class);
            startActivity(i);
        });

        ivEye.setOnClickListener(v -> {
            if (!isShowPass) {
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

            } else {
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            int pos = etPassword.getText().length();
            etPassword.setSelection(pos);
            isShowPass = !isShowPass;
        });
        etUsernameEn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etUsernameEn.getText().length() == 0) {
                    ivClose.setVisibility(View.GONE);
                } else {
                    ivClose.setVisibility(View.VISIBLE);
                }
            }
        });
        ivClose.setOnClickListener(v -> {
            etUsernameEn.setText("");
            ivClose.setVisibility(View.GONE);
            etUsernameEn.requestFocus();
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_en;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        loginPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        loginPresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        HawkHelper.getInstance(this).putSwitchStatus(false);
        super.onBackPressed();
    }

    @OnClick(R.id.bt_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                if (!canLogin) {
                    tvError.setText("Đăng nhập tạm thời bị khóa");
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT);
                    new Handler().postDelayed(() -> alertDialog.dismiss(), 800);
                    return;
                }
                if (TextUtils.isEmpty(etUsernameEn.getText().toString())) {
                    tvError.setText("Chưa điền tên đăng nhập!");
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT);
                    new Handler().postDelayed(() -> alertDialog.dismiss(), 800);
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    tvError.setText("Chưa điền mật khẩu!");
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT);
                    new Handler().postDelayed(() -> alertDialog.dismiss(), 800);
                    return;
                }
                btLogin.setEnabled(false);
                new Handler().postDelayed(() -> btLogin.setEnabled(true), 1000);
                showLoading();
                loginPresenter.doLoginWithToken(this, etUsernameEn.getText().toString().trim(), etPassword.getText().toString(), firebaseToken);

                break;
        }
    }

    @Override
    public void onSuccessLogin(String authenToken) {
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        HawkHelper.getInstance(this).saveAuthorToken(authenToken);
//        FirebaseMessaging.getInstance().subscribeToTopic("article")
//                .addOnCompleteListener(task ->
//                {
//                    Timber.e("token" + task.getResult());
//                });
        btLogin.setEnabled(false);
        new Handler().postDelayed(() -> btLogin.setEnabled(true), 3500);
        Intent i = new Intent(LoginEnActivity.this, HomeActivity2.class);
        startActivity(i);
        overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
        finish();
        HawkHelper.getInstance(this).putLoginStatus(true);
//        loginPresenter.getEmployeeInfo(LoginEnActivity.this, authenToken);
    }

    @Override
    public void saveEmployeeInfo(EmployeeInfomation.DataBean dataBean) {
        HawkHelper.getInstance(this).saveEmployeeInfo(dataBean);
    }

    @Override
    public void showLockNoti() {
        countTime = 20;
        canLogin = false;
        Toast.makeText(this, "Đặng nhập bị khóa trong 20s ", Toast.LENGTH_SHORT).show();
        CountDownTimer w = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvCountDown.setText("Còn lại: " + millisUntilFinished / 1000 + " s");
            }

            @Override
            public void onFinish() {
                canLogin = true;
            }
        }.start();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (countTime != 0) {
//                    countTime -= 1;
//                    tvCountDown.setText("Còn lại: "+ countTime + " s");
//                } else {
//                    canLogin = true;
//                    tvCountDown.setVisibility(View.GONE);
//                }
//            }
//        }, 1000);
//    }
    }

    @Override
    public void showErrorMess(String mess) {
        tvError.setText(mess);
        alertDialog.show();
        alertDialog.getWindow().setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT);
        new Handler().postDelayed(() -> alertDialog.dismiss(), 800);
        return;
    }
}
