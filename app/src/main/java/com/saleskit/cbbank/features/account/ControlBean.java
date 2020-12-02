package com.saleskit.cbbank.features.account;

import java.io.Serializable;
import java.util.List;

public class ControlBean {

    /**
     * data : [{"description":"Tình trạng sở hữu nhà","fieldName":"AssetStatus","type":1,"selectedValue":null,"selectedDisplay":"Chưa chọn tham số","optionControlModels":[{"value":7,"display":"Đại học/trên đại học"},{"value":8,"display":"Cao đẳng"},{"value":9,"display":"Trung cấp"},{"value":10,"display":"Dưới trung cấp"}]},{"description":"Loại hình cơ quan","fieldName":"CompanyTypeId","type":1,"selectedValue":null,"selectedDisplay":"Chưa chọn tham số","optionControlModels":[{"value":1,"display":"Cơ quan Đảng, Cơ quan Nhà nước  tại địa phương"},{"value":2,"display":"Cơ quan Đảng, Cơ quan Nhà nước tại Trung Ương, Hà Nội , TP HCM (chỉ tính các quận)"},{"value":3,"display":"Đơn vị sự nghiệp công lập tại địa phương"},{"value":4,"display":"Đơn vị sự nghiệp công lập tại Trung ương, Hà Nội, TP HCM (chỉ tính các quận)"},{"value":5,"display":"Đơn vị sự nghiệp ngoài công lập tại địa phương"},{"value":6,"display":"Đơn vị sự nghiệp ngoài công lập tại Trung ương, Hà Nội, TP HCM (chỉ tính các quận)"},{"value":7,"display":"Đơn vị vũ trang nhân dân"},{"value":8,"display":"Tổ chức chính trị - xã hội - nghề nghiệp tại địa phương"},{"value":9,"display":"Tổ chức chính trị - xã hội - nghề nghiệp tại trung ương, Hà Nội, TP HCM (chỉ tính các quận)"},{"value":10,"display":"Tổng công ty/Tập đoàn kinh tế Nhà nước; Các ngân hàng thương mại, công ty bảo hiểm, công ty đa quốc gia, tổ chức phi chính phủ, Doanh nghiệp FDI"},{"value":11,"display":"Các tổ chức, doanh nghiệp, định chế tài chính khác"},{"value":12,"display":"Các trường hợp khác (bao gồm cá nhân tự kinh doanh)"}]},{"description":"Số hợp đồng chưa kết thúc","fieldName":"ContractNumberInProgress","type":2,"selectedValue":1000,"selectedDisplay":"1000","optionControlModels":[]},{"description":"Hình thức hợp đồng lao động","fieldName":"ContractTypeId","type":1,"selectedValue":null,"selectedDisplay":"Chưa chọn tham số","optionControlModels":[{"value":1,"display":"Hợp đồng không xác định thời hạn"},{"value":2,"display":"Hợp đồng lao động 1 đến 3 năm"},{"value":3,"display":"Hợp đồng lao động từ 6 tháng đến dưới 1 năm"},{"value":4,"display":"Tại doanh nghiệp do người vay sở hữu hoặc của gia đình người vay"},{"value":5,"display":"Hợp đồng lao động dưới 6 tháng hoặc không có hợp đồng"}]},{"description":"Tình hình trả gốc lãi với các tổ chức tín dụng trong 12 tháng qua","fieldName":"CreditStatus","type":1,"selectedValue":null,"selectedDisplay":"Chưa chọn tham số","optionControlModels":[]},{"description":"Ngày sinh","fieldName":"DateOfBirth","type":2,"selectedValue":24,"selectedDisplay":"24","optionControlModels":[]},{"description":"Trình độ học vấn","fieldName":"EducationStatus","type":1,"selectedValue":null,"selectedDisplay":"Chưa chọn tham số","optionControlModels":[{"value":60,"display":"Dưới 1 năm"}]},{"description":"Tình trạng hộn nhân","fieldName":"MarriedStatus","type":1,"selectedValue":null,"selectedDisplay":"Chưa chọn giá trị","optionControlModels":[]},{"description":"Tỷ lệ trả nợ","fieldName":"RateOfDebt","type":2,"selectedValue":0,"selectedDisplay":"0","optionControlModels":[]},{"description":"Vùng miền","fieldName":"ReligionId","type":1,"selectedValue":1,"selectedDisplay":"Hà Nội","optionControlModels":[{"value":1,"display":"Hà Nội"},{"value":6,"display":"Hồ Chí Minh"},{"value":7,"display":"Tây Nam Bộ"},{"value":8,"display":"Bắc Bộ"},{"value":9,"display":"Miền Trung - Tây Nguyên"},{"value":12,"display":"Đông Nam Bộ"},{"value":13,"display":"Trung du"}]},{"description":"Hình thức thanh toán lương hoặc thu nhập khác","fieldName":"SalaryPaymentMethod","type":1,"selectedValue":null,"selectedDisplay":"Chưa chọn tham số","optionControlModels":[]}]
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
         * description : Tình trạng sở hữu nhà
         * fieldName : AssetStatus
         * type : 1
         * selectedValue : null
         * selectedDisplay : Chưa chọn tham số
         * optionControlModels : [{"value":7,"display":"Đại học/trên đại học"},{"value":8,"display":"Cao đẳng"},{"value":9,"display":"Trung cấp"},{"value":10,"display":"Dưới trung cấp"}]
         */

        private String description;
        private String fieldName;
        private int type;
        private String selectedValue;
        private String selectedDisplay;
        private boolean canEdit;
        private List<OptionControlModelsBean> optionControlModels;

        public boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSelectedValue() {
            return selectedValue;
        }

        public void setSelectedValue(String selectedValue) {
            this.selectedValue = selectedValue;
        }

        public String getSelectedDisplay() {
            return selectedDisplay;
        }

        public void setSelectedDisplay(String selectedDisplay) {
            this.selectedDisplay = selectedDisplay;
        }

        public List<OptionControlModelsBean> getOptionControlModels() {
            return optionControlModels;
        }

        public void setOptionControlModels(List<OptionControlModelsBean> optionControlModels) {
            this.optionControlModels = optionControlModels;
        }

        public static class OptionControlModelsBean implements Serializable {
            /**
             * value : 7
             * display : Đại học/trên đại học
             */

            private int valueMember;
            private String displayMember;

            public int getValueMember() {
                return valueMember;
            }

            public void setValueMember(int valueMember) {
                this.valueMember = valueMember;
            }

            public String getDisplayMember() {
                return displayMember;
            }

            public void setDisplayMember(String displayMember) {
                this.displayMember = displayMember;
            }
        }
    }
}
