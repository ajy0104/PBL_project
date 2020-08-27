package com.example.pbl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.pbl_project.MainActivity.vacation_chart;

public class vacation_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        ListView listview = (ListView)findViewById(R.id.vacation_list);

        List<String> list = new ArrayList<>();

        for(int i = 0; i<vacation_chart.size(); i++){
            list.add("휴가신청직원 : " + vacation_chart.get(i).getNum());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //현재접속 아이디.equals(사수번호 or 팀장 번호)일 경우만 글씨체 변화?
            }
        });
    }
}