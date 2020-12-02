package com.saleskit.cbbank.features.kpi;

import java.util.List;

public class YearDetailEmployee {

    /**
     * Data : {"Current":{"JobTitleId":"GĐCN","JobTitleName":"Giám đốc Chi nhánh","DepartmentCode":"900","DepartmentName":"BGÐ - CN CA MAU","BranchId":"900","BranchName":"Chi nhánh Cà Mau","UserName":"thanhnc1","FullName":"Nguyễn Chí Thanh","TargetFDFN":43208000000,"FDFN":25371006680,"PercentFDFN":null,"TargetLN":41000000000,"LN":17209768340,"PercentLN":null,"PercentKpi":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},"ListPercentKpiYear":[{"Year":2019,"PercentKpi":-43.905},{"Year":2018,"PercentKpi":276.523},{"Year":2017,"PercentKpi":0},{"Year":2016,"PercentKpi":0}]}
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
         * Current : {"JobTitleId":"GĐCN","JobTitleName":"Giám đốc Chi nhánh","DepartmentCode":"900","DepartmentName":"BGÐ - CN CA MAU","BranchId":"900","BranchName":"Chi nhánh Cà Mau","UserName":"thanhnc1","FullName":"Nguyễn Chí Thanh","TargetFDFN":43208000000,"FDFN":25371006680,"PercentFDFN":null,"TargetLN":41000000000,"LN":17209768340,"PercentLN":null,"PercentKpi":null,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0}
         * ListPercentKpiYear : [{"Year":2019,"PercentKpi":-43.905},{"Year":2018,"PercentKpi":276.523},{"Year":2017,"PercentKpi":0},{"Year":2016,"PercentKpi":0}]
         */

        private CurrentBean Current;
        private List<ListPercentKpiYearBean> ListPercentKpiYear;

        public CurrentBean getCurrent() {
            return Current;
        }

        public void setCurrent(CurrentBean Current) {
            this.Current = Current;
        }

        public List<ListPercentKpiYearBean> getListPercentKpiYear() {
            return ListPercentKpiYear;
        }

        public void setListPercentKpiYear(List<ListPercentKpiYearBean> ListPercentKpiYear) {
            this.ListPercentKpiYear = ListPercentKpiYear;
        }

        public static class CurrentBean {
            /**
             * JobTitleId : GĐCN
             * JobTitleName : Giám đốc Chi nhánh
             * DepartmentCode : 900
             * DepartmentName : BGÐ - CN CA MAU
             * BranchId : 900
             * BranchName : Chi nhánh Cà Mau
             * UserName : thanhnc1
             * FullName : Nguyễn Chí Thanh
             * TargetFDFN : 43208000000
             * FDFN : 25371006680
             * PercentFDFN : null
             * TargetLN : 41000000000
             * LN : 17209768340
             * PercentLN : null
             * PercentKpi : null
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

            public void setTargetFDFN(double TargetFDFN) {
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

            public Object getPercentKpi() {
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

        public static class ListPercentKpiYearBean {
            /**
             * Year : 2019
             * PercentKpi : -43.905
             */

            private int Year;
            private double PercentKpi;

            public int getYear() {
                return Year;
            }

            public void setYear(int Year) {
                this.Year = Year;
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
