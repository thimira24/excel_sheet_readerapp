package me.thimira.excelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.apache.log4j.chainsaw.Main;

public class SyncingFailedActivity extends AppCompatActivity {

    private Button reloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syncing_failed);

        reloadButton = findViewById(R.id.btn_reload);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SyncingFailedActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}