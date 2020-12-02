package com.saleskit.cbbank.features.kpi;

import com.saleskit.cbbank.features.base.MvpView;

public interface DetailKpiView extends MvpView {
    void showLoading();

    void hideLoading();

    void showDetailEmployee(boolean rewnewModel, QuaterDetailEmployee.DataBean dataBean);

    void showMonthDetailEmployee(boolean rewnewModel, MonthDetailEmployee.DataBean dataBean);

    void showDetailYearEmployee(boolean rewnewModel, YearDetailEmployee.DataBean dataBean);
}
