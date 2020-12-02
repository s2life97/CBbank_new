package com.saleskit.cbbank.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.feng.fixtablelayout.inter.IDataAdapter;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.InterestDetail;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.TunFixTableLayout;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlxActivity extends BaseActivity {
    @BindView(R.id.ftl)
    TunFixTableLayout ftl;
    //
    @BindView(R.id.btnBack)
    ImageView btnBack;
//    @BindView(R.id.rl_blx)
//    RecyclerView rvBlx;

    private String token;
    private int id;
    private String[] titles = {"Loại kỳ hạn\n   ", "Trả lãi cuối kỳ\n(%/năm)",
            "Trả lãi hàng tháng\n(%/năm)", "Trả lãi hàng quý\n(%/năm)", "Trả lãi trước\n(%/năm)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blx);
        ButterKnife.bind(this);
        token = HawkHelper.getInstance(this).getToken();
        Intent i = getIntent();
        id = i.getIntExtra(Constants.CODE, 0);
        setupUI();
        getData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_blx;
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

    private void getData() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<InterestDetail> call = apiInterface.getIntersetDetail(token, String.valueOf(id));
        call.enqueue(new Callback<InterestDetail>() {
            @Override
            public void onResponse(Call<InterestDetail> call, Response<InterestDetail> response) {
                hideLoading();
                if (response.code() == 200) {
                    List<InterestDetail.DataBean.InterestRateTableDetailsBean> dataBeans =
                            response.body().getData().getInterestRateTableDetails();
                    if (dataBeans.size() == 0) {
                        return;
                    }
                    FixTableAdapter fixTableAdapter = new FixTableAdapter(titles, dataBeans);
                    ftl.setAdapter(fixTableAdapter);
                }
            }

            @Override
            public void onFailure(Call<InterestDetail> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void setupUI() {
        btnBack.setOnClickListener(v -> {
            finish();
        });

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rvBlx.setLayoutManager(linearLayoutManager);


    }
}

class FixTableAdapter implements IDataAdapter {

    public String[] titles;

    public List<InterestDetail.DataBean.InterestRateTableDetailsBean> data;

    public FixTableAdapter(String[] titles, List<InterestDetail.DataBean.InterestRateTableDetailsBean> data) {
        this.titles = titles;
        this.data = data;
    }

    public void setData(List<InterestDetail.DataBean.InterestRateTableDetailsBean> data) {
        this.data = data;
    }

    @Override
    public String getTitleAt(int pos) {
        return titles[pos];
    }

    @Override
    public int getTitleCount() {
        return titles.length;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void convertData(int position, List<TextView> bindViews) {
        InterestDetail.DataBean.InterestRateTableDetailsBean dataBean = data.get(position);
        try {
            bindViews.get(0)
                    .setText(dataBean.getType());
            bindViews.get(1)
                    .setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getPayInterestAtMaturity())));
            bindViews.get(2)
                    .setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getPayMonthlyInterest())));
            bindViews.get(3)
                    .setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getQuarterlyInterestPayment())));
            bindViews.get(4)
                    .setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dataBean.getPayInterestFirst())));
        } catch (Exception e) {
        }
    }

    @Override
    public void convertLeftData(int position, TextView bindView) {
        bindView.setText(data.get(position).getType());
    }
}
