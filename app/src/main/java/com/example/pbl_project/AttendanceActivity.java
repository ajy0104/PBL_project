package com.example.pbl_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AttendanceActivity extends AppCompatActivity {

    private TextView txtTitle; // 연/월 텍스트뷰
    private Button btnPrev;
    private Button btnNext;


    //private GridAdapter gridAdapter; // 그리드뷰 어댑터
    private ArrayList<String> dayList;     // 요일 저장 할 리스트
    private GridView gridView;  // 그리드뷰
    private Calendar mCal; // 캘린더변수
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
