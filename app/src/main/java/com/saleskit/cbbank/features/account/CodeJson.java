package com.saleskit.cbbank.features.account;

public class CodeJson {

    /**
     * data : {"isSuccess":true,"content":"Thêm mới khách hàng & Thêm mới hồ sơ thành công!","data":{"customerId":207,"customerProfileId":486,"tradingCode":"19HD00700001"}}
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
         * content : Thêm mới khách hàng & Thêm mới hồ sơ thành công!
         * data : {"customerId":207,"customerProfileId":486,"tradingCode":"19HD00700001"}
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
             * customerId : 207
             * customerProfileId : 486
             * tradingCode : 19HD00700001
             */

            private int customerId;
            private int customerProfileId;
            private String tradingCode;

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

            public String getTradingCode() {
                return tradingCode;
            }

            public void setTradingCode(String tradingCode) {
                this.tradingCode = tradingCode;
            }
        }
    }
}
