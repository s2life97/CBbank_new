package com.saleskit.cbbank.features.account;

import java.util.List;

public class KPIJson {

    /**
     * Data : [{"Id":"010vung3cn201901","Code":"010","Name":"Chi nhánh Rạch Kiến"},{"Id":"010vung4cn201809","Code":"010","Name":"Chi nhánh Rạch Kiến"},{"Id":"020vung3cn201901","Code":"020","Name":"Chi nhánh Long An"},{"Id":"020vung4cn201809","Code":"020","Name":"Chi nhánh Long An"},{"Id":"030vung1cn201809","Code":"030","Name":"Chi nhánh Hà Nội"},{"Id":"040vung1cn201809","Code":"040","Name":"Chi nhánh Sài Gòn"},{"Id":"050vung2cn201809","Code":"050","Name":"Chi nhánh Cần Thơ"},{"Id":"060vung2cn201809","Code":"060","Name":"Chi nhánh Đà Nẵng"},{"Id":"070vung2cn201809","Code":"070","Name":"Chi nhánh Vũng Tàu"},{"Id":"080vung2cn201809","Code":"080","Name":"Chi nhánh Đồng Nai"},{"Id":"090vung3cn201809","Code":"090","Name":"Chi nhánh Tiền Giang"},{"Id":"200vung3cn201809","Code":"200","Name":"Chi nhánh An Giang"},{"Id":"300vung4cn201809","Code":"300","Name":"Chi nhánh Bến Tre"},{"Id":"310vung2cn201901","Code":"310","Name":"Chi nhánh Bình Thuận"},{"Id":"310vung3cn201809","Code":"310","Name":"Chi nhánh Bình Thuận"},{"Id":"400vung4cn201809","Code":"400","Name":"Chi nhánh Vĩnh Long"},{"Id":"500vung3cn201809","Code":"500","Name":"Chi nhánh Đồng Tháp"},{"Id":"520vung3cn201809","Code":"520","Name":"Chi nhánh Hậu Giang"},{"Id":"540vung2cn201809","Code":"540","Name":"Chi nhánh Bình Dương"},{"Id":"560vung2cn201809","Code":"560","Name":"Chi nhánh Nha Trang"},{"Id":"600vung3cn201809","Code":"600","Name":"Chi nhánh Kiên Giang"},{"Id":"620vung1cn201809","Code":"620","Name":"Chi nhánh Lam Giang"},{"Id":"700vung4cn201809","Code":"700","Name":"Chi nhánh Trà Vinh"},{"Id":"800NULLcn201810","Code":"800","Name":"Chi nhánh Long Hiệp"},{"Id":"800vung4cn201809","Code":"800","Name":"Chi nhánh Long Hiệp"},{"Id":"900vung3cn201809","Code":"900","Name":"Chi nhánh Cà Mau"}]
     * ErrorMessage : null
     * Status : true
     */

    private Object ErrorMessage;
    private boolean Status;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 010vung3cn201901
         * Code : 010
         * Name : Chi nhánh Rạch Kiến
         */

        private String Id;
        private String Code;
        private String Name;
        private String jobTitle;
        private double percentKPI;
        private boolean isShow;

        public DataBean(String name, String id, String jobTitle, double percentKPI, boolean isShow) {
            Name = name;
            Id = id;
            this.jobTitle = jobTitle;
            this.percentKPI = percentKPI;
            this.isShow = isShow;
        }

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public DataBean(String name, String jobTitle, String id) {
            Name = name;
            this.jobTitle = jobTitle;
            this.Id = id;
        }
        public DataBean(String name, String jobTitle, double percentKPI, boolean isShow) {
            Name = name;
            this.jobTitle = jobTitle;
            this.percentKPI = percentKPI;
            this.isShow = isShow;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

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

        public double getPercentKPI() {
            return percentKPI;
        }

        public void setPercentKPI(double percentKPI) {
            this.percentKPI = percentKPI;
        }
    }
}
