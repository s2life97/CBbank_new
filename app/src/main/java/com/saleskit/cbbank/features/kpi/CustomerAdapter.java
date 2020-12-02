package com.saleskit.cbbank.features.kpi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saleskit.cbbank.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private Context context;
    public CustomerAdapter(Context context, List<CustomerModel> customerModels) {
        this.context = context;
        this.customerModels = customerModels;
    }

    private List<CustomerModel> customerModels;

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer_info, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return customerModels.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_expire_date)
        TextView tvExpireDate;
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_information)
        TextView tvInformation;
        @BindView(R.id.tv_number_borrow)
        TextView tvNumberBorrow;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
//            tvCompanyName.setText(customerModels.get(position).getCompanyName());
//            tvExpireDate.setText(customerModels.get(position).getExpireDate());
//            tvInformation.setText(customerModels.get(position).getInformation());
//            tvNumberBorrow.setText((int) customerModels.get(position).getMoneyBorrowed());
        }
    }
}
