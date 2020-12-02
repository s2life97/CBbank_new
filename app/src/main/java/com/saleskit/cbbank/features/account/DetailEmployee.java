package com.saleskit.cbbank.features.account;

import java.util.List;

public class DetailEmployee {


    /**
     * Data : {"Current":{"JobTitleId":"GĐCN","JobTitleName":"Giám đốc Chi nhánh","DepartmentCode":"900","DepartmentName":"BGÐ - CN CA MAU","BranchId":"900","BranchName":"Chi nhánh Cà Mau","PercentKpiYear":-521.262,"PercentKpiQuarter":23.861,"UserName":"thanhnc1","FullName":"Nguyễn Chí Thanh","TargetFDFN":9600000000,"FDFN":16187660263,"PercentFDFN":168.62,"TargetLN":8000000000,"LN":5714677938,"PercentLN":71.43,"PercentKpi":112.13,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},"ListPercentKpiMonth":[{"Year":2019,"Month":4,"PercentKpi":112.13},{"Year":2019,"Month":3,"PercentKpi":1.74},{"Year":2019,"Month":2,"PercentKpi":146.92},{"Year":2019,"Month":1,"PercentKpi":-526.86}]}
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
         * Current : {"JobTitleId":"GĐCN","JobTitleName":"Giám đốc Chi nhánh","DepartmentCode":"900","DepartmentName":"BGÐ - CN CA MAU","BranchId":"900","BranchName":"Chi nhánh Cà Mau","PercentKpiYear":-521.262,"PercentKpiQuarter":23.861,"UserName":"thanhnc1","FullName":"Nguyễn Chí Thanh","TargetFDFN":9600000000,"FDFN":16187660263,"PercentFDFN":168.62,"TargetLN":8000000000,"LN":5714677938,"PercentLN":71.43,"PercentKpi":112.13,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0}
         * ListPercentKpiMonth : [{"Year":2019,"Month":4,"PercentKpi":112.13},{"Year":2019,"Month":3,"PercentKpi":1.74},{"Year":2019,"Month":2,"PercentKpi":146.92},{"Year":2019,"Month":1,"PercentKpi":-526.86}]
         */

        private CurrentBean Current;
        private List<ListPercentKpiMonthBean> ListPercentKpiMonth;

        public CurrentBean getCurrent() {
            return Current;
        }

        public void setCurrent(CurrentBean Current) {
            this.Current = Current;
        }

        public List<ListPercentKpiMonthBean> getListPercentKpiMonth() {
            return ListPercentKpiMonth;
        }

        public void setListPercentKpiMonth(List<ListPercentKpiMonthBean> ListPercentKpiMonth) {
            this.ListPercentKpiMonth = ListPercentKpiMonth;
        }

        public static class CurrentBean {
            /**
             * JobTitleId : GĐCN
             * JobTitleName : Giám đốc Chi nhánh
             * DepartmentCode : 900
             * DepartmentName : BGÐ - CN CA MAU
             * BranchId : 900
             * BranchName : Chi nhánh Cà Mau
             * PercentKpiYear : -521.262
             * PercentKpiQuarter : 23.861
             * UserName : thanhnc1
             * FullName : Nguyễn Chí Thanh
             * TargetFDFN : 9600000000
             * FDFN : 16187660263
             * PercentFDFN : 168.62
             * TargetLN : 8000000000
             * LN : 5714677938
             * PercentLN : 71.43
             * PercentKpi : 112.13
             * OverdueDebt : 0
             * LoanOutstandingBalance : 0
             * PercentOverdueDebt : 0
             */

            private String JobTitleId;
            private String JobTitleName;
            private String DepartmentCode;
            private String DepartmentName;
            private String BranchId;
            private String BranchName;
            private double PercentKpiYear;
            private double PercentKpiQuarter;
            private String UserName;
            private String FullName;
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

            public String getJobTitleId() {
                return JobTitleId;
            }

            public void setJobTitleId(String JobTitleId) {
                this.JobTitleId = JobTitleId;
            }

            public String getJobTitleName() {
                return JobTitleName;
            }

            public void setJobTitleName(String JobTitleName) {
                this.JobTitleName = JobTitleName;
            }

            public String getDepartmentCode() {
                return DepartmentCode;
            }

            public void setDepartmentCode(String DepartmentCode) {
                this.DepartmentCode = DepartmentCode;
            }

            public String getDepartmentName() {
                return DepartmentName;
            }

            public void setDepartmentName(String DepartmentName) {
                this.DepartmentName = DepartmentName;
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

            public double getPercentKpiYear() {
                return PercentKpiYear;
            }

            public void setPercentKpiYear(double PercentKpiYear) {
                this.PercentKpiYear = PercentKpiYear;
            }

            public double getPercentKpiQuarter() {
                return PercentKpiQuarter;
            }

            public void setPercentKpiQuarter(double PercentKpiQuarter) {
                this.PercentKpiQuarter = PercentKpiQuarter;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String FullName) {
                this.FullName = FullName;
            }

            public double getTargetFDFN() {
                return TargetFDFN;
            }

            public void setTargetFDFN(long TargetFDFN) {
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

        public static class ListPercentKpiMonthBean {
            /**
             * Year : 2019
             * Month : 4
             * PercentKpi : 112.13
             */

            private int Year;
            private int Month;
            private double PercentKpi;

            public int getYear() {
                return Year;
            }

            public void setYear(int Year) {
                this.Year = Year;
            }

            public int getMonth() {
                return Month;
            }

            public void setMonth(int Month) {
                this.Month = Month;
            }

            public double getPercentKpi() {
                return PercentKpi;
            }

            public void setPercentKpi(double PercentKpi) {
                this.PercentKpi = PercentKpi;
            }
        }
    }
}
