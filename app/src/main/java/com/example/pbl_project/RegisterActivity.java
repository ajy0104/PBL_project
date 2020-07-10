package com.example.pbl_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        // firebase 정의
        mReference = FirebaseDatabase.getInstance().getReference();


    } // OnCreate()



    private void readDB() {
        mReference.child("Employee").child("Member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue(MemberBean.class) != null){

                } else {
                    Toast.makeText(RegisterActivity.this, "데이터 없음.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            // Getting Post failed, log a message
                Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });

    } // readDB()


}
