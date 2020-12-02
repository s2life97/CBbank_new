package com.saleskit.cbbank.features.kpi;

import java.util.List;

public class KpiBranch {


    /**
     * Data : {"Results":[{"BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","TargetFDFN":16000000,"FDFN":-2174195514,"PercentFDFN":-13588.72,"TargetLN":2600000000,"LN":7241685160,"PercentLN":278.53,"PercentKpi":-3859.36,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"080","BranchName":"Chi nhánh Đồng Nai","TargetFDFN":12000000,"FDFN":-1096517791,"PercentFDFN":-9137.65,"TargetLN":2400000000,"LN":7096022500,"PercentLN":295.67,"PercentKpi":-2519.54,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"900","BranchName":"Chi nhánh Cà Mau","TargetFDFN":5000000,"FDFN":-441525596,"PercentFDFN":-8830.51,"TargetLN":2000000000,"LN":1622340310,"PercentLN":81.12,"PercentKpi":-2590.75,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"310","BranchName":"Chi nhánh Bình Thuận","TargetFDFN":6000000,"FDFN":-428451870,"PercentFDFN":-7140.86,"TargetLN":1600000000,"LN":1894589837,"PercentLN":118.41,"PercentKpi":-2057,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"540","BranchName":"Chi nhánh Bình Dương","TargetFDFN":7000000,"FDFN":-405017364,"PercentFDFN":-5785.96,"TargetLN":2000000000,"LN":10220492951,"PercentLN":511.02,"PercentKpi":-1367.85,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0}],"CurrentPage":1,"PageCount":1,"PageSize":10,"RowCount":5,"FirstRowOnPage":1,"LastRowOnPage":5}
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
         * Results : [{"BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","TargetFDFN":16000000,"FDFN":-2174195514,"PercentFDFN":-13588.72,"TargetLN":2600000000,"LN":7241685160,"PercentLN":278.53,"PercentKpi":-3859.36,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"080","BranchName":"Chi nhánh Đồng Nai","TargetFDFN":12000000,"FDFN":-1096517791,"PercentFDFN":-9137.65,"TargetLN":2400000000,"LN":7096022500,"PercentLN":295.67,"PercentKpi":-2519.54,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"900","BranchName":"Chi nhánh Cà Mau","TargetFDFN":5000000,"FDFN":-441525596,"PercentFDFN":-8830.51,"TargetLN":2000000000,"LN":1622340310,"PercentLN":81.12,"PercentKpi":-2590.75,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"310","BranchName":"Chi nhánh Bình Thuận","TargetFDFN":6000000,"FDFN":-428451870,"PercentFDFN":-7140.86,"TargetLN":1600000000,"LN":1894589837,"PercentLN":118.41,"PercentKpi":-2057,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"BranchId":"540","BranchName":"Chi nhánh Bình Dương","TargetFDFN":7000000,"FDFN":-405017364,"PercentFDFN":-5785.96,"TargetLN":2000000000,"LN":10220492951,"PercentLN":511.02,"PercentKpi":-1367.85,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0}]
         * CurrentPage : 1
         * PageCount : 1
         * PageSize : 10
         * RowCount : 5
         * FirstRowOnPage : 1
         * LastRowOnPage : 5
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
             * BranchId : 070
             * BranchName : Chi nhánh Vũng Tàu
             * TargetFDFN : 16000000
             * FDFN : -2174195514
             * PercentFDFN : -13588.72
             * TargetLN : 2600000000
             * LN : 7241685160
             * PercentLN : 278.53
             * PercentKpi : -3859.36
             * OverdueDebt : 0
             * LoanOutstandingBalance : 0
             * PercentOverdueDebt : 0
             */

            private String BranchId;
            private String BranchName;
            private double TargetFDFN;
            private double FDFN;
            private double PercentFDFN;
            private double TargetLN;
            private double LN;
            private double PercentLN;
            private double PercentKpi;
            private double OverdueDebt;
            private double LoanOutstandingBalance;
            private double PercentOverdueDebt;
            private boolean IsShow;

            public boolean isShow() {
                return IsShow;
            }

            public void setShow(boolean show) {
                IsShow = show;
            }

            public String getBranchId() {
                return BranchId;
            }

            public void setBranchId(String BranchId) {
                this.BranchId = BranchId;
            }

            public String getBranchName() {
                return BranchName;
            }

            public void setBranchName(String BranchName) {
                this.BranchName = BranchName;
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

            public void setFDFN(long FDFN) {
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

            public void setTargetLN(long TargetLN) {
                this.TargetLN = TargetLN;
            }

            public double getLN() {
                return LN;
            }

            public void setLN(long LN) {
                this.LN = LN;
            }

            public double getPercentLN() {
                return PercentLN;
            }

            public void setPercentLN(double PercentLN) {
                this.PercentLN = PercentLN;
            }

            public double getPercentKpi() {
                return PercentKpi;
            }

            public void setPercentKpi(double PercentKpi) {
                this.PercentKpi = PercentKpi;
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
        }
    }
}
