package com.iamquan.qlnhansu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iamquan.qlnhansu.R;
import com.iamquan.qlnhansu.dao.BoPhanDao;
import com.iamquan.qlnhansu.model.BoPhan;

public class EditbophanActivity extends AppCompatActivity {
    private EditText edtTenBoPhanSua,edtGhiChuSua,edtMaBoPhanSua;
    private Button btnSuaBoPhan,btnThoatSuaBoPhan;
    private BoPhanDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbophan);
        anhxa();
        dao = new BoPhanDao(this);
        Intent intent= getIntent();
        final BoPhan bp=(BoPhan) intent.getSerializableExtra("DuLieu");
        edtMaBoPhanSua.setText(String.valueOf(bp.getMaBoPhan()) );
        edtTenBoPhanSua.setText(bp.getTenBoPhan());
        edtGhiChuSua.setText(bp.getGhiChu());
        btnSuaBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoPhan bp = new BoPhan(Integer.parseInt(edtMaBoPhanSua.getText().toString()),edtTenBoPhanSua.getText().toString(),edtGhiChuSua.getText().toString());
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