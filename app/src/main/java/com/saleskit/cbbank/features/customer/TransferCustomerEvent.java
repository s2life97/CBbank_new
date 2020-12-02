package com.saleskit.cbbank.features.customer;

import com.saleskit.cbbank.features.personal.CustomerInfo;

public class TransferCustomerEvent {
    public CustomerInfo.DataBean customer;
    public int productId;
    public int customerProfileId;
    public int process;
    public boolean canEdit;
    public boolean hasFormular;
    public int typeProduct;
    public int customerType;

    public TransferCustomerEvent(CustomerInfo.DataBean customer, int productId, int customerProfileId,
                                 int process, boolean canEdit, boolean hasFormular, int typeProduct, int customerType) {
        this.customer = customer;
        this.productId = productId;
        this.customerProfileId = customerProfileId;
        this.process = process;
        this.canEdit = canEdit;
        this.hasFormular = hasFormular;
        this.typeProduct = typeProduct;
        this.customerType = customerType;
    }

    public TransferCustomerEvent(CustomerInfo.DataBean customer, int productId, int customerProfileId) {
        this.customer = customer;
        this.productId = productId;
        this.customerProfileId = customerProfileId;
    }

    public TransferCustomerEvent(CustomerInfo.DataBean customer, int productId, int customerProfileId,
                                 int process, boolean canEdit, boolean hasFormular) {
        this.customer = customer;
        this.productId = productId;
        this.customerProfileId = customerProfileId;
        this.process = process;
        this.canEdit = canEdit;
        this.hasFormular = hasFormular;
    }

    public TransferCustomerEvent(CustomerInfo.DataBean customer, int productId,
                                 int customerProfileId, int process, boolean canEdit, boolean hasFormular, int typeProduct) {
        this.customer = customer;
        this.productId = productId;
        this.customerProfileId = customerProfileId;
        this.process = process;
        this.canEdit = canEdit;
        this.hasFormular = hasFormular;
        this.typeProduct = typeProduct;
    }
}
