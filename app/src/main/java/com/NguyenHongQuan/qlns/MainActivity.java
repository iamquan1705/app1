package com.NguyenHongQuan.qlns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.NguyenHongQuan.qlns.activity.Nguyenhongquan_ActivityBophan;
import com.NguyenHongQuan.qlns.activity.Nguyenhongquan_ActivityNhanvienmain;

public class MainActivity extends AppCompatActivity {
    private Button btnBoPhan,btnNhanVien,btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBoPhan = findViewById(R.id.btnBoPhan);
        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnDangXuat = findViewById(R.id.btnDangXuat);

        btnBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Nguyenhongquan_ActivityBophan.class);
                startActivity(intent);

            }
        });

        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Nguyenhongquan_ActivityNhanvienmain.class);
                startActivity(intent);
            }
        });
        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}