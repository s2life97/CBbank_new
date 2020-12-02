package com.saleskit.cbbank.features.home;

import java.util.List;

public class AtmResponse {

    /**
     * data : [{"atmLocationId":1,"name":"CN Hà Nội","address":"96 Bà Triệu, P.Hàng Bài, Q.Hoàn Kiếm, TP.Hà Nội"},{"atmLocationId":2,"name":"CN Đà Nẵng","address":"130 Lý Thái Tổ, P.Thạc Gián, Q.Thanh Khê, TP.Đà Nẵng"},{"atmLocationId":3,"name":"CN Nha Trang","address":"30 Quang Trung, Phường Vạn Thắng, TP. Nha Trang, Tỉnh Khánh Hòa"},{"atmLocationId":4,"name":"CN Bình Thuận","address":"377-379 Trần Hưng Đạo, P.Bình Hưng, Tp.Phan Thiết, Tỉnh Bình Thuận"},{"atmLocationId":5,"name":"CN Đồng Nai","address":" 253, Lô D1, Phạm Văn Thuận, P.Tân Mai, TP.Biên Hoà, T.Đồng Nai"},{"atmLocationId":6,"name":"CN Bình Dương","address":"A1/K1 (số mới 422) Đại lộ Bình Dương, Phường Hiệp Thành, Thị xã Thủ Dầu Một, Tỉnh Bình Dương"},{"atmLocationId":7,"name":"CN Vũng Tàu","address":"8H3 Nguyễn Thái Học, P.7, TP.Vũng Tàu, T.Bà Rịa - Vũng Tàu"},{"atmLocationId":8,"name":"CN Sài Gòn","address":"426 Nguyễn Thị Minh Khai, P.5, Q.3, TP.HCM"},{"atmLocationId":9,"name":"PGD Lý Tự Trọng","address":"Số 10 Lý Tự Trọng, P.Bến Nghé, Q.1, TP.HCM"},{"atmLocationId":10,"name":"CN Lam Giang","address":"167-169-171-173 Trần Hưng Đạo, P. Cô Giang, Quận 1"},{"atmLocationId":11,"name":"PGD Thủ Thừa","address":"Số 1/55 Ấp Cầu Xây, Thị trấn Thủ Thừa, H.Thủ Thừa, T.Long An"},{"atmLocationId":12,"name":"CN Hậu Giang","address":"36 Đường 30/4, Khu vực 2, P.1, TP. Vị Thanh, Tỉnh Hậu Giang"},{"atmLocationId":13,"name":"CN Long An","address":"145-147-149 Hùng Vương, P.2, TP Tân An, T.Long An"},{"atmLocationId":14,"name":"CN Rạng Kiến","address":"01 Thị Tứ Long Hoà, H. Cần Đước, Long An"},{"atmLocationId":15,"name":"CN Bến Tre","address":"37 Cách Mạng Tháng 8, P.3, Thành phố Bến Tre, tỉnh Bến Tre"},{"atmLocationId":16,"name":"CN Vĩnh Long","address":"Số 66 - đường Phạm Thái Bường, phường 4, Thành phố Vĩnh Long, Tỉnh Vĩnh Long"},{"atmLocationId":17,"name":"PGD Hùng Vương","address":"01-03 Lý Tự Trọng, Phường 2, Tp Cao lãnh, Tỉnh Đồng Tháp"},{"atmLocationId":18,"name":"CN Trà Vinh","address":"403 Nguyễn Đáng, Khóm 10, Phường 9, TP Trà Vinh, Tỉnh Trà Vinh"},{"atmLocationId":19,"name":"CN An Giang","address":"Số 20 Phan Văn Ràng, P. Châu Phú B, TP. Châu Đốc, tỉnh An Giang"},{"atmLocationId":20,"name":"CN Kiên Giang","address":"117 Trần Phú, Phường Vĩnh Thanh, Tp Rạch Giá, Tỉnh Kiên Giang"},{"atmLocationId":21,"name":"CN Tiền Giang","address":"416/1A Trần Hưng Đạo, Phường 4, Tp Mỹ Tho, Tỉnh Tiền Giang"},{"atmLocationId":21,"name":"CN Cần Thơ","address":"228.1C-228/1Đ Trần Hưng Đạo, P. An Nghiệp, Q. Ninh Kiều, Tp Cần Thơ"},{"atmLocationId":21,"name":"CN Cà Mau","address":"30A Trần Hưng Đạo, P.5, TP.Cà Mau, Tỉnh Cà Mau"}]
     * totalRecords : 23
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
         * atmLocationId : 1
         * name : CN Hà Nội
         * address : 96 Bà Triệu, P.Hàng Bài, Q.Hoàn Kiếm, TP.Hà Nội
         */

        private int atmLocationId;
        private String name;
        private String address;

        public int getAtmLocationId() {
            return atmLocationId;
        }

        public void setAtmLocationId(int atmLocationId) {
            this.atmLocationId = atmLocationId;
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
    }
}
