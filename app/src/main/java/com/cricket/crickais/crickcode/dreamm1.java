package com.cricket.crickais.crickcode;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class dreamm1 extends AppCompatActivity {
    TextView asd,tt1,t2,abc1,abc12,abc13,point43,point431,point432;
    ImageView kar,img98,dream;
    private InterstitialAd interstitialAd;
    private final String TAG ="FB_ADS";
    FirebaseDatabase firebaseDatabase;
    private AdView mAdView,mAdView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dreamm1);

        getSupportActionBar().hide();
        mAdView=(AdView)findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        loadAd();

        tt1=(TextView) findViewById(R.id.tt1);
        dream=findViewById(R.id.dream);
        point43=(TextView) findViewById(R.id.point43);
        point431=(TextView) findViewById(R.id.point431);
        point432=(TextView) findViewById(R.id.point432);
        abc1=(TextView) findViewById(R.id.abc1);
        abc12=(TextView) findViewById(R.id.abc12);
        abc13=(TextView) findViewById(R.id.abc13);
        kar=(ImageView)findViewById(R.id.kar);
        img98=(ImageView)findViewById(R.id.img98);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tt1.setText(getIntent().getStringExtra("head"));
        point43.setText(getIntent().getStringExtra("head"));
        point431.setText(getIntent().getStringExtra("ts1"));
        point432.setText(getIntent().getStringExtra("ts2"));

        String link = getIntent().getStringExtra("newsimg");
        Picasso.get().load(link).into(img98);

        String link1 = getIntent().getStringExtra("subimg");
        Picasso.get().load(link1).into(dream);

        abc1.setText(getIntent().getStringExtra("news"));


    }
    public void loadAd() {
        AdRequest adRequest4 = new AdRequest.Builder().build();
        InterstitialAd.load(
                this, getString(R.string.inter2),
                adRequest4,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        dreamm1.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        showInterstitial();

                    }


                });

    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();

        }
    }
}