package com.iamquan.qlnhansu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="QLNHANSU.db";
    public static final String TABLE_NAME_BOPHAN="TB_BOPHAN";
    public static final String TABLE_NAME_NHANVIEN="TB_NHANVIEN";
    private static int version=4;
    private Context context;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_BOPHAN+
                "(maBoPhan  integer primary key AUTOINCREMENT, "+
                "tenBoPhan  text, "+
                "ghiChu text)";
        String sql1="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_NHANVIEN+
                "(maNhanVien text primary key, "+
                "tenNhanVien text, "+
                "ngaySinh  text," +
                "sdt text," +
                "chucVu text," +
                "anh blob," +
                "maBoPhan integer) ";

        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_BOPHAN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_NHANVIEN);
        onCreate(sqLiteDatabase);
    }
}