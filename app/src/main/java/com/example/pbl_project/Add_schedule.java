package com.example.pbl_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Add_schedule extends AppCompatActivity {
    private TextView txtYMD;
    ScheduleItem SI = new ScheduleItem();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schecule);

        final TextView textView = (TextView)findViewById(R.id.txtSchedule);
        txtYMD = findViewById(R.id.txtYMD);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnSave = findViewById(R.id.btnSave);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String sc = spinner.getSelectedItem().toString();
        SI.setSchedule(sc);

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
                Intent i = new Intent(Add_schedule.this, ScheduleActivity.class);
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
