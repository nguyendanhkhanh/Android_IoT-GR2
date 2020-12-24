package com.khanh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.khanh.iot_esptouch_demo.DangKyActivity;
import com.khanh.iot_esptouch_demo.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edittendangnhap,editpass;
    private Button btnlogin,btndangky;
    private FirebaseAuth firebaseAuth;
    private TextView txtquenpass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InWidget();
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edittendangnhap.getText().toString().trim();
                String pass= editpass.getText().toString().trim();
                try{
                    firebaseAuth.signInWithEmailAndPassword(taikhoan,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }else{
                                Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch (Exception e){

                }
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, DangKyActivity.class));finish();
            }
        });
        txtquenpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,QuenMatKhauActivity.class));
            }
        });

    }

    private void InWidget() {
        edittendangnhap=findViewById(R.id.edittendangnhap);
        editpass=findViewById(R.id.editmatkhau);
        btndangky=findViewById(R.id.btndangky);
        btnlogin=findViewById(R.id.btndangnhap);
        txtquenpass=findViewById(R.id.txtquenmatkhau);
    }
}
