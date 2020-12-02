package com.saleskit.cbbank.features.personal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.EmployeeInfomation;
import com.saleskit.cbbank.features.account.MessageJson;
import com.saleskit.cbbank.features.appointment.AddApointmentActivity;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;
import com.saleskit.cbbank.features.customer.add_new_customer.AddNewCustomerActivity;
import com.saleskit.cbbank.features.customer.enterprise.AddNewEnterpriseCustomerActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.detail.HDCustomerDetailActivity;
import com.saleskit.cbbank.features.home.activity.ProductListActivity;
import com.saleskit.cbbank.features.appointment.AppointListActivity;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Customer;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PersonalActivity extends BaseActivity implements PersonView {
    private static final String TAG = "PersonalActivity";
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.bt_creat)
    Button btCreat;
    @BindView(R.id.bt_creat_file)
    Button btCreatFile;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    @BindView(R.id.tv_identity)
    TextView tvIdentity;
    private CustomerProductAdapter customerProductAdapter;
    private String token;
    private Customer.DataBean customer;
    private int productId = 1;
    private String employeeFullName = "";
    private CustomerInfo.DataBean.CustomerProfilesBean customerProfilesBean;
    List<CustomerInfo.DataBean.CustomerProfilesBean> customerProduct = new ArrayList<>();
    int page = 1;
    int totalPages;
    private boolean isFromHome;
    private CustomerInfo.DataBean customerinfo;
    private int customerTypePerson = 0;
    private String productype;
    private int customerProfileId;
    private AlertDialog dialog;
    private int itemPosition;
    private int typeProduct;
    private boolean hasFormular;
    private String userId = "";
    private int customerTypeProduct = -1;
    private String departmentId = "";
    private boolean canRework = false;
    private boolean canEdit = false;
    private List<EmployeeInfomation.DataBean.PositionBean> positionBeans;
    private boolean isEmplyee = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        setupSw();
        token = HawkHelper.getInstance(this).getToken();
        positionBeans = HawkHelper.getInstance(this).getEmployeeInfo().getPosition();
        Intent i = getIntent();
        customer = (Customer.DataBean) i.getSerializableExtra(Constants.CUSTOMER_ID);
        isFromHome = i.getBooleanExtra(Constants.CUSTOMER_TYPE, false);
        try {
            customerTypeProduct = i.getIntExtra(Constants.CUSTOMER_TYPE_PRODUCT, -1);
        } catch (Exception e) {

        }
        // Kiểm tra gán chức năng cho user
        if (isFromHome) {
            userId = i.getStringExtra(Constants.EMPLOYEE);
            if (userId.equals(HawkHelper.getInstance(this).getTokenModel().getUserName())) {
                canEdit = true;
            }
            employeeFullName = i.getStringExtra(Constants.USER_FULL_NAME);
            departmentId = i.getStringExtra(Constants.DEPARTMENT_ID);
            canRework = i.getBooleanExtra(Constants.CAN_REWORK, false);
            if (positionBeans.size() == 0) {
                isEmplyee = false;
            } else {
                for (EmployeeInfomation.DataBean.PositionBean positionBean : positionBeans) {
                    if (positionBean.isIsLeaderDepartment() || positionBean.isIsLeaderBranch() || positionBean.isIsLeaderRegion()) {
                        isEmplyee = false;
                        break;
                    }
                }
            }
            if (!isEmplyee) {
                btCreat.setVisibility(View.GONE);
                btCreatFile.setVisibility(View.GONE);
            }
        }
        productype = i.getStringExtra(Constants.PRODUCT_TYPE);
        if (productype.equals(Constants.CUSTOMER_DETAIL)) {
            typeProduct = i.getIntExtra(Constants.TYPE_PRODUCT, -1);
//            if (typeProduct == 1) {
//                customerTypeProduct = i.getIntExtra(Constants.CUSTOMER_TYPE_PRODUCT, -1);
//            }
        } else if (productype.equals(Constants.CUSTOMER_APPOINT)) {
            isFromHome = false;
            btCreatFile.setText("Thêm mới cuộc hẹn");

        }
        productId = i.getIntExtra(Constants.PRODUCT_ID, -1);
        hasFormular = i.getBooleanExtra(Constants.PRODUCT_FORMUALR, true);
        setupDialog();

        setupUI();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoading();
        page = 1;
        getData(String.valueOf(customer.getCustomerId()));

    }

    private void setupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.comfirm_delte);
        builder.setPositiveButton(R.string.ok, (dialog, id) -> {
            showLoading();
            APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
            Call<MessageJson> call = apiInterface.deleteProfile(token, String.valueOf(customerProfileId));
            call.enqueue(new Callback<MessageJson>() {
                @Override
                public void onResponse(Call<MessageJson> call, Response<MessageJson> response) {
                    hideLoading();
                    if (response.code() == 200) {
                        customerProduct.remove(itemPosition);
                        customerProductAdapter.notifyDataSetChanged();
                        Toast.makeText(PersonalActivity.this, getResources().getString(R.string.delete_complete), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PersonalActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageJson> call, Throwable t) {
                    hideLoading();
                }
            });
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            dialog.dismiss();
        });
        dialog = builder.create();
    }

    private void setupUI() {
        btnBack.setOnClickListener(v -> finish());
        rvProduct.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        rvProduct.setLayoutManager(linearLayoutManager);

        btCreat.setOnClickListener(v -> {
            Intent i = new Intent(this, AppointListActivity.class);
            i.putExtra(Constants.APPOINT_TYPE, Constants.PERSONAL_TYPE);
            if (TextUtils.isEmpty(userId)) {
                i.putExtra(Constants.USER_NAME, HawkHelper.getInstance(PersonalActivity.this).getTokenModel().getUserName());
            } else {
                i.putExtra(Constants.USER_NAME, userId);
            }
            i.putExtra(Constants.CUSTOMER_ID, customer);
//            i.putExtra(Constants.SEE_DETAIL, "");
            startActivity(i);
        });

        btCreatFile.setOnClickListener(v -> {
            Timber.e("type cuss" + customerTypeProduct);
            if (productype.equals(Constants.CUSTOMER_APPOINT)) {
                Intent i = new Intent(PersonalActivity.this, AddApointmentActivity.class);
                i.putExtra(Constants.SEE_DETAIL, Constants.CUSTOMER_TYPE);
                i.putExtra(Constants.CUSTOMER_ID, (Serializable) customer);
                startActivity(i);
                finish();
            } else {
                if (isFromHome) {
                    Intent i = new Intent(this, ProductListActivity.class);
                    i.putExtra(Constants.SCREEN_TYPE, "from_person");
                    i.putExtra(Constants.CUSTOMER_ID, customerinfo);
                    startActivity(i);
                } else {
                    if (productype.equals(Constants.CUSTOMER_HD)) {
                        Intent i = new Intent(this, HDCustomerDetailActivity.class);
                        i.putExtra(Constants.CUSTOMER_ID, customerinfo);
                        i.putExtra(Constants.PRODUCT_ID, productId);
                        startActivity(i);
                    } else {
                        if (customerTypeProduct != customerTypePerson) {
                            Toast.makeText(this, "Không thể tạo hồ sơ với sản phẩm khác loại!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (customerTypePerson == 1) {
                            Intent intent = new Intent(PersonalActivity.this, AddNewCustomerActivity.class);
                            EventBus.getDefault().postSticky(new TransferCustomerEvent(customerinfo, productId, 0, 0, true, hasFormular, Integer.valueOf(typeProduct), customerTypePerson));
                            startActivity(intent);
                        } else {
                            Intent i = new Intent(PersonalActivity.this, AddNewEnterpriseCustomerActivity.class);
                            i.putExtra(Constants.CUSTOMER_ID, String.valueOf(customerinfo.getCustomerId()));
                            Timber.e("Customer " + customerinfo.getCustomerId());
                            i.putExtra(Constants.PRODUCT_EDIT, true);
                            i.putExtra(Constants.PRODUCT_ID, productId);
                            i.putExtra(Constants.CUSTOMER_PROFILEID, "");
                            i.putExtra(Constants.PROFILE_CODE, "");
                            startActivity(i);
                        }
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
            getData(String.valueOf(customer.getCustomerId()));
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personal;
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

    private void getData(String id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("customerId", id);
        options.put("page", String.valueOf(page));
        options.put("pagesize", "10");
        if (isFromHome) {
            options.put("userId", userId);
        }
        Call<CustomerInfo> call = apiInterface.getCustomerInfo(token, options);

        call.enqueue(new Callback<CustomerInfo>() {
            @Override
            public void onResponse(Call<CustomerInfo> call, Response<CustomerInfo> response) {
                hideLoading();
                swAll.setRefreshing(false);
                if (response.code() == 200) {

                    int totalItems = response.body().getTotalRecords() + 1;
                    totalPages = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        totalPages += 1;
                    }

                    if (page == 1) {
                        customerProduct.clear();
                    }
                    customerTypePerson = response.body().getData().getCustomerType();
                    CustomerInfo.DataBean dataBean = response.body().getData();
                    String name = dataBean.getLastName() + " " + dataBean.getFirstName();
                    tvName.setText(name);
                    String address = dataBean.getAddress();
                    String phoneNumber = dataBean.getPhoneNumber();
                    tvAddress.setText(address);
                    tvPhone.setText(phoneNumber);
                    tvIdentity.setText(dataBean.getIdentityNumber());

                    customerProduct.addAll(response.body().getData().getCustomerProfiles());

                    CustomerInfo.DataBean customerInfo = response.body().getData();
                    customerinfo = customerInfo;
                    OnItemClicklistener onItemClicklistener = position -> {
                        int typeProduct = customerProduct.get(position).getProductType();
                        if (typeProduct == 2) {
                            Intent i = new Intent(PersonalActivity.this, HDCustomerDetailActivity.class);
                            i.putExtra(Constants.PRODUCT_ID, customerProduct.get(position).getProductId());
                            i.putExtra(Constants.PROFILE_CODE, customerProduct.get(position).getCustomerProfileCode());
                            i.putExtra(Constants.CUSTOMER_ID, customerInfo);
                            i.putExtra(Constants.PRODUCT_EDIT, false);
                            i.putExtra(Constants.CUSTOMER_PROFILEID, customerProduct.get(position).getCustomerProfileId());
                            i.putExtra(Constants.PRODUCT_STATUS, customerProduct.get(position).getStatus());
                            startActivity(i);
                        } else {
                            if (customerTypePerson == 1) {
                                boolean hasFormular = customerProduct.get(position).isHasCalculationFormula();
                                Intent i = new Intent(PersonalActivity.this, AddNewCustomerActivity.class);
                                EventBus.getDefault().postSticky(new TransferCustomerEvent(customerInfo, customerProduct.get(position).getProductId(),
                                        customerProduct.get(position).getCustomerProfileId()
                                        , customerProduct.get(position).getProcess(), false, hasFormular, typeProduct, dataBean.getCustomerType()));
//                            }
//                            AddNewCustomerActivity.isFromHome = false;
                                startActivity(i);
                            } else {
                                Intent i = new Intent(PersonalActivity.this, AddNewEnterpriseCustomerActivity.class);
                                i.putExtra(Constants.CUSTOMER_ID, String.valueOf(customerinfo.getCustomerId()));
                                Timber.e("Customer " + customerinfo.getCustomerId());
                                i.putExtra(Constants.PRODUCT_EDIT, false);
                                i.putExtra(Constants.PRODUCT_ID, customerProduct.get(position).getProductId());
                                i.putExtra(Constants.CUSTOMER_PROFILEID, customerProduct.get(position).getCustomerProfileId());
                                i.putExtra(Constants.PROFILE_CODE, customerProduct.get(position).getCustomerProfileCode());
                                startActivity(i);
                            }
                        }
                    };
                    if (customerProductAdapter == null) {
                        customerProductAdapter = new CustomerProductAdapter(employeeFullName, canRework, canEdit,
                                departmentId, userId, onItemClicklistener, customerProduct,
                                PersonalActivity.this, PersonalActivity.this, rvProduct, isFromHome, customerTypePerson);
                        rvProduct.setAdapter(customerProductAdapter);
                    } else {
                        customerProductAdapter.notifyDataSetChanged();
                    }

                    customerProductAdapter.setLoaded();
                    page++;
                    customerProductAdapter.setOnLoadMoreListener(() -> {
                        if (page > totalPages && totalPages > 0) return;
                        getData(String.valueOf(customer.getCustomerId()));
                    });

                } else {
                    Toast.makeText(PersonalActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerInfo> call, Throwable throwable) {
                hideLoading();
            }
        });


    }

    @Override
    public void onDelete(int position) {
        itemPosition = position;
        customerProfileId = customerProduct.get(position).getCustomerProfileId();
        dialog.show();
    }

    @Override
    public void onItemClick(int position) {
        int producttype = customerProduct.get(position).getProductType();
        Log.d(TAG, "onItemClick: " + customerinfo + isFromHome);
        if (customerTypePerson == 1) {
            if (producttype == 1 || producttype == 3) {
                Intent i = new Intent(PersonalActivity.this, AddNewCustomerActivity.class);
                EventBus.getDefault().postSticky(new TransferCustomerEvent(customerinfo, customerProduct.get(position).getProductId(),
                        customerProduct.get(position).getCustomerProfileId()
                        , customerProduct.get(position).getProcess(),
                        true, customerProduct.get(position).isHasCalculationFormula(), producttype, customerTypePerson));
                startActivity(i);
            } else {
                Intent i = new Intent(PersonalActivity.this, AddNewEnterpriseCustomerActivity.class);
                i.putExtra(Constants.CUSTOMER_ID, String.valueOf(customerinfo.getCustomerId()));
                i.putExtra(Constants.PRODUCT_ID, customerProduct.get(position).getProductId());
                i.putExtra(Constants.CUSTOMER_PROFILEID, customerProduct.get(position).getCustomerProfileId());
                i.putExtra(Constants.PROFILE_CODE, customerProduct.get(position).getCustomerProfileCode());
                i.putExtra(Constants.PRODUCT_EDIT, true);
                startActivity(i);
            }
        } else {
            Intent i = new Intent(PersonalActivity.this, HDCustomerDetailActivity.class);
            i.putExtra(Constants.PRODUCT_ID, customerProduct.get(position).getProductId());
            i.putExtra(Constants.PROFILE_CODE, customerProduct.get(position).getCustomerProfileCode());
            i.putExtra(Constants.CUSTOMER_ID, customerinfo);
            i.putExtra(Constants.PRODUCT_EDIT, true);
            i.putExtra(Constants.CUSTOMER_PROFILEID, customerProduct.get(position).getCustomerProfileId());
            i.putExtra(Constants.PRODUCT_STATUS, customerProduct.get(position).getStatus());
            startActivity(i);
        }

    }
}
