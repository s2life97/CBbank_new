package com.saleskit.cbbank.features.account;

import java.util.List;

public class AppointmentDetail {

    /**
     * data : {"appointmentId":60,"customerName":"Đã sửa","identityNumber":"Đã sửa","phoneNumber":"Đã sửa","email":"Đã sửa","appointmentAddress":"Đã sửa","expectedProduct":"string","description":"string","appointmentTime":"2019-09-23T01:30:47","resultStatus":0,"resultStatusString":"Chưa có kết quả","resultDescription":"string","nextAppointmentId":0,"appointmentImages":[{"appointmentImageId":39,"appointmentId":60,"fileName":"6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","path":"/Files/Upload/AppointmentImage/6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","extension":".jpg","size":883793},{"appointmentImageId":50,"appointmentId":60,"fileName":"6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","path":"/Files/Upload/AppointmentImage/6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","extension":"JPG","size":883793}]}
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
         * appointmentId : 60
         * customerName : Đã sửa
         * identityNumber : Đã sửa
         * phoneNumber : Đã sửa
         * email : Đã sửa
         * appointmentAddress : Đã sửa
         * expectedProduct : string
         * description : string
         * appointmentTime : 2019-09-23T01:30:47
         * resultStatus : 0
         * resultStatusString : Chưa có kết quả
         * resultDescription : string
         * nextAppointmentId : 0
         * appointmentImages : [{"appointmentImageId":39,"appointmentId":60,"fileName":"6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","path":"/Files/Upload/AppointmentImage/6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","extension":".jpg","size":883793},{"appointmentImageId":50,"appointmentId":60,"fileName":"6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","path":"/Files/Upload/AppointmentImage/6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg","extension":"JPG","size":883793}]
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
        private int nextAppointmentId;
        private List<AppointmentImagesBean> appointmentImages;

        public DataBean(int appointmentId, String customerName, String identityNumber, String phoneNumber, String email, String appointmentAddress, String expectedProduct, String description, String appointmentTime, int resultStatus, String resultDescription, int nextAppointmentId, List<AppointmentImagesBean> appointmentImages) {
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
            this.resultDescription = resultDescription;
            this.nextAppointmentId = nextAppointmentId;
            this.appointmentImages = appointmentImages;
        }

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

        public int getNextAppointmentId() {
            return nextAppointmentId;
        }

        public void setNextAppointmentId(int nextAppointmentId) {
            this.nextAppointmentId = nextAppointmentId;
        }

        public List<AppointmentImagesBean> getAppointmentImages() {
            return appointmentImages;
        }

        public void setAppointmentImages(List<AppointmentImagesBean> appointmentImages) {
            this.appointmentImages = appointmentImages;
        }

        public static class AppointmentImagesBean {
            /**
             * appointmentImageId : 39
             * appointmentId : 60
             * fileName : 6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg
             * path : /Files/Upload/AppointmentImage/6a068d9f-2e01-4fcb-978d-e816ff97a7a0.jpg
             * extension : .jpg
             * size : 883793
             */

            private int appointmentImageId;
            private int appointmentId;
            private String fileName;
            private String path;
            private String extension;
            private int size;

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
