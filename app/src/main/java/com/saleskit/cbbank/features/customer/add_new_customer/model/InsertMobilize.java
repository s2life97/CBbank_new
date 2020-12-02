package com.saleskit.cbbank.features.customer.add_new_customer.model;

public class InsertMobilize {


    /**
     * Customer : {"CustomerId":0,"CustomerType":0,"FirstName":"string","LastName":"string","CustomerCode":"string","DateOfBirth":"2019-10-15T03:36:35.797Z","Gender":0,"IdentityNumber":"string","IssuedDate":"2019-10-15T03:36:35.797Z","IssuedBy":"string","Address":"string","PhoneNumber":"string","Email":"string","EducationStatus":0,"AssetStatus":0,"MaritalStatus":0,"CICResult":0}
     * CustomerProfileMobilize : {"CustomerProfileId":0,"CustomerProfileCode":"string","ProductId":0,"CustomerId":0,"Money":0,"PeriodTime":0,"Interest":0,"AppointmentTransactionDate":"2019-10-15T03:36:35.797Z","DepartmentId":0}
     */

    private CustomerBean Customer;
    private CustomerProfileMobilizeBean CustomerProfileMobilize;

    public InsertMobilize(CustomerBean customerBean, CustomerProfileMobilizeBean customerProfileMobilizeBean) {
        Customer = customerBean;
        CustomerProfileMobilize = customerProfileMobilizeBean;
    }

    public CustomerBean getCustomer() {
        return Customer;
    }

    public void setCustomer(CustomerBean Customer) {
        this.Customer = Customer;
    }

    public CustomerProfileMobilizeBean getCustomerProfileMobilize() {
        return CustomerProfileMobilize;
    }

    public void setCustomerProfileMobilize(CustomerProfileMobilizeBean CustomerProfileMobilize) {
        this.CustomerProfileMobilize = CustomerProfileMobilize;
    }

    public static class CustomerBean {
        /**
         * CustomerId : 0
         * CustomerType : 0
         * FirstName : string
         * LastName : string
         * CustomerCode : string
         * DateOfBirth : 2019-10-15T03:36:35.797Z
         * Gender : 0
         * IdentityNumber : string
         * IssuedDate : 2019-10-15T03:36:35.797Z
         * IssuedBy : string
         * Address : string
         * PhoneNumber : string
         * Email : string
         * EducationStatus : 0
         * AssetStatus : 0
         * MaritalStatus : 0
         * CICResult : 0
         */

        private String CustomerId;
        private String CustomerType;
        private String FirstName;
        private String LastName;
        private String CustomerCode;
        private String DateOfBirth;
        private String IdentityNumber;
        private String IssuedDate;
        private String IssuedBy;
        private String Address;
        private String PhoneNumber;
        private String Email;
        public CustomerBean(String customerId, String customerType,
                            String firstName, String lastName, String customerCode,
                            String dateOfBirth, String identityNumber, String issuedDate, String issuedBy,
                            String address, String phoneNumber,
                            String email) {
            CustomerId = customerId;
            CustomerType = customerType;
            FirstName = firstName;
            LastName = lastName;
            CustomerCode = customerCode;
            DateOfBirth = dateOfBirth;
            IdentityNumber = identityNumber;
            IssuedDate = issuedDate;
            IssuedBy = issuedBy;
            this.Address = address;
            PhoneNumber = phoneNumber;
            this.Email = email;
        }

        public String getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(String CustomerId) {
            this.CustomerId = CustomerId;
        }

//        public int getCustomerType() {
//            return CustomerType;
//        }
//
//        public void setCustomerType(int CustomerType) {
//            this.CustomerType = CustomerType;
//        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        public String getCustomerCode() {
            return CustomerCode;
        }

        public void setCustomerCode(String CustomerCode) {
            this.CustomerCode = CustomerCode;
        }

        public String getDateOfBirth() {
            return DateOfBirth;
        }

        public void setDateOfBirth(String DateOfBirth) {
            this.DateOfBirth = DateOfBirth;
        }

//        public int getGender() {
//            return Gender;
//        }
//
//        public void setGender(int Gender) {
//            this.Gender = Gender;
//        }

        public String getIdentityNumber() {
            return IdentityNumber;
        }

        public void setIdentityNumber(String IdentityNumber) {
            this.IdentityNumber = IdentityNumber;
        }

        public String getIssuedDate() {
            return IssuedDate;
        }

        public void setIssuedDate(String IssuedDate) {
            this.IssuedDate = IssuedDate;
        }

        public String getIssuedBy() {
            return IssuedBy;
        }

        public void setIssuedBy(String IssuedBy) {
            this.IssuedBy = IssuedBy;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

//        public int getEducationStatus() {
//            return EducationStatus;
//        }
//
//        public void setEducationStatus(int EducationStatus) {
//            this.EducationStatus = EducationStatus;
//        }
//
//        public int getAssetStatus() {
//            return AssetStatus;
//        }
//
//        public void setAssetStatus(int AssetStatus) {
//            this.AssetStatus = AssetStatus;
//        }
//
//        public int getMaritalStatus() {
//            return MaritalStatus;
//        }
//
//        public void setMaritalStatus(int MaritalStatus) {
//            this.MaritalStatus = MaritalStatus;
//        }
//
//        public int getCICResult() {
//            return CICResult;
//        }
//
//        public void setCICResult(int CICResult) {
//            this.CICResult = CICResult;
//        }
    }

    public static class CustomerProfileMobilizeBean {
        /**
         * CustomerProfileId : 0
         * CustomerProfileCode : string
         * ProductId : 0
         * CustomerId : 0
         * Money : 0
         * PeriodTime : 0
         * Interest : 0
         * AppointmentTransactionDate : 2019-10-15T03:36:35.797Z
         * DepartmentId : 0
         */

        private String CustomerProfileId;
        private String CustomerProfileCode;
        private int ProductId;
        private String CustomerId;
        private String Money;
        private String PeriodTime;
        private String Interest;
        private String AppointmentTransactionDate;
        private String DepartmentId;
        private String BranchId;

        public CustomerProfileMobilizeBean(String customerProfileId, String customerProfileCode,
                                           int productId, String customerId, String money,
                                           String periodTime, String interest,
                                           String appointmentTransactionDate, String departmentId, String branchId) {
            CustomerProfileId = customerProfileId;
            CustomerProfileCode = customerProfileCode;
            ProductId = productId;
            CustomerId = customerId;
            Money = money;
            PeriodTime = periodTime;
            Interest = interest;
            AppointmentTransactionDate = appointmentTransactionDate;
            DepartmentId = departmentId;
            BranchId = branchId;
        }

        public String getCustomerProfileId() {
            return CustomerProfileId;
        }

        public void setCustomerProfileId(String CustomerProfileId) {
            this.CustomerProfileId = CustomerProfileId;
        }

        public String getCustomerProfileCode() {
            return CustomerProfileCode;
        }

        public void setCustomerProfileCode(String CustomerProfileCode) {
            this.CustomerProfileCode = CustomerProfileCode;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(String CustomerId) {
            this.CustomerId = CustomerId;
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

        public String getInterest() {
            return Interest;
        }

        public void setInterest(String Interest) {
            this.Interest = Interest;
        }

        public String getAppointmentTransactionDate() {
            return AppointmentTransactionDate;
        }

        public void setAppointmentTransactionDate(String AppointmentTransactionDate) {
            this.AppointmentTransactionDate = AppointmentTransactionDate;
        }

        public String getDepartmentId() {
            return DepartmentId;
        }

        public void setDepartmentId(String DepartmentId) {
            this.DepartmentId = DepartmentId;
        }
    }
}
