package com.saleskit.cbbank.features.kpi;

import com.saleskit.cbbank.features.account.KPI;
import com.saleskit.cbbank.features.account.KpiEmplyee;
import com.saleskit.cbbank.features.base.MvpView;

import java.util.List;

public interface KpiView extends MvpView {
    void onPercentClick();

    void showAllBranch(List<KpiBranch.DataBean.ResultsBean> list, int totalPages);

    void showLoading();

    void hideLoading();


    void showRegionList(List<KPI.DataBean.ResultsBean> list, int totalPage);

    void showAllDepartment(List<KpiDepartment.DataBean.ResultsBean> list, int pages);

    void showAllEmployee(List<KpiEmplyee.DataBean.ResultsBean> list, int pages);
}
