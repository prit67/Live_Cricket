package com.cricket.crickais.crickcode;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class newsview1 extends AppCompatActivity {
    private RecyclerView recyclerView3;
    ProgressBar progressBar;
    ImageView kar,img98;
    private MyAdapter11 adapter11;
    private ArrayList<Model> list;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("NewsAssamses");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsview1);
        getSupportActionBar().hide();
        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerview3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(newsview1.this));
        list = new ArrayList<>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        kar=(ImageView)findViewById(R.id.kar);

        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        adapter11 = new MyAdapter11(newsview1.this, list);

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

    }
}