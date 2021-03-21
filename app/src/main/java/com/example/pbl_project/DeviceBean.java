package com.example.pbl_project;

public class DeviceBean {

    public DeviceBean(){}


    public String macAddress;
    public String deviceName;

    public String getMacAddress() {return macAddress;}
    public String getDeviceName() {return deviceName;}


    public void setMacAddress(String macAddress){
        this.macAddress = macAddress;
    }

    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }


}
