package com.NguyenHongQuan.qlns.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.adapter.Nguyenhongquan_BophanAdapter;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_BophanDao;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_NhanvienDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Bophan;

import java.util.ArrayList;
import java.util.List;

public class Nguyenhongquan_ActivityBophan extends AppCompatActivity {
    private Nguyenhongquan_BophanDao bpDao;
    private ListView lvBoPhan;
    private Button btnThemBoPhan;
    private List<Nguyenhongquan_Bophan> boPhanList;
    private Nguyenhongquan_BophanAdapter boPhanAdapter;
    private Nguyenhongquan_NhanvienDao nvd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyenhongquan_bophan);
        anhxa();
        boPhanList = new ArrayList<Nguyenhongquan_Bophan>();
        bpDao = new Nguyenhongquan_BophanDao(this);
        nvd = new Nguyenhongquan_NhanvienDao(this);
        boPhanList= bpDao.DanhSachBoPhan();
        boPhanAdapter=new Nguyenhongquan_BophanAdapter(this,boPhanList);
        lvBoPhan.setAdapter(boPhanAdapter);
        registerForContextMenu(lvBoPhan);

        btnThemBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Nguyenhongquan_ActivityBophan.this, Nguyenhongquan_ActivityThembophan.class);
                startActivity(intent);
            }
        });
        lvBoPhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Nguyenhongquan_ActivityBophan.this, Nguyenhongquan_ActivityNhanvienbp.class);
                intent.putExtra("MaBoPhan",boPhanList.get(i).getMaBoPhan());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_lop,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position=info.position;
        Nguyenhongquan_Bophan boPhan=boPhanList.get(position);
        switch (item.getItemId()){
            case R.id.mnSuaBoPhan:{
                Intent intent=new Intent(Nguyenhongquan_ActivityBophan.this, Nguyenhongquan_ActivitySuabophan.class);
                intent.putExtra("DuLieu",boPhan);
                startActivity(intent);
                onResume();
                break;
            }
            case R.id.mnXoaBoPhan:{
                Dialog dialog = new Dialog(Nguyenhongquan_ActivityBophan.this);
                dialog.setContentView(R.layout.dialog_xoabophan);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nvd.XoaBoPhanNhanVien(boPhan.getMaBoPhan());
                        bpDao.XoaBoPHan(boPhan.getMaBoPhan());
                        boPhanAdapter.notifyDataSetChanged();
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
                boPhanAdapter.notifyDataSetChanged();
                onResume();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }



    private void anhxa() {
        btnThemBoPhan = findViewById(R.id.btnThemBoPhan);
        lvBoPhan = findViewById(R.id.lvBoPhan);
    }
    @Override
    protected void onResume() {
        super.onResume();
        boPhanList.clear();
        boPhanList.addAll(bpDao.DanhSachBoPhan());
        boPhanAdapter.notifyDataSetChanged();
    }
}