package com.saleskit.cbbank.features.account;

import com.saleskit.cbbank.features.base.MvpView;

interface LoginView extends MvpView {
    void onSuccessLogin(String authenToken);

    void showLoading();

    void hideLoading();

    void saveEmployeeInfo(EmployeeInfomation.DataBean dataBean);

    void showLockNoti();

    void showErrorMess(String mess);
}
