/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
import entity.NhanVien;
import java.util.ArrayList;
import Connect.ConnectDB1;
import entity.PhongBan;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class ChamCongNVDAO {
    public List<NhanVien> getNVChamCong(){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select MaNV,TenNV,MaPB,Sđt from NhanVienHanhChanh";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String sdt = rs.getString(4);
                PhongBan pb = new PhongBan(rs.getString(3));
                NhanVien nv = new NhanVien(maNV, tenNV, sdt, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
}
