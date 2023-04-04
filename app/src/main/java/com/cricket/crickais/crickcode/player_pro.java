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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class player_pro extends Fragment {
ImageView icon;
TextView pn,pnc,born,gender,position,bats,balls;
ProgressBar progressBar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_player_pro, container, false);

        icon=rootView.findViewById(R.id.icon);
        pn=rootView.findViewById(R.id.pn);
        pnc=rootView.findViewById(R.id.pnc);
        born=rootView.findViewById(R.id.born);
        gender=rootView.findViewById(R.id.gender);
        position=rootView.findViewById(R.id.position);
        bats=rootView.findViewById(R.id.bats);
        balls=rootView.findViewById(R.id.balls);
        progressBar2=rootView.findViewById(R.id.progressBar2);

        String strtext1 = getArguments().getString("link");
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
                            progressBar2.setVisibility(View.GONE);


                            try {
                                JSONObject jsonObject=new JSONObject(students_array);
                                JSONObject parent =  jsonObject.getJSONObject("data");

                               String name=parent.getString("fullname");
                               String dob=parent.getString("dateofbirth");
                                String gender1=parent.getString("gender");
                                String bats1=parent.getString("battingstyle");
                                String bals1=parent.getString("bowlingstyle");
                                String image=parent.getString("image_path");
                                JSONObject ch = parent.getJSONObject("position");
                                String pos=ch.getString("name");
                                JSONObject coun = parent.getJSONObject("country");
                                String country=coun.getString("name");

                                Picasso.get().load(image).into(icon);
                                pn.setText(name);
                                pnc.setText(country);
                                born.setText(dob);
                                if(gender1.equals("m")){
                                    gender.setText("Male");
                                }else if(gender1.equals("f")){
                                    gender.setText("Female");
                                }

                                position.setText(pos);
                                bats.setText(bats1);
                                balls.setText(bals1);


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