package com.saleskit.cbbank.features.customer.add_new_customer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Collateral;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.account.ProcessBeam;
import com.saleskit.cbbank.features.base.BaseFragment;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.FragmentComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Customer;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class CollateralFragment extends BaseFragment implements CollateralView {
    private static final String TAG = "CollateralFragment";
    @BindView(R.id.appbar_main)
    AppBarLayout appbarMain;
    @BindView(R.id.rv_new)
    RecyclerView rvNew;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.bt_continue)
    Button btContinue;
    @BindView(R.id.fl_end)
    FrameLayout flEnd;
    @BindView(R.id.fl_add)
    FloatingActionButton flAdd;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    private Context context;
    private View view;
    private int productId;
    private List<String> collaterals = new ArrayList<>();
    private CollateralAdapter collateralAdapter;
    private String token;
    private String customerId;
    private Customer.DataBean customer;
    List<Collateral.DataBean> list = new ArrayList<>();
    public static int numberAdd = 0;
    public static double value = 0;
    private double realValue;
    private boolean doneStep = true;
    private boolean hasFormular = false;

    public CollateralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_collateral, container, false);
        ButterKnife.bind(this, view);
        token = HawkHelper.getInstance(context).getToken();
        setupSwipe();
        setupUI();
        AppUtil.hideKeyboard(getActivity());
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    private void setupSwipe() {
        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            getData(customerId);
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            EventBus.getDefault().post(new FragmentChangedEvent(3, "Tài sản đảm bảo"));
        }
    }

    private void setupUI() {
        rvNew.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rvNew.setLayoutManager(linearLayoutManager);
        flAdd.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), InformationCollateralActivity.class);
            i.putExtra(Constants.CUSTOMER_ID, customerId);
            i.putExtra(Constants.PRODUCT_ID, productId);
            startActivity(i);
        });

        btContinue.setOnClickListener(v -> {
            if (!doneStep) {
                AddNewCustomerActivity.viewPager.setCurrentItem(3);
            } else {
                if (list.size() > 0) {
                    APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
//                    int process;
//                    if (hasFormular) {
//                        process = 3;
//                    } else {
//                        process = 2;
//                    }
                    Call<MessageBean> call = apiInterface.changeProcess3(token, customerId);
                    call.enqueue(new Callback<MessageBean>() {
                        @Override
                        public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                            if (response.code() == 200) {
                                AddNewCustomerActivity.viewPager.setCurrentItem(3);
                                collateralAdapter.setEdit();
                                changeView();
                                EventBus.getDefault().post(new ConfirmEvent(customerId, realValue));
                            } else if (response.code() == 400) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response.errorBody().string());
                                    JSONObject json_object = (JSONObject) jsonArray.get(0);
                                    String mess = json_object.getString("errorMessage");
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

                        }
                    });
                } else {
                    Toast.makeText(context, "Bạn chưa thêm tài sản nào!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changeView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_collateral;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        btContinue.setOnClickListener(view1 -> AddNewCustomerActivity.viewPager.setCurrentItem(3));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Subscribe
    public void onReceiveCustomerId(ResultEvent resultEvent) {
        customerId = resultEvent.result;
        productId = Integer.valueOf(resultEvent.productId);
        getData(customerId);
    }

    private void getData(String id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("customerProfileId", String.valueOf(id));
        Call<Collateral> call = apiInterface.getListCollaterl(token, option);
        call.enqueue(new Callback<Collateral>() {
            @Override
            public void onResponse(Call<Collateral> call, Response<Collateral> response) {
                value = 0;
                list.clear();
                value = 0;
                swAll.setRefreshing(false);
                if (response.code() == 200) {
                    list = response.body().getData();
                    collateralAdapter = new CollateralAdapter(list, context, CollateralFragment.this, doneStep);
                    rvNew.setAdapter(collateralAdapter);
                    realValue = 0;
                    for (Collateral.DataBean collateral : list) {
                        double value = collateral.getCollateralValue();
//                        long sumValue = Math.round(value);
//                        Log.d(TAG, "onResponse: " + sumValue);
                        double rate = collateral.getRateOfLending();
                        realValue += value * rate / 100;
                    }
                    value = realValue;
                    Log.e(TAG, "onResponse: " + realValue);
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
                    String finalAmount = numberFormat
                            .format(realValue)
                            .replaceAll("[^0123456789.,]", "");
//                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
//                    String result = format.format(value);
                    RangeCreditFragment.etRangeOwn.setText(finalAmount);
                    Log.d(TAG, "onResponse:  " + value);
                } else {
                    Toast.makeText(context, R.string.cannot_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Collateral> call, Throwable t) {
            }
        });
    }

    @Subscribe()
    public void reload(ReloadEvent reloadEvent) {
        if (reloadEvent.content.equals("reload")) {
            getData(customerId);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        numberAdd = 0;
    }

    @Override
    public void delete(int position) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<MessageBean> call = apiInterface.delateItem(token, String.valueOf(list.get(position).getCustomerCollateralId()));
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.code() == 200) {
                    list.remove(position);
                    collateralAdapter.notifyDataSetChanged();
                    getData(customerId);
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(int position) {
        Intent i = new Intent(getActivity(), InformationCollateralActivity.class);
        i.putExtra(Constants.CUSTOMER_ID, customerId);
        i.putExtra(Constants.PRODUCT_ID, productId);
        i.putExtra(Constants.COLLATERAL_TYPE, list.get(position));
        startActivity(i);
    }

    @Subscribe(sticky = true)
    public void onEditEvent(TransferCustomerEvent transferCustomerEvent) {
        productId = transferCustomerEvent.productId;
        if (transferCustomerEvent.process >= 2) {
            customerId = String.valueOf(transferCustomerEvent.customerProfileId);
            getData(customerId);
        }
        hasFormular = transferCustomerEvent.hasFormular;
        doneStep = transferCustomerEvent.canEdit;
        if (!doneStep) {
            flAdd.hide();
            if (transferCustomerEvent.hasFormular) {
                if (transferCustomerEvent.process <= 3) {
                    btContinue.setVisibility(View.GONE);
                }
            } else {
                if (transferCustomerEvent.process <= 2) {
                    btContinue.setVisibility(View.GONE);
                }
            }
        }


    }
}
