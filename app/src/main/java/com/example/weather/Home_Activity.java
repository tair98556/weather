package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home_Activity extends AppCompatActivity {

    ImageView search;
    TextView tempText , descText , humidityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        search = findViewById(R.id.search);
        tempText = findViewById(R.id.tempText);
        descText = findViewById(R.id.descText);
        humidityText = findViewById(R.id.humidityText);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling API
            }
        });
    }

    public String recommendation (int temp)
    {
        if (temp>25){

        }
    }
}