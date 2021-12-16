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
import android.widget.TextView;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.adapter.Nguyenhongquan_NhanvienAdapter;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_BophanDao;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_NhanvienDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Nhanvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Nguyenhongquan_ActivityNhanvienbp extends AppCompatActivity {
    private FloatingActionButton btnThemNhanVienBP;
    private SearchView svNhanVienBP;
    private ListView lvNhanVienBP;
    private TextView tvTieuDeNhanVienBP,tvTongSoBP;
    private Nguyenhongquan_NhanvienDao dao;
    private List<Nguyenhongquan_Nhanvien> nvList;
    private Nguyenhongquan_NhanvienAdapter nhanVienAdapter;
    private String tenBoPhan;
    private Nguyenhongquan_BophanDao bpdao;
    private int mabp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyenhongquan_nhanvienbp);
        anhxa();
        bpdao = new Nguyenhongquan_BophanDao(this);
        nvList = new ArrayList<>();
        Intent intent = getIntent();
        mabp = intent.getIntExtra("MaBoPhan", 0);
        tenBoPhan = bpdao.getTenBoPhan(mabp);
        tvTieuDeNhanVienBP.setText("Danh sách nhân viên bộ phận " + tenBoPhan);
        dao = new Nguyenhongquan_NhanvienDao(this);
        nvList = dao.DanhSachNhanVien(mabp);
        tvTongSoBP.setText("Tổng số : "+nvList.size() + " nhân viên");
        nhanVienAdapter = new Nguyenhongquan_NhanvienAdapter(this, nvList);
        lvNhanVienBP.setAdapter(nhanVienAdapter);
        registerForContextMenu(lvNhanVienBP);
        btnThemNhanVienBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisintent = new Intent(Nguyenhongquan_ActivityNhanvienbp.this, Nguyenhongquan_ActivityThemnhanvien.class);
                thisintent.putExtra("maBoPhan", mabp);
                startActivity(thisintent);
            }
        });

        lvNhanVienBP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nguyenhongquan_Nhanvien nv = nvList.get(i);
                Intent inte = new Intent(Nguyenhongquan_ActivityNhanvienbp.this, Nguyenhongquan_ActivityThongtinnv.class);
                inte.putExtra("maNhanVien", nv.getMaNhanVien());
                startActivity(inte);
            }
        });
        lvNhanVienBP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(Nguyenhongquan_ActivityNhanvienbp.this);
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
        svNhanVienBP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        nvList.addAll(dao.DanhSachNhanVien(mabp));
        nhanVienAdapter.notifyDataSetChanged();
    }
    private void anhxa() {
        btnThemNhanVienBP = findViewById(R.id.btnThemNhanVienBP);
        svNhanVienBP = findViewById(R.id.svNhanVienBP);
        lvNhanVienBP = findViewById(R.id.lvNhanVienBP);
        tvTieuDeNhanVienBP = findViewById(R.id.tvTieuDeNhanVienBP);
        tvTongSoBP = findViewById(R.id.tvTongSoBP);
    }

}