package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView myTitle, myName, myEmail, myLastName;
    private Button myFavorit;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        myTitle = findViewById(R.id.myTitle);
        myEmail = findViewById(R.id.myEmail);
        myName = findViewById(R.id.myName);
        myLastName = findViewById(R.id.mylastname);
        myFavorit = findViewById(R.id.myFavorit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                myEmail.setText("אימייל " +user.getEmail());
                Log.d("email", myEmail.toString());
                myName.setText("שם פרטי"+user.getName());
                myLastName.setText( "שם משפחה" +user.getLastName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        myFavorit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == myFavorit) {
            Intent i = new Intent(this, ShowFCitiesActivity.class);
            startActivity(i);
        }
    }
}