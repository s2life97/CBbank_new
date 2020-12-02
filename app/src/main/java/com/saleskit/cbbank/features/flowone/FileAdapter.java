package com.saleskit.cbbank.features.flowone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.home.ProductDetail;
import com.saleskit.cbbank.features.news.OnItemClicklistener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    private OnItemClicklistener onItemClicklistener;
    private List<ProductDetail.DataBean.ProductFileModelsBean> productFileModelsBeans;
    private Context context;

    public FileAdapter(OnItemClicklistener onItemClicklistener,List<ProductDetail.DataBean.ProductFileModelsBean> productFileModelsBeans, Context context) {
        this.onItemClicklistener = onItemClicklistener;
        this.productFileModelsBeans = productFileModelsBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return productFileModelsBeans.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_2)
        TextView tv2;
        @BindView(R.id.rl_download)
        RelativeLayout rlDownload;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tv2.setText(productFileModelsBeans.get(position).getLoanProfileName());
            rlDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicklistener.onItemClick(position);
                }
            });
        }
    }
}
