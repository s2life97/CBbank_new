package com.saleskit.cbbank.features.customer.add_new_customer.model;

public class CustomerEnterpriseRespond {

    /**
     * Customer : {"CustomerId":0,"CustomerType":0,"FirstName":"string","LastName":"string","CustomerCode":"string","DateOfBirth":"2020-07-20T01:51:20.256Z","Gender":0,"IdentityNumber":"string","IssuedDate":"2020-07-20T01:51:20.256Z","IssuedBy":"string","Address":"string","PhoneNumber":"string","Email":"string","EducationStatus":0,"AssetStatus":0,"MaritalStatus":0,"CICResult":0,"EnterpriseType":0,"EnterpriseName":"string","EnterpriseLicenseNumber":"string","EnterpriseLicenseIssuedDate":"2020-07-20T01:51:20.256Z","EnterpriseLicenseIssuedBy":"string","EnterpriseLicenseAddress":"string","EnterpriseAddress":"string","EnterprisePhone":"string","EnterpriseEmail":"string","ManagerName":"string","ManagerPosition":"string","RepresentativePosition":"string","ManagerIdentityNumber":"string","ManagerPhone":"string","ManagerEmail":"string"}
     * CustomerProfileCredit : {"CustomerProfileId":0,"ProductId":0,"CustomerId":0}
     */

    private CustomerBean Customer;
    private CustomerProfileCreditBean CustomerProfileCredit;

    public CustomerEnterpriseRespond(CustomerBean customer, CustomerProfileCreditBean customerProfileCredit) {
        Customer = customer;
        CustomerProfileCredit = customerProfileCredit;
    }

    public CustomerEnterpriseRespond(CustomerBean customer, CustomerPostProfile.CustomerProfileCreditBean customerProfileCreditBean) {

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
         * CustomerId : 0
         * CustomerType : 0
         * FirstName : string
         * LastName : string
         * CustomerCode : string
         * IdentityNumber : string
         * IssuedDate : 2020-07-20T01:51:20.256Z
         * IssuedBy : string
         * Address : string
         * PhoneNumber : string
         * Email : string
         * EnterpriseType : 0
         * EnterpriseName : string
         * EnterpriseLicenseNumber : string
         * EnterpriseLicenseAddress : string
         * EnterpriseAddress : string
         * EnterprisePhone : string
         * EnterpriseEmail : string
         * ManagerName : string
         * ManagerPosition : string
         * RepresentativePosition : string
         * ManagerIdentityNumber : string
         * ManagerPhone : string
         * ManagerEmail : string
         */

        private String CustomerId;
        private int CustomerType;
        private String FirstName;
        private String LastName;
        private String CustomerCode;
        private String IdentityNumber;
        private String Address;
        private String PhoneNumber;
        private String Email;
        private int EnterpriseType;
        private String EnterpriseName;
        private String EnterpriseLicenseNumber;
        private String EnterpriseLicenseIssuedDate;
        private String EnterpriseLicenseIssuedBy;
        private String EnterpriseLicenseAddress;
        private String EnterpriseAddress;
        private String EnterprisePhone;
        private String EnterpriseEmail;
        private String ManagerName;
        private String ManagerPosition;
        private String RepresentativePosition;
        private String ManagerIdentityNumber;
        private String ManagerPhone;
        private String ManagerEmail;
        public CustomerBean( int customerType, String firstName,
                            String lastName, String customerCode, String identityNumber,
                            String address, String phoneNumber,
                            String email, int enterpriseType, String enterpriseName,
                            String enterpriseLicenseNumber, String enterpriseLicenseIssuedDate,
                            String enterpriseLicenseIssuedBy,
                            String enterpriseAddress, String enterprisePhone, String enterpriseEmail,
                            String managerName, String managerPosition, String representativePosition,
                            String managerIdentityNumber, String managerPhone, String managerEmail) {

            CustomerType = customerType;
            FirstName = firstName;
            LastName = lastName;
            CustomerCode = customerCode;
            IdentityNumber = identityNumber;
            EnterpriseAddress = address;
            PhoneNumber = phoneNumber;
            Email = email;
            EnterpriseType = enterpriseType;
            EnterpriseName = enterpriseName;
            EnterpriseLicenseNumber = enterpriseLicenseNumber;
            EnterpriseLicenseIssuedDate = enterpriseLicenseIssuedDate;
            EnterpriseLicenseIssuedBy = enterpriseLicenseIssuedBy;
            EnterpriseLicenseAddress = enterpriseAddress;
            EnterprisePhone = enterprisePhone;
            EnterpriseEmail = enterpriseEmail;
            ManagerName = managerName;
            ManagerPosition = managerPosition;
            RepresentativePosition = representativePosition;
            ManagerIdentityNumber = managerIdentityNumber;
            ManagerPhone = managerPhone;
            ManagerEmail = managerEmail;
        }
        public CustomerBean(String customerId, int customerType, String firstName,
                            String lastName, String customerCode, String identityNumber,
                            String address, String phoneNumber,
                            String email, int enterpriseType, String enterpriseName,
                            String enterpriseLicenseNumber, String enterpriseLicenseIssuedDate,
                            String enterpriseLicenseIssuedBy,
                            String enterpriseAddress, String enterprisePhone, String enterpriseEmail,
                            String managerName, String managerPosition, String representativePosition,
                            String managerIdentityNumber, String managerPhone, String managerEmail) {

            CustomerId = customerId;
            CustomerType = customerType;
            FirstName = firstName;
            LastName = lastName;
            CustomerCode = customerCode;
            IdentityNumber = identityNumber;
            EnterpriseAddress = address;
            PhoneNumber = phoneNumber;
            Email = email;
            EnterpriseType = enterpriseType;
            EnterpriseName = enterpriseName;
            EnterpriseLicenseNumber = enterpriseLicenseNumber;
            EnterpriseLicenseIssuedDate = enterpriseLicenseIssuedDate;
            EnterpriseLicenseIssuedBy = enterpriseLicenseIssuedBy;
            EnterpriseLicenseAddress = enterpriseAddress;
            EnterprisePhone = enterprisePhone;
            EnterpriseEmail = enterpriseEmail;
            ManagerName = managerName;
            ManagerPosition = managerPosition;
            RepresentativePosition = representativePosition;
            ManagerIdentityNumber = managerIdentityNumber;
            ManagerPhone = managerPhone;
            ManagerEmail = managerEmail;
        }

        public int getCustomerType() {
            return CustomerType;
        }

        public void setCustomerType(int customerType) {
            CustomerType = customerType;
        }

        public String getEnterpriseLicenseIssuedDate() {
            return EnterpriseLicenseIssuedDate;
        }

        public void setEnterpriseLicenseIssuedDate(String enterpriseLicenseIssuedDate) {
            EnterpriseLicenseIssuedDate = enterpriseLicenseIssuedDate;
        }

        public String getEnterpriseLicenseIssuedBy() {
            return EnterpriseLicenseIssuedBy;
        }

        public void setEnterpriseLicenseIssuedBy(String enterpriseLicenseIssuedBy) {
            EnterpriseLicenseIssuedBy = enterpriseLicenseIssuedBy;
        }

        public String getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(String CustomerId) {
            this.CustomerId = CustomerId;
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

        public String getIdentityNumber() {
            return IdentityNumber;
        }

        public void setIdentityNumber(String IdentityNumber) {
            this.IdentityNumber = IdentityNumber;
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


        public int getEnterpriseType() {
            return EnterpriseType;
        }

        public void setEnterpriseType(int EnterpriseType) {
            this.EnterpriseType = EnterpriseType;
        }

        public String getEnterpriseName() {
            return EnterpriseName;
        }

        public void setEnterpriseName(String EnterpriseName) {
            this.EnterpriseName = EnterpriseName;
        }

        public String getEnterpriseLicenseNumber() {
            return EnterpriseLicenseNumber;
        }

        public void setEnterpriseLicenseNumber(String EnterpriseLicenseNumber) {
            this.EnterpriseLicenseNumber = EnterpriseLicenseNumber;
        }


        public String getEnterpriseLicenseAddress() {
            return EnterpriseLicenseAddress;
        }

        public void setEnterpriseLicenseAddress(String EnterpriseLicenseAddress) {
            this.EnterpriseLicenseAddress = EnterpriseLicenseAddress;
        }

        public String getEnterpriseAddress() {
            return EnterpriseAddress;
        }

        public void setEnterpriseAddress(String EnterpriseAddress) {
            this.EnterpriseAddress = EnterpriseAddress;
        }

        public String getEnterprisePhone() {
            return EnterprisePhone;
        }

        public void setEnterprisePhone(String EnterprisePhone) {
            this.EnterprisePhone = EnterprisePhone;
        }

        public String getEnterpriseEmail() {
            return EnterpriseEmail;
        }

        public void setEnterpriseEmail(String EnterpriseEmail) {
            this.EnterpriseEmail = EnterpriseEmail;
        }

        public String getManagerName() {
            return ManagerName;
        }

        public void setManagerName(String ManagerName) {
            this.ManagerName = ManagerName;
        }

        public String getManagerPosition() {
            return ManagerPosition;
        }

        public void setManagerPosition(String ManagerPosition) {
            this.ManagerPosition = ManagerPosition;
        }

        public String getRepresentativePosition() {
            return RepresentativePosition;
        }

        public void setRepresentativePosition(String RepresentativePosition) {
            this.RepresentativePosition = RepresentativePosition;
        }

        public String getManagerIdentityNumber() {
            return ManagerIdentityNumber;
        }

        public void setManagerIdentityNumber(String ManagerIdentityNumber) {
            this.ManagerIdentityNumber = ManagerIdentityNumber;
        }

        public String getManagerPhone() {
            return ManagerPhone;
        }

        public void setManagerPhone(String ManagerPhone) {
            this.ManagerPhone = ManagerPhone;
        }

        public String getManagerEmail() {
            return ManagerEmail;
        }

        public void setManagerEmail(String ManagerEmail) {
            this.ManagerEmail = ManagerEmail;
        }
    }

    public static class CustomerProfileCreditBean {
        /**
         * CustomerProfileId : 0
         * ProductId : 0
         * CustomerId : 0
         */

        private int CustomerProfileId;
        private int ProductId;
        private String CustomerId;

        public CustomerProfileCreditBean(int customerProfileId, int productId) {
            CustomerProfileId = customerProfileId;
            ProductId = productId;
        }

        public CustomerProfileCreditBean(int customerProfileId, int productId, String customerId) {
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

        public String getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(String CustomerId) {
            this.CustomerId = CustomerId;
        }
    }
}
