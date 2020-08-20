package com.example.pbl_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Add_schedule extends AppCompatActivity {
    private TextView txtYMD;
    private TextView txt;
    public Spinner spinner;
    public String sc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schecule);

        txt = findViewById(R.id.txt);
        spinner = (Spinner)findViewById(R.id.spinner);
        txtYMD = findViewById(R.id.txtYMD);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        final String ymn = intent.getExtras().getString("날짜");
        txtYMD.setText(ymn+"일 일정");



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add_schedule.this, ScheduleActivity.class);
                startActivity(i);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sc = spinner.getSelectedItem().toString();
                //ScheduleItem SI = new ScheduleItem("");
                //SI.setSchedule(sc);
                Intent i = new Intent(Add_schedule.this, ScheduleActivity.class);
                i.putExtra("일정",sc);
                i.putExtra("index",ymn);
                startActivity(i);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //textView.setText("일정명 : "+parent.getItemAtPosition(position));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }//onCreate()







}
