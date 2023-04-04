package com.cricket.crickais.crickcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class matchsh extends AppCompatActivity {
    private ArrayList<Model> list;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("MatchSh");
    ProgressBar progressBar;
    private RecyclerView recview;
    private RecyclerView recyclerView1;
    private MyAdapter2 adapter2;
    List<homing> homes;
    homageadapt1 homageadapt;
    ImageView kar;
    RelativeLayout log;
    TextView tt1;
    private ViewPager mViewPager;
    private TabLayout mtabLayout;
    private FragmentPagerAdapter mTabAccessorAdapter;

    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchsh);
        getSupportActionBar().hide();

        tt1=findViewById(R.id.tt1);
        kar=findViewById(R.id.kar);
        kar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        String stage = getIntent().getStringExtra("link1stage");
        String lg = getIntent().getStringExtra("link1stage1");
        String s1l = getIntent().getStringExtra("s1link");
        String v1l = getIntent().getStringExtra("v1link");
        String st1l = getIntent().getStringExtra("st1link");
        String pt1l = getIntent().getStringExtra("pt1link");
        tt1.setText(lg);
        mtabLayout = findViewById(R.id.main_tab);
        mViewPager = findViewById(R.id.main_tabs_pager);
        mTabAccessorAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        m1 tab111=new m1();
                        Bundle bundle = new Bundle();
                        bundle.putString("link1stage", stage);
                        tab111.setArguments(bundle);
                        return tab111;

                    case 1:
                       pt1 tab22 = new pt1();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("link1stage", stage);
                        tab22.setArguments(bundle2);
                        return tab22;



                    case 2:
                        st1 tab2 = new st1();
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("sl", stage);
                        tab2.setArguments(bundle3);
                        return tab2;

                }
                return null;
            }
            @Override
            public int getCount() {
                // Show 3 total pages.
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "Matches";

                    case 1:
                        return "Point Table";
                    case 2:
                        return "Stats";



                }
                return null;
            }

        };
        mViewPager.setAdapter(mTabAccessorAdapter);


        mtabLayout.setupWithViewPager(mViewPager);




    }
}