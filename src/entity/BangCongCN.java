/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import gui.PhanCongCN;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Tuan Kiet Admin
 */
public class BangCongCN {

    public BangCongCN(String maPhieu, PhanCongCN maPhanCong, CaLamViec maCa, CongNhan maCN, CongDoan maCĐ, int soSPChamCong, boolean diLam, boolean nghiPhep, boolean tangCa, Date ngayCham) {
        this.maPhieu = maPhieu;
        this.maPhanCong = maPhanCong;
        this.maCa = maCa;
        this.maCN = maCN;
        this.maCĐ = maCĐ;
        this.soSPChamCong = soSPChamCong;
        this.diLam = diLam;
        this.nghiPhep = nghiPhep;
        this.tangCa = tangCa;
        this.ngayCham = ngayCham;
    }

    public BangCongCN(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public BangCongCN() {
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public PhanCongCN getMaPhanCong() {
        return maPhanCong;
    }

    public void setMaPhanCong(PhanCongCN maPhanCong) {
        this.maPhanCong = maPhanCong;
    }

    public CaLamViec getMaCa() {
        return maCa;
    }

    public void setMaCa(CaLamViec maCa) {
        this.maCa = maCa;
    }

    public CongNhan getMaCN() {
        return maCN;
    }

    public void setMaCN(CongNhan maCN) {
        this.maCN = maCN;
    }

    public CongDoan getMaCĐ() {
        return maCĐ;
    }

    public void setMaCĐ(CongDoan maCĐ) {
        this.maCĐ = maCĐ;
    }

    public int getSoSPChamCong() {
        return soSPChamCong;
    }

    public void setSoSPChamCong(int soSPChamCong) {
        this.soSPChamCong = soSPChamCong;
    }

    public boolean isDiLam() {
        return diLam;
    }

    public void setDiLam(boolean diLam) {
        this.diLam = diLam;
    }

    public boolean isNghiPhep() {
        return nghiPhep;
    }

    public void setNghiPhep(boolean nghiPhep) {
        this.nghiPhep = nghiPhep;
    }

    public boolean isTangCa() {
        return tangCa;
    }

    public void setTangCa(boolean tangCa) {
        this.tangCa = tangCa;
    }

    public Date getNgayCham() {
        return ngayCham;
    }

    public void setNgayCham(Date ngayCham) {
        this.ngayCham = ngayCham;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.maPhieu);
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
        final BangCongCN other = (BangCongCN) obj;
        return Objects.equals(this.maPhieu, other.maPhieu);
    }

    @Override
    public String toString() {
        return "BangCongCN{" + "maPhieu=" + maPhieu + ", maPhanCong=" + maPhanCong + ", maCa=" + maCa + ", maCN=" + maCN + ", maC\u0110=" + maCĐ + ", soSPChamCong=" + soSPChamCong + ", diLam=" + diLam + ", nghiPhep=" + nghiPhep + ", tangCa=" + tangCa + ", ngayCham=" + ngayCham + '}';
    }



    String maPhieu;
    PhanCongCN maPhanCong;
    CaLamViec maCa;
    CongNhan maCN;
    CongDoan maCĐ;
    int soSPChamCong;
    boolean diLam;
    boolean nghiPhep;
    boolean tangCa;
    Date ngayCham;

}
