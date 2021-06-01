package com.assoftek.splashscreen;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static String BASE_URL = "http://dcipl.yourtechshow.com/";
    public static RetrofitClient retrofitClient;
    public static Retrofit retrofit;
    //    public static Retrofit getRetrofit(){
//
//        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    public RetrofitClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
//    public static Api getApi() {
//          return retrofit.create(Api.class);
//    }
}
