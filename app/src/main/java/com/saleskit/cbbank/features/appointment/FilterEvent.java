package com.saleskit.cbbank.features.appointment;

public class FilterEvent {
    public String startDate;
    public String endDate;
    public int resultStatus;

    public FilterEvent(String startDate, String endDate, int resultStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.resultStatus = resultStatus;
    }
}
