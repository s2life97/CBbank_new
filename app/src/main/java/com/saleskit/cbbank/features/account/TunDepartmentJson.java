package com.saleskit.cbbank.features.account;

import java.util.List;

public class TunDepartmentJson {

    /**
     * Data : {"Results":[{"Id":"074","Code":"074","Name":"PGD TAN THANH.","BranchId":"070"},{"Id":"072","Code":"072","Name":"PGD SAO MAI","BranchId":"070"},{"Id":"073","Code":"073","Name":"PGD BACH HO","BranchId":"070"},{"Id":"071","Code":"071","Name":"PGD BA RIA","BranchId":"070"},{"Id":"07001","Code":"07001","Name":"P.KHBL - VUNG TAU","BranchId":"070"},{"Id":"07002","Code":"07002","Name":"P.DVKH - VUNG TAU","BranchId":"070"},{"Id":"070","Code":"070","Name":"BGÐ - CN VUNG TAU","BranchId":"070"}],"CurrentPage":1,"PageCount":1,"PageSize":10,"RowCount":7,"FirstRowOnPage":1,"LastRowOnPage":7}
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
         * Results : [{"Id":"074","Code":"074","Name":"PGD TAN THANH.","BranchId":"070"},{"Id":"072","Code":"072","Name":"PGD SAO MAI","BranchId":"070"},{"Id":"073","Code":"073","Name":"PGD BACH HO","BranchId":"070"},{"Id":"071","Code":"071","Name":"PGD BA RIA","BranchId":"070"},{"Id":"07001","Code":"07001","Name":"P.KHBL - VUNG TAU","BranchId":"070"},{"Id":"07002","Code":"07002","Name":"P.DVKH - VUNG TAU","BranchId":"070"},{"Id":"070","Code":"070","Name":"BGÐ - CN VUNG TAU","BranchId":"070"}]
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
             * Id : 074
             * Code : 074
             * Name : PGD TAN THANH.
             * BranchId : 070
             */

            private String Id;
            private String Code;
            private String Name;
            private String BranchId;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getBranchId() {
                return BranchId;
            }

            public void setBranchId(String BranchId) {
                this.BranchId = BranchId;
            }
        }
    }
}
