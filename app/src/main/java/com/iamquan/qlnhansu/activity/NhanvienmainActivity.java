package com.iamquan.qlnhansu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.iamquan.qlnhansu.R;
import com.iamquan.qlnhansu.adapter.NhanVienAdapter;
import com.iamquan.qlnhansu.dao.NhanVienDao;
import com.iamquan.qlnhansu.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanvienmainActivity extends AppCompatActivity {
    private SearchView svNhanVienMain;
    private ListView lvNhanVienMain;
    private Button btnThemNhanVienMain;
    private List<NhanVien> nvList;
    private NhanVienAdapter nhanVienAdapter;
    private NhanVienDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvienmain);
        anhxa();

        nvList = new ArrayList<>();
        dao = new NhanVienDao(this);
        nvList = dao.DanhSachSinhVienMain();
        nhanVienAdapter = new NhanVienAdapter(this,nvList);
        lvNhanVienMain.setAdapter(nhanVienAdapter);

        btnThemNhanVienMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisintent = new Intent(NhanvienmainActivity.this,AddnhanvienActivity.class);
                thisintent.putExtra("maBoPhan",-1);
                startActivity(thisintent);
            }
        });
        lvNhanVienMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhanVien nv = nvList.get(i);
                Intent in = new Intent(NhanvienmainActivity.this, ThongtinnvActivity.class);
                in.putExtra("maNhanVien", nv.getMaNhanVien());
                startActivity(in);
            }
        });
        lvNhanVienMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(NhanvienmainActivity.this);
                dialog.setContentView(R.layout.dialog_xoanhanvien);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao.XoaNhanVien(nvList.get(i).getMaNhanVien());
                        nhanVienAdapter.notifyDataSetChanged();
                        onResume();
                        dialog.cancel();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                nhanVienAdapter.notifyDataSetChanged();
                onResume();
                return true;
            }
        });
        svNhanVienMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;

                if (text != null) {
                    nhanVienAdapter.filter(text);
                }

                return false;
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        nvList.clear();
        nvList.addAll(dao.DanhSachSinhVienMain());
        nhanVienAdapter.notifyDataSetChanged();
    }
    private void anhxa() {
        svNhanVienMain = findViewById(R.id.svNhanVienMain);
        lvNhanVienMain = findViewById(R.id.lvNhanVienMain);
        btnThemNhanVienMain = findViewById(R.id.btnThemNhanVienMain);
    }
}