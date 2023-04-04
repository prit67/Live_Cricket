package com.cricket.crickais.crickcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
public class Tab333 extends Fragment {

    RecyclerView listViewnew1,list_viewnew11;
    RecyclerView listView1new1;

    ListView listViewnew2;
    ListView listView1new2;
    ListView listViewnew3;
    ListView listView1new3;
    RecyclerView listView,listView1,recview;
    RelativeLayout textii,rela1,text02,  text02new2,textiinew2,rela1new2,text02new3,textiinew3,rela1new3;
    RelativeLayout textiinew1,rela1new1,text02new1,visit,first,second,third, fourth;
    ArrayList<HashMap<String, String>>
            arrayList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>>
            arrayListnewbowl = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>>
            arrayListnewbowl3rd = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>>
            arrayListnewbowl4th = new ArrayList<HashMap<String, String>>();

    ArrayList<HashMap<String, String>>
            arrayList2 = new ArrayList<HashMap<String, String>>();
    private AdView mAdView,mAdView1;
    ArrayList<HashMap<String, String>>
            arrayList2newbowl = new ArrayList<HashMap<String, String>>();

    ArrayList<HashMap<String, String>>
            arrayList2newbowl3rd = new ArrayList<HashMap<String, String>>();

    ArrayList<HashMap<String, String>>
            arrayList2newbowl4th = new ArrayList<HashMap<String, String>>();


    FirebaseDatabase firebaseDatabase;

    ArrayAdapter arrayAdapter1;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList22 = new ArrayList<>();
    ArrayList<String> arrayList1newbowl = new ArrayList<>();
    ArrayList<String> arrayList1newbowl3rd = new ArrayList<>();
    ArrayList<String> arrayList1newbowl4th = new ArrayList<>();
    TextView texti,team1,run,ref1,exn,exd,run1,ref12,ref01,tott1,ref,fowtext,team11,run01,ref001,ref11;
    TextView textinew1,team1new1,runnew1,ref1new1,exnnew1,exdnew1,run1new1,ref12new1,ref01new1,tott1new1,refnew1,fowtextnew1,team11new1,run01new1,
            ref001new1,ref11new1,stricker_player;

    TextView team1new2,runnew2,ref1new2,exnnew2,exdnew2,run1new2,ref12new2,ref01new2,tott1new2,refnew2,fowtextnew2,team11new2,run01new2,
            ref001new2,ref11new2;

    TextView team1new3,runnew3,ref1new3,exnnew3,exdnew3,run1new3,ref12new3,ref01new3,tott1new3,refnew3,fowtextnew3,team11new3,run01new3,
            ref001new3,ref11new3;
    RecyclerView listView11;
    ProgressBar progressBar2;
    List<song> songs;
    List<song> songs2;
    List<song> songs3;
    List<song> songs31;
    List<bowler> bowlers;
    List<bowler> bowlers1;
    com.cricket.crickais.crickcode.Adapter adapter;
    com.cricket.crickais.crickcode.Adapter adapter1;
    Adapter1 adapterbowl;
    Adapter1 adapterbowl1;
    Adapterfow adapterfow;
    Adapterfow adapterfow1;

    public Tab333() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_tab333,container,false);
        songs = new ArrayList<>();
        songs2 = new ArrayList<>();
        songs3 = new ArrayList<>();
        songs31 = new ArrayList<>();
        bowlers = new ArrayList<>();
        bowlers1 = new ArrayList<>();
        list_viewnew11=rootView.findViewById(R.id.list_viewnew11);
        text02new2=rootView.findViewById(R.id.text02new2);
        textiinew2=rootView.findViewById(R.id.textiinew2);
        rela1new2=rootView.findViewById(R.id.rela1new2);
        third=rootView.findViewById(R.id.third);
        team1new2=rootView.findViewById(R.id.team1new2);
        runnew2=rootView.findViewById(R.id.runnew2);
        run1new2=rootView.findViewById(R.id.run1new2);
        ref1new2=rootView.findViewById(R.id.ref1new2);
        ref12new2=rootView.findViewById(R.id.ref12new2);
        exdnew2=rootView.findViewById(R.id.exdnew2);
        exnnew2=rootView.findViewById(R.id.exnnew2);
        ref01new2=rootView.findViewById(R.id.ref01new2);
        tott1new2=rootView.findViewById(R.id.tott1new2);
        refnew2=rootView.findViewById(R.id.refnew2);
        fowtextnew2=rootView.findViewById(R.id.fowtextnew2);
        team11new2=rootView.findViewById(R.id.team11new2);
        run01new2=rootView.findViewById(R.id.run01new2);
        ref001new2=rootView.findViewById(R.id.ref001new2);
        ref11new2=rootView.findViewById(R.id.ref11new2);
        listView11=rootView.findViewById(R.id.list_view11);
        text02new3=rootView.findViewById(R.id.text02new3);
        textiinew3=rootView.findViewById(R.id.textiinew3);
        rela1new3=rootView.findViewById(R.id.rela1new3);
        fourth=rootView.findViewById(R.id.fourth);
        team1new3=rootView.findViewById(R.id.team1new3);
        runnew3=rootView.findViewById(R.id.runnew3);
        run1new3=rootView.findViewById(R.id.run1new3);
        ref1new3=rootView.findViewById(R.id.ref1new3);
        ref12new3=rootView.findViewById(R.id.ref12new3);
        exdnew3=rootView.findViewById(R.id.exdnew3);
        exnnew3=rootView.findViewById(R.id.exnnew3);
        ref01new3=rootView.findViewById(R.id.ref01new3);
        tott1new3=rootView.findViewById(R.id.tott1new3);
        refnew3=rootView.findViewById(R.id.refnew3);
        fowtextnew3=rootView.findViewById(R.id.fowtextnew3);
        team11new3=rootView.findViewById(R.id.team11new3);
        run01new3=rootView.findViewById(R.id.run01new3);
        ref001new3=rootView.findViewById(R.id.ref001new3);
        ref11new3=rootView.findViewById(R.id.ref11new3);


        textii=rootView.findViewById(R.id.textii);
        stricker_player=rootView.findViewById(R.id.stricker_player);
        rela1=rootView.findViewById(R.id.rela1);
        text02=rootView.findViewById(R.id.text02);
        text02new1=rootView.findViewById(R.id.text02new1);
        visit= rootView.findViewById(R.id.visit);
        progressBar2 = rootView.findViewById(R.id.progressBar2);
        textiinew1=rootView.findViewById(R.id.textiinew1);
        rela1new1=rootView.findViewById(R.id.rela1new1);
        mAdView=(com.google.android.gms.ads.AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        first=rootView.findViewById(R.id.first);
        second=rootView.findViewById(R.id.second);



        textii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rela1.setVisibility(View.GONE);
                textii.setVisibility(View.GONE);
                text02.setVisibility(View.VISIBLE);

            }
        });

        String matchid = getArguments().getString("mun");

        text02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rela1.setVisibility(View.VISIBLE);
                textii.setVisibility(View.VISIBLE);
                text02.setVisibility(View.GONE);

                rela1new1.setVisibility(View.GONE);
                textiinew1.setVisibility(View.GONE);
                text02new1.setVisibility(View.VISIBLE);

                rela1new2.setVisibility(View.GONE);
                textiinew2.setVisibility(View.GONE);
                text02new2.setVisibility(View.VISIBLE);

                rela1new3.setVisibility(View.GONE);
                textiinew3.setVisibility(View.GONE);
                text02new3.setVisibility(View.VISIBLE);

            }
        });

        text02new1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text02new1.setVisibility(View.GONE);
                textiinew1.setVisibility(View.VISIBLE);
                rela1new1.setVisibility(View.VISIBLE);
                rela1.setVisibility(View.GONE);
                textii.setVisibility(View.GONE);
                text02.setVisibility(View.VISIBLE);

                rela1new2.setVisibility(View.GONE);
                textiinew2.setVisibility(View.GONE);
                text02new2.setVisibility(View.VISIBLE);

                rela1new3.setVisibility(View.GONE);
                textiinew3.setVisibility(View.GONE);
                text02new3.setVisibility(View.VISIBLE);
            }
        });
        textiinew1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rela1new1.setVisibility(View.GONE);
                textiinew1.setVisibility(View.GONE);
                text02new1.setVisibility(View.VISIBLE);


            }
        });

        text02new2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text02new2.setVisibility(View.GONE);
                textiinew2.setVisibility(View.VISIBLE);
                rela1new2.setVisibility(View.VISIBLE);
                rela1.setVisibility(View.GONE);
                textii.setVisibility(View.GONE);
                text02.setVisibility(View.VISIBLE);
                rela1new1.setVisibility(View.GONE);
                textiinew1.setVisibility(View.GONE);
                text02new1.setVisibility(View.VISIBLE);

                rela1new3.setVisibility(View.GONE);
                textiinew3.setVisibility(View.GONE);
                text02new3.setVisibility(View.VISIBLE);
            }
        });
        textiinew2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rela1new2.setVisibility(View.GONE);
                textiinew2.setVisibility(View.GONE);
                text02new2.setVisibility(View.VISIBLE);

            }
        });
        textiinew3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rela1new3.setVisibility(View.GONE);
                textiinew3.setVisibility(View.GONE);
                text02new3.setVisibility(View.VISIBLE);

            }
        });
        text02new3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text02new3.setVisibility(View.GONE);
                textiinew3.setVisibility(View.VISIBLE);
                rela1new3.setVisibility(View.VISIBLE);
                rela1.setVisibility(View.GONE);
                textii.setVisibility(View.GONE);
                text02.setVisibility(View.VISIBLE);
                rela1new1.setVisibility(View.GONE);
                textiinew1.setVisibility(View.GONE);
                text02new1.setVisibility(View.VISIBLE);
                rela1new2.setVisibility(View.GONE);
                textiinew2.setVisibility(View.GONE);
                text02new2.setVisibility(View.VISIBLE);


            }
        });



        team11=rootView.findViewById(R.id.team11);
        run01=rootView.findViewById(R.id.run01);
        ref001=rootView.findViewById(R.id.ref001);
        ref11=rootView.findViewById(R.id.ref11);

        recview=rootView.findViewById(R.id.list_view);
        listView1=rootView.findViewById(R.id.list_view1);
        texti=rootView.findViewById(R.id.texti);
        team1=rootView.findViewById(R.id.team1);
        run=rootView.findViewById(R.id.run);
        run1=rootView.findViewById(R.id.run1);
        ref1=rootView.findViewById(R.id.ref1);
        ref12=rootView.findViewById(R.id.ref12);
        exd=rootView.findViewById(R.id.exd);
        exn=rootView.findViewById(R.id.exn);
        ref01=rootView.findViewById(R.id.ref01);
        tott1=rootView.findViewById(R.id.tott1);
        ref=rootView.findViewById(R.id.ref);


        team11new1=rootView.findViewById(R.id.team11new1);
        run01new1=rootView.findViewById(R.id.run01new1);
        ref001new1=rootView.findViewById(R.id.ref001new1);
        ref11new1=rootView.findViewById(R.id.ref11new1);

        listViewnew1=rootView.findViewById(R.id.list_viewnew1);
        listView1new1=rootView.findViewById(R.id.list_view1new1);
        listViewnew2=rootView.findViewById(R.id.list_viewnew2);
        listView1new2=rootView.findViewById(R.id.list_view1new2);
        listViewnew3=rootView.findViewById(R.id.list_viewnew3);
        listView1new3=rootView.findViewById(R.id.list_view1new3);

        team1new1=rootView.findViewById(R.id.team1new1);
        runnew1=rootView.findViewById(R.id.runnew1);
        run1new1=rootView.findViewById(R.id.run1new1);
        ref1new1=rootView.findViewById(R.id.ref1new1);
        ref12new1=rootView.findViewById(R.id.ref12new1);
        exdnew1=rootView.findViewById(R.id.exdnew1);
        exnnew1=rootView.findViewById(R.id.exnnew1);
        ref01new1=rootView.findViewById(R.id.ref01new1);
        tott1new1=rootView.findViewById(R.id.tott1new1);
        refnew1=rootView.findViewById(R.id.refnew1);

        arrayList1.clear();
        arrayList22.clear();

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
                if(response.isSuccessful()){
                    String asd=response.body().string();


                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String yt = asd;

                                progressBar2.setVisibility(View.GONE);
                                String students_array = yt;
                                try {
                                    JSONObject jsonObject = new JSONObject(students_array);
                                    JSONObject parent = jsonObject.getJSONObject("data");
                                    JSONArray jsonArray = parent.getJSONArray("batting");
                                    JSONArray jsonArraybowl = parent.getJSONArray("bowling");
                                    JSONArray jsonArray1 = parent.getJSONArray("runs");
                                    JSONObject firstteam = jsonArray1.getJSONObject(Integer.parseInt("0"));
                                    JSONObject team1obj = firstteam.getJSONObject("team");
                                    String teamname = team1obj.getString("name");
                                    String teamid = team1obj.getString("id");
                                    String sco = firstteam.getString("score");
                                    String wk = firstteam.getString("wickets");
                                    String over = firstteam.getString("overs");
                                    visit.setVisibility(View.VISIBLE);

                                    run.setText(sco);
                                    ref.setText("- " + wk);
                                    ref1.setText("(" + over + ")");
                                    team1.setText(teamname);

                                    run1.setText(sco);
                                    ref01.setText("- " + wk);
                                    ref12.setText("(" + over + ")");

                                    team11.setText(teamname);
                                    run01.setText(sco);
                                    ref11.setText("(" + over + ")");
                                    ref001.setText("- " + wk);

                                    JSONArray jsonArrayextra = parent.getJSONArray("scoreboards");
                                    JSONObject firstteamextra = jsonArrayextra.getJSONObject(Integer.parseInt("0"));

                                    String exwide = firstteamextra.getString("wide");
                                    String exno = firstteamextra.getString("noball_runs");
                                    String exbye = firstteamextra.getString("bye");
                                    String exlb = firstteamextra.getString("leg_bye");
                                    String exp = firstteamextra.getString("penalty");
                                    String score1 = firstteamextra.getString("team_id");


                                    if (score1.equals(teamid)) {
                                        int extrac = Integer.parseInt(exbye) + Integer.parseInt(exlb) + Integer.parseInt(exwide) + Integer.parseInt(exno) + Integer.parseInt(exp);
                                        exn.setText(String.valueOf(extrac));
                                        exd.setText("b " + exbye + ", lb " + exlb + ", w " + exwide + ", nb " + exno );

                                    } else {
                                        JSONObject firstteamextra1 = jsonArrayextra.getJSONObject(Integer.parseInt("2"));
                                        String exwide1 = firstteamextra1.getString("wide");
                                        String exno1 = firstteamextra1.getString("noball_runs");
                                        String exbye1 = firstteamextra1.getString("bye");
                                        String exlb1 = firstteamextra1.getString("leg_bye");
                                        String exp1 = firstteamextra1.getString("penalty");
                                        String score2 = firstteamextra1.getString("scoreboard");

                                        int extrac1 = Integer.parseInt(exbye1) + Integer.parseInt(exlb1) + Integer.parseInt(exwide1) + Integer.parseInt(exno1) + Integer.parseInt(exp1);
                                        exn.setText(String.valueOf(extrac1));
                                        exd.setText("b " + exbye1 + ", lb " + exlb1 + ", w " + exwide1 + ", nb " + exno1 );
                                    }


                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);

                                        JSONObject bn = object.getJSONObject("batsman");
                                        String did1 = bn.getString("fullname");
                                        String baid = bn.getString("id");
                                        String playim = bn.getString("image_path");

                                        String score4 = object.getString("score");
                                        int score = Integer.parseInt(score4);
                                        String six = object.getString("six_x");
                                        String four = object.getString("four_x");
                                        String team_id = object.getString("team_id");
                                        int temmi = Integer.valueOf(team_id);
                                        String ball = object.getString("ball");
                                        String sr = object.getString("rate");

                                        JSONObject result = object.getJSONObject("result");
                                        String resid = result.getString("id");
                                        int pres = Integer.valueOf(resid);
                                        String bid = object.getString("catch_stump_player_id");
                                        String cid = object.getString("bowling_player_id");

                                        if (team_id.equals(teamid)) {
                                            if (pres == 54) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                JSONObject rescatch = object.getJSONObject("catchstump");
                                                String bcatch = rescatch.getString("fullname");
                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout("c " + bcatch + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            } else if (pres == 55) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                JSONObject rescatch = object.getJSONObject("catchstump");
                                                String bcatch = rescatch.getString("fullname");
                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout("c (sub) " + bcatch + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            }

                                            else if (pres == 84) {
                                                String resname = result.getString("name");
                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(resname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            } else if (pres == 87) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                String resname = result.getString("name");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(resname + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            } else if (pres == 79) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            } else if (pres == 83) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" lbw " + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            } else if (pres == 86) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" Hit the ball twice ");
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            } else if (pres == 85) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" Retired Hurt ");
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            } else if (pres == 63) {
                                                JSONObject resbowl = object.getJSONObject("catchstump");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" run out (" + bname + ")");
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);
                                            } else if (pres == 56) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                JSONObject rescatch = object.getJSONObject("catchstump");
                                                String bcatch = rescatch.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout("st " + bcatch + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs.add(song);

                                            }


                                        }
                                    }

                                    for (int i = 0; i < jsonArraybowl.length(); i++) {

                                        JSONObject object1 = jsonArraybowl.getJSONObject(i);
                                        JSONObject bn1 = object1.getJSONObject("bowler");
                                        String did2 = bn1.getString("fullname");
                                        String boid = bn1.getString("id");
                                        String playimb = bn1.getString("image_path");
                                        String run1 = object1.getString("runs");
                                        String over1 = object1.getString("overs");
                                        String med1 = object1.getString("medians");
                                        String wik1 = object1.getString("wickets");
                                        String er1 = object1.getString("rate");
                                        String ttid = object1.getString("team_id");

                                        if (!ttid.equals(teamid)) {
                                            bowler bowler = new bowler();
                                            bowler.setPname(did2);
                                            bowler.setPimage(playimb);
                                            bowler.setPover(over1);
                                            bowler.setPmedi(med1);
                                            bowler.setPrrun(run1);
                                            bowler.setPwik(wik1);
                                            bowler.setPer(er1);
                                            bowler.setPid(boid);
                                            bowlers.add(bowler);

                                        }


                                    }
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        JSONObject fbn = object.getJSONObject("batsman");
                                        String fdid1 = fbn.getString("fullname");
                                        String baid1 = fbn.getString("id");
                                        String playimf = fbn.getString("image_path");
                                        String fover = object.getString("fow_balls");
                                        String fscore = object.getString("fow_score");

                                        JSONObject btnres1 = object.getJSONObject("result");
                                        String btnyt1 = btnres1.getString("name");

                                        int p1 = Integer.valueOf(fscore);
                                        String ghj1 = String.valueOf(p1);
                                        String team_id = object.getString("team_id");
                                        int extrac1 = Integer.parseInt(team_id) - Integer.parseInt(team_id);
                                        String ghj = String.valueOf(extrac1);


                                        if (team_id.equals(teamid) && !btnyt1.equals("Not Out")) {
                                            song song2 = new song();
                                            song2.setPname(fdid1);
                                            song2.setPimage(playimf);
                                            song2.setPrun(p1);
                                            song2.setPout(fover);
                                            song2.setPid(baid1);
                                            songs2.add(song2);


                                        }
                                        Collections.sort(songs2, new Comparator<song>() {
                                            @Override
                                            public int compare(song song, song t1) {
                                                return song.getPrun().compareTo(t1.getPrun());
                                            }
                                        });

                                    }
                                    JSONArray jsonArrayline = parent.getJSONArray("lineup");
                                    for (int i = 0; i < jsonArrayline.length(); i++) {

                                        JSONObject objectline = jsonArrayline.getJSONObject(i);
                                        JSONObject hj = objectline.getJSONObject("lineup");
                                        String tid = hj.getString("team_id");
                                        if (tid.equals(teamid)) {
                                            String did1 = objectline.getString("fullname");

                                            arrayList1.add(did1);
                                        }

                                    }
                                    for (int j = 0; j < jsonArray.length(); j++) {

                                        JSONObject object = jsonArray.getJSONObject(j);
                                        String team_id = object.getString("player_id");
                                        JSONObject bn = object.getJSONObject("batsman");
                                        String did = bn.getString("fullname");
                                        arrayList1.remove(did);

                                    }

                                    JSONObject secteam = jsonArray1.getJSONObject(Integer.parseInt("1"));
                                    JSONObject team2obj = secteam.getJSONObject("team");
                                    String teamname2 = team2obj.getString("name");
                                    String teamid2 = team2obj.getString("id");
                                    String sco2 = secteam.getString("score");
                                    String wk2 = secteam.getString("wickets");
                                    String over2 = secteam.getString("overs");

                                    second.setVisibility(View.VISIBLE);
                                    text02new1.setVisibility(View.GONE);
                                    textiinew1.setVisibility(View.VISIBLE);
                                    rela1new1.setVisibility(View.VISIBLE);
                                    rela1.setVisibility(View.GONE);
                                    textii.setVisibility(View.GONE);
                                    text02.setVisibility(View.VISIBLE);


                                    runnew1.setText(sco2);
                                    refnew1.setText("- " + wk2);
                                    ref1new1.setText("(" + over2 + ")");
                                    team1new1.setText(teamname2);

                                    run01new1.setText(sco2);
                                    ref001new1.setText("- " + wk2);
                                    ref11new1.setText("(" + over2 + ")");
                                    team11new1.setText(teamname2);

                                    run1new1.setText(sco2);
                                    ref01new1.setText("- " + wk2);
                                    ref12new1.setText("(" + over2 + ")");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);

                                        JSONObject bn = object.getJSONObject("batsman");
                                        String did1 = bn.getString("fullname");
                                        String baid = bn.getString("id");
                                        String playim = bn.getString("image_path");

                                        String score4 = object.getString("score");
                                        int score = Integer.parseInt(score4);
                                        String six = object.getString("six_x");
                                        String four = object.getString("four_x");
                                        String team_id = object.getString("team_id");
                                        int temmi = Integer.valueOf(team_id);
                                        String ball = object.getString("ball");
                                        String sr = object.getString("rate");

                                        JSONObject result = object.getJSONObject("result");
                                        String resid = result.getString("id");
                                        int pres = Integer.valueOf(resid);
                                        String bid = object.getString("catch_stump_player_id");
                                        String cid = object.getString("bowling_player_id");

                                        if (!team_id.equals(teamid)) {
                                            if (pres == 54) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                JSONObject rescatch = object.getJSONObject("catchstump");
                                                String bcatch = rescatch.getString("fullname");
                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout("c " + bcatch + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            }else if (pres == 55) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                JSONObject rescatch = object.getJSONObject("catchstump");
                                                String bcatch = rescatch.getString("fullname");
                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout("c (sub) " + bcatch + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            }

                                            else if (pres == 84) {
                                                String resname = result.getString("name");
                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(resname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            } else if (pres == 87) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                String resname = result.getString("name");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(resname + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            } else if (pres == 79) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            } else if (pres == 83) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" lbw " + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            } else if (pres == 86) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" Hit the ball twice ");
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            } else if (pres == 85) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" Retired Hurt ");
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            } else if (pres == 63) {
                                                JSONObject resbowl = object.getJSONObject("catchstump");
                                                String bname = resbowl.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout(" run out (" + bname + ")");
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);
                                            } else if (pres == 56) {
                                                JSONObject resbowl = object.getJSONObject("bowler");
                                                String bname = resbowl.getString("fullname");
                                                JSONObject rescatch = object.getJSONObject("catchstump");
                                                String bcatch = rescatch.getString("fullname");

                                                song song = new song();
                                                song.setPname(did1);
                                                song.setPout("st " + bcatch + " b " + bname);
                                                song.setPimage(playim);
                                                song.setPrun(score);
                                                song.setPsix(six);
                                                song.setPfour(four);
                                                song.setPball(ball);
                                                song.setPsr(sr);
                                                song.setPid(baid);
                                                songs3.add(song);

                                            }


                                        }

                                    }

                                    for (int i = 0; i < jsonArraybowl.length(); i++) {

                                        JSONObject object1 = jsonArraybowl.getJSONObject(i);
                                        JSONObject bn1 = object1.getJSONObject("bowler");
                                        String did2 = bn1.getString("fullname");
                                        String boid = bn1.getString("id");
                                        String playimb = bn1.getString("image_path");
                                        String run1 = object1.getString("runs");
                                        String over1 = object1.getString("overs");
                                        String med1 = object1.getString("medians");
                                        String wik1 = object1.getString("wickets");
                                        String er1 = object1.getString("rate");
                                        String ttid = object1.getString("team_id");

                                        if (ttid.equals(teamid)) {
                                            bowler bowler = new bowler();
                                            bowler.setPname(did2);
                                            bowler.setPimage(playimb);
                                            bowler.setPover(over1);
                                            bowler.setPmedi(med1);
                                            bowler.setPrrun(run1);
                                            bowler.setPwik(wik1);
                                            bowler.setPer(er1);
                                            bowler.setPid(boid);
                                            bowlers1.add(bowler);

                                        }
                                    }
                                    JSONArray jsonArrayextra2 = parent.getJSONArray("scoreboards");
                                    JSONObject firstteamextra2 = jsonArrayextra2.getJSONObject(Integer.parseInt("0"));

                                    String exwide2 = firstteamextra2.getString("wide");
                                    String exno2 = firstteamextra2.getString("noball_runs");
                                    String exbye2 = firstteamextra2.getString("bye");
                                    String exlb2 = firstteamextra2.getString("leg_bye");
                                    String exp2 = firstteamextra2.getString("penalty");
                                    String score12 = firstteamextra2.getString("team_id");


                                    if (!score1.equals(teamid)) {
                                        int extrac1 = Integer.parseInt(exbye2) + Integer.parseInt(exlb2) + Integer.parseInt(exwide2) + Integer.parseInt(exno2) + Integer.parseInt(exp2);
                                        exnnew1.setText(String.valueOf(extrac1));
                                        exdnew1.setText("b " + exbye2 + ", lb " + exlb2 + ", w " + exwide2 + ", nb " + exno2 );

                                    } else {
                                        JSONObject firstteamextra21 = jsonArrayextra2.getJSONObject(Integer.parseInt("2"));
                                        String exwide21 = firstteamextra21.getString("wide");
                                        String exno21 = firstteamextra21.getString("noball_runs");
                                        String exbye21 = firstteamextra21.getString("bye");
                                        String exlb21 = firstteamextra21.getString("leg_bye");
                                        String exp21 = firstteamextra21.getString("penalty");
                                        String score21 = firstteamextra21.getString("scoreboard");

                                        int extrac21 = Integer.parseInt(exbye21) + Integer.parseInt(exlb21) + Integer.parseInt(exwide21) + Integer.parseInt(exno21) + Integer.parseInt(exp21);
                                        exnnew1.setText(String.valueOf(extrac21));
                                        exdnew1.setText("b " + exbye21 + ", lb " + exlb21 + ", w " + exwide21 + ", nb " + exno21);
                                    }

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        JSONObject fbn = object.getJSONObject("batsman");
                                        String fdid1 = fbn.getString("fullname");
                                        String baid1 = fbn.getString("id");
                                        String playimf = fbn.getString("image_path");
                                        String fover = object.getString("fow_balls");
                                        String fscore = object.getString("fow_score");
                                        JSONObject btnres = object.getJSONObject("result");
                                        String btnyt = btnres.getString("name");

                                        int p1 = Integer.valueOf(fscore);
                                        String ghj1 = String.valueOf(p1);
                                        String team_id = object.getString("team_id");
                                        int extrac1 = Integer.parseInt(team_id) - Integer.parseInt(team_id);
                                        String ghj = String.valueOf(extrac1);


                                        if (!team_id.equals(teamid) && !btnyt.equals("Not Out")) {
                                            song song2 = new song();
                                            song2.setPname(fdid1);
                                            song2.setPimage(playimf);
                                            song2.setPrun(p1);
                                            song2.setPout(fover);
                                            song2.setPid(baid1);
                                            songs31.add(song2);


                                        }
                                        Collections.sort(songs31, new Comparator<song>() {
                                            @Override
                                            public int compare(song song, song t1) {
                                                return song.getPrun().compareTo(t1.getPrun());
                                            }
                                        });

                                    }
                                    for (int i = 0; i < jsonArrayline.length(); i++) {

                                        JSONObject objectline = jsonArrayline.getJSONObject(i);
                                        JSONObject hj = objectline.getJSONObject("lineup");
                                        String tid = hj.getString("team_id");
                                        if (!tid.equals(teamid)) {
                                            String did12 = objectline.getString("fullname");

                                            arrayList22.add(did12);
                                        }

                                    }
                                    for (int j = 0; j < jsonArray.length(); j++) {

                                        JSONObject object = jsonArray.getJSONObject(j);
                                        String team_id = object.getString("player_id");
                                        JSONObject bn = object.getJSONObject("batsman");
                                        String did2 = bn.getString("fullname");
                                        arrayList22.remove(did2);

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                recview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                adapter = new com.cricket.crickais.crickcode.Adapter(getContext(), songs);
                                recview.setAdapter(adapter);

                                listView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                adapterbowl = new Adapter1(getContext(), bowlers);
                                listView1.setAdapter(adapterbowl);

                                listView11.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                adapterfow = new Adapterfow(getContext(), songs2);
                                listView11.setAdapter(adapterfow);

                                String vb = arrayList1.toString();
                                tott1.setText(vb);
                                String vb1 = arrayList22.toString();
                                tott1new1.setText(vb1);

                                listViewnew1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                adapter1 = new com.cricket.crickais.crickcode.Adapter(getContext(), songs3);
                                listViewnew1.setAdapter(adapter1);

                                listView1new1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                adapterbowl1 = new Adapter1(getContext(), bowlers1);
                                listView1new1.setAdapter(adapterbowl1);

                                list_viewnew11.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                adapterfow1 = new Adapterfow(getContext(), songs31);
                                list_viewnew11.setAdapter(adapterfow1);

                            }

                        });
                    }

                }

            }
        });




        return rootView;
    }

}