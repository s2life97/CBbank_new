package com.saleskit.cbbank.features.appointment;

import java.util.List;

public class AppointDetail {

    /**
     * data : {"appointmentID":2,"appointmentDate":"2020-01-01T00:00:00","description":"Có","resultStatus":0,"resultDescription":null,"customerID":1,"customerDescription":"Muốn hỗ trợ vay def","customerFirstName":"A","customerLastName":"Nguyen Van","customerFullName":"Nguyen Van A","phoneNumber":"0123456789","identityNumber":"174808206","address":"Ha Noi - Vietnam","userId":"98242421-1fea-4471-9e33-14bd319c6c3c","userFirstName":"Tran Duc","userLastName":"Hung","userFullName":"Hung Tran Duc","appointmentImages":[{"appointmentImageId":1,"appointmentId":2,"fileName":"FileName","path":"Path","extension":"Extension","size":128}]}
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
         * appointmentID : 2
         * appointmentDate : 2020-01-01T00:00:00
         * description : Có
         * resultStatus : 0
         * resultDescription : null
         * customerID : 1
         * customerDescription : Muốn hỗ trợ vay def
         * customerFirstName : A
         * customerLastName : Nguyen Van
         * customerFullName : Nguyen Van A
         * phoneNumber : 0123456789
         * identityNumber : 174808206
         * address : Ha Noi - Vietnam
         * userId : 98242421-1fea-4471-9e33-14bd319c6c3c
         * userFirstName : Tran Duc
         * userLastName : Hung
         * userFullName : Hung Tran Duc
         * appointmentImages : [{"appointmentImageId":1,"appointmentId":2,"fileName":"FileName","path":"Path","extension":"Extension","size":128}]
         */

        private int appointmentID;
        private String appointmentDate;
        private String description;
        private int resultStatus;
        private Object resultDescription;
        private int customerID;
        private String customerDescription;
        private String customerFirstName;
        private String customerLastName;
        private String customerFullName;
        private String phoneNumber;
        private String identityNumber;
        private String address;
        private String userId;
        private String userFirstName;
        private String userLastName;
        private String userFullName;
        private List<AppointmentImagesBean> appointmentImages;

        public int getAppointmentID() {
            return appointmentID;
        }

        public void setAppointmentID(int appointmentID) {
            this.appointmentID = appointmentID;
        }

        public String getAppointmentDate() {
            return appointmentDate;
        }

        public void setAppointmentDate(String appointmentDate) {
            this.appointmentDate = appointmentDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(int resultStatus) {
            this.resultStatus = resultStatus;
        }

        public Object getResultDescription() {
            return resultDescription;
        }

        public void setResultDescription(Object resultDescription) {
            this.resultDescription = resultDescription;
        }

        public int getCustomerID() {
            return customerID;
        }

        public void setCustomerID(int customerID) {
            this.customerID = customerID;
        }

        public String getCustomerDescription() {
            return customerDescription;
        }

        public void setCustomerDescription(String customerDescription) {
            this.customerDescription = customerDescription;
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

        public String getCustomerFullName() {
            return customerFullName;
        }

        public void setCustomerFullName(String customerFullName) {
            this.customerFullName = customerFullName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserFirstName() {
            return userFirstName;
        }

        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        public String getUserLastName() {
            return userLastName;
        }

        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public List<AppointmentImagesBean> getAppointmentImages() {
            return appointmentImages;
        }

        public void setAppointmentImages(List<AppointmentImagesBean> appointmentImages) {
            this.appointmentImages = appointmentImages;
        }

        public static class AppointmentImagesBean {
            /**
             * appointmentImageId : 1
             * appointmentId : 2
             * fileName : FileName
             * path : Path
             * extension : Extension
             * size : 128
             */

            private int appointmentImageId;
            private int appointmentId;
            private String fileName;
            private String path;
            private String extension;
            private int size;

            public AppointmentImagesBean() {
            }

            public AppointmentImagesBean(int appointmentImageId, int appointmentId, String fileName, String path, String extension, int size) {
                this.appointmentImageId = appointmentImageId;
                this.appointmentId = appointmentId;
                this.fileName = fileName;
                this.path = path;
                this.extension = extension;
                this.size = size;
            }

            public int getAppointmentImageId() {
                return appointmentImageId;
            }

            public void setAppointmentImageId(int appointmentImageId) {
                this.appointmentImageId = appointmentImageId;
            }

            public int getAppointmentId() {
                return appointmentId;
            }

            public void setAppointmentId(int appointmentId) {
                this.appointmentId = appointmentId;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }
    }
}
