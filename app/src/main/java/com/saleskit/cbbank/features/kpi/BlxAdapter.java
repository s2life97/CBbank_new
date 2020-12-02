package com.saleskit.cbbank.features.kpi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;

public class BlxAdapter extends RecyclerView.Adapter<BlxAdapter.BlxViewHolder> {
    private Context context;


    @NonNull
    @Override
    public BlxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blx, parent, false);
        return new BlxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlxViewHolder holder, int position) {
        holder.onbind(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BlxViewHolder extends RecyclerView.ViewHolder {
        public BlxViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onbind(int position) {

        }
    }
}
