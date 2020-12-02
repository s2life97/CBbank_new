package com.saleskit.cbbank.features.account;

public class ConfirmModel {

    /**
     * CustomerProfileId : 0
     * Money : 0
     * PeriodTime : 0
     * Process : 0
     */

    private String CustomerProfileId;
    private String Money;
    private String PeriodTime;
    private int Process;

    public ConfirmModel(String customerProfileId, String money, String periodTime, int process) {
        CustomerProfileId = customerProfileId;
        Money = money;
        PeriodTime = periodTime;
        Process = process;
    }

    public String getCustomerProfileId() {
        return CustomerProfileId;
    }

    public void setCustomerProfileId(String CustomerProfileId) {
        this.CustomerProfileId = CustomerProfileId;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String Money) {
        this.Money = Money;
    }

    public String getPeriodTime() {
        return PeriodTime;
    }

    public void setPeriodTime(String PeriodTime) {
        this.PeriodTime = PeriodTime;
    }

    public int getProcess() {
        return Process;
    }

    public void setProcess(int Process) {
        this.Process = Process;
    }

}
