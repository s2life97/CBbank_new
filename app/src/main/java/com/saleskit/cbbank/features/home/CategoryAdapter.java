package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.flowone.ProductDetailActivity;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Product.DataBean> categoryModels;
    private OnItemClicklistener onItemClicklistener;

    public CategoryAdapter(Context context, List<Product.DataBean> categoryModels, OnItemClicklistener onItemClicklistener) {
        this.context = context;
        this.categoryModels = categoryModels;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_home, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tvContent.setText(categoryModels.get(position).getProductName());
            Glide.with(context).load(Constants.BASE_ONLINE_URL + categoryModels.get(position).getAvatar()).into(ivLogo);
            int type =  categoryModels.get(position).getProductType();;
            itemView.setOnClickListener(v -> {
               if(type==1 ){
                   Intent i1 = new Intent(context, ProductDetailActivity.class);
                   i1.putExtra(Constants.PRODUCT_TYPE, Constants.TYPE_HD);
                   i1.putExtra(Constants.PRODUCT_ID, categoryModels.get(position).getProductId());
                   i1.putExtra(Constants.PRODUCT_NAME, categoryModels.get(position).getProductName());
                   i1.putExtra(Constants.CUSTOMER_TYPE_PRODUCT, categoryModels.get(position).getCustomerType());
                   context.startActivity(i1);
               } else {
                   Intent i1 = new Intent(context, ProductDetailActivity.class);
                   i1.putExtra(Constants.PRODUCT_TYPE, Constants.CUSTOMER_HD);
                   i1.putExtra(Constants.PRODUCT_ID, categoryModels.get(position).getProductId());
                   i1.putExtra(Constants.PRODUCT_NAME, categoryModels.get(position).getProductName());
                   context.startActivity(i1);
               }
            });
        }
    }
}
