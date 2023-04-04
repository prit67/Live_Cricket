package com.cricket.crickais.crickcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity31 extends AppCompatActivity {
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
        setContentView(R.layout.activity_main31);

        getSupportActionBar().hide();

        team1=findViewById(R.id.team1);
        team2=findViewById(R.id.team2);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        loadAd();

        String matchid = getIntent().getStringExtra("idic");
        String expand = getIntent().getStringExtra("expand");

        String data = getIntent().getStringExtra("idd");
        String news = getIntent().getStringExtra("news");

        kar=findViewById(R.id.kar);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity31.this,starttingActivity.class);
                startActivity(intent);

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
                        Tab111 tab111=new Tab111();
                        Bundle bundle = new Bundle();
                        bundle.putString("mun", matchid);
                        tab111.setArguments(bundle);

                        return tab111;

                    case 1:
                        Tab222 tab222 = new Tab222();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("mun", matchid);
                        bundle1.putString("data", data);
                        bundle1.putString("news", news);
                        tab222.setArguments(bundle1);
                        return tab222;
                    case 2:
                        Tab333 tab333 = new Tab333();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("mun", matchid);
                        bundle2.putString("expand", expand);
                        tab333.setArguments(bundle2);
                        return tab333;
                    case 3:
                        Tab444 tab444 = new Tab444();
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("mun", matchid);
                        tab444.setArguments(bundle4);
                        return tab444;

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
                        return "INFO";
                    case 1:
                        return "LIVE";
                    case 2:
                        return "SCORECARD";

                    case 3:
                        return "NEWS";

                }
                return null;
            }

        };
        mViewPager.setAdapter(mTabAccessorAdapter);


        mtabLayout.setupWithViewPager(mViewPager);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
Tab1 tab1=new Tab1();

        Bundle bundle1 = new Bundle();
        bundle1.putString("edttext", "From Activity");
        tab1.setArguments(bundle1);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference4=firebaseDatabase.getReference("zandroid").child("host");
        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot1) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

        DatabaseReference databaseReference5=firebaseDatabase.getReference("zandroid");
        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot2) {
                Model model = snapshot2.getValue(Model.class);
                String host=model.getTs1();
                String key=model.getTs2();

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(matchid)
                        .get()
                        .addHeader("x-rapidapi-host", host)
                        .addHeader("x-rapidapi-key", key)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.isSuccessful()){
                            String asd=response.body().string();



                            MainActivity31.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String students_array = asd;



                                    try {
                                        JSONObject jsonObject=new JSONObject(students_array);
                                        JSONObject parent =  jsonObject.getJSONObject("results");

                                        JSONObject parent1 = parent.getJSONObject("fixture");
                                        JSONObject jsonArray1 = parent1.getJSONObject("home");
                                        String team12=jsonArray1.getString("code");
                                        JSONObject jsonArray2 = parent1.getJSONObject("away");
                                        String team122=jsonArray2.getString("code");

                                        JSONObject series1 = parent1.getJSONObject("series");
                                        String seri1=series1.getString("series_name");

                                        team1.setText(team12);
                                        team2.setText(team122);



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                        }

                    }
                });



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

}
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity31.this,starttingActivity.class);
        startActivity(intent);

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
                       MainActivity31.this.interstitialAd = interstitialAd;
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
