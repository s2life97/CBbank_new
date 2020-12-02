package com.saleskit.cbbank.features.customer.add_new_customer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Collateral;
import com.saleskit.cbbank.features.account.CollateralInsert;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.appointment.CategoryEvent;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.appointment.OptionActivity;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class InformationCollateralActivity extends BaseActivity {
    private static final String TAG = "InformationCollateralAc";
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.bt_complete)
    Button btComplete;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bt_pick_type)
    LinearLayout btPickType;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.bt_pick_price)
    LinearLayout btPickPrice;
    @BindView(R.id.et_des)
    ClearableEditText etDes;
    @BindView(R.id.et_max_rate)
    TextView etMaxRate;
    @BindView(R.id.et_property_value)
    ClearableEditText etPropertyValue;
    private String priceType;
    private String type;
    private int collateral, collateralId = -1;
    private String token;
    private String customerId;
    private Collateral.DataBean collateralBean;
    private boolean isEdit;
    private int productId;
    private String ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_collateral);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        token = HawkHelper.getInstance(this).getToken();
        Intent intent = getIntent();
        collateralBean = (Collateral.DataBean) intent.getSerializableExtra(Constants.COLLATERAL_TYPE);
        productId = intent.getIntExtra(Constants.PRODUCT_ID, 0);
        if (collateralBean != null) {
            isEdit = true;
            etMaxRate.setText(String.valueOf(collateralBean.getRateOfLending()));
//            int value = (int) Math.round(collateralBean.getCollateralValue());
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
            String value = numberFormat
                    .format(collateralBean.getCollateralValue())
                    .replaceAll("[^0123456789.,]", "");
            ratio = String.valueOf(collateralBean.getRateOfLending());
            NumberFormat formatter = new DecimalFormat("#,###");
            String formattedNumber = formatter.format(collateralBean.getCollateralValue());
            etPropertyValue.setText(NumberFormat.getInstance(Locale.US).format(collateralBean.getCollateralValue()));
            etDes.setText(collateralBean.getDescription());
            collateral = collateralBean.getPropertyTypeId();
            collateralId = collateralBean.getCustomerCollateralId();
            tvContent.setText(collateralBean.getPropertyTypeName());
            tvType.setText(collateralBean.getPropertyFormName());
            priceType = String.valueOf(collateralBean.getPropertyFormId());
        } else {
            isEdit = false;
        }
        etPropertyValue.addTextChangedListener(AppUtil.onTextChangedListener(etPropertyValue));
        customerId = intent.getStringExtra(Constants.CUSTOMER_ID);
        ivBack.setOnClickListener(v -> finish());
        btComplete.setOnClickListener(v -> {
            if (collateral == 0) {
                Toast.makeText(this, getResources().getString(R.string.collateral_not_select), Toast.LENGTH_SHORT).show();
                tvContent.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(priceType)) {
                Toast.makeText(this, getResources().getString(R.string.price_type_none), Toast.LENGTH_SHORT).show();
                tvType.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(etPropertyValue.getText().toString())) {
//                etValue.setError(getResources().getString(R.string.file_must_not_empty));
                Toast.makeText(this, "Vui lòng nhập giá trị tài sản", Toast.LENGTH_SHORT).show();
                etPropertyValue.requestFocus();
                return;
            }
//            Timber.e("rate" + etMaxRate.getText().toString());
//            String rate = etMaxRate.getText().toString();
//            if (rate.contains(".")) {
//                String rateText = rate.trim().split("\\.")[0];
//                try {
//                    if (Integer.parseInt(rateText) > 100) {
//                        Toast.makeText(this, getResources().getString(R.string.rate_over_limit), Toast.LENGTH_SHORT).show();
//                        etMaxRate.requestFocus();
//                        return;
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(this, getResources().getString(R.string.rate_over_limit), Toast.LENGTH_SHORT).show();
//                    etMaxRate.requestFocus();
//                    return;
//                }
//            } else {
//                if (Integer.parseInt(etMaxRate.getText().toString()) > 100) {
//                    Toast.makeText(this, getResources().getString(R.string.rate_over_limit), Toast.LENGTH_SHORT).show();
//                    etMaxRate.requestFocus();
//                    return;
//                }
//            }
            showLoading();
            if (isEdit) {
                APIWebservices apiInterface = NetworkUtil.getCBclient(InformationCollateralActivity.this).create(APIWebservices.class);
                Call<MessageBean> call = apiInterface.update(token, new CollateralInsert(
                        collateralId, customerId, collateral,
                        priceType,
                        etPropertyValue.getText().toString().replace(",", ""),
                        ratio, etDes.getText().toString()
                ));
                call.enqueue(new Callback<MessageBean>() {
                    @Override
                    public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            finish();
                            EventBus.getDefault().post(new ReloadEvent("reload"));
                            Toast.makeText(InformationCollateralActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 400) {
                            try {
                                JSONArray jsonArray = new JSONArray(response.errorBody().string());
                                JSONObject json_object = (JSONObject) jsonArray.get(0);
                                String mess = json_object.getString("errorMessage");
                                Toast.makeText(InformationCollateralActivity.this, mess, Toast.LENGTH_SHORT).show();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(InformationCollateralActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageBean> call, Throwable t) {
                        hideLoading();
                    }
                });
            } else {
                APIWebservices apiInterface = NetworkUtil.getCBclient(InformationCollateralActivity.this).create(APIWebservices.class);
                Call<MessageBean> call = apiInterface.postNewCollaterl(token, new CollateralInsert(
                        0, customerId, collateral,
                        priceType,
                        etPropertyValue.getText().toString().replace(",", ""),
                        ratio, etDes.getText().toString()

                ));
                call.enqueue(new Callback<MessageBean>() {
                    @Override
                    public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            CollateralFragment.numberAdd += 1;
                            finish();
                            EventBus.getDefault().post(new ReloadEvent("reload"));
                        } else if (response.code() == 400) {
                            try {
                                JSONArray jsonArray = new JSONArray(response.errorBody().string());
                                JSONObject json_object = (JSONObject) jsonArray.get(0);
                                String mess = json_object.getString("errorMessage");
                                Toast.makeText(InformationCollateralActivity.this, mess, Toast.LENGTH_SHORT).show();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                                Timber.e(" casd " + e.getMessage());
                            }
                        } else {
                            Toast.makeText(InformationCollateralActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageBean> call, Throwable t) {
                        hideLoading();
                    }
                });
            }
        });

        btPickType.setOnClickListener(v -> {
            type = Constants.PROPERTY;
            Intent i = new Intent(InformationCollateralActivity.this, OptionActivity.class);
            btPickType.setEnabled(false);
            new Handler().postDelayed(() -> btPickType.setEnabled(true), 1000);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.PROPERTY);
            i.putExtra(Constants.CATEGORY_VALUE, -1);
            i.putExtra(Constants.PRODUCT_ID, productId);
            startActivity(i);
        });

        btPickPrice.setOnClickListener(v -> {
            if(collateral==0){
                Toast.makeText(this, "Vui lòng chọn loại tài sản!", Toast.LENGTH_SHORT).show();
                return;
            }
            type = Constants.PRICE;
            Intent i = new Intent(InformationCollateralActivity.this, OptionActivity.class);
            btPickPrice.setEnabled(false);
            new Handler().postDelayed(() -> btPickPrice.setEnabled(true), 1000);
            i.putExtra(Constants.CATEGORY_TYPE, Constants.PRICE);
            i.putExtra(Constants.CATEGORY_VALUE, -1);
            i.putExtra(Constants.PRODUCT_ID, productId);
            i.putExtra(Constants.COLLATERAL_TYPE, collateral);
            startActivity(i);
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_information_collateral;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        switch (categoryEvent.category) {
            case Constants.PROPERTY:
                tvContent.setText(categoryEvent.value);
                collateral = Integer.valueOf(categoryEvent.categoryId);
                priceType ="";
                etMaxRate.setText("");
                tvType.setText("Chọn hình thức");
                break;
            case Constants.PRICE:
                tvType.setText(categoryEvent.value);
                priceType = categoryEvent.categoryId;
                etMaxRate.setText(String.valueOf(categoryEvent.ratio));
                ratio = String.valueOf(categoryEvent.ratio);
                break;
        }

    }
}
