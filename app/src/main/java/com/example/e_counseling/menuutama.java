package com.example.e_counseling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class menuutama extends AppCompatActivity {

    ImageButton pilihkampus, profiladmin, btnkumpulansoal, btntipsdantrik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);
        profiladmin = (ImageButton)findViewById(R.id.profiladmin);
        pilihkampus = (ImageButton) findViewById(R.id.btnpilihkampus);
        btnkumpulansoal = (ImageButton) findViewById(R.id.btnkumpulansoal);
        btntipsdantrik = (ImageButton) findViewById(R.id.btntipsdantrik);
        btntipsdantrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuutama.this, infoTipsdanTrik.class));
                finish();
            }
        });

        btnkumpulansoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuutama.this, kumpulanfilepdf.class));
                finish();
            }
        });
        pilihkampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuutama.this, list_siswaadmin.class));
                finish();
            }
        });
        profiladmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuutama.this, login.class));
                finish();
            }
        });

    }
}