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
import com.saleskit.cbbank.features.news.OnItemClicklistener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InterestTableAdapter extends RecyclerView.Adapter<InterestTableAdapter.InterestViewHolder> {
    private List<InterestMenu.DataBean> dataBeans;
    private Context context;
    private OnItemClicklistener onItemClicklistener;

    public InterestTableAdapter(List<InterestMenu.DataBean> dataBeans, Context context, OnItemClicklistener onItemClicklistener) {
        this.dataBeans = dataBeans;
        this.context = context;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public InterestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_table, parent, false);
        return new InterestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class InterestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        public InterestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tvName.setText(dataBeans.get(position).getName());
            itemView.setOnClickListener(v -> {
                onItemClicklistener.onItemClick(position);
            });
        }
    }
}
