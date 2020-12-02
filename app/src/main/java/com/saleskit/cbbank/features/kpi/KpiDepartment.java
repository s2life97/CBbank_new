package com.saleskit.cbbank.features.kpi;

import java.util.List;

public class KpiDepartment {


    /**
     * Data : {"Results":[{"DepartmentId":"074","DepartmentName":"PGD TAN THANH.","TargetFDFN":2000000,"FDFN":-45412445,"PercentFDFN":-2270.62,"TargetLN":400000000,"LN":768699737,"PercentLN":192.17,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-554.35,"IsShow":false},{"DepartmentId":"072","DepartmentName":"PGD SAO MAI","TargetFDFN":3000000,"FDFN":-243900218,"PercentFDFN":-8130.01,"TargetLN":400000000,"LN":1801560953,"PercentLN":450.39,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-2128.23,"IsShow":false},{"DepartmentId":"073","DepartmentName":"PGD BACH HO","TargetFDFN":3000000,"FDFN":-337012298,"PercentFDFN":-11233.74,"TargetLN":400000000,"LN":-193698016,"PercentLN":-48.42,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-3399.17,"IsShow":false},{"DepartmentId":"071","DepartmentName":"PGD BA RIA","TargetFDFN":3000000,"FDFN":-279400665,"PercentFDFN":-9313.36,"TargetLN":400000000,"LN":1102453880,"PercentLN":275.61,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-2595.57,"IsShow":false},{"DepartmentId":"07001","DepartmentName":"P.KHBL - VUNG TAU","TargetFDFN":2000000,"FDFN":-114764682,"PercentFDFN":-5738.23,"TargetLN":1000000000,"LN":3762668606,"PercentLN":376.27,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-1461.84,"IsShow":false},{"DepartmentId":"07002","DepartmentName":"P.DVKH - VUNG TAU","TargetFDFN":3000000,"FDFN":-821979642,"PercentFDFN":-27399.32,"TargetLN":0,"LN":0,"PercentLN":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-27399.32,"IsShow":false},{"DepartmentId":"070","DepartmentName":"BGÐ - CN VUNG TAU","TargetFDFN":16000000,"FDFN":-2174195514,"PercentFDFN":-13588.72,"TargetLN":2600000000,"LN":7241685160,"PercentLN":278.53,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-3859.36,"IsShow":false}],"CurrentPage":1,"PageCount":1,"PageSize":10,"RowCount":7,"FirstRowOnPage":1,"LastRowOnPage":7}
     * Message : null
     * Success : true
     * Error : null
     */

    private DataBean Data;
    private Object Message;
    private boolean Success;
    private Object Error;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public Object getError() {
        return Error;
    }

    public void setError(Object Error) {
        this.Error = Error;
    }

    public static class DataBean {
        /**
         * Results : [{"DepartmentId":"074","DepartmentName":"PGD TAN THANH.","TargetFDFN":2000000,"FDFN":-45412445,"PercentFDFN":-2270.62,"TargetLN":400000000,"LN":768699737,"PercentLN":192.17,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-554.35,"IsShow":false},{"DepartmentId":"072","DepartmentName":"PGD SAO MAI","TargetFDFN":3000000,"FDFN":-243900218,"PercentFDFN":-8130.01,"TargetLN":400000000,"LN":1801560953,"PercentLN":450.39,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-2128.23,"IsShow":false},{"DepartmentId":"073","DepartmentName":"PGD BACH HO","TargetFDFN":3000000,"FDFN":-337012298,"PercentFDFN":-11233.74,"TargetLN":400000000,"LN":-193698016,"PercentLN":-48.42,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-3399.17,"IsShow":false},{"DepartmentId":"071","DepartmentName":"PGD BA RIA","TargetFDFN":3000000,"FDFN":-279400665,"PercentFDFN":-9313.36,"TargetLN":400000000,"LN":1102453880,"PercentLN":275.61,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-2595.57,"IsShow":false},{"DepartmentId":"07001","DepartmentName":"P.KHBL - VUNG TAU","TargetFDFN":2000000,"FDFN":-114764682,"PercentFDFN":-5738.23,"TargetLN":1000000000,"LN":3762668606,"PercentLN":376.27,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-1461.84,"IsShow":false},{"DepartmentId":"07002","DepartmentName":"P.DVKH - VUNG TAU","TargetFDFN":3000000,"FDFN":-821979642,"PercentFDFN":-27399.32,"TargetLN":0,"LN":0,"PercentLN":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-27399.32,"IsShow":false},{"DepartmentId":"070","DepartmentName":"BGÐ - CN VUNG TAU","TargetFDFN":16000000,"FDFN":-2174195514,"PercentFDFN":-13588.72,"TargetLN":2600000000,"LN":7241685160,"PercentLN":278.53,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":-3859.36,"IsShow":false}]
         * CurrentPage : 1
         * PageCount : 1
         * PageSize : 10
         * RowCount : 7
         * FirstRowOnPage : 1
         * LastRowOnPage : 7
         */

        private int CurrentPage;
        private int PageCount;
        private int PageSize;
        private int RowCount;
        private int FirstRowOnPage;
        private int LastRowOnPage;
        private List<ResultsBean> Results;

        public int getCurrentPage() {
            return CurrentPage;
        }

        public void setCurrentPage(int CurrentPage) {
            this.CurrentPage = CurrentPage;
        }

        public int getPageCount() {
            return PageCount;
        }

        public void setPageCount(int PageCount) {
            this.PageCount = PageCount;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getRowCount() {
            return RowCount;
        }

        public void setRowCount(int RowCount) {
            this.RowCount = RowCount;
        }

        public int getFirstRowOnPage() {
            return FirstRowOnPage;
        }

        public void setFirstRowOnPage(int FirstRowOnPage) {
            this.FirstRowOnPage = FirstRowOnPage;
        }

        public int getLastRowOnPage() {
            return LastRowOnPage;
        }

        public void setLastRowOnPage(int LastRowOnPage) {
            this.LastRowOnPage = LastRowOnPage;
        }

        public List<ResultsBean> getResults() {
            return Results;
        }

        public void setResults(List<ResultsBean> Results) {
            this.Results = Results;
        }

        public static class ResultsBean {
            /**
             * DepartmentId : 074
             * DepartmentName : PGD TAN THANH.
             * TargetFDFN : 2000000
             * FDFN : -45412445
             * PercentFDFN : -2270.62
             * TargetLN : 400000000
             * LN : 768699737
             * PercentLN : 192.17
             * OverdueDebt : 0
             * LoanOutstandingBalance : 0
             * PercentOverdueDebt : 0
             * PercentKpi : -554.35
             * IsShow : false
             */

            private String DepartmentId;
            private String DepartmentName;
            private double TargetFDFN;
            private double FDFN;
            private double PercentFDFN;
            private double TargetLN;
            private double LN;
            private double PercentLN;
            private double OverdueDebt;
            private double LoanOutstandingBalance;
            private double PercentOverdueDebt;
            private double PercentKpi;
            private boolean IsShow;

            public String getDepartmentId() {
                return DepartmentId;
            }

            public void setDepartmentId(String DepartmentId) {
                this.DepartmentId = DepartmentId;
            }

            public String getDepartmentName() {
                return DepartmentName;
            }

            public void setDepartmentName(String DepartmentName) {
                this.DepartmentName = DepartmentName;
            }

            public double getTargetFDFN() {
                return TargetFDFN;
            }

            public void setTargetFDFN(int TargetFDFN) {
                this.TargetFDFN = TargetFDFN;
            }

            public double getFDFN() {
                return FDFN;
            }

            public void setFDFN(int FDFN) {
                this.FDFN = FDFN;
            }

            public double getPercentFDFN() {
                return PercentFDFN;
            }

            public void setPercentFDFN(double PercentFDFN) {
                this.PercentFDFN = PercentFDFN;
            }

            public double getTargetLN() {
                return TargetLN;
            }

            public void setTargetLN(int TargetLN) {
                this.TargetLN = TargetLN;
            }

            public double getLN() {
                return LN;
            }

            public void setLN(int LN) {
                this.LN = LN;
            }

            public double getPercentLN() {
                return PercentLN;
            }

            public void setPercentLN(double PercentLN) {
                this.PercentLN = PercentLN;
            }

            public double getOverdueDebt() {
                return OverdueDebt;
            }

            public void setOverdueDebt(int OverdueDebt) {
                this.OverdueDebt = OverdueDebt;
            }

            public double getLoanOutstandingBalance() {
                return LoanOutstandingBalance;
            }

            public void setLoanOutstandingBalance(int LoanOutstandingBalance) {
                this.LoanOutstandingBalance = LoanOutstandingBalance;
            }

            public double getPercentOverdueDebt() {
                return PercentOverdueDebt;
            }

            public void setPercentOverdueDebt(int PercentOverdueDebt) {
                this.PercentOverdueDebt = PercentOverdueDebt;
            }

            public double getPercentKpi() {
                return PercentKpi;
            }

            public void setPercentKpi(double PercentKpi) {
                this.PercentKpi = PercentKpi;
            }

            public boolean isIsShow() {
                return IsShow;
            }

            public void setIsShow(boolean IsShow) {
                this.IsShow = IsShow;
            }
        }
    }
}
