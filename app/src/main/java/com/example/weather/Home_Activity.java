package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView search;
    private TextView tempText, descText, humidityText, clothes;
    private EditText textField;
    private double feelsLike;
    private Dialog d;
    private EditText nameF;
    private EditText favCity;
    private Button enter;
    private FavCitynName f;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


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

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

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

        Call<Weather> call = apiInterface.getWeatherData(name, "metric", "456e96340dd9fc068cafa3204126e801");

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
        d.setTitle("מקום מועדף");
        nameF = d.findViewById(R.id.nameF);
        favCity = d.findViewById(R.id.favCity);
        enter = d.findViewById(R.id.enter);
        enter.setOnClickListener(this);
        Log.d("function","Fav1D");
        d.show();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.fP1) {

            fav1Dialog();
        }

        if( id == R.id.myProfile){

            Intent intent = new Intent(this,MyProfileActivity.class);
            startActivity(intent);}


        if( id == R.id.logOut){

            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }
        return true;
    }

    @Override
    public void onClick(View v) {

        if ( v == enter){

            f = new FavCitynName(favCity.getText().toString().trim(),nameF.getText().toString().trim());
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("favCities").
                    child(System.currentTimeMillis()+"").setValue(f);

            Intent i = new Intent(Home_Activity.this, ShowFCitiesActivity.class);
            startActivity(i);

        }
    }
}






