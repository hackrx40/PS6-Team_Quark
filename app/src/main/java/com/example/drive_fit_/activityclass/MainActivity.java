package com.example.drive_fit_.activityclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.drive_fit_.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView homebtn = (ImageView) findViewById(R.id.homebtn);
        ImageView bazaarbtn = (ImageView) findViewById(R.id.shopbtn);
        ImageView communitybtn = (ImageView) findViewById(R.id.commbtn);
        ImageView userbtn = (ImageView) findViewById(R.id.userbtn);

        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction t = m.beginTransaction();
        Fragment Home = new Home();
        t.replace(R.id.fragment,Home);
        t.commit();
        homebtn.setImageResource(R.drawable.homecolor);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment Home = new Home();
                t.replace(R.id.fragment, Home);
                t.commit();
                homebtn.setImageResource(R.drawable.homecolor);
                bazaarbtn.setImageResource(R.drawable.shopoutline);
                communitybtn.setImageResource(R.drawable.heart);
                userbtn.setImageResource(R.drawable.useroutline);
            }
        });

        communitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment community = new Community();
                t.replace(R.id.fragment, community);
                t.commit();
                homebtn.setImageResource(R.drawable.homeoutline);
                bazaarbtn.setImageResource(R.drawable.shopoutline);
                communitybtn.setImageResource(R.drawable.like);
                userbtn.setImageResource(R.drawable.useroutline);
            }
        });

        bazaarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment bazaar = new Bazaar();
                t.replace(R.id.fragment, bazaar);
                t.commit();
                homebtn.setImageResource(R.drawable.homeoutline);
                bazaarbtn.setImageResource(R.drawable.shopcolor);
                communitybtn.setImageResource(R.drawable.heart);
                userbtn.setImageResource(R.drawable.useroutline);
            }
        });

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment user = new Profile();
                t.replace(R.id.fragment, user);
                t.commit();
                homebtn.setImageResource(R.drawable.homeoutline);
                bazaarbtn.setImageResource(R.drawable.shopoutline);
                communitybtn.setImageResource(R.drawable.heart);
                userbtn.setImageResource(R.drawable.usercolor);
            }
        });

    }
}