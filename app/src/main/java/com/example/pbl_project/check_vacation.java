package com.example.pbl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class check_vacation extends AppCompatActivity {

    private ImageButton vacation;
    private ImageButton security;
    private ImageButton User_attend;
    private ImageButton scedule;
    private Button btn_accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_vacation);

        vacation = findViewById(R.id.vacation);
        security = findViewById(R.id.security);
        User_attend = findViewById(R.id.User_attend);
        scedule = findViewById(R.id.schedule);
        btn_accept = findViewById(R.id.btn_accept);

        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(check_vacation.this, vacation.class);
                startActivity(i);
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(check_vacation.this, ???.class);
                startActivity(i); */
            }
        });

        User_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(check_vacation.this, ???.class);
                startActivity(i);*/
            }
        });

        scedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(check_vacation.this, ScheduleActivity.class);
                startActivity(i);
            }
        });

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //휴가신청 리스트 xml로 이동.
            }
        });
    }
}
