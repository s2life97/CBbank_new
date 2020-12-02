package com.saleskit.cbbank.injection.component;

import dagger.Subcomponent;

import com.saleskit.cbbank.features.appointment.OptionActivity;
import com.saleskit.cbbank.features.customer.CustomerListActivity;
import com.saleskit.cbbank.features.home.HomeActivity;
import com.saleskit.cbbank.features.home.NotiDetailActivity;
import com.saleskit.cbbank.features.home.NotificationListActivity;
import com.saleskit.cbbank.features.home.ReportViewActivity;
import com.saleskit.cbbank.features.home.activity.HomeActivity2;
import com.saleskit.cbbank.features.home.activity.SearchActivity;
import com.saleskit.cbbank.features.kpi.KpiActivity;
import com.saleskit.cbbank.features.kpi.RoomKpiActivity;
import com.saleskit.cbbank.features.account.LoginActivity;
import com.saleskit.cbbank.features.account.LoginEnActivity;
import com.saleskit.cbbank.features.main.MainActivity;
import com.saleskit.cbbank.features.appointment.AddApointmentActivity;
import com.saleskit.cbbank.features.appointment.AppointListActivity;
import com.saleskit.cbbank.features.appointment.UpdateResultActivity;
import com.saleskit.cbbank.features.news.NewActivity;
import com.saleskit.cbbank.features.personal.InfoPersonActivity;
import com.saleskit.cbbank.features.personal.PersonalActivity;
import com.saleskit.cbbank.features.tun.TunActivity;
import com.saleskit.cbbank.injection.PerActivity;
import com.saleskit.cbbank.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(KpiActivity kpiActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeActivity homeActivity);

    void inject(NewActivity newActivity);

    void inject(SearchActivity searchActivity);

    void inject(HomeActivity2 homeActivity2);

    void inject(RoomKpiActivity roomKpiActivity);

    void inject(LoginEnActivity loginEnActivity);

    void inject(CustomerListActivity customerListActivity);

    void inject(PersonalActivity personalActivity);

    void inject(AppointListActivity appointListActivity);

    void inject(AddApointmentActivity addApointmentActivity);

    void inject(UpdateResultActivity updateResultActivity);

    void inject(TunActivity tunActivity);

    void inject(OptionActivity optionActivity);

    void inject(InfoPersonActivity infoPersonActivity);

    void inject(NotificationListActivity infoPersonActivity);

    void inject(NotiDetailActivity infoPersonActivity);

    void inject(ReportViewActivity infoPersonActivity);
    void inject(com.saleskit.cbbank.features.customer.enterprise.AddNewEnterpriseCustomerActivity addNewEnterpriseCustomerActivity);
}
