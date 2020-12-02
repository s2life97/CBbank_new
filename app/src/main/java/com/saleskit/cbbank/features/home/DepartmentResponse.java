package com.saleskit.cbbank.features.home;

import java.util.List;

public class DepartmentResponse {

    /**
     * data : [{"transactionLocationId":2,"parentId":1,"name":"PGD Đội Cấn","address":"200 Đội Cấn, P. Đội Cấn, Ba Đình, Hà Nội","phoneNumber":"Tel: (024) 3787 2368","faxNumber":"Fax: (024) 3787 2399"},{"transactionLocationId":3,"parentId":1,"name":"PGD Đống Đa","address":"49-51 Yên Lãng, Trung Liệt , Đống Đa, Hà Nội","phoneNumber":"Tel: (024) 3537 8724","faxNumber":"Fax: (024) 3537 8725"},{"transactionLocationId":4,"parentId":1,"name":"PGD NGUYỄN TRÃI","address":"32 Đường Thành, Hoàn Kiếm, Hà Nội.","phoneNumber":"Tel: (024) 3392 7918","faxNumber":"Fax: (024) 3392 7919"},{"transactionLocationId":5,"parentId":1,"name":"PGD HÀO NAM","address":"165 Phố Hào Nam, P.Ô Chợ Dừa, Q.Đống Đa, TP.Hà Nội","phoneNumber":"Tel: (024) 3512 3262","faxNumber":"Fax: (024) 3512 3263"},{"transactionLocationId":6,"parentId":1,"name":"PGD Hoài Đức","address":"Km 6 tỉnh lộ 419, thôn Chùa Tổng, La Phù, H.Hoài Đức, TP.Hà Nội","phoneNumber":"Tel: (024) 3365 6816","faxNumber":"Fax: (024) 3365 6826"},{"transactionLocationId":7,"parentId":1,"name":"PGD Thăng Long","address":"64 Duy Tân, P.Dịch Vọng Hậu, Q.Cầu Giấy, TP.Hà Nội.","phoneNumber":"Tel: (024) 3750 2108","faxNumber":"Fax: (024) 3750 2109"},{"transactionLocationId":8,"parentId":1,"name":"PGD Trung Hòa","address":"30 Nguyễn Thị Định, P.Trung Hòa, Q.Cầu Giấy, TP.Hà Nội","phoneNumber":"Tel: (024) 3556 2709","faxNumber":"Fax: (024) 3556 2719"}]
     * totalRecords : 7
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

    public static class DataBean {
        /**
         * transactionLocationId : 2
         * parentId : 1
         * name : PGD Đội Cấn
         * address : 200 Đội Cấn, P. Đội Cấn, Ba Đình, Hà Nội
         * phoneNumber : Tel: (024) 3787 2368
         * faxNumber : Fax: (024) 3787 2399
         */

        private int transactionLocationId;
        private int parentId;
        private String name;
        private String address;
        private String phoneNumber;
        private String faxNumber;

        public int getTransactionLocationId() {
            return transactionLocationId;
        }

        public void setTransactionLocationId(int transactionLocationId) {
            this.transactionLocationId = transactionLocationId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFaxNumber() {
            return faxNumber;
        }

        public void setFaxNumber(String faxNumber) {
            this.faxNumber = faxNumber;
        }
    }
}
