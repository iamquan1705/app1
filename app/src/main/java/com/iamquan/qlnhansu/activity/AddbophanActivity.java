package com.iamquan.qlnhansu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iamquan.qlnhansu.R;
import com.iamquan.qlnhansu.dao.BoPhanDao;
import com.iamquan.qlnhansu.model.BoPhan;

public class AddbophanActivity extends AppCompatActivity {
    private EditText edtTenBoPhanThem,edtGhiChuThem;
    private Button btnThemBoPhan,btnThoatThemBoPhan;
    private BoPhanDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbophan);
        anhxa();
        dao = new BoPhanDao(this);
        btnThemBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoPhan bp = new BoPhan(edtTenBoPhanThem.getText().toString(),edtGhiChuThem.getText().toString());
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