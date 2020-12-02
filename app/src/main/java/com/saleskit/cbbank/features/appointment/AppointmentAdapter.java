package com.saleskit.cbbank.features.appointment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.OndeleteEvent;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Appointment;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;
import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    private static final String TAG = "AppointmentAdapter";
    public SwipeLayout slNeedToBeClosed;
    private List<Appointment.DataBean> appointmentModels;
    private Context context;
    private OnItemClicklistener onItemClicklistener;
    private int lastTotalItemCount, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private OndeleteEvent ondeleteEvent;
    private boolean onlySee;

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

    public AppointmentAdapter(OndeleteEvent ondeleteEvent, List<Appointment.DataBean> appointmentModels, Context context,
                              OnItemClicklistener onItemClicklistener, RecyclerView recyclerView, boolean onlySee) {
        this.ondeleteEvent = ondeleteEvent;
        this.appointmentModels = appointmentModels;
        this.context = context;
        this.onItemClicklistener = onItemClicklistener;
        slNeedToBeClosed = null;
        this.onlySee = onlySee;

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

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metting_person, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return appointmentModels.size();
    }

    @Override
    public long getItemId(int position) {
        return appointmentModels.get(position).getAppointmentId();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private final int RESULT_CANCEL = 3;
        private final int RESULT_ACCEPT = 2;
        private final int RESULT_REMEET = 1;
        private final int RESULT_NONE = 0;
        @BindView(R.id.iv_rework)
        ImageView ivRework;
        @BindView(R.id.tv_rework)
        TextView tvRework;
        @BindView(R.id.bt_edit)
        RelativeLayout btEdit;
        @BindView(R.id.right_side)
        LinearLayout rightSide;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_location_1)
        TextView tvLocation1;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.iv_clock)
        ImageView ivClock;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_calendar)
        ImageView ivCalendar;
        @BindView(R.id.tv_calendar)
        TextView tvCalendar;
        @BindView(R.id.bt_1)
        LinearLayout bt1;
        @BindView(R.id.rl_delete)
        RelativeLayout rlDelete;
        @BindView(R.id.ll_bot)
        LinearLayout llBot;
        @BindView(R.id.iv_phone)
        ImageView ivPhone;
        @BindView(R.id.sl)
        SwipeLayout swipeLayout;
        private String token;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            token = HawkHelper.getInstance(context).getToken();
        }

        public void onBind(int position) {

            String name = appointmentModels.get(position).getCustomerName();
            tvName.setText(name);
            tvLocation1.setText(appointmentModels.get(position).getAppointmentAddress());
            tvPhone.setText(appointmentModels.get(position).getPhoneNumber());
            String date = appointmentModels.get(position).getAppointmentTime();
            String dateShow = AppUtil.format(date);
            String time = AppUtil.fomatTimeDeal(date);
            String formatDate = AppUtil.formatCompare(date);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");
            Date strDate = null;
            String currentTime = sdf.format(new Date());
            Date currentDateFormat = Calendar.getInstance().getTime();
            boolean isMissing = false;
            Log.e(TAG, "onBind: " + "date  " + " currne  " + currentTime.toString());
            try {
                strDate = sdf.parse(formatDate);
//                currentDateFormat = sdf.parse(currentTime);
                Log.e(TAG, "onBind: " + "date  " + strDate.toString() + " currne  " + currentTime.toString());
                if (currentDateFormat.after(strDate)) {
                    isMissing = false;
                } else {
                    isMissing = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            tvTime.setText(time);
            tvCalendar.setText(dateShow);

            int type = appointmentModels.get(position).getResultStatus();
            switch (type) {
                case RESULT_CANCEL:
                    tvStatus.setText(context.getResources().getString(R.string.cancel_meet));
                    tvRework.setText("Xem");
                    Glide.with(context).load(R.drawable.ic_eye_home).into(ivRework);
                    rlDelete.setVisibility(View.GONE);
                    tvName.setTextColor(context.getResources().getColor(R.color.black));
                    AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_red);
                    tvTime.setTextColor(context.getResources().getColor(R.color.red));
                    tvStatus.setTextColor(context.getResources().getColor(R.color.red));
                    tvCalendar.setTextColor(context.getResources().getColor(R.color.red));
                    Glide.with(context).load(R.drawable.ic_clock_red).into(ivClock);
                    Glide.with(context).load(R.drawable.ic_calendar_red).into(ivCalendar);
                    Glide.with(context).load(R.drawable.ic_phone).into(ivPhone);
                    tvPhone.setTextColor(context.getResources().getColor(R.color.gray_appoint));
                    AppUtil.setBackgroundForView(llBot, context, R.color.white);
                    break;
                case RESULT_ACCEPT:
                    tvRework.setText("Xem");
                    rlDelete.setVisibility(View.GONE);
                    tvStatus.setText(context.getResources().getString(R.string.accpet_meet));
                    Glide.with(context).load(R.drawable.ic_eye_home).into(ivRework);
                    tvName.setTextColor(context.getResources().getColor(R.color.black));
                    AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_oceanblue);
                    tvTime.setTextColor(context.getResources().getColor(R.color.green));
                    tvStatus.setTextColor(context.getResources().getColor(R.color.green));
                    tvCalendar.setTextColor(context.getResources().getColor(R.color.green));
                    Glide.with(context).load(R.drawable.ic_clock_green).into(ivClock);
                    Glide.with(context).load(R.drawable.ic_calendar_green).into(ivCalendar);
                    Glide.with(context).load(R.drawable.ic_phone).into(ivPhone);
                    tvPhone.setTextColor(context.getResources().getColor(R.color.gray_appoint));
                    AppUtil.setBackgroundForView(llBot, context, R.color.white);

                    break;
                case RESULT_REMEET:
                    tvRework.setText("Xem");
                    rlDelete.setVisibility(View.GONE);
                    tvName.setTextColor(context.getResources().getColor(R.color.black));
                    tvStatus.setText(context.getResources().getString(R.string.remeet));
                    AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_blue_met);
                    tvTime.setTextColor(context.getResources().getColor(R.color.blue_meeting));
                    tvStatus.setTextColor(context.getResources().getColor(R.color.blue_meeting));
                    tvCalendar.setTextColor(context.getResources().getColor(R.color.blue_meeting));
                    Glide.with(context).load(R.drawable.ic_clock_list).into(ivClock);
                    Glide.with(context).load(R.drawable.ic_calendar_meeting).into(ivCalendar);
                    Glide.with(context).load(R.drawable.ic_phone).into(ivPhone);
                    tvPhone.setTextColor(context.getResources().getColor(R.color.gray_appoint));
                    AppUtil.setBackgroundForView(llBot, context, R.color.white);
                    Glide.with(context).load(R.drawable.ic_eye_home).into(ivRework);
                    break;
                case RESULT_NONE:
                    if (!onlySee) {
                        rlDelete.setVisibility(View.VISIBLE);
                    } else {
                        rlDelete.setVisibility(View.GONE);
                    }
                    tvStatus.setText(context.getResources().getString(R.string.none));
                    if (isMissing) {
                        tvTime.setTextColor(context.getResources().getColor(R.color.blue_meeting));
                        tvPhone.setTextColor(context.getResources().getColor(R.color.gray_appoint));
                        tvStatus.setTextColor(context.getResources().getColor(R.color.orange));
                        tvCalendar.setTextColor(context.getResources().getColor(R.color.blue_meeting));
                        Glide.with(context).load(R.drawable.ic_clock_list).into(ivClock);
                        Glide.with(context).load(R.drawable.ic_phone).into(ivPhone);
                        Glide.with(context).load(R.drawable.ic_calendar_meeting).into(ivCalendar);
                        tvName.setTextColor(context.getResources().getColor(R.color.black));
                        AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_orange);
                        AppUtil.setBackgroundForView(llBot, context, R.color.white);
                    } else {
                        tvTime.setTextColor(context.getResources().getColor(R.color.red));
                        tvPhone.setTextColor(context.getResources().getColor(R.color.red));
                        tvStatus.setTextColor(context.getResources().getColor(R.color.red));
                        tvCalendar.setTextColor(context.getResources().getColor(R.color.red));
                        Glide.with(context).load(R.drawable.ic_clock_red).into(ivClock);
                        Glide.with(context).load(R.drawable.ic_calendar_red).into(ivCalendar);
                        Glide.with(context).load(R.drawable.ic_phone_red).into(ivPhone);
                        tvName.setTextColor(context.getResources().getColor(R.color.red));
                        AppUtil.setBackgroundForView(tvStatus, context, R.color.white);
                        AppUtil.setBackgroundForView(llBot, context, R.color.semi_red);

                    }
//                    AppUtil.setBackgroundForView(btEdit, context, R.color.red);
                    if (!onlySee) {
                        tvRework.setText("Sửa");
                        Glide.with(context).load(R.drawable.ic_pen_person).into(ivRework);
                    } else {
                        tvRework.setText("Xem");
                        Glide.with(context).load(R.drawable.ic_eye_home).into(ivRework);
                    }
                    break;
            }
//            if (onlySee && type != RESULT_NONE) {
//                rlDelete.setVisibility(View.GONE);
//            } else {
//                rlDelete.setVisibility(View.VISIBLE);
//            }
            if (onlySee) {
                AppUtil.setBackgroundForView(btEdit, context, R.drawable.square_gradient_yellow);
            }
//            tvLocation1.setText(appointmentModels.get(position).getAddress());
//            tvPhone.setText(appointmentModels.get(position).getPhoneNumber());
//            String date = appointmentModels.get(position).getAppointmentDate();
//            String formatDate = AppUtil.formatDateTime(date);
//            String time = AppUtil.fomatTime(date);
//            tvCalendar.setText(formatDate);
//            tvTime.setText(time);

            btEdit.setOnClickListener(v -> {
                if (onlySee) {
                    Intent i = new Intent(context, AddApointmentActivity.class);
                    i.putExtra(Constants.SEE_DETAIL, Constants.SEE_DETAIL);
                    i.putExtra(Constants.APPOINT_ID, (Serializable) appointmentModels.get(position));
                    context.startActivity(i);
                } else {
                    if (type == RESULT_NONE) {
                        Intent i = new Intent(context, AddApointmentActivity.class);
                        i.putExtra(Constants.SEE_DETAIL, Constants.EDIT_DETAIL);
                        i.putExtra(Constants.APPOINT_ID, (Serializable) appointmentModels.get(position));
                        context.startActivity(i);
                    } else {
                        Intent i = new Intent(context, AddApointmentActivity.class);
                        i.putExtra(Constants.SEE_DETAIL, Constants.SEE_DETAIL);
                        i.putExtra(Constants.APPOINT_ID, (Serializable) appointmentModels.get(position));
                        context.startActivity(i);
                    }
                }
//                    Intent i = new Intent(context, AddApointmentActivity.class);
//                    int id = appointmentModels.get(position).getAppointmentId();
//                    i.putExtra(Constants.SEE_DETAIL, "");
////                    i.putExtra(Constants.APPOINT_ID, id);
//                    context.startActivity(i);
            });
            rlDelete.setOnClickListener(v -> {
                APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
                Call<MessageBean> call = apiInterface.deleteAppoint(token, appointmentModels.get(position).getAppointmentId());
                call.enqueue(new Callback<MessageBean>() {
                    @Override
                    public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                        if (response.code() == 200) {
                            appointmentModels.remove(position);
                            notifyDataSetChanged();
                            ondeleteEvent.onDelete();
                        } else {
                            Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageBean> call, Throwable t) {

                    }
                });
            });
            bt1.setOnClickListener(v -> {
//                if (type == RESULT_NONE) {
//                    Intent i = new Intent(context, UpdateResultActivity.class);
//                    int id = appointmentModels.get(position).getAppointmentId();
//                    i.putExtra(Constants.SEE_DETAIL, Constants.SEE_DETAIL);
//                    i.putExtra(Constants.APPOINT_ID, id);
//                    context.startActivity(i);
//                } else {
                Intent i = new Intent(context, UpdateResultActivity.class);
                i.putExtra(Constants.EDIT_DETAIL, onlySee);
                int id = appointmentModels.get(position).getAppointmentId();
                i.putExtra(Constants.SEE_DETAIL, Constants.SEE_DETAIL);
                i.putExtra(Constants.APPOINT_ID, id);
                context.startActivity(i);
//                }
            });

            swipeLayout.removeSwipeListener(swipeListener);
            swipeLayout.addSwipeListener(swipeListener);
        }

    }
}
