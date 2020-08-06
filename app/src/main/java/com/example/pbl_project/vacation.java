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

public class vacation extends AppCompatActivity {

    private EditText edt_reason, edt_sasu, edt_teamjang;
    private Spinner start_year, start_month, start_date, end_year, end_month, end_date, spn_reason;
    private Button btn_submit;

    ArrayList<Vacation_bean> vacation_chart = new ArrayList<Vacation_bean>();

    Vacation_bean vc = new Vacation_bean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation);


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

                /*if(직급이 사수나 팀장이 아니면)
                Intent i = new Intent(vacation.this, ) 그냥 vacation.java로 이동. vacation_chart 배열 가지고...
                직급이 사수나 팀장이면 check_vacation.java로 이동.
                 */
                }
            }
        });

    }
}
