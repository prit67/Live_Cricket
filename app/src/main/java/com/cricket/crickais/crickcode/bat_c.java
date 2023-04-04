package com.cricket.crickais.crickcode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class bat_c extends Fragment {
   TextView tm,om,t2m,tin,oin,t2in,trun,orun,t2run,t2ball,t2high,t2avg,t2sr,t2not,t2four,t2six,t250,t2100,
          oball,ohigh,oavg,osr,onot,ofour,osix,o50,o100, tball,thigh,tavg,tsr,tnot,tfour,tsix,t50,t100;
    ArrayList<String> arrayList22 = new ArrayList<>();
    List<song> songs;
    ImageView icon;
    TextView name1, country,posit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bat_c, container, false);
        songs = new ArrayList<>();
        icon=rootView.findViewById(R.id.icon);
        name1=rootView.findViewById(R.id.name);
        country=rootView.findViewById(R.id.country);
        posit=rootView.findViewById(R.id.posit);
        tm=rootView.findViewById(R.id.tm);
        t2run=rootView.findViewById(R.id.t2run);
        om=rootView.findViewById(R.id.om);
        t2m=rootView.findViewById(R.id.t2m);
        tin=rootView.findViewById(R.id.tin);
        oin=rootView.findViewById(R.id.oin);
        t2in=rootView.findViewById(R.id.t2in);
        trun=rootView.findViewById(R.id.trun);
        orun=rootView.findViewById(R.id.orun);
        t2ball=rootView.findViewById(R.id.t2ball);
        t2high=rootView.findViewById(R.id.t2high);
        t2avg=rootView.findViewById(R.id.t2avg);
        t2sr=rootView.findViewById(R.id.t2sr);
        t2not=rootView.findViewById(R.id.t2not);
        t2four=rootView.findViewById(R.id.t2four);
        t2six=rootView.findViewById(R.id.t2six);
        t250=rootView.findViewById(R.id.t250);
        t2100=rootView.findViewById(R.id.t2100);

        oball=rootView.findViewById(R.id.oball);
        oavg=rootView.findViewById(R.id.oavg);
        osr=rootView.findViewById(R.id.osr);
        onot=rootView.findViewById(R.id.onot);
        ofour=rootView.findViewById(R.id.ofour);
        osix=rootView.findViewById(R.id.osix);
        o50=rootView.findViewById(R.id.o50);
        o100=rootView.findViewById(R.id.o100);

        tball=rootView.findViewById(R.id.tball);
        tavg=rootView.findViewById(R.id.tavg);
        tsr=rootView.findViewById(R.id.tsr);
        tnot=rootView.findViewById(R.id.tnot);
        tfour=rootView.findViewById(R.id.tfour);
        tsix=rootView.findViewById(R.id.tsix);
        t50=rootView.findViewById(R.id.t50);
        t100=rootView.findViewById(R.id.t100);

        String strtext1 = getArguments().getString("link1");
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



                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String students_array = asd;



                            try {
                                JSONObject jsonObject=new JSONObject(students_array);
                                JSONObject parent =  jsonObject.getJSONObject("data");

                                JSONArray jsonArray = parent.getJSONArray("career");
                                int total=0;
                                int innings=0;
                                int runs=0;
                                int balls=0;
                                int not=0;
                                int four=0;
                                int six=0;
                                int s50=0;
                                int s100=0;

                                int total1=0;
                                int innings1=0;
                                int runs1=0;
                                int balls1=0;
                                int not1=0;
                                int four1=0;
                                int six1=0;
                                int s501=0;
                                int s1001=0;

                                int total11=0;
                                int innings11=0;
                                int runs11=0;
                                int balls11=0;
                                int not11=0;
                                int four11=0;
                                int six11=0;
                                int s5011=0;
                                int s10011=0;

                                for (int j = 0; j < jsonArray.length(); j++) {

                                    JSONObject object = jsonArray.getJSONObject(j);
                                    String type = object.getString("type");
                                    String type1 = object.getString("batting");


                                    String name = parent.getString("fullname");
                                    String image = parent.getString("image_path");
                                    JSONObject ch = parent.getJSONObject("position");
                                    String pos = ch.getString("name");
                                    JSONObject coun = parent.getJSONObject("country");
                                    String country1 = coun.getString("name");

                                    Picasso.get().load(image).into(icon);
                                    name1.setText(name);
                                    country.setText(country1);
                                    posit.setText(pos);

                                    if (!type1.equals("null")) {
                                        JSONObject object1 = object.getJSONObject("batting");
                                        String match = object1.getString("matches");
                                        int mm = Integer.parseInt(match);
                                        String inning = object1.getString("innings");
                                        String run_s = object1.getString("runs_scored");
                                        String not_outs = object1.getString("not_outs");
                                        String highest = object1.getString("highest_inning_score");
                                        String sr = object1.getString("strike_rate");
                                        String balf = object1.getString("balls_faced");
                                        String four_x = object1.getString("four_x");
                                        String six_x = object1.getString("six_x");
                                        String fifties = object1.getString("fifties");
                                        String hundreds = object1.getString("hundreds");
                                        if (type.equals("T20I")) {

                                            total += Integer.parseInt(match);
                                            String kj = String.valueOf(total);
                                            innings += Integer.parseInt(inning);
                                            String inn = String.valueOf(innings);
                                            runs += Integer.parseInt(run_s);
                                            String runn = String.valueOf(runs);
                                            balls += Integer.parseInt(balf);
                                            String ballf = String.valueOf(balls);
                                            four += Integer.parseInt(four_x);
                                            String foures = String.valueOf(four);
                                            six += Integer.parseInt(six_x);
                                            String sixes = String.valueOf(six);
                                            s50 += Integer.parseInt(fifties);
                                            String fif = String.valueOf(s50);
                                            s100 += Integer.parseInt(hundreds);
                                            String hund = String.valueOf(s100);
                                            not += Integer.parseInt(not_outs);
                                            String nots = String.valueOf(not);
                                            t2m.setText(kj);
                                            t2in.setText(inn);
                                            t2run.setText(runn);
                                            t2ball.setText(ballf);
                                            t2four.setText(foures);
                                            t2six.setText(sixes);
                                            t250.setText(fif);
                                            t2100.setText(hund);
                                            t2not.setText(nots);
                                            t2high.setText(highest);
                                            Double a1 = new Double(runn);
                                            Double a2 = new Double(inn);
                                            Double a4 = new Double(nots);
                                            Double baf = new Double(ballf);
                                            Double out = a2 - a4;
                                            Double a3 = a1 / out;
                                            Double srr = a1 / baf * 100;
                                            t2avg.setText(String.format(Locale.US, "%.2f", a3));
                                            t2sr.setText(String.format(Locale.US, "%.2f", srr));
                                        } else if (type.equals("ODI")) {
                                            Picasso.get().load(image).into(icon);
                                            name1.setText(name);
                                            country.setText(country1);
                                            posit.setText(pos);
                                            total1 += Integer.parseInt(match);
                                            String kj = String.valueOf(total1);
                                            innings1 += Integer.parseInt(inning);
                                            String inn = String.valueOf(innings1);
                                            runs1 += Integer.parseInt(run_s);
                                            String runn = String.valueOf(runs1);
                                            balls1 += Integer.parseInt(balf);
                                            String ballf = String.valueOf(balls1);
                                            four1 += Integer.parseInt(four_x);
                                            String foures = String.valueOf(four1);
                                            six1 += Integer.parseInt(six_x);
                                            String sixes = String.valueOf(six1);
                                            s501 += Integer.parseInt(fifties);
                                            String fif = String.valueOf(s501);
                                            s1001 += Integer.parseInt(hundreds);
                                            String hund = String.valueOf(s1001);
                                            not1 += Integer.parseInt(not_outs);
                                            String nots = String.valueOf(not1);
                                            om.setText(kj);
                                            oin.setText(inn);
                                            orun.setText(runn);
                                            oball.setText(ballf);
                                            ofour.setText(foures);
                                            osix.setText(sixes);
                                            o50.setText(fif);
                                            o100.setText(hund);
                                            onot.setText(nots);
                                            Double a1 = new Double(runn);
                                            Double a2 = new Double(inn);
                                            Double a4 = new Double(nots);
                                            Double baf = new Double(ballf);
                                            Double out = a2 - a4;
                                            Double a3 = a1 / out;
                                            Double srr = a1 / baf * 100;
                                            oavg.setText(String.format(Locale.US, "%.2f", a3));
                                            osr.setText(String.format(Locale.US, "%.2f", srr));
                                        } else if (type.equals("Test/5day")) {
                                            Picasso.get().load(image).into(icon);
                                            name1.setText(name);
                                            country.setText(country1);
                                            posit.setText(pos);
                                            total11 += Integer.parseInt(match);
                                            String kj = String.valueOf(total11);
                                            innings11 += Integer.parseInt(inning);
                                            String inn = String.valueOf(innings11);
                                            runs11 += Integer.parseInt(run_s);
                                            String runn = String.valueOf(runs11);
                                            balls11 += Integer.parseInt(balf);
                                            String ballf = String.valueOf(balls11);
                                            four11 += Integer.parseInt(four_x);
                                            String foures = String.valueOf(four11);
                                            six11 += Integer.parseInt(six_x);
                                            String sixes = String.valueOf(six11);
                                            s5011 += Integer.parseInt(fifties);
                                            String fif = String.valueOf(s5011);
                                            s10011 += Integer.parseInt(hundreds);
                                            String hund = String.valueOf(s10011);
                                            not11 += Integer.parseInt(not_outs);
                                            String nots = String.valueOf(not11);
                                            tm.setText(kj);
                                            tin.setText(inn);
                                            trun.setText(runn);
                                            tball.setText(ballf);
                                            tfour.setText(foures);
                                            tsix.setText(sixes);
                                            t50.setText(fif);
                                            t100.setText(hund);
                                            tnot.setText(nots);
                                            Double a1 = new Double(runn);
                                            Double a2 = new Double(inn);
                                            Double a4 = new Double(nots);
                                            Double baf = new Double(ballf);
                                            Double out = a2 - a4;
                                            Double a3 = a1 / out;
                                            Double srr = a1 / baf * 100;
                                            tavg.setText(String.format(Locale.US, "%.2f", a3));
                                            tsr.setText(String.format(Locale.US, "%.2f", srr));
                                        }

                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }

            }
        });




        return rootView;
    }
}