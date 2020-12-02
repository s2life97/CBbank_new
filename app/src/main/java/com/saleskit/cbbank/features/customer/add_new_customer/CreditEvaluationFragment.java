package com.saleskit.cbbank.features.customer.add_new_customer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.CheckEvaluadate;
import com.saleskit.cbbank.features.account.ControlBean;
import com.saleskit.cbbank.features.base.BaseFragment;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.appointment.CategoryEvent;
import com.saleskit.cbbank.features.appointment.ClearableEditText;
import com.saleskit.cbbank.features.appointment.OptionActivity;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.FragmentComponent;
import com.saleskit.cbbank.util.AppUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditEvaluationFragment extends BaseFragment implements CreatEvaluationView, OnItemClicklistener {
    private static final String TAG = "CreditEvaluationFragmen";
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_evaluate_done)
    FrameLayout tvEvaluateDone;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.bt_evaluate)
    Button btEvaluate;
    @BindView(R.id.bt_next)
    Button btNext;
    private Context context;
    private View view;
    private EvaluadateAdapter evaluadateAdapter;
    private List<ControlBean.DataBean> list = new ArrayList<>();
    private ClearableEditText clearableEditText;
    private String type;
    private String token, customerProfileId, customerId;
    private boolean doneStep = true;
    int selectPosition;
    @Inject
    CreditEvaluationPresenter creditEvaluationPresenter;
    private int productId;
    private ArrayList<String> statusList = new ArrayList<>();
    private List<CheckEvaluadate.ScoringDetailModelsBean> scoringDetailModelsBeans = new ArrayList<>();
    private String filedName;
    private String description;
    private boolean hasFormular;

    public CreditEvaluationFragment() {
        // Required empty public constructor
    }

    static boolean canNext = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_credit_evaluation, container, false);
        ButterKnife.bind(this, view);
        token = HawkHelper.getInstance(context).getToken();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rvMain.setLayoutManager(linearLayoutManager);

        btNext.setOnClickListener(v -> {
            AppUtil.hideKeyboard(getActivity());
            if (!doneStep) {
                AddNewCustomerActivity.viewPager.setCurrentItem(2);
            } else {
                showLoading();
                creditEvaluationPresenter.postToNextStep(context, token, customerProfileId);
            }

        });
        return view;
    }

    private void getControl() {
        creditEvaluationPresenter.getAllControl(context, token, String.valueOf(customerProfileId));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            EventBus.getDefault().post(new FragmentChangedEvent(2, "Tiêu chí đánh giá"));
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_credit_evaluation;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        creditEvaluationPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        creditEvaluationPresenter.detachView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        btEvaluate.setOnClickListener(v -> {
//            tvEvaluateDone.setVisibility(View.VISIBLE);
//            btNext.setEnabled(true);
//            canNext = true;
//        });

        btEvaluate.setOnClickListener(view1 -> {
            if (!doneStep) {
                return;
            } else {
                int size = scoringDetailModelsBeans.size();
                Log.e(TAG, "Kich thuoc: " + size);
                for (int i = 0; i < size; i++) {
                    Log.e(TAG, "onViewCreated: " + scoringDetailModelsBeans.get(i).getValue()
                            + " fl  " + scoringDetailModelsBeans.get(i).getFieldName());
                    if (scoringDetailModelsBeans.get(i).getValue() == null || TextUtils.isEmpty(scoringDetailModelsBeans.get(i).getValue())) {
                        Toast.makeText(context, scoringDetailModelsBeans.get(i).getDescription() + " chưa lựa chọn!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                showLoading();
                creditEvaluationPresenter.checkCondition(context, token, customerId,
                        productId, customerProfileId, scoringDetailModelsBeans);
            }

        });

    }

    @Subscribe
    public void onReceiveCustomerId(ResultEvent resultEvent) {
        if (hasFormular) {
            customerProfileId = resultEvent.result;
            customerId = resultEvent.customerId;
            productId = Integer.parseInt(resultEvent.productId);
            btNext.setEnabled(false);
//        doneStep = true;
            canNext = false;
            tvEvaluateDone.setVisibility(View.GONE);
            getControl();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        canNext = false;
    }

    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        if (type.equals(Constants.ENUM)) {
            clearableEditText.setText(categoryEvent.value);
            int size = scoringDetailModelsBeans.size();
            Log.e(TAG, "Size: " + size);
            Log.e(TAG, "onReceiveValue: " + categoryEvent.id);
            scoringDetailModelsBeans.set(selectPosition,
                    new CheckEvaluadate.ScoringDetailModelsBean(filedName, String.valueOf(categoryEvent.id), description
                    ));
//            for (int i = 0; i < size; i++) {
//                if (scoringDetailModelsBeans.get(i).getFieldName().trim().equalsIgnoreCase(filedName.trim())) {
//                    scoringDetailModelsBeans.set(i,
//                            new CheckEvaluadate.ScoringDetailModelsBean(filedName, String.valueOf(categoryEvent.id), description
//                            ));
//                    break;
//                }
//            }
        }
    }

    @Subscribe(sticky = true)
    public void onReceiveCustomer(TransferCustomerEvent transferCustomerEvent) {
        hasFormular = transferCustomerEvent.hasFormular;
        if (hasFormular) {
            productId = transferCustomerEvent.productId;
            customerProfileId = String.valueOf(transferCustomerEvent.customerProfileId);
            customerId = String.valueOf(transferCustomerEvent.customer.getCustomerId());
            doneStep = transferCustomerEvent.canEdit;
            if (!doneStep) {
                btNext.setEnabled(true);
                canNext = true;
                btEvaluate.setEnabled(false);
            }
            getControl();
        }
        if (!doneStep) {
            if (transferCustomerEvent.process <= 2) {
                btNext.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showUi(List<ControlBean.DataBean> controlBeans) {
        scoringDetailModelsBeans.clear();

        for (ControlBean.DataBean dataBean : controlBeans) {
            scoringDetailModelsBeans.add(new CheckEvaluadate.ScoringDetailModelsBean(dataBean.getFieldName(),
                    dataBean.getSelectedValue() == null ? "" : String.valueOf(dataBean.getSelectedValue()), dataBean.getDescription()));
        }
        int size = scoringDetailModelsBeans.size();
        evaluadateAdapter = new EvaluadateAdapter(controlBeans, context,
                CreditEvaluationFragment.this, doneStep);
        rvMain.setAdapter(evaluadateAdapter);
        Log.e(TAG, "Size: " + size);
        list = controlBeans;
    }

    @Override
    public void intentNextScreen() {
        doneStep = true;
//        disableView();
        AddNewCustomerActivity.viewPager.setCurrentItem(2);
    }

    @Override
    public void onButtonClick(int position, ClearableEditText tvContent) {
        type = Constants.ENUM;
        description = scoringDetailModelsBeans.get(position).getDescription();
        clearableEditText = tvContent;
        filedName = scoringDetailModelsBeans.get(position).getFieldName();
        selectPosition = position;
        Intent i = new Intent(getActivity(), OptionActivity.class);
        int selectedValue = -1;
        if (!TextUtils.isEmpty(scoringDetailModelsBeans.get(position).getValue())) {
            if (scoringDetailModelsBeans.get(position).getValue().contains(".")) {
                try {
                    selectedValue = Integer.parseInt(scoringDetailModelsBeans.get(position).getValue().split(".")[0]);
                } catch (Exception e) {
                    selectedValue = -1;
                }
            } else {
                selectedValue = Integer.parseInt(scoringDetailModelsBeans.get(position).getValue());
            }
        }
        i.putExtra(Constants.ENUM_LIST, (Serializable) list.get(position).getOptionControlModels());
        i.putExtra(Constants.CATEGORY_TYPE, Constants.ENUM);
        i.putExtra(Constants.CATEGORY_VALUE, selectedValue);
        i.putExtra(Constants.CATEGORY_NAME, list.get(position).getDescription());
        startActivity(i);
    }

    @Override
    public void onTextChange(Editable editable, int postion) {
        filedName = list.get(postion).getFieldName();
        scoringDetailModelsBeans.set(postion, new CheckEvaluadate.ScoringDetailModelsBean(filedName, editable.toString(),
                list.get(postion).getDescription()));
    }

    @Override
    public void showResult(CreatResultBean.DataBean dataBean) {
        if (dataBean.isResult()) {
            tvEvaluateDone.setVisibility(View.VISIBLE);
//                        CreatResultBean.DataBean dataBean = response.body().getData();
////                        if (dataBean.isResult()) {
            tvResult.setText("Khách hàng đạt tiêu chí");
            AppUtil.setBackgroundForView(tvResult, context, R.color.green_title);
            btNext.setEnabled(true);
            canNext = true;
            scrollView.scrollTo(0, 0);
            tvEvaluateDone.requestFocus();
        } else {
            scrollView.scrollTo(0, 0);
            tvEvaluateDone.requestFocus();
            tvEvaluateDone.setVisibility(View.VISIBLE);
            tvResult.setText("Khách hàng chưa đạt tiêu chí");
            AppUtil.setBackgroundForView(tvResult, context, R.color.red);
            tvEvaluateDone.requestFocus();
        }
    }

    @Override
    public void onItemClick(int position) {

    }

}
