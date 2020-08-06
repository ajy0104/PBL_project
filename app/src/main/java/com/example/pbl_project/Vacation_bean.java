package com.example.pbl_project;

public class Vacation_bean {

    public Vacation_bean(){

    }

    private String start_date;
    private String end_date;
    private String reason;
    private String teamjang_num;
    private String sasu_num;

    //get
    public String getStart_date(){
        return start_date;
    }

    public String getEnd_date(){
        return end_date;
    }

    public String getReason(){
        return reason;
    }

    public String getTeamjang_num() {
        return teamjang_num;
    }

    public String getSasu_num() {
        return sasu_num;
    }

    //set
    public void setStart_date(String start_date){
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date){
        this.end_date = end_date;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public void setTeamjang_num(String teamjang_num){
        this.teamjang_num = teamjang_num;
    }

    public void setSasu_num(String sasu_num){
        this.sasu_num = sasu_num;
    }

}
