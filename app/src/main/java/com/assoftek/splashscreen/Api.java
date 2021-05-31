package com.assoftek.splashscreen;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("signin")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> register (
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password

    );

}
