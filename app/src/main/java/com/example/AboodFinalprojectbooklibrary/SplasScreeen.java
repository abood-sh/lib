package com.example.AboodFinalprojectbooklibrary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplasScreeen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplasScreeen.this,MainActivity.class);
                startActivity(i);
            }
        },3000);
    }
}