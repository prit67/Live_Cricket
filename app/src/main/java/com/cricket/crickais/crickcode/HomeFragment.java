package com.cricket.crickais.crickcode;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    private ArrayList<Model> songs;
    private RecyclerView recyclerView1,recyclerviewvid;
    private RecyclerView recview;
    List<homing> homes;
    homageadapt homageadapt;
    private MyAdapter adapter;
    private MyAdapter12 adapter11;
    private ArrayList<Model> list1;
    private ArrayList<Model> list2;
    ProgressBar progressBar;
    private ArrayList<Model> list;
    RelativeLayout mf110;
    private InterstitialAd interstitialAd;
    private final String TAG = "FB_ADS";
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("News");
    private MyAdapter12vid adaptervid;
    FirebaseDatabase firebaseDatabase;
    LinearLayout lhor;
    RelativeLayout tap,seri,third,fourth;
    TextView alls1;
    private Handler mHandler = new Handler();
    private DatabaseReference root2 = db.getReference("video");
    private DatabaseReference databaseReference1=db.getReference("zandroid").child("ts2");
    private AdView mAdView,mAdView1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        homes = new ArrayList<>();
        recview=rootView.findViewById(R.id.recyclerview3);
progressBar=rootView.findViewById(R.id.progressBar);
        mAdView=(com.google.android.gms.ads.AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        alls1=rootView.findViewById(R.id.alls1);
        alls1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), newsview.class);
                startActivity(intent);
            }
        });

        mToastRunnable.run();


        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1=firebaseDatabase.getReference("zandroid").child("ts2");







        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        loadAd();

        third=rootView.findViewById(R.id.third);
        fourth=rootView.findViewById(R.id.fourth);
        songs = new ArrayList<>();
        list = new ArrayList<>();
        recyclerView1 = (RecyclerView) rootView.findViewById(R.id.recyclerview1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new MyAdapter(getContext(), list);

        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1n=firebaseDatabase.getReference("rapidlive");
        databaseReference1n.addValueEventListener(new ValueEventListener() {
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
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        songs.clear();

                                        progressBar.setVisibility(View.GONE);
                                        String yt = asd;
                                        String students_array = yt;
                                        third.setVisibility(View.VISIBLE);

                                        try {
                                            JSONObject jsonObject = new JSONObject(students_array);
                                            JSONArray jsonArray = jsonObject.getJSONArray("articles");
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject object = jsonArray.getJSONObject(i);

                                                String title = object.getString("title");
                                                String title1 = object.getString("summary");
                                                String link4 = object.getString("link");
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
                                                song1.setTs1(link4);
                                                songs.add(song1);


                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
                                        adapter11 = new MyAdapter12(getContext(), songs);
                                        recyclerView1.setAdapter(adapter11);






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


        recyclerviewvid=rootView.findViewById(R.id.recyclerviewvid);
        recyclerviewvid.setHasFixedSize(true);
        recyclerviewvid.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        list2 = new ArrayList<>();
        adaptervid = new MyAdapter12vid(getContext(), list2);
        recyclerviewvid.setAdapter(adaptervid);
        root2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Model model4 = dataSnapshot.getValue(Model.class);
                    list2.add(model4);
                    progressBar.setVisibility(View.GONE);
                    fourth.setVisibility(View.VISIBLE);
                }
                adaptervid.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String appId = "ca-app-pub-3428954992805972~9198481054";


        return rootView;
    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stage = snapshot.getValue(String.class);
                    String token=stage;
                    Date d = new Date();
                    CharSequence s  = DateFormat.format("yyyy-MM-dd ", d.getTime());
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
                    String reg_date = df.format(c.getTime());
                    c.add(Calendar.DATE, -2);  // number of days to add
                    String start_date = df.format(c.getTime());

                    Calendar c1 = Calendar.getInstance();
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
                    c1.add(Calendar.DATE, 1);  // number of days to add
                    String end_date = df1.format(c1.getTime());

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://cricket.sportmonks.com/api/v2.0/fixtures?api_token="+token+"&include=runs,tosswon,localteam,visitorteam,league,stage&filter[starts_between]=" + df.format(c.getTime()) + "," + df1.format(c1.getTime()))
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

                                if (getActivity() != null) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @RequiresApi(api = Build.VERSION_CODES.O)
                                        @Override
                                        public void run() {
                                            homes.clear();
                                            String yt = asd;
                                            String students_array = yt;
                                            try {
                                                JSONObject jsonObject = new JSONObject(students_array);
                                                JSONArray jsonArray1 = jsonObject.getJSONArray("data");


                                                for (int i = 0; i < jsonArray1.length(); i++) {
                                                    JSONObject object1 = jsonArray1.getJSONObject(i);
                                                    String newest = object1.getString("id");
                                                    String note = object1.getString("note");

                                                    String stage = object1.getString("stage_id");

                                                    String stat = object1.getString("starting_at");
                                                    String elect = object1.getString("elected");
                                                    JSONObject bn1 = object1.getJSONObject("localteam");
                                                    String locname = bn1.getString("name");
                                                    String loccode = bn1.getString("code");
                                                    String locimg = bn1.getString("image_path");
                                                    int locid = Integer.parseInt(bn1.getString("id"));

                                                    JSONObject bn2 = object1.getJSONObject("visitorteam");
                                                    String visitname = bn2.getString("name");
                                                    String visitcode = bn2.getString("code");
                                                    String visitimg = bn2.getString("image_path");
                                                    String visitid = bn2.getString("id");

                                                    JSONObject st = object1.getJSONObject("stage");
                                                    String stname = st.getString("name");
                                                    String status = object1.getString("status");
                                                    String non = String.valueOf(note);

                                                    JSONObject lg = object1.getJSONObject("league");
                                                    String lgname = lg.getString("name");


                                                    String round = object1.getString("round");
                                                    String dateStr = stat;

                                                    SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
                                                    Date dateObj = curFormater.parse(dateStr);


                                                    Clock clock = Clock.systemDefaultZone();
                                                    String abcd = String.valueOf(clock.getZone());
                                                    String date = dateStr;
                                                    ZonedDateTime dateTime = ZonedDateTime.parse(date);
                                                    String newDateStr = dateTime.withZoneSameInstant(ZoneId.of(abcd)).format(DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm a"));





                                                    if (status.equals("NS")) {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setNext(lgname);
                                                        } else {
                                                            song1.setNext(stname);
                                                        }
                                                        song1.setTeam1(locname);
                                                        song1.setSta(locimg);
                                                        song1.setTeam2(visitname);
                                                        song1.setTeam(visitimg);
                                                        song1.setTeam3(stage);
                                                        song1.setDate(newDateStr);
                                                        song1.setBar(newest);
                                                        song1.setNotification("Upcoming");
                                                        song1.setTs1(non);
                                                        song1.setTs2("");
                                                        song1.setVenue(stat);
                                                        song1.setIfti("xx"+stat);
                                                        homes.add(song1);
                                                    }else if (status.equals("Delayed")) {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setNext(lgname);
                                                        } else {
                                                            song1.setNext(stname);
                                                        }
                                                        song1.setTeam1(locname);
                                                        song1.setSta(locimg);
                                                        song1.setTeam2(visitname);
                                                        song1.setTeam(visitimg);
                                                        song1.setTeam3(stage);
                                                        song1.setDate(note);
                                                        song1.setBar(newest);
                                                        song1.setTs1("");
                                                        song1.setTs2("");
                                                        song1.setNotification("Delayed");
                                                        song1.setVenue(stat);
                                                        song1.setIfti("xx"+stat);
                                                        homes.add(song1);
                                                    }else if (status.equals("Cancl.")) {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setNext(lgname);
                                                        } else {
                                                            song1.setNext(stname);
                                                        }
                                                        song1.setTeam1(locname);
                                                        song1.setSta(locimg);
                                                        song1.setTeam2(visitname);
                                                        song1.setTeam(visitimg);
                                                        song1.setTeam3(stage);
                                                        song1.setDate(note);
                                                        song1.setBar(newest);
                                                        song1.setTs1("");
                                                        song1.setTs2("");
                                                        song1.setNotification("Cancl.");
                                                        song1.setVenue(stat);
                                                        song1.setIfti("xx"+stat);
                                                        homes.add(song1);
                                                    }


                                                    else if (status.equals("Int.")) {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setNext(lgname);
                                                        } else {
                                                            song1.setNext(stname);
                                                        }
                                                        song1.setTeam1(locname);
                                                        song1.setSta(locimg);
                                                        song1.setTeam2(visitname);
                                                        song1.setTeam(visitimg);
                                                        song1.setTeam3(stage);
                                                        song1.setDate(note);
                                                        song1.setBar(newest);
                                                        song1.setNotification("Int.");
                                                        song1.setTs1("");
                                                        song1.setTs2("");
                                                        song1.setIfti("xx"+stat);
                                                        song1.setVenue(stat);
                                                        homes.add(song1);
                                                    }


                                                    else if (status.equals("Aban.")) {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setNext(lgname);
                                                        } else {
                                                            song1.setNext(stname);
                                                        }
                                                        song1.setTeam1(locname);
                                                        song1.setSta(locimg);
                                                        song1.setTeam2(visitname);
                                                        song1.setTeam(visitimg);
                                                        song1.setDate(non);
                                                        song1.setBar(newest);
                                                        song1.setNotification("Aban");
                                                        song1.setTs1("");
                                                        song1.setTs2("");
                                                        song1.setVenue(stat);
                                                        song1.setIfti("xx"+stat);
                                                        song1.setTeam3(newDateStr);
                                                        homes.add(song1);
                                                    }  else if (status.equals("Postp.")) {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setNext(lgname);
                                                        } else {
                                                            song1.setNext(stname);
                                                        }
                                                        song1.setTeam1(locname);
                                                        song1.setSta(locimg);
                                                        song1.setTeam2(visitname);
                                                        song1.setTeam(visitimg);
                                                        song1.setDate("Match Postponed");
                                                        song1.setBar(newest);
                                                        song1.setNotification("Postp");
                                                        song1.setTs1("");
                                                        song1.setTs2("");
                                                        song1.setVenue(stat);
                                                        song1.setIfti("xx"+stat);
                                                        song1.setTeam3(newDateStr);
                                                        homes.add(song1);
                                                    }


                                                    else if (status.equals("1st Innings") && elect.equals("batting")) {
                                                        JSONArray jsonArray = object1.getJSONArray("runs");
                                                        JSONObject parent2 = object1.getJSONObject("tosswon");
                                                        String winteam = parent2.getString("name");

                                                        JSONObject object21 = jsonArray.getJSONObject(Integer.parseInt("0"));
                                                        int runteamid1 = Integer.parseInt(object21.getString("team_id"));
                                                        String score1 = object21.getString("score");
                                                        String wkts1 = object21.getString("wickets");
                                                        String over1 = object21.getString("overs");

                                                        if (locid == runteamid1) {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bat first");
                                                            song1.setBar(newest);
                                                            song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setTs2("");
                                                            song1.setTeam3(stage);
                                                            song1.setNotification("Live");
                                                            song1.setVenue(stat);
                                                            song1.setIfti("zz"+stat);
                                                            homes.add(song1);
                                                        } else {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bat first");
                                                            song1.setBar(newest);
                                                            song1.setTs1("");
                                                            song1.setTeam3(stage);
                                                            song1.setNotification("Live");
                                                            song1.setIfti("zz"+stat);
                                                            song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);


                                                        }

                                                    } else if (status.equals("1st Innings") && elect.equals("bowling")) {
                                                        JSONArray jsonArray = object1.getJSONArray("runs");
                                                        JSONObject parent2 = object1.getJSONObject("tosswon");
                                                        String winteam = parent2.getString("name");

                                                        JSONObject object21 = jsonArray.getJSONObject(Integer.parseInt("0"));
                                                        int runteamid1 = Integer.parseInt(object21.getString("team_id"));
                                                        String score1 = object21.getString("score");
                                                        String wkts1 = object21.getString("wickets");
                                                        String over1 = object21.getString("overs");

                                                        if (locid == runteamid1) {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bowl first");
                                                            song1.setBar(newest);
                                                            song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setTs2("");
                                                            song1.setTeam3(stage);
                                                            song1.setNotification("Live");
                                                            song1.setIfti("zz"+stat);
                                                            song1.setVenue(stat);
                                                            homes.add(song1);
                                                        } else {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bowl first");
                                                            song1.setBar(newest);
                                                            song1.setTs1("");
                                                            song1.setTeam3(stage);
                                                            song1.setNotification("Live");
                                                            song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setVenue(stat);
                                                            song1.setIfti("zz"+stat);
                                                            homes.add(song1);


                                                        }

                                                    } else if (status.equals("Innings Break") && elect.equals("batting")) {
                                                        JSONArray jsonArray = object1.getJSONArray("runs");
                                                        JSONObject parent2 = object1.getJSONObject("tosswon");
                                                        String winteam = parent2.getString("name");

                                                        JSONObject object21 = jsonArray.getJSONObject(Integer.parseInt("0"));
                                                        int runteamid1 = Integer.parseInt(object21.getString("team_id"));
                                                        String score1 = object21.getString("score");
                                                        String wkts1 = object21.getString("wickets");
                                                        String over1 = object21.getString("overs");

                                                        if (locid == runteamid1) {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bat first");
                                                            song1.setBar(newest);
                                                            song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setTs2("");
                                                            song1.setNotification("Live");
                                                            song1.setIfti("zz"+stat);
                                                            song1.setTeam3(stage);
                                                            song1.setVenue(stat);
                                                            homes.add(song1);
                                                        } else {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bat first");
                                                            song1.setBar(newest);
                                                            song1.setTs1("");
                                                            song1.setTeam3(stage);
                                                            song1.setNotification("Live");
                                                            song1.setIfti("zz"+stat);
                                                            song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);


                                                        }

                                                    } else if (status.equals("Innings Break") && elect.equals("bowling")) {
                                                        JSONArray jsonArray = object1.getJSONArray("runs");
                                                        JSONObject parent2 = object1.getJSONObject("tosswon");
                                                        String winteam = parent2.getString("name");

                                                        JSONObject object21 = jsonArray.getJSONObject(Integer.parseInt("0"));
                                                        int runteamid1 = Integer.parseInt(object21.getString("team_id"));
                                                        String score1 = object21.getString("score");
                                                        String wkts1 = object21.getString("wickets");
                                                        String over1 = object21.getString("overs");

                                                        if (locid == runteamid1) {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bowl first");
                                                            song1.setBar(newest);
                                                            song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setTs2("");
                                                            song1.setTeam3(stage);
                                                            song1.setNotification("Live");
                                                            song1.setIfti("zz"+stat);
                                                            song1.setVenue(stat);
                                                            homes.add(song1);
                                                        } else {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bowl first");
                                                            song1.setBar(newest);
                                                            song1.setTs1("");
                                                            song1.setTeam3(stage);
                                                            song1.setNotification("Live");
                                                            song1.setIfti("zz"+stat);
                                                            song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);


                                                        }

                                                    } else if (status.equals("NS") && elect.equals("batting")) {
                                                        JSONArray jsonArray = object1.getJSONArray("runs");
                                                        JSONObject parent2 = object1.getJSONObject("tosswon");
                                                        String winteam = parent2.getString("name");

                                                        JSONObject object21 = jsonArray.getJSONObject(Integer.parseInt("0"));
                                                        int runteamid1 = Integer.parseInt(object21.getString("team_id"));
                                                        String score1 = object21.getString("score");
                                                        String wkts1 = object21.getString("wickets");
                                                        String over1 = object21.getString("overs");

                                                        if (locid == runteamid1) {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bat first");
                                                            song1.setBar(newest);
                                                            song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setTs2("");
                                                            song1.setTeam3(stage);
                                                            song1.setIfti("zz"+stat);
                                                            song1.setNotification("Live");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);
                                                        } else {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(winteam + " elected to bat first");
                                                            song1.setBar(newest);
                                                            song1.setTs1("");
                                                            song1.setTeam3(stage);
                                                            song1.setIfti("zz"+stat);
                                                            song1.setNotification("Live");
                                                            song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);


                                                        }

                                                    } else if (status.equals("NS") && elect.equals("bowling")) {
                                                        JSONArray jsonArray = object1.getJSONArray("runs");
                                                        JSONObject parent2 = object1.getJSONObject("tosswon");
                                                        String winteam = parent2.getString("name");

                                                        JSONObject object21 = jsonArray.getJSONObject(Integer.parseInt("0"));
                                                        int runteamid1 = Integer.parseInt(object21.getString("team_id"));
                                                        String score1 = object21.getString("score");
                                                        String wkts1 = object21.getString("wickets");
                                                        String over1 = object21.getString("overs");

                                                        if (locid == runteamid1) {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setIfti("zz"+stat);
                                                            song1.setDate(winteam + " elected to bowl first");
                                                            song1.setBar(newest);
                                                            song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setTs2("");
                                                            song1.setNotification("Live");
                                                            song1.setTeam3(stage);
                                                            song1.setVenue(stat);
                                                            homes.add(song1);
                                                        } else {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setDate(winteam + " elected to bowl first");
                                                            song1.setBar(newest);
                                                            song1.setTs1("");
                                                            song1.setTeam3(stage);
                                                            song1.setIfti("zz"+stat);
                                                            song1.setNotification("Live");
                                                            song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);


                                                        }

                                                    } else if (!status.equals("NS")) {

                                                        JSONArray jsonArray = object1.getJSONArray("runs");


                                                        JSONObject object21 = jsonArray.getJSONObject(Integer.parseInt("0"));
                                                        int runteamid1 = Integer.parseInt(object21.getString("team_id"));
                                                        String score1 = object21.getString("score");
                                                        String wkts1 = object21.getString("wickets");
                                                        String over1 = object21.getString("overs");

                                                        JSONObject object22 = jsonArray.getJSONObject(Integer.parseInt("1"));
                                                        String runteamid2 = object22.getString("team_id");
                                                        String score2 = object22.getString("score");
                                                        String wkts2 = object22.getString("wickets");
                                                        String over2 = object22.getString("overs");

                                                        if (locid == runteamid1) {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(note);
                                                            song1.setBar(newest);
                                                            song1.setNotification(status);
                                                            if(status.equals("Finished")){
                                                                song1.setIfti("aa"+stat);
                                                            }else{
                                                                song1.setIfti("zz"+stat);
                                                            }
                                                            song1.setTeam3(stage);
                                                            song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setTs2(score2 + "-" + wkts2 + " (" + over2 + ")");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);
                                                        } else {
                                                            homing song1 = new homing();
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setMatchno(lgname + " - " + round);
                                                            } else {
                                                                song1.setMatchno(stname + " - " + round);
                                                            }
                                                            if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                                song1.setNext(lgname);
                                                            } else {
                                                                song1.setNext(stname);
                                                            }
                                                            if(visitcode.length()>2){
                                                                song1.setTeam1(loccode+"  ");
                                                            }else{
                                                                song1.setTeam1(loccode);
                                                            }
                                                            song1.setSta(locimg);

                                                            if(loccode.length()>2){
                                                                song1.setTeam2(visitcode+"  ");
                                                            }else{
                                                                song1.setTeam2(visitcode);
                                                            }
                                                            song1.setTeam(visitimg);
                                                            song1.setDate(note);
                                                            song1.setBar(newest);
                                                            song1.setTeam3(stage);
                                                            song1.setNotification(status);
                                                            if(status.equals("Finished")){
                                                                song1.setIfti("aa"+stat);
                                                            }else{
                                                                song1.setIfti("zz"+stat);
                                                            }
                                                            song1.setTs1(score2 + "-" + wkts2 + " (" + over2 + ")");
                                                            song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                            song1.setVenue(stat);
                                                            homes.add(song1);


                                                        }


                                                    }
                                                    Collections.sort(homes, new Comparator<homing>() {
                                                        @Override
                                                        public int compare(homing homing, homing t1) {
                                                            return t1.getIfti().compareTo(homing.getIfti());
                                                        }
                                                    });

                                                }

                                            } catch (JSONException | ParseException e) {
                                                e.printStackTrace();
                                            }
                                            recview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                                            homageadapt = new homageadapt(getContext(), homes);
                                            recview.setAdapter(homageadapt);


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
            mHandler.postDelayed(this, 22000);
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mToastRunnable);


    }

    public void loadAd() {
        AdRequest adRequest4 = new AdRequest.Builder().build();
        com.google.android.gms.ads.interstitial.InterstitialAd.load(
                getContext(), getString(R.string.inter1),
                adRequest4,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        HomeFragment.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");


                    }


                });

    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            interstitialAd.show(getActivity());
        } else {
            Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();

        }


    }

}
