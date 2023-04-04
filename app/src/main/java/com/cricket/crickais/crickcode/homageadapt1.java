package com.cricket.crickais.crickcode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class homageadapt1 extends RecyclerView.Adapter<homageadapt1.ViewHolder> {
    LayoutInflater inflater;
    List<homing> homes;
    Context context;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("News");

    FirebaseDatabase firebaseDatabase;
    public homageadapt1(Context context, List<homing> homes){
        this.inflater=LayoutInflater.from(context);
        this.homes=homes;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item3,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position%3 == 0) {
            MobileAds.initialize(context);
            AdLoader.Builder builder = new AdLoader.Builder(context, context.getString(R.string.native2));
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    holder.templateView.setNativeAd(nativeAd);
                }
            });

        final AdLoader adLoader = builder.build();
        adLoader.loadAd(new AdRequest.Builder().build());
            if(adLoader.isLoading()) {
                holder.templateView.setVisibility(View.VISIBLE);
            }
        }
        holder.matchno.setText(homes.get(position).getMatchno());
        holder.summary.setText(homes.get(position).getDate());
        holder.team1.setText(homes.get(position).getTeam1());
        holder.team2.setText(homes.get(position).getTeam2());
        holder.ts1.setText(homes.get(position).getTs1());
        holder.ts2.setText(homes.get(position).getTs2());
        Picasso.get().load(homes.get(position).getSta()).into(holder.icon);
        Picasso.get().load(homes.get(position).getTeam()).into(holder.icon1);

        holder.point.setText(homes.get(position).getTeam3());
        holder.mun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference databaseReference1=firebaseDatabase.getReference("zandroid").child("ts2");
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String stage = snapshot.getValue(String.class);
                        String token=stage;

                        Intent intent=new Intent(context,MainActivity3.class);
                        intent.putExtra("link","https://cricket.sportmonks.com/api/v2.0/fixtures/"+homes.get(position).getBar()+"?api_token="+token+"&include=balls,batting.batsman,batting.bowler,lineup,batting,bowling,scoreboards,scoreboards.team,runs.team,batting.result,balls.batsmanout,batting.batsmanout,runs,runs.team,batting.runoutby,batting.catchstump,batting.batsmanout,bowling.bowler,localteam,visitorteam,stage,tosswon,league,firstumpire,secondumpire,tvumpire,venue,referee,balls.catchstump,manofmatch,manofseries");
                        intent.putExtra("link1stage",homes.get(position).getNext1());
                        context.startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        holder.finish.setText(homes.get(position).getNotification());
        if(homes.get(position).getNotification().equals("Upcoming")){
            holder.fcol.setBackgroundTintList(context.getResources().getColorStateList(R.color.org));
        }else if(homes.get(position).getNotification().equals("Finished")){
            holder.fcol.setBackgroundTintList(context.getResources().getColorStateList(R.color.finish));
        }else{
            holder.fcol.setBackgroundTintList(context.getResources().getColorStateList(R.color.live));
        }

    }


    @Override
    public int getItemCount() {
        return homes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView team1,team2,matchno,summary,ts1,ts2,schedule,point,newest,status,finish;
        ImageView icon,icon1;
        RelativeLayout mun,fcol;
        TemplateView templateView;
        public ViewHolder(View itemview){
            super(itemview);
            matchno=itemView.findViewById(R.id.matchno);
            icon=itemView.findViewById(R.id.icon);
            icon1=itemView.findViewById(R.id.icon1);
            team1=itemView.findViewById(R.id.team1);
            team2=itemView.findViewById(R.id.team2);
            summary=itemView.findViewById(R.id.summary);
            mun=itemView.findViewById(R.id.mf);
            ts1=itemView.findViewById(R.id.ts1);
            ts2=itemView.findViewById(R.id.ts2);
            newest=itemView.findViewById(R.id.newest);
            point=itemView.findViewById(R.id.point);
            fcol=itemView.findViewById(R.id.fcol);
            templateView=itemView.findViewById(R.id.my_template);
            finish=itemView.findViewById(R.id.finish);

        }
    }
}
