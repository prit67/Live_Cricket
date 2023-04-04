package com.cricket.crickais.crickcode;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class memeup1 extends AppCompatActivity {
    ImageView imageview1;
    Spinner categ;
    ArrayAdapter<CharSequence> categadop;
    TextView button1,post,point;
    private Uri imageUri;
    RelativeLayout postrel,post1rel;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Trial");
    private DatabaseReference rootp = FirebaseDatabase.getInstance().getReference("NewsPartE");
    private DatabaseReference root1 = FirebaseDatabase.getInstance().getReference("Trial1");
    private DatabaseReference rootp1 = FirebaseDatabase.getInstance().getReference("NewsPartAs");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    EditText first,editTextTextPersonName,editTextTextPersonName1,editTextTextPersonName2,editTextTextPersonName3;
    private FirebaseAuth firebaseAuth;
    EditText caption,name,title;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    CircleImageView icc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeup1);

        button1 = findViewById(R.id.button1);
        imageview1 = findViewById(R.id.icon);
        categ=findViewById(R.id.categ);
        categadop=ArrayAdapter.createFromResource(this,R.array.categarray, R.layout.list_item);
        icc=findViewById(R.id.icc);
        point=findViewById(R.id.point);
        post=findViewById(R.id.post);
        categadop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categ.setAdapter(categadop);

        postrel=findViewById(R.id.postrel);
        post1rel=findViewById(R.id.post1rel);
        caption=findViewById(R.id.caption);
        name=findViewById(R.id.name);
        title=findViewById(R.id.title);

        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference=firebaseDatabase.getReference("User").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mod2 userInformation =snapshot.getValue(mod2.class);
                point.setText(userInformation.getName());

                if(!userInformation.getImage().equals(".")) {
                    Picasso.get().load(userInformation.getImage()).into(icc);
                }else{
                    icc.setImageResource(R.drawable.sq);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(memeup1.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(524)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(memeup1.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
                String nam =name.getText().toString().trim();
                String ttl =title.getText().toString().trim();
                if (nam.isEmpty()|| ttl.isEmpty() ) {
                    name.setError("Required");
                    name.requestFocus();
                    return;
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data.getData();
        if(imageUri!=null){
            button1.setText("Change Image");
            post1rel.setVisibility(View.INVISIBLE);
            postrel.setVisibility(View.VISIBLE);
        }

        imageview1.setImageURI(imageUri);
    }
    private void uploadToFirebase(Uri uri){

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Date d = new Date();
                        CharSequence s  = DateFormat.format("yyyy-MM-dd ", d.getTime());
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String reg_date = df.format(c.getTime());
                        c.add(Calendar.DATE, 0);  // number of days to add

                        String modid = root.push().getKey();

                        String head = name.getText().toString().trim();
                        String idd = user.getUid();
                        String news = caption.getText().toString().trim();
                        String newshead = title.getText().toString().trim();
                        String newssub = df.format(c.getTime());
                        String desc = root.push().getKey();
                        String news1 = categ.getSelectedItem().toString();



                        if(news1.equals("English")) {
                            mod3 model = new mod3(head,idd,news,newshead,newssub,desc, uri.toString());
                            root.child(desc).setValue(model);
                            Toast.makeText(memeup1.this, "After verification your post will be visible", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(memeup1.this, starttingActivity.class);
                            startActivity(intent);
                        }
                       else if(news1.equals("Assamese")) {
                            mod3 model = new mod3(head, idd,news,newshead,newssub,desc, uri.toString());
                            root1.child(desc).setValue(model);
                            Toast.makeText(memeup1.this, "After verification your post will be visible", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(memeup1.this, starttingActivity.class);
                            startActivity(intent);
                        }

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                ProgressDialog progressDialog = new ProgressDialog(memeup1.this);
                progressDialog.setMessage("Saving Data....");
                progressDialog.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(memeup1.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}