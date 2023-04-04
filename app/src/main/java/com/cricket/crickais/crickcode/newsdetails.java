package com.cricket.crickais.crickcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class newsdetails extends AppCompatActivity {
    TextView asd,tt1,t2,abc1,abc12,abc13,point43,point431,point432;
    ImageView kar,img98,icon;
    FirebaseDatabase firebaseDatabase;
    private AdView mAdView,mAdView1;
    DatabaseReference databaseReferencemun;
    private FirebaseAuth firebaseAuth;
    RelativeLayout btn;
    Button dlt;
    LinearLayout vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetails);

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
        vm=findViewById(R.id.vm);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tt1.setText(getIntent().getStringExtra("newshead"));
        point43.setText(getIntent().getStringExtra("newshead"));



        String link = getIntent().getStringExtra("newsimg");
        String link1 = getIntent().getStringExtra("linki");
        String aid = getIntent().getStringExtra("author");
        String newsdate = getIntent().getStringExtra("newsdate");
        String desc = getIntent().getStringExtra("desc");
        point431.setText(aid);

        vm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(newsdetails.this, newsviewc.class);
                intent.putExtra("lkk",link1);
                intent.putExtra("head",getIntent().getStringExtra("newshead"));
                startActivity(intent);
            }
        });

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




        Picasso.get().load(link).into(img98);

        abc1.setText(getIntent().getStringExtra("news"));
      

    }
}