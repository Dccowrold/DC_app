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

}