package com.saleskit.cbbank.features.customer.add_new_customer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.MvpStarterApplication;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.CustomerInfoRespond;
import com.saleskit.cbbank.features.appointment.CategoryEvent;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.appointment.OptionActivity;
import com.saleskit.cbbank.features.base.BaseFragment;
import com.saleskit.cbbank.features.customer.CustomerAdapter;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;
import com.saleskit.cbbank.features.database.DaoSession;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.database.OptionItem;
import com.saleskit.cbbank.features.database.OptionItemDao;
import com.saleskit.cbbank.features.database.OptionItemGroupName;
import com.saleskit.cbbank.features.kpi.CustomerModel;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.injection.component.FragmentComponent;
import com.saleskit.cbbank.util.AppUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCustomerFragment extends BaseFragment implements OnItemClicklistener, AddCustomerView {
    private static final String TAG = "AddCustomerFragment";
    @BindView(R.id.tv_name_title)
    TextView tvNameTitle;
    @BindView(R.id.tv_name)
    ClearableEditText tvName;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.tv_birthday_title)
    TextView tvBirthdayTitle;
    @BindView(R.id.tv_date_birth)
    TextView tvDateBirth;
    @BindView(R.id.bt_pick_birthdate)
    LinearLayout btPickBirthdate;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.bt_gender)
    LinearLayout btGender;
    @BindView(R.id.tv_identity_title)
    TextView tvIdentityTitle;
    @BindView(R.id.tv_identity)
    ClearableEditText tvIdentity;
    @BindView(R.id.tv_check_cic)
    TextView tvCheckCic;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.bt_check_status)
    LinearLayout btCheckStatus;
    @BindView(R.id.tv_provide_place)
    ClearableEditText tvProvidePlace;
    @BindView(R.id.tv_date_provide)
    TextView tvDateProvide;
    @BindView(R.id.bt_pick_provide)
    LinearLayout btPickProvide;
    @BindView(R.id.tv_location_title)
    TextView tvLocationTitle;
    @BindView(R.id.tv_address)
    ClearableEditText tvAddress;
    @BindView(R.id.tv_phone)
    ClearableEditText tvPhone;
    @BindView(R.id.tv_email)
    ClearableEditText tvEmail;
    @BindView(R.id.tv_education)
    TextView tvEducation;
    @BindView(R.id.bt_pick_edcation)
    LinearLayout btPickEdcation;
    @BindView(R.id.tv_assert)
    TextView tvAssert;
    @BindView(R.id.bt_pick_assert)
    LinearLayout btPickAssert;
    @BindView(R.id.tv_martial)
    TextView tvMartial;
    @BindView(R.id.bt_pick_martial)
    LinearLayout btPickMartial;
    @BindView(R.id.tv_customer_type)
    TextView tvCustomerType;
    @BindView(R.id.bt_pick_customer_type)
    LinearLayout btPickCustomerType;
    @BindView(R.id.ll_option_personal)
    LinearLayout llOptionPersonal;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.bt_complete)
    Button btComplete;

    private boolean hasformular;

    private Context context;
    private CustomerAdapter customerAdapter;
    private List<CustomerModel> customerModelList = new ArrayList<>();
    private ArrayList<String> genderList = new ArrayList<>();
    private ArrayList<String> statusList = new ArrayList<>();
    private int education, assertStatus, martial;
    private int status = -1;
    private int customerType = 1;
    private String type;
    private int gender = -1;
    private View view;
    private TimePickerView pvPickDateBirth, pvPickDateProvide;
    private String birthDateText, birth;
    private String dateProvide, provide;
    private String token;
    private int productId;
    private int customerProfileId = 0;
    private CustomerInfo.DataBean customer;
    private boolean isAddNew;
    private int customerTypeProduct = 0;
    @Inject
    AddCustomerPresenter addCustomerPresenter;
    private boolean doneStep = false;
    private OptionItemDao optionItemDao;
    private int customerId = 0;
    public static boolean canNext = false;
    private String customerCode = "";
    private int enterpriseTypeNumber = 1;
    private boolean hasFormular;
//    String[] enterpriseType;

    public AddCustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        productId = getArguments().getInt(Constants.PRODUCT_ID);
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_add_customer, container, false);
        ButterKnife.bind(this, view);
        token = HawkHelper.getInstance(context).getToken();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        DaoSession daoSession = ((MvpStarterApplication) getActivity().getApplication()).getDaoSession();
        optionItemDao = daoSession.getOptionItemDao();

        setupUI();
        setupPicker();
        setupSpinner();
        genderList.add("Nam");
        genderList.add("Nữ");

        statusList.add("Đã kiểm tra");
        statusList.add("Chưa kiểm tra");
        return view;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, enterpriseType);
//        enterpriseType[0]= "";
//        if (optionItemList.size() != 0) {
//            for (int i =0 ; i < optionItemList.size(); i ++){
//                enterpriseType[i+1] = optionItemList.get(i).getDisplay();
//            }
//        }
//        MyArrayAdapter adapter = new MyArrayAdapter(context,
//                android.R.layout.simple_dropdown_item_1line, enterpriseType);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            EventBus.getDefault().post(new FragmentChangedEvent(1, "Thông tin khách hàng"));
        }
    }

    private void setupPicker() {
        pvPickDateBirth = new TimePickerBuilder(context, (date, view) -> {
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            birthDateText = dateFormatText.format(date);
            birth = birthDateText + "T" + "00:00:00.000" + "Z";
            tvDateBirth.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();

        pvPickDateProvide = new TimePickerBuilder(context, (date, view) -> {
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            dateProvide = dateFormatText.format(date);
            provide = dateProvide + "T" + "00:00:00.000" + "Z";
            tvDateProvide.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();

//        tvEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvEmail.setText(s.toString().trim());
//                    }
//                }, 2000);
//
//            }
//        });
    }

    private void setupUI() {
        tvCheckCic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.CIC_WEB));
                startActivity(browserIntent);
            }
        });

        btPickBirthdate.setOnClickListener(v -> {
            AppUtil.hideKeyboard(getActivity());
            pvPickDateBirth.show();
        });

        btPickProvide.setOnClickListener(v -> {
            AppUtil.hideKeyboard(getActivity());
            pvPickDateProvide.show();
        });

        btPickCustomerType.setOnClickListener(v -> {
            type = Constants.CUSTOMER_TYPE;
            Intent i = new Intent(context, OptionActivity.class);
            btPickCustomerType.setEnabled(false);
            new Handler().postDelayed(() -> btPickCustomerType.setEnabled(true), 1000);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.CUSTOMER_TYPE);
            i.putExtra(Constants.CATEGORY_VALUE, customerType);
            startActivity(i);
        });
        btGender.setOnClickListener(v -> {
            type = Constants.GENDER;
            AppUtil.getDialogwithID(getActivity(), genderList, getString(R.string.gender), this, 3).show();
        });

        btCheckStatus.setOnClickListener(v -> {
            type = Constants.STATUS;
            Intent i = new Intent(context, OptionActivity.class);
            btCheckStatus.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btCheckStatus.setEnabled(true);
                }
            }, 1000);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.CIC_STATUS);
            i.putExtra(Constants.CATEGORY_VALUE, status);
            startActivity(i);
        });


        btPickEdcation.setOnClickListener(v -> {
            type = Constants.EDUCATION;
            Intent i = new Intent(context, OptionActivity.class);
            btPickEdcation.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btPickEdcation.setEnabled(true);
                }
            }, 1000);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.EDUCATION);
            i.putExtra(Constants.CATEGORY_VALUE, education);
            startActivity(i);

        });

        btPickAssert.setOnClickListener(v -> {
            type = Constants.ASSERT;
            Intent i = new Intent(context, OptionActivity.class);
            btPickAssert.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btPickAssert.setEnabled(true);
                }
            }, 1000);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.ASSERT);
            i.putExtra(Constants.CATEGORY_VALUE, assertStatus);
            startActivity(i);

        });

        btPickMartial.setOnClickListener(v -> {
            type = Constants.MARTIAL;
            Intent i = new Intent(context, OptionActivity.class);
            btPickMartial.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btPickMartial.setEnabled(true);
                }
            }, 1000);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.MARTIAL);
            i.putExtra(Constants.CATEGORY_VALUE, martial);
            startActivity(i);

        });

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_add_customer;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        addCustomerPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        addCustomerPresenter.detachView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btComplete.setOnClickListener(view1 -> {
            if (!doneStep) {
                if (hasFormular) {
                    AddNewCustomerActivity.viewPager.setCurrentItem(1);
                } else {
                    AddNewCustomerActivity.viewPager.setCurrentItem(2);
                }
            } else {

                String lastName = "";
                String firstName = "";
                String name = tvName.getText().toString().trim();
                if (name.split("\\w+").length > 1) {
                    firstName = name.substring(name.lastIndexOf(" ") + 1);
                    lastName = name.substring(0, name.lastIndexOf(' '));
                } else {
                    lastName = name;
                }
                if (TextUtils.isEmpty(tvName.getText().toString().trim())) {
                    Toast.makeText(context, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                    tvName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvDateBirth.getText().toString())) {
                    Toast.makeText(context, "Ngày sinh không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvIdentity.setError(getResources().getString(R.string.identity_empty));
                    tvDateBirth.requestFocus();
                    return;
                }
                if (gender == -1) {
                    Toast.makeText(context, "Vui lòng nhập giới tính", Toast.LENGTH_SHORT).show();
//                    tvIdentity.setError(getResources().getString(R.string.identity_empty));
                    tvGender.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvIdentity.getText().toString())) {
                    Toast.makeText(context, "Số CMTND không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvIdentity.setError(getResources().getString(R.string.identity_empty));
                    tvIdentity.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvAddress.getText().toString())) {
                    Toast.makeText(context, "Địa chỉ không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvAddress.setError(getResources().getString(R.string.file_must_not_empty));
                    tvAddress.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvProvidePlace.getText().toString())) {
                    Toast.makeText(context, "Nơi cấp CMT không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvProvidePlace.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvDateProvide.getText().toString())) {
                    Toast.makeText(context, "Ngày cấp CMT không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvDateProvide.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tvPhone.getText().toString())) {
                    Toast.makeText(context, "Số điện thoại không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError(getResources().getString(R.string.file_must_not_empty));
                    tvPhone.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(tvPhone.getText().toString()).matches() || !tvPhone.getText().toString().startsWith("0")
                        || tvPhone.getText().length() < 9) {
                    Toast.makeText(context, "Số điện thoại không đúng định dạng!", Toast.LENGTH_SHORT).show();
//                    tvPhone.setError("Số điện thoại không đúng định dạng!");
                    tvPhone.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(tvEmail.getText().toString().trim())) {
                    Toast.makeText(context, "Địa chỉ email không được bỏ trống", Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
                    tvEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(tvEmail.getText().toString().trim()).matches()) {
                    Toast.makeText(context, getResources().getString(R.string.email_not_format), Toast.LENGTH_SHORT).show();
//                    tvEmail.setError(getResources().getString(R.string.email_not_format));
                    tvEmail.requestFocus();
                    return;
                }
                if (status == -1) {
                    Toast.makeText(context, "Vui lòng chọn trang thái CIC!", Toast.LENGTH_SHORT).show();
                    tvStatus.requestFocus();
                    return;
                }

                if (education == 0) {
                    Toast.makeText(context, "Vui lòng chọn trình độ học vấn", Toast.LENGTH_SHORT).show();
                    tvStatus.requestFocus();
                    return;
                }
                if (assertStatus == 0) {
                    Toast.makeText(context, "Vui lòng chọn tình trạng sở hữu nhà!", Toast.LENGTH_SHORT).show();
                    tvAssert.requestFocus();
                    return;
                }
                if (martial == 0) {
                    Toast.makeText(context, "Vui lòng chọn tình trạng hôn nhân!", Toast.LENGTH_SHORT).show();
                    tvMartial.requestFocus();
                    return;
                }
                if (customerType == 0) {
                    Toast.makeText(context, "Vui lòng chọn loại khách hàng!", Toast.LENGTH_SHORT).show();
                    tvStatus.requestFocus();
                    return;
                }

                addCustomerPresenter.confirmStep1(context, token, customerId, String.valueOf(customerType),
                        firstName, lastName,
                        customerCode, birth, String.valueOf(gender),
                        tvIdentity.getText().toString(), provide, tvProvidePlace.getText().toString(),
                        tvAddress.getText().toString(), tvPhone.getText().toString(), tvEmail.getText().toString().trim(),
                        String.valueOf(education), String.valueOf(assertStatus),
                        String.valueOf(martial), String.valueOf(status),
                        customerProfileId, productId, customerId
                );
//                if (isAddNew) {
//                    addCustomerPresenter.postNewCustomerProfile(context, token, 0, firstName, lastName,
//                            "", birth, String.valueOf(gender),
//                            tvIdentity.getText().toString(), provide, tvProvidePlace.getText().toString(),
//                            tvAddress.getText().toString(), tvPhone.getText().toString(), tvEmail.getText().toString(),
//                            String.valueOf(education), String.valueOf(assertStatus),
//                            String.valueOf(martial), String.valueOf(customerType), String.valueOf(status));
//                } else {
//                    addCustomerPresenter.updateCustomerProfile(context, token, customerId,
//                            firstName, lastName,
//                            "", birth, String.valueOf(gender), tvIdentity.getText().toString(),
//                            provide, tvProvidePlace.getText().toString(),
//                            tvAddress.getText().toString(),
//                            tvPhone.getText().toString(), tvEmail.getText().toString(),
//                            String.valueOf(education), String.valueOf(assertStatus),
//                            String.valueOf(martial), String.valueOf(customerType), String.valueOf(status));

//
//            }

            }
            ;
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onItemClick(int position) {
        gender = position + 1;
        tvGender.setText(genderList.get(position));
    }

    @Subscribe(sticky = true)
    public void onReceiveCustomer(TransferCustomerEvent transferCustomerEvent) {
        customerTypeProduct = transferCustomerEvent.customerType;

        if (transferCustomerEvent.customer != null) {
            isAddNew = false;
            showLoading();
            customer = transferCustomerEvent.customer;
            customerId = customer.getCustomerId();
            customerProfileId = transferCustomerEvent.customerProfileId;
            addCustomerPresenter.showCustomerInfo(context, token, customerId);

//            disableView();
        } else {
            isAddNew = true;
        }
        hasFormular = transferCustomerEvent.hasFormular;
        doneStep = transferCustomerEvent.canEdit;
        Log.d(TAG, "onReceiveCustomer: " + doneStep + " customer " + customer);
        if (!doneStep) {
            disableView();
        }
        productId = transferCustomerEvent.productId;
        if (!doneStep) {
            if (transferCustomerEvent.process == 1) {
                btComplete.setVisibility(View.GONE);
            }
        }

    }

    private void disableView() {
        tvName.setFocusable(false);
        tvPhone.setFocusable(false);
        tvAddress.setFocusable(false);
        tvEmail.setFocusable(false);
        tvPhone.setFocusable(false);
        tvIdentity.setFocusable(false);
        tvProvidePlace.setFocusable(false);
        btPickProvide.setEnabled(false);
        btPickBirthdate.setEnabled(false);
        btPickMartial.setEnabled(false);
        btPickEdcation.setEnabled(false);
        btCheckStatus.setEnabled(false);
        btPickAssert.setEnabled(false);
        btGender.setEnabled(false);
        btPickCustomerType.setEnabled(false);
    }

    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        switch (categoryEvent.category) {
            case Constants.EDUCATION:
                education = categoryEvent.id;
                tvEducation.setText(categoryEvent.value);
                break;
            case Constants.ASSERT:
                assertStatus = categoryEvent.id;
                tvAssert.setText(categoryEvent.value);
                break;
            case Constants.MARTIAL:
                martial = categoryEvent.id;
                tvMartial.setText(categoryEvent.value);
                break;
            case Constants.CUSTOMER_TYPE:
                customerType = categoryEvent.id;
                tvCustomerType.setText(categoryEvent.value);
                break;
            case Constants.CIC_STATUS:
                status = categoryEvent.id;
                tvStatus.setText(categoryEvent.value);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showCustomerInfomation(CustomerInfo.DataBean dataBean) {
        tvName.setText(dataBean.getLastName() + " " + dataBean.getFirstName());
        String dateOfbirth = AppUtil.format(dataBean.getDateOfBirth());
        String dateProvide = AppUtil.format(dataBean.getIssuedDate());
        tvDateBirth.setText(dateOfbirth);
        tvIdentity.setText(dataBean.getIdentityNumber());
        tvAddress.setText(dataBean.getAddress());
        tvEmail.setText(dataBean.getEmail());
        tvPhone.setText(dataBean.getPhoneNumber());
        tvProvidePlace.setText(dataBean.getIssuedBy());
        birth = dataBean.getDateOfBirth();
        provide = dataBean.getIssuedDate();
        status = dataBean.getCicResult();
        List<OptionItem> optionItemsCicStatus = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.CIC_STATUS),
                        OptionItemDao.Properties.Value.eq(dataBean.getCicResult()))
                .list();
        if (optionItemsCicStatus.size() != 0) {
            tvStatus.setText(optionItemsCicStatus.get(0).getDisplay());
        }

        tvDateProvide.setText(dateProvide);
        List<OptionItem> optionItemsGenders = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.GENDER),
                        OptionItemDao.Properties.Value.eq(dataBean.getGender()))
                .list();

        gender = dataBean.getGender();
        if (optionItemsGenders.size() != 0) {
            tvGender.setText(optionItemsGenders.get(0).getDisplay());
        }
        customerType = dataBean.getCustomerType();
        if (customerType == 0) {
            customerType = 1;
        } else {
            List<OptionItem> optionItemsCustomerType = optionItemDao.queryBuilder()
                    .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.CUSTOMER_TYPE),
                            OptionItemDao.Properties.Value.eq(dataBean.getCustomerType()))
                    .list();
            if (optionItemsCustomerType.size() != 0) {
                tvCustomerType.setText(optionItemsCustomerType.get(0).getDisplay());

            }
        }

        customerCode = dataBean.getCustomerCode();
        martial = dataBean.getMaritalStatus();
        List<OptionItem> optionItemsMarrieds = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.MARRIED_STATUS),
                        OptionItemDao.Properties.Value.eq(dataBean.getMaritalStatus()))
                .list();
        if (optionItemsMarrieds.size() != 0) {
            tvMartial.setText(optionItemsMarrieds.get(0).getDisplay());
        }

        List<OptionItem> optionItemsEducations = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.EDUCATION_STATUS),
                        OptionItemDao.Properties.Value.eq(dataBean.getEducationStatus()))
                .list();
        if (optionItemsEducations.size() != 0) {
            tvEducation.setText(optionItemsEducations.get(0).getDisplay());
        }

        education = dataBean.getEducationStatus();
        List<OptionItem> optionItemsAssets = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.ASSET_STATUS),
                        OptionItemDao.Properties.Value.eq(dataBean.getAssetStatus()))
                .list();
        if (optionItemsAssets.size() != 0) {
            tvAssert.setText(optionItemsAssets.get(0).getDisplay());
        }

        assertStatus = dataBean.getAssetStatus();

//            ArrayAdapter myAdap = (ArrayAdapter) spEnterPriseType.getAdapter(); //cast to an ArrayAdapter
//
//            int spinnerPosition = myAdap.getPosition(myString);
//
////set the default according to value
//            spEnterPriseType.setSelection(spinnerPosition);
    }


    @Override
    public void showResultData(String result) {
//        addCustomerPresenter.insertnewProfile(context, token, result, String.valueOf(productId));

//        btComplete.setEnabled(false);
    }

    @Override
    public void intentNextStep() {
//        addCustomerPresenter.insertnewProfile(context, token, String.valueOf(customerId), String.valueOf(productId));

//        btComplete.setEnabled(false);
    }

    @Override
    public void showProfileCustomerID(CustomerInfoRespond.DataBeanX.DataBean data) {
        customerId = data.getCustomerId();
        customerProfileId = data.getCustomerProfileId();
        Log.d(TAG, "showProfileCustomerID: " + data.getCustomerProfileId());
        EventBus.getDefault().post(new ResultEvent(String.valueOf(data.getCustomerProfileId()), assertStatus, education,
                tvDateBirth.getText().toString(),
                tvName.getText().toString(), String.valueOf(productId), String.valueOf(data.getCustomerId())));

        if (hasFormular) {
            AddNewCustomerActivity.viewPager.setCurrentItem(1);
        } else {
            AddNewCustomerActivity.viewPager.setCurrentItem(2);
        }
//        doneStep = true;
        canNext = true;
//        disableView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        canNext = false;
    }
}
