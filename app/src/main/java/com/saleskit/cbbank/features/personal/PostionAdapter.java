package com.saleskit.cbbank.features.personal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.InfoPersonJson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostionAdapter extends RecyclerView.Adapter<PostionAdapter.PositionViewHolder> {
    private Context context;
    private List<InfoPersonJson.DataBean.PositionBean> positionBeans = new ArrayList<>();

    public PostionAdapter(Context context, List<InfoPersonJson.DataBean.PositionBean> positionBeans) {
        this.context = context;
        this.positionBeans = positionBeans;
    }

    @NonNull
    @Override
    public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_postition, parent, false);
        return new PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return positionBeans.size();
    }

    public class PositionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_department)
        TextView tvDepartment;
        @BindView(R.id.bt_pick_person)
        LinearLayout btPickPerson;
        @BindView(R.id.tv_branch)
        TextView tvBranch;
        @BindView(R.id.tv_region)
        TextView tvRegion;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        public PositionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            tvBranch.setText(positionBeans.get(position).getBranchName());
            tvRegion.setText(positionBeans.get(position).getRegionName());
            tvPosition.setText(positionBeans.get(position).getPositionTitleName());
            tvDepartment.setText(positionBeans.get(position).getDepartmentName());
        }
    }
}
