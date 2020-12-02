package com.saleskit.cbbank.features.customer.add_new_customer;

import android.text.Editable;

import com.saleskit.cbbank.features.account.ControlBean;
import com.saleskit.cbbank.features.base.MvpView;
import com.saleskit.cbbank.features.appointment.ClearableEditText;

import java.util.List;

public interface CreatEvaluationView extends MvpView {
    void showUi(List<ControlBean.DataBean> controlBeans);

    void hideLoading();

    void intentNextScreen();

    void onButtonClick(int position, ClearableEditText tvContent);

    void onTextChange(Editable editable, int position);

    void showResult(CreatResultBean.DataBean dataBean);
}

