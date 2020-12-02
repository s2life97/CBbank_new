package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileNameAdapter extends RecyclerView.Adapter<FileNameAdapter.FileNameViewHolder> {
    private Context context;
    private List<ProductDetail.DataBean.ProductFileModelsBean> list;

    public FileNameAdapter(Context context, List<ProductDetail.DataBean.ProductFileModelsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FileNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file_name, parent, false);
        return new FileNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileNameViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FileNameViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContet;

        public FileNameViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            String content = list.get(position).getLoanProfileName();
            if(TextUtils.isEmpty(list.get(position).getFilePath())){
                tvContet.setText(content);
            } else {
                tvContet.setVisibility(View.GONE);
            }
        }
    }
}
