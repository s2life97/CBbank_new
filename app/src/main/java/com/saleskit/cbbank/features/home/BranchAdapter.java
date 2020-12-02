package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.news.OnItemClicklistener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {

    private Context context;
    private List<BranchResponse.DataBean> dataBeans;
    private OnItemClicklistener onItemClicklistener;

    public BranchAdapter(Context context, List<BranchResponse.DataBean> dataBeans, OnItemClicklistener onItemClicklistener) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class BranchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_atm)
        TextView tvAtm;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.iv_fax)
        TextView ivFax;
        @BindView(R.id.ll_contact)
        LinearLayout llContact;
        public BranchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicklistener.onItemClick(position);
                }
            });
            tvAtm.setText(dataBeans.get(position).getName());
            tvLocation.setText(dataBeans.get(position).getAddress());
            tvCode.setText(String.valueOf(dataBeans.get(position).getParentId()));
            tvPhone.setText(dataBeans.get(position).getPhoneNumber());
            ivFax.setText(dataBeans.get(position).getFaxNumber());
        }
    }
}
