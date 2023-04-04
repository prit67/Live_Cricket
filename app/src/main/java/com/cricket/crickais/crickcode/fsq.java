package com.cricket.crickais.crickcode;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class fsq extends AppCompatActivity {
    ArrayList<HashMap<String, String>>
            arrayList2newbowl = new ArrayList<HashMap<String, String>>();
    RecyclerView t1list,t1list1,t1list2,t1list3;
    FirebaseDatabase firebaseDatabase;
    TextView tt1;
    ImageView kar;
    ProgressBar progressBar2;
    List<song> songs;
    List<song> songs2;
    List<song> songs3;
    List<song> songs31;
    adp1 adapter;
RelativeLayout first, first1, first2, first3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fsq);

        songs = new ArrayList<>();
        songs2 = new ArrayList<>();
        songs3 = new ArrayList<>();
        songs31 = new ArrayList<>();
        String matchid = getIntent().getStringExtra("mun");
        String hati = getIntent().getStringExtra("tname");
        progressBar2 = findViewById(R.id.progressBar2);
        t1list = findViewById(R.id.t1list);
        t1list1 = findViewById(R.id.t1list1);
        t1list2 = findViewById(R.id.t1list2);
        t1list3 = findViewById(R.id.t1list3);
        tt1 = findViewById(R.id.tt1);

        first = findViewById(R.id.first);
        first1 = findViewById(R.id.first1);
        first2 = findViewById(R.id.first2);
        first3 = findViewById(R.id.first3);

        kar = findViewById(R.id.kar);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().hide();

        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1=firebaseDatabase.getReference("zandroid").child("ts2");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String stage = snapshot.getValue(String.class);
                String token=stage;

        String ti = getIntent().getStringExtra("team_id");
        String si = getIntent().getStringExtra("season_id");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cricket.sportmonks.com/api/v2.0/teams/"+ti+"/squad/"+si+"?api_token=lhkyue6be0nJnQUrDcqEEVWVRYshXvjLJyNcLNrat9Di7C5qFRLquPXjieDt"+token)
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


                    fsq.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String yt = asd;

                            progressBar2.setVisibility(View.GONE);
                            String students_array = yt;
                            try {
                                JSONObject jsonObject = new JSONObject(students_array);
                                JSONObject parent = jsonObject.getJSONObject("data");
                                String ghj = parent.getString("name");
                                JSONArray jsonArray = parent.getJSONArray("squad");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    JSONObject hj = object.getJSONObject("squad");

                                    tt1.setText(ghj+" "+"Squad");


                                        String playim = object.getString("image_path");
                                        JSONObject object1 = object.getJSONObject("position");
                                        String pos = object1.getString("name");
                                        String did1 = object.getString("fullname");
                                        String baid = object.getString("id");

                                        if(pos.equals("Batsman")) {
                                            song song = new song();
                                            song.setPname(did1);
                                            song.setPout(pos);
                                            song.setPimage(playim);

                                            song.setPid(baid);
                                            songs.add(song);
                                            first.setVisibility(View.VISIBLE);
                                        }

                                    if(pos.equals("Allrounder")) {
                                        song song1 = new song();
                                        song1.setPname(did1);
                                        song1.setPout(pos);
                                        song1.setPimage(playim);

                                        song1.setPid(baid);
                                        songs2.add(song1);
                                        first1.setVisibility(View.VISIBLE);
                                    }
                                    if(pos.equals("Wicketkeeper")) {
                                        song song2 = new song();
                                        song2.setPname(did1);
                                        song2.setPout(pos);
                                        song2.setPimage(playim);

                                        song2.setPid(baid);
                                        songs3.add(song2);
                                        first2.setVisibility(View.VISIBLE);
                                    }
                                    if(pos.equals("Bowler")) {
                                        song song3 = new song();
                                        song3.setPname(did1);
                                        song3.setPout(pos);
                                        song3.setPimage(playim);

                                        song3.setPid(baid);
                                        songs31.add(song3);
                                        first3.setVisibility(View.VISIBLE);
                                    }

                                }


                                    } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            t1list.setLayoutManager(new LinearLayoutManager(fsq.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new adp1(fsq.this, songs);
                            t1list.setAdapter(adapter);

                            t1list1.setLayoutManager(new LinearLayoutManager(fsq.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new adp1(fsq.this, songs2);
                            t1list1.setAdapter(adapter);

                            t1list2.setLayoutManager(new LinearLayoutManager(fsq.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new adp1(fsq.this, songs3);
                            t1list2.setAdapter(adapter);

                            t1list3.setLayoutManager(new LinearLayoutManager(fsq.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new adp1(fsq.this, songs31);
                            t1list3.setAdapter(adapter);

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

    }}