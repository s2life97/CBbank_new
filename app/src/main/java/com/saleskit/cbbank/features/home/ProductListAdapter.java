package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private List<Product.DataBean> list;
    private Context context;
    private OnItemClicklistener onItemClicklistener;

    public ProductListAdapter(List<Product.DataBean> list, Context context, OnItemClicklistener onItemClicklistener) {
        this.list = list;
        this.context = context;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tvContent.setText(list.get(position).getProductName());
            Glide.with(context).load(Constants.BASE_ONLINE_URL + list.get(position).getAvatar()).into(ivLogo);
            itemView.setOnClickListener(v -> onItemClicklistener.onItemClick(position));
        }
    }
}
