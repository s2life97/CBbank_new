package com.saleskit.cbbank.features.account;

public class CollateralInsert {
    /**
     * CustomerCollateralId : 0
     * CustomerProfileId : 0
     * PropertyTypeId : 0
     * PropertyFormId : 0
     * CollateralValue : 0
     * RateOfLending : 0
     * Description : string
     */

    private int CustomerCollateralId;
    private String CustomerProfileId;
    private int PropertyTypeId;
    private String PropertyFormId;
    private String CollateralValue;
    private String RateOfLending;
    private String Description;

    public CollateralInsert(int customerCollateralId, String customerProfileId,
                            int propertyTypeId, String propertyFormId,
                            String collateralValue, String rateOfLending, String description) {
        CustomerCollateralId = customerCollateralId;
        CustomerProfileId = customerProfileId;
        PropertyTypeId = propertyTypeId;
        PropertyFormId = propertyFormId;
        CollateralValue = collateralValue;
        RateOfLending = rateOfLending;
        Description = description;
    }

    public int getCustomerCollateralId() {
        return CustomerCollateralId;
    }

    public void setCustomerCollateralId(int CustomerCollateralId) {
        this.CustomerCollateralId = CustomerCollateralId;
    }

    public String getCustomerProfileId() {
        return CustomerProfileId;
    }

    public void setCustomerProfileId(String CustomerProfileId) {
        this.CustomerProfileId = CustomerProfileId;
    }

    public int getPropertyTypeId() {
        return PropertyTypeId;
    }

    public void setPropertyTypeId(int PropertyTypeId) {
        this.PropertyTypeId = PropertyTypeId;
    }

    public String getPropertyFormId() {
        return PropertyFormId;
    }

    public void setPropertyFormId(String PropertyFormId) {
        this.PropertyFormId = PropertyFormId;
    }

    public String getCollateralValue() {
        return CollateralValue;
    }

    public void setCollateralValue(String CollateralValue) {
        this.CollateralValue = CollateralValue;
    }

    public String getRateOfLending() {
        return RateOfLending;
    }

    public void setRateOfLending(String RateOfLending) {
        this.RateOfLending = RateOfLending;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }


    /**
     * CustomerCollateralId : 0
     * CustomerProfileId : 0
     * PropertyTypeId : 0
     * PropertyFormId : 0
     * CollateralValue : 0
     * RateOfLending : 0
     * Description : string
     */


}
