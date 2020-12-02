package com.saleskit.cbbank.util;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.app.feng.fixtablelayout.inter.IDataAdapter;
import com.app.feng.fixtablelayout.widget.SingleLineLinearLayout;
import com.app.feng.fixtablelayout.widget.TVHelper;
import java.util.ArrayList;
import java.util.List;

public class TunTableAdapter extends Adapter<TunTableAdapter.TableViewHolder> {
    private HorizontalScrollView titleView;
    private RecyclerView leftViews;
    private TextView left_top_view;
    private TunTableAdapter.ParametersHolder parametersHolder;
    private IDataAdapter dataAdapter;

    private TunTableAdapter(HorizontalScrollView titleView, RecyclerView leftViews, TextView left_top_view, TunTableAdapter.ParametersHolder parametersHolder, IDataAdapter dataAdapter) {
        this.titleView = titleView;
        this.leftViews = leftViews;
        this.left_top_view = left_top_view;
        this.parametersHolder = parametersHolder;
        this.dataAdapter = dataAdapter;
        this.initViews();
    }

    private void initViews() {
        this.left_top_view.setBackgroundColor(this.parametersHolder.title_color);
        this.left_top_view.setTextColor(Color.parseColor("#ffffff"));
        this.left_top_view.setLines(2);
        TVHelper.setTextView(this.left_top_view, this.dataAdapter.getTitleAt(0), this.parametersHolder.item_gravity,
                this.parametersHolder.item_width, this.parametersHolder.item_padding);
        this.leftViews.setAdapter(new TunTableAdapter.LeftViewAdapter());
        SingleLineLinearLayout titleChild = (SingleLineLinearLayout)this.titleView.getChildAt(0);

        for(int i = 0; i < this.dataAdapter.getTitleCount(); ++i) {
            TextView textView = TVHelper.generateTextView(titleChild.getContext(),
                    this.dataAdapter.getTitleAt(i), this.parametersHolder.item_gravity,
                    this.parametersHolder.item_width, this.parametersHolder.item_padding);
            textView.setTextColor(Color.parseColor("#ffffff"));
            textView.setLines(2);
            titleChild.addView(textView, i);
        }

        titleChild.setBackgroundColor(this.parametersHolder.title_color);
    }

    public TunTableAdapter.TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SingleLineLinearLayout singleLineLinearLayout = new SingleLineLinearLayout(parent.getContext());

        for(int i = 0; i < this.dataAdapter.getTitleCount(); ++i) {
            TextView textView = TVHelper.generateTextView(singleLineLinearLayout.getContext(), " ", this.parametersHolder.item_gravity, this.parametersHolder.item_width, this.parametersHolder.item_padding);
            singleLineLinearLayout.addView(textView, i);
        }

        return new TunTableAdapter.TableViewHolder(singleLineLinearLayout);
    }

    public void onBindViewHolder(TunTableAdapter.TableViewHolder holder, int position) {
        SingleLineLinearLayout ll_content = (SingleLineLinearLayout)holder.itemView;
        ll_content.setBackgroundColor(this.parametersHolder.col_2_color);
        List<TextView> bindViews = new ArrayList();

        for(int i = 0; i < this.dataAdapter.getTitleCount(); ++i) {
            TextView textView = (TextView)ll_content.getChildAt(i);
            bindViews.add(textView);
        }

        this.dataAdapter.convertData(position, bindViews);
        if (position % 2 != 0) {
            ll_content.setBackgroundColor(this.parametersHolder.col_1_color);
        }

    }

    public int getItemCount() {
        return this.dataAdapter.getItemCount();
    }

    public void notifyLoadData(int startPos, int loadNum) {
        this.notifyDataSetChanged();
        this.leftViews.getAdapter().notifyDataSetChanged();
    }

    public static class Builder {
        HorizontalScrollView titleView;
        RecyclerView leftViews;
        TextView left_top_view;
        TunTableAdapter.ParametersHolder parametersHolder;
        IDataAdapter dataAdapter;

        public Builder() {
        }

        public TunTableAdapter.Builder setTitleView(HorizontalScrollView titleView) {
            this.titleView = titleView;
            return this;
        }

        public TunTableAdapter.Builder setLeftViews(RecyclerView leftViews) {
            this.leftViews = leftViews;
            return this;
        }

        public TunTableAdapter.Builder setLeft_top_view(TextView left_top_view) {
            this.left_top_view = left_top_view;
            return this;
        }

        public TunTableAdapter.Builder setParametersHolder(TunTableAdapter.ParametersHolder parametersHolder) {
            this.parametersHolder = parametersHolder;
            return this;
        }

        public TunTableAdapter create() {
            return new TunTableAdapter(this.titleView, this.leftViews, this.left_top_view, this.parametersHolder, this.dataAdapter);
        }

        public TunTableAdapter.Builder setDataAdapter(IDataAdapter dataAdapter) {
            this.dataAdapter = dataAdapter;
            return this;
        }
    }

    public static class ParametersHolder {
        int col_1_color;
        int title_color;
        int item_width;
        int item_padding;
        int item_gravity;
        int col_2_color;

        public ParametersHolder(int s_color, int b_color, int title_color, int item_width, int item_padding, int item_gravity) {
            this.col_1_color = s_color;
            this.col_2_color = b_color;
            this.title_color = title_color;
            this.item_width = item_width;
            this.item_padding = item_padding;
            this.item_gravity = item_gravity;
        }
    }

    class LeftViewAdapter extends RecyclerView.Adapter<TunTableAdapter.LeftViewAdapter.LeftViewHolder> {
        LeftViewAdapter() {
        }

        private void bindView(int position, View v) {
            SingleLineLinearLayout singleLineLinearLayout = (SingleLineLinearLayout)v;
            TextView child = (TextView)singleLineLinearLayout.getChildAt(0);
            TVHelper.setTextView(child, " ", TunTableAdapter.this.parametersHolder.item_gravity, TunTableAdapter.this.parametersHolder.item_width, TunTableAdapter.this.parametersHolder.item_padding);
            child.setBackgroundColor(TunTableAdapter.this.parametersHolder.col_2_color);
            if (position % 2 != 0) {
                child.setBackgroundColor(TunTableAdapter.this.parametersHolder.col_1_color);
            }

            TunTableAdapter.this.dataAdapter.convertLeftData(position, child);
        }

        public TunTableAdapter.LeftViewAdapter.LeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            SingleLineLinearLayout singleLineLinearLayout = new SingleLineLinearLayout(parent.getContext());
            singleLineLinearLayout.addView(new TextView(parent.getContext()));
            return new TunTableAdapter.LeftViewAdapter.LeftViewHolder(singleLineLinearLayout);
        }

        public void onBindViewHolder(TunTableAdapter.LeftViewAdapter.LeftViewHolder holder, int position) {
            this.bindView(position, holder.itemView);
        }

        public int getItemCount() {
            return TunTableAdapter.this.dataAdapter.getItemCount();
        }

        class LeftViewHolder extends RecyclerView.ViewHolder {
            LeftViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    class TableViewHolder extends RecyclerView.ViewHolder {
        TableViewHolder(View itemView) {
            super(itemView);
        }
    }
}
