package com.example.e_counseling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class register extends AppCompatActivity {

    TextView log;
    private EditText inputUsername, inputPassword1, inputPassword2;
    String regregister, password1, password2;
    Button btncreate;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        log = (TextView) findViewById(R.id.logNow);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, login.class));

            }
        });

        inputUsername= findViewById(R.id.regregister);
        inputPassword1 = findViewById(R.id.password1);
        inputPassword2 = findViewById(R.id.password2);
        btncreate = findViewById(R.id.btncreate);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrasi();
            }
        });}

//    private void registrasi(){
//        regregister = inputUsername.getText().toString();
//        password1 = inputPassword1.getText().toString();
//        password2 = inputPassword2.getText().toString();
//
//        mAuth.createUserWithEmailAndPassword(regregister, password2)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Intent intent;
//                            intent = new Intent(register.this,login.class);
//                            startActivity(intent);
//                            Toast.makeText(register.this, "Successful Registration", Toast.LENGTH_LONG).show();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_LONG).show();
//                        }
//
//                        // ...
//                    }
//                });
//    }
    private void registrasi(){
        regregister = inputUsername.getText().toString();
        password1 = inputPassword1.getText().toString();
        password2 = inputPassword2.getText().toString();

        mAuth.createUserWithEmailAndPassword(regregister, password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent;
                            intent = new Intent(register.this, login.class);
                            Toast.makeText(register.this, "Successful Registration", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}

