/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import com.sun.source.tree.TryTree;
import connect.ConnectDB1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.PhanCong;
import entity.CongNhan;
import entity.CongDoan;
import entity.SanPham;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tuan Kiet Admin
 */
public class ChamCongCNDAO {
       
//      public List<PhanCong> findPCbyMa(String ma){
//        List<NhanVien> lst = new ArrayList<NhanVien>();
//        try {
//            ConnectDB1.getInstance();
//            Connection conn = ConnectDB1.getConnection();
//            String sql= "select * from NhanVienHanhChanh where MaNV= '"+ma+"'";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                String maNV = rs.getString(1);
//                String tenNV = rs.getString(3);
//                String diaChi = rs.getString(6);
//                String sdt = rs.getString(7);
//                Date ngSinh = rs.getDate(4);
//                Date ngCT = rs.getDate(5);
//                boolean gioiTinh = rs.getBoolean(8);
//                String trinhDo = rs.getString(9);
//                PhongBan pb = new PhongBan(rs.getString(2));
//                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ngSinh, ngCT, gioiTinh, pb);
//                lst.add(nv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//	return lst;
//    }
}

