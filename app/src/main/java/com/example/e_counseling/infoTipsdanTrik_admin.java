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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.e_counseling.adapter.TipstrikAdapter;
import com.example.e_counseling.adapter.UserAdapter;
import com.example.e_counseling.model.Tipsdantrik;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class infoTipsdanTrik_admin extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton backinfoTipsdanTrik;
    private FloatingActionButton btntambahTipsdanTrik;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Tipsdantrik> list = new ArrayList<>();
    private TipstrikAdapter tipstrikAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tipsdan_trik_admin);
        recyclerView = findViewById(R.id.recyclerView2);
        backinfoTipsdanTrik = findViewById(R.id.backinfoTipsdanTrik);
        btntambahTipsdanTrik = findViewById(R.id.btntambahTipsdanTrik);

        progressDialog = new ProgressDialog(infoTipsdanTrik_admin.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menggamil Data..");
        tipstrikAdapter = new TipstrikAdapter(getApplicationContext(),list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(tipstrikAdapter);

        progressDialog.show();
        db.collection("tipstriks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("notifyDatasetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Tipsdantrik tipsdantrik = new Tipsdantrik(document.getString("tipstrik"));
                                list.add(tipsdantrik);
                            }
                            tipstrikAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getApplicationContext(),"Data Gagal di Ambil!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();

                    }
                });

                backinfoTipsdanTrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(infoTipsdanTrik_admin.this, menuutama_admin.class));
            }
        });

        btntambahTipsdanTrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(infoTipsdanTrik_admin.this, inputtipsdantrik_admin.class));
            }
        });
    }
}