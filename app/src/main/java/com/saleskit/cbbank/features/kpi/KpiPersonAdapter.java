package com.saleskit.cbbank.features.kpi;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.KPIJson;
import com.saleskit.cbbank.features.appointment.OnLoadMoreListener;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KpiPersonAdapter extends RecyclerView.Adapter<KpiPersonAdapter.KpiPersonViewHolder> {

    private Context context;
    private List<KPIJson.DataBean> kpiPersonModels;
    private boolean hasPostion;
    private OnItemClicklistener onItemClicklistener;
    private boolean hasPercent;
    private OnLoadMoreListener onLoadMoreListener;
    private int lastTotalItemCount, totalItemCount;
    private boolean loading;
    private boolean isShow = true;

    public KpiPersonAdapter(Context context, List<KPIJson.DataBean> kpiPersonModels,
                            boolean hasPostion, OnItemClicklistener onItemClicklistener,
                            boolean hasPercent, RecyclerView recyclerView) {
        this.context = context;
        this.kpiPersonModels = kpiPersonModels;
        this.hasPostion = hasPostion;
        this.onItemClicklistener = onItemClicklistener;
        this.hasPercent = hasPercent;
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
    public KpiPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kpi_worker, parent, false);
        return new KpiPersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KpiPersonViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return kpiPersonModels.size();
    }

    public class KpiPersonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        @BindView(R.id.pb_media_progress_quater)
        ProgressBar pbMediaProgress;
        @BindView(R.id.tv_position)
        TextView tvPosition;

        public KpiPersonViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tvName.setText(kpiPersonModels.get(position).getName());
            if (hasPostion) {
                tvPosition.setVisibility(View.VISIBLE);
                tvPosition.setText(kpiPersonModels.get(position).getJobTitle());
            } else {
                tvPosition.setVisibility(View.GONE);
            }
            if (!hasPercent) {
                tvPercent.setVisibility(View.GONE);
                pbMediaProgress.setVisibility(View.GONE);
            } else {
                if (kpiPersonModels.get(position).isShow()) {
                    tvPercent.setVisibility(View.VISIBLE);
                    pbMediaProgress.setVisibility(View.VISIBLE);
                } else {
                    tvPercent.setVisibility(View.INVISIBLE);
                    pbMediaProgress.setVisibility(View.INVISIBLE);

                }
            }


            tvPercent.setText(String.valueOf(kpiPersonModels.get(position).

                    getPercentKPI()));
            pbMediaProgress.setProgress((int) (kpiPersonModels.get(position).

                    getPercentKPI()));

            if (kpiPersonModels.get(position).

                    getPercentKPI() > 100) {
                tvPercent.setTextColor(context.getResources().getColor(R.color.green));
                AppUtil.setBackgroundForView(tvPercent, context, R.drawable.rounded_kpi_green);
                AppUtil.setProgressDrawable(pbMediaProgress, context, R.drawable.progress_bg_green);
            } else if (kpiPersonModels.get(position).getPercentKPI() >= 30) {
                tvPercent.setTextColor(context.getResources().getColor(R.color.blue_ocean));
                AppUtil.setBackgroundForView(tvPercent, context, R.drawable.rounded_kpi_oceanblue);
                AppUtil.setProgressDrawable(pbMediaProgress, context, R.drawable.progress_bg_blueocean);
            } else if (kpiPersonModels.get(position).getPercentKPI() >= 15) {
                tvPercent.setTextColor(context.getResources().getColor(R.color.black));
                AppUtil.setBackgroundForView(tvPercent, context, R.drawable.rounded_kpi_black);
                AppUtil.setProgressDrawable(pbMediaProgress, context, R.drawable.progress_bg_black);
            } else if (kpiPersonModels.get(position).getPercentKPI() > 0) {
                tvPercent.setTextColor(context.getResources().getColor(R.color.red));
                AppUtil.setBackgroundForView(tvPercent, context, R.drawable.rounded_kpi_pink);
                AppUtil.setProgressDrawable(pbMediaProgress, context, R.drawable.progress_bg_pink);
            } else if (kpiPersonModels.get(position).getPercentKPI() == 0) {
                tvPercent.setTextColor(context.getResources().getColor(R.color.red));
                AppUtil.setBackgroundForView(tvPercent, context, R.drawable.rounded_kpi_red);
                AppUtil.setProgressDrawable(pbMediaProgress, context, R.drawable.progress_bg_black);
            } else {
                tvPercent.setTextColor(context.getResources().getColor(R.color.red));
                AppUtil.setBackgroundForView(tvPercent, context, R.drawable.rounded_kpi_red);
                AppUtil.setProgressDrawable(pbMediaProgress, context, R.drawable.progress_bg_red);
            }

            itemView.setOnClickListener(v ->

            {
                itemView.setEnabled(false);
                onItemClicklistener.onItemClick(position);
                new Handler().postDelayed(() -> itemView.setEnabled(true), 300);
            });
        }

    }
}
