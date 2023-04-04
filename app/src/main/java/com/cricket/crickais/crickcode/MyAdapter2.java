package com.cricket.crickais.crickcode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter2 extends RecyclerView.Adapter <MyAdapter2.MyViewHolder>{

    ArrayList<Model> mList;
    Context context;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;
    private NativeAd nativeAd;
    View containerView;
    public MyAdapter2(Context context, ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item3,parent,false);
        containerView=LayoutInflater.from(context).inflate(R.layout.nativefacebook_ads,parent,false);



       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position%4 == 0) {

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
            holder.templateView.setVisibility(View.VISIBLE);
        }
        Collections.sort(mList, new Comparator<Model>() {
            @Override
            public int compare(Model model, Model t1) {
                return t1.getIdd().compareTo(model.getIdd());
            }
        });

        Model model =mList.get(position);
        holder.matchno.setText(model.getMatchno());
        holder.summary.setText(model.getDate());
        holder.team1.setText(model.getTeam1());
        holder.team2.setText(model.getTeam2());
        holder.point.setText(model.getHead());
        holder.ts1.setText(model.getTs1());
        holder.ts2.setText(model.getTs2());
        Glide.with(holder.icon.getContext()).load(model.getSta()).into(holder.icon);
        Glide.with(holder.icon1.getContext()).load(model.getTeam()).into(holder.icon1);

        holder.mun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity3.class);
                intent.putExtra("matchno",model.getMatchno());
                intent.putExtra("idd",model.getIdd());
                intent.putExtra("team1",model.getTeam1());
                intent.putExtra("team2",model.getTeam2());
                intent.putExtra("icon",model.getSta());
                intent.putExtra("icon1",model.getTeam());
                intent.putExtra("idic", model.getDesc());
                intent.putExtra("news", model.getNews());

                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView team1,team2,matchno,summary,point,ts1,ts2;
        ImageView icon,icon1;
        RelativeLayout mun;
        TemplateView templateView;
        private NativeAdLayout nativeAd;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            matchno=itemView.findViewById(R.id.matchno);
            point=itemView.findViewById(R.id.point);
            icon=itemView.findViewById(R.id.icon);
            icon1=itemView.findViewById(R.id.icon1);
            team1=itemView.findViewById(R.id.team1);
            mun=itemView.findViewById(R.id.mf);
            team2=itemView.findViewById(R.id.team2);
            summary=itemView.findViewById(R.id.summary);
            ts1=itemView.findViewById(R.id.ts1);
            ts2=itemView.findViewById(R.id.ts2);
            templateView=itemView.findViewById(R.id.my_template);

        }
    }
}
