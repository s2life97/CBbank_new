package com.saleskit.cbbank.features.appointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.news.OnItemClicklistener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

    private Context context;
    private List<OptionModel> options;
    private int currentValue;
    private OnItemClicklistener onItemClicklistener;

    public OptionAdapter(Context context, List<OptionModel> options, int value, OnItemClicklistener onItemClicklistener) {
        this.context = context;
        this.options = options;
        this.currentValue = value;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_golf_name)
        TextView tvGolfName;
        @BindView(R.id.checkbox)
        CheckBox checkbox;
        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            if (options.get(position).getValue() == currentValue) {
                checkbox.setChecked(true);
            } else
                checkbox.setChecked(false);
            String job = options.get(position).name;
            tvGolfName.setText(job);
            itemView.setOnClickListener(v -> onItemClicklistener.onItemClick(position));
        }

    }
}
