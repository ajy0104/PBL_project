package com.example.pbl_project;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Device_authActivity extends AppCompatActivity {

    private DeviceBean deviceBean;
    private String mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_list);
        ListView listView = (ListView)findViewById(R.id.dev_list);

        ArrayList<String> Dlist = new ArrayList<>();

        mac = getMACAddress();

        Dlist.add(mac);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, Dlist);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Device_authActivity.this, Mac_Encrypt.class);
                i.putExtra("mac주소", mac);
                startActivity(i);
            }
        });



    }


    public String getMACAddress(){
        String macAddress="";
        boolean wifiOff = false;

        WifiManager mng = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!mng.isWifiEnabled()){
            mng.setWifiEnabled(true);
            wifiOff = true;
        }

        WifiInfo info =mng.getConnectionInfo();
        macAddress = info.getMacAddress();

        if(wifiOff){
            mng.setWifiEnabled(false);
            wifiOff = false;
        }
        return macAddress;
    }



}
