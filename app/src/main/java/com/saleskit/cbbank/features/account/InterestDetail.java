package com.saleskit.cbbank.features.account;

import java.util.List;

public class InterestDetail {

    /**
     * data : {"interestRateTableId":26,"code":"Test2","name":"123232131","description":null,"interestRateTableDetails":[{"interestRateTableDetailId":538,"interestRateTableId":26,"type":"Không kỳ hạn","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":539,"interestRateTableId":26,"type":"1 tuần","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":540,"interestRateTableId":26,"type":"2 tuần","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":541,"interestRateTableId":26,"type":"3 tuần","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":542,"interestRateTableId":26,"type":"1 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":543,"interestRateTableId":26,"type":"2 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":544,"interestRateTableId":26,"type":"3 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":545,"interestRateTableId":26,"type":"4 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":546,"interestRateTableId":26,"type":"5 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":547,"interestRateTableId":26,"type":"6 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":548,"interestRateTableId":26,"type":"7 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":549,"interestRateTableId":26,"type":"8 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":550,"interestRateTableId":26,"type":"9 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":551,"interestRateTableId":26,"type":"10 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":552,"interestRateTableId":26,"type":"11 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":553,"interestRateTableId":26,"type":"12 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":554,"interestRateTableId":26,"type":"13 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":555,"interestRateTableId":26,"type":"15 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":556,"interestRateTableId":26,"type":"18 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":557,"interestRateTableId":26,"type":"24 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":558,"interestRateTableId":26,"type":"36 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":559,"interestRateTableId":26,"type":"48 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":560,"interestRateTableId":26,"type":"60 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":561,"interestRateTableId":26,"type":"1-10 năm","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12}]}
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
         * interestRateTableId : 26
         * code : Test2
         * name : 123232131
         * description : null
         * interestRateTableDetails : [{"interestRateTableDetailId":538,"interestRateTableId":26,"type":"Không kỳ hạn","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":539,"interestRateTableId":26,"type":"1 tuần","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":540,"interestRateTableId":26,"type":"2 tuần","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":541,"interestRateTableId":26,"type":"3 tuần","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":542,"interestRateTableId":26,"type":"1 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":543,"interestRateTableId":26,"type":"2 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":544,"interestRateTableId":26,"type":"3 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":545,"interestRateTableId":26,"type":"4 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":546,"interestRateTableId":26,"type":"5 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":547,"interestRateTableId":26,"type":"6 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":548,"interestRateTableId":26,"type":"7 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":549,"interestRateTableId":26,"type":"8 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":550,"interestRateTableId":26,"type":"9 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":551,"interestRateTableId":26,"type":"10 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":552,"interestRateTableId":26,"type":"11 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":553,"interestRateTableId":26,"type":"12 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":554,"interestRateTableId":26,"type":"13 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":555,"interestRateTableId":26,"type":"15 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":556,"interestRateTableId":26,"type":"18 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":557,"interestRateTableId":26,"type":"24 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":558,"interestRateTableId":26,"type":"36 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":559,"interestRateTableId":26,"type":"48 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":560,"interestRateTableId":26,"type":"60 tháng","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12},{"interestRateTableDetailId":561,"interestRateTableId":26,"type":"1-10 năm","payInterestAtMaturity":12,"payMonthlyInterest":12,"quarterlyInterestPayment":12,"payInterestFirst":12}]
         */

        private int interestRateTableId;
        private String code;
        private String name;
        private Object description;
        private List<InterestRateTableDetailsBean> interestRateTableDetails;

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

        public List<InterestRateTableDetailsBean> getInterestRateTableDetails() {
            return interestRateTableDetails;
        }

        public void setInterestRateTableDetails(List<InterestRateTableDetailsBean> interestRateTableDetails) {
            this.interestRateTableDetails = interestRateTableDetails;
        }

        public static class InterestRateTableDetailsBean {
            /**
             * interestRateTableDetailId : 538
             * interestRateTableId : 26
             * type : Không kỳ hạn
             * payInterestAtMaturity : 12.0
             * payMonthlyInterest : 12.0
             * quarterlyInterestPayment : 12.0
             * payInterestFirst : 12.0
             */

            private int interestRateTableDetailId;
            private int interestRateTableId;
            private String type;
            private double payInterestAtMaturity;
            private double payMonthlyInterest;
            private double quarterlyInterestPayment;
            private double payInterestFirst;

            public int getInterestRateTableDetailId() {
                return interestRateTableDetailId;
            }

            public void setInterestRateTableDetailId(int interestRateTableDetailId) {
                this.interestRateTableDetailId = interestRateTableDetailId;
            }

            public int getInterestRateTableId() {
                return interestRateTableId;
            }

            public void setInterestRateTableId(int interestRateTableId) {
                this.interestRateTableId = interestRateTableId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getPayInterestAtMaturity() {
                return payInterestAtMaturity;
            }

            public void setPayInterestAtMaturity(double payInterestAtMaturity) {
                this.payInterestAtMaturity = payInterestAtMaturity;
            }

            public double getPayMonthlyInterest() {
                return payMonthlyInterest;
            }

            public void setPayMonthlyInterest(double payMonthlyInterest) {
                this.payMonthlyInterest = payMonthlyInterest;
            }

            public double getQuarterlyInterestPayment() {
                return quarterlyInterestPayment;
            }

            public void setQuarterlyInterestPayment(double quarterlyInterestPayment) {
                this.quarterlyInterestPayment = quarterlyInterestPayment;
            }

            public double getPayInterestFirst() {
                return payInterestFirst;
            }

            public void setPayInterestFirst(double payInterestFirst) {
                this.payInterestFirst = payInterestFirst;
            }
        }
    }
}
