package com.example.weather.Retrofit;

import android.util.Log;
import java.util.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?")
    Call<Weather> getWeatherData(@Query("q") String city,
                                 @Query("units") String units,
                                 @Query("appid") String appid);


}
