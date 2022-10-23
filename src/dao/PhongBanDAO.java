/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.PhongBan;
import java.sql.Connection;
import java.sql.*;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class PhongBanDAO {
    public List<PhongBan> getAllPB(){
        List<PhongBan> lstPB = new ArrayList<PhongBan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from PhongBan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maPB = rs.getString(1);
                String tenPB =rs.getString(2);
                PhongBan pb = new PhongBan(maPB, tenPB);
                lstPB.add(pb);
            }
        } catch (Exception e) {
        }
        return lstPB;
    }
}
