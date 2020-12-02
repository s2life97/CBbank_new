package com.saleskit.cbbank.features.home;

import android.os.Bundle;
import android.widget.ImageView;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.injection.component.ActivityComponent;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity  {
    @Inject
    HomePresent homePresent;

    ImageView ivMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSlideMenu();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_option;
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
        homePresent.detachView();
    }

    private void initSlideMenu() {

    }


}
