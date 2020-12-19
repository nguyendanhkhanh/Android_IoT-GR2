package com.khanh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.khanh.iot_esptouch_demo.R;

public class QuenMatKhauActivity extends AppCompatActivity {
    private EditText editemail;
    private Toolbar toolbar;
    private Button btntimkiem;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);
        editemail=findViewById(R.id.editemail);
        toolbar=findViewById(R.id.toolbar);
        btntimkiem=findViewById(R.id.btnfind);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editemail.getText().toString().trim();
                if(email.length()>0){
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(QuenMatKhauActivity.this, "Vao email kiem tra", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(QuenMatKhauActivity.this, "Email khong ton tai", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
             
             
             
                }
            
            
            
            
            }
            
        });
        
    }
}
