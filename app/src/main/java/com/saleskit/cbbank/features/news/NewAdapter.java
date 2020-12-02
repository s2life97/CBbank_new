package com.saleskit.cbbank.features.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.appointment.OnLoadMoreListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewsViewHolder> {

    private List<News.DataBean> newModelList;
    private Context context;
    private OnItemClicklistener onItemClicklistener;
    private OnLoadMoreListener onLoadMoreListener;
    private int lastTotalItemCount, totalItemCount;
    private boolean loading;
    public NewAdapter(List<News.DataBean> newModelList, Context context, OnItemClicklistener onItemClicklistener, RecyclerView recyclerView) {
        this.newModelList = newModelList;
        this.context = context;
        this.onItemClicklistener = onItemClicklistener;
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
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return newModelList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.iv_image)
        ImageView ivImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            itemView.setOnClickListener(v -> {
                onItemClicklistener.onItemClick(position);
                Intent i = new Intent(context, DetailNewActivity.class);
                i.putExtra(Constants.ARTICLE, newModelList.get(position).getArticleId());
                context.startActivity(i);
            });
            tvTitle.setText(newModelList.get(position).getTitle());
            tvContent.setText(newModelList.get(position).getShortContent());
            Glide.with(context).load(Constants.BASE_ARTICLE_URL + newModelList.get(position).getArticleImage())
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder).diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ivImage);
        }
    }
}
