package com.cricket.crickais.crickcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapterbatting1 extends RecyclerView.Adapter<Adapterbatting1.ViewHolder> {
    LayoutInflater inflater;
    List<song> songs;

    public Adapterbatting1(Context ctx, List<song> songs){
        this.inflater=LayoutInflater.from(ctx);
        this.songs=songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.batting1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.pname.setText(songs.get(position).getPname());

        if(songs.get(position).getPimage().equals("https://cdn.sportmonks.com")) {
            Picasso.get().load("https://www.cricbuzz.com/a/img/v1/75x75/i1/c174146/brian-chari.jpg").into(holder.icon);
        }else{
            Picasso.get().load(songs.get(position).getPimage()).into(holder.icon);
        }
        holder.prun.setText(songs.get(position).getPout());
        holder.pball.setText(songs.get(position).getPball());
        holder.pfour.setText(songs.get(position).getPfour());
        holder.psix.setText(songs.get(position).getPsix());
        holder.psr.setText(songs.get(position).getPsr());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView pname,psub,prun,pball,pfour,psix,psr;
        ImageView icon;
        public ViewHolder(View itemview){
            super(itemview);
            pname=itemview.findViewById(R.id.stricker_player);

            prun=itemview.findViewById(R.id.stricker_player_run);
            pball=itemview.findViewById(R.id.stricker_player_bowl);
            pfour=itemview.findViewById(R.id.stricker_player_4s);
            psix=itemview.findViewById(R.id.stricker_player_6s);
            psr=itemview.findViewById(R.id.stricker_player_sr);
            icon=itemview.findViewById(R.id.icon);
        }
    }
}
