package com.saleskit.cbbank.features.personal;

import java.util.List;

public class CustomerInfomation {

    /**
     * data : {"customerId":1813,"customerType":1,"firstName":"","lastName":"djs","customerCode":"CUSTOMER_CODE","dateOfBirth":"2020-04-17T00:00:00","gender":2,"identityNumber":"8686","issuedDate":"2020-07-20T00:00:00","issuedBy":"djsjs","phoneNumber":"09466565656","address":"sjs","email":"djs@gmail.com","educationStatus":10,"assetStatus":118,"maritalStatus":15,"cicResult":2,"enterpriseType":null,"enterpriseName":null,"enterpriseLicenseNumber":null,"enterpriseLicenseIssuedDate":null,"enterpriseLicenseIssuedBy":null,"enterpriseLicenseAddress":null,"enterpriseAddress":null,"enterprisePhone":null,"enterpriseEmail":null,"managerName":null,"managerPosition":null,"representativePosition":null,"managerIdentityNumber":null,"managerPhone":null,"managerEmail":null,"customerProfiles":[{"productId":1,"productCode":"22000","productType":1,"productTypeName":"Cho vay","productName":"Bất động sản (BĐS)","customerProfileId":3189,"customerProfileCode":"202200000001","process":1,"processString":"Đã nhập thông tin khách hàng","status":1,"statusString":"Mới khởi tạo","transactionDate":null,"appointmentTransactionDate":null,"tradingCode":null,"money":0,"interest":0,"descriptionInterest":"1. Biểu lãi suất cho vay tối thiểu danh cho Khách hàng bán lẻ tại CB.\r\n  a. Lãi suất thông thường:\r\n- Thời hạn 12 tháng trở xuống: 11.3%/năm\r\n- Thời hạn trên 01 năm: 13.3%/năm\r\n  b. Lãi suất ưu đãi:\r\n- Gói ưu đãi số 1: 9.9%/năm đầu, từ năm thứ 2 là 11.7%/năm\r\n- Gói ưu đãi số 2: 10.9%/năm 2 năm đầu, từ năm 3: 11.7%/năm\r\n2. Phí trả nợ trước hạn:\r\n - Trong năm 1 : 3.5%\r\n - Trong năm 2 : 2.5%\r\n - Trong năm 3 : 1.5%\r\n - Từ năm 4 : Miễn phí","bosInputDate":null,"hasCalculationFormula":true,"modifiedDate":"2020-07-20T21:19:53","modifiedBy":"thanhnc1","modifiedFirstName":"","modifiedLastName":"Chưa xác định","enjoyUserId":null,"enjoyUserFullName":null}]}
     * totalRecords : 1
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
         * customerId : 1813
         * customerType : 1
         * firstName :
         * lastName : djs
         * customerCode : CUSTOMER_CODE
         * dateOfBirth : 2020-04-17T00:00:00
         * gender : 2
         * identityNumber : 8686
         * issuedDate : 2020-07-20T00:00:00
         * issuedBy : djsjs
         * phoneNumber : 09466565656
         * address : sjs
         * email : djs@gmail.com
         * educationStatus : 10
         * assetStatus : 118
         * maritalStatus : 15
         * cicResult : 2
         * enterpriseType : null
         * enterpriseName : null
         * enterpriseLicenseNumber : null
         * enterpriseLicenseIssuedDate : null
         * enterpriseLicenseIssuedBy : null
         * enterpriseLicenseAddress : null
         * enterpriseAddress : null
         * enterprisePhone : null
         * enterpriseEmail : null
         * managerName : null
         * managerPosition : null
         * representativePosition : null
         * managerIdentityNumber : null
         * managerPhone : null
         * managerEmail : null
         * customerProfiles : [{"productId":1,"productCode":"22000","productType":1,"productTypeName":"Cho vay","productName":"Bất động sản (BĐS)","customerProfileId":3189,"customerProfileCode":"202200000001","process":1,"processString":"Đã nhập thông tin khách hàng","status":1,"statusString":"Mới khởi tạo","transactionDate":null,"appointmentTransactionDate":null,"tradingCode":null,"money":0,"interest":0,"descriptionInterest":"1. Biểu lãi suất cho vay tối thiểu danh cho Khách hàng bán lẻ tại CB.\r\n  a. Lãi suất thông thường:\r\n- Thời hạn 12 tháng trở xuống: 11.3%/năm\r\n- Thời hạn trên 01 năm: 13.3%/năm\r\n  b. Lãi suất ưu đãi:\r\n- Gói ưu đãi số 1: 9.9%/năm đầu, từ năm thứ 2 là 11.7%/năm\r\n- Gói ưu đãi số 2: 10.9%/năm 2 năm đầu, từ năm 3: 11.7%/năm\r\n2. Phí trả nợ trước hạn:\r\n - Trong năm 1 : 3.5%\r\n - Trong năm 2 : 2.5%\r\n - Trong năm 3 : 1.5%\r\n - Từ năm 4 : Miễn phí","bosInputDate":null,"hasCalculationFormula":true,"modifiedDate":"2020-07-20T21:19:53","modifiedBy":"thanhnc1","modifiedFirstName":"","modifiedLastName":"Chưa xác định","enjoyUserId":null,"enjoyUserFullName":null}]
         */

        private int customerId;
        private int customerType;
        private String firstName;
        private String lastName;
        private String customerCode;
        private String dateOfBirth;
        private int gender;
        private String identityNumber;
        private String issuedDate;
        private String issuedBy;
        private String phoneNumber;
        private String address;
        private String email;
        private int educationStatus;
        private int assetStatus;
        private int maritalStatus;
        private int cicResult;
        private int enterpriseType;
        private String enterpriseName;
        private String enterpriseLicenseNumber;
        private String enterpriseLicenseIssuedDate;
        private String enterpriseLicenseIssuedBy;
        private String enterpriseLicenseAddress;
        private String enterpriseAddress;
        private String enterprisePhone;
        private String enterpriseEmail;
        private String managerName;
        private String managerPosition;
        private String representativePosition;
        private String managerIdentityNumber;
        private String managerPhone;
        private String managerEmail;
        private List<CustomerProfilesBean> customerProfiles;

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getIssuedDate() {
            return issuedDate;
        }

        public void setIssuedDate(String issuedDate) {
            this.issuedDate = issuedDate;
        }

        public String getIssuedBy() {
            return issuedBy;
        }

        public void setIssuedBy(String issuedBy) {
            this.issuedBy = issuedBy;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getEducationStatus() {
            return educationStatus;
        }

        public void setEducationStatus(int educationStatus) {
            this.educationStatus = educationStatus;
        }

        public int getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(int assetStatus) {
            this.assetStatus = assetStatus;
        }

        public int getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(int maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public int getCicResult() {
            return cicResult;
        }

        public void setCicResult(int cicResult) {
            this.cicResult = cicResult;
        }

        public int getEnterpriseType() {
            return enterpriseType;
        }

        public void setEnterpriseType(int enterpriseType) {
            this.enterpriseType = enterpriseType;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getEnterpriseLicenseNumber() {
            return enterpriseLicenseNumber;
        }

        public void setEnterpriseLicenseNumber(String enterpriseLicenseNumber) {
            this.enterpriseLicenseNumber = enterpriseLicenseNumber;
        }

        public String getEnterpriseLicenseIssuedDate() {
            return enterpriseLicenseIssuedDate;
        }

        public void setEnterpriseLicenseIssuedDate(String enterpriseLicenseIssuedDate) {
            this.enterpriseLicenseIssuedDate = enterpriseLicenseIssuedDate;
        }

        public String getEnterpriseLicenseIssuedBy() {
            return enterpriseLicenseIssuedBy;
        }

        public void setEnterpriseLicenseIssuedBy(String enterpriseLicenseIssuedBy) {
            this.enterpriseLicenseIssuedBy = enterpriseLicenseIssuedBy;
        }

        public String getEnterpriseLicenseAddress() {
            return enterpriseLicenseAddress;
        }

        public void setEnterpriseLicenseAddress(String enterpriseLicenseAddress) {
            this.enterpriseLicenseAddress = enterpriseLicenseAddress;
        }

        public String getEnterpriseAddress() {
            return enterpriseAddress;
        }

        public void setEnterpriseAddress(String enterpriseAddress) {
            this.enterpriseAddress = enterpriseAddress;
        }

        public String getEnterprisePhone() {
            return enterprisePhone;
        }

        public void setEnterprisePhone(String enterprisePhone) {
            this.enterprisePhone = enterprisePhone;
        }

        public String getEnterpriseEmail() {
            return enterpriseEmail;
        }

        public void setEnterpriseEmail(String enterpriseEmail) {
            this.enterpriseEmail = enterpriseEmail;
        }

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public String getManagerPosition() {
            return managerPosition;
        }

        public void setManagerPosition(String managerPosition) {
            this.managerPosition = managerPosition;
        }

        public String getRepresentativePosition() {
            return representativePosition;
        }

        public void setRepresentativePosition(String representativePosition) {
            this.representativePosition = representativePosition;
        }

        public String getManagerIdentityNumber() {
            return managerIdentityNumber;
        }

        public void setManagerIdentityNumber(String managerIdentityNumber) {
            this.managerIdentityNumber = managerIdentityNumber;
        }

        public String getManagerPhone() {
            return managerPhone;
        }

        public void setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
        }

        public String getManagerEmail() {
            return managerEmail;
        }

        public void setManagerEmail(String managerEmail) {
            this.managerEmail = managerEmail;
        }

        public List<CustomerProfilesBean> getCustomerProfiles() {
            return customerProfiles;
        }

        public void setCustomerProfiles(List<CustomerProfilesBean> customerProfiles) {
            this.customerProfiles = customerProfiles;
        }

        public static class CustomerProfilesBean {
            /**
             * productId : 1
             * productCode : 22000
             * productType : 1
             * productTypeName : Cho vay
             * productName : Bất động sản (BĐS)
             * customerProfileId : 3189
             * customerProfileCode : 202200000001
             * process : 1
             * processString : Đã nhập thông tin khách hàng
             * status : 1
             * statusString : Mới khởi tạo
             * transactionDate : null
             * appointmentTransactionDate : null
             * tradingCode : null
             * money : 0.0
             * interest : 0.0
             * descriptionInterest : 1. Biểu lãi suất cho vay tối thiểu danh cho Khách hàng bán lẻ tại CB.
             a. Lãi suất thông thường:
             - Thời hạn 12 tháng trở xuống: 11.3%/năm
             - Thời hạn trên 01 năm: 13.3%/năm
             b. Lãi suất ưu đãi:
             - Gói ưu đãi số 1: 9.9%/năm đầu, từ năm thứ 2 là 11.7%/năm
             - Gói ưu đãi số 2: 10.9%/năm 2 năm đầu, từ năm 3: 11.7%/năm
             2. Phí trả nợ trước hạn:
             - Trong năm 1 : 3.5%
             - Trong năm 2 : 2.5%
             - Trong năm 3 : 1.5%
             - Từ năm 4 : Miễn phí
             * bosInputDate : null
             * hasCalculationFormula : true
             * modifiedDate : 2020-07-20T21:19:53
             * modifiedBy : thanhnc1
             * modifiedFirstName :
             * modifiedLastName : Chưa xác định
             * enjoyUserId : null
             * enjoyUserFullName : null
             */

            private int productId;
            private String productCode;
            private int productType;
            private String productTypeName;
            private String productName;
            private int customerProfileId;
            private String customerProfileCode;
            private int process;
            private String processString;
            private int status;
            private String statusString;
            private Object transactionDate;
            private Object appointmentTransactionDate;
            private Object tradingCode;
            private double money;
            private double interest;
            private String descriptionInterest;
            private Object bosInputDate;
            private boolean hasCalculationFormula;
            private String modifiedDate;
            private String modifiedBy;
            private String modifiedFirstName;
            private String modifiedLastName;
            private Object enjoyUserId;
            private Object enjoyUserFullName;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public int getProductType() {
                return productType;
            }

            public void setProductType(int productType) {
                this.productType = productType;
            }

            public String getProductTypeName() {
                return productTypeName;
            }

            public void setProductTypeName(String productTypeName) {
                this.productTypeName = productTypeName;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
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

            public int getProcess() {
                return process;
            }

            public void setProcess(int process) {
                this.process = process;
            }

            public String getProcessString() {
                return processString;
            }

            public void setProcessString(String processString) {
                this.processString = processString;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStatusString() {
                return statusString;
            }

            public void setStatusString(String statusString) {
                this.statusString = statusString;
            }

            public Object getTransactionDate() {
                return transactionDate;
            }

            public void setTransactionDate(Object transactionDate) {
                this.transactionDate = transactionDate;
            }

            public Object getAppointmentTransactionDate() {
                return appointmentTransactionDate;
            }

            public void setAppointmentTransactionDate(Object appointmentTransactionDate) {
                this.appointmentTransactionDate = appointmentTransactionDate;
            }

            public Object getTradingCode() {
                return tradingCode;
            }

            public void setTradingCode(Object tradingCode) {
                this.tradingCode = tradingCode;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getInterest() {
                return interest;
            }

            public void setInterest(double interest) {
                this.interest = interest;
            }

            public String getDescriptionInterest() {
                return descriptionInterest;
            }

            public void setDescriptionInterest(String descriptionInterest) {
                this.descriptionInterest = descriptionInterest;
            }

            public Object getBosInputDate() {
                return bosInputDate;
            }

            public void setBosInputDate(Object bosInputDate) {
                this.bosInputDate = bosInputDate;
            }

            public boolean isHasCalculationFormula() {
                return hasCalculationFormula;
            }

            public void setHasCalculationFormula(boolean hasCalculationFormula) {
                this.hasCalculationFormula = hasCalculationFormula;
            }

            public String getModifiedDate() {
                return modifiedDate;
            }

            public void setModifiedDate(String modifiedDate) {
                this.modifiedDate = modifiedDate;
            }

            public String getModifiedBy() {
                return modifiedBy;
            }

            public void setModifiedBy(String modifiedBy) {
                this.modifiedBy = modifiedBy;
            }

            public String getModifiedFirstName() {
                return modifiedFirstName;
            }

            public void setModifiedFirstName(String modifiedFirstName) {
                this.modifiedFirstName = modifiedFirstName;
            }

            public String getModifiedLastName() {
                return modifiedLastName;
            }

            public void setModifiedLastName(String modifiedLastName) {
                this.modifiedLastName = modifiedLastName;
            }

            public Object getEnjoyUserId() {
                return enjoyUserId;
            }

            public void setEnjoyUserId(Object enjoyUserId) {
                this.enjoyUserId = enjoyUserId;
            }

            public Object getEnjoyUserFullName() {
                return enjoyUserFullName;
            }

            public void setEnjoyUserFullName(Object enjoyUserFullName) {
                this.enjoyUserFullName = enjoyUserFullName;
            }
        }
    }
}
