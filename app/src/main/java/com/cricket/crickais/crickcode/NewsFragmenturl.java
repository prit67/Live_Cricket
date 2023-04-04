package com.cricket.crickais.crickcode;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class NewsFragmenturl extends Fragment {
    private RecyclerView recyclerView3,recyclerView4;
    ProgressBar progressBar;
    ImageView kar,img98;
    List<Model> songs11;
    private MyAdapter11 adapter11;
    private MyAdapter11new adapter11new;
    private ArrayList<Model> songs;
    private ArrayList<Model> songs1;
    FloatingActionButton assamese;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root1 = db.getReference().child("News");
    private DatabaseReference root = db.getReference("rapidlive");
    FirebaseDatabase firebaseDatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_newsurl,container,false);
        recyclerView3 = (RecyclerView)rootView.findViewById(R.id.recyclerview3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView4 = (RecyclerView)rootView.findViewById(R.id.recyclerview4);
        recyclerView4.setHasFixedSize(true);
        recyclerView4.setLayoutManager(new LinearLayoutManager(getContext()));

        songs = new ArrayList<>();
        songs1 = new ArrayList<>();
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        assamese=(FloatingActionButton)rootView.findViewById(R.id.assamese);
        assamese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), newsview1.class);
                startActivity(intent);
            }
        });

        adapter11new = new MyAdapter11new(getContext(), songs1);

        recyclerView4.setAdapter(adapter11new);
        root1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(snapshot!=null) {
                        Model model = dataSnapshot.getValue(Model.class);
                        songs1.add(model);
                    }
                    progressBar.setVisibility(ProgressBar.GONE);
                }
                adapter11new.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1=firebaseDatabase.getReference("rapidlive");
        databaseReference1.addValueEventListener(new ValueEventListener() {
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

                                        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                        adapter11 = new MyAdapter11(getContext(), songs);
                                        recyclerView3.setAdapter(adapter11);






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

