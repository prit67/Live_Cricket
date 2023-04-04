package com.cricket.crickais.crickcode;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class seriwomen extends Fragment {

    private RecyclerView recyclerView3;
    private RecyclerView recyclerView1;
    private MyAdapterInter adapter2;

    private ArrayList<Model> list;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("Series").child("Women");
    ProgressBar progressBar;
    public seriwomen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.seriwomen, container, false);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        recyclerView3 = (RecyclerView) rootView.findViewById(R.id.recyclerview3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this.getContext()));
        list = new ArrayList<>();

        recyclerView3.setHasFixedSize(true);




        adapter2 = new MyAdapterInter(getContext(), list);

        recyclerView3.setAdapter(adapter2);

        root.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(snapshot!=null) {
                        Model model = dataSnapshot.getValue(Model.class);
                        list.add(model);
                    }

                }
                adapter2.notifyDataSetChanged();
                progressBar.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return rootView;
    }



}