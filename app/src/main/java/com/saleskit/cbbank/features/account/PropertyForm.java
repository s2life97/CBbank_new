package com.saleskit.cbbank.features.account;

import java.util.List;

public class PropertyForm {

    /**
     * data : [{"propertyFormId":2,"propertyFormName":"Hình thức loại A","propertyFormCode":"HT01","description":"Là những tài sản mới 97% trở lên","ratio":98}]
     * totalRecords : 0
     */

    private int totalRecords;
    private List<DataBean> data;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * propertyFormId : 2
         * propertyFormName : Hình thức loại A
         * propertyFormCode : HT01
         * description : Là những tài sản mới 97% trở lên
         * ratio : 98.0
         */

        private int propertyFormId;
        private String propertyFormName;
        private String propertyFormCode;
        private String description;
        private double ratio;

        public int getPropertyFormId() {
            return propertyFormId;
        }

        public void setPropertyFormId(int propertyFormId) {
            this.propertyFormId = propertyFormId;
        }

        public String getPropertyFormName() {
            return propertyFormName;
        }

        public void setPropertyFormName(String propertyFormName) {
            this.propertyFormName = propertyFormName;
        }

        public String getPropertyFormCode() {
            return propertyFormCode;
        }

        public void setPropertyFormCode(String propertyFormCode) {
            this.propertyFormCode = propertyFormCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getRatio() {
            return ratio;
        }

        public void setRatio(double ratio) {
            this.ratio = ratio;
        }
    }
}
