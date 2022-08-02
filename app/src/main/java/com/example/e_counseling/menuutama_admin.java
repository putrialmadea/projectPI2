package com.example.e_counseling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class menuutama_admin extends AppCompatActivity {

    ImageButton pilihkampus_admin, kumpulsoal_admin, btntipsdantrikadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama_admin);
        pilihkampus_admin = (ImageButton) findViewById(R.id.btnpilihkampusadmin);
        kumpulsoal_admin = (ImageButton) findViewById(R.id.btnkumpulansoaladmin);
        btntipsdantrikadmin = (ImageButton) findViewById(R.id.btntipsdantrikadmin);
        btntipsdantrikadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuutama_admin.this, infoTipsdanTrik_admin.class));
                finish();
            }
        });

        pilihkampus_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuutama_admin.this, list_admin.class));
                finish();
            }
        });

        kumpulsoal_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuutama_admin.this, Uploadfile .class));
                finish();
            }
        });
    }
}