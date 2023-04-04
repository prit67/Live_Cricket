package com.cricket.crickais.crickcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class register1 extends AppCompatActivity {
    private EditText mobile,name;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        mobile = findViewById(R.id.mobile);


        databaseReference= FirebaseDatabase.getInstance().getReference("User");
        findViewById(R.id.cirLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number ="+"+mobile.getText().toString().trim();


//                if (number.isEmpty() || number.length() != 11) {
//                    mobile.setError("Valid number is required");
//                    mobile.requestFocus();
//                    return;
//                }

                String phonenumber =  number;
                Intent intent = new Intent(register1.this, otp1.class);
                intent.putExtra("phonenumber", phonenumber);

                startActivity(intent);




            }
        });

    }
}