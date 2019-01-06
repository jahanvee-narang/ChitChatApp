package com.example.jahanveenarang.chitchatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    Button save_status_button;
    TextInputEditText updated_status ;
    Toolbar status_toolbar;
    DatabaseReference statusDataabse ;
    FirebaseUser currentUser ;
    ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        save_status_button = findViewById(R.id.status_save_btn);
        updated_status = findViewById(R.id.statusUpdate);

        status_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(status_toolbar);
        getSupportActionBar().setTitle("Account Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser.getUid();

        statusDataabse = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);


        save_status_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar = new ProgressDialog(StatusActivity.this);
                progressBar.setTitle("Saving Changes....");
                progressBar.setMessage("Please wait while we save the changes");
                progressBar.show();
                String updatedStatusText = updated_status.getText().toString();
                statusDataabse.child("Status").setValue(updatedStatusText).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            progressBar.dismiss();
                            Intent i = new Intent(StatusActivity.this , SettingsActivity.class);
                            startActivity(i);
                            finish();
                        }

                        else {
                            Toast.makeText(getApplicationContext() , "We are facing problem in saving the changes, Please try again later", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

    }
}
