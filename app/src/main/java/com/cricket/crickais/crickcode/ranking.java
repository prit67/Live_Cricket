package com.cricket.crickais.crickcode;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class ranking extends AppCompatActivity {
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mtabLayout;
    private InterstitialAd interstitialAd;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private FragmentPagerAdapter mTabAccessorAdapter;
    private RelativeLayout main_page_toolbar1;
    ImageView go;
    private final String TAG ="FB_ADS";
    TextView team1,team2;
    ImageView kar;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().hide();

        kar=findViewById(R.id.kar);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mtabLayout = findViewById(R.id.main_tab);
        mViewPager = findViewById(R.id.main_tabs_pager);

        mTabAccessorAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        seri seri=new seri();
                        return seri;

                    case 1:
                        serit20 serit20 = new serit20();
                        return serit20;
                    case 2:
                        seridom seridom = new seridom();
                        return seridom;
                    case 3:
                        seriwomen seriwomen = new seriwomen();
                        return seriwomen;

                }
                return null;
            }
            @Override
            public int getCount() {
                // Show 3 total pages.
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "INTERNATIONAL";
                    case 1:
                        return "T20 LEAGUES";
                    case 2:
                        return "DOMESTIC";

                    case 3:
                        return "WOMEN";

                }
                return null;
            }

        };
        mViewPager.setAdapter(mTabAccessorAdapter);


        mtabLayout.setupWithViewPager(mViewPager);



    }

}