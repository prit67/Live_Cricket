package com.cricket.crickais.crickcode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class MyAdapter12vid extends RecyclerView.Adapter <MyAdapter12vid.MyViewHolder>{

    ArrayList<Model> mList;
    Context context;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;
    private NativeAd nativeAd;
View containerView;

    public MyAdapter12vid(Context context, ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.itemvid,parent,false);
containerView=LayoutInflater.from(context).inflate(R.layout.nativefacebook_ads,parent,false);


       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



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


        Collections.sort(mList, new Comparator<Model>() {
            @Override
            public int compare(Model model, Model t1) {
                return t1.getIdd().compareTo(model.getIdd());
            }
        });

        Model model =mList.get(position);

        holder.schedule.setText(model.getNewshead());
        holder.schedule1.setText(model.getNews());

        Glide.with(holder.imgnews.getContext()).load(model.getNewsimg()).into(holder.imgnews);

        holder.news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl(model.getNewssub());


            }
        });



    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        context.startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private final int limit = 6;
    @Override
    public int getItemCount()  {

        if(mList.size() >= limit) {
            Collections.reverse(mList);
            return limit;
        } else
        {
            return mList.size();
        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView schedule,schedule1;
        ImageView imgnews;
        RelativeLayout news;
        private NativeAdLayout nativeAd;
        TemplateView templateView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            schedule=itemView.findViewById(R.id.schedule);
            schedule1=itemView.findViewById(R.id.schedule1);
            imgnews=itemView.findViewById(R.id.imgnews);
            news=itemView.findViewById(R.id.news);
            templateView=itemView.findViewById(R.id.my_template);


        }
    }
}
