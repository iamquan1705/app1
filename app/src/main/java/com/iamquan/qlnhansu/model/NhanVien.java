package com.iamquan.qlnhansu.model;

import java.io.Serializable;
import java.util.Arrays;

public class NhanVien implements Serializable {
    private String maNhanVien;
    private String tenNhanVien;
    private String ngaySinh;
    private String sdt;
    private String chucVu;
    private byte[] anh;
    private int maBoPhan;

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String ngaySinh, String sdt, String chucVu, byte[] anh, int maBoPhan) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.chucVu = chucVu;
        this.anh = anh;
        this.maBoPhan = maBoPhan;
    }

    public NhanVien(String tenNhanVien, String ngaySinh, String sdt, String chucVu, byte[] anh, int maBoPhan) {
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.chucVu = chucVu;
        this.anh = anh;
        this.maBoPhan = maBoPhan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", chucVu='" + chucVu + '\'' +
                ", anh=" + Arrays.toString(anh) +
                ", maBoPhan=" + maBoPhan +
                '}';
    }
}

