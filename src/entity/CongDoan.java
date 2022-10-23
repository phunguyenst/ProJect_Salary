/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class CongDoan {

    public SanPham getTenSP() {
        return tenSP;
    }

    /**
     * @return the tenSP
     */
    public void setTenSP(SanPham tenSP) {    
        this.tenSP = tenSP;
    }

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

    public SanPham getSoLuong() {
        return soLuong;
    }

    /**
     * @return the soLuong
     */
    public void setSoLuong(SanPham soLuong) {    
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

    private String maCD;
    private String tenCD;
    private SanPham maSP;
    private SanPham tenSP;
    private float donGiaCD;
    private SanPham soLuong;
    private int maRangBuoc;
   

    public CongDoan(String maCD, String tenCD, SanPham maSP, SanPham tenSP, float donGiaCD, SanPham soLuong, int maRangBuoc) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGiaCD = donGiaCD;
        this.soLuong = soLuong;
        this.maRangBuoc = maRangBuoc;
     
    }

    public CongDoan(String maCD, String tenCD, SanPham soLuong) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.soLuong = soLuong;
    }

    public CongDoan(SanPham soLuong) {
        this.soLuong = soLuong;
    }

    public CongDoan(String maCD, String tenCD) {
        this.maCD = maCD;
        this.tenCD = tenCD;
    }
    


    public CongDoan() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CongDoan other = (CongDoan) obj;
        return Objects.equals(this.maCD, other.maCD);
    }

    
   
    
}
