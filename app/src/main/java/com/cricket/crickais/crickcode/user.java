package com.cricket.crickais.crickcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class user extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private Button button,button2;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        databaseReference= FirebaseDatabase.getInstance().getReference("nether");
        txt1=(TextView)findViewById(R.id.txt1) ;
        txt2=(TextView)findViewById(R.id.txt2) ;
        txt3=(TextView)findViewById(R.id.txt3) ;
        txt4=(TextView)findViewById(R.id.txt4) ;
        txt5=(TextView)findViewById(R.id.txt5) ;
        txt6=(TextView)findViewById(R.id.txt6) ;
        txt7=(TextView)findViewById(R.id.txt7) ;
        txt8=(TextView)findViewById(R.id.txt8) ;
        txt9=(TextView)findViewById(R.id.txt9) ;
        button=(Button) findViewById(R.id.button) ;
        button2=(Button) findViewById(R.id.button2) ;

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,starttingActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });



    }
    private void saveUserInformation() {
        String Date = txt1.getText().toString().trim();
        String Matchno = txt2.getText().toString().trim();
        String Sta = txt3.getText().toString().trim();
        String Team = txt4.getText().toString().trim();
        String Team1 = txt5.getText().toString().trim();
        String Team2 = txt6.getText().toString().trim();
        String Head = txt7.getText().toString().trim();
        String Newshead = txt8.getText().toString().trim();
        String Newssub = txt9.getText().toString().trim();

        String Newsimg = txt8.getText().toString().trim();
        String News = txt8.getText().toString().trim();
        String News1 = txt8.getText().toString().trim();
        String idd = txt8.getText().toString().trim();
        String ts1 = txt8.getText().toString().trim();
        String ts2 = txt8.getText().toString().trim();
        String desc = txt8.getText().toString().trim();
        String mom = txt8.getText().toString().trim();
        String dream1 = txt8.getText().toString().trim();
        String dream2 = txt8.getText().toString().trim();
        String dream3 = txt8.getText().toString().trim();
        String dream4 = txt8.getText().toString().trim();
        String dream5 = txt8.getText().toString().trim();
        String place = txt8.getText().toString().trim();






            UserInformation userInformation = new UserInformation(Date,Sta,Sta,Team,Team1,Team2,Head,Newshead,Newssub,Newsimg,News,News1,idd,ts1,ts2,desc,
                    mom,dream1,dream2,dream3,dream4,dream5,place);


            databaseReference.child(Matchno).setValue(userInformation);

            Toast.makeText(this, "Saved data Successfully", Toast.LENGTH_LONG).show();



        }

    }







