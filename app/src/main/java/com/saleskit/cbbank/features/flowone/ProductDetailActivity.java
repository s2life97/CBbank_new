package com.saleskit.cbbank.features.flowone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.saleskit.cbbank.features.account.DownLoadTask;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.customer.CustomerListActivity;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;
import com.saleskit.cbbank.features.customer.add_new_customer.AddNewCustomerActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.detail.HDCustomerDetailActivity;
import com.saleskit.cbbank.features.home.FileNameAdapter;
import com.saleskit.cbbank.features.home.ProductDetail;
import com.saleskit.cbbank.features.home.activity.MainProductDetailActivity;
import com.saleskit.cbbank.features.home.activity.ProductInfoAdapter;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.PermissionUtil;
import com.koushikdutta.ion.Ion;

import org.greenrobot.eventbus.EventBus;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ProductDetailActivity extends BaseActivity {
    private static final String TAG = "ProductDetailActivity";
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.bt_creat)
    Button btCreat;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.rv_file)
    RecyclerView rvFile;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.product_type)
    TextView productType;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    @BindView(R.id.bt_read_more)
    LinearLayout btReadMore;
    @BindView(R.id.rv_file_name)
    RecyclerView rvFileName;
    @BindView(R.id.rv_des)
    RecyclerView rvDes;
    private String token;
    private int productID;
    private int typeProduct;
    private int customerType;
    private ProgressDialog progressDialog;
    private String type;
    private String title;
    private int PERMISSION_REQUEST_CODE = 0;
    private int filePosition = -1;
    private List<ProductDetail.DataBean.ProductFileModelsBean> listFiles = new ArrayList<>();
    private List<ProductDetail.DataBean.ProductFileModelsBean> files = new ArrayList<>();
    private CustomerInfo.DataBean customer;
    private ProductInfoAdapter productInfoAdapter;
    private boolean hasFormular;
    private String screenType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        showLoading();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tải xuống ...");
        Intent intent = getIntent();
        try {
            screenType = intent.getStringExtra(Constants.SCREEN_TYPE);
        } catch (Exception e) {
            screenType = "";
        }
        if (!TextUtils.isEmpty(screenType) && screenType.equals(Constants.FROM_LOGIN)) {
            btCreat.setVisibility(View.INVISIBLE);
        }
        type = intent.getStringExtra(Constants.PRODUCT_TYPE);
        if (type.equals(Constants.TYPE_HD)) {
            typeProduct = intent.getIntExtra(Constants.TYPE_PRODUCT, -1);
            customerType = intent.getIntExtra(Constants.CUSTOMER_TYPE_PRODUCT, -1);
        }
        title = intent.getStringExtra(Constants.PRODUCT_NAME);
        customer = (CustomerInfo.DataBean) intent.getSerializableExtra(Constants.CUSTOMER_ID);
        tvTitle.setText(title);
        productID = intent.getIntExtra(Constants.PRODUCT_ID, 0);
        token = HawkHelper.getInstance(this).getToken();
        setupSw();
        getData();
        setupUI();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (PermissionUtil.hasPermissions(this)) {
                downloadSelectedFile();
            } else {
                Toast.makeText(this, "Cấp quyền để tải file xuống!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupSw() {
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            getData();
        });
    }

    private void setupUI() {
        if (type.equals(Constants.TYPE_HD)) {
            productType.setText("Hồ sơ vay vốn");
        } else {
            productType.setText("Hồ sơ đăng ký");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvFile.setLayoutManager(linearLayoutManager);
        LinearLayoutManager fileNameLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvFileName.setLayoutManager(fileNameLinearLayoutManager);

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
        btCreat.setOnClickListener(v -> {
            if (type.equals(Constants.TYPE_HD)) {
                if (customer != null) {
                    Intent intent = new Intent(this, AddNewCustomerActivity.class);
                    EventBus.getDefault().postSticky(new TransferCustomerEvent(customer, productID, 0,
                            0, true, hasFormular, typeProduct, customerType));
                    startActivity(intent);
                } else {
                    Intent i = new Intent(this, CustomerListActivity.class);
                    i.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_DETAIL);
                    i.putExtra(Constants.TYPE_PRODUCT, typeProduct);
                    i.putExtra(Constants.PRODUCT_ID, productID);
                    i.putExtra(Constants.PRODUCT_FORMUALR, hasFormular);
                    i.putExtra(Constants.CUSTOMER_TYPE_PRODUCT, customerType);
                    startActivity(i);
                }
            } else {
                if (customer != null) {
                    Intent i = new Intent(this, HDCustomerDetailActivity.class);
                    i.putExtra(Constants.CUSTOMER_ID, customer);
                    i.putExtra(Constants.PRODUCT_ID, productID);
                    startActivity(i);
                } else {
                    Intent i = new Intent(this, CustomerListActivity.class);
                    i.putExtra(Constants.CUSTOMER_TYPE, Constants.CUSTOMER_HD);
                    i.putExtra(Constants.PRODUCT_ID, productID);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_product_detail;
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
    private void downloadSelectedFile() {
        if (files != null) {
            String fileName = files.get(filePosition).getFileName();
            String filePath = files.get(filePosition).getFilePath();
            new DownLoadTask(ProductDetailActivity.this, Constants.BASE_ARTICLE_URL + filePath, fileName);
            Log.d("File", filePath);
        } else {
            // Do something here
        }
    }
     private void getData() {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<ProductDetail> call = apiInterface.getDetailProduct(token, String.valueOf(productID));
        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                hideLoading();
                swAll.setRefreshing(false);
                if (response.code() == 200) {
                    ProductDetail.DataBean product = response.body().getData();
                    listFiles.clear();
                    files.clear();
                    hasFormular = product.isHasCalculationFormula();
                    listFiles = response.body().getData().getProductFileModels();
                    for (ProductDetail.DataBean.ProductFileModelsBean productFileModelsBean : listFiles) {
                        if (!TextUtils.isEmpty(productFileModelsBean.getFilePath())) {
                            files.add(productFileModelsBean);
                        }
                    }
                    List<ProductDetail.DataBean.DetailInformationsBean> detailInformationsBeanList =
                            response.body().getData().getDetailInformations();
                    btReadMore.setOnClickListener(view -> {
                        Intent intent = new Intent(ProductDetailActivity.this, MainProductDetailActivity.class);
                        intent.putExtra(Constants.PRODUCT_NAME, title);
                        intent.putExtra(Constants.PRODUCT_TYPE, (Serializable) detailInformationsBeanList);
                        startActivity(intent);
                    });
                    OnItemClicklistener onItemClicklistener = position -> {
                        filePosition = position;
                        if (PermissionUtil.hasPermissions(ProductDetailActivity.this)) {
                            downloadSelectedFile();
                        } else {
                            PermissionUtil.requestPermissions(ProductDetailActivity.this, PERMISSION_REQUEST_CODE);
                        }
                    };
                    if (type.equals(Constants.TYPE_HD)) {
                        tvDetail.setText(product.getProductDetail());
                        rvDes.setVisibility(View.GONE);
                        tvDetail.setVisibility(View.VISIBLE);
                    } else {
                        rvDes.setVisibility(View.VISIBLE);
                        tvDetail.setVisibility(View.GONE);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailActivity.this, RecyclerView.VERTICAL, false);
                        rvDes.setLayoutManager(linearLayoutManager);
                        List<ProductDetail.DataBean.DetailInformationsBean> hightlightList = new ArrayList<>();
                        for (ProductDetail.DataBean.DetailInformationsBean detailInformationsBean : detailInformationsBeanList) {
                            if (detailInformationsBean.isHighlight()) {
                                hightlightList.add(detailInformationsBean);
                            }
                        }
                        productInfoAdapter = new ProductInfoAdapter(ProductDetailActivity.this, hightlightList);
                        rvDes.setAdapter(productInfoAdapter);
                    }
                    FileAdapter fileAdapter = new FileAdapter(onItemClicklistener, files, ProductDetailActivity.this);
                    rvFile.setAdapter(fileAdapter);
                    FileNameAdapter fileNameAdapter = new FileNameAdapter(ProductDetailActivity.this, listFiles);
                    rvFileName.setAdapter(fileNameAdapter);
                }
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable throwable) {

            }
        });
    }

    private void downLoadFile(String linkFileUrl, String fileName) {
        progressDialog.show();
        AppUtil.deleteExistFile(AppUtil.getSavePath() + fileName);
        String filePath = AppUtil.getSavePath();
        Ion.with(this)
                .load(Constants.BASE_ARTICLE_URL + linkFileUrl)
                .progressDialog(progressDialog)
                .progress((downloaded, total) -> {
                })
                .write(new File(filePath, fileName))
                .setCallback((e, file) -> {
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                    progressDialog.hide();
                    Toast.makeText(ProductDetailActivity.this, getResources().getString(R.string.success_down_report), Toast.LENGTH_SHORT).show();
                });
    }

    public static void hideKeyboard(Activity context) {
        if (context.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
