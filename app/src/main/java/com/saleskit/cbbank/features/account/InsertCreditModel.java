package com.saleskit.cbbank.features.account;

public class InsertCreditModel {

    /**
     * ProductId : 1
     * CustomerId : 2
     */

    private String ProductId;
    private String CustomerId;

    public InsertCreditModel(String productId, String customerId) {
        ProductId = productId;
        CustomerId = customerId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }
}
