package com.saleskit.cbbank.features.customer.add_new_customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.Collateral;
import com.daimajia.swipe.SwipeLayout;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollateralAdapter extends RecyclerView.Adapter<CollateralAdapter.CollateralViewHolder> {
    private boolean doneStep = false;
    private List<Collateral.DataBean> collateral;
    private Context context;
    private CollateralView collateralView;
    public SwipeLayout slNeedToBeClosed;

    public CollateralAdapter(List<Collateral.DataBean> collateral, Context context, CollateralView collateralView, boolean doneStep) {
        this.collateral = collateral;
        this.context = context;
        this.collateralView = collateralView;
        this.doneStep = doneStep;
    }
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

    @NonNull
    @Override
    public CollateralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collateral, parent, false);
        return new CollateralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollateralViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return collateral.size();
    }

    public void setEdit() {
        doneStep=true;
    }

    public class CollateralViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_rework)
        ImageView ivRework;
        @BindView(R.id.tv_rework)
        TextView tvRework;
        @BindView(R.id.bt_edit)
        RelativeLayout btEdit;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.bt_delete)
        RelativeLayout btDelete;
        @BindView(R.id.right_side)
        LinearLayout rightSide;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_rate)
        TextView tvRate;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;

        public CollateralViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            double value = collateral.get(position).getCollateralValue();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
//            String moneyDisplay = numberFormat.format(value)
//                    .replaceAll("[^0123456789.,]", "");
////            tvMoney.setText(String.format("%s VNĐ", moneyDisplay));
////            String result = format.format(value);
//            tvMoney.setText(moneyDisplay);
            tvName.setText(collateral.get(position).getPropertyTypeName());
            tvMoney.setText(NumberFormat.getInstance(Locale.US).format(value));
//            String rate = NumberFormat.getInstance().format(Math.ceil(collateral.get(position).getRateOfLending()));
            tvRate.setText(String.valueOf(collateral.get(position).getRateOfLending()));
            if(!doneStep){
                swipeLayout.setRightSwipeEnabled(false);
            } else {
                swipeLayout.setRightSwipeEnabled(true);
                btDelete.setOnClickListener(v -> collateralView.delete(position));
                btEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        collateralView.update(position);
                    }
                });
            }
//            if(doneStep){
//                swipeLayout.setRightSwipeEnabled(false);
//            } else {
            btDelete.setOnClickListener(v -> collateralView.delete(position));
            btEdit.setOnClickListener(v -> collateralView.update(position));
            swipeLayout.removeSwipeListener(swipeListener);
            swipeLayout.addSwipeListener(swipeListener);

        }
    }
}
