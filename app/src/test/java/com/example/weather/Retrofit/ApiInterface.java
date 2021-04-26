package com.example.weather.Retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("http://api.openweathermap.org/data/2.5/weather?q&appid=456e96340dd9fc068cafa3204126e801")
    Class<Example> getWeatherData(@Query("q") String name);


}
