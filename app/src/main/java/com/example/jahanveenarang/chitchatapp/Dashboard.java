package com.example.jahanveenarang.chitchatapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabWidget;
import android.widget.TableLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {


    Toolbar toolbar ;
    private FirebaseAuth mAuth;
    ViewPager viewPager;
    private SectionViewPagerAdapter sectionViewPagerAdapter;
  //  private  TabLayout tabLayout;
  private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        mAuth = FirebaseAuth.getInstance();
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ChitChat");


        viewPager = findViewById(R.id.viewtab);
        sectionViewPagerAdapter = new SectionViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionViewPagerAdapter);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if (currentuser == null){
           sendToStart();
        }
    }

    private void sendToStart() {
        Intent i = new Intent(Dashboard.this , LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

         getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if(item.getItemId() == R.id.logout) {

             FirebaseAuth.getInstance().signOut();
             sendToStart();
         }
         if (item.getItemId() == R.id.profile)
         {
             Intent i = new Intent(Dashboard.this ,SettingsActivity.class);
             startActivity(i);
         }

        if (item.getItemId() == R.id.settings)
        {
            Intent i = new Intent(Dashboard.this ,Profile.class);
            startActivity(i);
        }
        return true ;
    }




}
