package com.example.pbl_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

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
        private ArrayList<String> dayList; //일 저장할 리스트
        private GridView gridView; //그리드 뷰
        private Calendar mCal; //캘린더 변수

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        txtYM = (TextView)findViewById(R.id.txtYM);
        gridView = (GridView)findViewById(R.id.gridview);

        //오늘 날짜 세팅
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연, 월, 일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌리기
        txtYM.setText(curYearFormat.format(date)+"."+curMonthFormat.format(date));

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
        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

    }//onCreate()

    //해당월에 표시할 일 수 구하기
    private void setCalendarDate(int month){
        mCal.set(Calendar.MONTH, month - 1);
        for(int i =0; i<mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            dayList.add(""+(i+1));
        }

    }
    public class GridAdapter extends BaseAdapter {
        private final List<String> list;
        private final LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                convertView = inflater.inflate(R.layout.calendar_gridview, parent,false);
                holder = new ViewHolder();
                holder.txtGrid = (TextView)convertView.findViewById(R.id.txtGrid);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            holder.txtGrid.setText(""+getItem(position));

            //해당 날짜 텍스트 컬러 변경
            mCal = Calendar.getInstance();
            //오늘 day가져옴
            Integer today =  mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))){// 오늘 day 텍스트 컬러 변경
                holder.txtGrid.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            return convertView;
        }

    }
    private class ViewHolder{
        TextView txtGrid;
    }
}
