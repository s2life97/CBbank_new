package com.saleskit.cbbank.features.home;

import java.util.List;

public class BranchResponse {

    /**
     * data : [{"transactionLocationId":1,"parentId":0,"name":"Chi nhánh Hà Nội","address":"96 Bà Triệu, P.Hàng Bài, Q.Hoàn Kiếm, TP.Hà Nội","phoneNumber":"Tel: (024) 6278 0086","faxNumber":"Fax: (024) 6278 0089"},{"transactionLocationId":9,"parentId":0,"name":"CN Đà Nẵng","address":"130 Lý Thái Tổ, P.Thạc Gián, Q.Thanh Khê, TP.Đà Nẵng","phoneNumber":"","faxNumber":""},{"transactionLocationId":15,"parentId":0,"name":"CN Nha Trang","address":"Số 30, đường Quang Trung, Phường Vạn Thắng, TP. Nha Trang, tỉnh Khánh Hòa","phoneNumber":"Tel: (0258) 3820 010","faxNumber":"Fax: (0258) 3820 012"},{"transactionLocationId":16,"parentId":0,"name":"CN BÌNH THUẬN","address":"377-379 Trần Hưng Đạo, P. Bình Hưng, TP. Phan Thiết, T. Bình Thuận","phoneNumber":"Tel: (0252) 3770 377","faxNumber":"Fax: (0252) 3770 379"},{"transactionLocationId":17,"parentId":0,"name":"CN Vũng Tàu","address":"8H3 Nguyễn Thái Học, P.7, TP.Vũng Tàu, T.Bà Rịa - Vũng Tàu","phoneNumber":"Tel: (0254) 3576 906","faxNumber":"Fax: (0254) 3576 907"},{"transactionLocationId":22,"parentId":0,"name":"CN Đồng Nai","address":"253 Lô D1 Phạm Văn Thuận, P.Tân Mai, TP.Biên Hoà, T.Đồng Nai","phoneNumber":"Tel: (0251) 3918 306","faxNumber":"Fax: (0251) 3918 307"},{"transactionLocationId":26,"parentId":0,"name":"CN Bình Dương","address":" 302 Đại lộ Bình Dương, phường Phú Hòa, thành phố Thủ Dầu Một, tỉnh Bình Dương","phoneNumber":"Tel: (0274) 3819 195","faxNumber":"Fax: (0274) 3819 196"},{"transactionLocationId":27,"parentId":0,"name":"CN Sài Gòn","address":"426 Nguyễn Thị Minh Khai, P.5, Q.3, TP.HCM","phoneNumber":"Tel: (028) 3929 1295 - 3929 1290","faxNumber":"Fax: (028) 3929 1296 - 6290 9127"},{"transactionLocationId":50,"parentId":0,"name":"CN Lam Giang","address":"167-173 Trần Hưng Đạo, P. Cô Giang, Q.1, TP.HCM","phoneNumber":"Tel: (028) 3920 4652","faxNumber":"Fax: (028) 3920 4651"},{"transactionLocationId":55,"parentId":0,"name":"Hội sở","address":"145-147-149 Hùng Vương - P.2 - TP Tân An - Long An","phoneNumber":"Tel: (0272) 3524 639","faxNumber":"Fax: (0272) 3524 900"},{"transactionLocationId":56,"parentId":0,"name":"CN Long An","address":"145-147-149 Hùng Vương, P.2, TP Tân An, T.Long An","phoneNumber":"Tel: (0272) 3524 789","faxNumber":"Fax: (0272) 3524 787"}]
     * totalRecords : 11
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
        public DataBean() {
        }

        public DataBean(String name, String address, String phoneNumber, String faxNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.faxNumber = faxNumber;
        }

        /**
         * transactionLocationId : 1
         * parentId : 0
         * name : Chi nhánh Hà Nội
         * address : 96 Bà Triệu, P.Hàng Bài, Q.Hoàn Kiếm, TP.Hà Nội
         * phoneNumber : Tel: (024) 6278 0086
         * faxNumber : Fax: (024) 6278 0089
         */

        private int transactionLocationId;
        private int parentId;
        private String name;
        private String address;
        private String phoneNumber;
        private String faxNumber;

        public int getTransactionLocationId() {
            return transactionLocationId;
        }

        public void setTransactionLocationId(int transactionLocationId) {
            this.transactionLocationId = transactionLocationId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFaxNumber() {
            return faxNumber;
        }

        public void setFaxNumber(String faxNumber) {
            this.faxNumber = faxNumber;
        }
    }
}
