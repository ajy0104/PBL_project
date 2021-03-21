package com.example.pbl_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;

public class GridAdapter extends BaseAdapter {
   // private ArrayList<String> list;
    private ArrayList<ScheduleItem> list;
    private final LayoutInflater inflater;
    private Calendar mCal; //캘린더 변수


    public GridAdapter(Context context, ArrayList<ScheduleItem> list ) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position).getDate();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){

            convertView = inflater.inflate(R.layout.calendar_gridview, parent,false);
            holder = new ViewHolder();
            holder.txtGrid = (TextView)convertView.findViewById(R.id.txtGrid);
            holder.txtSchedule = (TextView)convertView.findViewById(R.id.txtSc);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.txtGrid.setText(""+getItem(position));

        holder.txtSchedule.setText(""+list.get(position).getSchedule());




        //해당 날짜 텍스트 컬러 변경
        mCal = Calendar.getInstance();

        //오늘 day가져옴
        Integer today =  mCal.get(Calendar.DAY_OF_MONTH);
        String sToday = String.valueOf(today);

        Integer Year = mCal.get(Calendar.YEAR);
        Integer Month = mCal.get(Calendar.MONTH)+1;
        String sMonth = String.valueOf(Month);


        if (sToday.equals(getItem(position))){// 오늘 day 텍스트 컬러 변경
            holder.txtGrid.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
        }
        return convertView;





    }

    private class ViewHolder{
        TextView txtGrid;
        TextView txtSchedule;


    }
}

