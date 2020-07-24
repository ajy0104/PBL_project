package com.example.pbl_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    private final List<String> list;
    private final LayoutInflater inflater;
    private Calendar mCal; //캘린더 변수


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
            holder.txtSchedule = (TextView)convertView.findViewById(R.id.txtSchedule);
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
            holder.txtGrid.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
        }
        return convertView;


        //holder.txtSchedule.setText();

    }

    private class ViewHolder{
        TextView txtGrid;
        TextView txtSchedule;


    }
}
