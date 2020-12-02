package com.saleskit.cbbank.features.kpi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthViewHolder> {

    public enum FilterType {
        YEAR,
        MONTH,
        QUARTER
    }

    private List<DateFilter> list;
    private FilterType filterType;
    private Context context;
    public int selectedPos = -1;
    private OnFilterClick onItemClicklistener;

    public MonthAdapter(List<DateFilter> list, Context context, FilterType filterType, OnFilterClick onItemClicklistener) {
        this.list = list;
        this.context = context;
        this.filterType = filterType;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_month, parent, false);
        return new MonthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthViewHolder holder, int position) {
        holder.onBind(position);
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MonthViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_month_name)
        TextView tvMonthName;
        @BindView(R.id.fl_main)
        FrameLayout flMain;

        public MonthViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {
            flMain.setOnClickListener(v -> {
                onItemClicklistener.onFilterClick(position, filterType);
                selectedPos = position;
                notifyDataSetChanged();
//                Toast.makeText(context, list.get(position).toString(), Toast.LENGTH_SHORT).show();
            });

            switch (filterType) {
                case YEAR:
                    tvMonthName.setText(String.valueOf(list.get(position).getYear()));
                    break;
                case MONTH:
                    tvMonthName.setText("T" + list.get(position).getMonth() + "/" + list.get(position).getYear());
                    break;
                case QUARTER:
                    String quater = "";
                    switch (list.get(position).getQuarter()) {
                        case 1:
                            quater = "I";
                            break;
                        case 2:
                            quater = "II";
                            break;
                        case 3:
                            quater = "III";
                            break;
                        case 4:
                            quater = "IV";
                            break;
                    }
                    tvMonthName.setText("Q" + quater + "/" + list.get(position).getYear());
                    break;
            }
        }
    }
}
