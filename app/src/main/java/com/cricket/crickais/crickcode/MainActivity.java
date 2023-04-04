package com.cricket.crickais.crickcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2000;
    TextView nb1;
    FirebaseDatabase firebaseDatabase1;
    private int REQUEST_CODE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();


        handlerSplash();


    }


    @Override
    protected void onResume() {
        super.onResume();
    }
    public void handlerSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this, starttingActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
