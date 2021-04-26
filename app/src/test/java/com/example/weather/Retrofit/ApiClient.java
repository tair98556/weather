package com.example.weather.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    static Retrofit getClient(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
