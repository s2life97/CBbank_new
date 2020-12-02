package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saleskit.cbbank.R;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentUseAdapter extends RecyclerView.Adapter<RecentUseAdapter.RecentUseViewHolder> {

    private Context context;
    private List<RecentModel> recentModelList;

    public RecentUseAdapter(Context context, List<RecentModel> recentModelList) {
        this.context = context;
        this.recentModelList = recentModelList;
    }

    @NonNull
    @Override
    public RecentUseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_percent, parent, false);
        return new RecentUseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentUseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return recentModelList.size();
    }

    public class RecentUseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_bg)
        ImageView ivBg;
        public RecentUseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tvTitle.setText(recentModelList.get(position).getTitle());
            tvContent.setText(recentModelList.get(position).getContent());
            Glide.with(context).load(recentModelList.get(position).getImagePath()).into(ivBg);
        }
    }
}
