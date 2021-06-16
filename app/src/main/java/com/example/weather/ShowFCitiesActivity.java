package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowFCitiesActivity extends AppCompatActivity {

    private ArrayList<FavCitynName> favCitynNames;
    private ListView lv;
    private Fav_citiesAdapter1 fav_citiesAdapter1;
    private FavCitynName favCitynNameSlected;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");
        favCitynNames = new ArrayList<FavCitynName>();
        lv = findViewById(R.id.lv);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("favCities").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot d : snapshot.getChildren()) {
                    favCitynNameSlected = d.getValue(FavCitynName.class);
                    favCitynNames.add(favCitynNameSlected);

                }
                fav_citiesAdapter1 = new Fav_citiesAdapter1(ShowFCitiesActivity.this, 0, 0, favCitynNames);
                for (int i=0; i<favCitynNames.size(); i++)
                Log.d("function",favCitynNames.get(i).getName()+"");
                lv.setAdapter(fav_citiesAdapter1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
