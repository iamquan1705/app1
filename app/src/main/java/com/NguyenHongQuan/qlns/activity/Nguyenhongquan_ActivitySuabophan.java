package com.NguyenHongQuan.qlns.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_BophanDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Bophan;

public class Nguyenhongquan_ActivitySuabophan extends AppCompatActivity {
    private EditText edtTenBoPhanSua,edtGhiChuSua,edtMaBoPhanSua;
    private Button btnSuaBoPhan,btnThoatSuaBoPhan;
    private Nguyenhongquan_BophanDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyenhongquan_suabophan);
        anhxa();
        dao = new Nguyenhongquan_BophanDao(this);
        Intent intent= getIntent();
        final Nguyenhongquan_Bophan bp=(Nguyenhongquan_Bophan) intent.getSerializableExtra("DuLieu");
        edtMaBoPhanSua.setText(String.valueOf(bp.getMaBoPhan()) );
        edtTenBoPhanSua.setText(bp.getTenBoPhan());
        edtGhiChuSua.setText(bp.getGhiChu());
        btnSuaBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nguyenhongquan_Bophan bp = new Nguyenhongquan_Bophan(Integer.parseInt(edtMaBoPhanSua.getText().toString()),edtTenBoPhanSua.getText().toString(),edtGhiChuSua.getText().toString());
                dao.SuaBoPhan(bp);
                finish();
            }
        });
        btnThoatSuaBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void anhxa() {
        edtTenBoPhanSua = findViewById(R.id.edtTenBoPhanSua);
        edtGhiChuSua = findViewById(R.id.edtGhiChuSua);
        edtMaBoPhanSua = findViewById(R.id.edtMaBoPhanSua);
        btnSuaBoPhan = findViewById(R.id.btnSuaBoPhan);
        btnThoatSuaBoPhan = findViewById(R.id.btnThoatSuaBoPhan);
    }
}