package com.example.jahanveenarang.chitchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button bt_continue ;
    private EditText editText;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bt_continue = findViewById(R.id.buttonContinue);
        editText = findViewById(R.id.editTextPhone);
        name= findViewById(R.id.displayname);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = editText.getText().toString().trim();
                String dName = name.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10  ) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                if ( dName == "" ) {
                    name.setError("Valid name is required");
                    name.requestFocus();
                    return;
                }

                String phoneNumber = number;

                Intent intent = new Intent(LoginActivity.this, Signup.class);
                intent.putExtra("phonenumber", phoneNumber);
                intent.putExtra("displayName", dName);
                startActivity(intent);
                finish();

            }
        });

    }
}
