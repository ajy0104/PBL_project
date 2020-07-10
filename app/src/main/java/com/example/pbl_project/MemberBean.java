package com.example.pbl_project;

public class MemberBean {

    public MemberBean(){
        // Default constructor required for calls to DataSnapshot.getValue(MemberBean.class)
    }

    public String memberNum;
    public String password;

    public MemberBean(String memberNum, String password){
        this.memberNum = memberNum;
        this.password = password;
    }

    public String getUserName(){
        return memberNum;
    }

    public void setUserName(){
        this.memberNum = memberNum;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(){
        this.password = password;
    }


}
