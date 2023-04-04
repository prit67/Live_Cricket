package com.cricket.crickais.crickcode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class register extends AppCompatActivity {
    private EditText mobile,name;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    CountryCodePicker ccp;
    TextView onLoginClick;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mobile = findViewById(R.id.mobile);
        ccp=findViewById(R.id.ccp);
        name=findViewById(R.id.name);
        onLoginClick=findViewById(R.id.onLoginClick);

        String ccd= ccp.getSelectedCountryCodeWithPlus();

        onLoginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), register1.class));
            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReference("User");
        findViewById(R.id.cirLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number =ccd+mobile.getText().toString().trim();
                String Name =name.getText().toString().trim();

//                if (number.isEmpty() || number.length() != 11) {
//                    mobile.setError("Valid number is required");
//                    mobile.requestFocus();
//                    return;
//                }else if (Name.isEmpty() ) {
//                    name.setError("Valid number is required");
//                    name.requestFocus();
//                    return;
//                }

                String phonenumber =  number;
                Intent intent = new Intent(register.this, otp.class);
                intent.putExtra("phonenumber", phonenumber);
                intent.putExtra("name", Name);
                startActivity(intent);




            }
        });

    }
}