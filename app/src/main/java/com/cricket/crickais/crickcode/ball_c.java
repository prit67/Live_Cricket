package com.cricket.crickais.crickcode;

import android.os.Bundle;
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
import java.util.Locale;

import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ball_c extends Fragment {
TextView t2m,t2in,t2ball,t2run,t2med,t2w,t2avg,t2eco,t2sr,t24w,t25w,t210w,
        om,oin,oball,orun,omed,ow,oavg,oeco,osr,o4w,o5w,o10w,
        tm,tin,tball,trun,tmed,tw,tavg,teco,tsr,t4w,t5w,t10w;

    ImageView icon;
    TextView name1, country,posit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ball_c, container, false);
        icon=rootView.findViewById(R.id.icon);
        name1=rootView.findViewById(R.id.name);
        country=rootView.findViewById(R.id.country);
        posit=rootView.findViewById(R.id.posit);
        t2m=rootView.findViewById(R.id.t2m);
        t210w=rootView.findViewById(R.id.t210w);
        t2in=rootView.findViewById(R.id.t2in);
        t2ball=rootView.findViewById(R.id.t2ball);
        t2run=rootView.findViewById(R.id.t2run);
        t2med=rootView.findViewById(R.id.t2med);
        t2avg=rootView.findViewById(R.id.t2avg);
        t2w=rootView.findViewById(R.id.t2w);
        t2eco=rootView.findViewById(R.id.t2eco);
        t2sr=rootView.findViewById(R.id.t2sr);
        t24w=rootView.findViewById(R.id.t24w);
        t25w=rootView.findViewById(R.id.t25w);

        om=rootView.findViewById(R.id.om);
        o10w=rootView.findViewById(R.id.o10w);
        oin=rootView.findViewById(R.id.oin);
        oball=rootView.findViewById(R.id.oball);
        orun=rootView.findViewById(R.id.orun);
        omed=rootView.findViewById(R.id.omed);
        oavg=rootView.findViewById(R.id.oavg);
        ow=rootView.findViewById(R.id.ow);
        oeco=rootView.findViewById(R.id.oeco);
        osr=rootView.findViewById(R.id.osr);
        o4w=rootView.findViewById(R.id.o4w);
        o5w=rootView.findViewById(R.id.o5w);

        tm=rootView.findViewById(R.id.tm);
        t10w=rootView.findViewById(R.id.t10w);
        tin=rootView.findViewById(R.id.tin);
        tball=rootView.findViewById(R.id.tball);
        trun=rootView.findViewById(R.id.trun);
        tmed=rootView.findViewById(R.id.tmed);
        tavg=rootView.findViewById(R.id.tavg);
        tw=rootView.findViewById(R.id.tw);
        teco=rootView.findViewById(R.id.teco);
        tsr=rootView.findViewById(R.id.tsr);
        t4w=rootView.findViewById(R.id.t4w);
        t5w=rootView.findViewById(R.id.t5w);
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
                                                        String name=parent.getString("fullname");
                                                        String image=parent.getString("image_path");
                                                        JSONObject ch = parent.getJSONObject("position");
                                                        String pos=ch.getString("name");
                                                        JSONObject coun = parent.getJSONObject("country");
                                                        String country1=coun.getString("name");

                                Picasso.get().load(image).into(icon);
                                name1.setText(name);
                                country.setText(country1);
                                posit.setText(pos);
                                int total=0;
                                int innings=0;
                                int runs=0;
                                int medi=0;
                                int wkt=0;
                                int fourw=0;
                                int s5w=0;
                                int s10w=0;

                                int total1=0;
                                int innings1=0;
                                int runs1=0;
                                int medi1=0;
                                int wkt1=0;
                                int fourw1=0;
                                int s5w1=0;
                                int s10w1=0;

                                int total11=0;
                                int innings11=0;
                                int runs11=0;
                                int medi11=0;
                                int wkt11=0;
                                int fourw11=0;
                                int s5w11=0;
                                int s10w11=0;



                                for (int j = 0; j < jsonArray.length(); j++) {

                                    JSONObject object = jsonArray.getJSONObject(j);
                                    String type = object.getString("type");
                                    String type1 = object.getString("bowling");


                                    if (!type1.equals("null")) {
                                        JSONObject object1 = object.getJSONObject("bowling");
                                        String match = object1.getString("matches");
                                        String inning = object1.getString("innings");
                                        String over = object1.getString("overs");
                                        String average = object1.getString("average");
                                        String econ_rate = object1.getString("econ_rate");
                                        String sr = object1.getString("strike_rate");
                                        String medians = object1.getString("medians");
                                        String runb = object1.getString("runs");
                                        String wickets = object1.getString("wickets");
                                        String wide = object1.getString("wide");
                                        String noball = object1.getString("noball");
                                        String four_wickets = object1.getString("four_wickets");
                                        String five_wickets = object1.getString("five_wickets");
                                        String ten_wickets = object1.getString("ten_wickets");
                                        String rate = object1.getString("rate");
                                        if (type.equals("T20I")) {

                                            total += Integer.parseInt(match);
                                            String kj = String.valueOf(total);
                                            innings += Integer.parseInt(inning);
                                            String inn = String.valueOf(innings);
                                            runs += Integer.parseInt(runb);
                                            String runn = String.valueOf(runs);
                                            medi += Integer.parseInt(medians);
                                            String medd = String.valueOf(medi);
                                            wkt += Integer.parseInt(wickets);
                                            String wk = String.valueOf(wkt);
                                            fourw += Integer.parseInt(four_wickets);
                                            String fw = String.valueOf(fourw);
                                            s5w += Integer.parseInt(five_wickets);
                                            String f5w = String.valueOf(s5w);
                                            s10w += Integer.parseInt(ten_wickets);
                                            String f10w = String.valueOf(s10w);

                                            t2m.setText(kj);
                                            t2in.setText(inn);
                                            t2run.setText(runn);
                                            t2med.setText(medd);
                                            t2w.setText(wk);
                                            t24w.setText(fw);
                                            t25w.setText(f5w);
                                            t210w.setText(f10w);

                                            Double a1 = new Double(runn);
                                            Double a2 = new Double(wk);
                                            Double a3 = new Double(over);
                                            Double avg = a1 / a2;

                                            t2avg.setText(String.format(Locale.US, "%.2f", avg));

                                        } else if (type.equals("ODI")) {
                                            total1 += Integer.parseInt(match);
                                            String kj = String.valueOf(total1);
                                            innings1 += Integer.parseInt(inning);
                                            String inn = String.valueOf(innings1);
                                            runs1 += Integer.parseInt(runb);
                                            String runn = String.valueOf(runs1);
                                            medi1 += Integer.parseInt(medians);
                                            String medd = String.valueOf(medi1);
                                            wkt1 += Integer.parseInt(wickets);
                                            String wk = String.valueOf(wkt1);
                                            fourw1 += Integer.parseInt(four_wickets);
                                            String fw = String.valueOf(fourw1);
                                            s5w1 += Integer.parseInt(five_wickets);
                                            String f5w = String.valueOf(s5w1);
                                            s10w1 += Integer.parseInt(ten_wickets);
                                            String f10w = String.valueOf(s10w1);

                                            om.setText(kj);
                                            oin.setText(inn);
                                            orun.setText(runn);
                                            omed.setText(medd);
                                            ow.setText(wk);
                                            o4w.setText(fw);
                                            o5w.setText(f5w);
                                            o10w.setText(f10w);
                                            Double a1 = new Double(runn);
                                            Double a2 = new Double(wk);
                                            Double avg = a1 / a2;

                                            oavg.setText(String.format(Locale.US, "%.2f", avg));
                                        } else if (type.equals("Test/5day")) {
                                            total11 += Integer.parseInt(match);
                                            String kj = String.valueOf(total11);
                                            innings11 += Integer.parseInt(inning);
                                            String inn = String.valueOf(innings11);
                                            runs11 += Integer.parseInt(runb);
                                            String runn = String.valueOf(runs11);
                                            medi11 += Integer.parseInt(medians);
                                            String medd = String.valueOf(medi11);
                                            wkt11 += Integer.parseInt(wickets);
                                            String wk = String.valueOf(wkt11);
                                            fourw11 += Integer.parseInt(four_wickets);
                                            String fw = String.valueOf(fourw11);
                                            s5w11 += Integer.parseInt(five_wickets);
                                            String f5w = String.valueOf(s5w11);
                                            s10w11 += Integer.parseInt(ten_wickets);
                                            String f10w = String.valueOf(s10w11);
                                            tm.setText(kj);
                                            tin.setText(inn);
                                            trun.setText(runn);
                                            tmed.setText(medd);
                                            tw.setText(wk);
                                            t4w.setText(fw);
                                            t5w.setText(f5w);
                                            t10w.setText(f10w);
                                            Double a1 = new Double(runn);
                                            Double a2 = new Double(wk);
                                            Double avg = a1 / a2;

                                            tavg.setText(String.format(Locale.US, "%.2f", avg));
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