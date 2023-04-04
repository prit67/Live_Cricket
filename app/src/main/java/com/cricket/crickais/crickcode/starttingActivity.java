package com.cricket.crickais.crickcode;


import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AlertDialog;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import de.hdodenhof.circleimageview.CircleImageView;

public class starttingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

private DrawerLayout drawerLayout;
private ActionBarDrawerToggle toggle;
private NavigationView navigationView;

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    TextView avc;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    public static  int UPDATE_CODE=22;
    AppUpdateManager appUpdateManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startting);

        getSupportActionBar().setElevation(0);
        //loading the default fragment

        inAppUp();

        BottomNavigationView bottomNavigationView1=(BottomNavigationView)findViewById(R.id.bottomNavigationView);
        View abv = bottomNavigationView1.getChildAt(0);
        Menu abc= bottomNavigationView1.getMenu();
        MenuItem jkl = abc.findItem(R.id.navigation_notifications);


        NavigationView navigationView1 = (NavigationView) findViewById(R.id.navmenu);
        View headerView = navigationView1.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.avc);
        TextView as1 = (TextView) headerView.findViewById(R.id.as1);
        TextView as = (TextView) headerView.findViewById(R.id.as);
        TextView inn = (TextView) headerView.findViewById(R.id.inn);
        CircleImageView prof = (CircleImageView) headerView.findViewById(R.id.prof);
        RelativeLayout keo = (RelativeLayout) headerView.findViewById(R.id.keo);
        keo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(starttingActivity.this, register.class);
                startActivity(intent);
            }
        });
        inn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(starttingActivity.this, register1.class);
                startActivity(intent1);
            }
        });

        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference1=firebaseDatabase.getReference("Featuresmain").child("name");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stage = snapshot.getValue(String.class);
                jkl.setTitle(stage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(firebaseAuth.getCurrentUser()!=null){
            keo.setVisibility(View.GONE);
            navUsername.setVisibility(View.VISIBLE);
            as1.setVisibility(View.VISIBLE);
            as.setVisibility(View.INVISIBLE);
            inn.setVisibility(View.INVISIBLE);



         databaseReference=firebaseDatabase.getReference("User").child(user.getUid());
         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 mod2 userInformation =snapshot.getValue(mod2.class);
                 navUsername.setText(userInformation.getName());

                 as1.setText(userInformation.getAbout());

                 if(!userInformation.getImage().equals(".")) {
                     Picasso.get().load(userInformation.getImage()).into(prof);
                 }else{
                     prof.setImageResource(R.drawable.sq);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
        }

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navmenu);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        navController= Navigation.findNavController(this, R.id.frame_layout);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);



    }

    private void inAppUp() {
        appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> task = appUpdateManager.getAppUpdateInfo();
        task.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                if(result.updateAvailability()== UpdateAvailability.UPDATE_AVAILABLE
                && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
                    try {
                        appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.FLEXIBLE,
                                starttingActivity.this,UPDATE_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        appUpdateManager.registerListener(installStateUpdatedListener);
    }
    private InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState state) {
            if(state.installStatus()== InstallStatus.DOWNLOADED){
                showCompleteUpdate();
            }

        }
    };

    @Override
    protected void onStop() {
        if(appUpdateManager!=null) appUpdateManager.unregisterListener(installStateUpdatedListener);
        super.onStop();
    }

    private void showCompleteUpdate() {
        Snackbar snackbar= Snackbar.make(findViewById(android.R.id.content),"New app is ready!",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==UPDATE_CODE && resultCode!=RESULT_OK) {
            Toast.makeText(this,"Cancel",Toast.LENGTH_LONG).show();
        }
            super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(starttingActivity.this,R.style.MyDialogTheme)
                .setTitle("Exiting CrickLive")
                .setMessage("Are you sure you want to exit CrickLive ?")
                .setPositiveButton("Yes, exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        starttingActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null);

        builder.show();


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_gb:
                if(firebaseAuth.getCurrentUser()!=null) {
                    Intent intent = new Intent(this, memeup1.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(starttingActivity.this, "Please Sign in", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_simple:
                if(firebaseAuth.getCurrentUser()!=null) {
                    Intent intent1 = new Intent(this, profile.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(starttingActivity.this, "Please Sign in", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign:
                if(firebaseAuth.getCurrentUser()!=null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(starttingActivity.this)
                            .setTitle("Logout")
                            .setMessage("Do you want to logout ?")
                            .setPositiveButton("Yes, Logout", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    firebaseAuth.signOut();
                                    finish();
                                    startActivity(new Intent(starttingActivity.this, starttingActivity.class));
                                }
                            })
                            .setNegativeButton("No", null);

                    builder.show();
                }else{
                    Toast.makeText(starttingActivity.this, "Please Sign in", Toast.LENGTH_SHORT).show();
                }break;
            case R.id.nav_share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody="Download CrickLive live score Now. Get All live updates of Cricket in one place.http://play.google.com/store/apps/details?id=" + this.getPackageName();
                String shareSub="Download this App now";
                intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(intent,"Share"));
                break;

            case R.id.nav_rate:
                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
                break;


        }
            return false;
    }
}
