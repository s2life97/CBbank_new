package com.saleskit.cbbank.features.customer.add_new_customer;

import com.saleskit.cbbank.features.base.MvpView;

public interface CollateralView extends MvpView {
    void delete(int position);

    void update(int position);
}
