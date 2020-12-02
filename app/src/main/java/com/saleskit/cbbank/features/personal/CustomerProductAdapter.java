package com.saleskit.cbbank.features.personal;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.appointment.OnLoadMoreListener;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.AppUtil;
import com.daimajia.swipe.SwipeLayout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CustomerProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CustomerProductAdapter";
    private static final int ITEM_OWN = 1;
    private static final int ITEM_HD = 2;
    private List<CustomerInfo.DataBean.CustomerProfilesBean> list;
    private Context context;
    private OnItemClicklistener onItemClicklistener;
    private PersonView personView;
    private String employeeId;
    private OnLoadMoreListener onLoadMoreListener;
    private int lastTotalItemCount, totalItemCount;
    private boolean loading;
    private boolean isFromHome;
    private String departmentId;
    private boolean canRework;
    private int customerType;
    private boolean canEdit;
    private String fullName = "";
    SwipeLayout slNeedToBeClosed = null;
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

    public CustomerProductAdapter(String fullName,
                                  boolean canRework, boolean canEdit,
                                  String departmentId, String employeeId, OnItemClicklistener onItemClicklistener, List<CustomerInfo.DataBean.CustomerProfilesBean> list,
                                  Context context, PersonView personView, RecyclerView recyclerView, boolean isFromHome, int customerType) {
        this.fullName = fullName;
        this.canRework = canRework;
        this.canEdit = canEdit;
        this.departmentId = departmentId;
        this.employeeId = employeeId;
        this.onItemClicklistener = onItemClicklistener;
        this.context = context;
        this.list = list;
        this.personView = personView;
        this.isFromHome = isFromHome;
        this.customerType = customerType;
        slNeedToBeClosed = null;
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
    public int getItemViewType(int position) {
        if (list.get(position).getProductType() == 1 || list.get(position).getProductType() == 3) {
            return ITEM_OWN;
        } else {
            return ITEM_HD;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_OWN) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_own_product, parent, false);
            return new OwnedProductViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_hd_product, parent, false);
            return new HDProductViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_OWN) {
            OwnedProductViewHolder ownedProductViewHolder = (OwnedProductViewHolder) holder;
            ownedProductViewHolder.onBind(position);
        } else {
            HDProductViewHolder galleryHolder = (HDProductViewHolder) holder;
            galleryHolder.onBind(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HDProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.rl_edit)
        RelativeLayout rlEdit;
        @BindView(R.id.rl_delete)
        RelativeLayout rlDelete;
        @BindView(R.id.right_side)
        LinearLayout rightSide;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_product_code)
        TextView tvProductCode;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_trans_date)
        TextView tvTransDate;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;
        @BindView(R.id.tv_bos_date)
        TextView tvBosDate;
        @BindView(R.id.tv_person_name)
        TextView tvPersonName;
        @BindView(R.id.tv_date_setting)
        TextView tvDateSetting;

        public HDProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            String name = list.get(position).getProductTypeName() + ": " + list.get(position).getProductName();
            tvName.setText(name);
            tvCode.setText(list.get(position).getTradingCode().toString());
            tvProductCode.setText(list.get(position).getProductCode());

            String transAppointDate = list.get(position).getAppointmentTransactionDate();
            String appointTransDateShow = AppUtil.format(transAppointDate);
            tvBosDate.setText(appointTransDateShow);

            String transDateShow = list.get(position).getTransactionDate();
            String dateShow = AppUtil.formatTimeDate(transDateShow);
            tvTransDate.setText(dateShow);

            String personName = list.get(position).getModifiedLastName() + " " + list.get(position).getModifiedFirstName();
            tvPersonName.setText(personName);

            String modifyDate = list.get(position).getModifiedDate();
            String modifyShowDate = AppUtil.formatTimeDate(modifyDate);
            tvDateSetting.setText(modifyShowDate);


            int status = list.get(position).getStatus();
            if (status == 1) {
                tvStatus.setText("Mới khởi tạo");
                tvStatus.setVisibility(View.VISIBLE);
                AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_blue_met);
                tvStatus.setTextColor(context.getResources().getColor(R.color.blue_meeting));
            } else if (status == 2) {
                tvStatus.setText("Đã hoàn thành");
                tvStatus.setVisibility(View.VISIBLE);
                AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_green);
                tvStatus.setTextColor(context.getResources().getColor(R.color.green));
            } else if (status == 0) {
                tvStatus.setVisibility(View.GONE);
            } else {
                tvStatus.setText("Đã tra cứu");
                tvStatus.setVisibility(View.VISIBLE);
                AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_orange);
                tvStatus.setTextColor(context.getResources().getColor(R.color.orange));
            }
            llItem.setOnClickListener(v -> onItemClicklistener.onItemClick(position));
//            int process = list.get(position).getProcess();
//            List<View> processView = new ArrayList<>();
//            processView.add(v1);
//            processView.add(v2);
//            processView.add(v3);
//            processView.add(v4);
//            processView.add(v5);
//            Log.d(TAG, "onBind: " + process);
//            for (int i = 0; i < process; i++) {
//                AppUtil.setBackgroundForView(processView.get(i), context, R.drawable.rounded_blue);
//            }
//            for (int i = process; i < 5; i++) {
//                AppUtil.setBackgroundForView(processView.get(i), context, R.drawable.rounded_gray);
//            }
            if (status == 1) {
                swipeLayout.setRightSwipeEnabled(true);
                rlDelete.setVisibility(View.VISIBLE);
                rlEdit.setVisibility(View.VISIBLE);
            } else {
                swipeLayout.setRightSwipeEnabled(false);
                rlDelete.setVisibility(View.GONE);
                rlEdit.setVisibility(View.GONE);
            }
            rlDelete.setOnClickListener(v -> personView.onDelete(position));
            rlEdit.setOnClickListener(view -> {
                personView.onItemClick(position);
            });
            swipeLayout.removeSwipeListener(swipeListener);
            swipeLayout.addSwipeListener(swipeListener);

        }

    }

    class OwnedProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_rework)
        ImageView ivRework;
        @BindView(R.id.tv_rework)
        TextView tvRework;
        @BindView(R.id.rl_rework)
        RelativeLayout rlRework;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.rl_delete)
        RelativeLayout rlDelete;
        @BindView(R.id.right_side)
        LinearLayout rightSide;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_interest)
        TextView tvInterest;
        @BindView(R.id.v_1)
        View v1;
        @BindView(R.id.rl_edit)
        RelativeLayout rlEdit;
        @BindView(R.id.v_2)
        View v2;
        @BindView(R.id.v_3)
        View v3;
        @BindView(R.id.v_4)
        View v4;
        @BindView(R.id.v_5)
        View v5;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;
        @BindView(R.id.tv_date_setting)
        TextView tvDateSetting;
        @BindView(R.id.tv_person_name)
        TextView tvPersonName;
        @BindView(R.id.rl_user_lookup)
        RelativeLayout rlUserLookup;
        @BindView(R.id.ll_process)
        LinearLayout llProcess;

        public OwnedProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {
            String name = list.get(position).getProductTypeName() + ": " + list.get(position).getProductName();
            tvName.setText(name);
            tvInterest.setText(list.get(position).getDescriptionInterest());
            tvMoney.setText(NumberFormat.getInstance(Locale.US).format(list.get(position).getMoney()) + " VNĐ");
//            tvMoney.setText(NumberFormat.getInstance().format(list.get(position).getMoney()) + " VNĐ");
            String date = list.get(position).getBosInputDate();
            String dateShow = AppUtil.formatTimeDate(date);
//            tvAppointDate.setText(appointDate);
            tvDate.setText(dateShow);

            String personName = list.get(position).getModifiedLastName() + " " + list.get(position).getModifiedFirstName();
            tvPersonName.setText(personName);
            String modifyDate = list.get(position).getModifiedDate();
            String modifyShowDate = AppUtil.formatTimeDate(modifyDate);
            tvDateSetting.setText(modifyShowDate);

            int status = list.get(position).getStatus();
            if (status == 1) {
                tvStatus.setText("Mới khởi tạo");
                tvStatus.setVisibility(View.VISIBLE);
                AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_blue_met);
                tvStatus.setTextColor(context.getResources().getColor(R.color.blue_meeting));
            } else if (status == 2) {
                tvStatus.setText("Đã hoàn thành");
                tvStatus.setVisibility(View.VISIBLE);
                AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_green);
                tvStatus.setTextColor(context.getResources().getColor(R.color.green));
            } else if (status == 0) {
                tvStatus.setVisibility(View.GONE);
            } else {
                tvStatus.setText("Đã tra cứu");
                tvStatus.setVisibility(View.VISIBLE);
                AppUtil.setBackgroundForView(tvStatus, context, R.drawable.rounded_kpi_orange);
                tvStatus.setTextColor(context.getResources().getColor(R.color.orange));
            }

            if ((status == 1 || status == 2) && isFromHome && (canRework || canEdit)) {
                swipeLayout.setRightSwipeEnabled(true);
                swipeLayout.setLeftSwipeEnabled(true);
            } else {
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(false);
            }
            if (isFromHome && status == 2 && !canRework) {
                swipeLayout.setRightSwipeEnabled(false);
                swipeLayout.setLeftSwipeEnabled(false);
            }
            if ((status == 1 || status == 2) && isFromHome && canRework) {
                if (canEdit) {
                    rlUserLookup.setVisibility(View.VISIBLE);
                } else {
                    rlUserLookup.setVisibility(View.GONE);
                }
                rlRework.setVisibility(View.VISIBLE);
            } else {
                rlUserLookup.setVisibility(View.GONE);
                rlRework.setVisibility(View.GONE);
            }
            if (status == 1 && isFromHome && canEdit) {
                rlDelete.setVisibility(View.VISIBLE);
                rlEdit.setVisibility(View.VISIBLE);
            } else {
                rlDelete.setVisibility(View.GONE);
                rlEdit.setVisibility(View.GONE);
            }
            rlEdit.setOnClickListener(view -> {
                personView.onItemClick(position);
            });
            if (customerType == 1) {
                llProcess.setVisibility(View.VISIBLE);
                int process;
                boolean hasFormular = list.get(position).isHasCalculationFormula();
                if (hasFormular) {
                    process = list.get(position).getProcess();
                } else {
                    process = list.get(position).getProcess() - 1;
                }
                if (!hasFormular) {
                    v5.setVisibility(View.GONE);
                } else {
                    v5.setVisibility(View.VISIBLE);
                }
                List<View> processView = new ArrayList<>();
                processView.add(v1);
                processView.add(v2);
                processView.add(v3);
                processView.add(v4);
                processView.add(v5);
                for (int i = 0; i < process; i++) {
                    AppUtil.setBackgroundForView(processView.get(i), context, R.drawable.rounded_blue);
                }
                for (int i = process; i < 5; i++) {
                    AppUtil.setBackgroundForView(processView.get(i), context, R.drawable.rounded_gray);
                }
            } else {
                llProcess.setVisibility(View.GONE);

            }

            rlDelete.setOnClickListener(v -> personView.onDelete(position));
            rlRework.setOnClickListener(v -> {
                Intent i = new Intent(context, ReworkActivity.class);
                i.putExtra(Constants.REWORK_TYPE, Constants.FILE_REWORK);
                i.putExtra(Constants.USER_ID, employeeId);
                i.putExtra(Constants.USER_FULL_NAME, fullName);
                i.putExtra(Constants.DEPARTMENT_ID, departmentId);
                i.putExtra(Constants.ALLOW_ID, list.get(position).getCustomerProfileId());
                context.startActivity(i);
            });
            rlUserLookup.setOnClickListener(v -> {
                Intent i = new Intent(context, UserLookupActivity.class);
                i.putExtra(Constants.USER_ID, employeeId);
                i.putExtra(Constants.USER_FULL_NAME, fullName);
                i.putExtra(Constants.DEPARTMENT_ID, departmentId);
                i.putExtra(Constants.ALLOW_ID, list.get(position).getCustomerProfileId());
                context.startActivity(i);
            });
            llItem.setOnClickListener(v -> onItemClicklistener.onItemClick(position));
            swipeLayout.removeSwipeListener(swipeListener);
            swipeLayout.addSwipeListener(swipeListener);
        }
    }
}
