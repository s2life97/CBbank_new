package com.saleskit.cbbank.features.customer.add_new_customer.model;

public class CreaditEvaluationInsert {

    /**
     * CustomerId : 0
     * ProductId : 0
     * CompanyTypeId : 0
     * ContractTypeId : 0
     * RegionId : 0
     * RateOfDebt : 0
     * ContractNumberInProgress : 0
     * AssetStatus : 0
     * SectorDevelopment : 0
     * Partnerships : 0
     * AnnualGrowthPercentage : 0
     * LoanMoney : 0
     * EducationStatus : 0
     * SalaryPaymentMethod : 0
     * CreditStatus : 0
     * HasPassbook : 0
     * ExperienceYear : 0
     * MonthOfUsingBanking : 0
     */
    private String CustomerProfileId;
    private String CustomerId;
    private int ProductId;
    private String CompanyTypeId;
    private String ContractTypeId;
    private String RegionId;
    private String RateOfDebt;
    private String ContractNumberInProgress;
    private int AssetStatus;
    private String SectorDevelopment;
    private String Partnerships;
    private String AnnualGrowthPercentage;
    private String LoanMoney;
    private int EducationStatus;
    private String SalaryPaymentMethod;
    private String CreditStatus;
    private String HasPassbook;
    private String ExperienceYear;
    private String MonthOfUsingBanking;

    public CreaditEvaluationInsert(String customerProfileId, String customerId, int productId, String companyTypeId, String contractTypeId,
                                   String regionId, String rateOfDebt, String contractNumberInProgress, int assetStatus,
                                   String sectorDevelopment, String partnerships, String annualGrowthPercentage, String loanMoney,
                                   int educationStatus, String salaryPaymentMethod, String creditStatus, String hasPassbook,
                                   String experienceYear, String monthOfUsingBanking) {
        CustomerProfileId = customerProfileId;
        CustomerId = customerId;
        ProductId = productId;
        CompanyTypeId = companyTypeId;
        ContractTypeId = contractTypeId;
        RegionId = regionId;
        RateOfDebt = rateOfDebt;
        ContractNumberInProgress = contractNumberInProgress;
        AssetStatus = assetStatus;
        SectorDevelopment = sectorDevelopment;
        Partnerships = partnerships;
        AnnualGrowthPercentage = annualGrowthPercentage;
        LoanMoney = loanMoney;
        EducationStatus = educationStatus;
        SalaryPaymentMethod = salaryPaymentMethod;
        CreditStatus = creditStatus;
        HasPassbook = hasPassbook;
        ExperienceYear = experienceYear;
        MonthOfUsingBanking = monthOfUsingBanking;
    }


}
