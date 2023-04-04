package com.cricket.crickais.crickcode;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab222 extends Fragment {
    RecyclerView comment;
    RecyclerView list_view;
    RecyclerView list_view1;
    RecyclerView list_view_second;
    LinearLayout tabllayout01,tabllayout;
    List<song> songs;
    List<song> songs1;
    List<song> songs2;
    List<song> songssec;
    List<bowler> bowlers;
    cmntadapt cmntadapt;
    Adapterbatting adapterbatting;
    Adapterbatting adapterbatting1;
    Adapterbowling adapterbowling;
    TextView plr1,team10,team101,team102,team1012,plr,team103,team20,team201,team202,team203,team10121,bar,team2011,team20111,team2012,team20122,team2013;
    RelativeLayout fsc,ssc,ssc1,ptm,pts,start;
    ImageView icon,icon1;
    ProgressBar progressBar2;
    RelativeLayout log;
    LinearLayout vm;
    private Handler mHandler = new Handler();
    private AdView mAdView,mAdView1;
    public Tab222() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_tab222,container,false);
        fsc=rootView.findViewById(R.id.fsc);
        ssc=rootView.findViewById(R.id.ssc);
        pts=rootView.findViewById(R.id.pts);
        icon1=rootView.findViewById(R.id.icon1);
        ssc1=rootView.findViewById(R.id.ssc1);
        team10 = rootView.findViewById(R.id.team10);
        team101 = rootView.findViewById(R.id.team101);
        team102 = rootView.findViewById(R.id.team102);
        team1012 = rootView.findViewById(R.id.team1012);
        team10121=rootView.findViewById(R.id.team10121);
        team103 = rootView.findViewById(R.id.team103);
        ptm=rootView.findViewById(R.id.ptm);
        bar=rootView.findViewById(R.id.bar);
        start=rootView.findViewById(R.id.start);
        plr=rootView.findViewById(R.id.plr);
        icon=rootView.findViewById(R.id.icon);
        team20111=rootView.findViewById(R.id.team20111);
        team20122=rootView.findViewById(R.id.team20122);
        team20 = rootView.findViewById(R.id.team20);
        team201 = rootView.findViewById(R.id.team201);
        team202 = rootView.findViewById(R.id.team202);
        team203 = rootView.findViewById(R.id.team203);
        progressBar2 = rootView.findViewById(R.id.progressBar2);
        team2011 = rootView.findViewById(R.id.team2011);
        team2012 = rootView.findViewById(R.id.team2012);
        team2013 = rootView.findViewById(R.id.team2013);
        log=rootView.findViewById(R.id.log);
        tabllayout01=rootView.findViewById(R.id.tabllayout01);
        tabllayout=rootView.findViewById(R.id.tabllayout);

        comment = rootView.findViewById(R.id.comment);
        list_view = rootView.findViewById(R.id.list_view);
        list_view1 = rootView.findViewById(R.id.list_view1);
        list_view_second=rootView.findViewById(R.id.list_view_second);
        plr1=rootView.findViewById(R.id.plr1);
        songs = new ArrayList<>();
        songs1 = new ArrayList<>();
        songs2 = new ArrayList<>();
        songssec = new ArrayList<>();
        bowlers = new ArrayList<>();

        vm=rootView.findViewById(R.id.vm);
        String strtext11 = getArguments().getString("link");
        vm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), moreview.class);
                intent.putExtra("link",strtext11);
                getContext().startActivity(intent);
            }
        });

        mToastRunnable.run();
        return rootView;


    }
    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            progressBar2.setVisibility(View.VISIBLE);
            String strtext1 = getArguments().getString("link");
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
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    songs.clear();
                                    songs1.clear();
                                    songs2.clear();
                                    songssec.clear();
                                    bowlers.clear();

                                    String yt = asd;
                                    String students_array = yt;
                                    progressBar2.setVisibility(View.GONE);

                                    try {
                                        JSONObject jsonObject = new JSONObject(students_array);
                                        JSONObject parent = jsonObject.getJSONObject("data");
                                        String elect = parent.getString("elected");
                                        String mob = parent.getString("man_of_match_id");
                                        String mos = parent.getString("man_of_series_id");
                                        String stt = parent.getString("status");
                                        if(stt.equals("NS")){
                                            log.setVisibility(View.VISIBLE);
                                        }else {
                                        JSONArray jsonArray1 = parent.getJSONArray("batting");
                                        JSONArray jsonArraybowl = parent.getJSONArray("bowling");
                                        String bv = parent.getString("id");
                                        String note = parent.getString("note");
                                        String lt = parent.getString("localteam_id");
                                        String vt = parent.getString("visitorteam_id");
                                        int lto = Integer.parseInt(lt);
                                        int vto = Integer.parseInt(vt);
                                        JSONArray jsonArray = parent.getJSONArray("balls");
                                        JSONArray jsonArray2 = parent.getJSONArray("runs");
                                        JSONObject firstteam = jsonArray2.getJSONObject(Integer.parseInt("0"));
                                        start.setVisibility(View.VISIBLE);
                                            log.setVisibility(View.GONE);
                                        JSONObject team1obj = firstteam.getJSONObject("team");
                                        String teamname = team1obj.getString("name");
                                        String tc = team1obj.getString("code");
                                        String teamid = team1obj.getString("id");
                                        int tommi = Integer.parseInt(teamid);
                                        String sco = firstteam.getString("score");
                                        String wk = firstteam.getString("wickets");
                                        String over = firstteam.getString("overs");

                                        JSONObject parent1 = parent.getJSONObject("stage");
                                        String sername = parent1.getString("name");

                                            JSONObject lg = parent.getJSONObject("league");
                                            String lgname = lg.getString("name");
                                        bar.setText(lgname);


                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject object = jsonArray.getJSONObject(i);
                                                String ball = object.getString("ball");
                                                JSONObject bn = object.getJSONObject("batsman");
                                                String did1 = bn.getString("fullname");

                                                JSONObject bon = object.getJSONObject("bowler");
                                                String bonf = bon.getString("fullname");
                                                JSONObject score = object.getJSONObject("score");
                                                String scorename = score.getString("name");
                                                String scorerun = score.getString("runs");
                                                int scorii = Integer.parseInt(scorerun);
                                                String scoreid = score.getString("id");
                                                int idd = Integer.parseInt(scoreid);


                                                if (idd == 56 || idd == 58 || idd == 63 || idd == 78 || idd == 79 || idd == 83 || idd == 87) {

                                                    song song = new song();
                                                    song.setPname(ball);
                                                    song.setPrun(scorii);
                                                    song.setPsr(bonf + " to " + did1 + " , " + scorename);

                                                    song.setPsix(scoreid);
                                                    songs.add(song);


                                                } else if (idd == 54) {

                                                    JSONObject resbowl = object.getJSONObject("bowler");
                                                    String bname = resbowl.getString("fullname");
                                                    JSONObject rescatch = object.getJSONObject("catchstump");
                                                    String bcatch = rescatch.getString("fullname");
                                                    song song = new song();
                                                    song.setPname(ball);
                                                    song.setPrun(scorii);


                                                    String sourceString = bonf + " to " + did1 + " , " + "<b>" + "caught " + bcatch + " ball " + bname + ", " + scorename + "</b> ";

                                                    song.setPsr(String.valueOf(Html.fromHtml(sourceString)));

                                                    song.setPsix(scoreid);
                                                    songs.add(song);

                                                } else {
                                                    song song = new song();
                                                    song.setPname(ball);
                                                    song.setPrun(scorii);
                                                    song.setPsr(bonf + " to " + did1 + " , " + scorename);

                                                    song.setPsix(scoreid);
                                                    songs.add(song);

                                                }

                                            }
                                            JSONObject parent2 = parent.getJSONObject("tosswon");
                                            String winteam = parent2.getString("name");

                                            Double a1 = new Double(sco);
                                            Double a2 = new Double(over);
                                            Double a3 = a1 / a2;
                                            team10.setText(teamname);
                                            team101.setText(sco + "-" + wk + " " + "(" + over + ")");
                                            if (elect.equals("batting")) {
                                                team103.setText(winteam + " elected to bat first");
                                            } else {
                                                team103.setText(winteam + " elected to bowl first");
                                            }
                                            team102.setText("CRR : " + String.format(Locale.US, "%.2f", a3));
                                            team1012.setText(stt);

                                            for (int i = 0; i < jsonArray1.length(); i++) {
                                                JSONObject object1 = jsonArray1.getJSONObject(i);

                                                JSONObject bn1 = object1.getJSONObject("batsman");
                                                String did1 = bn1.getString("fullname");
                                                String playim = bn1.getString("image_path");

                                                String score4 = object1.getString("score");
                                                int score = Integer.parseInt(score4);
                                                String six = object1.getString("six_x");
                                                String four = object1.getString("four_x");
                                                String team_id = object1.getString("team_id");
                                                int temmi = Integer.valueOf(team_id);
                                                String ball = object1.getString("ball");
                                                String sr = object1.getString("rate");

                                                JSONObject result = object1.getJSONObject("result");
                                                String resid = result.getString("id");
                                                int pres = Integer.valueOf(resid);
                                                String bid = object1.getString("catch_stump_player_id");
                                                String cid = object1.getString("bowling_player_id");

                                                if (pres == 84 && temmi == tommi) {

                                                    song song1 = new song();
                                                    song1.setPname(did1);
                                                    song1.setPimage(playim);
                                                    song1.setPrun(score);
                                                    song1.setPsix(six);
                                                    song1.setPfour(four);
                                                    song1.setPball(ball);
                                                    song1.setPsr(sr);
                                                    songs1.add(song1);
                                                }

                                            }
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                for (int j = 0; j < jsonArraybowl.length(); j++) {

                                                    JSONObject object11 = jsonArraybowl.getJSONObject(j);
                                                    JSONObject bn11 = object11.getJSONObject("bowler");
                                                    String did2 = bn11.getString("fullname");
                                                    String playimb = bn11.getString("image_path");
                                                    String run1 = object11.getString("runs");
                                                    String teamsi = object11.getString("team_id");
                                                    int tems = Integer.parseInt(teamsi);
                                                    String over1 = object11.getString("overs");
                                                    String med1 = object11.getString("medians");
                                                    String wik1 = object11.getString("wickets");
                                                    String er1 = object11.getString("rate");
                                                    String didid = bn11.getString("id");
                                                    int bowid = Integer.parseInt(didid);
                                                    JSONObject object1 = jsonArray.getJSONObject(i);


                                                    JSONObject bn1 = object1.getJSONObject("bowler");
                                                    String did1 = bn1.getString("fullname");
                                                    String playim = bn1.getString("image_path");
                                                    String playid = bn1.getString("id");
                                                    int plid = Integer.parseInt(playid);


                                                    if (bowid == plid) {
                                                        bowler bowler = new bowler();
                                                        bowler.setPname(did1);
                                                        bowler.setPimage(playim);
                                                        bowler.setPover(over1);
                                                        bowler.setPmedi(med1);
                                                        bowler.setPrrun(run1);
                                                        bowler.setPwik(wik1);
                                                        bowler.setPer(er1);
                                                        bowlers.add(bowler);
                                                    }
                                                }
                                            }


                                            JSONObject secteam = jsonArray2.getJSONObject(Integer.parseInt("1"));
                                            JSONObject team2obj = secteam.getJSONObject("team");
                                            String teamname2 = team2obj.getString("name");
                                            String tc2 = team2obj.getString("code");
                                            String teamid2 = team2obj.getString("id");
                                            String sco2 = secteam.getString("score");
                                            String wk2 = secteam.getString("wickets");
                                            String over2 = secteam.getString("overs");

                                            fsc.setVisibility(View.GONE);
                                            ssc.setVisibility(View.VISIBLE);

                                            list_view.setVisibility(View.GONE);
                                            list_view_second.setVisibility(View.VISIBLE);
                                            Double a11 = new Double(sco2);
                                            Double a22 = new Double(over2);
                                            Double a33 = a11 / a22;
                                            team20.setText(teamname2);
                                            team201.setText(sco2 + "-" + wk2 + " " + "(" + over2 + ")");
                                            team203.setText(note);
                                            team202.setText("CRR : " + String.format(Locale.US, "%.2f", a33));
                                            team10121.setText(stt);
                                            for (int i = 0; i < jsonArray1.length(); i++) {
                                                JSONObject object1 = jsonArray1.getJSONObject(i);

                                                JSONObject bn1 = object1.getJSONObject("batsman");
                                                String did1 = bn1.getString("fullname");
                                                String playim = bn1.getString("image_path");

                                                String score4 = object1.getString("score");
                                                int score = Integer.parseInt(score4);
                                                String six = object1.getString("six_x");
                                                String four = object1.getString("four_x");
                                                String team_id = object1.getString("team_id");
                                                int temmi = Integer.valueOf(team_id);
                                                String ball = object1.getString("ball");
                                                String sr = object1.getString("rate");

                                                JSONObject result = object1.getJSONObject("result");
                                                String resid = result.getString("id");
                                                int pres = Integer.valueOf(resid);
                                                String bid = object1.getString("catch_stump_player_id");
                                                String cid = object1.getString("bowling_player_id");

                                                if (pres == 84 && temmi != tommi) {

                                                    song song1 = new song();
                                                    song1.setPname(did1);
                                                    song1.setPimage(playim);
                                                    song1.setPrun(score);
                                                    song1.setPsix(six);
                                                    song1.setPfour(four);
                                                    song1.setPball(ball);
                                                    song1.setPsr(sr);
                                                    songssec.add(song1);
                                                }

                                            }


                                            if (stt.equals("Finished")) {
                                                ssc.setVisibility(View.GONE);
                                                list_view_second.setVisibility(View.GONE);
                                                tabllayout.setVisibility(View.GONE);
                                                tabllayout01.setVisibility(View.GONE);
                                                list_view1.setVisibility(View.GONE);
                                                ssc1.setVisibility(View.VISIBLE);

                                                team2011.setText(tc);
                                                team20111.setText(sco + "-" + wk + " " + "(" + over + ")");
                                                team2012.setText(tc2);
                                                team20122.setText(sco2 + "-" + wk2 + " " + "(" + over2 + ")");
                                                team2013.setText(note);
                                            }

                                            if (!mob.equals("null")) {
                                                ptm.setVisibility(View.VISIBLE);
                                                JSONObject bn = parent.getJSONObject("manofmatch");
                                                String pln = bn.getString("fullname");
                                                String plim = bn.getString("image_path");

                                                plr.setText(pln);
                                                if (plim.equals("https://cdn.sportmonks.com")) {
                                                    Picasso.get().load("https://www.cricbuzz.com/a/img/v1/75x75/i1/c174146/brian-chari.jpg").into(icon);
                                                } else {
                                                    Picasso.get().load(plim).into(icon);
                                                }

                                            }
                                            if (!mos.equals("null")) {
                                                pts.setVisibility(View.VISIBLE);
                                                JSONObject bn1 = parent.getJSONObject("manofseries");
                                                String pln1 = bn1.getString("fullname");
                                                String plim1 = bn1.getString("image_path");

                                                plr1.setText(pln1);
                                                if (plim1.equals("https://cdn.sportmonks.com")) {
                                                    Picasso.get().load("https://www.cricbuzz.com/a/img/v1/75x75/i1/c174146/brian-chari.jpg").into(icon);
                                                } else {
                                                    Picasso.get().load(plim1).into(icon1);
                                                }

                                            }
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    comment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                    cmntadapt = new cmntadapt(getContext(), songs);
                                    comment.setAdapter(cmntadapt);

                                    list_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
                                    adapterbatting = new Adapterbatting(getContext(), songs1);
                                    list_view.setAdapter(adapterbatting);

                                    list_view_second.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
                                    adapterbatting1 = new Adapterbatting(getContext(), songssec);
                                    list_view_second.setAdapter(adapterbatting1);

                                    list_view1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
                                    adapterbowling = new Adapterbowling(getContext(), bowlers);
                                    list_view1.setAdapter(adapterbowling);





                                }
                            });
                        }
                    }
                }
            });


            mHandler.postDelayed(this, 30000);
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mToastRunnable);


    }
}