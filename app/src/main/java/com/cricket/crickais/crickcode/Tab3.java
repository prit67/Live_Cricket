package com.cricket.crickais.crickcode;


import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import androidx.annotation.RequiresApi;
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
public class Tab3 extends Fragment {
    private RecyclerView recview;
    private RecyclerView recyclerView1;
    private MyAdapter2 adapter2;
    List<homing> homes;
    homageadapt1 homageadapt;
    private ArrayList<Model> list;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("completed");
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    public Tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab3, container, false);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        homes = new ArrayList<>();
        recview=rootView.findViewById(R.id.recyclerview3);
        Date d = new Date();
        CharSequence s  = DateFormat.format("yyyy-MM-dd ", d.getTime());
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
        String reg_date = df.format(c.getTime());
        c.add(Calendar.DATE, -30);  // number of days to add
        String start_date = df.format(c.getTime());

        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");

        c1.add(Calendar.DATE, 0);  // number of days to add
        String end_date = df1.format(c1.getTime());



        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1=firebaseDatabase.getReference("zandroid").child("ts2");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stage = snapshot.getValue(String.class);
                String token=stage;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://cricket.sportmonks.com/api/v2.0/fixtures?api_token=lhkyue6be0nJnQUrDcqEEVWVRYshXvjLJyNcLNrat9Di7C5qFRLquPXjieDt"+token+"&include=runs,tosswon,localteam,league,visitorteam,stage&filter[starts_between]="+df.format(c.getTime())+","+df1.format(c1.getTime()))
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
                                        String yt = asd;
                                        String students_array = yt;
                                        try {
                                            JSONObject jsonObject = new JSONObject(students_array);
                                            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                                            progressBar.setVisibility(View.GONE);


                                            for (int i=0; i<jsonArray1.length(); i++) {
                                                JSONObject object1 = jsonArray1.getJSONObject(i);
                                                String newest = object1.getString("id");
                                                String note = object1.getString("note");

                                                String stage1 = object1.getString("stage_id");

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
                                                    song1.setTeam1(locname);
                                                    song1.setSta(locimg);
                                                    song1.setTeam2(visitname);
                                                    song1.setTeam(visitimg);
                                                    song1.setDate(newDateStr);
                                                    song1.setBar(newest);
                                                    song1.setTs1(non);
                                                    song1.setTs2("");
                                                    song1.setNotification("Upcoming");
                                                    song1.setVenue(stat);
                                                    song1.setTeam3(newDateStr);
                                                    song1.setNext1(stage1);
                                                    homes.add(song1);
                                                }
                                                else if (status.equals("Delayed")) {
                                                    homing song1 = new homing();
                                                    if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                        song1.setMatchno(lgname + " - " + round);
                                                    } else {
                                                        song1.setMatchno(stname + " - " + round);
                                                    }
                                                    song1.setTeam1(locname);
                                                    song1.setSta(locimg);
                                                    song1.setTeam2(visitname);
                                                    song1.setTeam(visitimg);
                                                    song1.setNotification("Delayed");
                                                    song1.setDate(note);
                                                    song1.setBar(newest);
                                                    song1.setTs1("");
                                                    song1.setTs2("");
                                                    song1.setVenue(stat);
                                                    song1.setNext1(stage1);
                                                    homes.add(song1);
                                                }
                                                else if (status.equals("Cancl.")) {
                                                    homing song1 = new homing();
                                                    if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                        song1.setMatchno(lgname + " - " + round);
                                                    } else {
                                                        song1.setMatchno(stname + " - " + round);
                                                    }
                                                    song1.setTeam1(locname);
                                                    song1.setSta(locimg);
                                                    song1.setTeam2(visitname);
                                                    song1.setTeam(visitimg);
                                                    song1.setNotification("Cancl.");
                                                    song1.setDate(note);
                                                    song1.setBar(newest);
                                                    song1.setTs1("");
                                                    song1.setTs2("");
                                                    song1.setVenue(stat);
                                                    song1.setNext1(stage1);
                                                    homes.add(song1);
                                                }



                                                else if (status.equals("Aban.")) {
                                                    homing song1 = new homing();
                                                    if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                        song1.setMatchno(lgname + " - " + round);
                                                    } else {
                                                        song1.setMatchno(stname + " - " + round);
                                                    }
                                                    song1.setTeam1(locname);
                                                    song1.setSta(locimg);
                                                    song1.setTeam2(visitname);
                                                    song1.setTeam(visitimg);
                                                    song1.setDate(non);
                                                    song1.setBar(newest);
                                                    song1.setTs1("");
                                                    song1.setTs2("");
                                                    song1.setNotification("Aban");
                                                    song1.setVenue(stat);
                                                    song1.setTeam3(newDateStr);
                                                    song1.setNext1(stage1);
                                                    homes.add(song1);
                                                }
                                                else if (status.equals("Int.")) {
                                                    homing song1 = new homing();
                                                    if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                        song1.setMatchno(lgname + " - " + round);
                                                    } else {
                                                        song1.setMatchno(stname + " - " + round);
                                                    }
                                                    song1.setTeam1(locname);
                                                    song1.setSta(locimg);
                                                    song1.setTeam2(visitname);
                                                    song1.setTeam(visitimg);
                                                    song1.setTeam3(stage);
                                                    song1.setDate(note);
                                                    song1.setNotification("Int.");
                                                    song1.setBar(newest);
                                                    song1.setTs1("");
                                                    song1.setTs2("");
                                                    song1.setVenue(stat);
                                                    song1.setNext1(stage1);
                                                    homes.add(song1);
                                                }
                                                else if (status.equals("Postp.")) {
                                                    homing song1 = new homing();
                                                    if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                        song1.setMatchno(lgname + " - " + round);
                                                    } else {
                                                        song1.setMatchno(stname + " - " + round);
                                                    }
                                                    song1.setTeam1(locname);
                                                    song1.setSta(locimg);
                                                    song1.setTeam2(visitname);
                                                    song1.setTeam(visitimg);
                                                    song1.setDate(non);
                                                    song1.setBar(newest);
                                                    song1.setTs1("");
                                                    song1.setTs2("");
                                                    song1.setNotification("Postp");
                                                    song1.setVenue(stat);
                                                    song1.setTeam3(newDateStr);
                                                    song1.setNext1(stage1);
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
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);
                                                    } else {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
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
                                                        song1.setNotification("Live");
                                                        song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
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
                                                        song1.setNotification("Live");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);
                                                    } else {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
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
                                                        song1.setNotification("Live");
                                                        song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
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
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);
                                                    } else {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
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
                                                        song1.setNotification("Live");
                                                        song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
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
                                                        song1.setNotification("Live");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);
                                                    } else {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
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
                                                        song1.setNotification("Live");
                                                        song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
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
                                                        if(visitcode.length()>2){
                                                            song1.setTeam1(loccode+"  ");
                                                        }else{
                                                            song1.setTeam1(loccode);
                                                        }
                                                        song1.setSta(locimg);
                                                        song1.setNotification("Live");
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
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);
                                                    } else {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
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
                                                        song1.setNotification("Live");
                                                        song1.setDate(winteam + " elected to bat first");
                                                        song1.setBar(newest);
                                                        song1.setTs1("");
                                                        song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
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
                                                        if(visitcode.length()>2){
                                                            song1.setTeam1(loccode+"  ");
                                                        }else{
                                                            song1.setTeam1(loccode);
                                                        }
                                                        song1.setSta(locimg);
                                                        song1.setNotification("Live");
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
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);
                                                    } else {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if(visitcode.length()>2){
                                                            song1.setTeam1(loccode+"  ");
                                                        }else{
                                                            song1.setTeam1(loccode);
                                                        }
                                                        song1.setSta(locimg);
                                                        song1.setNotification("Live");
                                                        if(loccode.length()>2){
                                                            song1.setTeam2(visitcode+"  ");
                                                        }else{
                                                            song1.setTeam2(visitcode);
                                                        }
                                                        song1.setTeam(visitimg);
                                                        song1.setDate(winteam + " elected to bowl first");
                                                        song1.setBar(newest);
                                                        song1.setTs1("");
                                                        song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
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
                                                        song1.setTs1(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setTs2(score2 + "-" + wkts2 + " (" + over2 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);
                                                    } else {
                                                        homing song1 = new homing();
                                                        if (stname.equals("Regular Season") || stname.equals("Group Stage")) {
                                                            song1.setMatchno(lgname + " - " + round);
                                                        } else {
                                                            song1.setMatchno(stname + " - " + round);
                                                        }
                                                        if(visitcode.length()>2){
                                                            song1.setTeam1(loccode+"  ");
                                                        }else{
                                                            song1.setTeam1(loccode);
                                                        }
                                                        song1.setSta(locimg);
                                                        song1.setNotification(status);
                                                        if(loccode.length()>2){
                                                            song1.setTeam2(visitcode+"  ");
                                                        }else{
                                                            song1.setTeam2(visitcode);
                                                        }
                                                        song1.setTeam(visitimg);
                                                        song1.setDate(note);
                                                        song1.setBar(newest);
                                                        song1.setTs1(score2 + "-" + wkts2 + " (" + over2 + ")");
                                                        song1.setTs2(score1 + "-" + wkts1 + " (" + over1 + ")");
                                                        song1.setVenue(stat);
                                                        song1.setTeam3(newDateStr);
                                                        song1.setNext1(stage1);
                                                        homes.add(song1);


                                                    }


                                                }
                                                Collections.sort(homes, new Comparator<homing>() {
                                                    @Override
                                                    public int compare(homing homing, homing t1) {
                                                        return t1.getVenue().compareTo(homing.getVenue());
                                                    }
                                                });

                                            }


                                        } catch (JSONException | ParseException e) {
                                            e.printStackTrace();
                                        }
                                        recview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                        homageadapt = new homageadapt1(getContext(), homes);
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





        return rootView;
    }



}