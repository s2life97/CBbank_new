package com.saleskit.cbbank.util.rx.netmodel;

import java.util.List;

public class FilterModel {


    public FilterModel(int pageIndex, int pageSize, Object orderBy, List<FilterModelsBean> filterModels) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.filterModels = filterModels;
    }

    /**
     * pageIndex : 1
     * pageSize : 5
     * orderBy : null
     * filterModels : [{"FilterType":"0","ColumnName":"FirstName","Value":""}]
     */

    private int pageIndex;
    private int pageSize;
    private Object orderBy;
    private List<FilterModelsBean> filterModels;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Object orderBy) {
        this.orderBy = orderBy;
    }

    public List<FilterModelsBean> getFilterModels() {
        return filterModels;
    }

    public void setFilterModels(List<FilterModelsBean> filterModels) {
        this.filterModels = filterModels;
    }

    public static class FilterModelsBean {
        public FilterModelsBean(String filterType, String columnName, String value) {
            FilterType = filterType;
            ColumnName = columnName;
            Value = value;
        }

        /**
         * FilterType : 0
         * ColumnName : FirstName
         * Value :
         */

        private String FilterType;
        private String ColumnName;
        private String Value;

        public String getFilterType() {
            return FilterType;
        }

        public void setFilterType(String FilterType) {
            this.FilterType = FilterType;
        }

        public String getColumnName() {
            return ColumnName;
        }

        public void setColumnName(String ColumnName) {
            this.ColumnName = ColumnName;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }
}
