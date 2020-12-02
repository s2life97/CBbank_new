package com.saleskit.cbbank.features.personal;

import com.saleskit.cbbank.features.base.MvpView;

public interface PersonView extends MvpView {
    void onDelete(int position);
    void onItemClick(int position);
}
