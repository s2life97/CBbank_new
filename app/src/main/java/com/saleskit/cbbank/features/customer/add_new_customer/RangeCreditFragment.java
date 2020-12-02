package com.saleskit.cbbank.features.customer.add_new_customer;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.ConfirmModel;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.base.BaseFragment;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerProfile;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.FragmentComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class RangeCreditFragment extends BaseFragment {
    private static final String TAG = "RangeCreditFragment";
    public static TextView etRangeOwn;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.et_time_own)
    ClearableEditText etTimeOwn;
    @BindView(R.id.et_money_own)
    ClearableEditText etMoneyOwn;
    @BindView(R.id.bt_complete)
    Button btComplete;
    @BindView(R.id.tv_range_name)
    TextView tvRangeName;
    private View view;
    private String id;
    private Context context;
    private String token;
    private String dateofBirth, name;
    private boolean doneStep = true;
    private boolean hasFormular;

    public RangeCreditFragment() {
        // Required empty public constructor
    }

    public static boolean canNext = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_range_credit, container, false);
        ButterKnife.bind(this, view);
        etRangeOwn = view.findViewById(R.id.et_range_own);
        token = HawkHelper.getInstance(context).getToken();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        etMoneyOwn.addTextChangedListener(AppUtil.onTextChangedListener(etMoneyOwn));
//        etTimeOwn.addTextChangedListener(AppUtil.onTextChangedListener(etTimeOwn));

        setupUI();
        return view;

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_range_credit;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {

    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            EventBus.getDefault().post(new FragmentChangedEvent(4, "Hạn mức tín dụng"));
        }
    }

    private void setupUI() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String finalAmount = numberFormat
                .format(CollateralFragment.value)
                .replaceAll("[^0123456789.,]", "");
        etRangeOwn.setText(finalAmount);

        btComplete.setOnClickListener(v -> {
            Log.e(TAG, "setupUI: " + doneStep);
            if (!doneStep) {
                AddNewCustomerActivity.viewPager.setCurrentItem(4);
            } else {
                if (TextUtils.isEmpty(etTimeOwn.getText().toString())) {
                    Toast.makeText(context, "thời hạn vay không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etMoneyOwn.getText().toString())) {
                    Toast.makeText(context, "Số tiền vay không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                String text = etMoneyOwn.getText().toString();
                String moneyDisplay = text.replace(",", "");
                try {
                    double range = CollateralFragment.value;
                    double money = Double.parseDouble(moneyDisplay);

                    if (money > range) {
                        Toast.makeText(context, getResources().getString(R.string.money_condition), Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                }
                showLoading();
                APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
                int process;
//                if (hasFormular) {
//                    process = 4;
//                } else {
//                    process = 3;
//                }
                Call<MessageBean> call = apiInterface.confirmCreat(token, new ConfirmModel(id, text,
                        etTimeOwn.getText().toString(), 4));
                call.enqueue(new Callback<MessageBean>() {
                    @Override
                    public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            AddNewCustomerActivity.viewPager.setCurrentItem(4);
                            canNext = true;
//                            if(doneStep){
//                                etTimeOwn.setFocusable(false);
//                                etMoneyOwn.setFocusable(false);
//                            }
                            EventBus.getDefault().postSticky(new FinalEvent(dateofBirth, name,
                                    etMoneyOwn.getText().toString(), etTimeOwn.getText().toString(), String.valueOf(id)));
                        } else if (response.code() == 400) {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                String mess = jObjError.getString("errorMessage");
                                Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageBean> call, Throwable t) {
                        hideLoading();
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Subscribe
    public void onReceiveCustomerId(ConfirmEvent resultEvent) {
        id = resultEvent.customerID;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String finalAmount = numberFormat
                .format(CollateralFragment.value)
                .replaceAll("[^0123456789.,]", "");
        etRangeOwn.setText(finalAmount);
    }

    @Subscribe
    public void onReceiveData(ResultEvent resultEvent) {
        dateofBirth = resultEvent.dateOfBirth;
        name = resultEvent.name;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        canNext = false;
    }

    @Subscribe(sticky = true)
    public void onReceiveEditEvent(TransferCustomerEvent transferCustomerEvent) {
        int typeProduct = transferCustomerEvent.typeProduct;
        if (typeProduct == 3) {
            tvRangeName.setText("Hạn mức bảo lãnh");
        }
        String birth = transferCustomerEvent.customer.getDateOfBirth();
        dateofBirth = AppUtil.format(birth);
        name = transferCustomerEvent.customer.getLastName() + " " + transferCustomerEvent.customer.getFirstName();
        doneStep = transferCustomerEvent.canEdit;
        if (!doneStep) {
            etMoneyOwn.setFocusable(false);
            etTimeOwn.setFocusable(false);
            if (transferCustomerEvent.hasFormular) {
                if (transferCustomerEvent.process <= 4) {
                    btComplete.setVisibility(View.GONE);
                }
            } else {
                if (transferCustomerEvent.process <= 3) {
                    btComplete.setVisibility(View.GONE);
                }
            }
        }
        if (transferCustomerEvent.process >= 3) {
            hasFormular = transferCustomerEvent.hasFormular;
            id = String.valueOf(transferCustomerEvent.customerProfileId);
            APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
            Call<CustomerProfile> call = apiInterface.getCustomerProfie(token, String.valueOf(transferCustomerEvent.customerProfileId));
            call.enqueue(new Callback<CustomerProfile>() {
                @Override
                public void onResponse(Call<CustomerProfile> call, Response<CustomerProfile> response) {
                    if (response.code() == 200) {
                        CustomerProfile.DataBean dataBean = response.body().getData();
//                        etRangeOwn.setText(NumberFormat.getInstance().format(dataBean.getMoneyMaxAmount()));
                        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
                        String finalAmount = numberFormat
                                .format(dataBean.getMoneyMaxAmount())
                                .replaceAll("[^0123456789.,]", "");
//                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
//                    String result = format.format(value);
                        RangeCreditFragment.etRangeOwn.setText(finalAmount);
                        CollateralFragment.value = dataBean.getMoneyMaxAmount();
                        etMoneyOwn.setText(NumberFormat.getInstance(Locale.US).format(dataBean.getMoney()));
                        etTimeOwn.setText(String.valueOf(dataBean.getPeriodTime()));
                    }
                }

                @Override
                public void onFailure(Call<CustomerProfile> call, Throwable t) {

                }
            });
        }
    }
}
