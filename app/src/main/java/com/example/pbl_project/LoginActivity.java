package com.example.pbl_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText mEdtId;
    private  EditText mEdtPw;

    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mBtnLogin = findViewById(R.id.btnLogin);
        Button mBtnRegister = findViewById(R.id.btnSign);

        mEdtId=findViewById(R.id.edtId);
        mEdtPw=findViewById(R.id.edtPw);


        mReference = FirebaseDatabase.getInstance().getReference();

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//클릭이벤트 등록

                if((mEdtId.getText().toString().length() == 0) ||( mEdtPw.getText().toString().length() == 0)) {
                    Toast.makeText(LoginActivity.this,"사원번호 또는 패스워드를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i = new Intent(LoginActivity.this, RegisterActivity.class); //회원가입 액티비티로 이동
              startActivity(i);

            }
        });
    }//end onCreate()

    private void login(){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if( dataSnapshot.getValue(MemberBean.class).getUserName().equals( mEdtId.getText().toString() ) &&
                        dataSnapshot.getValue(MemberBean.class).getPassword().equals( mEdtPw.getText().toString() )  ) {
                    Toast.makeText(LoginActivity.this,
                            dataSnapshot.getValue(MemberBean.class).getUserName() + "님 환영 합니다.", Toast.LENGTH_SHORT).show();

                } else{
                    Toast.makeText(LoginActivity.this,
                            "사원번호, 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }//login()






}

