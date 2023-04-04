package com.cricket.crickais.crickcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class newsdetails2 extends AppCompatActivity {
    TextView asd,tt1,t2,abc1,abc12,abc13,point43,point431,point432;
    ImageView kar,img98,icon;
    FirebaseDatabase firebaseDatabase;
    private AdView mAdView,mAdView1;
    DatabaseReference databaseReferencemun;
    private FirebaseAuth firebaseAuth;
    RelativeLayout btn;
    Button dlt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetails2);

        getSupportActionBar().hide();
        mAdView=(com.google.android.gms.ads.AdView)findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        dlt=findViewById(R.id.dlt);
        tt1=(TextView) findViewById(R.id.tt1);
        icon=findViewById(R.id.icon);
        point43=(TextView) findViewById(R.id.point43);
        point431=(TextView) findViewById(R.id.point431);
        point432=(TextView) findViewById(R.id.point432);
        abc1=(TextView) findViewById(R.id.abc1);
        abc12=(TextView) findViewById(R.id.abc12);
        abc13=(TextView) findViewById(R.id.abc13);
        kar=(ImageView)findViewById(R.id.kar);
        img98=(ImageView)findViewById(R.id.img98);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tt1.setText(getIntent().getStringExtra("newshead"));
        point43.setText(getIntent().getStringExtra("newshead"));



        String link = getIntent().getStringExtra("newsimg");
        String aid = getIntent().getStringExtra("aid");
        String newsdate = getIntent().getStringExtra("newsdate");
        String desc = getIntent().getStringExtra("desc");

        try {
            String dateStr = newsdate;

            SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date dateObj = curFormater.parse(dateStr);
            SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");

            String newDateStr = postFormater.format(dateObj);
            point432.setText(newDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btn=findViewById(R.id.btn);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReferencemun=firebaseDatabase.getReference("User").child(aid);
        databaseReferencemun.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mod2 userInformation =snapshot.getValue(mod2.class);
                point431.setText(userInformation.getName());
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
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(firebaseAuth.getCurrentUser()!=null) {
            if (aid.equals(user.getUid())) {
                btn.setVisibility(View.VISIBLE);
                dlt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(newsdetails2.this, R.style.MyDialogTheme)
                                .setTitle("Deleting Article")
                                .setMessage("Are you sure you want to delete ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatabaseReference hati = firebaseDatabase.getReference("News").child(desc);
                                        DatabaseReference hati1 = firebaseDatabase.getReference("NewsPartE").child(user.getUid()).child(desc);
                                        DatabaseReference ass = firebaseDatabase.getReference("NewsAssamses").child(desc);
                                        DatabaseReference ass1 = firebaseDatabase.getReference("NewsPartAs").child(user.getUid()).child(desc);

                                        hati.removeValue();
                                        hati1.removeValue();
                                        ass.removeValue();
                                        ass1.removeValue();
                                        Intent intent = new Intent(newsdetails2.this, starttingActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No", null);

                        builder.show();
                    }
                });
            }
        }

        Picasso.get().load(link).into(img98);

        abc1.setText(getIntent().getStringExtra("news"));


    }
}