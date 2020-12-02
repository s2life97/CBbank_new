package com.saleskit.cbbank.features.tun;

import com.saleskit.cbbank.features.account.EmployeeJson;
import com.saleskit.cbbank.features.account.RegionJson;
import com.saleskit.cbbank.features.account.TunDepartmentJson;
import com.saleskit.cbbank.features.account.TunJson;
import com.saleskit.cbbank.features.base.MvpView;

import java.util.List;

public interface TunView extends MvpView {
    void hideLoading();

    void showLoading();

    void showBranchlist(List<TunJson.DataBean.ResultsBean> list, int pages);

    void showAllDepartment(List<TunDepartmentJson.DataBean.ResultsBean> list, int pages);

    void showAllEmployee(List<EmployeeJson.DataBean.ResultsBean> list, int pages);

    void showAllRegion(List<RegionJson.DataBean.ResultsBean> dataBeans, int pages);
}
