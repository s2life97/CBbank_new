package com.saleskit.cbbank.features.account;

import java.util.List;

public class PropertyType {

    /**
     * data : [{"propertyTypeId":4,"propertyTypeName":"Bất động sản","propertyTypeCode":"LTS_02","description":"5656"},{"propertyTypeId":5,"propertyTypeName":"TTK/HĐTG","propertyTypeCode":"LTS_01","description":"Thẻ tiết kiệm/Hợp đồng tiền gửi"},{"propertyTypeId":7,"propertyTypeName":"TSHTTVV","propertyTypeCode":"LTS_04","description":"Tài sản hình thành từ vốn vay"}]
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
         * propertyTypeId : 4
         * propertyTypeName : Bất động sản
         * propertyTypeCode : LTS_02
         * description : 5656
         */

        private int propertyTypeId;
        private String propertyTypeName;
        private String propertyTypeCode;
        private String description;

        public int getPropertyTypeId() {
            return propertyTypeId;
        }

        public void setPropertyTypeId(int propertyTypeId) {
            this.propertyTypeId = propertyTypeId;
        }

        public String getPropertyTypeName() {
            return propertyTypeName;
        }

        public void setPropertyTypeName(String propertyTypeName) {
            this.propertyTypeName = propertyTypeName;
        }

        public String getPropertyTypeCode() {
            return propertyTypeCode;
        }

        public void setPropertyTypeCode(String propertyTypeCode) {
            this.propertyTypeCode = propertyTypeCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
