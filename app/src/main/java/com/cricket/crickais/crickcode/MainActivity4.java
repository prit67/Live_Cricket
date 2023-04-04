package com.cricket.crickais.crickcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity4 extends AppCompatActivity {
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mtabLayout;
    private InterstitialAd interstitialAd;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private FragmentPagerAdapter mTabAccessorAdapter;
    private RelativeLayout main_page_toolbar1;
    ImageView go;
    private final String TAG = "FB_ADS";
    TextView team1;
    ImageView kar;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();
        mtabLayout = findViewById(R.id.main_tab);
        mViewPager = findViewById(R.id.main_tabs_pager);
team1=findViewById(R.id.team1);
        kar=findViewById(R.id.kar);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1=firebaseDatabase.getReference("zandroid").child("ts2");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stage = snapshot.getValue(String.class);
                String token=stage;


                String matchid10 = getIntent().getStringExtra("link");
                String matchid = "https://cricket.sportmonks.com/api/v2.0/players/"+matchid10+"?api_token=lhkyue6be0nJnQUrDcqEEVWVRYshXvjLJyNcLNrat9Di7C5qFRLquPXjieDt"+token+"&include=career,country,teams,currentteams,career.season";

                String matchid1 = "https://cricket.sportmonks.com/api/v2.0/players/"+matchid10+"?api_token=lhkyue6be0nJnQUrDcqEEVWVRYshXvjLJyNcLNrat9Di7C5qFRLquPXjieDt"+token+"&include=career,country,teams,currentteams,career.season";

                mTabAccessorAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


                    @NonNull
                    @Override
                    public Fragment getItem(int position) {

                        switch (position) {
                            case 0:
                                player_pro pp = new player_pro();
                                Bundle bundle = new Bundle();
                                bundle.putString("link", matchid);
                                pp.setArguments(bundle);

                                return pp;

                            case 1:
                                bat_c bc = new bat_c();
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("link1", matchid1);
                                bc.setArguments(bundle1);
                                return bc;
                            case 2:
                                ball_c balc = new ball_c();
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("link1", matchid1);
                                balc.setArguments(bundle2);
                                return balc;
                            case 3:
                                Tab444 tab444 = new Tab444();
                                Bundle bundle4 = new Bundle();
                                bundle4.putString("link1", matchid);
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
                        switch (position) {
                            case 0:
                                return "INFO";
                            case 1:
                                return "BATTING";
                            case 2:
                                return "BOWLING";

                            case 3:
                                return "NEWS";

                        }
                        return null;
                    }

                };
                mViewPager.setAdapter(mTabAccessorAdapter);


                mtabLayout.setupWithViewPager(mViewPager);
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(matchid)
                        .get()

                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String asd = response.body().string();


                            MainActivity4.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String students_array = asd;


                                    try {
                                        JSONObject jsonObject = new JSONObject(students_array);
                                        JSONObject parent = jsonObject.getJSONObject("data");

                                        JSONArray jsonArray = parent.getJSONArray("career");
                                        String name = parent.getString("fullname");
                                        String image = parent.getString("image_path");
                                        JSONObject ch = parent.getJSONObject("position");
                                        String pos = ch.getString("name");
                                        JSONObject coun = parent.getJSONObject("country");
                                        String country1 = coun.getString("name");
                                        team1.setText(name);
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
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
