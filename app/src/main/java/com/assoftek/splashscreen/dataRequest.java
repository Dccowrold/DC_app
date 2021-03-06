package com.assoftek.splashscreen;

public class dataRequest {
    private String name;
    private String email;
    private String FixedIncome;
    private String OtherIncome;
    private String MedianIncome;
    private String TotalExpenses;
    private String SavingIncome;
    private String Age;
    private String RetirementAge;
    private String AssetClass;
    private String Return;
    private String Risk;
    private String Time;
    private String FinancialRisk;
    private String Standard;
    private String RiskWillingness;
    private String Liquidity;

    public dataRequest(String name, String email, String fixedIncome, String otherIncome, String medianIncome, String totalExpenses, String savingIncome, String age, String retirementAge, String assetClass, String aReturn, String risk, String time, String financialRisk, String standard, String riskWillingness, String liquidity) {
        this.name = name;
        this.email = email;
        FixedIncome = fixedIncome;
        OtherIncome = otherIncome;
        MedianIncome = medianIncome;
        TotalExpenses = totalExpenses;
        SavingIncome = savingIncome;
        Age = age;
        RetirementAge = retirementAge;
        AssetClass = assetClass;
        Return = aReturn;
        Risk = risk;
        Time = time;
        FinancialRisk = financialRisk;
        Standard = standard;
        RiskWillingness = riskWillingness;
        Liquidity = liquidity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFixedIncome() {
        return FixedIncome;
    }

    public void setFixedIncome(String fixedIncome) {
        FixedIncome = fixedIncome;
    }

    public String getOtherIncome() {
        return OtherIncome;
    }

    public void setOtherIncome(String otherIncome) {
        OtherIncome = otherIncome;
    }

    public String getMedianIncome() {
        return MedianIncome;
    }

    public void setMedianIncome(String medianIncome) {
        MedianIncome = medianIncome;
    }

    public String getTotalExpenses() {
        return TotalExpenses;
    }

    public void setTotalExpenses(String totalExpenses) {
        TotalExpenses = totalExpenses;
    }

    public String getSavingIncome() {
        return SavingIncome;
    }

    public void setSavingIncome(String savingIncome) {
        SavingIncome = savingIncome;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getRetirementAge() {
        return RetirementAge;
    }

    public void setRetirementAge(String retirementAge) {
        RetirementAge = retirementAge;
    }

    public String getAssetClass() {
        return AssetClass;
    }

    public void setAssetClass(String assetClass) {
        AssetClass = assetClass;
    }

    public String getReturn() {
        return Return;
    }

    public void setReturn(String aReturn) {
        Return = aReturn;
    }

    public String getRisk() {
        return Risk;
    }

    public void setRisk(String risk) {
        Risk = risk;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getFinancialRisk() {
        return FinancialRisk;
    }

    public void setFinancialRisk(String financialRisk) {
        FinancialRisk = financialRisk;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }

    public String getRiskWillingness() {
        return RiskWillingness;
    }

    public void setRiskWillingness(String riskWillingness) {
        RiskWillingness = riskWillingness;
    }

    public String getLiquidity() {
        return Liquidity;
    }

    public void setLiquidity(String liquidity) {
        Liquidity = liquidity;
    }
}
