package com.saleskit.cbbank.features.account;

import java.util.List;

public class CheckEvaluadate {

    /**
     * CustomerId : 1
     * ProductId : 1
     * CustomerProfileId : 1
     * ScoringDetailModels : [{"FieldName":"string","Value":0}]
     */

    private String CustomerId;
    private String ProductId;
    private String CustomerProfileId;
    private List<ScoringDetailModelsBean> ScoringDetailModels;

    public CheckEvaluadate(String customerId, String productId, String customerProfileId, List<ScoringDetailModelsBean> scoringDetailModels) {
        CustomerId = customerId;
        ProductId = productId;
        CustomerProfileId = customerProfileId;
        ScoringDetailModels = scoringDetailModels;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getCustomerProfileId() {
        return CustomerProfileId;
    }

    public void setCustomerProfileId(String CustomerProfileId) {
        this.CustomerProfileId = CustomerProfileId;
    }

    public List<ScoringDetailModelsBean> getScoringDetailModels() {
        return ScoringDetailModels;
    }

    public void setScoringDetailModels(List<ScoringDetailModelsBean> ScoringDetailModels) {
        this.ScoringDetailModels = ScoringDetailModels;
    }

    public static class ScoringDetailModelsBean {
        public ScoringDetailModelsBean(String fieldName, String value, String description) {
            FieldName = fieldName;
            Value = value;
            Description = description;

        }

        /**
         * FieldName : string
         * Value : 0
         */

        private String FieldName;
        private String Value;
        private String Description;

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            this.Description = description;
        }

        public String getFieldName() {
            return FieldName;
        }

        public void setFieldName(String FieldName) {
            this.FieldName = FieldName;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }
}
