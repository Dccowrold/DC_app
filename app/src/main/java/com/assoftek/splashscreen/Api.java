package com.assoftek.splashscreen;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @POST("/signin")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<LoginResponse> signin (@Body LoginRequest loginRequest);

    @POST("/register")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<RegisterResponse> register (@Body RegisterRequest registerRequest);

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