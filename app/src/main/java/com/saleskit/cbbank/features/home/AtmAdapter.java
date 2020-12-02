package com.saleskit.cbbank.features.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AtmAdapter extends RecyclerView.Adapter<AtmAdapter.AtmViewHolder> {


    private Context context;
    private List<AtmResponse.DataBean> dataBeans;

    public AtmAdapter(Context context, List<AtmResponse.DataBean> dataBeans) {
        this.context = context;
        this.dataBeans = dataBeans;
    }

    @NonNull
    @Override
    public AtmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_atm, parent, false);
        return new AtmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtmViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class AtmViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_atm)
        TextView tvAtm;
        @BindView(R.id.tv_location)
        TextView tvLocation;

        public AtmViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tvAtm.setText(dataBeans.get(position).getName());
            tvLocation.setText(dataBeans.get(position).getAddress());
        }
    }
}
