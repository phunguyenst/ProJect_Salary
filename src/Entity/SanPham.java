/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class SanPham {

    /**
     * @return the maSP
     */
    public String getMaSP() {
        return maSP;
    }

    /**
     * @param maSP the maSP to set
     */
    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    /**
     * @return the TenSP
     */
    public String getTenSP() {
        return TenSP;
    }

    /**
     * @param TenSP the TenSP to set
     */
    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    /**
     * @return the thuongHieu
     */
    public String getThuongHieu() {
        return thuongHieu;
    }

    /**
     * @param thuongHieu the thuongHieu to set
     */
    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    /**
     * @return the donViTinh
     */
    public String getDonViTinh() {
        return donViTinh;
    }

    /**
     * @param donViTinh the donViTinh to set
     */
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    /**
     * @return the donGia
     */
    public float getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(float donGia) {
        this.donGia = donGia;
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
    private String maSP;
    private String TenSP;
    private String thuongHieu;
    
    private float donGia;
    private int soLuong;
    private String donViTinh;

    public SanPham() {
    }

    public SanPham(String maSP) {
        this.maSP = maSP;
    }
    
    public SanPham(String maSP, String TenSP, String thuongHieu, float donGia, int soLuong, String donViTinh) {
        this.maSP = maSP;
        this.TenSP = TenSP;
        this.thuongHieu = thuongHieu;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SanPham other = (SanPham) obj;
        return Objects.equals(this.maSP, other.maSP);
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSP=" + maSP + ", TenSP=" + TenSP + ", thuongHieu=" + thuongHieu + ", donGia=" + donGia + ", soLuong=" + soLuong + ", donViTinh=" + donViTinh + '}';
    }
    
    
}
