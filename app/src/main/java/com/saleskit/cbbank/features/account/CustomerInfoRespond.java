package com.saleskit.cbbank.features.account;

public class CustomerInfoRespond {

    /**
     * data : {"isSuccess":true,"content":"Cập nhật khách hàng & Cập nhật hồ sơ thành công!","data":{"customerId":160,"customerProfileId":379,"tradingCode":null}}
     * totalRecords : 0
     */

    private DataBeanX data;
    private int totalRecords;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public static class DataBeanX {
        /**
         * isSuccess : true
         * content : Cập nhật khách hàng & Cập nhật hồ sơ thành công!
         * data : {"customerId":160,"customerProfileId":379,"tradingCode":null}
         */

        private boolean isSuccess;
        private String content;
        private DataBean data;

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * customerId : 160
             * customerProfileId : 379
             * tradingCode : null
             */

            private int customerId;
            private int customerProfileId;
            private Object tradingCode;

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public int getCustomerProfileId() {
                return customerProfileId;
            }

            public void setCustomerProfileId(int customerProfileId) {
                this.customerProfileId = customerProfileId;
            }

            public Object getTradingCode() {
                return tradingCode;
            }

            public void setTradingCode(Object tradingCode) {
                this.tradingCode = tradingCode;
            }
        }
    }
}
