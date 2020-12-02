package com.saleskit.cbbank.features.home;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.saleskit.cbbank.features.home.fragment.EnterpriseProductFragment;
import com.saleskit.cbbank.features.home.fragment.Home2Fragment;
import com.saleskit.cbbank.features.home.fragment.HomeMainFragment;

public class MainViewpagerAdapter extends FragmentStatePagerAdapter {
    private HomeMainFragment homeMainFragment;
    private Home2Fragment home2Fragment;
    private EnterpriseProductFragment enterpriseProductFragment;

    public MainViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            if (homeMainFragment == null) homeMainFragment = new HomeMainFragment();
            return homeMainFragment;
        } else if (position == 1) {
            if (home2Fragment == null) home2Fragment = new Home2Fragment();
            return home2Fragment;
        } else if (position == 2) {
            if (enterpriseProductFragment == null) enterpriseProductFragment = new EnterpriseProductFragment();
            return enterpriseProductFragment;
        }
        return homeMainFragment;

    }

    @Override
    public int getCount() {
        return 3;
    }
}
