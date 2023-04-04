package com.cricket.crickais.crickcode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class fsqq extends RecyclerView.Adapter<fsqq.ViewHolder> {
    LayoutInflater inflater;
    List<homing> homes;
    Context context;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("News");

    FirebaseDatabase firebaseDatabase;
    public fsqq(Context context, List<homing> homes){
        this.inflater=LayoutInflater.from(context);
        this.homes=homes;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.itemsquad,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.teams.setText(homes.get(position).getSta());

holder.first.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, fsq.class);
        intent.putExtra("team_id",homes.get(position).getTeam());
        intent.putExtra("season_id",homes.get(position).getTeam1());
        context.startActivity(intent);
    }
});



    }


    @Override
    public int getItemCount() {
        return homes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView teams;
        RelativeLayout first;
        public ViewHolder(View itemview){
            super(itemview);
            teams=itemView.findViewById(R.id.teams);
            first=itemView.findViewById(R.id.first);

        }
    }
}
