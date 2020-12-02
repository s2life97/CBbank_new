package com.saleskit.cbbank.features.customer.add_new_customer.model;

public class CustomerPostProfile {

    /**
     * Customer : {"CustomerId":1,"CustomerType":0,"FirstName":"string","LastName":"string","CustomerCode":"string","DateOfBirth":"2019-10-14T07:26:23.327Z","Gender":0,"IdentityNumber":"56456456","IssuedDate":"2019-10-14T07:26:23.327Z","IssuedBy":"string","Address":"string","PhoneNumber":"string","Email":"string","EducationStatus":0,"AssetStatus":0,"MaritalStatus":0,"CICResult":1}
     * CustomerProfileCredit : {"CustomerProfileId":0,"ProductId":1,"CustomerId":0}
     */

    private CustomerBean Customer;
    private CustomerProfileCreditBean CustomerProfileCredit;

    public CustomerPostProfile(CustomerBean customer, CustomerProfileCreditBean customerProfileCredit) {
        Customer = customer;
        CustomerProfileCredit = customerProfileCredit;
    }

    public CustomerBean getCustomer() {
        return Customer;
    }

    public void setCustomer(CustomerBean Customer) {
        this.Customer = Customer;
    }

    public CustomerProfileCreditBean getCustomerProfileCredit() {
        return CustomerProfileCredit;
    }

    public void setCustomerProfileCredit(CustomerProfileCreditBean CustomerProfileCredit) {
        this.CustomerProfileCredit = CustomerProfileCredit;
    }

    public static class CustomerBean {
        /**
         * CustomerId : 1
         * CustomerType : 0
         * FirstName : string
         * LastName : string
         * CustomerCode : string
         * DateOfBirth : 2019-10-14T07:26:23.327Z
         * Gender : 0
         * IdentityNumber : 56456456
         * IssuedDate : 2019-10-14T07:26:23.327Z
         * IssuedBy : string
         * Address : string
         * PhoneNumber : string
         * Email : string
         * EducationStatus : 0
         * AssetStatus : 0
         * MaritalStatus : 0
         * CICResult : 1
         */

        private int CustomerId;
        private String CustomerType;
        private String FirstName;
        private String LastName;
        private String CustomerCode;
        private String DateOfBirth;
        private String Gender;
        private String IdentityNumber;
        private String IssuedDate;
        private String IssuedBy;
        private String Address;
        private String PhoneNumber;
        private String Email;
        private String EducationStatus;
        private String AssetStatus;
        private String MaritalStatus;
        private String CICResult;

        public CustomerBean(int customerId, String customerType, String firstName, String lastName, String customerCode, String dateOfBirth, String gender, String identityNumber, String issuedDate, String issuedBy, String address, String phoneNumber, String email, String educationStatus, String assetStatus, String maritalStatus, String CICResult) {
            CustomerId = customerId;
            CustomerType = customerType;
            FirstName = firstName;
            LastName = lastName;
            CustomerCode = customerCode;
            DateOfBirth = dateOfBirth;
            Gender = gender;
            IdentityNumber = identityNumber;
            IssuedDate = issuedDate;
            IssuedBy = issuedBy;
            Address = address;
            PhoneNumber = phoneNumber;
            Email = email;
            EducationStatus = educationStatus;
            AssetStatus = assetStatus;
            MaritalStatus = maritalStatus;
            this.CICResult = CICResult;
        }

        public int getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(int CustomerId) {
            this.CustomerId = CustomerId;
        }

        public String getCustomerType() {
            return CustomerType;
        }

        public void setCustomerType(String CustomerType) {
            this.CustomerType = CustomerType;
        }

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

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

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

        public String getEducationStatus() {
            return EducationStatus;
        }

        public void setEducationStatus(String EducationStatus) {
            this.EducationStatus = EducationStatus;
        }

        public String getAssetStatus() {
            return AssetStatus;
        }

        public void setAssetStatus(String AssetStatus) {
            this.AssetStatus = AssetStatus;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String MaritalStatus) {
            this.MaritalStatus = MaritalStatus;
        }

        public String getCICResult() {
            return CICResult;
        }

        public void setCICResult(String CICResult) {
            this.CICResult = CICResult;
        }
    }

    public static class CustomerProfileCreditBean {
        /**
         * CustomerProfileId : 0
         * ProductId : 1
         * CustomerId : 0
         */

        private int CustomerProfileId;
        private int ProductId;
        private int CustomerId;

        public CustomerProfileCreditBean(int customerProfileId, int productId, int customerId) {
            CustomerProfileId = customerProfileId;
            ProductId = productId;
            CustomerId = customerId;
        }

        public int getCustomerProfileId() {
            return CustomerProfileId;
        }

        public void setCustomerProfileId(int CustomerProfileId) {
            this.CustomerProfileId = CustomerProfileId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public int getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(int CustomerId) {
            this.CustomerId = CustomerId;
        }
    }
}
