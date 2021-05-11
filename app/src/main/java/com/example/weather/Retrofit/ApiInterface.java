package com.example.weather.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=456e96340dd9fc068cafa3204126e801")
    Call<Example> getWeatherData(@Query("q") String name);


}
