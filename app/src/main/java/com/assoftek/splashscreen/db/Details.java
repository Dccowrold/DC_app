package com.assoftek.splashscreen.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "details_data")
public class Details {

    @PrimaryKey
    @NonNull
    public String email;

    public String name;

    public String FixedIncome;

    public String OtherIncome;

    public String MedianIncome;

    public String TotalExpenses;

    public String SavingIncome;

    public String Age;

    public String RetirementAge;

    public String AssetClass;

    public String Return;

    public String Risk;

    public String Time;

    public String FinancialRisk;

    public String Standard;

    public String RiskWillingness;

    public String Liquidity;

}
