package com.saleskit.cbbank.features.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.flowone.ProductDetailActivity;
import com.saleskit.cbbank.features.home.Product;
import com.saleskit.cbbank.features.home.ProductListAdapter;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.google.android.material.appbar.AppBarLayout;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ProductListActivity extends BaseActivity {
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.appbar_main)
    AppBarLayout appbarMain;
    @BindView(R.id.rv_product_list)
    RecyclerView rvProductList;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    private List<Product.DataBean> list = new ArrayList<>();
    private List<Product.DataBean> searchList = new ArrayList<>();
    private ProductListAdapter productListAdapter;
    private CustomerInfo.DataBean customer;
    private String type;
    private List<Product.DataBean> ownProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra(Constants.SCREEN_TYPE);
        if (type.equals(Constants.FROM_LOGIN)) {
            getDataProduct();
        } else {
            getData();
        }
        setupUi();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_product_list;
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

    private void getDataProduct() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<Product> call = apiInterface.getAllproduct(Constants.DEFAULT_AUTHOR);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                hideLoading();
                if (response.code() == 200) {
                    list.clear();
                    List<Product.DataBean> datas = response.body().getData();
                    for (Product.DataBean dataBean : datas) {
                        list.add(dataBean);
                    }
                    OnItemClicklistener onItemClicklistener = position -> {
                        if (list.get(position).getProductType() == 1 || list.get(position).getProductType() == 3) {
                            Intent i1 = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                            i1.putExtra(Constants.PRODUCT_TYPE, Constants.TYPE_HD);
                            i1.putExtra(Constants.PRODUCT_ID, list.get(position).getProductId());
                            i1.putExtra(Constants.TYPE_PRODUCT, list.get(position).getProductType());
                            i1.putExtra(Constants.CUSTOMER_ID, customer);
                            i1.putExtra(Constants.SCREEN_TYPE, type);
                            i1.putExtra(Constants.PRODUCT_NAME, list.get(position).getProductName());
                            startActivity(i1);
                        } else {
                            Intent i = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                            i.putExtra(Constants.PRODUCT_TYPE, Constants.CUSTOMER_HD);
                            i.putExtra(Constants.PRODUCT_ID, list.get(position).getProductId());
                            i.putExtra(Constants.CUSTOMER_ID, customer);
                            i.putExtra(Constants.SCREEN_TYPE, type);
                            i.putExtra(Constants.PRODUCT_NAME, list.get(position).getProductName());
                            startActivity(i);
                        }
                    };
                    productListAdapter = new ProductListAdapter(list,
                            ProductListActivity.this, onItemClicklistener);
                    rvProductList.setAdapter(productListAdapter);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable throwable) {
                hideLoading();
            }
        });
    }


    private void setupUi() {
        ivBack.setOnClickListener(v -> onBackPressed());
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvProductList.setLayoutManager(linearLayout);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    ivSearch.setVisibility(View.GONE);
                } else {
                    ivSearch.setVisibility(View.VISIBLE);
                }
                searchList.clear();
                if (list.size() > 0) {
                    for (Product.DataBean categoryModel : list) {
                        if (categoryModel.getProductName().toLowerCase().contains(s.toString())
                                || categoryModel.getProductName().toUpperCase().contains(s.toString())) {
                            searchList.add(categoryModel);
                        }
                    }
                    if (searchList.size() == 0) {
                        tvResult.setVisibility(View.VISIBLE);
                    } else {
                        tvResult.setVisibility(View.GONE);
                    }
                    OnItemClicklistener onItemClicklistener = position -> {
                        if (searchList.get(position).getProductType() == 1 || searchList.get(position).getProductType() == 3) {
                            Intent i1 = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                            i1.putExtra(Constants.PRODUCT_TYPE, Constants.TYPE_HD);
                            i1.putExtra(Constants.PRODUCT_ID, searchList.get(position).getProductId());
                            i1.putExtra(Constants.CUSTOMER_ID, customer);
                            i1.putExtra(Constants.SCREEN_TYPE, type);
                            i1.putExtra(Constants.TYPE_PRODUCT, searchList.get(position).getProductType());
                            i1.putExtra(Constants.PRODUCT_NAME, searchList.get(position).getProductName());
                            i1.putExtra(Constants.CUSTOMER_TYPE_PRODUCT, searchList.get(position).getCustomerType());
                            startActivity(i1);
                        } else {
                            Intent i = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                            i.putExtra(Constants.PRODUCT_TYPE, Constants.CUSTOMER_HD);
                            i.putExtra(Constants.PRODUCT_ID, searchList.get(position).getProductId());
                            i.putExtra(Constants.CUSTOMER_ID, customer);
                            i.putExtra(Constants.SCREEN_TYPE, type);
                            i.putExtra(Constants.PRODUCT_NAME, searchList.get(position).getProductName());
                            startActivity(i);
                        }
                    };
                    productListAdapter = new ProductListAdapter(searchList, ProductListActivity.this, onItemClicklistener);
                    rvProductList.setAdapter(productListAdapter);
                }

                ivSearch.setOnClickListener(v -> etSearch.setText(""));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getData() {

        Intent intent = getIntent();
        customer = (CustomerInfo.DataBean) intent.getSerializableExtra(Constants.CUSTOMER_ID);
        List<Product.DataBean> ownList = HawkHelper.getInstance(this).getListProduct();
        List<Product.DataBean> hdList = HawkHelper.getInstance(this).getListHDProduct();
        for (Product.DataBean dataBean : ownList) {
            list.add(dataBean);
        }
        for (Product.DataBean dataBean : hdList) {
            list.add(dataBean);
        }
        OnItemClicklistener onItemClicklistener = position -> {
            if (list.get(position).getProductType() == 1 || list.get(position).getProductType() == 3) {
                Intent i1 = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                i1.putExtra(Constants.PRODUCT_TYPE, Constants.TYPE_HD);
                i1.putExtra(Constants.PRODUCT_ID, list.get(position).getProductId());
                i1.putExtra(Constants.TYPE_PRODUCT, list.get(position).getProductType());
                i1.putExtra(Constants.CUSTOMER_ID, customer);
                i1.putExtra(Constants.SCREEN_TYPE, type);
                i1.putExtra(Constants.PRODUCT_NAME, list.get(position).getProductName());
                startActivity(i1);
            } else {
                Intent i = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                i.putExtra(Constants.PRODUCT_TYPE, Constants.CUSTOMER_HD);
                i.putExtra(Constants.PRODUCT_ID, list.get(position).getProductId());
                i.putExtra(Constants.CUSTOMER_ID, customer);
                i.putExtra(Constants.SCREEN_TYPE, type);
                i.putExtra(Constants.PRODUCT_NAME, list.get(position).getProductName());
                startActivity(i);
            }
        };
        productListAdapter = new ProductListAdapter(list, this, onItemClicklistener);
        rvProductList.setAdapter(productListAdapter);
    }
}
