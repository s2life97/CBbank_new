package com.saleskit.cbbank.features.account;

import java.io.Serializable;
import java.util.List;

public class
Appointment implements Serializable {

    /**
     * data : [{"appointmentId":1,"customerName":"Tran Duc Hung","identityNumber":"123456789","phoneNumber":"0976709886","email":"duchung.1510@gmail.com","appointmentAddress":"Số nhà 12, ngõ 3, đường 4, phường 5, quận 6, Hà Nội","expectedProduct":"Huy động vốn 3 triệu đô","description":"Không","appointmentTime":"2019-10-01T00:00:00","resultStatus":1,"resultStatusString":"Hẹn gặp lại","resultDescription":null,"nextAppointmentId":null},{"appointmentId":47,"customerName":"CESC FABREGAS","identityNumber":"string","phoneNumber":"string","email":"string@gmail.com","appointmentAddress":"string","expectedProduct":"string","description":"string","appointmentTime":"2019-09-20T08:30:18","resultStatus":2,"resultStatusString":"Đồng ý gặp","resultDescription":"string","nextAppointmentId":null},{"appointmentId":46,"customerName":"PIRLO","identityNumber":"string","phoneNumber":"string","email":"string@gmail.com","appointmentAddress":"string","expectedProduct":"string","description":"string","appointmentTime":"2019-09-20T07:30:18","resultStatus":2,"resultStatusString":"Đồng ý gặp","resultDescription":"string","nextAppointmentId":null},{"appointmentId":45,"customerName":"HAZARD","identityNumber":"string","phoneNumber":"string","email":"string@gmail.com","appointmentAddress":"string","expectedProduct":"string","description":"string","appointmentTime":"2019-09-20T06:30:18","resultStatus":2,"resultStatusString":"Đồng ý gặp","resultDescription":"string","nextAppointmentId":null},{"appointmentId":43,"customerName":"LIONEL MESSI","identityNumber":"Thay đổi CMTND","phoneNumber":"Thay đổi SĐT","email":"thaydoigmail@gmail.com","appointmentAddress":"thaydoidiadiem","expectedProduct":"thaydoisanphamdukien","description":"thaydoighichu","appointmentTime":"2019-09-20T04:40:18","resultStatus":3,"resultStatusString":"Hủy bỏ","resultDescription":"thaydoighichuketqua","nextAppointmentId":null},{"appointmentId":48,"customerName":"string","identityNumber":"string","phoneNumber":"string","email":"string","appointmentAddress":"string","expectedProduct":"string","description":"string","appointmentTime":"2019-09-19T07:16:47","resultStatus":0,"resultStatusString":"Chưa có kết quả","resultDescription":"string","nextAppointmentId":0}]
     * totalRecords : 6
     */

    private int totalRecords;
    private List<DataBean> data;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        public DataBean(int appointmentId, String customerName, String identityNumber, String phoneNumber, String email, String appointmentAddress, String expectedProduct, String description, String appointmentTime, int resultStatus, String resultStatusString, String resultDescription) {
            this.appointmentId = appointmentId;
            this.customerName = customerName;
            this.identityNumber = identityNumber;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.appointmentAddress = appointmentAddress;
            this.expectedProduct = expectedProduct;
            this.description = description;
            this.appointmentTime = appointmentTime;
            this.resultStatus = resultStatus;
            this.resultStatusString = resultStatusString;
            this.resultDescription = resultDescription;
        }

        /**
         * appointmentId : 1
         * customerName : Tran Duc Hung
         * identityNumber : 123456789
         * phoneNumber : 0976709886
         * email : duchung.1510@gmail.com
         * appointmentAddress : Số nhà 12, ngõ 3, đường 4, phường 5, quận 6, Hà Nội
         * expectedProduct : Huy động vốn 3 triệu đô
         * description : Không
         * appointmentTime : 2019-10-01T00:00:00
         * resultStatus : 1
         * resultStatusString : Hẹn gặp lại
         * resultDescription : null
         * nextAppointmentId : null
         */

        private int appointmentId;
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
        private Object nextAppointmentId;

        public int getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
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

        public Object getNextAppointmentId() {
            return nextAppointmentId;
        }

        public void setNextAppointmentId(Object nextAppointmentId) {
            this.nextAppointmentId = nextAppointmentId;
        }
    }
}
