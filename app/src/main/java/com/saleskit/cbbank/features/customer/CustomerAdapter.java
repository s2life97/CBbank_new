package com.saleskit.cbbank.features.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.appointment.OnLoadMoreListener;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.rx.netmodel.Customer;
import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private OnItemClicklistener onItemClicklistener;
    private Context context;
    private List<Customer.DataBean> customerModelList;
    private boolean isEdited;
    public SwipeLayout slNeedToBeClosed;
    private OnLoadMoreListener onLoadMoreListener;
    private OnReworkClick onReworkClick;
    private int lastTotalItemCount, totalItemCount;
    private boolean loading;
    private boolean isRework;
    private boolean enableCheckBox;
    private boolean canRework;

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

    public CustomerAdapter(boolean canRework, boolean isRework, boolean isEdited, OnItemClicklistener onItemClicklistener, Context context,
                           List<Customer.DataBean> customerModelList, RecyclerView recyclerView, OnReworkClick onReworkClick) {
        this.canRework = canRework;
        this.isRework = isRework;
        this.isEdited = isEdited;
        this.onItemClicklistener = onItemClicklistener;
        this.context = context;
        this.customerModelList = customerModelList;
        this.onReworkClick = onReworkClick;

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

    public CustomerAdapter(boolean canRework, boolean isRework, boolean isEdited, OnItemClicklistener onItemClicklistener, Context context,
                           List<Customer.DataBean> customerModelList, RecyclerView recyclerView, OnReworkClick onReworkClick, boolean enableCheckBox) {
        this.canRework = canRework;
        this.isRework = isRework;
        this.isEdited = isEdited;
        this.onItemClicklistener = onItemClicklistener;
        this.context = context;
        this.customerModelList = customerModelList;
        this.onReworkClick = onReworkClick;
        this.enableCheckBox = enableCheckBox;

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

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return customerModelList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_1)
        ImageView iv1;
        @BindView(R.id.tv_rework)
        TextView tvRework;
        @BindView(R.id.right_side)
        LinearLayout rightSide;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_identity)
        TextView tvIdentity;
        @BindView(R.id.tv_hd)
        TextView tvHd;
        @BindView(R.id.tv_own)
        TextView tvOwn;
        @BindView(R.id.ll_file_number)
        LinearLayout llFileNumber;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;
        @BindView(R.id.rl_rework)
        RelativeLayout rlRework;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.checkbox)
        CheckBox checkBox;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {

            if (enableCheckBox) {
                checkBox.setVisibility(View.VISIBLE);
                checkBox.setChecked(customerModelList.get(position).isChosen());
            } else {
                checkBox.setVisibility(View.GONE);
            }

            String name = customerModelList.get(position).getLastName() + " " + customerModelList.get(position).getFirstName();
            tvName.setText(name);
            if (!isRework) {
                Glide.with(context).load(R.drawable.ic_iden_demo).into(ivIcon);
                tvIdentity.setText(customerModelList.get(position).getIdentityNumber());
            } else {
                tvIdentity.setText(customerModelList.get(position).getAddress());
            }
            llItem.setOnClickListener(v -> onItemClicklistener.onItemClick(position));
            if (isEdited && !isRework) {
                llFileNumber.setVisibility(View.VISIBLE);
            } else {
                llFileNumber.setVisibility(View.GONE);
            }
            if (isRework && canRework) {
                swipeLayout.setLeftSwipeEnabled(true);
                swipeLayout.setRightSwipeEnabled(true);
            } else {
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(false);
            }

            String hdContent = "Huy động  " + customerModelList.get(position).getCountFinalMobilize() + "/"
                    + customerModelList.get(position).getCountAllMobilize();
            String creaditContent = "Cho vay  " + customerModelList.get(position).getCountFinalCredit() + "/"
                    + customerModelList.get(position).getCountAllCredit();
            tvHd.setText(hdContent);
            tvOwn.setText(creaditContent);
            rlRework.setOnClickListener(v -> {
                onReworkClick.onReworkClick(position);
            });
            swipeLayout.removeSwipeListener(swipeListener);
            swipeLayout.addSwipeListener(swipeListener);

        }
    }
}
