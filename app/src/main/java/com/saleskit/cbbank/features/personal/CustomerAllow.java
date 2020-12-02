package com.saleskit.cbbank.features.personal;

public class CustomerAllow {

    /**
     * data : {"customerProfileAllowcationId":1382,"customerProfileId":1182,"userId":"anntt","searchUserId":"anntt","createdDate":"2019-12-11T16:33:54","userFirstName":"An","userLastName":"Nguyễn Thị Thùy","username":"anntt","searchUserFullName":"Nguyễn Thị Thùy An","searchUsername":null}
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
         * customerProfileAllowcationId : 1382
         * customerProfileId : 1182
         * userId : anntt
         * searchUserId : anntt
         * createdDate : 2019-12-11T16:33:54
         * userFirstName : An
         * userLastName : Nguyễn Thị Thùy
         * username : anntt
         * searchUserFullName : Nguyễn Thị Thùy An
         * searchUsername : null
         */

        private int customerProfileAllowcationId;
        private int customerProfileId;
        private String userId;
        private String searchUserId;
        private String createdDate;
        private String userFirstName;
        private String userLastName;
        private String username;
        private String searchUserFullName;
        private Object searchUsername;

        public int getCustomerProfileAllowcationId() {
            return customerProfileAllowcationId;
        }

        public void setCustomerProfileAllowcationId(int customerProfileAllowcationId) {
            this.customerProfileAllowcationId = customerProfileAllowcationId;
        }

        public int getCustomerProfileId() {
            return customerProfileId;
        }

        public void setCustomerProfileId(int customerProfileId) {
            this.customerProfileId = customerProfileId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSearchUserId() {
            return searchUserId;
        }

        public void setSearchUserId(String searchUserId) {
            this.searchUserId = searchUserId;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSearchUserFullName() {
            return searchUserFullName;
        }

        public void setSearchUserFullName(String searchUserFullName) {
            this.searchUserFullName = searchUserFullName;
        }

        public Object getSearchUsername() {
            return searchUsername;
        }

        public void setSearchUsername(Object searchUsername) {
            this.searchUsername = searchUsername;
        }
    }
}
