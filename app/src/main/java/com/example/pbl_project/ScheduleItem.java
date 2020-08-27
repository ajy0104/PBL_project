package com.example.pbl_project;

public class ScheduleItem {
    public String Schedule;
    public String Date;

    public ScheduleItem(String Date){
         this.Date = Date;
    }


    public ScheduleItem(String Date, String Schedule){
        this.Date = Date;
        this.Schedule = Schedule;
    }

    public void setDate(String Date){
        this.Date = Date;
    }

    public void setSchedule(String Schedule){
        this.Schedule = Schedule;
    }

    public String getSchedule(){
        return Schedule;
    }

    public String getDate(){
        return Date;
    }



}
