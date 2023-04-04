package com.cricket.crickais.crickcode;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab111 extends Fragment {
TextView team1,team2,match,series,date1,time,toss,venue,ump,ump1,ump1r,info,capname,stcname,stname;
RelativeLayout t1,t2;
ImageView img98;
    FirebaseDatabase firebaseDatabase;
    RelativeLayout img34;
    ProgressBar progressBar2;
    private AdView mAdView,mAdView1;
    public Tab111() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab111, container, false);

        String strtext1 = getArguments().getString("link");

        mAdView=(com.google.android.gms.ads.AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        team1=rootView.findViewById(R.id.team1);

        capname=rootView.findViewById(R.id.capname);
        stcname=rootView.findViewById(R.id.stcname);
        stname=rootView.findViewById(R.id.stname);
        img98=rootView.findViewById(R.id.img98);
img34=rootView.findViewById(R.id.img34);
        team2=rootView.findViewById(R.id.team2);
        match=rootView.findViewById(R.id.match);
        series=rootView.findViewById(R.id.series);
        date1=rootView.findViewById(R.id.date1);
        time=rootView.findViewById(R.id.time);
info=rootView.findViewById(R.id.info1);
        toss=rootView.findViewById(R.id.toss);
        venue=rootView.findViewById(R.id.venue);
        ump=rootView.findViewById(R.id.ump);
        ump1=rootView.findViewById(R.id.ump1);
        ump1r=rootView.findViewById(R.id.ump1r);
        progressBar2 = rootView.findViewById(R.id.progressBar2);
        t1=rootView.findViewById(R.id.t1);
        t2=rootView.findViewById(R.id.t2);



                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(strtext1)
                        .get()
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


                            if (getActivity() != null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    public void run() {
                                        String students_array = asd;
                                        progressBar2.setVisibility(View.GONE);


                                        try {
                                            JSONObject jsonObject = new JSONObject(students_array);
                                            JSONObject parent = jsonObject.getJSONObject("data");
                                            String match1 = parent.getString("round");
                                            String stat = parent.getString("starting_at");
                                            String elect = parent.getString("elected");

                                            String dateStr = stat;

                                            SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
                                            Date dateObj = curFormater.parse(dateStr);
                                            SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");

                                            String newDateStr = postFormater.format(dateObj);

                                            Clock clock = Clock.systemDefaultZone();
                                            String abcd = String.valueOf(clock.getZone());
                                            String date = dateStr;
                                            ZonedDateTime dateTime = ZonedDateTime.parse(date);
                                            String newDateStr1 = dateTime.withZoneSameInstant(ZoneId.of(abcd)).format(DateTimeFormatter.ofPattern("hh:mm a"));
                                            String newDateStr2 = dateTime.withZoneSameInstant(ZoneId.of(abcd)).format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));


                                            JSONObject parent1 = parent.getJSONObject("stage");
                                            String sername = parent1.getString("name");


                                            JSONObject parent11 = parent.getJSONObject("localteam");
                                            String llname = parent11.getString("name");
                                            String llid = parent11.getString("id");


                                            JSONObject parent12 = parent.getJSONObject("visitorteam");
                                            String vvname = parent12.getString("name");
                                            String vvid = parent12.getString("id");

                                            t1.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(getContext(),t1.class);
                                                    intent.putExtra("mun", llid);
                                                    intent.putExtra("tname", llname);
                                                    intent.putExtra("link", strtext1);
                                                    startActivity(intent);
                                                }
                                            });

                                            t2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(getContext(),t2.class);
                                                    intent.putExtra("mun1", vvid);
                                                    intent.putExtra("tname1", vvname);
                                                    intent.putExtra("link", strtext1);
                                                    startActivity(intent);
                                                }
                                            });

                                            match.setText(match1);
                                            series.setText(sername);
                                            date1.setText(newDateStr2);
                                            time.setText(newDateStr1);

                                            team1.setText(llname);
                                            team2.setText(vvname);

                                            JSONObject parent3 = parent.getJSONObject("venue");
                                            String vname = parent3.getString("name");
                                            String vcity = parent3.getString("city");
                                            String vcap = parent3.getString("capacity");
                                            String vi = parent3.getString("image_path");

                                            venue.setText(vname + ", " + vcity);

                                            stname.setText(vname);
                                            stcname.setText(vcity);
                                            capname.setText(vcap);
                                            img34.setVisibility(View.VISIBLE);
                                            Picasso.get().load(vi).into(img98);

                                            JSONObject parent2 = parent.getJSONObject("tosswon");
                                            String winteam = parent2.getString("name");

                                            if (elect.equals("batting")) {
                                                toss.setText(winteam + ", elected to bat first");
                                            } else {
                                                toss.setText(winteam + ", elected to bowl first");
                                            }


                                            JSONObject parent4 = parent.getJSONObject("firstumpire");
                                            String ffull = parent4.getString("fullname");
                                            JSONObject parent5 = parent.getJSONObject("secondumpire");
                                            String sfull = parent5.getString("fullname");
                                            JSONObject parent6 = parent.getJSONObject("tvumpire");
                                            String tfull = parent6.getString("fullname");
                                            JSONObject parent7 = parent.getJSONObject("referee");
                                            String rfull = parent7.getString("fullname");


                                            ump.setText(ffull + "," + sfull);
                                            ump1.setText(tfull);
                                            ump1r.setText(rfull);

                                            stname.setText(vname);
                                            stcname.setText(vcity);
                                            capname.setText(vcap);
                                            Picasso.get().load(vi).into(img98);


                                        } catch (JSONException | ParseException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });
                            }
                        }

                    }
                });







        return rootView;
    }


}