package com.saleskit.cbbank.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.app.feng.fixtablelayout.R.id;
import com.app.feng.fixtablelayout.R.layout;
import com.app.feng.fixtablelayout.R.styleable;
import com.app.feng.fixtablelayout.inter.IDataAdapter;
import com.app.feng.fixtablelayout.inter.ILoadMoreListener;
import com.app.feng.fixtablelayout.widget.SingleLineItemDecoration;
import com.app.feng.fixtablelayout.widget.TableLayoutManager;
import java.lang.ref.WeakReference;

public class TunFixTableLayout extends FrameLayout {
    public static final int MESSAGE_FIX_TABLE_LOAD_COMPLETE = 1001;
    RecyclerView recyclerView;
    HorizontalScrollView titleView;
    RecyclerView leftViews;
    TextView left_top_view;
    View leftViewShadow;
    FrameLayout fl_load_mask;
    int divider_height;
    int divider_color;
    int col_1_color;
    int col_2_color;
    int title_color;
    int item_width;
    int item_padding;
    int item_gravity;
    boolean show_left_shadow;
    private IDataAdapter dataAdapter;
    private boolean isLoading;
    private ILoadMoreListener loadMoreListener;
    private boolean hasMoreData;
    int lastVisablePos;

    public void setLoadMoreListener(ILoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public TunFixTableLayout(Context context) {
        this(context, (AttributeSet)null);
    }

    public TunFixTableLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunFixTableLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.show_left_shadow = false;
        this.isLoading = false;
        this.hasMoreData = true;
        this.lastVisablePos = -1;
        TypedArray array = context.obtainStyledAttributes(attrs, styleable.FixTableLayout);
        this.divider_height = array.getDimensionPixelOffset(styleable.FixTableLayout_fixtable_divider_height, 4);
        this.divider_color = array.getColor(styleable.FixTableLayout_fixtable_divider_color, -16777216);
        this.col_1_color = array.getColor(styleable.FixTableLayout_fixtable_column_1_color, -16776961);
        this.col_2_color = array.getColor(styleable.FixTableLayout_fixtable_column_2_color, -1);
        this.title_color = array.getColor(styleable.FixTableLayout_fixtable_title_color, -7829368);
        this.item_width = array.getDimensionPixelOffset(styleable.FixTableLayout_fixtable_item_width, 400);
        this.item_padding = array.getDimensionPixelOffset(styleable.FixTableLayout_fixtable_item_top_bottom_padding, 0);
        this.item_gravity = array.getInteger(styleable.FixTableLayout_fixtable_item_gravity, 0);
        switch(this.item_gravity) {
            case 0:
                this.item_gravity = 17;
                break;
            case 1:
                this.item_gravity = 8388627;
                break;
            case 2:
                this.item_gravity = 8388629;
        }

        this.show_left_shadow = array.getBoolean(styleable.FixTableLayout_fixtable_show_left_view_shadow, false);
        array.recycle();
        View view = inflate(context, layout.table_view, (ViewGroup)null);
        this.init(view);
        this.addView(view);
    }

    private void init(View view) {
        this.recyclerView = (RecyclerView)view.findViewById(id.recyclerView);
        this.titleView = (HorizontalScrollView)view.findViewById(id.titleView);
        this.leftViews = (RecyclerView)view.findViewById(id.leftViews);
        this.left_top_view = (TextView)view.findViewById(id.left_top_view);
        this.leftViewShadow = view.findViewById(id.leftView_shadows);
        this.fl_load_mask = (FrameLayout)view.findViewById(id.load_mask);
        TableLayoutManager t1 = new TableLayoutManager();
        TableLayoutManager t2 = new TableLayoutManager();
        this.recyclerView.setLayoutManager(t1);
        this.leftViews.setLayoutManager(t2);
        this.leftViews.addItemDecoration(new SingleLineItemDecoration(this.divider_height, this.divider_color));
        this.leftViews.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                TunFixTableLayout.this.recyclerView.onTouchEvent(event);
                return true;
            }
        });
        this.titleView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                TunFixTableLayout.this.recyclerView.onTouchEvent(event);
                return true;
            }
        });
        if (this.show_left_shadow) {
            this.leftViewShadow.setVisibility(VISIBLE);
        } else {
            this.leftViewShadow.setVisibility(GONE);
        }

        this.recyclerView.addItemDecoration(new SingleLineItemDecoration(this.divider_height, this.divider_color));

        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                TunFixTableLayout.this.titleView.scrollBy(dx, 0);
                TunFixTableLayout.this.leftViews.scrollBy(0, dy);
            }
        });

    }

    public void setAdapter(IDataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.initAdapter();
    }

    public void enableLoadMoreData() {
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0 && TunFixTableLayout.this.lastVisablePos == recyclerView.getAdapter().getItemCount() - 1 && !TunFixTableLayout.this.isLoading && TunFixTableLayout.this.hasMoreData) {
                    TunFixTableLayout.this.isLoading = true;
                    TunFixTableLayout.this.fl_load_mask.setVisibility(VISIBLE);
                    TunFixTableLayout.this.loadMoreListener.loadMoreData(new TunFixTableLayout.FixTableHandler(TunFixTableLayout.this, recyclerView));
                }

            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View bottomView = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                TunFixTableLayout.this.lastVisablePos = recyclerView.getChildAdapterPosition(bottomView);
            }
        });
    }

    private void initAdapter() {
        TunTableAdapter.Builder builder = new TunTableAdapter.Builder();
        TunTableAdapter tableAdapter = builder.setLeft_top_view(this.left_top_view).setTitleView(this.titleView)
                .setParametersHolder(new TunTableAdapter.ParametersHolder(this.col_1_color, this.col_2_color, this.title_color, this.item_width, this.item_padding, this.item_gravity)).setLeftViews(this.leftViews).setDataAdapter(this.dataAdapter).create();
        this.recyclerView.setAdapter(tableAdapter);
    }

    static class FixTableHandler extends Handler {
        WeakReference<RecyclerView> recyclerViewWeakReference;
        WeakReference<TunFixTableLayout> fixTableLayoutWeakReference;

        public FixTableHandler(TunFixTableLayout fixTableLayout, RecyclerView recyclerView) {
            this.recyclerViewWeakReference = new WeakReference(recyclerView);
            this.fixTableLayoutWeakReference = new WeakReference(fixTableLayout);
        }

        public void handleMessage(Message msg) {
            if (msg.what == 1001) {
                RecyclerView recyclerView = (RecyclerView)this.recyclerViewWeakReference.get();
                TunFixTableLayout fixTableLayout = (TunFixTableLayout)this.fixTableLayoutWeakReference.get();
                TunTableAdapter tableAdapter = (TunTableAdapter)recyclerView.getAdapter();
                int startPos = tableAdapter.getItemCount() - 1;
                int loadNum = msg.arg1;
                if (msg.arg1 > 0) {
                    tableAdapter.notifyLoadData(startPos, loadNum);
                } else {
                    fixTableLayout.hasMoreData = false;
                }

                fixTableLayout.fl_load_mask.setVisibility(GONE);
                fixTableLayout.isLoading = false;
            }

        }
    }
}
