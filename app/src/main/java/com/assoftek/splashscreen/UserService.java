package com.assoftek.splashscreen;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("user/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

}
