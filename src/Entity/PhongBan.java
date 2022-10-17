/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class PhongBan {
    String maPB;
    String tenPB;

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public PhongBan() {
    }

    public PhongBan(String maPB, String tenPB) {
        this.maPB = maPB;
        this.tenPB = tenPB;
    }

    public PhongBan(String maPB) {
        this.maPB = maPB;
    }

    @Override
    public String toString() {
        return "PhongBan{" + "maPB=" + maPB + ", tenPB=" + tenPB + '}';
    }
    
    
}
