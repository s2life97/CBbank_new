package com.saleskit.cbbank.features.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.MvpStarterApplication;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.CodeJson;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.account.MobilizeEnum;
import com.saleskit.cbbank.features.appointment.CategoryEvent;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.appointment.OptionActivity;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerProfile;
import com.saleskit.cbbank.features.customer.add_new_customer.model.InsertMobilize;
import com.saleskit.cbbank.features.database.DaoSession;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.database.OptionItem;
import com.saleskit.cbbank.features.database.OptionItemDao;
import com.saleskit.cbbank.features.database.OptionItemGroupName;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HDCustomerDetailActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.et_name)
    ClearableEditText etName;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.et_identity)
    ClearableEditText etIdentity;
    @BindView(R.id.et_address)
    ClearableEditText etAddress;
    @BindView(R.id.et_phone)
    ClearableEditText etPhone;
    @BindView(R.id.et_email)
    ClearableEditText etEmail;
    @BindView(R.id.et_money_lend)
    ClearableEditText etMoneyLend;
    @BindView(R.id.et_pick_limit)
    ClearableEditText etPickLimit;
    @BindView(R.id.bt_pick_edcation)
    LinearLayout btPickEdcation;
    @BindView(R.id.et_pick_rate)
    ClearableEditText etPickRate;
    @BindView(R.id.bt_pick_interest)
    LinearLayout btPickInterest;
    @BindView(R.id.tv_time_meeting)
    TextView tvTimeMeeting;
    @BindView(R.id.bt_pick_date)
    LinearLayout btPickDate;
    @BindView(R.id.tv_root)
    TextView tvRoot;
    @BindView(R.id.bt_pick_root)
    LinearLayout btPickRoot;
    @BindView(R.id.tv_room)
    TextView tvRoom;
    @BindView(R.id.bt_pick_room)
    LinearLayout btPickRoom;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.bt_complete)
    Button btComplete;
    @BindView(R.id.iv_branch)
    ImageView ivBranch;
    @BindView(R.id.iv_department)
    ImageView ivDepartment;
    private String token;
    private String customerId;
    private int productId;
    private String timeNew;
    private TimePickerView pvPickDateBirth;
    private String time;
    private CustomerInfo.DataBean customer;
    private boolean isAddnew;
    private int customerType = 0;
    private int root = -1;
    private int room = -1;
    private String rootId = "";
    private String roomId = "";
    private String customerCode, customerProfileCode;
    private String dateOfBirth, issueDate, issueBy = "";
    private int gender, education, assertStatus, martial, cicResult = 0;
    private int customerProfileId;
    private int status;
    private boolean canEdit = true;
    private boolean fromNoti = false;
    private boolean isRead = true;
    private String notificationId;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_hdcustomer_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        token = HawkHelper.getInstance(this).getToken();
        etMoneyLend.addTextChangedListener(AppUtil.onTextChangedListener(etMoneyLend));
//        getListCateGory();
        btnBack.setOnClickListener(v -> onBackPressed());
        setupView();
        Intent intent = getIntent();
        fromNoti = intent.getBooleanExtra(Constants.SCREEN_TYPE, false);
        if (fromNoti) {
            disabaleView();
            disableButton();
            customerProfileId = intent.getIntExtra(Constants.CUSTOMER_PROFILEID, -1);
            isRead = intent.getBooleanExtra(Constants.READ_NOTI, true);
            if (!isRead) {
                notificationId = intent.getStringExtra(Constants.NOTIFICATION_ID);
                readNoti(Integer.valueOf(notificationId));
            }
            getDetailbyID(customerProfileId);

        } else {
            canEdit = intent.getBooleanExtra(Constants.PRODUCT_EDIT, true);
            customer = (CustomerInfo.DataBean) intent.getSerializableExtra(Constants.CUSTOMER_ID);
            productId = intent.getIntExtra(Constants.PRODUCT_ID, -1);
            customerProfileCode = intent.getStringExtra(Constants.PROFILE_CODE);
            customerProfileId = intent.getIntExtra(Constants.CUSTOMER_PROFILEID, -1);
            status = intent.getIntExtra(Constants.PRODUCT_STATUS, -1);

            Timber.e("Prodasdp" + productId + " cus  " + customerProfileCode);
            if (customer != null) {
                etName.setText(customer.getLastName() + " " + customer.getFirstName());
                etIdentity.setText(customer.getIdentityNumber());
                etAddress.setText(customer.getAddress());
                etPhone.setText(customer.getPhoneNumber());
                etEmail.setText(customer.getEmail());
                isAddnew = false;
                disabaleView();
                customerId = String.valueOf(customer.getCustomerId());
            } else {
                isAddnew = true;
                customerId = null;
            }
            if (!canEdit) {
                getDetailbyID(customerProfileId);
                disabaleView();
                disableButton();

            }
        }

        btComplete.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String lastName = "";
            String firstName = "";
            if (name.split("\\w+").length > 1) {
                firstName = name.substring(name.lastIndexOf(" ") + 1);
                lastName = name.substring(0, name.lastIndexOf(' '));
            } else {
                lastName = name;
            }
            if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                Toast.makeText(this, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
//                etName.setError("Vui lòng nhập họ và tên đệm");
                etName.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etIdentity.getText().toString())) {
//                etIdentity.setError(getResources().getString(R.string.identity_empty));
                Toast.makeText(this, getResources().getString(R.string.identity_empty), Toast.LENGTH_SHORT).show();
                etIdentity.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etAddress.getText().toString())) {
                Toast.makeText(this, "Địa chỉ không được bỏ trống", Toast.LENGTH_SHORT).show();
                etAddress.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etPhone.getText().toString())) {
                Toast.makeText(this, "Số điện thoại không được bỏ trống", Toast.LENGTH_SHORT).show();
//                etPhone.setError(getResources().getString(R.string.file_must_not_empty));
                etPhone.requestFocus();
                return;
            }
            if (!Patterns.PHONE.matcher(etPhone.getText().toString()).matches() || !etPhone.getText().toString().startsWith("0")
                    || etPhone.getText().length() < 9) {
                Toast.makeText(this, "Số điện thoại không đúng định dạng!", Toast.LENGTH_SHORT).show();
                etPhone.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etEmail.getText().toString().trim())) {
                Toast.makeText(this, "Địa chỉ email không được bỏ trống", Toast.LENGTH_SHORT).show();
//                etEmail.setError(getResources().getString(R.string.file_must_not_empty));
                etEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
                Toast.makeText(this, getResources().getString(R.string.email_not_format), Toast.LENGTH_SHORT).show();
                etEmail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etMoneyLend.getText().toString())) {
//                etMoneyLend.setError(getResources().getString(R.string.file_must_not_empty));
                Toast.makeText(this, "Số tiền gửi không được bỏ trống", Toast.LENGTH_SHORT).show();
                etMoneyLend.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etPickLimit.getText().toString())) {
                Toast.makeText(this, "Vui lòng nhập kỳ hạn", Toast.LENGTH_SHORT).show();
//                etEmail.setError(getResources().getString(R.string.file_must_not_empty));
                etPickLimit.requestFocus();
                return;
            }
//            if (Integer.parseInt(etPickLimit.getText().toString().replace(",", "")) > 100) {
//                Toast.makeText(this, getResources().getString(R.string.rate_over_limit), Toast.LENGTH_SHORT).show();
////                etPickLimit.setError(getResources().getString(R.string.rate_over_limit));
//                etPickLimit.requestFocus();
//                return;
//            }
            if (TextUtils.isEmpty(etPickRate.getText().toString())) {
                Toast.makeText(this, "Vui lòng nhập lãi suất", Toast.LENGTH_SHORT).show();
//                etPickRate.setError(getResources().getString(R.string.file_must_not_empty));
                etPickRate.requestFocus();
                return;
            }
            Timber.e("rate" + etPickRate.getText().toString());
            String rate = etPickRate.getText().toString();
            if (rate.contains(".")) {
                String rateText = rate.trim().split("\\.")[0];
                try {
                    if (Integer.parseInt(rateText) > 100) {
                        Toast.makeText(this, getResources().getString(R.string.interest_over_limit), Toast.LENGTH_SHORT).show();
                        etPickRate.requestFocus();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(this, getResources().getString(R.string.interest_over_limit), Toast.LENGTH_SHORT).show();
                    etPickRate.requestFocus();
                    return;
                }
            } else {
                if (Integer.parseInt(etPickRate.getText().toString()) > 100) {
                    Toast.makeText(this, getResources().getString(R.string.interest_over_limit), Toast.LENGTH_SHORT).show();
                    etPickRate.requestFocus();
                    return;
                }
            }
            if (TextUtils.isEmpty(tvTimeMeeting.getText().toString())) {
                Toast.makeText(this, "Vui lòng chọn ngày giao dịch", Toast.LENGTH_SHORT).show();
//                etPickRate.setError(getResources().getString(R.string.file_must_not_empty));
                tvTimeMeeting.requestFocus();
                return;
            }
            Date currentDateFormat = Calendar.getInstance().getTime();
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = dateFormatText.format(currentDateFormat);
            if (!AppUtil.compareBiggerDate(startDate, timeNew)) {
                Toast.makeText(this, "Ngày hẹn phải sau ngày hiện tại!", Toast.LENGTH_SHORT).show();
                return;
            }


            String moneyLend = etMoneyLend.getText().toString().replace(",", "");
            showLoading();
            APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
            Call<CodeJson> call = apiInterface.confirmMobilize(token, new InsertMobilize(new InsertMobilize.CustomerBean(customerId,
                    "0", firstName, lastName, null, null, etIdentity.getText().toString(),
                    null, null, etAddress.getText().toString(), etPhone.getText().toString(),
                    etEmail.getText().toString().trim()),
                    new InsertMobilize.CustomerProfileMobilizeBean(-1 == customerProfileId ? null : String.valueOf(customerProfileId),
                            customerProfileCode, productId,
                            customerId, moneyLend, etPickLimit.getText().toString(),
                            etPickRate.getText().toString().trim(), time, roomId, rootId
                    )));
            call.enqueue(new Callback<CodeJson>() {
                @Override
                public void onResponse(Call<CodeJson> call, Response<CodeJson> response) {
                    hideLoading();
                    if (response.code() == 200) {
                        Intent i = new Intent(HDCustomerDetailActivity.this, TransitionCodeActivity.class);
                        i.putExtra(Constants.CODE, response.body().getData().getData().getTradingCode());
                        startActivity(i);
                        overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
                    } else {
                        try {
                            JSONArray jsonArray = new JSONArray(response.errorBody().string());
                            JSONObject json_object = (JSONObject) jsonArray.get(0);
                            String mess = json_object.getString("errorMessage");
                            Toast.makeText(HDCustomerDetailActivity.this, mess, Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                            Timber.e(" casd " + e.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CodeJson> call, Throwable t) {

                }
            });
        });
    }

    private void readNoti(int id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<MessageBean> call = apiInterface.readNoti(token, id);
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.code() == 200) {
                } else {
                    Toast.makeText(HDCustomerDetailActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });
    }

    private void disableButton() {
        ivBranch.setVisibility(View.GONE);
        ivDepartment.setVisibility(View.GONE);
        etPickLimit.setFocusable(false);
        etPickRate.setFocusable(false);
        etMoneyLend.setFocusable(false);
        btComplete.setVisibility(View.GONE);
        btPickDate.setEnabled(false);
        btPickRoom.setEnabled(false);
        btPickRoot.setEnabled(false);
    }

    private void getDetailbyID(int customerProfileId) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<CustomerProfile> call = apiInterface.getCustomerProfie(token, String.valueOf(customerProfileId));
        call.enqueue(new Callback<CustomerProfile>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CustomerProfile> call, Response<CustomerProfile> response) {
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null) {
                        String firstName = "";
                        CustomerProfile.DataBean dataBean = response.body().getData();
                        if (dataBean.getCustomerFirstName() == null || TextUtils.isEmpty(dataBean.getCustomerFirstName())) {
                            firstName = "";
                        } else {
                            firstName = dataBean.getCustomerFirstName();
                        }
                        etName.setText(dataBean.getCustomerLastName() + " " + firstName);
                        etIdentity.setText(dataBean.getCustomerIdentityNumber());
                        etAddress.setText(dataBean.getAddress());
                        etPhone.setText(dataBean.getCustomerPhoneNumber());
                        etEmail.setText(dataBean.getCustomerEmail());
                        rootId = dataBean.getBranchId();
                        roomId = dataBean.getDepartmentId();
                        tvRoom.setText(dataBean.getDepartmentName());
                        tvRoot.setText(dataBean.getBranchName());
                        time = dataBean.getAppointmentTransactionDate();
                        String showTime = AppUtil.format(time);
                        tvTimeMeeting.setText(showTime);
                        etPickLimit.setText(String.valueOf(dataBean.getPeriodTime()));
                        etMoneyLend.setText(NumberFormat.getInstance().format(dataBean.getMoney()));
                        etPickRate.setText(NumberFormat.getInstance().format(dataBean.getInterest()));
                    } else {
                        Toast.makeText(HDCustomerDetailActivity.this, "không tìm thấy thông tin hồ sơ!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerProfile> call, Throwable t) {

            }
        });
    }
//            if (isAddnew) {

//                Call<MessageBean> call = apiInterface.postNewCustomer(token,
//                        new CustomerPostProfile(0, firstName, lastName, "", null, null
//                                , etIdentity.getText().toString()
//                                , null, null, etAddress.getText().toString(),
//                                etPhone.getText().toString(), etEmail.getText().toString(),
//                                null, null, null, null, null));
//                call.enqueue(new Callback<MessageBean>() {
//                    @Override
//                    public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
//                        if (response.code() == 400) {
//                            hideLoading();
//                            Toast.makeText(HDCustomerDetailActivity.this, "Số CMND/Hộ chiếu đã tồn tại trong hệ thống!", Toast.LENGTH_SHORT).show();
//                        } else if (response.code() == 200) {
//                            apiInterface.postCustomerProfileMobilize(token, new InsertMobilize(
//                                    0,
//                                    null,
//                                    productId, // chs productId null '_'
//                                    response.body().getData(),
//                                    moneyLend,
//                                    etPickLimit.getText().toString(),
//                                    etPickRate.getText().toString(),
//                                    time,
//                                    room))
//                                    .enqueue(new Callback<MessageBean>() {
//                                        @Override
//                                        public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
//                                            hideLoading();
//                                            if (response.code() == 200) {
//                                                Intent i = new Intent(HDCustomerDetailActivity.this, TransitionCodeActivity.class);
//                                                i.putExtra(Constants.CODE, response.body().getData());
//                                                startActivity(i);
//                                                overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//                                            } else if (response.code() == 400) {
//                                                try {
//                                                    JSONArray jsonArray = new JSONArray(response.errorBody().string());
//                                                    JSONObject json_object = (JSONObject) jsonArray.get(0);
//                                                    String mess = json_object.getString("errorMessage");
//                                                    Toast.makeText(HDCustomerDetailActivity.this, mess, Toast.LENGTH_SHORT).show();
//                                                } catch (JSONException | IOException e) {
//                                                    e.printStackTrace();
//                                                    Timber.e(" casd " + e.getMessage());
//                                                }
//                                            } else {
//                                                Toast.makeText(HDCustomerDetailActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onFailure(Call<MessageBean> call, Throwable t) {
//
//                                        }
//                                    });
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MessageBean> call, Throwable t) {
//
//                    }
//                });

//                apiInterface.postCustomerProfileMobilize(token, new InsertMobilize(
//                        0,
//                        null,
//                        productId, // chs productId null '_'
//                        String.valueOf(customerId),
//                        moneyLend,
//                        etPickLimit.getText().toString(),
//                        etPickRate.getText().toString(),
//                        time,
//                        room))
//                        .enqueue(new Callback<MessageBean>() {
//                            @Override
//                            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
//                                hideLoading();
//                                if (response.code() == 200) {
//                                    Intent i = new Intent(HDCustomerDetailActivity.this, TransitionCodeActivity.class);
//                                    i.putExtra(Constants.CODE, response.body().getData());
//                                    startActivity(i);
//                                    overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//                                } else if (response.code() == 400) {
//                                    try {
//                                        JSONArray jsonArray = new JSONArray(response.errorBody().string());
//                                        JSONObject json_object = (JSONObject) jsonArray.get(0);
//                                        String mess = json_object.getString("errorMessage");
//                                        Toast.makeText(HDCustomerDetailActivity.this, mess, Toast.LENGTH_SHORT).show();
//                                    } catch (JSONException | IOException e) {
//                                        e.printStackTrace();
//                                        Timber.e(" casd " + e.getMessage());
//                                    }
//                                } else {
//                                    Toast.makeText(HDCustomerDetailActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<MessageBean> call, Throwable t) {
//
//                            }
//                        });
//
//            }
//
//        });


    private void disabaleView() {
        etName.setFocusable(false);
        etPhone.setFocusable(false);
        etAddress.setFocusable(false);
        etIdentity.setFocusable(false);
        etEmail.setFocusable(false);
    }

    private void setupView() {
        pvPickDateBirth = new TimePickerBuilder(this, (date, view) -> {
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            timeNew = dateFormatText.format(date);
            time = timeNew + "T" + "00:00:00.000" + "Z";
            tvTimeMeeting.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();
        btPickDate.setOnClickListener(v -> {
            AppUtil.hideKeyboard(HDCustomerDetailActivity.this);
            pvPickDateBirth.show();
        });
        btPickRoot.setOnClickListener(v -> {
            Intent i = new Intent(HDCustomerDetailActivity.this, OptionActivity.class);
            btPickRoot.setEnabled(false);
            new Handler().postDelayed(() -> btPickRoot.setEnabled(true), 1000);
            i.putExtra(Constants.CATEGORY_TYPE, OptionItemGroupName.BANK_STRUCTURES);
            i.putExtra(Constants.CATEGORY_VALUE, root);
            startActivity(i);
        });

        btPickRoom.setOnClickListener(v -> {
            if (TextUtils.isEmpty(rootId)) {
                Toast.makeText(HDCustomerDetailActivity.this, "Vui lòng chọn chi nhánh trước khi chọn phòng GD!", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(HDCustomerDetailActivity.this, OptionActivity.class);
                btPickRoom.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btPickRoom.setEnabled(true);
                    }
                }, 1000);
                i.putExtra(Constants.CATEGORY_TYPE, OptionItemGroupName.DEPARTMENTS);
                i.putExtra(Constants.CATEGORY_VALUE, room);
                i.putExtra(Constants.ROOT_VALUE, rootId);
                startActivity(i);
            }
        });
    }

    private void getListCateGory() {
        DaoSession daoSession = ((MvpStarterApplication) getApplication()).getDaoSession();
        OptionItemDao optionItemDao = daoSession.getOptionItemDao();

        List<OptionItem> optionItemsBankStructures = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.BANK_STRUCTURES))
                .list();

        if (optionItemsBankStructures.size() != 0) return;

        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<MobilizeEnum> call = apiInterface.getMobilizeEnums(token);
        call.enqueue(new Callback<MobilizeEnum>() {
            @Override
            public void onResponse(Call<MobilizeEnum> call, Response<MobilizeEnum> response) {
                hideLoading();
                if (response.code() == 200) {
                    List<MobilizeEnum.DataBean.CbBankStructuresBean> structuresBeanslist = response.body().getData().getCbBankStructures();
                    List<MobilizeEnum.DataBean.DepartmentsBean> departmentsBeanList = response.body().getData().getDepartments();

                    for (MobilizeEnum.DataBean.CbBankStructuresBean cbBankStructuresBean : structuresBeanslist) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.BANK_STRUCTURES,
                                cbBankStructuresBean.getValueMember(), cbBankStructuresBean.getDisplayMember()));
                    }

                    for (MobilizeEnum.DataBean.DepartmentsBean departmentsBean : departmentsBeanList) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.DEPARTMENTS, departmentsBean.getValueMember(),
                                departmentsBean.getDisplayMember(), departmentsBean.getValueCategory()));
                    }
                }
            }

            @Override
            public void onFailure(Call<MobilizeEnum> call, Throwable t) {

            }
        });

    }

    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        switch (categoryEvent.category) {
            case OptionItemGroupName.BANK_STRUCTURES:
                rootId = categoryEvent.categoryId;
                tvRoot.setText(categoryEvent.value);
                tvRoom.setText(getResources().getString(R.string.pick_transroom));
                roomId = "";
                break;
            case OptionItemGroupName.DEPARTMENTS:
                roomId = categoryEvent.categoryId;
                tvRoom.setText(categoryEvent.value);
                break;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_hdcustomer_detail;
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
}
