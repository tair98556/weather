package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.Retrofit.ApiClient;
import com.example.weather.Retrofit.ApiInterface;
import com.example.weather.Retrofit.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Activity extends AppCompatActivity {

    private ImageView search;
    private TextView tempText, descText, humidityText, clothes;
    private EditText textField;
    private double feelsLike;

    private Dialog d;
    private EditText n1;
    private EditText n2;
    private Button s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        search = findViewById(R.id.search);
        tempText = findViewById(R.id.tempText);
        descText = findViewById(R.id.descText);
        humidityText = findViewById(R.id.humidityText);
        textField = findViewById(R.id.textField);
        clothes = findViewById(R.id.clothes);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling API
                getWeatherData(textField.getText().toString().trim()); //taking the name of the city
            }
        });

    }

    private void getWeatherData(String name) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Weather> call = apiInterface.getWeatherData(name, "metric", getResources().getString(R.string.apiKey));

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                try {
                    feelsLike = Double.parseDouble(response.body().getMain().getFeels_like());
                    tempText.setText("טמפרטורה" + " " + response.body().getMain().getTemp());
                    descText.setText("מרגיש כמו" + " " + response.body().getMain().getFeels_like());
                    humidityText.setText("לחות" + " " + response.body().getMain().getHumidity());

                    if (feelsLike > 20) {
                        clothes.setText("מומלץ ללבוש בגדים קיציים:)");
                    } else if ((feelsLike < 20) && (feelsLike > 15)) {
                        clothes.setText("מומלץ ללבוש חולצה ארוכה דקה:)");
                    } else if ((feelsLike > 10) && (feelsLike < 15)) {
                        clothes.setText("מומלץ ללבוש חולצה וג'קט דקים");
                    } else if (feelsLike < 10) {
                        clothes.setText("מומלץ ללבוש בגדים חורפיים");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public void fav1Dialog() {


        d = new Dialog(this);
        d.setContentView(R.layout.fav1_dialog);
        d.setCancelable(true);
        d.setTitle("מקום 1");
        n1 = d.findViewById(R.id.nameF1);
        n2 = d.findViewById(R.id.favCity1);
        s1 = d.findViewById(R.id.enter);
        //s1.setOnClickListener((View.OnClickListener) this); //why? VIEW...
        d.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.fP1) {

            fav1Dialog();
        }
        return true;
    }


}




