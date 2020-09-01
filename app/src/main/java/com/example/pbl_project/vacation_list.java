package com.example.pbl_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.pbl_project.MainActivity.num;
import static com.example.pbl_project.MainActivity.vacation_chart;

public class vacation_list extends AppCompatActivity {

    private Vacation_bean vacation_bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        ListView listview = (ListView)findViewById(R.id.vacation_list);

        List<String> list = new ArrayList<>();

        for(int i = 0; i<vacation_chart.size(); i++){
            list.add("휴가신청직원 : " + vacation_chart.get(i).getNum() + "\n" + vacation_chart.get(i).getSasu_num() + "과 " + vacation_chart.get(i).getTeamjang_num() + "의 승인 필요");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                vacation_bean = new Vacation_bean();
                vacation_bean = vacation_chart.get(position);

                if(num.equals(vacation_bean.getSasu_num()) || num.equals(vacation_bean.getTeamjang_num())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(vacation_list.this);
                    builder.setTitle("휴가신청 승인창");
                    builder.setMessage("\n휴가신청자 사원번호 : " + vacation_bean.getNum() + "\n"
                    + "휴가신청 기간 : " + vacation_bean.getStart_date() + "부터 " + vacation_bean.getEnd_date()
                    + "\n휴가신청 사유 : " + vacation_bean.getReason());

                    builder.setPositiveButton("승인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(num.equals(vacation_bean.getSasu_num())){
                                vacation_chart.get(position).setSasu_flag(true);
                                Toast.makeText(vacation_list.this, "사수 승인 완료", Toast.LENGTH_SHORT).show();
                            }
                            else if(num.equals(vacation_bean.getTeamjang_num())){
                                vacation_chart.get(position).setTeamjang_flag(true);
                                Toast.makeText(vacation_list.this, "팀장 승인 완료", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create().show();
                }
                else if(num.equals(vacation_bean.getNum())){
                    if(vacation_bean.isSasu_flag()==true && vacation_bean.isTeamjang_flag()==true){ //둘다 승인한 경우 조건문 안으로 들어오게 됨.
                        Toast.makeText(vacation_list.this, "휴가신청이 승인되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else { //둘다 승인한 경우 조건문 안으로 들어오게 됨.
                        Toast.makeText(vacation_list.this, "휴가신청 최종승인되지 않았습니다.\n조금만 기다려주십시오.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(vacation_list.this, "본인의 승인이 필요한 신청서가 아닙니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}