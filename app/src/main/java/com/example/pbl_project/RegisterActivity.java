package com.example.pbl_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {

    EditText editName, editBirth, editNumber, editPassword, editPasswordConfirm;
    Button btnVerify, btnRegister;
    TextView txtVerify;
    Spinner spnPosition, spnDepartment;

    private DatabaseReference mReference;
    private DatabaseReference mr;
    private ChildEventListener mChild; /////
    private boolean verify = false;
    private boolean exist = true;
    MemberBean mb = new MemberBean();
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //mReference = FirebaseDatabase.getInstance().getReference("Employee");

        editName = findViewById(R.id.editName); editBirth = findViewById(R.id.editBirth);
        editNumber = findViewById(R.id.editNumber); editPassword = findViewById(R.id.editPassword);
        editPasswordConfirm = findViewById(R.id.editPasswordConfirm);

        btnVerify = findViewById(R.id.btnVerify);
        btnRegister = findViewById(R.id.btnRegister);

        txtVerify = findViewById(R.id.txtVerify);

        spnPosition = findViewById(R.id.spnPosition);
        spnDepartment = findViewById(R.id.spnDepartment);

        // firebase 정의 및 데이터읽기
        mReference = FirebaseDatabase.getInstance().getReference();
        mr = FirebaseDatabase.getInstance().getReference().child("Employee");

        // 인증하기 버튼 눌렀을 때
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = editName.getText().toString();
                final String birth = editBirth.getText().toString();
                final String position = spnPosition.getSelectedItem().toString();
                final String department = spnDepartment.getSelectedItem().toString();
                final String number = editNumber.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(birth) || TextUtils.isEmpty(number)){
                    Toast.makeText(RegisterActivity.this, "모든 칸을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Iterator<DataSnapshot> child = snapshot.getChildren().iterator();
                        //while (child.hasNext()){
                        mb.setUserName(snapshot.child(number).child("name").getValue().toString());
                        mb.setMemberNum(snapshot.child(number).child("employeeNumber").getValue().toString());
                        mb.setBirth(snapshot.child(number).child("birth").getValue().toString());
                        mb.setPosition(snapshot.child(number).child("position").getValue().toString());
                        mb.setDepartment(snapshot.child(number).child("department").getValue().toString());

                        String mbName = mb.getUserName();
                        String mbBirth = mb.getBirth();
                        String mbPosition = mb.getPosition();
                        String mbDepartment = mb.getDepartment();
                        String mbNumber = mb.getMemberNum();
                        String editName = name;
                        String editBirth = birth;
                        String editPosition = position;
                        String editDepartment = department;
                        String editNum = number;

                        if(mb.getUserName().equals(name) && mb.getMemberNum().equals(number) && mb.getBirth().equals(birth) && mb.getPosition().equals(position) && mb.getDepartment().equals(department)){
                            Toast.makeText(RegisterActivity.this, "인증 성공.", Toast.LENGTH_SHORT).show();
                            txtVerify.setText("인증 성공");
                            verify = true;
                        } else {
                            txtVerify.setText("인증 실패"); verify=false; return;
                        }
                        
                        //}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            } // onClick
        }); // btnVerify.setOnClickListener

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editPassword.getText().toString();
                String passwordConfirm = editPasswordConfirm.getText().toString();

                if(TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConfirm)){
                    Toast.makeText(RegisterActivity.this, "패스워드와 패스워드확인을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 7){
                    Toast.makeText(RegisterActivity.this, "패스워드는 7자 이상이어야합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.equals(passwordConfirm)){
                    //mReference.child("Employee").child(editNumber.getText().toString()).push().setValue(mb.getPassword());
                    Toast.makeText(RegisterActivity.this, "회원가입 성공.", Toast.LENGTH_SHORT).show();
                    // 로그인 액티비티로 이동
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                } else{
                    Toast.makeText(RegisterActivity.this, "패스워드 불일치.", Toast.LENGTH_SHORT).show();
                }


            }
        }); // btnRegister.setOnClickListener

    } // OnCreate()

}
