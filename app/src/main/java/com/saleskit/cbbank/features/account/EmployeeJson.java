package com.saleskit.cbbank.features.account;

import java.util.List;

public class EmployeeJson {

    /**
     * Data : {"Results":[{"Id":"074070pb201809201909kimtt","FullName":"Trương Thị Kim","UserName":"kimtt","Email":"kimtt@cbbank.vn"},{"Id":"074070pb201809201903vietta","FullName":"Trần Anh Việt","UserName":"vietta","Email":"vietta@cbbank.vn"},{"Id":"074070pb201809201906TUONGNV1","FullName":"Nguyễn Văn Tưởng","UserName":"TUONGNV1","Email":"TUONGNV1@cbbank.vn"},{"Id":"074070pbhuongnt","FullName":"Nguyễn Thị Hương","UserName":"huongnt","Email":"huongnt@cbbank.vn"},{"Id":"074070pbquykh","FullName":"Kim Hữu Quý","UserName":"quykh","Email":"quykh@cbbank.vn"},{"Id":"074070pbsinhht","FullName":"Huỳnh Thị Sinh","UserName":"sinhht","Email":"sinhht@cbbank.vn"},{"Id":"074070pbthaibq","FullName":"Bùi Quốc Thái","UserName":"thaibq","Email":"thaibq@cbbank.vn"}],"CurrentPage":1,"PageCount":1,"PageSize":10,"RowCount":7,"FirstRowOnPage":1,"LastRowOnPage":7}
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
         * Results : [{"Id":"074070pb201809201909kimtt","FullName":"Trương Thị Kim","UserName":"kimtt","Email":"kimtt@cbbank.vn"},{"Id":"074070pb201809201903vietta","FullName":"Trần Anh Việt","UserName":"vietta","Email":"vietta@cbbank.vn"},{"Id":"074070pb201809201906TUONGNV1","FullName":"Nguyễn Văn Tưởng","UserName":"TUONGNV1","Email":"TUONGNV1@cbbank.vn"},{"Id":"074070pbhuongnt","FullName":"Nguyễn Thị Hương","UserName":"huongnt","Email":"huongnt@cbbank.vn"},{"Id":"074070pbquykh","FullName":"Kim Hữu Quý","UserName":"quykh","Email":"quykh@cbbank.vn"},{"Id":"074070pbsinhht","FullName":"Huỳnh Thị Sinh","UserName":"sinhht","Email":"sinhht@cbbank.vn"},{"Id":"074070pbthaibq","FullName":"Bùi Quốc Thái","UserName":"thaibq","Email":"thaibq@cbbank.vn"}]
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
             * Id : 074070pb201809201909kimtt
             * FullName : Trương Thị Kim
             * UserName : kimtt
             * Email : kimtt@cbbank.vn
             */

            private String Id;
            private String FullName;
            private String PositionTitleName;
            private String UserName;
            private String Email;

            public String getPositionTitleName() {
                return PositionTitleName;
            }

            public void setPositionTitleName(String positionTitleName) {
                PositionTitleName = positionTitleName;
            }

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String FullName) {
                this.FullName = FullName;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String Email) {
                this.Email = Email;
            }
        }
    }
}
