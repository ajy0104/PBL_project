package com.example.pbl_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Mac_Encrypt extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_encrypt);

        Intent intent = getIntent();
        String macAddress = intent.getExtras().getString("mac주소");

    }





}
