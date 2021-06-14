package com.assoftek.splashscreen.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "details_data")
public class Details {

    @PrimaryKey
    @NonNull
    public String email;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String FixedIncome;

    @ColumnInfo
    public String OtherIncome;

    @ColumnInfo
    public String MedianIncome;

    @ColumnInfo
    public String TotalExpenses;

    @ColumnInfo
    public String SavingIncome;

    @ColumnInfo
    public String Age;

    @ColumnInfo
    public String RetirementAge;

    @ColumnInfo
    public String AssetClass;

    @ColumnInfo
    public String Return;

    @ColumnInfo
    public String Risk;

    @ColumnInfo
    public String Time;

    @ColumnInfo
    public String FinancialRisk;

    @ColumnInfo
    public String Standard;

    @ColumnInfo
    public String RiskWillingness;

    @ColumnInfo
    public String Liquidity;

}
