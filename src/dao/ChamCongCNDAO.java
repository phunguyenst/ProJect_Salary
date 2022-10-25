/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import com.sun.source.tree.TryTree;
import connect.ConnectDB1;
import entity.BangCongCN;
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
      public int findSL(String maCĐ, String maCN){
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String  sql ="select * from PhanCong INNER JOIN congdoan ON congdoan.macđ=phancong.macđ  where congdoan.macđ='"+maCĐ+"'and phancong.maCN='"+maCN+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int sl = rs.getInt(12);
               return sl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return 0;
    }
      
       public String layGioLamtheoca(String maCa){
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String  sql ="select * from calamviec where maCa='"+maCa+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String temca = rs.getString(2);
               return temca;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return null;
    }
          public String layTenCatheoMaca(String maCa){
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String  sql ="select * from calamviec where maCa='"+maCa+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String tenca = rs.getString(3);
               return tenca;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return null;
    }
//           public boolean createBC(BangCongCN bc){
//        ConnectDB1.getInstance();
//	Connection conn = ConnectDB1.getConnection();
//	PreparedStatement pstm = null;
//        try {
//            String sql = "insert into PhieuCong_CN values(?,?,?,?,?,?,?,?,?)";
//            pstm = conn.prepareStatement(sql);
//            pstm.setString(1, bc.getMaCa());
//            pstm.setString(2, bc.getTenCa());
//            pstm.setString(3,bc.get);
//            pstm.setString(4, cn.getSđt());
//            pstm.setString(5, cn.getDiaChi());
//            pstm.setBoolean(6, cn.isGioiTinh());           
//            pstm.setDate(7, cn.getNgaySinh());
//            pstm.setDate(7, cn.getNgaySinh());
//            pstm.setDate(7, cn.getNgaySinh());
//            pstm.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }finally{
//            try{
//                pstm.close();
//            }catch (SQLException ex){
//                ex.printStackTrace();
//            }
//        }
//    }

      public boolean craeateChamCongAndUpdateCD(BangCongCN bc, int soLuong){
          ConnectDB1.getInstance();
          Connection conn = ConnectDB1.getConnection();
          PreparedStatement pstm = null;
          int n = 0;
          try {
              pstm = conn.prepareStatement("Insert Into PhieuCong_CN values(?,?,?,?,?,?,?,?)");
              pstm.setString(1, bc.getMaCa().getMaCa());
              pstm.setString(2, bc.getTenCa().getTenCa());
              pstm.setString(3,bc.getMaCN().getMaCN());
              pstm.setString(4, bc.getTenCN().getTenCN());
              pstm.setInt(5, bc.getSoLuongLamDc());
              pstm.setString(6, bc.getMaCD().getMaCD());
              pstm.setString(7,bc.getTenCD().getTenCD());
              pstm.setDate(8, bc.getNgayCham());
              
              n = pstm.executeUpdate();
              return true;
          } catch (SQLException e) {
              e.printStackTrace();
              return false;
          }
      }
      
      public boolean updateCongDoan(BangCongCN bc, int soLuong){
          ConnectDB1.getInstance();
          Connection conn = ConnectDB1.getConnection();
          PreparedStatement pstm = null;
          int n = 0;
          try {
              
              pstm = conn.prepareStatement("Update CongDoan set SoLuong = ? Where maCĐ = ?");
              pstm.setInt(1, soLuong);
              pstm.setString(2, bc.getMaCD().getMaCD());
              n = pstm.executeUpdate();
              return true;
          } catch (SQLException e) {
              e.printStackTrace();
              return false;
          }
      }
}

