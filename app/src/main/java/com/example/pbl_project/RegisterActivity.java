package com.example.pbl_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity extends AppCompatActivity {

    EditText editName, editBirth, editNumber, editPassword, editPasswordConfirm;
    Button btnVerify, btnRegister;
    TextView txtVerify;
    Spinner spnPosition, spnDepartment;

//    private DatabaseReference mReference;
    private DatabaseReference mReference;
    private boolean verify = false;
    MemberBean mb = new MemberBean();


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
        //mReference = FirebaseDatabase.getInstance().getReference();
        mReference = FirebaseDatabase.getInstance().getReference().child("Employee");

        // 인증하기 버튼 눌렀을 때
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(editName.getText().toString()) || TextUtils.isEmpty(editBirth.getText().toString()) || TextUtils.isEmpty(editNumber.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "모든 칸을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Iterator<DataSnapshot> child = snapshot.getChildren().iterator();
                        //while (child.hasNext()){

                        if(snapshot.child(editNumber.getText().toString()).hasChild("password")) {
                            Toast.makeText(RegisterActivity.this, "이미 등록된 회원입니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mb.setUserName(snapshot.child(editNumber.getText().toString()).child("name").getValue().toString());
                        mb.setEmployeeNumber(snapshot.child(editNumber.getText().toString()).child("employeeNumber").getValue().toString());
                        mb.setBirth(snapshot.child(editNumber.getText().toString()).child("birth").getValue().toString());
                        mb.setPosition(snapshot.child(editNumber.getText().toString()).child("position").getValue().toString());
                        mb.setDepartment(snapshot.child(editNumber.getText().toString()).child("department").getValue().toString());

                        if(mb.getUserName().equals(editName.getText().toString()) && mb.getEmployeeNumber().equals(editNumber.getText().toString())&& mb.getBirth().equals(editBirth.getText().toString())&& mb.getPosition().equals(spnPosition.getSelectedItem().toString()) && mb.getDepartment().equals(spnDepartment.getSelectedItem().toString())){
                            Toast.makeText(RegisterActivity.this, "인증 성공.", Toast.LENGTH_SHORT).show();
                            txtVerify.setText("인증 성공");
                            txtVerify.setTextColor(Color.RED);
                            verify = true;
                        } else {
                            txtVerify.setText("인증 실패"); verify=false; txtVerify.setTextColor(Color.RED); return;
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

                if(verify == false){
                    Toast.makeText(RegisterActivity.this, "인증이 되지 않았습니다. 사원 인증부터 받으세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(editPassword.getText().toString()) || TextUtils.isEmpty(editPasswordConfirm.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "패스워드와 패스워드확인을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editPassword.getText().toString().length() < 7){
                    Toast.makeText(RegisterActivity.this, "패스워드는 7자 이상이어야합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editPassword.getText().toString().equals(editPasswordConfirm.getText().toString())){
                    //mReference.child("Employee").child(editNumber.getText().toString()).push().setValue(mb.getPassword());
                    mb.setPassword(editPassword.getText().toString());
                    mReference.child(mb.getEmployeeNumber()).child("password").setValue(mb.getPassword());
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
