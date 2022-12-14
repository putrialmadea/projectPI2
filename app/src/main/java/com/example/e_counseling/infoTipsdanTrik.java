package com.example.e_counseling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.e_counseling.adapter.TipstrikAdapter;
import com.example.e_counseling.model.Tipsdantrik;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class infoTipsdanTrik extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton backinfoTipsdanTrik;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Tipsdantrik> list = new ArrayList<>();
    private TipstrikAdapter tipstrikAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tipsdan_trik);
        backinfoTipsdanTrik = findViewById(R.id.backinfoTipsdanTrik);
        recyclerView = findViewById(R.id.recyclerView3);
        backinfoTipsdanTrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(infoTipsdanTrik.this, menuutama.class));
            }
        });
        progressDialog = new ProgressDialog(infoTipsdanTrik.this);
        progressDialog.setTitle("Loding");
        progressDialog.setMessage("Mengambil Data...");

        tipstrikAdapter = new TipstrikAdapter(getApplicationContext(), list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(tipstrikAdapter);

        db.collection("tipstriks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("notifyDatasetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Tipsdantrik tipsdantrik = new Tipsdantrik(document.getString("tipstrik"));
                                tipsdantrik.setId(document.getId());
                                list.add(tipsdantrik);
                            }
                            tipstrikAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data Gagal di Ambil!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
    }