package com.saleskit.cbbank.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.OndeleteEvent;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.account.NotificationJson;
import com.saleskit.cbbank.features.appointment.AddApointmentActivity;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.detail.HDCustomerDetailActivity;
import com.saleskit.cbbank.features.news.DetailNewActivity;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
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

public class NotificationListActivity extends BaseActivity {

    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_choose_all)
    TextView tvChooseAll;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_choose)
    TextView tvChoose;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.rv_noti)
    RecyclerView rvNoti;
    @BindView(R.id.sw_all)
    SwipeRefreshLayout swAll;
    private int page = 1;
    private String token;
    private int totalPages;
    private List<NotificationJson.DataBean> notiList = new ArrayList<>();
    private NotiAdapter notiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        ButterKnife.bind(this);
        token = "Bearer " + HawkHelper.getInstance(this).getTokenModel().getToken();
        setupUi();
        loadData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_notification_list;
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

    private void loadData() {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(page));
        options.put("pageSize", "10");
        Call<NotificationJson> call = apiInterface.getListNoti(token, options);
        call.enqueue(new Callback<NotificationJson>() {
            @Override
            public void onResponse(Call<NotificationJson> call, Response<NotificationJson> response) {
                swAll.setRefreshing(false);
                hideLoading();
                if (response.code() == 200) {
                    totalPages = 0;
                    if (page == 1) {
                        notiList.clear();
                    }
                    notiList.addAll(response.body().getData());

                    if (notiList.size() == 0) {
                        tvResult.setVisibility(View.VISIBLE);
                    } else {
                        tvResult.setVisibility(View.GONE);
                    }
                    OnItemClicklistener onItemClicklistener = position -> {
                        int id = notiList.get(position).getNotificationId();
                        boolean status = notiList.get(position).isSeen();
                        int type = notiList.get(position).getType();
                        int forwardId = notiList.get(position).getForwardId();
                        Timber.e("status  " + status);
                        if (!status) {
                            readNoti(id, position);
                        }

                        switch (type) {
                            case 1:
                                Intent intent = new Intent(getApplicationContext(), DetailNewActivity.class);
                                intent.putExtra(Constants.ARTICLE, Integer.valueOf(forwardId));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra(Constants.NOTIFY_ID, forwardId);
                                startActivity(intent);
                                break;
                            case 2:
                                Intent intent1 = new Intent(getApplicationContext(), AddApointmentActivity.class);
                                intent1.putExtra(Constants.SEE_DETAIL, Constants.FROM_NOTI);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent1.putExtra(Constants.APPOINT_ID, String.valueOf(forwardId));
                                startActivity(intent1);
                                break;
                            case 3:
                                Intent intent2 = new Intent(getApplicationContext(), HDCustomerDetailActivity.class);
                                intent2.putExtra(Constants.SCREEN_TYPE, true);
                                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent2.putExtra(Constants.CUSTOMER_PROFILEID, forwardId);
                                startActivity(intent2);
                                break;
                            default:
                                Intent intent3 = new Intent(getApplicationContext(), NotiDetailActivity.class);
                                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent3.putExtra(Constants.NOTIFY_ID, id);
                                startActivity(intent3);
                                break;
                        }
                    };
                    OndeleteEvent ondeleteEvent = new OndeleteEvent() {
                        @Override
                        public void onDelete() {

                        }

                        @Override
                        public void onDeletePosition(int position) {
                            int id = notiList.get(position).getNotificationId();
                            APIWebservices apiInterface = NetworkUtil.getCBclient(NotificationListActivity.this).create(APIWebservices.class);
                            String token = "Bearer " + HawkHelper.getInstance(NotificationListActivity.this).getTokenModel().getToken();
                            Call<MessageBean> call1 = apiInterface.hideNoti(token, id);
                            call1.enqueue(new Callback<MessageBean>() {
                                @Override
                                public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                                    if (response.code() == 200) {
                                        notiList.remove(position);
                                        notiAdapter.notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(NotificationListActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<MessageBean> call, Throwable t) {

                                }
                            });
                        }
                    };

                    if (notiAdapter == null) {
                        notiAdapter = new NotiAdapter(notiList, NotificationListActivity.this,
                                onItemClicklistener, rvNoti, ondeleteEvent);
                        notiAdapter.setHasStableIds(true);
                        rvNoti.setAdapter(notiAdapter);
                    } else {
                        notiAdapter.notifyDataSetChanged();
                    }
                    int totalItems = response.body().getTotalRecords();
                    totalPages = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        totalPages += 1;
                    }
                    notiAdapter.setLoaded();
                    page++;
                    notiAdapter.setOnLoadMoreListener(() -> {
                        Timber.e(" load more   ");
                        if (page > totalPages && totalPages > 0) return;
                        loadData();
                    });
                }
            }

            @Override
            public void onFailure(Call<NotificationJson> call, Throwable t) {

            }
        });
    }

    private void readNoti(int id, int postion) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<MessageBean> call = apiInterface.readNoti(token, id);
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.code() == 200) {
                    notiList.get(postion).setSeen(true);
                } else {
                    Toast.makeText(NotificationListActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });
    }

    private void setupUi() {
        ivBack.setOnClickListener(v -> finish());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvNoti.setLayoutManager(linearLayoutManager);

        swAll.setColorSchemeResources(
                R.color.red,
                R.color.fuchsia,
                R.color.aqua,
                R.color.maroon,
                R.color.blue);
        swAll.setOnRefreshListener(() -> {
            page = 1;
            loadData();
        });
    }
}
