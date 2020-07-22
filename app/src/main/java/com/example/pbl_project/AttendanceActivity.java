package com.example.pbl_project;

import android.os.Bundle;

import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.Calendar;



public class AttendanceActivity extends AppCompatActivity {

    private ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_attendance);

        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(2);
        pagerAdapter vpAdapter = new pagerAdapter(getSupportFragmentManager());
        //vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        FragmentFirst fragment1 = new FragmentFirst();
        vpAdapter.addItem(fragment1);

        FragmentSecond fragment2 = new FragmentSecond();
        vpAdapter.addItem(fragment2);

        vp.setAdapter(vpAdapter);

    }


} // AttendanceActivity
