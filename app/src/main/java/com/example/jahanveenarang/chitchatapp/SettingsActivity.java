package com.example.jahanveenarang.chitchatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    FirebaseUser mfirebaseUser;
    DatabaseReference mdatabase;
    private StorageReference mStorage;


    TextView displayName, displayStatus;
    Button changeDisplayPicture, changeDisplayStatus;
    CircleImageView displayImage;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        displayName = findViewById(R.id.displayName);
        displayStatus = findViewById(R.id.displayStatus);
        changeDisplayPicture = findViewById(R.id.changeDisplaypic);
        changeDisplayStatus = findViewById(R.id.changeDisplayStatus);
        displayImage = findViewById(R.id.displayImage);

        mStorage = FirebaseStorage.getInstance().getReference();
        mfirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final String uid = mfirebaseUser.getUid();

        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("DisplayName").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String thumbnail = dataSnapshot.child("thumbnail").getValue().toString();

                displayName.setText(name);
                displayStatus.setText(status);
                Picasso.get().load(image).into(displayImage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changeDisplayPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "SELECT IMAGE"), 1);
            }
        });

        changeDisplayStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SettingsActivity.this, StatusActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            Uri imageUri = data.getData();
//            CropImage.activity(imageUri).setAspectRatio(1, 1)
//                    .start(this);
//        }
//
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                progressDialog = new ProgressDialog(SettingsActivity.this);
//                progressDialog.setTitle("Uploading the image");
//                progressDialog.setMessage("Please wait while we are saving the image....");
//                progressDialog.show();
//                progressDialog.setCanceledOnTouchOutside(false);
//                Uri resultUri = result.getUri();
//
//                String currentUserid = mfirebaseUser.getUid();
//                final StorageReference filePath = mStorage.child("user_profile_pictures").child(currentUserid + ".jpg");
//
//                filePath.putFile(resultUri);
                //         .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

//
//                        if (task.isSuccessful()){
//

//                          //  String download_url = filePath.getDownloadUrl().toString();
//                            mdatabase .child("image").setValue(download_url).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        progressDialog.dismiss();
//                                        Toast.makeText(getApplicationContext() , "Saving the chnages", Toast.LENGTH_LONG).show();
//
//                                    }
//                                }
//                            });

//                        }
//                        else
//                        {
//                            Toast.makeText(getApplicationContext() , "Error while saving the chnages", Toast.LENGTH_LONG).show();
//
////                        }
////                    }
////                });
//
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }
//        }
//
//    }
}



