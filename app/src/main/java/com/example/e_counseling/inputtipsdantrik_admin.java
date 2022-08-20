package com.example.e_counseling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class inputtipsdantrik_admin extends AppCompatActivity {

    private ImageButton backInputTipsdanTrik_admin;
    private EditText inputTipsdanTrik;
    private Button btnsimpaninputtipsdantrik_admin;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputtipsdantrik_admin);
        backInputTipsdanTrik_admin = (ImageButton) findViewById(R.id.backInputTipsdanTrik_admin);
        inputTipsdanTrik = (EditText) findViewById(R.id.inputTipsdanTrik);

        progressDialog= new ProgressDialog(inputtipsdantrik_admin.this);
        progressDialog.setTitle("Loding");
        progressDialog.setMessage("Menyimpan...");

        btnsimpaninputtipsdantrik_admin = (Button) findViewById(R.id.btnsimpaninputtipsdantrik_admin);


        btnsimpaninputtipsdantrik_admin.setOnClickListener(v -> {
            if(inputTipsdanTrik.getText().length()>0){
                saveData(inputTipsdanTrik.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan Isi Semua Data!", Toast.LENGTH_SHORT).show();
            }
        });

        backInputTipsdanTrik_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(inputtipsdantrik_admin.this, infoTipsdanTrik_admin.class));
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent!=null){
            id = intent.getStringExtra("id");
            inputTipsdanTrik.setText(intent.getStringExtra("tipstrik"));
        }

    }
    private void saveData(String inputTipsdanTrik ){
        Map<String, Object> tipstrik = new HashMap<>();
        tipstrik.put("tipstrik", inputTipsdanTrik);

        progressDialog.show();
        if (id!=null){
            db.collection("tipstriks").document(id)
                    .set(tipstrik)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            db.collection("tipstriks")
                    .add(tipstrik)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}