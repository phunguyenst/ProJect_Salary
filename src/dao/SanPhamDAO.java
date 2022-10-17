/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
import java.util.ArrayList;
import Connect.ConnectDB1;
import Entity.SanPham;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.Statement;


/**
 *
 * @author admin
 */
public class SanPhamDAO {
    public List<SanPham> getALLSP(){
        List<SanPham> listSP = new ArrayList<SanPham>();
        
       
        try {
             ConnectDB1.getInstance();
             Connection con = ConnectDB1.getConnection();
             String sql = "Select * From SanPham";
             Statement stmt;
            stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                 String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
           return listSP;
    }
    public boolean createSP(SanPham sp){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        
        String sql = "insert into SanPham values (?,?,?,?,?,?)";
        try {
               pstm = conn.prepareStatement(sql);
               pstm.setString(1, sp.getMaSP());
               pstm.setString(2, sp.getTenSP());
               pstm.setString(3, sp.getThuongHieu());
               pstm.setFloat(4, sp.getDonGia());
               pstm.setInt(5, sp.getSoLuong());
               pstm.setString(6, sp.getDonViTinh());
               return pstm.executeUpdate()>0;
              
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return false;
    }
    public boolean removeSP(String maSP){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from SanPham where maSP=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maSP);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }
    public boolean suaSanPham(SanPham sp){
         ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update SanPham set tenSP=?,thuongHieu=?,donGia=?,soLuong=?,donViTinh=? where maSP=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sp.getTenSP());
            stmt.setString(2, sp.getThuongHieu());
            stmt.setFloat(3, sp.getDonGia());
            stmt.setInt(4, sp.getSoLuong());
            stmt.setString(5, sp.getDonViTinh());
            stmt.setString(6, sp.getMaSP());
            return stmt.executeUpdate() >0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<SanPham> timSanPham(String ma){
        List<SanPham> listSP = new ArrayList<SanPham>();
        ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from SanPham where maSP= '"+ma+"'";
            try {
             Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
            }
        } catch (SQLException e) {
        }
            return listSP;
    }
    
}
