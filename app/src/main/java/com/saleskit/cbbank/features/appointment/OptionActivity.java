package com.saleskit.cbbank.features.appointment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.MvpStarterApplication;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.ControlBean;
import com.saleskit.cbbank.features.account.EmployeeEnum;
import com.saleskit.cbbank.features.account.EmployeeJson;
import com.saleskit.cbbank.features.account.LowerUser;
import com.saleskit.cbbank.features.account.PropertyForm;
import com.saleskit.cbbank.features.account.PropertyType;
import com.saleskit.cbbank.features.account.RegionJson;
import com.saleskit.cbbank.features.account.TunDepartmentJson;
import com.saleskit.cbbank.features.account.TunJson;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.add_new_customer.model.Category;
import com.saleskit.cbbank.features.database.DaoSession;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.database.OptionItem;
import com.saleskit.cbbank.features.database.OptionItemDao;
import com.saleskit.cbbank.features.database.OptionItemGroupName;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class OptionActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_search)
    ClearableEditText tvSearch;
    @BindView(R.id.rl_search)
    LinearLayout rlSearch;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.rv_info_cate)
    RecyclerView rvInfoCate;
    private List<OptionModel> optionModels = new ArrayList<>();
    private List<OptionModel> searchOptionModels = new ArrayList<>();
    OptionAdapter optionAdapter;
    private int value;
    private String token;
    private String type;
    private String employeeName, name;
    private String branchId;
    private String departmentId;
    private int productId;
    private int collateral;
    private List<Category.DataBean.AppointmentResultStatus> appointmentResultStatuses = new ArrayList<>();
    private List<ControlBean.DataBean.OptionControlModelsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        ButterKnife.bind(this);
        token = HawkHelper.getInstance(this).getToken();
        getDataAppoint();
        Intent i = getIntent();
        type = i.getStringExtra(Constants.CATEGORY_TYPE);
        value = i.getIntExtra(Constants.CATEGORY_VALUE, 0);
        name = i.getStringExtra(Constants.CATEGORY_NAME);
        list = (List<ControlBean.DataBean.OptionControlModelsBean>) i.getSerializableExtra(Constants.ENUM_LIST);
//        branchId = HawkHelper.getInstance(this).getEmployeeInfo().getPosition().get(0).getBranchId();
        ivSearch.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvInfoCate.setLayoutManager(linearLayoutManager);
        btnBack.setOnClickListener(v -> {
            finish();
        });
        ivSearch.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            rlSearch.setVisibility(View.VISIBLE);
            ivSearch.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            tvSearch.requestFocus();
        });
        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchLocal(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (type.equals(Constants.ENUM)) {
            for (ControlBean.DataBean.OptionControlModelsBean optionControlModelsBean : list) {
                optionModels.add(new OptionModel(optionControlModelsBean.getDisplayMember(),
                        optionControlModelsBean.getValueMember()));
                OnItemClicklistener onItemClicklistener = position -> {
                    EventBus.getDefault().post(
                            new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getValue(), type));
                    finish();
                };
                optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                rvInfoCate.setAdapter(optionAdapter);
                tvTitle.setText(name);
            }
        } else if (type.equals(Constants.GET_LOWER_USER)) {
            tvTitle.setText("Cán bộ xử lý");
            showLoading();
            getLowerUser();
        } else if (type.equals(OptionItemGroupName.BANK_STRUCTURES)) {
            tvTitle.setText("Chi nhánh");
            showLoading();
            getBranch();
        } else if (type.equals(Constants.EMPLOYEE)) {
            showLoading();
            tvTitle.setText("Cán bộ xử lý");
            employeeName = i.getStringExtra(Constants.USER_ID);
            departmentId = i.getStringExtra(Constants.DEPARTMENT_ID);
            getEmployee(departmentId);
        } else if (type.equals(OptionItemGroupName.DEPARTMENTS)) {
            showLoading();
            tvTitle.setText("Phòng giao dịch");
            branchId = i.getStringExtra(Constants.ROOT_VALUE);
            getDepartment(branchId);
        } else if (type.equals(Constants.PROPERTY)) {
            showLoading();
            tvTitle.setText("Tài sản");
            productId = i.getIntExtra(Constants.PRODUCT_ID, 0);
            getProPertyType(productId);
        } else if (type.equals(Constants.PRICE)) {
            showLoading();
            tvTitle.setText("Hình thức định giá");
            productId = i.getIntExtra(Constants.PRODUCT_ID, 0);
            collateral = i.getIntExtra(Constants.COLLATERAL_TYPE, 0);
            getPrice(collateral);
        } else {
            getDataFromRoom(i);
        }

    }

    private void getDataAppoint() {
        DaoSession daoSession = ((MvpStarterApplication) getApplication()).getDaoSession();
        OptionItemDao optionItemDao = daoSession.getOptionItemDao();
        List<OptionItem> optionItemsCicStatus = optionItemDao.queryBuilder()
                .where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.APPOINTMENT))
                .list();
        if (optionItemsCicStatus.size() == 0) {
            appointmentResultStatuses.add(new Category.DataBean.AppointmentResultStatus(1, "Hẹn gặp lại"));
            appointmentResultStatuses.add(new Category.DataBean.AppointmentResultStatus(2, "Đồng ý gặp"));
            appointmentResultStatuses.add(new Category.DataBean.AppointmentResultStatus(3, "Hủy bỏ"));
            for (Category.DataBean.AppointmentResultStatus appointmentResultStatus : appointmentResultStatuses) {
                optionItemDao.insert(new OptionItem(OptionItemGroupName.APPOINTMENT, appointmentResultStatus.getValueMember(), appointmentResultStatus.getDisplayMember()));
            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_option;
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

    private void getPrice(int collateral) {
        APIWebservices apiInterface1 = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("productId", String.valueOf(productId));
        option.put("propertyTypeId", String.valueOf(collateral));
        Call<PropertyForm> call = apiInterface1.getPropertyForm(token, option);
        call.enqueue(new Callback<PropertyForm>() {
            @Override
            public void onResponse(Call<PropertyForm> call, Response<PropertyForm> response) {
                hideLoading();
                if (response.code() == 200) {
                    for (PropertyForm.DataBean dataBean : response.body().getData()) {
                        optionModels.add(new OptionModel(dataBean.getPropertyFormName(), String.valueOf(dataBean.getPropertyFormId()), dataBean.getRatio()));
                    }
                    OnItemClicklistener onItemClicklistener = position -> {
                        EventBus.getDefault().post(
                                new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), Constants.PRICE,
                                        optionModels.get(position).getRatio()));
                        finish();
                    };
                    optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                    rvInfoCate.setAdapter(optionAdapter);
                }

            }

            @Override
            public void onFailure(Call<PropertyForm> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getProPertyType(int productId) {
        APIWebservices apiInterface1 = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<PropertyType> call = apiInterface1.getProperty(token, productId);
        call.enqueue(new Callback<PropertyType>() {
            @Override
            public void onResponse(Call<PropertyType> call, Response<PropertyType> response) {
                hideLoading();
                if (response.code() == 200) {
                    for (PropertyType.DataBean dataBean : response.body().getData()) {
                        optionModels.add(new OptionModel(dataBean.getPropertyTypeName(), dataBean.getPropertyTypeId()));
                    }
                    OnItemClicklistener onItemClicklistener = position -> {
                        EventBus.getDefault().post(
                                new CategoryEvent(optionModels.get(position).getName(), String.valueOf(optionModels.get(position).getValue()), Constants.PROPERTY));
                        finish();
                    };
                    optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                    rvInfoCate.setAdapter(optionAdapter);
                }
            }

            @Override
            public void onFailure(Call<PropertyType> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getEmployee(String departmentId) {
        APIWebservices apiInterface1 = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<EmployeeEnum> call = apiInterface1.getEmployeeEnum(token, departmentId);
        call.enqueue(new Callback<EmployeeEnum>() {
            @Override
            public void onResponse(Call<EmployeeEnum> call, Response<EmployeeEnum> response) {
                hideLoading();
                if (response.code() == 200) {
                    for (EmployeeEnum.DataBean dataBean : response.body().getData()) {
                        if (dataBean.getUserName().equals(employeeName)) {
                            continue;
                        } else {
                            optionModels.add(new OptionModel(dataBean.getFullName(), dataBean.getUserName()));
                        }
                    }
                    OnItemClicklistener onItemClicklistener = position -> {
                        EventBus.getDefault().post(
                                new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), OptionItemGroupName.DEPARTMENTS));
                        finish();
                    };
                    optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                    rvInfoCate.setAdapter(optionAdapter);
                }
            }

            @Override
            public void onFailure(Call<EmployeeEnum> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getDepartment(String branchId) {
        APIWebservices apiInterface1 = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<Branch> call = apiInterface1.getAllDepartMent(token, branchId);
        call.enqueue(new Callback<Branch>() {
            @Override
            public void onResponse(Call<Branch> call, Response<Branch> response) {
                hideLoading();
                if (response.code() == 200) {
                    for (Branch.DataBean dataBean : response.body().getData()) {
                        optionModels.add(new OptionModel(dataBean.getName(), dataBean.getId()));
                    }

                    OnItemClicklistener onItemClicklistener = position -> {
                        EventBus.getDefault().post(
                                new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), OptionItemGroupName.DEPARTMENTS));
                        finish();
                    };
                    optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                    rvInfoCate.setAdapter(optionAdapter);
                }
            }

            @Override
            public void onFailure(Call<Branch> call, Throwable t) {
                hideLoading();
            }
        });

    }

    private void getBranch() {
        APIWebservices apiInterface1 = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<Branch> call = apiInterface1.getAllBranch(token);
        call.enqueue(new Callback<Branch>() {
            @Override
            public void onResponse(Call<Branch> call, Response<Branch> response) {
                hideLoading();
                if (response.code() == 200) {
                    for (Branch.DataBean dataBean : response.body().getData()) {
                        optionModels.add(new OptionModel(dataBean.getName(), dataBean.getId()));
                    }

                    OnItemClicklistener onItemClicklistener = position -> {
                        EventBus.getDefault().post(
                                new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), OptionItemGroupName.BANK_STRUCTURES));
                        finish();
                    };
                    optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                    rvInfoCate.setAdapter(optionAdapter);
                }
            }

            @Override
            public void onFailure(Call<Branch> call, Throwable t) {
                hideLoading();
            }
        });
    }


    private void getLowerUser() {
        APIWebservices apiInterface1 = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<LowerUser> call = apiInterface1.getListLoweUser(token);
        call.enqueue(new Callback<LowerUser>() {
            @Override
            public void onResponse(Call<LowerUser> call, Response<LowerUser> response) {
                hideLoading();
                if (response.code() == 200) {
                    for (LowerUser.DataBean dataBean : response.body().getData()) {
                        optionModels.add(new OptionModel(dataBean.getFullName(), dataBean.getId()));
                    }

                    OnItemClicklistener onItemClicklistener = position -> {
                        EventBus.getDefault().post(
                                new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId()));
                        finish();
                    };
                    optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                    rvInfoCate.setAdapter(optionAdapter);
                }
            }

            @Override
            public void onFailure(Call<LowerUser> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getDataFromRoom(Intent intent) {
        DaoSession daoSession = ((MvpStarterApplication) getApplication()).getDaoSession();
        OptionItemDao optionItemDao = daoSession.getOptionItemDao();

        switch (type) {
            case Constants.REGION_REPORT:
                tvTitle.setText("Vùng miền");
                getRegion();
                break;
            case Constants.BRANCH_REPORT:
                tvTitle.setText("Chi nhánh");
                String regionId = intent.getStringExtra(Constants.REPORT_ID);
                getBranchReport(regionId);
                break;
            case Constants.DEPARTMENT_REPORT:
                String branchId = intent.getStringExtra(Constants.REPORT_ID);
                getDepartmentReport(branchId);
                tvTitle.setText("Phòng ban");
                break;
            case Constants.USER_REPORT:
                String departmentId = intent.getStringExtra(Constants.REPORT_ID);
                tvTitle.setText("Nhân sự");
                getEmployeeReport(departmentId);
                break;
            case Constants.CIC_STATUS:
                tvTitle.setText("Trạng thái");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("cic_status")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.ASSERT:
                tvTitle.setText("Tình trạng sở hữu nhà");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("assetStatus")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.MARTIAL:
                tvTitle.setText("Tình trạng hôn nhân");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("marriedStatus")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.EDUCATION:
                tvTitle.setText("Trình độ học vấn");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("educationStatus")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.COMPANY_TYPE:
                tvTitle.setText("Loại hình cơ quan");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("companyTypes")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.SALARY_PAYMETHOD:
                tvTitle.setText("Hình thức thanh toán lương");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("salaryPaymentMethod")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.REGION:
                tvTitle.setText("Khu vực");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("religions")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.PARTNER_SHIP:
                tvTitle.setText("Quan hệ với đối tác");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("parner_ship")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.CONTRACT:
                tvTitle.setText("Hình thức hợp đồng");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq("contracts")).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.APPOINT_STATUS:
                tvTitle.setText("Trạng thái cuộc hẹn");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.APPOINTMENT)).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
            case Constants.CUSTOMER_TYPE:
                tvTitle.setText("Kiểu khách hàng");
                for (OptionItem optionItem : optionItemDao.queryBuilder().where(OptionItemDao.Properties.Group.eq(OptionItemGroupName.CUSTOMER_TYPE)).list()) {
                    optionModels.add(new OptionModel(optionItem.getDisplay(), optionItem.getValue()));
                }
                break;
        }
        OnItemClicklistener onItemClicklistener = position -> {
            EventBus.getDefault().post(
                    new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getValue(), type));
            finish();
        };
        optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
        rvInfoCate.setAdapter(optionAdapter);
    }

    private void getEmployeeReport(String departmentId) {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(1));
        option.put("pageSize", "1000");
        option.put("departmentId", departmentId);
        Call<EmployeeJson> call = apiInterface.getAllEmplyee(token, option);
        call.enqueue(new Callback<EmployeeJson>() {
            @Override
            public void onResponse(Call<EmployeeJson> call, Response<EmployeeJson> response) {
                hideLoading();
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null && response.body().getData().getResults() != null) {
                        for (EmployeeJson.DataBean.ResultsBean dataBean : response.body().getData().getResults()) {
                            optionModels.add(new OptionModel(dataBean.getFullName(), dataBean.getId()));
                        }
                        OnItemClicklistener onItemClicklistener = position -> {
                            EventBus.getDefault().post(
                                    new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), type));
                            finish();
                        };
                        optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                        rvInfoCate.setAdapter(optionAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeeJson> call, Throwable t) {

            }
        });
    }

    private void getDepartmentReport(String branchId) {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(1));
        option.put("pageSize", "1000");
        option.put("branchId", branchId);
        Call<TunDepartmentJson> call = apiInterface.getDepartmentById(token, option);
        call.enqueue(new Callback<TunDepartmentJson>() {
            @Override
            public void onResponse(Call<TunDepartmentJson> call, Response<TunDepartmentJson> response) {
                hideLoading();
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null && response.body().getData().getResults() != null) {
                        for (TunDepartmentJson.DataBean.ResultsBean dataBean : response.body().getData().getResults()) {
                            optionModels.add(new OptionModel(dataBean.getName(), dataBean.getId()));
                        }
                        OnItemClicklistener onItemClicklistener = position -> {
                            EventBus.getDefault().post(
                                    new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), type));
                            finish();
                        };
                        optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                        rvInfoCate.setAdapter(optionAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<TunDepartmentJson> call, Throwable t) {

            }
        });

    }

    private void getBranchReport(String regionId) {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(1));
        option.put("pageSize", "1000");
        option.put("regionId", regionId);
        Call<TunJson> call = apiInterface.getBranchbyId(token, option);
        call.enqueue(new Callback<TunJson>() {
            @Override
            public void onResponse(Call<TunJson> call, Response<TunJson> response) {
                hideLoading();
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null && response.body().getData().getResults() != null) {
                        for (TunJson.DataBean.ResultsBean dataBean : response.body().getData().getResults()) {
                            optionModels.add(new OptionModel(dataBean.getName(), dataBean.getId()));
                        }
                        OnItemClicklistener onItemClicklistener = position -> {
                            EventBus.getDefault().post(
                                    new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), type));
                            finish();
                        };
                        optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                        rvInfoCate.setAdapter(optionAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<TunJson> call, Throwable t) {

            }
        });
    }

    private void getRegion() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(1));
        option.put("pageSize", "1000");
        Call<RegionJson> call = apiInterface.getAllRegion(token, option);
        call.enqueue(new Callback<RegionJson>() {
            @Override
            public void onResponse(Call<RegionJson> call, Response<RegionJson> response) {
                hideLoading();
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null && response.body().getData().getResults() != null) {
                        for (RegionJson.DataBean.ResultsBean dataBean : response.body().getData().getResults()) {
                            optionModels.add(new OptionModel(dataBean.getName(), dataBean.getId()));
                        }
                        OnItemClicklistener onItemClicklistener = position -> {
                            EventBus.getDefault().post(
                                    new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getId(), type));
                            finish();
                        };
                        optionAdapter = new OptionAdapter(OptionActivity.this, optionModels, value, onItemClicklistener);
                        rvInfoCate.setAdapter(optionAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionJson> call, Throwable t) {

            }
        });
    }

    private void searchLocal(CharSequence s) {
        searchOptionModels.clear();
        s = s.toString().toLowerCase();
        for (int i = 0; i < optionModels.size(); i++) {
            if (optionModels.get(i).getName().toLowerCase().contains(s)) {
                searchOptionModels.add(optionModels.get(i));
            }
        }
        OnItemClicklistener onItemClicklistener = position -> {
            EventBus.getDefault().postSticky(new CategoryEvent(optionModels.get(position).getName(), optionModels.get(position).getValue(), type));
            finish();
        };
        optionAdapter = new OptionAdapter(
                OptionActivity.this, searchOptionModels, value, onItemClicklistener);
        rvInfoCate.setAdapter(optionAdapter);
    }
}
