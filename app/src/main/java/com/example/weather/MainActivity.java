package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private EditText userEmail;
    private EditText userPass;
    private Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.newUser);
        login = findViewById(R.id.enter);
        userEmail = findViewById(R.id.email);
        userPass = findViewById(R.id.pass);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, Home_Activity.class);
            startActivity(intent);
        }

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == login) {

            if (userEmail.getText().toString().matches("")|| userPass.getText().toString().matches(""))
            {
                Toast.makeText(this, "אנא הכנס ערכים", Toast.LENGTH_SHORT).show();
                return;
            }

            else {
            firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(), userPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(MainActivity.this, Home_Activity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "מייל או סיסמא אינם תקינים", Toast.LENGTH_SHORT).show();
                                return;
                            }


                        }
                    });
            }


        }
        if (v == register) { // register screen
            Intent intent = new Intent(this, Register_Activity.class);
            startActivity(intent);
        }

    }
}

