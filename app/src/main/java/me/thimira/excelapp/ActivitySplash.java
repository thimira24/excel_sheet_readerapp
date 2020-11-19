package me.thimira.excelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivitySplash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread myThread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(ActivitySplash.this, MainActivity.class));
                    finish();
                } catch (Exception e) {

                }
            }
        };

        myThread.start();
    }
}