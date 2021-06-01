package com.assoftek.splashscreen;

public class dataResponse {
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

    public dataResponse(){
    }

    public dataResponse(String name, String email, String FixedIncome, String OtherIncome, String MedianIncome, String TotalExpenses, String SavingIncome, String Age, String RetirementAge, String AssetClass, String Return, String Risk, String Time, String FinancialRisk, String Standard, String RiskWillingness, String Liquidity) {
        this.name = name;
        this.email = email;
        this.FixedIncome = FixedIncome;
        this.OtherIncome = OtherIncome;
        this.MedianIncome = MedianIncome;
        this.TotalExpenses = TotalExpenses;
        this.SavingIncome = SavingIncome;
        this.Age = Age;
        this.RetirementAge = RetirementAge;
        this.AssetClass = AssetClass;
        this.Return = Return;
        this.Risk = Risk;
        this.Time = Time;
        this.FinancialRisk = FinancialRisk;
        this.Standard = Standard;
        this.RiskWillingness = RiskWillingness;
        this.Liquidity = Liquidity;
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
