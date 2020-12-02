package com.saleskit.cbbank.features.customer.add_new_customer.model;

import java.io.Serializable;
import java.util.List;

public class Category {

    /**
     * data : {"customerTypes":[{"valueMember":1,"displayMember":"Khách hàng cá nhân"},{"valueMember":2,"displayMember":"Khách hàng doanh nghiệp"}],"genders":[{"valueMember":1,"displayMember":"Nam"},{"valueMember":2,"displayMember":"Nữ"},{"valueMember":3,"displayMember":"Chưa xác định"}],"cicResult":[{"valueMember":0,"displayMember":"Chưa kiểm tra CIC"},{"valueMember":1,"displayMember":"Không đạt"},{"valueMember":2,"displayMember":"Chưa có kết quả"},{"valueMember":3,"displayMember":"Đạt"}],"educationStatus":[{"valueMember":7,"displayMember":"Đại học"},{"valueMember":8,"displayMember":"Cao đẳng"},{"valueMember":9,"displayMember":"Trung cấp"},{"valueMember":10,"displayMember":"Dưới trung cấp"},{"valueMember":116,"displayMember":"Trên đại học"}],"assetStatus":[{"valueMember":20,"displayMember":"Ở chung với bố mẹ(trừ trường hợp bố mẹ cũng đi thuê nhà)"},{"valueMember":21,"displayMember":"Nhà đi thuê"},{"valueMember":22,"displayMember":"Nhà sở hữu riêng"},{"valueMember":23,"displayMember":"Khác"},{"valueMember":118,"displayMember":"Mua trả góp"}],"marriedStatus":[{"valueMember":11,"displayMember":"Có gia đình"},{"valueMember":12,"displayMember":"Độc thân"},{"valueMember":13,"displayMember":"Góa"},{"valueMember":14,"displayMember":"Ly thân"},{"valueMember":15,"displayMember":"Ly dị"}],"propertyType":[{"valueMember":16,"displayMember":"Giấy tờ có giá trị"},{"valueMember":17,"displayMember":"Bất động sản"},{"valueMember":18,"displayMember":"Quyền tài sản"},{"valueMember":19,"displayMember":"Phương tiện vận tải"}],"customerProfileProcess":[{"valueMember":1,"displayMember":"Đã nhập thông tin khách hàng"},{"valueMember":2,"displayMember":"Đã chấm điểm tín dụng"},{"valueMember":3,"displayMember":"Đã nhập tài sản đảm bảo"},{"valueMember":4,"displayMember":"Đã xác thực số tiền vay"},{"valueMember":5,"displayMember":"Đã hoàn thành"}],"enterpriseTypes":[{"valueMember":1,"displayMember":"DN tư nhân"},{"valueMember":2,"displayMember":"Công ty TNHH"},{"valueMember":3,"displayMember":"Công ty cổ phần"},{"valueMember":4,"displayMember":"Công ty hợp danh"},{"valueMember":5,"displayMember":"Công ty TNHH một thành viên"}]}
     * totalRecords : 0
     */

    private DataBean data;
    private int totalRecords;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public static class DataBean {
        private List<CustomerTypesBean> customerTypes;
        private List<GendersBean> genders;
        private List<CicResultBean> cicResult;
        private List<EducationStatusBean> educationStatus;
        private List<AssetStatusBean> assetStatus;
        private List<MarriedStatusBean> marriedStatus;
        private List<PropertyTypeBean> propertyType;
        private List<CustomerProfileProcessBean> customerProfileProcess;
        private List<EnterpriseTypesBean> enterpriseTypes;
        private List<AppointmentResultStatus> appointmentResultStatuses;

        public List<AppointmentResultStatus> getAppointmentResultStatuses() {
            return appointmentResultStatuses;
        }

        public void setAppointmentResultStatuses(List<AppointmentResultStatus> appointmentResultStatuses) {
            this.appointmentResultStatuses = appointmentResultStatuses;
        }

        public List<CustomerTypesBean> getCustomerTypes() {
            return customerTypes;
        }

        public void setCustomerTypes(List<CustomerTypesBean> customerTypes) {
            this.customerTypes = customerTypes;
        }

        public List<GendersBean> getGenders() {
            return genders;
        }

        public void setGenders(List<GendersBean> genders) {
            this.genders = genders;
        }

        public List<CicResultBean> getCicResult() {
            return cicResult;
        }

        public void setCicResult(List<CicResultBean> cicResult) {
            this.cicResult = cicResult;
        }

        public List<EducationStatusBean> getEducationStatus() {
            return educationStatus;
        }

        public void setEducationStatus(List<EducationStatusBean> educationStatus) {
            this.educationStatus = educationStatus;
        }

        public List<AssetStatusBean> getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(List<AssetStatusBean> assetStatus) {
            this.assetStatus = assetStatus;
        }

        public List<MarriedStatusBean> getMarriedStatus() {
            return marriedStatus;
        }

        public void setMarriedStatus(List<MarriedStatusBean> marriedStatus) {
            this.marriedStatus = marriedStatus;
        }

        public List<PropertyTypeBean> getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(List<PropertyTypeBean> propertyType) {
            this.propertyType = propertyType;
        }

        public List<CustomerProfileProcessBean> getCustomerProfileProcess() {
            return customerProfileProcess;
        }

        public void setCustomerProfileProcess(List<CustomerProfileProcessBean> customerProfileProcess) {
            this.customerProfileProcess = customerProfileProcess;
        }

        public List<EnterpriseTypesBean> getEnterpriseTypes() {
            return enterpriseTypes;
        }

        public void setEnterpriseTypes(List<EnterpriseTypesBean> enterpriseTypes) {
            this.enterpriseTypes = enterpriseTypes;
        }

        public static class CustomerTypesBean {
            /**
             * valueMember : 1
             * displayMember : Khách hàng cá nhân
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

        public static class GendersBean {
            /**
             * valueMember : 1
             * displayMember : Nam
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

        public static class CicResultBean {
            /**
             * valueMember : 0
             * displayMember : Chưa kiểm tra CIC
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

        public static class EducationStatusBean {
            /**
             * valueMember : 7
             * displayMember : Đại học
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

        public static class AssetStatusBean {
            /**
             * valueMember : 20
             * displayMember : Ở chung với bố mẹ(trừ trường hợp bố mẹ cũng đi thuê nhà)
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

        public static class MarriedStatusBean {
            /**
             * valueMember : 11
             * displayMember : Có gia đình
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

        public static class PropertyTypeBean {
            /**
             * valueMember : 16
             * displayMember : Giấy tờ có giá trị
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

        public static class CustomerProfileProcessBean {
            /**
             * valueMember : 1
             * displayMember : Đã nhập thông tin khách hàng
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

        public static class EnterpriseTypesBean {
            /**
             * valueMember : 1
             * displayMember : DN tư nhân
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

        public static class AppointmentResultStatus implements Serializable {
            private int valueMember;
            private String displayMember;

            public AppointmentResultStatus(int valueMember, String displayMember) {
                this.valueMember = valueMember;
                this.displayMember = displayMember;
            }

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