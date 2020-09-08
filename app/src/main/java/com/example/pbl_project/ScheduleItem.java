package com.example.pbl_project;

public class ScheduleItem {
    public String Schedule;
    public String Date;
    public String Month;

    public ScheduleItem(String Date){
         this.Date = Date;
    }


    public ScheduleItem(String Month, String Date, String Schedule){
        this.Month = Month;
        this.Date = Date;
        this.Schedule = Schedule;
    }

    public void setMonth(String Month){this.Month = Month;}

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

    public String getMonth() {return Month;}




}
