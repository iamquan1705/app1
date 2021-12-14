package com.iamquan.qlnhansu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iamquan.qlnhansu.activity.BophanActivity;
import com.iamquan.qlnhansu.activity.NhanvienmainActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnBoPhan,btnNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBoPhan = findViewById(R.id.btnBoPhan);
        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BophanActivity.class);
                startActivity(intent);

            }
        });

        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NhanvienmainActivity.class);
                startActivity(intent);
            }
        });
    }
}