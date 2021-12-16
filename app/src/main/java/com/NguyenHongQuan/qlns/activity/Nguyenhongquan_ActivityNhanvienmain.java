package com.NguyenHongQuan.qlns.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.adapter.Nguyenhongquan_NhanvienAdapter;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_NhanvienDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Nhanvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Nguyenhongquan_ActivityNhanvienmain extends AppCompatActivity {
    private SearchView svNhanVienMain;
    private ListView lvNhanVienMain;
    private FloatingActionButton btnThemNhanVienMain;
    private List<Nguyenhongquan_Nhanvien> nvList;
    private Nguyenhongquan_NhanvienAdapter nhanVienAdapter;
    private Nguyenhongquan_NhanvienDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyenhongquan_nhanvienmain);
        anhxa();

        nvList = new ArrayList<>();
        dao = new Nguyenhongquan_NhanvienDao(this);
        nvList = dao.DanhSachSinhVienMain();
        nhanVienAdapter = new Nguyenhongquan_NhanvienAdapter(this,nvList);
        lvNhanVienMain.setAdapter(nhanVienAdapter);

        btnThemNhanVienMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisintent = new Intent(Nguyenhongquan_ActivityNhanvienmain.this, Nguyenhongquan_ActivityThemnhanvien.class);
                thisintent.putExtra("maBoPhan",-1);
                startActivity(thisintent);
            }
        });
        lvNhanVienMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nguyenhongquan_Nhanvien nv = nvList.get(i);
                Intent in = new Intent(Nguyenhongquan_ActivityNhanvienmain.this, Nguyenhongquan_ActivityThongtinnv.class);
                in.putExtra("maNhanVien", nv.getMaNhanVien());
                startActivity(in);
            }
        });
        lvNhanVienMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(Nguyenhongquan_ActivityNhanvienmain.this);
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