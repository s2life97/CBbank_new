package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.content.res.Configuration;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.OndeleteEvent;
import com.saleskit.cbbank.features.account.NotificationJson;
import com.saleskit.cbbank.features.appointment.OnLoadMoreListener;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.AppUtil;
import com.daimajia.swipe.SwipeLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder> {
    private List<NotificationJson.DataBean> dataBeans;
    private Context context;
    private int lastTotalItemCount, totalItemCount;
    private OnItemClicklistener onItemClicklistener;
    public SwipeLayout slNeedToBeClosed;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean loading;
    private OndeleteEvent ondeleteEvent;

    SwipeLayout.SwipeListener swipeListener = new SwipeLayout.SwipeListener() {
        @Override
        public void onStartOpen(SwipeLayout layout) {

        }

        @Override
        public void onOpen(SwipeLayout layout) {
            if (slNeedToBeClosed != null && slNeedToBeClosed != layout) {
                slNeedToBeClosed.close(true);
            }
            slNeedToBeClosed = layout;
        }

        @Override
        public void onStartClose(SwipeLayout layout) {

        }

        @Override
        public void onClose(SwipeLayout layout) {
            if (slNeedToBeClosed == layout) {
                slNeedToBeClosed = null;
            }
        }

        @Override
        public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

        }

        @Override
        public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

        }
    };


    public NotiAdapter(List<NotificationJson.DataBean> dataBeans, Context context,
                       OnItemClicklistener onItemClicklistener, RecyclerView recyclerView, OndeleteEvent ondeleteEvent) {
        this.dataBeans = dataBeans;
        this.context = context;
        slNeedToBeClosed = null;
        this.onItemClicklistener = onItemClicklistener;
        this.ondeleteEvent = ondeleteEvent;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    totalItemCount = linearLayoutManager.getItemCount();

                    if (!recyclerView.canScrollVertically(View.SCROLL_INDICATOR_TOP)) {
                        if (!loading && totalItemCount != lastTotalItemCount) {
                            lastTotalItemCount = totalItemCount;
                            if (onLoadMoreListener != null) {
                                onLoadMoreListener.onLoadMore();
                            }
                            loading = true;
                        }
                    }
                }
            });

        }
    }

    public void setLoaded() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public long getItemId(int position) {
        return dataBeans.get(position).getNotificationId();
    }

    @NonNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_noti, parent, false);
        return new NotiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class NotiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_user_lookup)
        ImageView ivUserLookup;
        @BindView(R.id.tv_user_lookup)
        TextView tvUserLookup;
        @BindView(R.id.rl_user_lookup)
        RelativeLayout rlUserLookup;
        @BindView(R.id.right_side)
        LinearLayout rightSide;
        @BindView(R.id.iv_logo_noti)
        ImageView ivLogoNoti;
        @BindView(R.id.tv_title_noti)
        TextView tvTitleNoti;
        @BindView(R.id.tv_body_noti)
        TextView tvBodyNoti;
        @BindView(R.id.tv_time_noti)
        TextView tvTimeNoti;
        @BindView(R.id.rl_item_noti)
        RelativeLayout rlItemNoti;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;
        @BindView(R.id.rl_main)
        RelativeLayout rlMain;

        public NotiViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            NotificationJson.DataBean dataBean = dataBeans.get(position);
            boolean status = dataBean.isSeen();
            if (!status) {
                AppUtil.setBackgroundForView(rlMain, context, R.drawable.square_gray_noti);
            } else {
                AppUtil.setBackgroundForView(rlMain, context, R.drawable.square_white);
            }
            Locale locale = new Locale("vi");
            Locale.setDefault(locale);
            Configuration config = context.getResources().getConfiguration();
            config.locale = locale;
            context.getResources().updateConfiguration(config,
                    context.getResources().getDisplayMetrics());
            String time = dataBean.getCreatedDate();
            Date date = AppUtil.getNewDate(time);
            Date currentTime = Calendar.getInstance().getTime();
            Long dateTime = date.getTime();
            long nowTime = currentTime.getTime();
            long distance = nowTime - dateTime;
            Timber.e("datetime " + distance);
            if (distance < 61 * 1000) {
                tvTimeNoti.setText("Vừa xong");
            } else {
                tvTimeNoti.setText(DateUtils.getRelativeTimeSpanString(dateTime));
            }
            int id = dataBean.getNotificationId();

            tvTitleNoti.setText(dataBean.getTitle());
            tvBodyNoti.setText(dataBean.getText());
            rlItemNoti.setOnClickListener(v -> {
                onItemClicklistener.onItemClick(position);
                AppUtil.setBackgroundForView(rlMain, context, R.drawable.square_white);
            });

            rlUserLookup.setOnClickListener(v -> {
                swipeLayout.close();
                ondeleteEvent.onDeletePosition(position);
            });

            swipeLayout.removeSwipeListener(swipeListener);
            swipeLayout.addSwipeListener(swipeListener);
        }
    }
}
