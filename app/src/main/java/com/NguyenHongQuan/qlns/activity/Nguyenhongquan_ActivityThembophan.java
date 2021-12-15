package com.NguyenHongQuan.qlns.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_BophanDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Bophan;

public class Nguyenhongquan_ActivityThembophan extends AppCompatActivity {
    private EditText edtTenBoPhanThem,edtGhiChuThem;
    private Button btnThemBoPhan,btnThoatThemBoPhan;
    private Nguyenhongquan_BophanDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyenhongquan_thembophan);
        anhxa();
        dao = new Nguyenhongquan_BophanDao(this);
        btnThemBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nguyenhongquan_Bophan bp = new Nguyenhongquan_Bophan(edtTenBoPhanThem.getText().toString(),edtGhiChuThem.getText().toString());
                dao.ThemBoPhan(bp);
                finish();
            }
        });
        btnThoatThemBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void anhxa() {
        edtTenBoPhanThem = findViewById(R.id.edtTenBoPhanThem);
        edtGhiChuThem = findViewById(R.id.edtGhiChuThem);
        btnThemBoPhan = findViewById(R.id.btnThemBoPhan);
        btnThoatThemBoPhan = findViewById(R.id.btnThoatThemBoPhan);
    }
}