package com.cricket.crickais.crickcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReferencemun;
    ImageView icon;
    ImageView kar,img98;
    TextView name,about,tt1;
    private RecyclerView recyclerView1,recyclerView2;
    private MyAdapter13 adapter11;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    RelativeLayout mot21;
    private ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        kar=(ImageView)findViewById(R.id.kar);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, starttingActivity.class);
                startActivity(intent);
            }
        });

        mot21=findViewById(R.id.mot21);

        name=findViewById(R.id.team1);
        about=findViewById(R.id.team2);
        tt1=findViewById(R.id.tt1);
        icon=findViewById(R.id.icon);
        list = new ArrayList<>();
        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReferencemun=firebaseDatabase.getReference("User").child(user.getUid());
        mot21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, user_details1.class);
                startActivity(intent);
            }
        });

        databaseReferencemun.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mod2 userInformation =snapshot.getValue(mod2.class);
                tt1.setText(userInformation.getName());
                name.setText(userInformation.getName());
                about.setText(userInformation.getAbout() +"\n" +userInformation.getWeb());
                if(!userInformation.getImage().equals(".")) {
                    Picasso.get().load(userInformation.getImage()).into(icon);

                }else{
                    icon.setImageResource(R.drawable.sq);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(profile.this));


        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerview2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(profile.this));

        adapter11 = new MyAdapter13(profile.this, list);

        recyclerView1.setAdapter(adapter11);
        DatabaseReference root = db.getReference("NewsPartE").child(user.getUid());
        root.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(snapshot!=null) {
                        Model model = dataSnapshot.getValue(Model.class);
                        list.add(model);
                    }

                }
                adapter11.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView2.setAdapter(adapter11);
        DatabaseReference root1 = db.getReference("NewsPartAs").child(user.getUid());
        root1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(snapshot!=null) {
                        Model model = dataSnapshot.getValue(Model.class);
                        list.add(model);
                    }

                }
                adapter11.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}