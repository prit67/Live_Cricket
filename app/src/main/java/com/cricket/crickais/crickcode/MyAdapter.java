package com.cricket.crickais.crickcode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>{

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);



       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model =mList.get(position);
        holder.matchno.setText(model.getMatchno());
        holder.summary.setText(model.getDate());
        holder.team1.setText(model.getTeam1());
        holder.team2.setText(model.getTeam2());
        holder.ts1.setText(model.getTs1());
        holder.ts2.setText(model.getTs2());

        Glide.with(holder.icon.getContext()).load(model.getSta()).into(holder.icon);
        Glide.with(holder.icon1.getContext()).load(model.getTeam()).into(holder.icon1);


        holder.schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, matchsh.class);
                context.startActivity(intent);
            }
        });
        holder.point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, pointtab.class);
                context.startActivity(intent);
            }
        });








    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView team1,team2,matchno,summary,ts1,ts2,schedule,point;
        ImageView icon,icon1;
        RelativeLayout mun;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            matchno=itemView.findViewById(R.id.matchno);
            icon=itemView.findViewById(R.id.icon);
            icon1=itemView.findViewById(R.id.icon1);
            team1=itemView.findViewById(R.id.team1);
            team2=itemView.findViewById(R.id.team2);
            summary=itemView.findViewById(R.id.summary);
            mun=itemView.findViewById(R.id.mf);
            ts1=itemView.findViewById(R.id.ts1);
            ts2=itemView.findViewById(R.id.ts2);
            schedule=itemView.findViewById(R.id.schedule);
            point=itemView.findViewById(R.id.point);


        }
    }
}
