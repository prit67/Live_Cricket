package com.cricket.crickais.crickcode;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RankFragment extends Fragment {
    TextView mr;
    RelativeLayout mf2,mf3,mf4,mf5,mf6,mf7,mf8,mf9,mf10,mf11;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_rank,container,false);

        mf2=(RelativeLayout) rootView.findViewById(R.id.mf2);
        mf3=(RelativeLayout) rootView.findViewById(R.id.mf3);
        mf4=(RelativeLayout) rootView.findViewById(R.id.mf4);
        mf5=(RelativeLayout) rootView.findViewById(R.id.mf5);
        mf6=(RelativeLayout) rootView.findViewById(R.id.mf6);
        mf7=(RelativeLayout) rootView.findViewById(R.id.mf7);
        mf8=(RelativeLayout) rootView.findViewById(R.id.mf8);
        mf9=(RelativeLayout) rootView.findViewById(R.id.mf9);
        mf10=(RelativeLayout) rootView.findViewById(R.id.mf10);
        mf11=(RelativeLayout) rootView.findViewById(R.id.mf11);
        mf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ranking.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        mf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), archive.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        mf10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateUsOnPlayStore();
            }
        });


        return rootView;

    }
    public void rateUsOnPlayStore() {
        Uri uri = Uri.parse("market://details?id=com.cricket.crickais.cricketaisps");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=com.cricket.crickais.cricketaisps")));
        }
    }
}

