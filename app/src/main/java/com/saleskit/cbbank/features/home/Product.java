package com.saleskit.cbbank.features.home;

import java.util.List;

public class Product {

    /**
     * data : [{"productId":1,"productName":"Bất động sản","productCode":"SP1","avatar":"/Files/Upload/Product/icon1.png","productType":1,"productTypeName":"Cho vay"},{"productId":5,"productName":"Nhà dự án","productCode":"SP4","avatar":"/Files/Upload/Product/icon4.png","productType":2,"productTypeName":"Huy động"},{"productId":2,"productName":"Ô tô","productCode":"SP2","avatar":"/Files/Upload/Product/icon2.png","productType":2,"productTypeName":"Huy động"},{"productId":6,"productName":"Sản xuất kinh doah SMEs","productCode":"SP5","avatar":"/Files/Upload/Product/icon5.png","productType":2,"productTypeName":"Huy động"},{"productId":7,"productName":"Sản xuất kinh doanh","productCode":"SP6","avatar":"/Files/Upload/Product/icon6.png","productType":2,"productTypeName":"Huy động"},{"productId":4,"productName":"Tín dụng bất động sản","productCode":"SP3","avatar":"/Files/Upload/Product/icon3.png","productType":2,"productTypeName":"Huy động"}]
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
         * productId : 1
         * productName : Bất động sản
         * productCode : SP1
         * avatar : /Files/Upload/Product/icon1.png
         * productType : 1
         * productTypeName : Cho vay
         */

        private int productId;
        private String productName;
        private String productCode;
        private String avatar;
        private int productType;
        private String productTypeName;
        private int customerType;
        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public String getProductTypeName() {
            return productTypeName;
        }

        public void setProductTypeName(String productTypeName) {
            this.productTypeName = productTypeName;
        }
    }
}
