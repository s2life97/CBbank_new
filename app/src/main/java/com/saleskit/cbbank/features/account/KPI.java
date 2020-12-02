package com.saleskit.cbbank.features.account;

import java.util.List;

public class KPI {


    /**
     * Data : {"Results":[{"RegionId":"MTNB2","RegionName":"TÂY NAM BỘ 2","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MTNB1","RegionName":"TÂY NAM BỘ 1","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MSG","RegionName":"SÀI GÒN","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MT","RegionName":"MIỀN TRUNG","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MHN","RegionName":"HÀ NỘI","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MDNB","RegionName":"ĐÔNG NAM BỘ","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0}],"CurrentPage":1,"PageCount":1,"PageSize":10,"RowCount":6,"FirstRowOnPage":1,"LastRowOnPage":6}
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
         * Results : [{"RegionId":"MTNB2","RegionName":"TÂY NAM BỘ 2","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MTNB1","RegionName":"TÂY NAM BỘ 1","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MSG","RegionName":"SÀI GÒN","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MT","RegionName":"MIỀN TRUNG","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MHN","RegionName":"HÀ NỘI","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0},{"RegionId":"MDNB","RegionName":"ĐÔNG NAM BỘ","TargetFDFN":0,"FDFN":0,"PercentFDFN":null,"TargetLN":0,"LN":0,"PercentLN":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0,"PercentKpi":0}]
         * CurrentPage : 1
         * PageCount : 1
         * PageSize : 10
         * RowCount : 6
         * FirstRowOnPage : 1
         * LastRowOnPage : 6
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
             * RegionId : MTNB2
             * RegionName : TÂY NAM BỘ 2
             * TargetFDFN : 0
             * FDFN : 0
             * PercentFDFN : null
             * TargetLN : 0
             * LN : 0
             * PercentLN : null
             * OverdueDebt : 0
             * LoanOutstandingBalance : 0
             * PercentOverdueDebt : 0
             * PercentKpi : 0
             */

            private String RegionId;
            private String RegionName;
            private double TargetFDFN;
            private double FDFN;
            private Object PercentFDFN;
            private double TargetLN;
            private double LN;
            private Object PercentLN;
            private double OverdueDebt;
            private double LoanOutstandingBalance;
            private double PercentOverdueDebt;
            private double PercentKpi;
            private boolean IsShow;

            public boolean isShow() {
                return IsShow;
            }

            public void setShow(boolean show) {
                IsShow = show;
            }

            public String getRegionId() {
                return RegionId;
            }

            public void setRegionId(String RegionId) {
                this.RegionId = RegionId;
            }

            public String getRegionName() {
                return RegionName;
            }

            public void setRegionName(String RegionName) {
                this.RegionName = RegionName;
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

            public Object getPercentFDFN() {
                return PercentFDFN;
            }

            public void setPercentFDFN(Object PercentFDFN) {
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

            public Object getPercentLN() {
                return PercentLN;
            }

            public void setPercentLN(Object PercentLN) {
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

            public void setPercentKpi(int PercentKpi) {
                this.PercentKpi = PercentKpi;
            }
        }
    }
}
