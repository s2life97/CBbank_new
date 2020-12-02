package com.saleskit.cbbank.features.appointment;

import java.util.List;

public class Branch {

    /**
     * Data : [{"Id":"070","Code":"070","Name":"Chi nhánh Vũng Tàu","RegionId":"MDNB"},{"Id":"080","Code":"080","Name":"Chi nhánh Đồng Nai","RegionId":"MDNB"},{"Id":"310","Code":"310","Name":"Chi nhánh Bình Thuận","RegionId":"MDNB"},{"Id":"540","Code":"540","Name":"Chi nhánh Bình Dương","RegionId":"MDNB"},{"Id":"900","Code":"900","Name":"Chi nhánh Cà Mau","RegionId":"MDNB"}]
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
