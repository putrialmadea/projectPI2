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

public class pilihkampus_admin extends AppCompatActivity {

    private ImageButton kembali_univadmin;
    private EditText input_jurusanadmin,input_kampusadmin;
    private Button btnsimpandmin;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id =" ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihkampus_admin);
        kembali_univadmin = (ImageButton) findViewById(R.id.kembali_univadmin);
        btnsimpandmin = (Button) findViewById(R.id.btnsimpanadmin);


        progressDialog = new ProgressDialog(pilihkampus_admin.this);
        progressDialog.setTitle("Loding");
        progressDialog.setMessage("Menyimpan...");


        input_jurusanadmin = (EditText) findViewById(R.id.input_jurusanadmin);
        input_kampusadmin = (EditText) findViewById(R.id.input_kampusadmin);

        btnsimpandmin.setOnClickListener(v -> {
            if (input_jurusanadmin.getText().length()>0 && input_kampusadmin.getText().length()>0){
                saveData(input_jurusanadmin.getText().toString(), input_kampusadmin.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan Isi Semua Data!", Toast.LENGTH_SHORT).show();
            }
        });

        kembali_univadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pilihkampus_admin.this, list_admin.class));
            }
        });

        Intent intent = getIntent();
        if (intent!=null){
            id = intent.getStringExtra("id");
            input_jurusanadmin.setText(intent.getStringExtra("Jurusan"));
            input_kampusadmin.setText(intent.getStringExtra("Universitas"));
        }

    }
        private void saveData (String input_jurusanadmin, String input_kampusadmin){
                    Map<String, Object> user = new HashMap<>();
                    user.put("Jurusan", input_jurusanadmin);
                    user.put("Universitas", input_kampusadmin);

                    progressDialog.show();
                    if (id!=null){
                        db.collection("users").document(id)
                                .set(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else {
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
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
