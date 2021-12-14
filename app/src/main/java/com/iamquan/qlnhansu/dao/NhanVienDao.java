package com.iamquan.qlnhansu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iamquan.qlnhansu.database.Database;
import com.iamquan.qlnhansu.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDao {
    private Database database;

    public NhanVienDao(Context context) {
        database = new Database(context);
    }

    public List<NhanVien> DanhSachNhanVien(int maBP) {
        List<NhanVien> NVLIST = new ArrayList<NhanVien>();
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
            NhanVien sv = new NhanVien(maNhanVien, tenNhanVien, ngaySinh, sdt,chucVu, anh, maBoPhan);
            NVLIST.add(sv);
            cursor.moveToNext();
        }
        return NVLIST;
    }

    public List<NhanVien> DanhSachSinhVienMain() {
        List<NhanVien> nvs = new ArrayList<NhanVien>();
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
            NhanVien sv = new NhanVien(maNhanVien, tenNhanVien, ngaySinh, sdt,chucVu, anh, maBoPhan);
            nvs.add(sv);
            cursor.moveToNext();
        }
        return nvs;
    }

    public void ThemNhanVien(NhanVien nv) {
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
    public void SuaNhanVien(NhanVien nv) {
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

    public NhanVien getNhanVien(String manv) {
        String sql = "SELECT * FROM '"+database.TABLE_NAME_NHANVIEN+"' where maNhanVien = '" + manv + "'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        NhanVien nv = null;
        if (!cursor.isAfterLast()) {
            String maNhanVien = cursor.getString(0);
            String tenNhanVien = cursor.getString(1);
            String ngaySinh  = cursor.getString(2);
            String sdt  = cursor.getString(3);
            String chucVu   = cursor.getString(4);
            byte[] anh = cursor.getBlob(5);
            int maBoPhan  = cursor.getInt(6);
            nv = new NhanVien(maNhanVien, tenNhanVien, ngaySinh, sdt,chucVu, anh, maBoPhan);
            cursor.moveToNext();
        }
        return nv;
    }


}

