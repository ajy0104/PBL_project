package com.example.pbl_project;

public class MemberBean {

    public MemberBean(){
        // Default constructor required for calls to DataSnapshot.getValue(MemberBean.class)
    }

    public String memberNum;
    public String password;
    public String userName;
    public String birth;
    public String position;
    public String department;

    public MemberBean(String memberNum, String password){
        this.memberNum = memberNum;
        this.password = password;
    }

    /* Getter */
    public String getMemberNum() {
        return memberNum;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getBirth() {
        return birth;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }


    /* Setter */
    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
