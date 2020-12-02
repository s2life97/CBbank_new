package com.saleskit.cbbank.features.account;

public class ProcessBeam {

    /**
     * CustomerProfileId : 0
     * Process : 0
     */

    private String CustomerProfileId;
    private int Process;

    public ProcessBeam(String customerProfileId, int process) {
        CustomerProfileId = customerProfileId;
        Process = process;
    }

    public String getCustomerProfileId() {
        return CustomerProfileId;
    }

    public void setCustomerProfileId(String CustomerProfileId) {
        this.CustomerProfileId = CustomerProfileId;
    }

    public int getProcess() {
        return Process;
    }

    public void setProcess(int Process) {
        this.Process = Process;
    }
}
