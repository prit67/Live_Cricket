package com.cricket.crickais.crickcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class t1 extends AppCompatActivity {
    ArrayList<HashMap<String, String>>
            arrayList2newbowl = new ArrayList<HashMap<String, String>>();
    RecyclerView t1list,t1list1,t1list2,t1list3;
    RelativeLayout first, first1, first2, first3;
    FirebaseDatabase firebaseDatabase;
    TextView tt1;
    ImageView kar;
    ProgressBar progressBar2;
    List<song> songs;
    List<song> songs2;
    List<song> songs3;
    List<song> songs31;
    com.cricket.crickais.crickcode.adp1 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t1);

        songs = new ArrayList<>();
        songs2 = new ArrayList<>();
        songs3 = new ArrayList<>();
        songs31 = new ArrayList<>();
        String matchid = getIntent().getStringExtra("mun");
        String hati = getIntent().getStringExtra("tname");
        progressBar2 = findViewById(R.id.progressBar2);
        t1list = findViewById(R.id.t1list);
        tt1 = findViewById(R.id.tt1);

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

        String strtext1 = getIntent().getStringExtra("link");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(strtext1)
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


                    t1.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String yt = asd;

                            progressBar2.setVisibility(View.GONE);
                            String students_array = yt;
                            try {
                                JSONObject jsonObject = new JSONObject(students_array);
                                JSONObject parent = jsonObject.getJSONObject("data");
                                JSONArray jsonArray = parent.getJSONArray("lineup");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    JSONObject hj = object.getJSONObject("lineup");
                                    String tid = hj.getString("team_id");
                                    Integer fg = Integer.parseInt(tid);
                                    tt1.setText(hati+" "+"Playing XI");

                                    if (fg == Integer.parseInt(matchid)) {
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

                                }


                                    } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            t1list.setLayoutManager(new LinearLayoutManager(t1.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new com.cricket.crickais.crickcode.adp1(t1.this, songs);
                            t1list.setAdapter(adapter);

                            t1list1.setLayoutManager(new LinearLayoutManager(t1.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new adp1(t1.this, songs2);
                            t1list1.setAdapter(adapter);

                            t1list2.setLayoutManager(new LinearLayoutManager(t1.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new adp1(t1.this, songs3);
                            t1list2.setAdapter(adapter);

                            t1list3.setLayoutManager(new LinearLayoutManager(t1.this, LinearLayoutManager.VERTICAL, false));
                            adapter = new adp1(t1.this, songs31);
                            t1list3.setAdapter(adapter);

                        }
                    });

                }
            }
        });

    }}