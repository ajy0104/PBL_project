package com.example.pbl_project;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText editName, editBirth, editNumber, editPassword, editPasswordConfirm;
    Button btnVerify, btnRegister;
    TextView txtVerify;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName); editBirth = findViewById(R.id.editBirth);
        editNumber = findViewById(R.id.editNumber); editPassword = findViewById(R.id.editPassword);
        editPasswordConfirm = findViewById(R.id.editPasswordConfirm);

        btnVerify = findViewById(R.id.btnVerify);
        btnRegister = findViewById(R.id.btnRegister);
        txtVerify = findViewById(R.id.txtVerify);


    }


}
