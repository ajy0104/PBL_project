package com.example.pbl_project;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import androidx.appcompat.app.AppCompatActivity;

public class Security_auth extends AppCompatActivity {

    public String getMACAddress(){
        String macAddress="";
        boolean wifiOff = false;

        WifiManager mng = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        if(!mng.isWifiEnabled()){
            mng.setWifiEnabled(true);
            wifiOff = true;
        }

        WifiInfo info =mng.getConnectionInfo();
        String mac = info.getMacAddress();

        if(wifiOff){
            mng.setWifiEnabled(false);
            wifiOff = false;
        }
        return macAddress;
    }



}
