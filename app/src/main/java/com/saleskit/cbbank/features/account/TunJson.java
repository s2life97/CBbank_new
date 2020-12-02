package com.saleskit.cbbank.features.account;

import java.util.List;

public class TunJson {

    /**
     * Data : {"Results":[{"Id":"070","Code":"070","Name":"Chi nhánh Vũng Tàu","RegionId":"MDNB"},{"Id":"080","Code":"080","Name":"Chi nhánh Đồng Nai","RegionId":"MDNB"},{"Id":"900","Code":"900","Name":"Chi nhánh Cà Mau","RegionId":"MDNB"},{"Id":"310","Code":"310","Name":"Chi nhánh Bình Thuận","RegionId":"MDNB"},{"Id":"540","Code":"540","Name":"Chi nhánh Bình Dương","RegionId":"MDNB"}],"CurrentPage":1,"PageCount":1,"PageSize":10,"RowCount":5,"FirstRowOnPage":1,"LastRowOnPage":5}
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
         * Results : [{"Id":"070","Code":"070","Name":"Chi nhánh Vũng Tàu","RegionId":"MDNB"},{"Id":"080","Code":"080","Name":"Chi nhánh Đồng Nai","RegionId":"MDNB"},{"Id":"900","Code":"900","Name":"Chi nhánh Cà Mau","RegionId":"MDNB"},{"Id":"310","Code":"310","Name":"Chi nhánh Bình Thuận","RegionId":"MDNB"},{"Id":"540","Code":"540","Name":"Chi nhánh Bình Dương","RegionId":"MDNB"}]
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
             * Id : 070
             * Code : 070
             * Name : Chi nhánh Vũng Tàu
             * RegionId : MDNB
             */

            private String Id;
            private String Code;
            private String Name;
            private String RegionId;

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

            public String getRegionId() {
                return RegionId;
            }

            public void setRegionId(String RegionId) {
                this.RegionId = RegionId;
            }
        }
    }
}
