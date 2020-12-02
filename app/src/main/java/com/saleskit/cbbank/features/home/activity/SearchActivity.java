package com.saleskit.cbbank.features.home.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.home.CategoryAdapter;
import com.saleskit.cbbank.features.home.CategoryModel;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SearchActivity extends BaseActivity implements OnItemClicklistener {
    @BindView(R.id.iv_menu_home)
    ImageView ivMenuHome;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rl_head_main)
    RelativeLayout rlHeadMain;
    @BindView(R.id.tv_number_item)
    TextView tvNumberItem;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> searchCategoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        tvCancel.setOnClickListener(v -> {
            finish();
        });
        ivMenuHome.setOnClickListener(v -> {
            onBackPressed();

        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }


    @Override
    public void onItemClick(int position) {
    }
}
