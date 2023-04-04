package com.cricket.crickais.crickcode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class fsquad extends Fragment {

    RelativeLayout log;
    private ArrayList<Model> list;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("MatchSh");
    ProgressBar progressBar;
    private RecyclerView recview;
    private RecyclerView recyclerView1;
    private MyAdapter2 adapter2;
    List<homing> homes;

    WebView webView;
    TextView txt;
    FirebaseDatabase firebaseDatabase;
    FirebaseDatabase firebaseDatabase1;
    RecyclerView t1list;
    List<homing> songs;
    private fsqq adapter;
    public fsquad() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fsquad, container, false);

        progressBar=rootView.findViewById(R.id.progressBar);
        songs = new ArrayList<>();
        t1list=rootView.findViewById(R.id.recyclerview1);

        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference12=firebaseDatabase.getReference("Featuresmain").child("season");

        databaseReference12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String season = snapshot.getValue(String.class);
        DatabaseReference databaseReference1=firebaseDatabase.getReference("zandroid").child("ts2");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stage = snapshot.getValue(String.class);
                String token=stage;

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://cricket.sportmonks.com/api/v2.0/seasons/"+season+"?api_token=lhkyue6be0nJnQUrDcqEEVWVRYshXvjLJyNcLNrat9Di7C5qFRLquPXjieDt"+token+"&include=teams")
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
                                        String yt = asd;
                                        progressBar.setVisibility(View.GONE);
                                        String students_array = yt;
                                        try {
                                            JSONObject jsonObject = new JSONObject(students_array);
                                            JSONObject parent = jsonObject.getJSONObject("data");
                                            String sid = parent.getString("id");
                                            JSONArray jsonArray = parent.getJSONArray("teams");
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject object = jsonArray.getJSONObject(i);

                                                String did1 = object.getString("name");
                                                String tid = object.getString("id");


                                                homing song = new homing();
                                                song.setSta(did1);
                                                song.setTeam(tid);
                                                song.setTeam1(sid);

                                                songs.add(song);


                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        t1list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                        adapter = new fsqq(getContext(), songs);
                                        t1list.setAdapter(adapter);


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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootView;
    }

}