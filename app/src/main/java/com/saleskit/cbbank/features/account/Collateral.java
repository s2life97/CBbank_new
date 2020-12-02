package com.saleskit.cbbank.features.account;

import java.io.Serializable;
import java.util.List;

public class Collateral {

    /**
     * data : [{"customerCollateralId":448,"customerProfileId":973,"propertyTypeId":4,"propertyTypeName":"Bất động sản","propertyTypeCode":"LTS_02","propertyFormId":3,"propertyFormName":"Hình thức loại B","propertyFormCode":"HT02","collateralValue":55,"rateOfLending":75,"description":"đii"}]
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

    public static class DataBean implements Serializable {
        /**
         * customerCollateralId : 448
         * customerProfileId : 973
         * propertyTypeId : 4
         * propertyTypeName : Bất động sản
         * propertyTypeCode : LTS_02
         * propertyFormId : 3
         * propertyFormName : Hình thức loại B
         * propertyFormCode : HT02
         * collateralValue : 55.0
         * rateOfLending : 75.0
         * description : đii
         */

        private int customerCollateralId;
        private int customerProfileId;
        private int propertyTypeId;
        private String propertyTypeName;
        private String propertyTypeCode;
        private int propertyFormId;
        private String propertyFormName;
        private String propertyFormCode;
        private double collateralValue;
        private double rateOfLending;
        private String description;

        public int getCustomerCollateralId() {
            return customerCollateralId;
        }

        public void setCustomerCollateralId(int customerCollateralId) {
            this.customerCollateralId = customerCollateralId;
        }

        public int getCustomerProfileId() {
            return customerProfileId;
        }

        public void setCustomerProfileId(int customerProfileId) {
            this.customerProfileId = customerProfileId;
        }

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

        public double getCollateralValue() {
            return collateralValue;
        }

        public void setCollateralValue(double collateralValue) {
            this.collateralValue = collateralValue;
        }

        public double getRateOfLending() {
            return rateOfLending;
        }

        public void setRateOfLending(double rateOfLending) {
            this.rateOfLending = rateOfLending;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
