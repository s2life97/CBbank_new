package com.saleskit.cbbank.features.customer.enterprise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.MvpStarterApplication;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.CodeJson;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerEnterpriseRespond;
import com.saleskit.cbbank.features.database.DaoSession;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.database.OptionItem;
import com.saleskit.cbbank.features.database.OptionItemDao;
import com.saleskit.cbbank.features.database.OptionItemGroupName;
import com.saleskit.cbbank.features.detail.TransitionCodeActivity;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AddNewEnterpriseCustomerActivity extends BaseActivity {

    @BindView(R.id.tv_name_title)
    TextView tvNameTitle;
    @BindView(R.id.tv_enterprise_name)
    ClearableEditText tvEnterpriseName;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.sp_enterprise_type)
    Spinner spEnterpriseType;
    @BindView(R.id.ll_type_enterprise)
    LinearLayout llTypeEnterprise;
    @BindView(R.id.tv_identity_title)
    TextView tvIdentityTitle;
    @BindView(R.id.tv_enterprise_identity)
    ClearableEditText tvEnterpriseIdentity;
    @BindView(R.id.tv_check_cic)
    TextView tvCheckCic;
    @BindView(R.id.tv_provide_place)
    ClearableEditText tvProvidePlace;
    @BindView(R.id.tv_date_provide)
    TextView tvDateProvide;
    @BindView(R.id.bt_pick_provide)
    LinearLayout btPickProvide;
    @BindView(R.id.tv_location_title)
    TextView tvLocationTitle;
    @BindView(R.id.tv_enterprise_address)
    ClearableEditText tvEnterpriseAddress;
    @BindView(R.id.tv_phone)
    ClearableEditText tvPhone;
    @BindView(R.id.tv_email)
    ClearableEditText tvEmail;
    @BindView(R.id.et_current_address)
    ClearableEditText etCurrentAddress;
    @BindView(R.id.et_name_present_enterPrise)
    ClearableEditText etNamePresentEnterPrise;
    @BindView(R.id.et_position_represent)
    ClearableEditText etPositionRepresent;
    @BindView(R.id.et_identity_represent)
    ClearableEditText etIdentityRepresent;
    @BindView(R.id.et_phone_represent)
    ClearableEditText etPhoneRepresent;
    @BindView(R.id.et_email_represent)
    ClearableEditText etEmailRepresent;
    @BindView(R.id.et_name_manager)
    ClearableEditText etNameManager;
    @BindView(R.id.et_position_manager)
    ClearableEditText etPositionManager;
    @BindView(R.id.et_identity_manager)
    ClearableEditText etIdentityManager;
    @BindView(R.id.et_phone_manager)
    ClearableEditText etPhoneManager;
    @BindView(R.id.et_email_manager)
    ClearableEditText etEmailManager;
    @BindView(R.id.ll_option_enterprise)
    LinearLayout llOptionEnterprise;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.bt_complete)
    Button btComplete;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    private OptionItemDao optionItemDao;
    private int enterpriseTypeNumber = 1;
    private String token;
    private String customerCode;
    private String customerId = "";
    private int customerProfileId = 0;
    private String dateTime;
    private int productId;
    private TimePickerView pvPickDateProvide;
    private String dateProvide;
    private boolean isAddNew = false;
    private Call<CodeJson> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_enterprise_customer);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        token = HawkHelper.getInstance(this).getToken();
        DaoSession daoSession = ((MvpStarterApplication) this.getApplication()).getDaoSession();
        optionItemDao = daoSession.getOptionItemDao();
        try {
            customerId = intent.getStringExtra(Constants.CUSTOMER_ID);

        } catch (Exception e){
            customerId = "";
        }
        productId = intent.getIntExtra(Constants.PRODUCT_ID, 0);
        try {
            customerProfileId = Integer.parseInt(intent.getStringExtra(Constants.CUSTOMER_PROFILEID));
        } catch (Exception e) {
            customerProfileId = 0;
        }
        Timber.e("IDCustomer " + customerProfileId);
        customerCode = intent.getStringExtra(Constants.PROFILE_CODE);
        isAddNew = intent.getBooleanExtra(Constants.PRODUCT_EDIT, false);
        if (!isAddNew) {
            disableView();
        }
        if (!TextUtils.isEmpty(customerId)) {
            receiveData();
        }

        setupSpinner();
        setupPicker();


        setupUI();
    }

    private void receiveData() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("customerId", String.valueOf(customerId));
        options.put("page", String.valueOf(1));
        options.put("pagesize", "10");
        Call<CustomerInfo> call = apiInterface.getCustomerInfo(token, options);
        call.enqueue(new Callback<CustomerInfo>() {
            @Override
            public void onResponse(Call<CustomerInfo> call, Response<CustomerInfo> response) {
                hideLoading();
                if (response.code() == 200) {
                    CustomerInfo.DataBean dataBean = response.body().getData();
                    tvEnterpriseName.setText(dataBean.getEnterpriseName());
                    tvEnterpriseIdentity.setText(dataBean.getEnterpriseLicenseNumber());
                    tvProvidePlace.setText(dataBean.getEnterpriseLicenseIssuedBy());
                    dateTime = dataBean.getEnterpriseLicenseIssuedDate();
                    enterpriseTypeNumber = dataBean.getEnterpriseType();
                    String dateIssue = AppUtil.format(dataBean.getEnterpriseLicenseIssuedDate());
                    tvDateProvide.setText(dateIssue);
                    tvEnterpriseAddress.setText(dataBean.getEnterpriseLicenseAddress());
                    tvPhone.setText(dataBean.getEnterprisePhone());
                    tvEmail.setText(dataBean.getEnterpriseEmail());
                    etCurrentAddress.setText(dataBean.getEnterpriseAddress());
                    etPositionRepresent.setText(dataBean.getRepresentativePosition());
                    etIdentityRepresent.setText(dataBean.getIdentityNumber());
                    etPhoneRepresent.setText(dataBean.getPhoneNumber());
                    etNamePresentEnterPrise.setText(dataBean.getLastName() + " " + dataBean.getFirstName());
                    etEmailRepresent.setText(dataBean.getEmail());
                    etNameManager.setText(dataBean.getManagerName());
                    etPositionManager.setText(dataBean.getManagerPosition());
                    etIdentityManager.setText(dataBean.getManagerIdentityNumber());
                    etPhoneManager.setText(dataBean.getManagerPhone());
                    etEmailManager.setText(dataBean.getManagerEmail());
                    try {
                        int type = dataBean.getEnterpriseType();
                        spEnterpriseType.setSelection(type - 1);
                    } catch (Exception e) {

                    }

                } else {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerInfo> call, Throwable throwable) {
                hideLoading();
            }
        });
    }

    private void setupPicker() {
        pvPickDateProvide = new TimePickerBuilder(this, (date, view) -> {
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            dateProvide = dateFormatText.format(date);
            dateTime = dateProvide + "T" + "00:00:00.000" + "Z";
            tvDateProvide.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();
    }

    private void setupUI() {
        tvCheckCic.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.CIC_WEB));
            startActivity(browserIntent);
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btPickProvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.hideKeyboard(AddNewEnterpriseCustomerActivity.this);
                pvPickDateProvide.show();
            }
        });

        btComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = "";
                String lastName = "";
                String name = etNamePresentEnterPrise.getText().toString().trim();
                if (name.split("\\w+").length > 1) {
                    firstName = name.substring(name.lastIndexOf(" ") + 1);
                    lastName = name.substring(0, name.lastIndexOf(' '));
                } else {
                    lastName = name;
                }
                if (TextUtils.isEmpty(tvEnterpriseName.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Vui lòng nhập tên doanh nghiệp", Toast.LENGTH_SHORT).show();
                    tvEnterpriseName.requestFocus();
                    return;
                }
                if (enterpriseTypeNumber == 0) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Vui lòng chọn loại doanh nghiệp", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tvEnterpriseIdentity.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Vui lòng nhập số giấy phép", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvEnterpriseIdentity.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvProvidePlace.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Nơi cấp giấy phép không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvProvidePlace.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvDateProvide.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Ngày cấp giấy phép không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvDateProvide.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvEnterpriseAddress.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Địa chỉ CNĐKKD không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvEnterpriseAddress.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvPhone.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số điện thoại không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError(getResources().getString(R.string.file_must_not_empty));
                    tvPhone.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(tvPhone.getText().toString()).matches() || !tvPhone.getText().toString().startsWith("0")
                        || tvPhone.getText().length() < 9) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số điện thoại không đúng định dạng!", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError("Số điện thoại không đúng định dạng!");
                    tvPhone.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(tvEmail.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Địa chỉ email không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(tvEmail.getText().toString().trim()).matches()) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, getResources().getString(R.string.email_not_format), Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.email_not_format));
                    tvEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etCurrentAddress.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Địa chỉ giao dịch hiện tại không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etCurrentAddress.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etNamePresentEnterPrise.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Họ tên người đại diện không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etNamePresentEnterPrise.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etPositionRepresent.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Chức vụ người đại diện không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etPositionRepresent.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etIdentityRepresent.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số chứng minh thư không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etIdentityRepresent.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etPhoneRepresent.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số điện thoại không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError(getResources().getString(R.string.file_must_not_empty));
                    etPhoneRepresent.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(etPhoneRepresent.getText().toString()).matches() || !etPhoneRepresent.getText().toString().startsWith("0")
                        || etPhoneRepresent.getText().length() < 9) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số điện thoại không đúng định dạng!", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError("Số điện thoại không đúng định dạng!");
                    etPhoneRepresent.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(etEmailRepresent.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Địa chỉ email không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etEmailRepresent.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(etEmailRepresent.getText().toString().trim()).matches()) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, getResources().getString(R.string.email_not_format), Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.email_not_format));
                    etEmailRepresent.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etNameManager.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Họ tên người quản lý không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etNameManager.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etPositionManager.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Chức vụ của người quản lý không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etPositionManager.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etIdentityManager.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số CMND/CCCD không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etIdentityManager.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(etPhoneManager.getText().toString())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số điện thoại không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError(getResources().getString(R.string.file_must_not_empty));
                    etPhoneManager.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(etPhoneManager.getText().toString()).matches() || !etPhoneManager.getText().toString().startsWith("0")
                        || etPhoneManager.getText().length() < 9) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Số điện thoại không đúng định dạng!", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError("Số điện thoại không đúng định dạng!");
                    etPhoneManager.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(etEmailManager.getText().toString().trim())) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, "Địa chỉ email không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    etEmailManager.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(etEmailManager.getText().toString().trim()).matches()) {
                    Toast.makeText(AddNewEnterpriseCustomerActivity.this, getResources().getString(R.string.email_not_format), Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.email_not_format));
                    etEmailManager.requestFocus();
                    return;
                }
                APIWebservices apiInterface = NetworkUtil.getCBclient(AddNewEnterpriseCustomerActivity.this).create(APIWebservices.class);
                if ("".equals(customerId)) {
                    call = apiInterface.confirmStep1EnterPrise(token, new CustomerEnterpriseRespond(
                            new CustomerEnterpriseRespond.CustomerBean(2, firstName,
                                    lastName, customerCode, etIdentityRepresent.getText().toString()
                                    , etCurrentAddress.getText().toString(), etPhoneRepresent.getText().toString(),
                                    etEmailRepresent.getText().toString(), enterpriseTypeNumber, tvEnterpriseName.getText().toString(),
                                    tvEnterpriseIdentity.getText().toString(), dateTime,
                                    tvProvidePlace.getText().toString(),
                                    tvEnterpriseAddress.getText().toString(), tvPhone.getText().toString(),
                                    tvEmail.getText().toString(), etNameManager.getText().toString(),
                                    etPositionManager.getText().toString(), etPositionRepresent.getText().toString(),
                                    etIdentityManager.getText().toString(), etPhoneManager.getText().toString(), etEmailManager.getText().toString()
                            ),
                            new CustomerEnterpriseRespond.CustomerProfileCreditBean(customerProfileId, productId)
                    ));
                } else {
                    call = apiInterface.confirmStep1EnterPrise(token, new CustomerEnterpriseRespond(
                            new CustomerEnterpriseRespond.CustomerBean(customerId, 2, firstName,
                                    lastName, customerCode, etIdentityRepresent.getText().toString()
                                    , etCurrentAddress.getText().toString(), etPhoneRepresent.getText().toString(),
                                    etEmailRepresent.getText().toString(), enterpriseTypeNumber, tvEnterpriseName.getText().toString(),
                                    tvEnterpriseIdentity.getText().toString(), dateTime,
                                    tvProvidePlace.getText().toString(),
                                    tvEnterpriseAddress.getText().toString(), tvPhone.getText().toString(),
                                    tvEmail.getText().toString(), etNameManager.getText().toString(),
                                    etPositionManager.getText().toString(), etPositionRepresent.getText().toString(),
                                    etIdentityManager.getText().toString(), etPhoneManager.getText().toString(), etEmailManager.getText().toString()
                            ),
                            new CustomerEnterpriseRespond.CustomerProfileCreditBean(customerProfileId, productId, customerId)
                    ));
                }
                call.enqueue(new Callback<CodeJson>() {
                    @Override
                    public void onResponse(Call<CodeJson> call, Response<CodeJson> response) {
                        showLoading();
                        if (response.code() == 200) {
                            CodeJson.DataBeanX.DataBean dataBean = response.body().getData().getData();
                            Intent i = new Intent(AddNewEnterpriseCustomerActivity.this, TransitionCodeActivity.class);
                            i.putExtra(Constants.CODE, dataBean.getTradingCode());
                            startActivity(i);
                        } else {
                            try {
                                JSONArray jsonArray = new JSONArray(response.errorBody().string());
                                JSONObject json_object = (JSONObject) jsonArray.get(0);
                                String mess = json_object.getString("errorMessage");
                                Toast.makeText(AddNewEnterpriseCustomerActivity.this, mess, Toast.LENGTH_SHORT).show();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CodeJson> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void setupSpinner() {
        List<OptionItem> optionItemList = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.ENTERPRISE_TYPE))
                .list();
        Timber.e("size " + optionItemList.size());
        // fill the array
        String[] enterpriseType = new String[optionItemList.size()];
        // fill the array
        if (optionItemList.size() != 0) {
            for (int i = 0; i < optionItemList.size(); i++) {
                enterpriseType[i] = optionItemList.get(i).getDisplay();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, enterpriseType);
//        enterpriseType[0]= "";
//        if (optionItemList.size() != 0) {
//            for (int i =0 ; i < optionItemList.size(); i ++){
//                enterpriseType[i+1] = optionItemList.get(i).getDisplay();
//            }
//        }
//        MyArrayAdapter adapter = new MyArrayAdapter(context,
//                android.R.layout.simple_dropdown_item_1line, enterpriseType);
        spEnterpriseType.setAdapter(adapter);
        spEnterpriseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                enterpriseTypeNumber = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_new_enterprise_customer;
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

    private void disableView() {
        tvCheckCic.setVisibility(View.GONE);
        btComplete.setText("Hoàn thành");
        btComplete.setEnabled(false);
        tvEnterpriseName.setFocusable(false);
        tvPhone.setFocusable(false);
        tvEnterpriseAddress.setFocusable(false);
        tvEmail.setFocusable(false);
        tvPhone.setFocusable(false);
        tvEnterpriseIdentity.setFocusable(false);
        tvProvidePlace.setFocusable(false);
        btPickProvide.setEnabled(false);
        btPickProvide.setEnabled(false);
        etCurrentAddress.setFocusable(false);
        etPhoneManager.setFocusable(false);
        spEnterpriseType.setEnabled(false);
        etPhoneRepresent.setFocusable(false);
        etEmailRepresent.setFocusable(false);
        etEmailManager.setFocusable(false);
        etNamePresentEnterPrise.setFocusable(false);
        etNameManager.setFocusable(false);
        etPositionManager.setFocusable(false);
        etPositionRepresent.setFocusable(false);
        etIdentityManager.setFocusable(false);
        etIdentityRepresent.setFocusable(false);
    }
}
