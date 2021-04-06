package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText lastname;
    private EditText email;
    private EditText pass;
    private EditText confirm_pass;
    private Button register;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name2);
        lastname = findViewById(R.id.lastname2);
        email = findViewById(R.id.email2);
        pass = findViewById(R.id.pass2);
        confirm_pass = findViewById(R.id.confirm_pass2);
        register = findViewById(R.id.enter2);

        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == register) {
            // convert the edit text to string
            String namestr = name.getText().toString();
            String laststr = lastname.getText().toString();
            String emailstr = email.getText().toString();
            String passwordstr = pass.getText().toString();
            String confirm_password = confirm_pass.getText().toString();

            if (!passwordstr.equals(confirm_password)) {
                Toast.makeText(this, "סיסמאות לא תואמות", Toast.LENGTH_SHORT).show();
                pass.setText("");
                confirm_pass.setText("");
                return;
            }

            if (passwordstr.length() < 5) {
                Toast.makeText(this, "הסיסמא צריכה להיות יותר מ-5 תווים", Toast.LENGTH_SHORT).show();
                pass.setText("");
                confirm_pass.setText("");
                return;
            }
            User user = new User(emailstr, namestr, laststr, passwordstr);
            firebaseAuth.createUserWithEmailAndPassword(emailstr, passwordstr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                        Intent intent = new Intent(Register_Activity.this, Home_Activity.class);
                        startActivity(intent);
                    }
                }
            });

        }
    }
}