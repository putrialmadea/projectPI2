package com.example.e_counseling.adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.e_counseling.R;
import com.example.e_counseling.model.Tipsdantrik;

import java.util.List;

public class TipstrikAdapter extends RecyclerView.Adapter<TipstrikAdapter.MyViewHolder> {
    private Context context;
    private List<Tipsdantrik> list;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public TipstrikAdapter(Context context, List<Tipsdantrik> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tipsdantrik,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tipsdantrik.setText(list.get(position).getInputtipsdantrik());
    }

    @Override
    public  int getItemCount(){
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tipsdantrik;

        public MyViewHolder(android.view.View itemView) {
            super(itemView);
            tipsdantrik=itemView.findViewById(R.id. Itipsdantrik);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}


