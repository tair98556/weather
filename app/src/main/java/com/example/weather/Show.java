package com.example.weather;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.weather.Retrofit.ApiClient;
import com.example.weather.Retrofit.ApiInterface;
import com.example.weather.Retrofit.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Show extends Thread{

    private String name, nickname;
    public TextView tempTv, recoTv, nameTv, nickTv;
    public View v;

    public Show( View v, String name, String nickname) {

        this.v = v;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        super.run();
        for(int i = 0; i <1; i++){

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<Weather> call = apiInterface.getWeatherData(name, "metric", "456e96340dd9fc068cafa3204126e801");


            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {

                    try {

                        nameTv = v.findViewById(R.id.placeT);
                        recoTv = v.findViewById(R.id.recomm);
                        tempTv = v.findViewById(R.id.deg);
                        nickTv = v.findViewById(R.id.nickname);
                        String reco = "";

                        Double feelsLike = Double.parseDouble(response.body().getMain().getFeels_like());

                        if (feelsLike > 20) {
                            reco = "מומלץ ללבוש בגדים קיציים:)";
                        } else if ((feelsLike < 20) && (feelsLike > 15)) {
                            reco = "מומלץ ללבוש חולצה ארוכה דקה:)";
                        } else if ((feelsLike > 10) && (feelsLike < 15)) {
                            reco = "מומלץ ללבוש חולצה וג'קט דקים";
                        } else if (feelsLike < 10) {
                            reco = "מומלץ ללבוש בגדים חורפיים";
                        }

                        nameTv.setText(name);
                        Log.d("name", name);
                        nickTv.setText(nickname);
                        Log.d("nickTv", nickname);
                        recoTv.setText(reco);
                        Log.d("recoTv", reco);
                        tempTv.setText(response.body().getMain().getTemp() + "C");
                        Log.d("tempTv", response.body().getMain().getTemp());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {
                }
            });
        }
    }
}

