package com.example.e_counseling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class infoTipsdanTrik extends AppCompatActivity {

    ImageButton backinfoTipsdanTrik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tipsdan_trik);
        backinfoTipsdanTrik = findViewById(R.id.backinfoTipsdanTrik);
        backinfoTipsdanTrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(infoTipsdanTrik.this, menuutama.class));
            }
        });
    }
}