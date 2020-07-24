package com.example.pbl_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class FragmentFirst extends Fragment {

    private FragmentFirst.GridAdapter gridAdapter; // 그리드뷰 어댑터
    private ArrayList<String> dayList;     // 요일 저장 할 리스트
    private GridView gridView;  // 그리드뷰
    private Calendar mCal; // 캘린더변수

    private TextView txtTitle;
    private Button btnLastMonth, btnNextMonth;

    public FragmentFirst(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_first, container, false);

        txtTitle = (TextView)layout.findViewById(R.id.txtCalTitle);
        gridView = (GridView)layout.findViewById(R.id.gridview1);
        btnLastMonth = layout.findViewById(R.id.btnLastMonth);
        btnNextMonth = layout.findViewById(R.id.btnNextMonth);

        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        txtTitle.setText(curYearFormat.format(date) + "년 " + curMonthFormat.format(date) + "월");

        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        mCal = Calendar.getInstance();


        // 이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }

        setCalendarDate(mCal.get(Calendar.MONTH) + 1);
        gridAdapter = new FragmentFirst.GridAdapter(getActivity(),dayList);
        //gridAdapter = new FragmentFirst.GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

        btnLastMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCal = getLastMonth(mCal);

                dayList = new ArrayList<String>();
                dayList.add("일");
                dayList.add("월");
                dayList.add("화");
                dayList.add("수");
                dayList.add("목");
                dayList.add("금");
                dayList.add("토");
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {
                    dayList.add("");
                }

                setCalendarDate(mCal.get(Calendar.MONTH) + 1);
            }
        });

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCal = getNextMonth(mCal);

                dayList = new ArrayList<String>();
                dayList.add("일");
                dayList.add("월");
                dayList.add("화");
                dayList.add("수");
                dayList.add("목");
                dayList.add("금");
                dayList.add("토");
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {
                    dayList.add("");
                }

                setCalendarDate(mCal.get(Calendar.MONTH) + 1);
            }
        });

        return layout;

    } //onCreateView


    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int month) {

        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }
        gridAdapter = new FragmentFirst.GridAdapter(getActivity(),dayList);
        //gridAdapter = new FragmentFirst.GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);
    }


    // 그리드뷰 어댑터
    private class GridAdapter extends BaseAdapter {

        private final List<String> list;

        private final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            FragmentFirst.ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new FragmentFirst.ViewHolder();
                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (FragmentFirst.ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));

            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);

            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvItemGridView;
    }

    /**
     * 지난달의 Calendar 객체를 반환합니다.
     *
     * @param calendar
     * @return LastMonthCalendar
     */
    private Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        txtTitle.setText(mCal.get(Calendar.YEAR) + "년 "
                + (mCal.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }

    /**
     * 다음달의 Calendar 객체를 반환합니다.
     *
     * @param calendar
     * @return NextMonthCalendar
     */
    private Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        txtTitle.setText(mCal.get(Calendar.YEAR) + "년 "
                + (mCal.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }



} // FragmentFirst
