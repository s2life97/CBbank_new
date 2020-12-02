package com.saleskit.cbbank.features.customer.presenter;

import com.saleskit.cbbank.features.base.MvpView;
import com.saleskit.cbbank.util.rx.netmodel.Customer;

import java.util.List;

public interface CustomerView extends MvpView {
    void hideLoading();

    void showListCusomter(List<Customer.DataBean> customerList, int totalPages);

    void showLoading();

    void hideSwipe();

    void showListReworkCusomter(List<Customer.DataBean> customerList);
}
