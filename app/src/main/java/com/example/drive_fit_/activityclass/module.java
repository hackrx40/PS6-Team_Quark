package com.example.drive_fit_.activityclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.drive_fit_.R;
import com.example.drive_fit_.genie_insight;
import com.example.drive_fit_.module_ar;
import com.example.drive_fit_.module_ml;

public class module extends AppCompatActivity {

    ImageView mark1, mark2, mark3, nextbtn, backbtn;
    int count = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);


        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction t = m.beginTransaction();
        Fragment Home = new genie_insight();
        //Home.setArguments(bundle);
        t.replace(R.id.fragmodule, Home);
        t.commit();


        nextbtn = (ImageView) findViewById(R.id.nextbtn);
        backbtn = (ImageView) findViewById(R.id.backbtn);

        mark1 = (ImageView) findViewById(R.id.mark1);
        mark2 = (ImageView) findViewById(R.id.mark2);
        mark3 = (ImageView) findViewById(R.id.mark3);

        mark1.setImageResource(R.drawable.onboarding_markoutline);
        mark2.setImageResource(R.drawable.onboarding_mark);
        mark3.setImageResource(R.drawable.onboarding_markoutline);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), " "+count, Toast.LENGTH_SHORT).show();
                count++;

                if(count == 1)
                {
                    FragmentManager m = getSupportFragmentManager();
                    FragmentTransaction t = m.beginTransaction();
                    Fragment Home = new module_ar();
                    t.replace(R.id.fragmodule, Home);
                    t.commit();

                    mark1.setImageResource(R.drawable.onboarding_mark);
                    mark2.setImageResource(R.drawable.onboarding_markoutline);
                    mark3.setImageResource(R.drawable.onboarding_markoutline);



                }
                else if(count == 2)
                {
                    FragmentManager m = getSupportFragmentManager();
                    FragmentTransaction t = m.beginTransaction();
                    Fragment Home = new genie_insight();
                    t.replace(R.id.fragmodule, Home);
                    t.commit();

                    mark1.setImageResource(R.drawable.onboarding_markoutline);
                    mark2.setImageResource(R.drawable.onboarding_mark);
                    mark3.setImageResource(R.drawable.onboarding_markoutline);

                }
                else if(count == 3)
                {
                    FragmentManager m = getSupportFragmentManager();
                    FragmentTransaction t = m.beginTransaction();
                    Fragment Home = new module_ml();
                    //Home.setArguments(bundle);
                    t.replace(R.id.fragmodule, Home);
                    t.commit();

                    mark1.setImageResource(R.drawable.onboarding_markoutline);
                    mark2.setImageResource(R.drawable.onboarding_markoutline);
                    mark3.setImageResource(R.drawable.onboarding_mark);


                }
                else if(count >= 3)
                {

                    //Toast.makeText(getApplicationContext(), "Cannot move", Toast.LENGTH_SHORT).show();
                }


            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), " "+count, Toast.LENGTH_SHORT).show();
                count--;

                if(count == 1)
                {
                    FragmentManager m = getSupportFragmentManager();
                    FragmentTransaction t = m.beginTransaction();
                    Fragment Home = new module_ar();
                    t.replace(R.id.fragmodule, Home);
                    t.commit();

                    mark1.setImageResource(R.drawable.onboarding_mark);
                    mark2.setImageResource(R.drawable.onboarding_markoutline);
                    mark3.setImageResource(R.drawable.onboarding_markoutline);



                }
                else if(count == 2)
                {
                    FragmentManager m = getSupportFragmentManager();
                    FragmentTransaction t = m.beginTransaction();
                    Fragment Home = new genie_insight();
                    t.replace(R.id.fragmodule, Home);
                    t.commit();

                    mark1.setImageResource(R.drawable.onboarding_markoutline);
                    mark2.setImageResource(R.drawable.onboarding_mark);
                    mark3.setImageResource(R.drawable.onboarding_markoutline);

                }
                else if(count == 3)
                {
                    FragmentManager m = getSupportFragmentManager();
                    FragmentTransaction t = m.beginTransaction();
                    Fragment Home = new module_ml();
                    //Home.setArguments(bundle);
                    t.replace(R.id.fragmodule, Home);
                    t.commit();

                    mark1.setImageResource(R.drawable.onboarding_markoutline);
                    mark2.setImageResource(R.drawable.onboarding_markoutline);
                    mark3.setImageResource(R.drawable.onboarding_mark);


                }
                else if(count >= 3)
                {

                    //Toast.makeText(getApplicationContext(), "Cannot move", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
}