package com.saleskit.cbbank.features.home.activity;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.home.ProductDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.ProductInfoViewHolder> {

    private Context context;
    private List<ProductDetail.DataBean.DetailInformationsBean> list;

    public ProductInfoAdapter(Context context, List<ProductDetail.DataBean.DetailInformationsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_info, parent, false);
        return new ProductInfoViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ProductInfoViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductInfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public ProductInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onBind(int position) {
            String title= list.get(position).getTitle()+ ": ";
            boolean breakLine = list.get(position).isIsBreakLine();
            String content = list.get(position).getContent();
            tvContent.setText(list.get(position).getContent());

            if (breakLine) {
                if (TextUtils.isEmpty(content)) {
                    tvTitle.setVisibility(View.GONE);
                    tvContent.setVisibility(View.GONE);
                } else {
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText(Html.fromHtml("<b>" + title + "</b>"));
                    tvContent.setVisibility(View.VISIBLE);
                }
            } else {
                if (TextUtils.isEmpty(content)) {
                    tvTitle.setVisibility(View.GONE);
                    tvContent.setVisibility(View.GONE);
                } else {
                    tvTitle.setText(Html.fromHtml("<b>" + title + "</b>" +
                            content  ));
                    tvContent.setVisibility(View.GONE);
                    tvTitle.setVisibility(View.VISIBLE);
                }

            }
        }
    }
}
