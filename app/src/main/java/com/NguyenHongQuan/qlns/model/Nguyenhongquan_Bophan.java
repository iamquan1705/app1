package com.NguyenHongQuan.qlns.model;

import java.io.Serializable;

public class Nguyenhongquan_Bophan implements Serializable {
    private int maBoPhan;
    private String tenBoPhan;
    private String ghiChu;

    public Nguyenhongquan_Bophan(int maBoPhan, String tenBoPhan, String ghiChu) {
        this.maBoPhan = maBoPhan;
        this.tenBoPhan = tenBoPhan;
        this.ghiChu = ghiChu;
    }

    public Nguyenhongquan_Bophan(String tenBoPhan, String ghiChu) {
        this.tenBoPhan = tenBoPhan;
        this.ghiChu = ghiChu;
    }

    public Nguyenhongquan_Bophan() {
    }

    public int getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    public String getTenBoPhan() {
        return tenBoPhan;
    }

    public void setTenBoPhan(String tenBoPhan) {
        this.tenBoPhan = tenBoPhan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "BoPhan{" +
                "maBoPhan=" + maBoPhan +
                ", tenBoPhan='" + tenBoPhan + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}
