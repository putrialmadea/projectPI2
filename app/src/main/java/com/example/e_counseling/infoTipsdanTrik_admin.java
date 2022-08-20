package com.example.e_counseling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

        tipstrikAdapter.setDialog(new TipstrikAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem =  {"Edit", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(infoTipsdanTrik_admin.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), inputtipsdantrik_admin.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("tipstrik", list.get(pos).getInputtipsdantrik());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
            dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(tipstrikAdapter);



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

        getData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getData(){
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
                                    tipsdantrik.setId(document.getId());
                                    list.add(tipsdantrik);
                                }
                                tipstrikAdapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(getApplicationContext(),"Data Gagal di Ambil!", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }

    private void deleteData(String id){
        progressDialog.show();
        db.collection("tipstriks").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Data Gagal di hapus!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            getData();
                        }
                    }
                });
        }
}
