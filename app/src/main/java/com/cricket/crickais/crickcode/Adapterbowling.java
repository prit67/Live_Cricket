package com.cricket.crickais.crickcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapterbowling extends RecyclerView.Adapter<Adapterbowling.ViewHolder> {
    LayoutInflater inflater;
    List<bowler> bowlers;

    public Adapterbowling(Context ctx, List<bowler> bowlers){
        this.inflater=LayoutInflater.from(ctx);
        this.bowlers=bowlers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bowling,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.pname.setText(bowlers.get(position).getPname());
        holder.pover.setText(bowlers.get(position).getPover());
        if(bowlers.get(position).getPimage().equals("https://cdn.sportmonks.com")) {
            Picasso.get().load("https://www.cricbuzz.com/a/img/v1/75x75/i1/c174146/brian-chari.jpg").into(holder.icon);
        }else{
            Picasso.get().load(bowlers.get(position).getPimage()).into(holder.icon);
        }
        holder.pmedi.setText(bowlers.get(position).getPmedi());
        holder.prrun.setText(bowlers.get(position).getPrrun());
        holder.pwik.setText(bowlers.get(position).getPwik());
        holder.per.setText(bowlers.get(position).getPer());

    }

    private final int limit = 1;
    @Override
    public int getItemCount()  {

        if(bowlers.size() >= limit) {
            Collections.reverse(bowlers);
            return limit;
        } else
        {
            return bowlers.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView pname,pover,pmedi,prrun,pwik,per;
        ImageView icon;
        public ViewHolder(View itemview){
            super(itemview);
            pname=itemview.findViewById(R.id.bowler);
            pover=itemview.findViewById(R.id.over);
            pmedi=itemview.findViewById(R.id.medi);
            prrun=itemview.findViewById(R.id.rrun);
            pwik=itemview.findViewById(R.id.wik);
            per=itemview.findViewById(R.id.er);
            icon=itemview.findViewById(R.id.icon);
        }
    }
}
