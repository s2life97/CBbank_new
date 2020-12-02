package com.saleskit.cbbank.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.DetailNoti;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotiDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.iv_logo_noti)
    ImageView ivLogoNoti;
    @BindView(R.id.tv_title_noti)
    TextView tvTitleNoti;
    @BindView(R.id.tv_body)
    TextView tvBody;
    private String token;
    private int notiId;
    private boolean isRead = true;
    private String notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_noti_detail);
        ButterKnife.bind(this);
        token = "Bearer " + HawkHelper.getInstance(this).getTokenModel().getToken();
        Intent i = getIntent();
        notiId = i.getIntExtra(Constants.NOTIFY_ID, -1);
        ivBack.setOnClickListener(v -> finish());
        isRead = i.getBooleanExtra(Constants.READ_NOTI, true);
        if(!isRead){
            notificationId = i.getStringExtra(Constants.NOTIFICATION_ID);
            readNoti(Integer.valueOf(notificationId));
        }
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<DetailNoti> call = apiInterface.getDetailNoti(token, notiId);
        call.enqueue(new Callback<DetailNoti>() {
            @Override
            public void onResponse(Call<DetailNoti> call, Response<DetailNoti> response) {
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null) {
                        DetailNoti.DataBean dataBean = response.body().getData();
                        tvTitleNoti.setText(dataBean.getTitle());
                        tvBody.setText(dataBean.getText());
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailNoti> call, Throwable t) {

            }
        });
    }

    private void readNoti(int id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<MessageBean> call = apiInterface.readNoti(token, id);
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.code() == 200) {
                } else {
                }
            }
            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_noti_detail;
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
}
