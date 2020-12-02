package com.saleskit.cbbank.features.account;

import java.util.List;

public class NotificationJson {

    /**
     * data : [{"notificationId":1,"type":1,"forwardId":42,"title":"Thông báo số 1","text":"Đây là nội dung thông báo số 1","priority":"medium","seen":true,"createdDate":"2019-12-23T21:11:21"},{"notificationId":2,"type":1,"forwardId":38,"title":"Thông báo số 2","text":"Đây là nội dung thông báo số 2","priority":"medium","seen":true,"createdDate":"2019-12-23T00:00:00"},{"notificationId":3,"type":2,"forwardId":519,"title":"Thông báo số 3","text":"Đây là nội dung thông báo số 3","priority":"medium","seen":false,"createdDate":"2019-12-23T00:00:00"},{"notificationId":4,"type":3,"forwardId":1405,"title":"Thông báo số 4","text":"Đây là nội dung thông báo số 4","priority":"medium","seen":false,"createdDate":"2019-12-23T00:00:00"},{"notificationId":5,"type":4,"forwardId":123123,"title":"Thông báo số 5","text":"Đây là nội dung thông báo số 5","priority":"medium","seen":false,"createdDate":"2019-12-23T00:00:00"}]
     * totalRecords : 5
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
         * notificationId : 1
         * type : 1
         * forwardId : 42
         * title : Thông báo số 1
         * text : Đây là nội dung thông báo số 1
         * priority : medium
         * seen : true
         * createdDate : 2019-12-23T21:11:21
         */

        private int notificationId;
        private int type;
        private int forwardId;
        private String title;
        private String text;
        private String priority;
        private boolean seen;
        private String createdDate;

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

        public int getForwardId() {
            return forwardId;
        }

        public void setForwardId(int forwardId) {
            this.forwardId = forwardId;
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

        public boolean isSeen() {
            return seen;
        }

        public void setSeen(boolean seen) {
            this.seen = seen;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }
    }
}
