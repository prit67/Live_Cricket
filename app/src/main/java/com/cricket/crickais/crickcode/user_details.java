package com.cricket.crickais.crickcode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_details extends AppCompatActivity {
    EditText name,web,about;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name=findViewById(R.id.name);
        web=findViewById(R.id.web);
        about=findViewById(R.id.about);
        String Name = getIntent().getStringExtra("name");
        name.setText(Name);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("User");
        FirebaseUser user=firebaseAuth.getCurrentUser();

        findViewById(R.id.cirLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });

    }
    private void saveUserInformation() {
        String Name = name.getText().toString().trim();
        String Web = web.getText().toString().trim();
        String About = about.getText().toString().trim();
        String Number = getIntent().getStringExtra("phonenumber");
        String image =".";

        if ((Name.isEmpty())) {
            name.setError("Enter Your Name");
            name.requestFocus();
        }
        else {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Saving Data....");
            progressDialog.show();

            mod2 userInformation = new mod2(Name, Web, About,Number,image);
            FirebaseUser user = firebaseAuth.getCurrentUser();

            databaseReference.child(user.getUid()).setValue(userInformation);

            startActivity(new Intent(this,pro_img.class));


        }

    }
}

