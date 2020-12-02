package com.saleskit.cbbank.features.customer.add_new_customer;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.ControlBean;
import com.saleskit.cbbank.features.appointment.ClearableEditText;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluadateAdapter extends RecyclerView.Adapter<EvaluadateAdapter.EvaluadateViewHolder> {
    private CreatEvaluationView creatEvaluationView;
    private List<ControlBean.DataBean> list;
    private Context context;
    private HashMap<String, String> options = new HashMap<>();
    private boolean canChange;

    public EvaluadateAdapter(List<ControlBean.DataBean> list, Context context, CreatEvaluationView creatEvaluationView,
                             boolean canChange) {
        this.list = list;
        this.context = context;
        this.creatEvaluationView = creatEvaluationView;
        this.canChange = canChange;
    }

    @NonNull
    @Override
    public EvaluadateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evaluadate, parent, false);
        return new EvaluadateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluadateViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EvaluadateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        ClearableEditText tvContent;
        @BindView(R.id.iv_next)
        ImageView ivNext;
        @BindView(R.id.bt_pick)
        LinearLayout btPick;

        public EvaluadateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            int type = list.get(position).getType();
            tvContent.setText(list.get(position).getSelectedDisplay());
            tvTitle.setText(list.get(position).getDescription());
            boolean canEdit = list.get(position).isCanEdit();
            if (canEdit && canChange&& type==1) {
                ivNext.setVisibility(View.VISIBLE);
            } else {
                ivNext.setVisibility(View.GONE);

            }

            if (canEdit && canChange) {

                tvContent.setFocusable(true);
                itemView.setEnabled(true);
                tvTitle.setEnabled(true);
                tvContent.setEnabled(true);
            } else {
                tvContent.setFocusable(false);
                itemView.setEnabled(false);
                tvTitle.setEnabled(false);
                tvContent.setEnabled(false);

            }
            if (type == 1) {
                itemView.setOnClickListener(view -> creatEvaluationView.onButtonClick(position, tvContent));
                tvTitle.setOnClickListener(view -> creatEvaluationView.onButtonClick(position, tvContent));
                tvContent.setOnClickListener(view -> creatEvaluationView.onButtonClick(position, tvContent));
                tvContent.setFocusable(false);
            } else if (type == 2) {
                tvContent.setFocusable(true);
            }


            tvContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (type != 1)
                        creatEvaluationView.onTextChange(editable, position);
                }
            });
        }
    }
}
