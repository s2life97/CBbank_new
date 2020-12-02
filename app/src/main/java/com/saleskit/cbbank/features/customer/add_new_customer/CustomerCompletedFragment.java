package com.saleskit.cbbank.features.customer.add_new_customer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.MessageJson;
import com.saleskit.cbbank.features.base.BaseFragment;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerProfile;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.home.activity.HomeActivity2;
import com.saleskit.cbbank.injection.component.FragmentComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerCompletedFragment extends BaseFragment {
    private static final String TAG = "CustomerCompletedFragme";
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_time_own)
    TextView tvTimeOwn;
    @BindView(R.id.bt_complete)
    Button btComplete;
    private Context context;
    private View view;
    private String customerProfildId;
    private String token;
    private boolean doneStep= true;

    public CustomerCompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_customer_completed, container, false);
        ButterKnife.bind(this, view);
        token = HawkHelper.getInstance(context).getToken();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setupUI();
        return view;
    }

    private void setupUI() {
        btComplete.setOnClickListener(v -> {
            Log.e(TAG, "setupUI: " + doneStep );
            if (!doneStep) {
                getActivity().finish();
            } else {
                showLoading();
                APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
                Call<MessageJson> call = apiInterface.confirmFinalStep(token, customerProfildId);
                call.enqueue(new Callback<MessageJson>() {
                    @Override
                    public void onResponse(Call<MessageJson> call, Response<MessageJson> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            Intent i = new Intent(context, HomeActivity2.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            Toast.makeText(context, "Hoàn thành hồ sơ!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONArray jsonArray = new JSONArray(response.errorBody().string());
                                JSONObject json_object = (JSONObject) jsonArray.get(0);
                                String mess = json_object.getString("errorMessage");
                                Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageJson> call, Throwable t) {
                        hideLoading();
                    }
                });
//            if (AddNewCustomerActivity.isFromHome) {
//                Intent i = new Intent(context, HomeActivity2.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//                getActivity().overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//            } else {
//                EventBus.getDefault().post(new BackPersonalEvent());
//            }

            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            EventBus.getDefault().post(new FragmentChangedEvent(5, "Điểm tín dụng khách hàng"));
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_customer_completed;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Subscribe(sticky = true)
    public void receiveData(FinalEvent finalEvent) {
        tvName.setText(finalEvent.name);
        tvBirth.setText(finalEvent.date);
        tvMoney.setText(finalEvent.money);
        tvTimeOwn.setText(finalEvent.time);
        customerProfildId = finalEvent.customerProfileId;
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(sticky = true)
    public void onEditEvent(TransferCustomerEvent transferCustomerEvent) {
        tvName.setText(transferCustomerEvent.customer.getLastName() + " " + transferCustomerEvent.customer.getFirstName());
        String date = transferCustomerEvent.customer.getDateOfBirth();
        doneStep = transferCustomerEvent.canEdit;
        String dateShow = AppUtil.format(date);
        tvBirth.setText(dateShow);
        if (transferCustomerEvent.process >=4 ) {
            customerProfildId = String.valueOf(transferCustomerEvent.customerProfileId);
            String name = transferCustomerEvent.customer.getLastName() + " " + transferCustomerEvent.customer.getFirstName();
            tvName.setText(name);
            String birth = transferCustomerEvent.customer.getDateOfBirth();
            String dateBirth = AppUtil.format(birth);
            tvBirth.setText(dateBirth);
            APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
            Call<CustomerProfile> call = apiInterface.getCustomerProfie(token, String.valueOf(transferCustomerEvent.customerProfileId));
            call.enqueue(new Callback<CustomerProfile>() {
                @Override
                public void onResponse(Call<CustomerProfile> call, Response<CustomerProfile> response) {
                    if (response.code() == 200) {
                        CustomerProfile.DataBean dataBean = response.body().getData();
                        tvMoney.setText(NumberFormat.getInstance(Locale.US).format(dataBean.getMoney()));
                        tvTimeOwn.setText(String.valueOf(dataBean.getPeriodTime()));
                    }
                }

                @Override
                public void onFailure(Call<CustomerProfile> call, Throwable t) {

                }
            });

        }
    }
}
