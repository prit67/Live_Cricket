package com.cricket.crickais.crickcode;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class newsview extends AppCompatActivity {
    private RecyclerView recyclerView3;
    ProgressBar progressBar;
    ImageView kar,img98;
    private MyAdapter11 adapter11;
    private ArrayList<Model> list;
    private ArrayList<Model> songs;
    FirebaseDatabase firebaseDatabase;
    private InterstitialAd interstitialAd;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("News");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsview);
        getSupportActionBar().hide();
        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerview3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(newsview.this));
        list = new ArrayList<>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        kar=(ImageView)findViewById(R.id.kar);

        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        songs = new ArrayList<>();
        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1=firebaseDatabase.getReference("rapid");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model userInformation =snapshot.getValue(Model.class);
                String host = userInformation.getTs1();
                String hostapi = userInformation.getTs2();
                String link = userInformation.getTeam1();


                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(link)
                        .get()
                        .addHeader("x-rapidapi-host", host)
                        .addHeader("x-rapidapi-key", hostapi)
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
                            if (newsview.this != null) {
                                newsview.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        songs.clear();

                                        progressBar.setVisibility(View.GONE);
                                        String yt = asd;
                                        String students_array = yt;


                                        try {
                                            JSONObject jsonObject = new JSONObject(students_array);
                                            JSONArray jsonArray = jsonObject.getJSONArray("articles");
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject object = jsonArray.getJSONObject(i);

                                                String title = object.getString("title");
                                                String title1 = object.getString("summary");
                                                String image = object.getString("media");
                                                String content = object.getString("summary");
                                                String author = object.getString("author");
                                                String date = object.getString("published_date");


                                                Model song1 = new Model();
                                                song1.setNewshead(title);
                                                song1.setNewssub(title1);
                                                song1.setNewsimg(image);
                                                song1.setNews(content);
                                                song1.setTeam1(author);
                                                song1.setTeam2(date);

                                                songs.add(song1);


                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        recyclerView3.setLayoutManager(new LinearLayoutManager(newsview.this, LinearLayoutManager.VERTICAL, false));
                                        adapter11 = new MyAdapter11(newsview.this, songs);
                                        recyclerView3.setAdapter(adapter11);






                                    }
                                });
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void onBackPressed(){
        AdRequest adRequest4 = new AdRequest.Builder().build();
        InterstitialAd.load(
                this, getString(R.string.inter2),
                adRequest4,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        newsview.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        showInterstitial();

                    }


                });
        super.onBackPressed();

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