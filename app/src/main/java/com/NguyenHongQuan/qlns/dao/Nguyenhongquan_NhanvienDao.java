package com.NguyenHongQuan.qlns.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.NguyenHongQuan.qlns.database.Nguyenhongquan_Database;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Nhanvien;

import java.util.ArrayList;
import java.util.List;

public class Nguyenhongquan_NhanvienDao {
    private Nguyenhongquan_Database database;

    public Nguyenhongquan_NhanvienDao(Context context) {
        database = new Nguyenhongquan_Database(context);
    }

    public List<Nguyenhongquan_Nhanvien> DanhSachNhanVien(int maBP) {
        List<Nguyenhongquan_Nhanvien> NVLIST = new ArrayList<Nguyenhongquan_Nhanvien>();
        String sql = "SELECT * FROM '"+database.TABLE_NAME_NHANVIEN+"' where maBoPhan ='" + maBP + "'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maNhanVien = cursor.getString(0);
            String tenNhanVien = cursor.getString(1);
            String ngaySinh  = cursor.getString(2);
            String sdt  = cursor.getString(3);
            String chucVu   = cursor.getString(4);
            byte[] anh = cursor.getBlob(5);
            int maBoPhan  = cursor.getInt(6);
            Nguyenhongquan_Nhanvien sv = new Nguyenhongquan_Nhanvien(maNhanVien, tenNhanVien, ngaySinh, sdt,chucVu, anh, maBoPhan);
            NVLIST.add(sv);
            cursor.moveToNext();
        }
        return NVLIST;
    }

    public List<Nguyenhongquan_Nhanvien> DanhSachSinhVienMain() {
        List<Nguyenhongquan_Nhanvien> nvs = new ArrayList<Nguyenhongquan_Nhanvien>();
        String sql = "SELECT * FROM '"+database.TABLE_NAME_NHANVIEN+"'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maNhanVien = cursor.getString(0);
            String tenNhanVien = cursor.getString(1);
            String ngaySinh  = cursor.getString(2);
            String sdt  = cursor.getString(3);
            String chucVu   = cursor.getString(4);
            byte[] anh = cursor.getBlob(5);
            int maBoPhan  = cursor.getInt(6);
            Nguyenhongquan_Nhanvien sv = new Nguyenhongquan_Nhanvien(maNhanVien, tenNhanVien, ngaySinh, sdt,chucVu, anh, maBoPhan);
            nvs.add(sv);
            cursor.moveToNext();
        }
        return nvs;
    }

    public void ThemNhanVien(Nguyenhongquan_Nhanvien nv) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNhanVien", nv.getMaNhanVien());
        values.put("tenNhanVien", nv.getTenNhanVien());
        values.put("ngaySinh", nv.getNgaySinh());
        values.put("sdt", nv.getNgaySinh());
        values.put("chucVu", nv.getChucVu());
        values.put("anh", nv.getAnh());
        values.put("maBoPhan", nv.getMaBoPhan());
        db.insert("'"+database.TABLE_NAME_NHANVIEN+"'", null, values);

    }

    // Cập nhật HV
    public void SuaNhanVien(Nguyenhongquan_Nhanvien nv) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNhanVien", nv.getTenNhanVien());
        values.put("ngaySinh", nv.getNgaySinh());
        values.put("sdt", nv.getSdt());
        values.put("chucVu", nv.getChucVu());
        values.put("anh", nv.getAnh());
        values.put("maBoPhan", nv.getMaBoPhan());
        db.update("'"+database.TABLE_NAME_NHANVIEN+"'", values, "maNhanVien=?", new String[]{String.valueOf(nv.getMaNhanVien())});
        db.close();

    }

    public void XoaNhanVien(String id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String delete = "Delete from '"+database.TABLE_NAME_NHANVIEN+"' where maNhanVien='" + id + "'";
        db.execSQL(delete);
        db.close();
    }

    public void XoaBoPhanNhanVien(int mbp) {
        SQLiteDatabase db = database.getWritableDatabase();
        String delete = "Delete from '"+database.TABLE_NAME_NHANVIEN+"' where maBoPhan='" + mbp + "'";
        db.execSQL(delete);
        db.close();
    }

    public Nguyenhongquan_Nhanvien getNhanVien(String manv) {
        String sql = "SELECT * FROM '"+database.TABLE_NAME_NHANVIEN+"' where maNhanVien = '" + manv + "'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Nguyenhongquan_Nhanvien nv = null;
        if (!cursor.isAfterLast()) {
            String maNhanVien = cursor.getString(0);
            String tenNhanVien = cursor.getString(1);
            String ngaySinh  = cursor.getString(2);
            String sdt  = cursor.getString(3);
            String chucVu   = cursor.getString(4);
            byte[] anh = cursor.getBlob(5);
            int maBoPhan  = cursor.getInt(6);
            nv = new Nguyenhongquan_Nhanvien(maNhanVien, tenNhanVien, ngaySinh, sdt,chucVu, anh, maBoPhan);
            cursor.moveToNext();
        }
        return nv;
    }


}

