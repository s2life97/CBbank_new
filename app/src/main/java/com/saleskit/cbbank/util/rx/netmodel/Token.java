package com.saleskit.cbbank.util.rx.netmodel;

public class Token {
    /**
     * Data : {"Id":"417SG4pbtuyetdtb","FullName":"Đỗ Thị Bạch Tuyết","UserName":"tuyetdtb","Email":"tuyetdtb@cbbank.vn","LoginName":"tuyetdtb","Token":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IsSQ4buXIFRo4buLIELhuqFjaCBUdXnhur90IiwiZW1haWwiOiJ0dXlldGR0YkBjYmJhbmsudm4iLCJVc2VyTmFtZSI6InR1eWV0ZHRiIiwicHJpbWFyeXNpZCI6IjQxN1NHNHBidHV5ZXRkdGIiLCJuYmYiOjE1NzM0MzY2ODksImV4cCI6MTYwNTA1OTA4OSwiaWF0IjoxNTczNDM2Njg5fQ.dtJ3Jzux1KZC1KRqksl5NmAmz8-8MyF4uOrw_pmYMc-qmbq4XY25cJXDwKzTqqlYGU4eSP5P8YRoLaqKkR5vdw","TokenExpires":"2020-11-11T01:44:49.3231149Z"}
     * ErrorMessage : null
     * Status : true
     */
    private DataBean Data;
    private Object ErrorMessage;
    private boolean Status;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public Object getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(Object ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public static class DataBean {
        /**
         * Id : 417SG4pbtuyetdtb
         * FullName : Đỗ Thị Bạch Tuyết
         * UserName : tuyetdtb
         * Email : tuyetdtb@cbbank.vn
         * LoginName : tuyetdtb
         * Token : eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IsSQ4buXIFRo4buLIELhuqFjaCBUdXnhur90IiwiZW1haWwiOiJ0dXlldGR0YkBjYmJhbmsudm4iLCJVc2VyTmFtZSI6InR1eWV0ZHRiIiwicHJpbWFyeXNpZCI6IjQxN1NHNHBidHV5ZXRkdGIiLCJuYmYiOjE1NzM0MzY2ODksImV4cCI6MTYwNTA1OTA4OSwiaWF0IjoxNTczNDM2Njg5fQ.dtJ3Jzux1KZC1KRqksl5NmAmz8-8MyF4uOrw_pmYMc-qmbq4XY25cJXDwKzTqqlYGU4eSP5P8YRoLaqKkR5vdw
         * TokenExpires : 2020-11-11T01:44:49.3231149Z
         */
        private String Id;
        private String FullName;
        private String UserName;
        private String Email;
        private String LoginName;
        private String Token;
        private String TokenExpires;

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

        public String getLoginName() {
            return LoginName;
        }

        public void setLoginName(String LoginName) {
            this.LoginName = LoginName;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getTokenExpires() {
            return TokenExpires;
        }

        public void setTokenExpires(String TokenExpires) {
            this.TokenExpires = TokenExpires;
        }
    }
}
