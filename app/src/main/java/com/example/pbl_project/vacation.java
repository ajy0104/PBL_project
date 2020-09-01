package com.example.pbl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.pbl_project.MainActivity.num;
import static com.example.pbl_project.MainActivity.vacation_chart;

public class vacation extends AppCompatActivity {

    private EditText edt_reason, edt_sasu, edt_teamjang;
    private Spinner start_year, start_month, start_date, end_year, end_month, end_date, spn_reason;
    private Button btn_submit;
    private Vacation_bean vc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation);


        vc = new Vacation_bean();

        start_year = findViewById(R.id.start_year);
        start_month = findViewById(R.id.start_month);
        start_date = findViewById(R.id.start_date);

        end_year = findViewById(R.id.end_year);
        end_month = findViewById(R.id.end_month);
        end_date = findViewById(R.id.end_date);

        spn_reason = findViewById(R.id.spn_reason);

        edt_reason = findViewById(R.id.edt_reason);

        btn_submit = findViewById(R.id.btn_submit);

        edt_sasu = findViewById(R.id.edt_sasu);

        edt_teamjang = findViewById(R.id.edt_teamjang);

        vc.setNum(num); //사원번호와 함께 생성자에 저장

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_sasu.getText().toString().length()==0 || edt_teamjang.getText().toString().length()==0){
                    Toast.makeText(vacation.this,"사수와 팀장의 사수번호를 모두 입력하세요.",Toast.LENGTH_SHORT).show();
                }
                else { //빈칸이 없다면..

                    //버튼 누르면 클래스에 정보 담아서 전송
                    String start_day = start_year.getSelectedItem().toString() + "년 " + start_month.getSelectedItem().toString() + "월 " + start_date.getSelectedItem().toString() + "일";
                    vc.setStart_date(start_day);

                    String end_day = end_year.getSelectedItem().toString() + "년 " + end_month.getSelectedItem().toString() + "월 " + end_date.getSelectedItem().toString() + "일";
                    vc.setEnd_date(end_day);

                    String reason = spn_reason.getSelectedItem().toString() + "\n" + "추가사유 : " + edt_reason.getText().toString();
                    vc.setReason(reason);

                    String sasu = edt_sasu.getText().toString();
                    vc.setSasu_num(sasu);

                    String teamjang = edt_teamjang.getText().toString();
                    vc.setTeamjang_num(teamjang);

                    vacation_chart.add(vc);
                    //클래스에 휴가신청 정보 몽땅 담기 완료.

                    Intent i = new Intent(vacation.this, MainActivity.class);
                    i.putExtra("num", num);
                    startActivity(i);

                }
            }
        });

    }
}
