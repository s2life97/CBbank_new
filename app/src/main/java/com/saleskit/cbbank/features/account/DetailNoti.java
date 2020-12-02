package com.saleskit.cbbank.features.account;

import java.util.List;

public class DetailNoti {

    /**
     * data : {"notificationId":5,"type":4,"forwardId":null,"iconType":"333","title":"Thông báo số 5","text":"Đây là nội dung thông báo số 5","priority":"medium","userId":null,"status":2,"isDelete":false,"createdDate":"2019-01-01T00:00:00","validationErrors":[]}
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
         * notificationId : 5
         * type : 4
         * forwardId : null
         * iconType : 333
         * title : Thông báo số 5
         * text : Đây là nội dung thông báo số 5
         * priority : medium
         * userId : null
         * status : 2
         * isDelete : false
         * createdDate : 2019-01-01T00:00:00
         * validationErrors : []
         */

        private int notificationId;
        private int type;
        private Object forwardId;
        private String iconType;
        private String title;
        private String text;
        private String priority;
        private Object userId;
        private int status;
        private boolean isDelete;
        private String createdDate;
        private List<?> validationErrors;

        public int getNotificationId() {
            return notificationId;
        }

        public void setNotificationId(int notificationId) {
            this.notificationId = notificationId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getForwardId() {
            return forwardId;
        }

        public void setForwardId(Object forwardId) {
            this.forwardId = forwardId;
        }

        public String getIconType() {
            return iconType;
        }

        public void setIconType(String iconType) {
            this.iconType = iconType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public List<?> getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(List<?> validationErrors) {
            this.validationErrors = validationErrors;
        }
    }
}
