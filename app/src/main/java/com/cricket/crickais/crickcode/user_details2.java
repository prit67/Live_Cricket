package com.cricket.crickais.crickcode;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class user_details2 extends AppCompatActivity {
    EditText name,web,about,phnnum,images;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    CircularProgressButton profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details2);

        name=findViewById(R.id.name);
        web=findViewById(R.id.web);
        about=findViewById(R.id.about);
        phnnum=findViewById(R.id.phnnum);
        images=findViewById(R.id.images);



        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("User").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mod2 userInformation =snapshot.getValue(mod2.class);
                if(userInformation!=null) {
                    name.setText(userInformation.getName());
                    about.setText(userInformation.getAbout());
                    web.setText(userInformation.getWeb());
                    phnnum.setText(userInformation.getNumber());
                    images.setText(userInformation.getImage());
                }else{
                    name.setText("");
                    about.setText("");
                    web.setText("");
                    phnnum.setText("");
                    images.setText(".");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        String Number = phnnum.getText().toString().trim();
        String image =images.getText().toString().trim();

        if ((Name.isEmpty())) {
            name.setError("Enter Your Name");
            name.requestFocus();
        }
        else {

            ProgressDialog progressDialog = new ProgressDialog(user_details2.this);
            progressDialog.setMessage("Saving Data....");
            progressDialog.show();

            mod2 userInformation2 = new mod2(Name, Web, About,Number,image);
            FirebaseUser user = firebaseAuth.getCurrentUser();

            databaseReference.setValue(userInformation2);

            startActivity(new Intent(user_details2.this,starttingActivity.class));


        }

    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(user_details2.this,R.style.MyDialogTheme)
                .setTitle("Exiting Login")
                .setMessage("Are you sure you want to exit ?")
                .setPositiveButton("Yes, exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(user_details2.this, starttingActivity.class));
                    }
                })
                .setNegativeButton("No", null);

        builder.show();


    }
}

