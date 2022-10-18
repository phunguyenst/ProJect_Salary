/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.CongDoan;
import java.util.List;
import entity.SanPham;
import java.util.ArrayList;
import Connect.ConnectDB1;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class CongDoanSPDAO {
    public List<CongDoan> getCongDoan(){
           List<CongDoan> lstCD = new ArrayList<CongDoan>();
        try {
         
            
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "Select * From CongDoan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCD = rs.getString(1);
                SanPham sp = new SanPham(rs.getString(2));
                String tenCD = rs.getString(3);
                float donGiaCD = rs.getFloat(4);
                int soLuong = rs.getInt(5);
                int maRangBuoc = rs.getInt(6);
                boolean trangThai = rs.getBoolean(7);
                CongDoan cd = new CongDoan(maCD, tenCD, sp, donGiaCD, soLuong, maRangBuoc, trangThai);
                lstCD.add(cd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCD;
    }
   
}
