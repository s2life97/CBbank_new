package com.saleskit.cbbank.features.customer.add_new_customer.model;

public class CustomerProfile {


    /**
     * data : {"customerProfileId":900,"customerProfileCode":"19310HD00600001","productId":29,"customerId":342,"money":8497,"periodTime":89,"interest":79,"transactionDate":null,"appointmentTransactionDate":"2017-11-22T00:00:00","branchId":"310","departmentId":"31001","bosInputDate":null,"process":0,"status":2,"productCode":"HD006","productName":"Tiền gửi thanh toán (VND/USD)","productType":2,"customerFirstName":"1","customerLastName":"Tester","customerCode":"CUSTOMER_CODE","customerIdentityNumber":"12497864944","branchName":"Chi nhánh Bình Thuận","departmentName":"P.KHBL - BINH THUAN","cbBankStructureId":null,"cbBankStructureName":null,"moneyMaxAmount":0}
     * totalRecords : 0
     */

    private DataBean data;
    private int totalRecords;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public static class DataBean {
        /**
         * customerProfileId : 900
         * customerProfileCode : 19310HD00600001
         * productId : 29
         * customerId : 342
         * money : 8497.0
         * periodTime : 89
         * interest : 79.0
         * transactionDate : null
         * appointmentTransactionDate : 2017-11-22T00:00:00
         * branchId : 310
         * departmentId : 31001
         * bosInputDate : null
         * process : 0
         * status : 2
         * productCode : HD006
         * productName : Tiền gửi thanh toán (VND/USD)
         * productType : 2
         * customerFirstName : 1
         * customerLastName : Tester
         * customerCode : CUSTOMER_CODE
         * customerIdentityNumber : 12497864944
         * branchName : Chi nhánh Bình Thuận
         * departmentName : P.KHBL - BINH THUAN
         * cbBankStructureId : null
         * cbBankStructureName : null
         * moneyMaxAmount : 0.0
         */

        private int customerProfileId;
        private String customerProfileCode;
        private int productId;
        private int customerId;
        private double money;
        private int periodTime;
        private double interest;
        private Object transactionDate;
        private String appointmentTransactionDate;
        private String branchId;
        private String departmentId;
        private Object bosInputDate;
        private int process;
        private int status;
        private String productCode;
        private String productName;
        private int productType;
        private String customerFirstName;
        private String customerLastName;
        private String customerCode;
        private String customerIdentityNumber;
        private String branchName;
        private String departmentName;
        private Object cbBankStructureId;
        private Object cbBankStructureName;
        private double moneyMaxAmount;
        private String customerEmail;
        private String customerPhoneNumber;
        private String address;

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getCustomerPhoneNumber() {
            return customerPhoneNumber;
        }

        public void setCustomerPhoneNumber(String customerPhoneNumber) {
            this.customerPhoneNumber = customerPhoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCustomerProfileId() {
            return customerProfileId;
        }

        public void setCustomerProfileId(int customerProfileId) {
            this.customerProfileId = customerProfileId;
        }

        public String getCustomerProfileCode() {
            return customerProfileCode;
        }

        public void setCustomerProfileCode(String customerProfileCode) {
            this.customerProfileCode = customerProfileCode;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getPeriodTime() {
            return periodTime;
        }

        public void setPeriodTime(int periodTime) {
            this.periodTime = periodTime;
        }

        public double getInterest() {
            return interest;
        }

        public void setInterest(double interest) {
            this.interest = interest;
        }

        public Object getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(Object transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getAppointmentTransactionDate() {
            return appointmentTransactionDate;
        }

        public void setAppointmentTransactionDate(String appointmentTransactionDate) {
            this.appointmentTransactionDate = appointmentTransactionDate;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public Object getBosInputDate() {
            return bosInputDate;
        }

        public void setBosInputDate(Object bosInputDate) {
            this.bosInputDate = bosInputDate;
        }

        public int getProcess() {
            return process;
        }

        public void setProcess(int process) {
            this.process = process;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public String getCustomerFirstName() {
            return customerFirstName;
        }

        public void setCustomerFirstName(String customerFirstName) {
            this.customerFirstName = customerFirstName;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public void setCustomerLastName(String customerLastName) {
            this.customerLastName = customerLastName;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public String getCustomerIdentityNumber() {
            return customerIdentityNumber;
        }

        public void setCustomerIdentityNumber(String customerIdentityNumber) {
            this.customerIdentityNumber = customerIdentityNumber;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public Object getCbBankStructureId() {
            return cbBankStructureId;
        }

        public void setCbBankStructureId(Object cbBankStructureId) {
            this.cbBankStructureId = cbBankStructureId;
        }

        public Object getCbBankStructureName() {
            return cbBankStructureName;
        }

        public void setCbBankStructureName(Object cbBankStructureName) {
            this.cbBankStructureName = cbBankStructureName;
        }

        public double getMoneyMaxAmount() {
            return moneyMaxAmount;
        }

        public void setMoneyMaxAmount(double moneyMaxAmount) {
            this.moneyMaxAmount = moneyMaxAmount;
        }
    }
}
