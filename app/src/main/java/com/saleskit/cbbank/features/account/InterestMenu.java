package com.saleskit.cbbank.features.account;

import java.util.List;

public class InterestMenu {

    /**
     * data : [{"interestRateTableId":5,"code":"Test","name":"Biểu lãi suất 1","description":null,"interestRateTableDetails":[]},{"interestRateTableId":9,"code":"Test","name":"testtest","description":null,"interestRateTableDetails":[]},{"interestRateTableId":10,"code":"â","name":"aaaaaaaaaaaaaaaaaaa","description":"aaaaaaaaaa","interestRateTableDetails":[]},{"interestRateTableId":11,"code":"qqqqqqqq","name":"qqqqqqqqqqqqqqqqq","description":"qqqqqqqqqqqqqqqqqqqqqqqqqq","interestRateTableDetails":[]},{"interestRateTableId":4,"code":"BLS0001","name":"Biểu lãi suất thử nghiệm 1","description":"Không có miêu tả","interestRateTableDetails":[]}]
     * totalRecords : 0
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
        public DataBean() {
        }

        public DataBean(String name) {
            this.name = name;
        }

        /**
         * interestRateTableId : 5
         * code : Test
         * name : Biểu lãi suất 1
         * description : null
         * interestRateTableDetails : []
         */

        private int interestRateTableId;
        private String code;
        private String name;
        private Object description;
        private List<?> interestRateTableDetails;

        public int getInterestRateTableId() {
            return interestRateTableId;
        }

        public void setInterestRateTableId(int interestRateTableId) {
            this.interestRateTableId = interestRateTableId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public List<?> getInterestRateTableDetails() {
            return interestRateTableDetails;
        }

        public void setInterestRateTableDetails(List<?> interestRateTableDetails) {
            this.interestRateTableDetails = interestRateTableDetails;
        }
    }
}
