package com.assoftek.splashscreen;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @POST("/investment")
    Call<dataResponse> uploadData(@Body dataRequest dataRequest);

}