package com.example.pbl_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScheduleActivity extends AppCompatActivity {

        private TextView txtYM; //연, 월 텍스트 뷰
        private GridAdapter gridAdapter; //그리드 뷰 어댑터
        private GridAdapter ScheduleAdapter;
        private ArrayList<String> dayList; //일 저장할 리스트
        private ArrayList<String> ScList;
        private GridView gridView; //그리드 뷰
        private Calendar mCal; //캘린더 변수
        private int selected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        txtYM = (TextView)findViewById(R.id.txtYM);
        gridView = (GridView)findViewById(R.id.gridview);


        Button btnPrev = findViewById(R.id.btnPrev);
        Button btnNext = findViewById(R.id.btnNext);


        //오늘 날짜 세팅
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연, 월, 일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌리기
        txtYM.setText(curYearFormat.format(date)+"년 "+curMonthFormat.format(date)+"월");

        //gridview 요일 표시
        dayList = new ArrayList<String>();
        /*
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");
        */
        mCal = Calendar.getInstance(); //캘린더 객체 생성(시스템의 현재 날짜, 시간 정보)
        //이번달 1일 무슨요일인지 판단 mCal.set(Year, Month, Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)),Integer.parseInt(curMonthFormat.format(date))-1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK); //calendar가 가리키는 특정 날짜가 무슨요일인지 알 수 있음
        //1일 - 요일 매칭 시키기 위해 공백 ADD
        for(int i = 1; i<dayNum; i++){
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH)+1);
        gridAdapter = new GridAdapter(getApplicationContext(), dayList, ScList);
        gridView.setAdapter(gridAdapter); //초기 설정에만 사용



        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    dayList= new ArrayList<String>();
                    mCal.add(Calendar.MONTH,-1);
                    txtYM.setText(mCal.get(Calendar.YEAR) + "년 "
                            + (mCal.get(Calendar.MONTH) + 1)+"월");

                    //이번달 1일 무슨요일인지 판단
                    int dayNum = mCal.get(Calendar.DAY_OF_WEEK); //calendar가 가리키는 특정 날짜가 무슨요일인지 알 수 있음

                    //1일 - 요일 매칭 시키기 위해 공백 ADD
                    for (int i = 1; i < dayNum; i++) {
                        dayList.add("");
                    }

                    setCalendarDate(mCal.get(Calendar.MONTH) + 1);


            }//onClick()
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dayList= new ArrayList<String>();
                mCal.add(Calendar.MONTH,+1);
                txtYM.setText(mCal.get(Calendar.YEAR) + "년 "
                        + (mCal.get(Calendar.MONTH) + 1)+"월");

                int dayNum = mCal.get(Calendar.DAY_OF_WEEK); //calendar가 가리키는 특정 날짜가 무슨요일인지 알 수 있음

                //1일 - 요일 매칭 시키기 위해 공백 ADD
                for(int i = 1; i<dayNum; i++){
                    dayList.add("");
                }
                setCalendarDate(mCal.get(Calendar.MONTH)+1);


            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                selected = Integer.parseInt(dayList.get(position));
                Intent i = new Intent(ScheduleActivity.this, Add_schedule.class);
                i.putExtra("날짜", dayList.get(position));
                startActivity(i);

            }
        });



        Intent sc_intent = getIntent();
        Bundle intentExtras= sc_intent.getExtras();
        ScList = new ArrayList<String>();
        if(intentExtras != null) {

            String schedule  =  intentExtras.getString("일정");
            ScheduleItem SI = new ScheduleItem();
            SI.setSchedule(schedule);
            //ScList.set(selected,SI.getSchedule());
            gridAdapter = new GridAdapter(getApplicationContext(),dayList, ScList);
            gridAdapter.addItem(SI);
            gridView.setAdapter(gridAdapter);
        }



        gridAdapter = new GridAdapter(getApplicationContext(),dayList, ScList);
        gridView.setAdapter(gridAdapter); //초기 설정에만 사용


    }//onCreate()



    //해당월에 표시할 일 수 구하기
    private void setCalendarDate(int month){
        mCal.set(Calendar.MONTH, month - 1);
        for(int i =0; i<mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            dayList.add(""+(i+1));
        }

        /*adapter 내용 업데이트 부분 */
        gridAdapter = new GridAdapter(getApplicationContext(), dayList, ScList);
        gridView.setAdapter(gridAdapter); //초기 설정에만 사용

    }




}
