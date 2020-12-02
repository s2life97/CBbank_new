package com.saleskit.cbbank.features.customer.add_new_customer;

public class CreatResultBean {

    /**
     * data : {"result":true,"message":"Khách hàng đủ điều kiện tạo hồ sơ vay vốn!"}
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
         * result : true
         * message : Khách hàng đủ điều kiện tạo hồ sơ vay vốn!
         */

        private boolean result;
        private String message;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
