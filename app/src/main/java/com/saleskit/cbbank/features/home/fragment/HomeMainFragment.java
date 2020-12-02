package com.saleskit.cbbank.features.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.base.BaseFragment;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.flowone.ProductDetailActivity;
import com.saleskit.cbbank.features.home.Product;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.injection.component.FragmentComponent;
import com.saleskit.cbbank.util.NetworkUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMainFragment extends BaseFragment {
    private static final String TAG = "HomeMainFragment";
    @BindView(R.id.iv_6)
    ImageView iv6;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.rl_6)
    RelativeLayout rl6;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.rl_5)
    RelativeLayout rl5;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.rl_4)
    RelativeLayout rl4;
    @BindView(R.id.iv_7)
    ImageView iv7;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.rl_7)
    RelativeLayout rl7;
    @BindView(R.id.iv_8)
    ImageView iv8;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.rl_8)
    RelativeLayout rl8;
    @BindView(R.id.guideline)
    Guideline guideline;
    @BindView(R.id.guideline3)
    Guideline guideline3;
    @BindView(R.id.iv_9)
    ImageView iv9;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.rl_9)
    RelativeLayout rl9;
    @BindView(R.id.iv_10)
    ImageView iv10;
    @BindView(R.id.tv_10)
    TextView tv10;
    @BindView(R.id.rl_10)
    RelativeLayout rl10;
    @BindView(R.id.iv_11)
    ImageView iv11;
    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.rl_11)
    RelativeLayout rl11;
    private List<View> buttons = new ArrayList<>();
    private List<View> images = new ArrayList<>();
    private List<TextView> texts = new ArrayList<>();
    private Context context;
    private View view;
    private String token;
    private boolean isCreatNewfile = false;
    private CustomerInfo.DataBean customer;
    private List<Product.DataBean> ownProducts = new ArrayList<>();

    public static HomeMainFragment getInstance() {
        return new HomeMainFragment();
    }


    public HomeMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_home_main, container, false);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        ButterKnife.bind(this, view);

        token = HawkHelper.getInstance(context).getToken();
        creatList();
        getData();
        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_main;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {

    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }

    private void getData() {
        ownProducts = HawkHelper.getInstance(context).getListProduct();
        int size = ownProducts.size();
        if (size >= 11) {
            size = 11;
        }
        for (int i = 0; i < size; i++) {
            //Glide.with(context).load(Constants.BASE_ONLINE_URL + ownProducts.get(i).getAvatar()).into((ImageView) images.get(i));
            texts.get(i).setText(ownProducts.get(i).getProductName());
        }
        for (int i = size; i < 11; i++) {
            buttons.get(i).setVisibility(View.INVISIBLE);

        }
        for (int i = 0; i < size; i++) {
            int productID = ownProducts.get(i).getProductId();
            String data = ownProducts.get(i).getProductName();
            int productType = ownProducts.get(i).getProductType();
            int customerType = ownProducts.get(i).getCustomerType();
            buttons.get(i).setOnClickListener(v -> {
//
                Intent i1 = new Intent(getActivity(), ProductDetailActivity.class);
                i1.putExtra(Constants.PRODUCT_TYPE, Constants.TYPE_HD);
                i1.putExtra(Constants.TYPE_PRODUCT, productType);
                i1.putExtra(Constants.PRODUCT_ID, productID);
                i1.putExtra(Constants.PRODUCT_NAME, data);
                i1.putExtra(Constants.CUSTOMER_TYPE_PRODUCT, customerType);
                startActivity(i1);
                getActivity().overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
            });
        }
    }

    private void creatList() {
        buttons.add(rl1);
        buttons.add(rl2);
        buttons.add(rl3);
        buttons.add(rl4);
        buttons.add(rl5);
        buttons.add(rl6);
        buttons.add(rl7);
        buttons.add(rl8);
        buttons.add(rl9);
        buttons.add(rl10);
        buttons.add(rl11);

        images.add(iv1);
        images.add(iv2);
        images.add(iv3);
        images.add(iv4);
        images.add(iv5);
        images.add(iv6);
        images.add(iv7);
        images.add(iv8);
        images.add(iv9);
        images.add(iv10);
        images.add(iv11);

        texts.add(tv1);
        texts.add(tv2);
        texts.add(tv3);
        texts.add(tv4);
        texts.add(tv5);
        texts.add(tv6);
        texts.add(tv7);
        texts.add(tv8);
        texts.add(tv9);
        texts.add(tv10);
        texts.add(tv11);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

//    @Subscribe(sticky = true)
//    public void onCreatFile(CreatFileEvent creatFileEvent) {
//        isCreatNewfile = true;
//        customer = creatFileEvent.customer;
//    }

    @Override
    public void onStop() {
        super.onStop();
        isCreatNewfile = false;
    }
}
