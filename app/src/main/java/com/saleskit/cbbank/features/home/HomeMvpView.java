package com.saleskit.cbbank.features.home;

import com.saleskit.cbbank.features.base.MvpView;

public interface  HomeMvpView extends MvpView {
    void onHomeClick();

    void closeDrawer();

    void onDestroyKpiView();

    void openNewScreen();

    void openCustomerScreen();

    void onLogOut();

    void onKPIClick();

    void onShowUserError();

}
