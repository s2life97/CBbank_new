package com.saleskit.cbbank.features.customer.add_new_customer;

public class FragmentChangedEvent {
    int stepIndex;
    String title;

    public FragmentChangedEvent(int stepIndex, String title) {
        this.stepIndex = stepIndex;
        this.title = title;
    }
}
