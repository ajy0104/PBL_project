package com.example.pbl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton vacation;
    private ImageButton security;
    private ImageButton User_attend;
    private ImageButton scedule;
    private TextView id;
    private Button logout;

    public static ArrayList<Vacation_bean> vacation_chart = new ArrayList<Vacation_bean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.txtId);

        String id2 = getIntent().getStringExtra("사원번호");
        id.setText(id2);

        vacation = findViewById(R.id.vacation);
        security = findViewById(R.id.security);
        User_attend = findViewById(R.id.User_attend);
        scedule = findViewById(R.id.schedule);
        logout = findViewById(R.id.btn_logout2);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, vacation.class);
                startActivity(i);
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(MainActivity.this, ???.class);
                startActivity(i); */
            }
        });

        User_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(MainActivity.this, ???.class);
                startActivity(i);*/
            }
        });

        scedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(i);
            }
        });

    }
}
