package com.cricket.crickais.crickcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterpoint extends RecyclerView.Adapter <MyAdapterpoint.MyViewHolder>{

    List<homing> homes;
    Context context;

    public MyAdapterpoint(Context context, ArrayList<homing> homes){
        this.homes=homes;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.itempoint,parent,false);



       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.p.setText(homes.get(position).getSta());
        holder.w.setText(homes.get(position).getTeam2());
        holder.l.setText(homes.get(position).getTeam());
        holder.nr.setText(homes.get(position).getTeam3());
        holder.pts.setText(homes.get(position).getDate());
        holder.nrr.setText(homes.get(position).getBar());
        holder.teams.setText(homes.get(position).getTeam1());




    }

    @Override
    public int getItemCount() {
        return homes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView p,w,l,nr,pts,nrr,teams;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            teams=itemView.findViewById(R.id.teams);
            p=itemView.findViewById(R.id.p);
            w=itemView.findViewById(R.id.w);
            l=itemView.findViewById(R.id.l);
            nr=itemView.findViewById(R.id.nr);
            pts=itemView.findViewById(R.id.pts);
            nrr=itemView.findViewById(R.id.nrr);

        }
    }
}
