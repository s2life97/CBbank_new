package com.saleskit.cbbank.features.appointment;

import com.saleskit.cbbank.features.account.Appointment;
import com.saleskit.cbbank.features.base.MvpView;

import java.util.List;

public interface AppointView extends MvpView {
    void showResult(Imgage.DataBean image);

    void showNearestAppoint(List<Appointment.DataBean> list);

    void showALlAppoint(List<Appointment.DataBean> list, int pages);

    void hideLoading();

    void showListSearchedAppoint(List<Appointment.DataBean> list);

    void hideSwipe();

    void clearList();

    void setLoaded();

    void showFilterAppoint(List<FilterAppointment.DataBean.AppointmentBelongPeriodsBean> list, int pages,
                           FilterAppointment.DataBean dataBean, int totalItems);
}
