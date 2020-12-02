package com.saleskit.cbbank.features.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Article;
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

public class DetailNewActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.wv_main)
    WebView wvMain;
    private int id;
    private String token;
    private String notificationId;
    private boolean isRead = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        wvMain.getSettings();
        wvMain.setPadding(0, 0, 0, 0);
        wvMain.setInitialScale(getScale());
        wvMain.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvMain.getSettings().setBuiltInZoomControls(true);
        wvMain.getSettings().setDisplayZoomControls(false);

        ivBack.setOnClickListener(v -> {
            finish();
        });
        showLoading();
        token = HawkHelper.getInstance(this).getToken();
        Intent intent = getIntent();
        id = intent.getIntExtra(Constants.ARTICLE, 0);
        getArticleDetail(id);
        isRead = intent.getBooleanExtra(Constants.READ_NOTI, true);
        if (!isRead) {
            notificationId = intent.getStringExtra(Constants.NOTIFICATION_ID);
            readNoti(Integer.valueOf(notificationId));
        }
    }

    private void readNoti(int id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<MessageBean> call = apiInterface.readNoti(token, id);
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.code() == 200) {
                } else {
                    Toast.makeText(DetailNewActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });
    }

    private int getScale() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width) / new Double(550);
        val = val * 100d;
        return val.intValue();
    }

    private void getArticleDetail(int id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<Article> call = apiInterface.getArticleDetail(token, id);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                hideLoading();
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null) {
                        Article.DataBean dataBean = response.body().getData();
                        tvName.setText(dataBean.getTitle());
                        String content = dataBean.getContent();
                        wvMain.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
                    } else {
                        Toast.makeText(DetailNewActivity.this, "Không tìm thấy thông tin tin tức!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailNewActivity.this, getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail_new;
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
}
