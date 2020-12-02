package com.saleskit.cbbank.features.account;

import java.io.Serializable;
import java.util.List;

public class KpiEmplyee {


    /**
     * Data : {"Results":[{"UserName":"binhpq","FullName":"Phạm Quang Bình","SaleId":2,"SaleName":null,"JobTitleId":"TN.QHKH2","JobTitleName":"Trưởng nhóm Quan hệ khách hàng bậc 2 (lương bậc 2)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":900000,"FDFN":8516387,"PercentFDFN":200,"TargetLN":7.0E8,"LN":-5.808041E7,"PercentLN":-8.3,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"tuanpn","FullName":"Phạm Ngọc Tuân","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH2","JobTitleName":"Chuyên viên QHKH bậc 2  (lương bậc 2)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":500000,"FDFN":8569599,"PercentFDFN":200,"TargetLN":3.0E8,"LN":8.6176359E7,"PercentLN":28.73,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"vyntk","FullName":"Nguyễn Thị Kim Vy","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH1","JobTitleName":"Chuyên viên QHKH bậc 1  (lương bậc 1)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":400000,"FDFN":-2802756,"PercentFDFN":-700.69,"TargetLN":2.0E8,"LN":-1666667,"PercentLN":-0.83,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"luannt","FullName":"Nguyễn Thành Luận","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH2","JobTitleName":"Chuyên viên QHKH bậc 2  (lương bậc 2)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":500000,"FDFN":0,"PercentFDFN":0,"TargetLN":3.0E8,"LN":0,"PercentLN":0,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"thuanltb","FullName":"Lê Thị Bích Thuận","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH1","JobTitleName":"Chuyên viên QHKH bậc 1  (lương bậc 1)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":400000,"FDFN":2.5121983E7,"PercentFDFN":200,"TargetLN":2.0E8,"LN":-8.3066046E7,"PercentLN":-41.53,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"hungbt","FullName":"Bùi Thế Hùng","SaleId":2,"SaleName":null,"JobTitleId":"PT.KHBL","JobTitleName":"Phụ trách phòng Khách hàng bán lẻ","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":2000000,"FDFN":5.7712824E7,"PercentFDFN":200,"TargetLN":1.0E9,"LN":-8.6866522E7,"PercentLN":-8.69,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0}],"CurrentPage":1,"PageCount":1,"PageSize":10,"RowCount":6,"FirstRowOnPage":1,"LastRowOnPage":6}
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
         * Results : [{"UserName":"binhpq","FullName":"Phạm Quang Bình","SaleId":2,"SaleName":null,"JobTitleId":"TN.QHKH2","JobTitleName":"Trưởng nhóm Quan hệ khách hàng bậc 2 (lương bậc 2)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":900000,"FDFN":8516387,"PercentFDFN":200,"TargetLN":7.0E8,"LN":-5.808041E7,"PercentLN":-8.3,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"tuanpn","FullName":"Phạm Ngọc Tuân","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH2","JobTitleName":"Chuyên viên QHKH bậc 2  (lương bậc 2)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":500000,"FDFN":8569599,"PercentFDFN":200,"TargetLN":3.0E8,"LN":8.6176359E7,"PercentLN":28.73,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"vyntk","FullName":"Nguyễn Thị Kim Vy","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH1","JobTitleName":"Chuyên viên QHKH bậc 1  (lương bậc 1)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":400000,"FDFN":-2802756,"PercentFDFN":-700.69,"TargetLN":2.0E8,"LN":-1666667,"PercentLN":-0.83,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"luannt","FullName":"Nguyễn Thành Luận","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH2","JobTitleName":"Chuyên viên QHKH bậc 2  (lương bậc 2)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":500000,"FDFN":0,"PercentFDFN":0,"TargetLN":3.0E8,"LN":0,"PercentLN":0,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"thuanltb","FullName":"Lê Thị Bích Thuận","SaleId":2,"SaleName":null,"JobTitleId":"CVQHKH1","JobTitleName":"Chuyên viên QHKH bậc 1  (lương bậc 1)","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":400000,"FDFN":2.5121983E7,"PercentFDFN":200,"TargetLN":2.0E8,"LN":-8.3066046E7,"PercentLN":-41.53,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0},{"UserName":"hungbt","FullName":"Bùi Thế Hùng","SaleId":2,"SaleName":null,"JobTitleId":"PT.KHBL","JobTitleName":"Phụ trách phòng Khách hàng bán lẻ","DepartmentCode":"07001","DepartmentName":"P.KHBL - VUNG TAU","AreaId":"070","AreaName":"CN VUNG TAU","BranchId":"070","BranchName":"Chi nhánh Vũng Tàu","RegionId":"vung2","RegionName":"Địa bàn 2","TargetFDFN":2000000,"FDFN":5.7712824E7,"PercentFDFN":200,"TargetLN":1.0E9,"LN":-8.6866522E7,"PercentLN":-8.69,"PercentKpi":0,"OverdueDebt":0,"LoanOutstandingBalance":0,"PercentOverdueDebt":0}]
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

        public static class ResultsBean implements Serializable {
            /**
             * UserName : binhpq
             * FullName : Phạm Quang Bình
             * SaleId : 2
             * SaleName : null
             * JobTitleId : TN.QHKH2
             * JobTitleName : Trưởng nhóm Quan hệ khách hàng bậc 2 (lương bậc 2)
             * DepartmentCode : 07001
             * DepartmentName : P.KHBL - VUNG TAU
             * AreaId : 070
             * AreaName : CN VUNG TAU
             * BranchId : 070
             * BranchName : Chi nhánh Vũng Tàu
             * RegionId : vung2
             * RegionName : Địa bàn 2
             * TargetFDFN : 900000.0
             * FDFN : 8516387.0
             * PercentFDFN : 200.0
             * TargetLN : 7.0E8
             * LN : -5.808041E7
             * PercentLN : -8.3
             * PercentKpi : 0.0
             * OverdueDebt : 0.0
             * LoanOutstandingBalance : 0.0
             * PercentOverdueDebt : 0.0
             */

            private String UserName;
            private String FullName;
            private int SaleId;
            private Object SaleName;
            private String JobTitleId;
            private String JobTitleName;
            private String DepartmentCode;
            private String DepartmentName;
            private String AreaId;
            private String AreaName;
            private String BranchId;
            private String BranchName;
            private String RegionId;
            private String RegionName;
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

            public int getSaleId() {
                return SaleId;
            }

            public void setSaleId(int SaleId) {
                this.SaleId = SaleId;
            }

            public Object getSaleName() {
                return SaleName;
            }

            public void setSaleName(Object SaleName) {
                this.SaleName = SaleName;
            }

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

            public String getAreaId() {
                return AreaId;
            }

            public void setAreaId(String AreaId) {
                this.AreaId = AreaId;
            }

            public String getAreaName() {
                return AreaName;
            }

            public void setAreaName(String AreaName) {
                this.AreaName = AreaName;
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

            public void setTargetFDFN(double TargetFDFN) {
                this.TargetFDFN = TargetFDFN;
            }

            public double getFDFN() {
                return FDFN;
            }

            public void setFDFN(double FDFN) {
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

            public void setTargetLN(double TargetLN) {
                this.TargetLN = TargetLN;
            }

            public double getLN() {
                return LN;
            }

            public void setLN(double LN) {
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

            public void setOverdueDebt(double OverdueDebt) {
                this.OverdueDebt = OverdueDebt;
            }

            public double getLoanOutstandingBalance() {
                return LoanOutstandingBalance;
            }

            public void setLoanOutstandingBalance(double LoanOutstandingBalance) {
                this.LoanOutstandingBalance = LoanOutstandingBalance;
            }

            public double getPercentOverdueDebt() {
                return PercentOverdueDebt;
            }

            public void setPercentOverdueDebt(double PercentOverdueDebt) {
                this.PercentOverdueDebt = PercentOverdueDebt;
            }
        }
    }
}
