package com.example.pbl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class vacation extends AppCompatActivity {

    private EditText edt_reason;
    private Spinner start_year, start_month, start_date, end_year, end_month, end_date, spn_reason;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation);

        final Vacation_bean vc = new Vacation_bean();

        start_year = findViewById(R.id.start_year);
        start_month = findViewById(R.id.start_month);
        start_date = findViewById(R.id.start_date);

        end_year = findViewById(R.id.end_year);
        end_month = findViewById(R.id.end_month);
        end_date = findViewById(R.id.end_date);

        spn_reason = findViewById(R.id.spn_reason);

        edt_reason = findViewById(R.id.edt_reason);

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //버튼 누르면 클래스에 정보 담아서 전송
                String start_day = start_year.getSelectedItem().toString() + "년 " + start_month.getSelectedItem().toString() + "월 " + start_date.getSelectedItem().toString() + "일";
                vc.setStart_date(start_day);

                String end_day = end_year.getSelectedItem().toString() + "년 " + end_month.getSelectedItem().toString() + "월 " + end_date.getSelectedItem().toString() + "일";
                vc.setEnd_date(end_day);

                String reason = spn_reason.getSelectedItem().toString() + "\n" + "추가사유 : " + edt_reason.toString();
                vc.setReason(reason);

            }
        });

    }
}
