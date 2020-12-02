package com.saleskit.cbbank.features.customer.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Customer;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class CustomerPresenter extends BasePresenter<CustomerView> {
    @Inject
    public CustomerPresenter() {

    }

    @Override
    public void attachView(CustomerView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getListCustomer(Context context, String token, String key, int page) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(page));
        options.put("pagesize", "10");
        if (!TextUtils.isEmpty(key)) {
            options.put("searchKey", TextUtils.isEmpty(key) ? null : key.trim());
        }
        Call<Customer> call = apiInterface.getListCustomer(token, options);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    if (getView() != null) {
                        getView().hideSwipe();
                    }
                    List<Customer.DataBean> customerList = response.body().getData();
                    int totalItems = response.body().getTotalRecords();
                    int pages = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        pages += 1;
                    }
                    getView().showListCusomter(customerList, pages);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable throwable) {
                if(getView()!=null){
                getView().hideLoading();}
            }
        });
    }

    public void searchCustomer(Context context, String key, String token) {
        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("page", "1");
        options.put("pagesize", "10");
        options.put("searchKey", key);
        Call<Customer> call = apiInterface.getSearchListCustomer(token, options);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                getView().hideLoading();
                if (response.code() == 200) {
                    List<Customer.DataBean> customerList = response.body().getData();
//                    getView().showSearchListCusomter(customerList);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable throwable) {
                getView().hideLoading();
            }
        });
    }

    public void getListPermissionCustomer(Context context, String token, String key, int page, String userId) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(page));
        options.put("userIds", userId);
        options.put("pagesize", "10");
        if (!TextUtils.isEmpty(key)) {
            options.put("searchKey", key);
        }
        Call<Customer> call = apiInterface.getListPermissionCustomer(token, options);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    if (getView() != null) {
                        getView().hideSwipe();
                    }
                    List<Customer.DataBean> customerList = response.body().getData();
                    int totalItems = response.body().getTotalRecords();
                    int pages = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        pages += 1;
                    }
                    if (getView() != null) {
                        getView().showListCusomter(customerList, pages);
                    }
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable throwable) {
                getView().hideLoading();
                Timber.e("error " + throwable.getMessage());
            }
        });
    }

    public void getListReworkCustomer(Context context, String token, String userId) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("userId", userId);
//        if (!TextUtils.isEmpty(key)) {
//            options.put("searchKey", TextUtils.isEmpty(key) ? null : key);
//        }
        Call<Customer> call = apiInterface.getReworkCustomers(token, options);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                checkViewAttached();
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    if (getView() != null) {
                        getView().hideSwipe();
                        List<Customer.DataBean> customerList = response.body().getData();
                        getView().showListReworkCusomter(customerList);
                    }
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                getView().hideLoading();
            }
        });
    }
}
