package com.example.drive_fit_.activityclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.drive_fit_.R;
import com.example.drive_fit_.activityclass.MainActivity;

public class Rewards extends AppCompatActivity {

    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        backbtn = findViewById(R.id.backbutton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Rewards.this,MainActivity.class);
                startActivity(i);

            }
        });
    }
}