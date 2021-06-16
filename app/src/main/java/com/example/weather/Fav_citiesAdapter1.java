package com.example.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weather.R;
import com.example.weather.Retrofit.ApiClient;
import com.example.weather.Retrofit.ApiInterface;
import com.example.weather.Retrofit.Weather;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fav_citiesAdapter1 extends ArrayAdapter<FavCitynName> {

    Context context;
    List<FavCitynName> fav_cities;
    private String name, nickname;
    private Show s;
    public View v;


    public Fav_citiesAdapter1(@NonNull Context context, int resource, int textVievResourceId, @NonNull List<FavCitynName> objects) {
        super(context, resource, textVievResourceId, objects);


        this.context = context;
        this.fav_cities = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FavCitynName favorit = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, parent, false);
        }

        v = convertView;
        name = favorit.getName();
        nickname = favorit.getNickname();
        s = new Show(v, name, nickname);
        s.start();

        return convertView;
    }
}