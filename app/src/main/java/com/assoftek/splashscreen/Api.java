package com.assoftek.splashscreen;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("signin")
    Call<LoginResponse> signin (
            @Field("email") String email,
            @Field("password") String password

    );
    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> register (
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("data")
    Call<dataResponse> uploadData(
            @Field("name") String name,
            @Field("email") String email,
            @Field("FixedIncome") String FixedIncome,
            @Field("OtherIncome") String OtherIncome,
            @Field("MedianIncome") String MedianIncome,
            @Field("TotalExpenses") String TotalExpenses,
            @Field("SavingIncome") String SavingIncome,
            @Field("Age") String Age,
            @Field("RetirementAge") String RetirementAge,
            @Field("AssetClass") String AssetClass,
            @Field("Return") String Return,
            @Field("Risk") String Risk,
            @Field("Time") String Time,
            @Field("FinancialRisk") String FinancialRisk,
            @Field("Standard") String Standard,
            @Field("RiskWillingness") String RiskWillingness,
            @Field("Liquidity") String Liquidity
    );
}