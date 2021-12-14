package com.iamquan.qlnhansu.activity;

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

import com.iamquan.qlnhansu.R;
import com.iamquan.qlnhansu.adapter.BoPhanAdapter;
import com.iamquan.qlnhansu.dao.BoPhanDao;
import com.iamquan.qlnhansu.dao.NhanVienDao;
import com.iamquan.qlnhansu.model.BoPhan;

import java.util.ArrayList;
import java.util.List;

public class BophanActivity extends AppCompatActivity {
    private BoPhanDao bpDao;
    private ListView lvBoPhan;
    private Button btnThemBoPhan;
    private List<BoPhan> boPhanList;
    private BoPhanAdapter boPhanAdapter;
    private NhanVienDao nvd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bophan);
        anhxa();
        boPhanList = new ArrayList<BoPhan>();
        bpDao = new BoPhanDao(this);
        nvd = new NhanVienDao(this);
        boPhanList= bpDao.DanhSachBoPhan();
        boPhanAdapter=new BoPhanAdapter(this,boPhanList);
        lvBoPhan.setAdapter(boPhanAdapter);
        registerForContextMenu(lvBoPhan);

        btnThemBoPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BophanActivity.this,AddbophanActivity.class);
                startActivity(intent);
            }
        });
        lvBoPhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BophanActivity.this, NhanvienbpActivity.class);
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
        BoPhan boPhan=boPhanList.get(position);
        switch (item.getItemId()){
            case R.id.mnSuaBoPhan:{
                Intent intent=new Intent(BophanActivity.this, EditbophanActivity.class);
                intent.putExtra("DuLieu",boPhan);
                startActivity(intent);
                onResume();
                break;
            }
            case R.id.mnXoaBoPhan:{
                Dialog dialog = new Dialog(BophanActivity.this);
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