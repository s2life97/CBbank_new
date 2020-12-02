package com.saleskit.cbbank.features.account;

import java.util.List;

public class MobilizeEnum {

    /**
     * data : {"cbBankStructures":[{"valueMember":2,"displayMember":"ABC"},{"valueMember":4,"displayMember":"hhhhhhhhhhhh"},{"valueMember":5,"displayMember":"hhhhhhhhh"},{"valueMember":8,"displayMember":"tudt"}],"departments":[{"valueCategory":2,"valueMember":1,"displayMember":"Bộ phận hành chính (Đã sửa)"},{"valueCategory":2,"valueMember":4,"displayMember":"dddddddd"},{"valueCategory":2,"valueMember":7,"displayMember":"tudt"},{"valueCategory":2,"valueMember":8,"displayMember":"hjghjghj"},{"valueCategory":2,"valueMember":9,"displayMember":"hiyuiyui"},{"valueCategory":8,"valueMember":11,"displayMember":"ad"}]}
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
        private List<CbBankStructuresBean> cbBankStructures;
        private List<DepartmentsBean> departments;

        public List<CbBankStructuresBean> getCbBankStructures() {
            return cbBankStructures;
        }

        public void setCbBankStructures(List<CbBankStructuresBean> cbBankStructures) {
            this.cbBankStructures = cbBankStructures;
        }

        public List<DepartmentsBean> getDepartments() {
            return departments;
        }

        public void setDepartments(List<DepartmentsBean> departments) {
            this.departments = departments;
        }

        public static class CbBankStructuresBean {
            /**
             * valueMember : 2
             * displayMember : ABC
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

        public static class DepartmentsBean {
            /**
             * valueCategory : 2
             * valueMember : 1
             * displayMember : Bộ phận hành chính (Đã sửa)
             */

            private int valueCategory;
            private int valueMember;
            private String displayMember;

            public int getValueCategory() {
                return valueCategory;
            }

            public void setValueCategory(int valueCategory) {
                this.valueCategory = valueCategory;
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
