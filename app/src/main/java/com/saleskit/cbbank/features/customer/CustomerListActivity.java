package com.saleskit.cbbank.features.customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.MvpStarterApplication;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.appointment.AddApointmentActivity;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.add_new_customer.AddNewCustomerActivity;
import com.saleskit.cbbank.features.customer.add_new_customer.model.Category;
import com.saleskit.cbbank.features.customer.enterprise.AddNewEnterpriseCustomerActivity;
import com.saleskit.cbbank.features.customer.presenter.CustomerPresenter;
import com.saleskit.cbbank.features.customer.presenter.CustomerView;
import com.saleskit.cbbank.features.database.DaoSession;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.database.OptionItem;
import com.saleskit.cbbank.features.database.OptionItemDao;
import com.saleskit.cbbank.features.database.OptionItemGroupName;
import com.saleskit.cbbank.features.detail.HDCustomerDetailActivity;
import com.saleskit.cbbank.features.kpi.CustomerModel;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.features.personal.PersonalActivity;
import com.saleskit.cbbank.features.personal.ReworkActivity;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Customer;
import com.saleskit.cbbank.util.rx.netmodel.FilterModel;
import com.saleskit.cbbank.util.rx.netmodel.Token;
import com.google.android.material.appbar.AppBarLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class CustomerListActivity extends BaseActivity implements OnItemClicklistener, CustomerView, OnReworkClick {
    private static final String TAG = "CustomerListActivity";
    @Inject
    CustomerPresenter customerPresenter;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.appbar_main)
    AppBarLayout appbarMain;
    @BindView(R.id.rv_customer)
    RecyclerView rvCustomer;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.bt_add_new)
    Button btAddNew;
    @BindView(R.id.bt_re_arrange)
    Button btReArrange;
    @BindView(R.id.fl_end)
    FrameLayout flEnd;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    @BindView(R.id.tv_result)
    TextView tvResult;

    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_choose)
    TextView tvChoose;
    @BindView(R.id.tv_choose_all)
    TextView tvChooseAll;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private int typeProduct;
    private boolean enableCheckBox;
    private List<String> chosenIds = new ArrayList<>();

    private boolean isEdit;
    String token = "";
    int page = 1;
    int totalPages;
    private int customerTypeProduct = -1;
    private Token.DataBean userToken;
    private String userId;
    private List<CustomerModel> customerModels = new ArrayList<>();
    private CustomerAdapter customerAdapter = null;
    private List<FilterModel.FilterModelsBean> filterModelsBeans = new ArrayList<>();
    private List<Customer.DataBean> originCustomerList = new ArrayList<>();
    private int productID = -1;
    private boolean isFromAppoint = false;
    private boolean isFromHD = false;
    private Customer.DataBean customer;
    private String type;
    private boolean isFromHome = false;
    private boolean hasFormular;
    private boolean isRework = false;
    private String fullName;
    private String departmentId;
    private boolean canRework = false;
    private List<Customer.DataBean> searchCustomers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        ButterKnife.bind(this);
        getCategoryData();

        Intent intent = getIntent();
        if (intent != null) {
            productID = intent.getIntExtra(Constants.PRODUCT_ID, -1);
            type = intent.getStringExtra(Constants.CUSTOMER_TYPE);

            hasFormular = intent.getBooleanExtra(Constants.PRODUCT_FORMUALR, true);
            switch (type) {
                case Constants.CUSTOMER_APPOINT:
                    isFromAppoint = true;
                    isEdit = false;
                    customer = (Customer.DataBean) intent.getSerializableExtra(Constants.CUSTOMER_ID);
                    btAddNew.setVisibility(View.VISIBLE);
                    userId = HawkHelper.getInstance(this).getTokenModel().getUserName();
                    btAddNew.setText("Thêm mới cuộc hẹn");
                    btAddNew.setOnClickListener(v -> {
                        Intent i = new Intent(this, AddApointmentActivity.class);
                        i.putExtra(Constants.CUSTOMER_ID, customer);
                        i.putExtra(Constants.SEE_DETAIL, "");
                        startActivity(i);
                        finish();
                    });
                    break;
                case Constants.CUSTOMER_HOME:
                    btAddNew.setVisibility(View.GONE);
                    isEdit = false;
                    isFromHome = true;
                    fullName = intent.getStringExtra(Constants.USER_FULL_NAME);
                    canRework = intent.getBooleanExtra(Constants.CAN_REWORK, false);
                    departmentId = intent.getStringExtra(Constants.DEPARTMENT_ID);
                    userId = intent.getStringExtra(Constants.USER_NAME);
//                    fullName = HawkHelper.getInstance(this).getEmployeeInfo().getFullName();
//                    departmentId = HawkHelper.getInstance(this).getEmployeeInfo().getPosition().get(0).getDepartmentId();
                    break;
                case Constants.CUSTOMER_DETAIL:
//                    btAddNew.setVisibility(View.GONE);
                    isEdit = true;
                    typeProduct = intent.getIntExtra(Constants.TYPE_PRODUCT, -1);
                    customerTypeProduct = intent.getIntExtra(Constants.CUSTOMER_TYPE_PRODUCT, -1);
                    if (customerTypeProduct == 1) {
                        btAddNew.setOnClickListener(v -> {
                            Intent i = new Intent(this, AddNewCustomerActivity.class);
                            EventBus.getDefault().postSticky(new TransferCustomerEvent(null, productID, 0, 0, true, hasFormular, typeProduct, customerTypeProduct));
//                    AddNewCustomerActivity.isFromHome = true;
                            startActivity(i);
                        });
                    } else {
                        btAddNew.setOnClickListener(view -> {
                            Timber.e("product id " + productID);
                            Intent i = new Intent(CustomerListActivity.this, AddNewEnterpriseCustomerActivity.class);
                            i.putExtra(Constants.CUSTOMER_ID, "");
                            i.putExtra(Constants.PRODUCT_ID, productID);
                            i.putExtra(Constants.CUSTOMER_PROFILEID, "");
                            i.putExtra(Constants.PROFILE_CODE, "");
                            i.putExtra(Constants.PRODUCT_EDIT, true);
                            startActivity(i);
                        });

                    }
                    break;
                case Constants.CUSTOMER_HD:
                    isEdit = true;
                    btAddNew.setVisibility(View.VISIBLE);
                    btAddNew.setOnClickListener(v -> {
                        Intent intent1 = new Intent(this, HDCustomerDetailActivity.class);
                        intent1.putExtra(Constants.PRODUCT_ID, productID);
                        startActivity(intent1);
                    });
                    break;
                case Constants.REWORK_TYPE:
                    btAddNew.setVisibility(View.GONE);
                    canRework = intent.getBooleanExtra(Constants.CAN_REWORK, false);
                    userId = intent.getStringExtra(Constants.USER_NAME);
                    fullName = intent.getStringExtra(Constants.USER_FULL_NAME);
                    departmentId = intent.getStringExtra(Constants.DEPARTMENT_ID);
                    isFromHome = true;
                    isRework = true;
                    break;

            }


        }
        setupUI();
        setupSw();

    }

    private void getCategoryData() {
//        DaoSession daoSession = ((MvpStarterApplication) getApplication()).getDaoSession();
//        OptionItemDao optionItemDao = daoSession.getOptionItemDao();
//        List<OptionItem> optionItemsCicStatus = optionItemDao.queryBuilder()
//                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.CIC_STATUS))
//                .list();
//        if (optionItemsCicStatus.size() == 0) {
//            showLoading();
        getDataForCustomerProfile();
//        }
    }

    private void getDataForCustomerProfile() {
        DaoSession daoSession = ((MvpStarterApplication) getApplication()).getDaoSession();
        OptionItemDao optionItemDao = daoSession.getOptionItemDao();

        optionItemDao.deleteAll();

        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<Category> call = apiInterface.getAllCategory(HawkHelper.getInstance(this).getToken());
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
//                hideLoading();
                if (response.code() == 200) {
                    List<Category.DataBean.MarriedStatusBean> maritalBeanList = response.body().getData().getMarriedStatus();
                    List<Category.DataBean.EducationStatusBean> educationsBeanList = response.body().getData().getEducationStatus();
                    List<Category.DataBean.PropertyTypeBean> propertyType = response.body().getData().getPropertyType();
                    List<Category.DataBean.GendersBean> gendersBeanList = response.body().getData().getGenders();
                    List<Category.DataBean.CustomerTypesBean> customerTypesBeans = response.body().getData().getCustomerTypes();
                    List<Category.DataBean.AssetStatusBean> assetStatusBeans = response.body().getData().getAssetStatus();
                    List<Category.DataBean.CicResultBean> cicResultBeans = response.body().getData().getCicResult();
                    List<Category.DataBean.EnterpriseTypesBean> enterpriseTypesBeans = response.body().getData().getEnterpriseTypes();
                    for (Category.DataBean.EnterpriseTypesBean enterpriseTypesBean : enterpriseTypesBeans) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.ENTERPRISE_TYPE, enterpriseTypesBean.getValueMember(), enterpriseTypesBean.getDisplayMember()));
                    }

                    for (Category.DataBean.CicResultBean cicResultBean : cicResultBeans) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.CIC_STATUS, cicResultBean.getValueMember(), cicResultBean.getDisplayMember()));
                    }
                    for (Category.DataBean.CustomerTypesBean customerTypesBean : customerTypesBeans) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.CUSTOMER_TYPE, customerTypesBean.getValueMember(), customerTypesBean.getDisplayMember()));
                    }
                    for (Category.DataBean.MarriedStatusBean maritalBean : maritalBeanList) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.MARRIED_STATUS, maritalBean.getValueMember(), maritalBean.getDisplayMember()));
                    }
                    for (Category.DataBean.EducationStatusBean educationsBean : educationsBeanList) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.EDUCATION_STATUS, educationsBean.getValueMember(), educationsBean.getDisplayMember()));
                    }

                    for (Category.DataBean.GendersBean gendersBean : gendersBeanList) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.GENDER, gendersBean.getValueMember(), gendersBean.getDisplayMember()));
                    }
                    for (Category.DataBean.PropertyTypeBean propertyTypeBean : propertyType) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.PROPERTY, propertyTypeBean.getValueMember(), propertyTypeBean.getDisplayMember()));
                    }
                    for (Category.DataBean.AssetStatusBean assetStatusBean : assetStatusBeans) {
                        optionItemDao.insert(new OptionItem(OptionItemGroupName.ASSET_STATUS, assetStatusBean.getValueMember(), assetStatusBean.getDisplayMember()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable throwable) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoading();
        chosenIds.clear();
        page = 1;
        token = HawkHelper.getInstance(this).getToken();
        if (type.equals(Constants.CUSTOMER_HOME) || type.equals(Constants.CUSTOMER_APPOINT)) {
            customerPresenter.getListPermissionCustomer(CustomerListActivity.this, token, etSearch.getText().toString(), page, userId);
        } else if (type.equals(Constants.REWORK_TYPE)) {
            customerPresenter.getListReworkCustomer(CustomerListActivity.this, token, userId);
            if (canRework) {
                tvChoose.setVisibility(View.VISIBLE);
            } else {
                tvChoose.setVisibility(View.GONE);
            }
            tvChooseAll.setVisibility(View.GONE);
            tvCancel.setVisibility(View.GONE);
        } else {
            customerPresenter.getListCustomer(this, token, etSearch.getText().toString(), page);
        }

        tvChoose.setOnClickListener(v -> {
            tvChoose.setVisibility(View.GONE);
            tvChooseAll.setVisibility(View.VISIBLE);
            tvCancel.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.GONE);
            tvTitle.setText("Chọn mục");
            btReArrange.setVisibility(View.VISIBLE);
            enableCheckBox = true;
            swAll.setEnabled(false);
            swAll.setRefreshing(false);
            customerPresenter.getListReworkCustomer(CustomerListActivity.this, token, userId);
            chosenIds.clear();
        });
        btReArrange.setOnClickListener(v -> {
            if (chosenIds.size() == 0) {
                Toast.makeText(CustomerListActivity.this, "Chưa chọn khách hàng nào!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent i = new Intent(CustomerListActivity.this, ReworkActivity.class);
            i.putExtra(Constants.DEPARTMENT_ID, departmentId);
            i.putExtra(Constants.REWORK_TYPE, Constants.CUSTOMER_REWORK);
            i.putExtra(Constants.USER_FULL_NAME, fullName);
            i.putExtra(Constants.USER_ID, userId);
            i.putExtra(Constants.CUSTOMER_ID, (ArrayList<String>) chosenIds);
            startActivity(i);
            getOriginStatus();
        });

        tvCancel.setOnClickListener(v -> {
            chosenIds.clear();
            getOriginStatus();

        });
    }


    private void getOriginStatus() {
        tvChoose.setVisibility(View.VISIBLE);
        tvChooseAll.setVisibility(View.GONE);
        tvCancel.setVisibility(View.GONE);
        swAll.setEnabled(true);
        ivBack.setVisibility(View.VISIBLE);
        btReArrange.setVisibility(View.GONE);
        enableCheckBox = false;
        swAll.setRefreshing(true);
        tvTitle.setText("Danh sách khách hàng");
        customerPresenter.getListReworkCustomer(CustomerListActivity.this, token, userId);

    }

    private void setupSw() {
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            if (!isRework) {
                page = 1;
                totalPages = 0;
                tvResult.setVisibility(View.GONE);
                if (type.equals(Constants.CUSTOMER_HOME) || type.equals(Constants.CUSTOMER_APPOINT)) {
                    customerPresenter.getListPermissionCustomer(CustomerListActivity.this, token, etSearch.getText().toString(), page, userId);
                } else {
                    customerPresenter.getListCustomer(this, token, etSearch.getText().toString(), page);
                }
            } else {
                if (!enableCheckBox) {
                    customerPresenter.getListReworkCustomer(CustomerListActivity.this, token, userId);
                } else {
                    swAll.setRefreshing(false);
                }
            }
        });


    }

    private void setupUI() {

        rvCustomer.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCustomer.setLayoutManager(linearLayoutManager);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: ");
                if (!isRework) {
                    tvResult.setVisibility(View.GONE);
                    if (s.toString().isEmpty()) {
                        page = 1;
                        if (type.equals(Constants.CUSTOMER_HOME) || type.equals(Constants.CUSTOMER_APPOINT)) {
                            customerPresenter.getListPermissionCustomer(CustomerListActivity.this, token, etSearch.getText().toString(), page, userId);
                        } else {
                            customerPresenter.getListCustomer(CustomerListActivity.this, token, etSearch.getText().toString(), page);
                        }
                    }
                } else {
                    if (s.toString().isEmpty()) {
                        if (!enableCheckBox) {
                            customerPresenter.getListReworkCustomer(CustomerListActivity.this, token, userId);
                        } else {
                            OnItemClicklistener onItemClicklistener;
                            onItemClicklistener = position -> {
                                if (!chosenIds.contains(String.valueOf(searchCustomers.get(position).getCustomerId()))) {
                                    searchCustomers.get(position).setChosen(true);
                                    chosenIds.add(String.valueOf(searchCustomers.get(position).getCustomerId()));
//                    Toast.makeText(this, "added " + chosenIds.toString(), Toast.LENGTH_SHORT).show();
                                } else {
                                    searchCustomers.get(position).setChosen(false);
                                    chosenIds.remove(String.valueOf(searchCustomers.get(position).getCustomerId()));
//                    Toast.makeText(this, "removed " + chosenIds.toString(), Toast.LENGTH_SHORT).show();
                                }
                                customerAdapter.notifyDataSetChanged();
                            };
                            customerAdapter = new CustomerAdapter(canRework, isRework, isFromHome, onItemClicklistener,
                                    CustomerListActivity.this, originCustomerList,
                                    rvCustomer, CustomerListActivity.this::onReworkClick, enableCheckBox);
                            rvCustomer.setAdapter(customerAdapter);
                        }
                    }
                }
            }
        });

        ivBack.setOnClickListener(v -> finish());

        ivSearch.setOnClickListener(v -> {
            if (!isRework) {
                {
                    tvResult.setVisibility(View.GONE);
                    page = 1;
                    if (type.equals(Constants.CUSTOMER_HOME) || type.equals(Constants.CUSTOMER_APPOINT)) {
                        customerPresenter.getListPermissionCustomer(CustomerListActivity.this, token, etSearch.getText().toString(), page, userId);
                    } else {
                        customerPresenter.getListCustomer(this, token, etSearch.getText().toString(), page);
                    }
                }
            } else {
                searchCustomers.clear();
                for (Customer.DataBean dataBean : originCustomerList) {
                    if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                        String name = dataBean.getLastName() + " " + dataBean.getFirstName();
                        if (name.toLowerCase().contains(etSearch.getText().toString())
                                || name.toUpperCase().contains(etSearch.getText().toString())) {
                            searchCustomers.add(dataBean);
                        }
                    }
                }
                OnItemClicklistener onItemClicklistener;
                onItemClicklistener = position -> {

                    if (!enableCheckBox) {
                        if (canRework) {
                            chosenIds.clear();
                            chosenIds.add(String.valueOf(searchCustomers.get(position).getCustomerId()));
                            Intent i = new Intent(CustomerListActivity.this, ReworkActivity.class);
                            i.putExtra(Constants.DEPARTMENT_ID, departmentId);
                            i.putExtra(Constants.REWORK_TYPE, Constants.CUSTOMER_REWORK);
                            i.putExtra(Constants.USER_FULL_NAME, fullName);
                            i.putExtra(Constants.CUSTOMER_ID, (ArrayList<String>) chosenIds);
                            startActivity(i);
                            getOriginStatus();
                        }
                    } else {
                        if (!chosenIds.contains(String.valueOf(searchCustomers.get(position).getCustomerId()))) {
                            searchCustomers.get(position).setChosen(true);
                            chosenIds.add(String.valueOf(searchCustomers.get(position).getCustomerId()));
//                    Toast.makeText(this, "added " + chosenIds.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            searchCustomers.get(position).setChosen(false);
                            chosenIds.remove(String.valueOf(searchCustomers.get(position).getCustomerId()));
//                    Toast.makeText(this, "removed " + chosenIds.toString(), Toast.LENGTH_SHORT).show();
                        }
                        customerAdapter.notifyDataSetChanged();
                    }

                };
                customerAdapter = new CustomerAdapter(canRework, isRework, isFromHome, onItemClicklistener,
                        this, searchCustomers, rvCustomer, this::onReworkClick, enableCheckBox);
                rvCustomer.setAdapter(customerAdapter);

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_customer_list;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        customerPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        customerPresenter.detachView();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void showListCusomter(List<Customer.DataBean> customerList, int totalPages) {
        this.totalPages = totalPages;
        if (page == 1) {
            originCustomerList.clear();
        }
        originCustomerList.addAll(customerList);
        if (originCustomerList.size() == 0) {
            tvResult.setVisibility(View.VISIBLE);
        } else {
            tvResult.setVisibility(View.GONE);
        }
        OnItemClicklistener onItemClicklistener = position -> {
            if (isFromAppoint) {
                Intent i = new Intent(CustomerListActivity.this, PersonalActivity.class);
                i.putExtra(Constants.SEE_DETAIL, Constants.CUSTOMER_TYPE);
                i.putExtra(Constants.CUSTOMER_ID, originCustomerList.get(position));
                i.putExtra(Constants.PRODUCT_TYPE, Constants.CUSTOMER_APPOINT);
                startActivity(i);
                finish();
            } else {
//                if (isFromHD) {
//                    Intent i = new Intent(this, HDCustomerDetailActivity.class);
//                    i.putExtra(Constants.CUSTOMER_ID, originCustomerList.get(position));
//                    i.putExtra(Constants.PRODUCT_ID, productID);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//                } else {
//                    if (!isEdit) {
                Intent i = new Intent(CustomerListActivity.this, PersonalActivity.class);
                i.putExtra(Constants.CUSTOMER_ID, originCustomerList.get(position));
                i.putExtra(Constants.CUSTOMER_TYPE, isFromHome);
                if (customerTypeProduct != -1) {
                    i.putExtra(Constants.CUSTOMER_TYPE_PRODUCT, customerTypeProduct);
                }
                i.putExtra(Constants.EMPLOYEE, userId);
                i.putExtra(Constants.USER_FULL_NAME, fullName);
                i.putExtra(Constants.DEPARTMENT_ID, departmentId);
                i.putExtra(Constants.TYPE_PRODUCT, typeProduct);
                i.putExtra(Constants.PRODUCT_TYPE, type);
                i.putExtra(Constants.CAN_REWORK, canRework);
                i.putExtra(Constants.PRODUCT_FORMUALR, hasFormular);
                if (productID != -1) {
                    i.putExtra(Constants.PRODUCT_ID, productID);
                }
                startActivity(i);
//                    } else {
//                        Intent i = new Intent(CustomerListActivity.this, AddNewCustomerActivity.class);
//                        EventBus.getDefault().postSticky(new TransferCustomerEvent(originCustomerList.get(position), productID));
//                        startActivity(i);
//                        overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//                    }
//                }
            }
        };

        if (customerAdapter == null) {
            customerAdapter = new CustomerAdapter(canRework, isRework, isFromHome, onItemClicklistener, this, originCustomerList, rvCustomer, this::onReworkClick);
            rvCustomer.setAdapter(customerAdapter);
        } else {
            customerAdapter.notifyDataSetChanged();
        }

        customerAdapter.setLoaded();
        page++;
        customerAdapter.setOnLoadMoreListener(() -> {
            if (page > totalPages && totalPages > 0) return;
            if (type.equals(Constants.CUSTOMER_HOME) || type.equals(Constants.CUSTOMER_APPOINT)) {
                customerPresenter.getListPermissionCustomer(CustomerListActivity.this, token, etSearch.getText().toString(), page, userId);
            } else {
                customerPresenter.getListCustomer(CustomerListActivity.this, token, etSearch.getText().toString(), page);
            }
        });
    }

    @Override
    public void hideSwipe() {
        swAll.setRefreshing(false);
    }

    @Override
    public void showListReworkCusomter(List<Customer.DataBean> customerList) {
        originCustomerList.clear();
        originCustomerList.addAll(customerList);
        OnItemClicklistener onItemClicklistener;
        if (!enableCheckBox) {
            onItemClicklistener = position -> {
                if (canRework) {
                    chosenIds.clear();
                    chosenIds.add(String.valueOf(originCustomerList.get(position).getCustomerId()));
                    Intent i = new Intent(CustomerListActivity.this, ReworkActivity.class);
                    i.putExtra(Constants.DEPARTMENT_ID, departmentId);
                    i.putExtra(Constants.REWORK_TYPE, Constants.CUSTOMER_REWORK);
                    i.putExtra(Constants.USER_ID, userId);
                    i.putExtra(Constants.USER_FULL_NAME, fullName);
                    i.putExtra(Constants.CUSTOMER_ID, (ArrayList<String>) chosenIds);
                    Log.e(TAG, "showListReworkCusomter: " + originCustomerList.get(position).getCustomerId());
                    startActivity(i);
                    getOriginStatus();
                }
            }
            ;
        } else {
            onItemClicklistener = position -> {
                if (!chosenIds.contains(String.valueOf(customerList.get(position).getCustomerId()))) {
                    customerList.get(position).setChosen(true);
                    chosenIds.add(String.valueOf(customerList.get(position).getCustomerId()));
//                    Toast.makeText(this, "added " + chosenIds.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    customerList.get(position).setChosen(false);
                    chosenIds.remove(String.valueOf(customerList.get(position).getCustomerId()));
//                    Toast.makeText(this, "removed " + chosenIds.toString(), Toast.LENGTH_SHORT).show();
                }
                customerAdapter.notifyDataSetChanged();
            };

        }

        tvChooseAll.setOnClickListener(v -> {
            chosenIds.clear();
            for (Customer.DataBean dataBean : customerList) {
                dataBean.setChosen(true);
                chosenIds.add(String.valueOf(dataBean.getCustomerId()));
//                Toast.makeText(this, "added all " + chosenIds.toString(), Toast.LENGTH_SHORT).show();
                customerAdapter.notifyDataSetChanged();
            }
        });

        customerAdapter = new CustomerAdapter(canRework, isRework, isFromHome, onItemClicklistener,
                this, originCustomerList, rvCustomer, this::onReworkClick, enableCheckBox);
        rvCustomer.setAdapter(customerAdapter);

    }

    @Override
    public void onReworkClick(int postion) {
        chosenIds.clear();
        chosenIds.add(String.valueOf(originCustomerList.get(postion).getCustomerId()));
        Intent i = new Intent(CustomerListActivity.this, ReworkActivity.class);
        i.putExtra(Constants.DEPARTMENT_ID, departmentId);
        i.putExtra(Constants.REWORK_TYPE, Constants.CUSTOMER_REWORK);
        i.putExtra(Constants.USER_FULL_NAME, fullName);
        i.putExtra(Constants.USER_ID, userId);
        Log.e(TAG, "showListReworkCusomter: " + chosenIds.get(0) + " sice " + chosenIds.size());
        i.putExtra(Constants.CUSTOMER_ID, (ArrayList<String>) chosenIds);
        startActivity(i);
        getOriginStatus();
    }
}
