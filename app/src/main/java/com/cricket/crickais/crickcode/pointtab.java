package com.cricket.crickais.crickcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.interstitial.InterstitialAd;
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
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class pointtab extends AppCompatActivity {
ImageView imagepoint,kar,imagepoint1;
    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;
    private MyAdapterpoint adapter;
    List<homing> homes;
    private InterstitialAd interstitialAd;
    private final String TAG ="FB_ADS";
    private RecyclerView recview;
    TextView tt1;
    RelativeLayout first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointtab);
        getSupportActionBar().hide();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        homes = new ArrayList<>();
        recview = findViewById(R.id.recyclerview3);
        first=findViewById(R.id.first);
        String stage = getIntent().getStringExtra("link1stage");
        String lg = getIntent().getStringExtra("link1stage1");
        kar = (ImageView) findViewById(R.id.kar);
        tt1 = findViewById(R.id.tt1);
        tt1.setText(lg);
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
                String stage1 = snapshot.getValue(String.class);
                String token=stage1;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://cricket.sportmonks.com/api/v2.0/standings/stage/"+stage+"?api_token=lhkyue6be0nJnQUrDcqEEVWVRYshXvjLJyNcLNrat9Di7C5qFRLquPXjieDt"+token+"&include=team")
                        .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String asd = response.body().string();


                        pointtab.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String yt = asd;
                                String students_array = yt;
                                try {
                                    JSONObject jsonObject = new JSONObject(students_array);
                                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                                    progressBar.setVisibility(View.GONE);

                                    for (int i = 0; i < jsonArray1.length(); i++) {
                                        JSONObject object1 = jsonArray1.getJSONObject(i);
                                        String play = object1.getString("played");
                                        String won = object1.getString("won");

                                        String lost = object1.getString("lost");

                                        String nr = object1.getString("noresult");
                                        String pts = object1.getString("points");
                                        String nrr = object1.getString("netto_run_rate");

                                        JSONObject tn = object1.getJSONObject("team");
                                        String tname= tn.getString("code");
                                        first.setVisibility(View.VISIBLE);


                                            homing song1 = new homing();

                                            song1.setTeam1(tname);
                                            song1.setSta(play);
                                            song1.setTeam2(won);
                                            song1.setTeam(lost);
                                            song1.setTeam3(nr);
                                            song1.setDate(pts);
                                            song1.setBar(nrr);

                                            homes.add(song1);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                recview.setLayoutManager(new LinearLayoutManager(pointtab.this, LinearLayoutManager.VERTICAL, false));
                                adapter = new MyAdapterpoint(pointtab.this, (ArrayList<homing>) homes);
                                recview.setAdapter(adapter);
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