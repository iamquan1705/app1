package com.iamquan.qlnhansu.model;

import java.io.Serializable;

public class BoPhan implements Serializable {
    private int maBoPhan;
    private String tenBoPhan;
    private String ghiChu;

    public BoPhan(int maBoPhan, String tenBoPhan, String ghiChu) {
        this.maBoPhan = maBoPhan;
        this.tenBoPhan = tenBoPhan;
        this.ghiChu = ghiChu;
    }

    public BoPhan(String tenBoPhan, String ghiChu) {
        this.tenBoPhan = tenBoPhan;
        this.ghiChu = ghiChu;
    }

    public BoPhan() {
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
