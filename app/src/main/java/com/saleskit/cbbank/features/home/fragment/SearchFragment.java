package com.saleskit.cbbank.features.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.home.CategoryAdapter;
import com.saleskit.cbbank.features.home.CategoryModel;
import com.saleskit.cbbank.features.home.Product;
import com.saleskit.cbbank.features.home.activity.BackEvent;
import com.saleskit.cbbank.features.home.activity.ChangePageEvent;
import com.saleskit.cbbank.features.home.activity.DeleteEvent;
import com.saleskit.cbbank.features.home.activity.SearchQueryEvent;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements OnItemClicklistener {
    private static final String TAG = "SearchFragment";
    private static SearchFragment searchFragment;
    @BindView(R.id.tv_number_item)
    TextView tvNumberItem;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private View view;
    private Context context;
    public static boolean openSearch= false;
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> searchCategoryList = new ArrayList<>();
    private List<Product.DataBean> products = new ArrayList<>();
    private List<Product.DataBean> searchProducts = new ArrayList<>();

    public static Fragment newInstance() {
        searchFragment = new SearchFragment();
        Bundle args = new Bundle();
        searchFragment.setArguments(args);
        return searchFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null)
            return view;
        openSearch = true;
        view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        getData();
        setupUI();
        return view;
    }

    private void setupUI() {

    }

    private void getData() {
//        categoryModelList.add(new CategoryModel("Bất động sản"));
//        categoryModelList.add(new CategoryModel("Vay mua nhà"));
//        categoryModelList.add(new CategoryModel("Thế chấp nhà đất"));
//        categoryModelList.add(new CategoryModel("Vay mua nhà chung cư"));
//        categoryModelList.add(new CategoryModel("Ô tô"));
//        categoryModelList.add(new CategoryModel("Ô tô kinh doanh"));


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        openSearch = false;
        EventBus.getDefault().postSticky(Constants.DELETE_EVENT);
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Subscribe(sticky = true)
    public void onSearchEvent(SearchQueryEvent searchQueryEvent) {
        tvNumberItem.setVisibility(View.VISIBLE);
        tvNumberItem.setText("");
        searchProducts.clear();
        for (Product.DataBean categoryModel : products) {
            if (categoryModel.getProductName().toLowerCase().contains(searchQueryEvent.content)
                    || categoryModel.getProductName().toUpperCase().contains(searchQueryEvent.content)) {
                searchProducts.add(categoryModel);
            }
        }
        categoryAdapter = new CategoryAdapter(context, searchProducts, this);
        rvMain.setAdapter(categoryAdapter);
        tvNumberItem.setText("Có " + searchProducts.size() + " kết quả tìm kiếm");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        openSearch = false;
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(sticky = true)
    public void onPgaeChange(ChangePageEvent changePageEvent) {
        products.clear();
        if (changePageEvent.postion == 0) {
            products = HawkHelper.getInstance(context).getListProduct();
        } else if(changePageEvent.postion ==1 ) {
            products = HawkHelper.getInstance(context).getListHDProduct();
        } else {
            products = HawkHelper.getInstance(context).getListEnterpriseProduct();
        }
        tvNumberItem.setText("Có " + products.size() + " kết quả tìm kiếm");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rvMain.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(context, products, this::onItemClick);
        rvMain.setAdapter(categoryAdapter);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            openSearch = true;
        } else {
            openSearch = false;
        }
    }

    @Subscribe(sticky = true)
    public void onBackEvent(BackEvent backEvent) {
        getActivity().onBackPressed();
    }


    @Subscribe(sticky = true)
    public void onDeleteEvent(DeleteEvent deleteEvent) {
        categoryAdapter = new CategoryAdapter(context, products, this);
        rvMain.setAdapter(categoryAdapter);
    }
}
