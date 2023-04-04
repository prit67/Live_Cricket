package com.cricket.crickais.crickcode;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import in.aabhasjindal.otptextview.OtpTextView;

public class otp1 extends AppCompatActivity {
    private String verificationid;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private OtpTextView editText;
    private TextView numshow;
    private TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp1);

        numshow=findViewById(R.id.numshow);
        timer=(TextView)findViewById(R.id.textView2);

        int second=Integer.valueOf(60);
        CountDownTimer countDownTimer=new CountDownTimer(second * 1000,1000) {
            @Override
            public void onTick(long millis) {
                timer.setText("Resend OTP in :"+(int)(millis/1000));

            }

            @Override
            public void onFinish() {
                timer.setText("Resend OTP");

            }
        }.start();






        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);

        editText = findViewById(R.id.otp);
        if(
        editText.getOTP().length()<8){
            findViewById(R.id.cirLoginButton).setVisibility(View.VISIBLE);
        }


        String phonenumber = getIntent().getStringExtra("phonenumber");
        numshow.setText(phonenumber);
        sendVerificationCode(phonenumber);

        findViewById(R.id.cirLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editText.getOTP().toString().trim();

                if ((code.length() < 6)){
                    editText.requestFocus();
                    progressBar.setVisibility(View.VISIBLE);
                    return;
                }
                verifyCode(code);

            }
        });
    }



    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String phonenumber = getIntent().getStringExtra("phonenumber");

                            Intent intent = new Intent(otp1.this, user_details2.class);
                            intent.putExtra("phonenumber", phonenumber);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {
                            Toast.makeText(otp1.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    private void sendVerificationCode(String number){

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(otp1.this, e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
        signInWithCredential(credential);
    }

}

