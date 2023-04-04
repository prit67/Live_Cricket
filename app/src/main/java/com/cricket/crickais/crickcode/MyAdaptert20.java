package com.cricket.crickais.crickcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdaptert20 extends RecyclerView.Adapter <MyAdaptert20.MyViewHolder>{

    ArrayList<Model> mList;
    Context context;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;
    private NativeAd nativeAd;
    View containerView;
    public MyAdaptert20(Context context, ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item6,parent,false);
        containerView=LayoutInflater.from(context).inflate(R.layout.nativefacebook_ads,parent,false);



       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position%4 == 0) {

            MobileAds.initialize(context);
            AdLoader.Builder builder = new AdLoader.Builder(context, context.getString(R.string.native1));
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    holder.templateView.setNativeAd(nativeAd);
                }
            });
        }
        Collections.sort(mList, new Comparator<Model>() {
            @Override
            public int compare(Model model, Model t1) {
                return t1.getIdd().compareTo(model.getIdd());
            }
        });

        Model model =mList.get(position);

        holder.team1.setText(model.getDesc());
        holder.team2.setText(model.getDate());





        holder.mun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView team1,team2;

        RelativeLayout mun;
        private NativeAdLayout nativeAd;
        TemplateView templateView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            team1=itemView.findViewById(R.id.team1);
            team2=itemView.findViewById(R.id.team2);

            mun=itemView.findViewById(R.id.mf10);





        }
    }
}
