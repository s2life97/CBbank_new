package com.saleskit.cbbank.features.home;

import com.saleskit.cbbank.features.base.BasePresenter;

import javax.inject.Inject;

public class HomePresent extends BasePresenter<HomeMvpView> {
    @Inject
    public HomePresent () {
    }

    @Override
    public void attachView(HomeMvpView mvpView) {
        super.attachView(mvpView);
    }
}
