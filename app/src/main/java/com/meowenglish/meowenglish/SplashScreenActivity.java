package com.meowenglish.meowenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import nl.siegmann.epublib.epub.Main;

public class SplashScreenActivity extends AppCompatActivity {
    private static int TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, TIME_OUT);

    }
}