package com.example.pbl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.pbl_project.MainActivity.vacation_chart;

public class vacation_list extends AppCompatActivity {

    private ListView listView;
    ArrayList<Vacation_bean> list = new ArrayList<Vacation_bean>();
    ArrayAdapter<Vacation_bean> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        //사원번호에 따라 전체 리스트에서 검색해서 listview에 출력

        listView = findViewById(R.id.vacation_list);
        for(int i = 0 ; i < vacation_chart.size() ; i++){
            list.add(vacation_chart.get(i));
        }

        arrayAdapter = new ArrayAdapter<Vacation_bean>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position 몇번째 눌렀는지에 따라 vacation_chart안에 flag값 바꾸기
            }
        });
    }
}
