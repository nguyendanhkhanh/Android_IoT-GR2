package com.khanh.iot_esptouch_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.khanh.LoginActivity;
import com.khanh.User;


public class DangKyActivity extends AppCompatActivity {
    private Button btndangnhap,btndangky;
    private EditText edittaikhoan,editpass,editpass1;
    private User user;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        InitWidget();
        Init();
    }

    private void Init() {
        firebaseAuth=FirebaseAuth.getInstance();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKy();
            }
        });

      //  user=new User(this);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKyActivity.this,
                        LoginActivity.class));
                finish();
            }
        });
    }

    private void DangKy() {
        String taikhoan = edittaikhoan.getText().toString().trim();
        String pass= editpass.getText().toString().trim();
        String pass1= editpass1.getText().toString().trim();
        try{
            if(pass.equals(pass1) && taikhoan.length()>0 && pass.length()>0){
                firebaseAuth.createUserWithEmailAndPassword(taikhoan,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(DangKyActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DangKyActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                    }
                });

            }else{
                Toast.makeText(this, "Không Để Trống", Toast.LENGTH_SHORT).show();
            }
            
        }catch (Exception e){
            Toast.makeText(this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void InitWidget() {
        editpass=findViewById(R.id.editmatkhau);
        editpass1=findViewById(R.id.editmatkhau1);
        edittaikhoan=findViewById(R.id.edittendangnhap);
        btndangnhap=findViewById(R.id.btndangnhap);
        btndangky=findViewById(R.id.btndangky);
    }
}
