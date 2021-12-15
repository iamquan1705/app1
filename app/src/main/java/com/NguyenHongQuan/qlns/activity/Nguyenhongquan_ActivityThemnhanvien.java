package com.NguyenHongQuan.qlns.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_BophanDao;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_NhanvienDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Nhanvien;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Nguyenhongquan_ActivityThemnhanvien extends AppCompatActivity {

    private EditText edtMaNhanVienAdd,edtTenNhanVienAdd,edtNgaySinhNhanVienAdd,edtSDTNhanVienAdd,edtChucVuNhanVienAdd;
    private Button btnCameraAdd,btnLibraryAdd,btnAddNhanVien,btnThoatAddNV;
    private CircleImageView imgAnhNhanVienAdd;
    private int Resquet_code_camera=123;
    private int Resquet_code_library=456;
    private Nguyenhongquan_NhanvienDao nhanVienDao;
    private Spinner spnTenBoPhan;
    private Nguyenhongquan_BophanDao boPhanDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyenhongquan_themnhanvien);
        anhxa();
        nhanVienDao = new Nguyenhongquan_NhanvienDao(this);
        boPhanDao = new Nguyenhongquan_BophanDao(this);
        Intent intent = getIntent();
        int maBoPhan = intent.getIntExtra("maBoPhan",0);
        ArrayList listtenNV = new ArrayList();
        if (maBoPhan==-1) {

            listtenNV = (ArrayList) boPhanDao.getAllTenBoPhan();
        } else {
            listtenNV.clear();
            listtenNV.add(boPhanDao.getTenBoPhan(maBoPhan));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listtenNV);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spnTenBoPhan.setAdapter(adapter);


        btnCameraAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Resquet_code_camera);
            }
        });

        btnLibraryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Resquet_code_library);

            }
        });
        btnAddNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNhanVien =edtMaNhanVienAdd.getText().toString();
                String tenNV =edtTenNhanVienAdd.getText().toString();
                String ngaySinh =edtNgaySinhNhanVienAdd.getText().toString();
                String soDienThoai =edtSDTNhanVienAdd.getText().toString();
                String chucVu =edtChucVuNhanVienAdd.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhNhanVienAdd.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bytearray);
                byte[] hinh = bytearray.toByteArray();
                String tl = spnTenBoPhan.getSelectedItem().toString();
                int mabp = boPhanDao.getMaBoPhan(tl);
                Nguyenhongquan_Nhanvien nv = new Nguyenhongquan_Nhanvien(maNhanVien,tenNV,ngaySinh,soDienThoai,chucVu,hinh,mabp);
                nhanVienDao.ThemNhanVien(nv);
                finish();
            }
        });

        btnThoatAddNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Resquet_code_camera && resultCode == RESULT_OK && data != null) {
            Bitmap bimap = (Bitmap) data.getExtras().get("data");
            imgAnhNhanVienAdd.setImageBitmap(bimap);
        }
        if (requestCode == Resquet_code_library && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhNhanVienAdd.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void anhxa() {
        edtMaNhanVienAdd = findViewById(R.id.edtMaNhanVienAdd);
        edtTenNhanVienAdd = findViewById(R.id.edtTenNhanVienAdd);
        edtNgaySinhNhanVienAdd = findViewById(R.id.edtNgaySinhNhanVienAdd);
        edtSDTNhanVienAdd = findViewById(R.id.edtSDTNhanVienAdd);
        edtChucVuNhanVienAdd = findViewById(R.id.edtChucVuNhanVienAdd);

        btnCameraAdd = findViewById(R.id.btnCameraAdd);
        btnLibraryAdd = findViewById(R.id.btnLibraryAdd);
        btnAddNhanVien = findViewById(R.id.btnAddNhanVien);
        btnThoatAddNV = findViewById(R.id.btnThoatAddNV);

        imgAnhNhanVienAdd = findViewById(R.id.imgAnhNhanVienAdd);

        spnTenBoPhan = findViewById(R.id.spnTenBoPhan);
    }
}