/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class CongDoan {

    /**
     * @return the maCD
     */
    public String getMaCD() {
        return maCD;
    }

    /**
     * @param maCD the maCD to set
     */
    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    /**
     * @return the tenCD
     */
    public String getTenCD() {
        return tenCD;
    }

    /**
     * @param tenCD the tenCD to set
     */
    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    /**
     * @return the maSP
     */
    public SanPham getMaSP() {
        return maSP;
    }

    /**
     * @param maSP the maSP to set
     */
    public void setMaSP(SanPham maSP) {
        this.maSP = maSP;
    }

    /**
     * @return the donGiaCD
     */
    public float getDonGiaCD() {
        return donGiaCD;
    }

    /**
     * @param donGiaCD the donGiaCD to set
     */
    public void setDonGiaCD(float donGiaCD) {
        this.donGiaCD = donGiaCD;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the maRangBuoc
     */
    public int getMaRangBuoc() {
        return maRangBuoc;
    }

    /**
     * @param maRangBuoc the maRangBuoc to set
     */
    public void setMaRangBuoc(int maRangBuoc) {
        this.maRangBuoc = maRangBuoc;
    }

    /**
     * @return the trangThai
     */
    public boolean isTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    private String maCD;
    private String tenCD;
    private SanPham maSP;
    private float donGiaCD;
    private int soLuong;
    private int maRangBuoc;
    private boolean trangThai;

    public CongDoan(String maCD, String tenCD, SanPham maSP, float donGiaCD, int soLuong, int maRangBuoc, boolean trangThai) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.maSP = maSP;
        this.donGiaCD = donGiaCD;
        this.soLuong = soLuong;
        this.maRangBuoc = maRangBuoc;
        this.trangThai = trangThai;
    }

    public CongDoan() {
    }

    @Override
    public String toString() {
        return "CongDoan{" + "maCD=" + maCD + ", tenCD=" + tenCD + ", maSP=" + maSP + ", donGiaCD=" + donGiaCD + ", soLuong=" + soLuong + ", maRangBuoc=" + maRangBuoc + ", trangThai=" + trangThai + '}';
    }
    
    
}
