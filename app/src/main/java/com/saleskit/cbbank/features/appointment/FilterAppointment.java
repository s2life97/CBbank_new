package com.saleskit.cbbank.features.appointment;

import java.util.List;

public class FilterAppointment {


    /**
     * data : {"appointmentBelongPeriods":[],"appointmentBeforePeriod":{"appointmentId":316,"customerId":null,"customerName":"kimtt 1","identityNumber":"128852555","phoneNumber":"0652655589","email":"h@buca.vn","appointmentAddress":"hn","expectedProduct":null,"description":null,"appointmentTime":"2019-12-03T00:00:00","resultStatus":2,"resultStatusString":"Đồng ý gặp","resultDescription":"Chưa có","nextAppointmentId":0},"appointmentAfterPeriod":{"appointmentId":338,"customerId":null,"customerName":"bebe","identityNumber":"1858508828","phoneNumber":"0952285552","email":"h@ct.vn","appointmentAddress":"hn","expectedProduct":"Chưa có","description":"Chưa có","appointmentTime":"2019-12-05T19:00:13","resultStatus":0,"resultStatusString":"Chưa có kết quả","resultDescription":null,"nextAppointmentId":0}}
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
         * appointmentBelongPeriods : []
         * appointmentBeforePeriod : {"appointmentId":316,"customerId":null,"customerName":"kimtt 1","identityNumber":"128852555","phoneNumber":"0652655589","email":"h@buca.vn","appointmentAddress":"hn","expectedProduct":null,"description":null,"appointmentTime":"2019-12-03T00:00:00","resultStatus":2,"resultStatusString":"Đồng ý gặp","resultDescription":"Chưa có","nextAppointmentId":0}
         * appointmentAfterPeriod : {"appointmentId":338,"customerId":null,"customerName":"bebe","identityNumber":"1858508828","phoneNumber":"0952285552","email":"h@ct.vn","appointmentAddress":"hn","expectedProduct":"Chưa có","description":"Chưa có","appointmentTime":"2019-12-05T19:00:13","resultStatus":0,"resultStatusString":"Chưa có kết quả","resultDescription":null,"nextAppointmentId":0}
         */

        private AppointmentBeforePeriodBean appointmentBeforePeriod;
        private AppointmentAfterPeriodBean appointmentAfterPeriod;
        private List<AppointmentBelongPeriodsBean> appointmentBelongPeriods;

        public AppointmentBeforePeriodBean getAppointmentBeforePeriod() {
            return appointmentBeforePeriod;
        }

        public void setAppointmentBeforePeriod(AppointmentBeforePeriodBean appointmentBeforePeriod) {
            this.appointmentBeforePeriod = appointmentBeforePeriod;
        }

        public AppointmentAfterPeriodBean getAppointmentAfterPeriod() {
            return appointmentAfterPeriod;
        }

        public void setAppointmentAfterPeriod(AppointmentAfterPeriodBean appointmentAfterPeriod) {
            this.appointmentAfterPeriod = appointmentAfterPeriod;
        }

        public List<AppointmentBelongPeriodsBean> getAppointmentBelongPeriods() {
            return appointmentBelongPeriods;
        }


        public static class AppointmentBeforePeriodBean {
            /**
             * appointmentId : 316
             * customerId : null
             * customerName : kimtt 1
             * identityNumber : 128852555
             * phoneNumber : 0652655589
             * email : h@buca.vn
             * appointmentAddress : hn
             * expectedProduct : null
             * description : null
             * appointmentTime : 2019-12-03T00:00:00
             * resultStatus : 2
             * resultStatusString : Đồng ý gặp
             * resultDescription : Chưa có
             * nextAppointmentId : 0
             */

            private int appointmentId;
            private String customerId;
            private String customerName;
            private String identityNumber;
            private String phoneNumber;
            private String email;
            private String appointmentAddress;
            private String expectedProduct;
            private String description;
            private String appointmentTime;
            private int resultStatus;
            private String resultStatusString;
            private String resultDescription;
            private int nextAppointmentId;

            public int getAppointmentId() {
                return appointmentId;
            }

            public void setAppointmentId(int appointmentId) {
                this.appointmentId = appointmentId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getIdentityNumber() {
                return identityNumber;
            }

            public void setIdentityNumber(String identityNumber) {
                this.identityNumber = identityNumber;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getAppointmentAddress() {
                return appointmentAddress;
            }

            public void setAppointmentAddress(String appointmentAddress) {
                this.appointmentAddress = appointmentAddress;
            }

            public Object getExpectedProduct() {
                return expectedProduct;
            }

            public void setExpectedProduct(String expectedProduct) {
                this.expectedProduct = expectedProduct;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAppointmentTime() {
                return appointmentTime;
            }

            public void setAppointmentTime(String appointmentTime) {
                this.appointmentTime = appointmentTime;
            }

            public int getResultStatus() {
                return resultStatus;
            }

            public void setResultStatus(int resultStatus) {
                this.resultStatus = resultStatus;
            }

            public String getResultStatusString() {
                return resultStatusString;
            }

            public void setResultStatusString(String resultStatusString) {
                this.resultStatusString = resultStatusString;
            }

            public String getResultDescription() {
                return resultDescription;
            }

            public void setResultDescription(String resultDescription) {
                this.resultDescription = resultDescription;
            }

            public int getNextAppointmentId() {
                return nextAppointmentId;
            }

            public void setNextAppointmentId(int nextAppointmentId) {
                this.nextAppointmentId = nextAppointmentId;
            }
        }

        public static class AppointmentAfterPeriodBean {
            /**
             * appointmentId : 338
             * customerId : null
             * customerName : bebe
             * identityNumber : 1858508828
             * phoneNumber : 0952285552
             * email : h@ct.vn
             * appointmentAddress : hn
             * expectedProduct : Chưa có
             * description : Chưa có
             * appointmentTime : 2019-12-05T19:00:13
             * resultStatus : 0
             * resultStatusString : Chưa có kết quả
             * resultDescription : null
             * nextAppointmentId : 0
             */

            private int appointmentId;
            private Object customerId;
            private String customerName;
            private String identityNumber;
            private String phoneNumber;
            private String email;
            private String appointmentAddress;
            private String expectedProduct;
            private String description;
            private String appointmentTime;
            private int resultStatus;
            private String resultStatusString;
            private Object resultDescription;
            private int nextAppointmentId;

            public int getAppointmentId() {
                return appointmentId;
            }

            public void setAppointmentId(int appointmentId) {
                this.appointmentId = appointmentId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getIdentityNumber() {
                return identityNumber;
            }

            public void setIdentityNumber(String identityNumber) {
                this.identityNumber = identityNumber;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getAppointmentAddress() {
                return appointmentAddress;
            }

            public void setAppointmentAddress(String appointmentAddress) {
                this.appointmentAddress = appointmentAddress;
            }

            public String getExpectedProduct() {
                return expectedProduct;
            }

            public void setExpectedProduct(String expectedProduct) {
                this.expectedProduct = expectedProduct;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAppointmentTime() {
                return appointmentTime;
            }

            public void setAppointmentTime(String appointmentTime) {
                this.appointmentTime = appointmentTime;
            }

            public int getResultStatus() {
                return resultStatus;
            }

            public void setResultStatus(int resultStatus) {
                this.resultStatus = resultStatus;
            }

            public String getResultStatusString() {
                return resultStatusString;
            }

            public void setResultStatusString(String resultStatusString) {
                this.resultStatusString = resultStatusString;
            }

            public Object getResultDescription() {
                return resultDescription;
            }

            public void setResultDescription(Object resultDescription) {
                this.resultDescription = resultDescription;
            }

            public int getNextAppointmentId() {
                return nextAppointmentId;
            }

            public void setNextAppointmentId(int nextAppointmentId) {
                this.nextAppointmentId = nextAppointmentId;
            }
        }

        public static class AppointmentBelongPeriodsBean {
            /**
             * appointmentId : 306
             * customerId : null
             * customerName : Khach cua KimTT
             * identityNumber : 456564562
             * phoneNumber : 04953045934
             * email : ads@gmail.com
             * appointmentAddress : ádasd
             * expectedProduct : ads
             * description : ádas
             * appointmentTime : 2019-11-26T04:08:44
             * resultStatus : 0
             * resultStatusString : Chưa có kết quả
             * resultDescription : string
             * nextAppointmentId : 0
             */

            private int appointmentId;
            private String customerId;
            private String customerName;
            private String identityNumber;
            private String phoneNumber;
            private String email;
            private String appointmentAddress;
            private String expectedProduct;
            private String description;
            private String appointmentTime;
            private int resultStatus;
            private String resultStatusString;
            private String resultDescription;
            private int nextAppointmentId;

            public int getAppointmentId() {
                return appointmentId;
            }

            public void setAppointmentId(int appointmentId) {
                this.appointmentId = appointmentId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getIdentityNumber() {
                return identityNumber;
            }

            public void setIdentityNumber(String identityNumber) {
                this.identityNumber = identityNumber;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getAppointmentAddress() {
                return appointmentAddress;
            }

            public void setAppointmentAddress(String appointmentAddress) {
                this.appointmentAddress = appointmentAddress;
            }

            public String getExpectedProduct() {
                return expectedProduct;
            }

            public void setExpectedProduct(String expectedProduct) {
                this.expectedProduct = expectedProduct;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAppointmentTime() {
                return appointmentTime;
            }

            public void setAppointmentTime(String appointmentTime) {
                this.appointmentTime = appointmentTime;
            }

            public int getResultStatus() {
                return resultStatus;
            }

            public void setResultStatus(int resultStatus) {
                this.resultStatus = resultStatus;
            }

            public String getResultStatusString() {
                return resultStatusString;
            }

            public void setResultStatusString(String resultStatusString) {
                this.resultStatusString = resultStatusString;
            }

            public String getResultDescription() {
                return resultDescription;
            }

            public void setResultDescription(String resultDescription) {
                this.resultDescription = resultDescription;
            }

            public int getNextAppointmentId() {
                return nextAppointmentId;
            }

            public void setNextAppointmentId(int nextAppointmentId) {
                this.nextAppointmentId = nextAppointmentId;
            }
        }
    }
}
