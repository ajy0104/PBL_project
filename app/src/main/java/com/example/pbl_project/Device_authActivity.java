package com.example.pbl_project;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Device_authActivity extends AppCompatActivity {

    private DeviceBean deviceBean;
    private DevAdapter devAdapter;
    ArrayList<String> Dlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_list);



        ListView listView = (ListView)findViewById(R.id.dev_list);
        devAdapter = new DevAdapter(getApplicationContext(), Dlist);
        listView.setAdapter(devAdapter);
        Dlist.add(getMACAddress());






    }


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
