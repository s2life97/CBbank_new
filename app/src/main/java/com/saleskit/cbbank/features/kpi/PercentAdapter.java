package com.saleskit.cbbank.features.kpi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PercentAdapter extends RecyclerView.Adapter<PercentAdapter.PercentViewHolder> {

    private Animation animationZoomIn;
    private Animation animationZoomOut;
    private List<DateFilter> list;
    private MonthAdapter.FilterType filterType;
    private Context context;
    public int selectedPos = -1;
    private OnFilterClick onItemClicklistener;
    private List<Double> percentKpi;

    public PercentAdapter(List<Double> percentKpi, List<DateFilter> list, Context context, MonthAdapter.FilterType filterType, OnFilterClick onItemClicklistener) {
        this.percentKpi = percentKpi;
        this.list = list;
        this.context = context;
        this.filterType = filterType;
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public PercentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_percent, parent, false);
        return new PercentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PercentViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void setFilterType(MonthAdapter.FilterType filterType) {
        this.filterType = filterType;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PercentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        @BindView(R.id.tv_percent_name)
        TextView tvPercentNem;
        int position;

        public PercentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint({"SetTextI18n", "Locale.US"})
        public void onBind(int position) {
            this.position = position;
            animationZoomIn = AnimationUtils.loadAnimation(context, R.anim.animation_zoom_in);
            animationZoomOut = AnimationUtils.loadAnimation(context, R.anim.animation_out_screen);
            tvPercentNem.setText(NumberFormat.getNumberInstance(Locale.US).format(percentKpi.get(position)) + "%");

            if (selectedPos == position) {
                tvPercentNem.setHasTransientState(true);
                tvPercentNem.setAnimation(animationZoomIn);
            }

            switch (filterType) {
                case YEAR:
                    tvPercent.setText(String.valueOf(list.get(position).getYear()));
                    break;
                case MONTH:
                    tvPercent.setText("T" + list.get(position).getMonth() + "/" + list.get(position).getYear());
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
                    tvPercent.setText("Q" + quater + "/" + list.get(position).getYear());
                    break;
            }

            itemView.setOnClickListener(v -> {
                onItemClicklistener.onFilterClick(position, filterType);
                selectedPos = position;
                notifyDataSetChanged();
//                Toast.makeText(context, list.get(position).toString(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}
