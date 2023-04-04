package com.cricket.crickais.crickcode;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView3;
    ProgressBar progressBar;
    ImageView kar,img98;
    private MyAdapter11 adapter11;
    private ArrayList<Model> list;
    FloatingActionButton assamese;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("News");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_news,container,false);
        recyclerView3 = (RecyclerView)rootView.findViewById(R.id.recyclerview3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        assamese=(FloatingActionButton)rootView.findViewById(R.id.assamese);
        assamese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), newsview1.class);
                startActivity(intent);
            }
        });
        adapter11 = new MyAdapter11(getContext(), list);

        recyclerView3.setAdapter(adapter11);

        root.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(snapshot!=null) {
                        Model model = dataSnapshot.getValue(Model.class);
                        list.add(model);
                    }
                    progressBar.setVisibility(ProgressBar.GONE);
                }
                adapter11.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return rootView;

    }



}

