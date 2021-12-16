package com.NguyenHongQuan.qlns.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.NguyenHongQuan.qlns.MainActivity;
import com.NguyenHongQuan.qlns.R;

public class Nguyenhongquan_ActivityDangnhap extends AppCompatActivity {
    private EditText edtPass,edtUser;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyenhongquan__dangnhap);

        edtPass = findViewById(R.id.edtPass);
        edtUser = findViewById(R.id.edtUser);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edtUser.getText().toString().isEmpty()) && (edtUser.getText().toString().isEmpty())) {
                    Toast.makeText(Nguyenhongquan_ActivityDangnhap.this, "Bạn chưa nhập tên hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                } else if ((edtUser.getText().toString().equals("iamquan1705")) && (edtPass.getText().toString().equals("Quan170520"))) {
                    Intent inte = new Intent(Nguyenhongquan_ActivityDangnhap.this, MainActivity.class);
                    startActivity(inte);
                } else {
                    Toast.makeText(Nguyenhongquan_ActivityDangnhap.this, "Tên hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
    }