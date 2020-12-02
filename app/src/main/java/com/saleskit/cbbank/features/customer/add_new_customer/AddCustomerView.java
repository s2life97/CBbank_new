package com.saleskit.cbbank.features.customer.add_new_customer;

import com.saleskit.cbbank.features.account.CustomerInfoRespond;
import com.saleskit.cbbank.features.base.MvpView;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.features.personal.CustomerInfomation;

public interface AddCustomerView extends MvpView {
    void hideLoading();

    void showCustomerInfomation(CustomerInfo.DataBean dataBean);

    void showResultData(String result);

    void intentNextStep();

    void showProfileCustomerID(CustomerInfoRespond.DataBeanX.DataBean data);
}
