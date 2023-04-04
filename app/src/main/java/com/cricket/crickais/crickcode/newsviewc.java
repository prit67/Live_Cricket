package com.cricket.crickais.crickcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class newsviewc extends AppCompatActivity {

    RelativeLayout log;
    private ArrayList<Model> list;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("MatchSh");
    ProgressBar progressBar;
    private RecyclerView recview;
    private RecyclerView recyclerView1;
    private MyAdapter2 adapter2;
    List<homing> homes;
    TextView tt1;
    ImageView kar;
    WebView webView;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsviewc);
        getSupportActionBar().hide();
        kar=(ImageView)findViewById(R.id.kar);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tt1=(TextView) findViewById(R.id.tt1);
        String stage = getIntent().getStringExtra("lkk");
        String head = getIntent().getStringExtra("head");
        tt1.setText(head);
        webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(stage);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        webView.canGoBack();
        webView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

                view.loadUrl("about:blank");
                Toast.makeText(newsviewc.this, "Please check newtwork connectivity", Toast.LENGTH_SHORT).show();

            }
        });


    }

}