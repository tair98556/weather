package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.Retrofit.ApiClient;
import com.example.weather.Retrofit.ApiInterface;
import com.example.weather.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class


Home_Activity extends AppCompatActivity {

    ImageView search;
    TextView tempText , descText , humidityText, clothes;
    EditText textField;
    int feelsLike;

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

    private void getWeatherData(String name){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                feelsLike = Integer.parseInt(response.body().getMain().getFeels_like());

                try {
                    tempText.setText("טמפרטורה" + " " + response.body().getMain().getTemp() + " C");
                    descText.setText("מרגיש כמו" + " " + response.body().getMain().getFeels_like());
                    humidityText.setText("לחות" + " " + response.body().getMain().getHumidity());

                    if (feelsLike>25){
                        clothes.setText("מומלץ ללבוש בגדים קיציים:)");}
                    else if ((feelsLike<25)&&(feelsLike>15)){
                        clothes.setText("מומלץ ללבוש חולצה ארוכה דקה:)");}
                    else if ((feelsLike>10) &&(feelsLike<15)){
                        clothes.setText("מומלץ ללבוש חולצה וג'קט דקים");}
                    else if (feelsLike<10){
                        clothes.setText("מומלץ ללבוש בגדים חורפיים");}

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.fP1){
           // Toast.makeText(this)
        }
        return true;
    }
}