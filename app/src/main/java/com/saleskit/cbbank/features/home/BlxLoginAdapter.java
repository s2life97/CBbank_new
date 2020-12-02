package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.InterestMenu;
import com.saleskit.cbbank.features.kpi.BlxAdapter;
import com.saleskit.cbbank.features.news.OnItemClicklistener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlxLoginAdapter extends RecyclerView.Adapter<BlxLoginAdapter.BlxLoginViewHolder> {
    private Context context;
    private List<InterestMenu.DataBean> dataBeans;
    private OnItemClicklistener onItemClicklistener;

    public BlxLoginAdapter(Context context, List<InterestMenu.DataBean> dataBeans, OnItemClicklistener onItemClicklistener) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public BlxLoginViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blx_new, parent, false);
        return new BlxLoginViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlxLoginViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class BlxLoginViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_blx_name)
        TextView tvBlxName;
        public BlxLoginViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            itemView.setOnClickListener(v -> onItemClicklistener.onItemClick(position));
            tvBlxName.setText(dataBeans.get(position).getName());
        }
    }
}
