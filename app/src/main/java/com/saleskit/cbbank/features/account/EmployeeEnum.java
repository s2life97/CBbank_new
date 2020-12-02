package com.saleskit.cbbank.features.account;

import java.util.List;

public class EmployeeEnum {

    /**
     * Data : [{"Id":"09001090pbthuyhtb","FullName":"Huỳnh Thị Bích Thủy","UserName":"thuyhtb","Email":"thuyhtb@cbbank.vn"}]
     * Message : null
     * Success : true
     * Error : null
     */

    private Object Message;
    private boolean Success;
    private Object Error;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 09001090pbthuyhtb
         * FullName : Huỳnh Thị Bích Thủy
         * UserName : thuyhtb
         * Email : thuyhtb@cbbank.vn
         */

        private String Id;
        private String FullName;
        private String UserName;
        private String Email;

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
