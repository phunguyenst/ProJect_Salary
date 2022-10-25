/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;
import connect.ConnectDB1;
import javax.swing.table.DefaultTableModel;
import dao.SanPhamDAO;
import dao.CongNhanDAO;
import dao.CongDoanSPDAO;
import dao.PhanCongDAO;
import entity.CongDoan;
import entity.CongNhan;
import entity.PhanCong;


import entity.SanPham;
import java.util.ArrayList;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author admin
 */
public class PhanCongCN extends javax.swing.JPanel {

    /**
     * Creates new form PhanCongCN
     */
    DefaultTableModel modelCD;
    DefaultTableModel modelCN;
     DefaultTableModel modelPC;
    private  SanPhamDAO spDao;
    private  CongNhanDAO cnDao;
    private  CongDoanSPDAO cdDao;
    private  PhanCongDAO pcDao;
    public PhanCongCN() {
        initComponents();
        
        ConnectDB1.getInstance().connect();
         spDao = new SanPhamDAO();
         cnDao = new CongNhanDAO();
         cdDao = new CongDoanSPDAO();
         pcDao = new PhanCongDAO();
        modelCD = (DefaultTableModel) tblCongDoan.getModel();
         modelPC = (DefaultTableModel) tblPhanCong.getModel();
       
//        DocSanPhamVaoTable();
        loadComboBoxMa();
        loadComboBoxTen();
        loadComboBoxMaSP();
        loadComboBoxMaCD();
        loadComboBoxTenCD();
        loadPhanCong();
        
        
        
        
    }

    public void loadComboBoxMa(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaCN.getModel();
        model.removeAllElements();

        List<CongNhan> lst = cnDao.getALLCN();
        for (CongNhan cn : lst) {
            model.addElement(cn.getMaCN());
        }
        cboMaCN.setSelectedIndex(0);
    }
   
     public void loadComboBoxMaCD(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaCD.getModel();
        model.removeAllElements();

        List<CongDoan> lst = cdDao.getCongDoan();
        for (CongDoan cd : lst) {
            model.addElement(cd.getMaCD());
        }
        cboMaCD.setSelectedIndex(0);
    }
     public void loadComboBoxTenCD(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenCD.getModel();
        model.removeAllElements();

        List<CongDoan> lst = cdDao.getCongDoan();
        for (CongDoan cd : lst) {
            model.addElement(cd.getTenCD());
        }
        cboTenCD.setSelectedIndex(0);
    }
    public void loadPhanCong(){
        modelPC.setRowCount(0);
        List<PhanCong> listPC = pcDao.getPhanCong();
        for (PhanCong pc : listPC) {
            Object[] row = { pc.getMaCD().getMaCD(),pc.getMaCN().getMaCN(), pc.getTenCN().getTenCN(), pc.getTenCD().getTenCD(), pc.getMaSP().getMaSP(), pc.getTenSP().getTenSP()};
            modelPC.addRow(row);
        }
    }
    public void ThemPhanCong(){
        
        String maCN = cboMaCN.getSelectedItem().toString();
        String tenCN = cboTenCN.getSelectedItem().toString();
        String maCD = cboMaCD.getSelectedItem().toString();
        String tenCD = cboTenCD.getSelectedItem().toString();
        String maSP = cboSanPham.getSelectedItem().toString();
        String tenSP = cboTenSP.getSelectedItem().toString();
        int soCD = Integer.parseInt(txtSoCD.getText());
        boolean trangThai = Boolean.parseBoolean(txtTrangThai.getText());
        CongNhan cn = new CongNhan(maCN, tenCN);
        CongDoan cd = new CongDoan(maCD, tenCD);
        System.out.println(trangThai);
        
        SanPham sp = new SanPham(maSP, tenSP);
        PhanCong pc = new PhanCong(cn, cn, sp, sp, cd, cd);
        List<PhanCong> listPC = pcDao.getPhanCong();
        
        
        if(trangThai == false && soCD==1){
            if(pcDao.createPC(pc)){
              Object[] row = { pc.getMaCD().getMaCD(),pc.getMaCN().getMaCN(), pc.getTenCN().getTenCN(), pc.getTenCD().getTenCD(), pc.getMaSP().getMaSP(), pc.getTenSP().getTenSP()};
            modelPC.addRow(row);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } 
          
            
        }
        else if(trangThai == false){
            JOptionPane.showMessageDialog(this, "Công đoạn trước chưa hoàn thành");
        }
        else {
           if(pcDao.createPC(pc)){
              Object[] row = { pc.getMaCD().getMaCD(),pc.getMaCN().getMaCN(), pc.getTenCN().getTenCN(), pc.getTenCD().getTenCD(), pc.getMaSP().getMaSP(), pc.getTenSP().getTenSP()};
            modelPC.addRow(row);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
               
            } 
        }
//        else{
//            if(trangThai ==false){
//                JOptionPane.showMessageDialog(this, "");
//            }
//        }
//        else if(trangThai== false && cd.getSoLuong()>0 ){
//              
//            
//        }
//        else{
//            if(pcDao.createPC(pc)){
//              Object[] row = { pc.getMaCD().getMaCD(),pc.getMaCN().getMaCN(), pc.getTenCN().getTenCN(), pc.getTenCD().getTenCD(), pc.getMaSP().getMaSP(), pc.getTenSP().getTenSP()};
//            modelPC.addRow(row);
//                JOptionPane.showMessageDialog(this, "Thêm thành công");
//               
////            } 
//        }
        
         
    }
    public void loadComboBoxMaSP(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();

        List<SanPham> lst = spDao.getALLSP();
        for (SanPham sp : lst) {
            model.addElement(sp.getMaSP());
        }
        cboSanPham.setSelectedIndex(0);
    }
  
   
      public void loadComboBoxTen(){
       DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenCN.getModel();
        model.removeAllElements();

        List<CongNhan> lst = cnDao.getALLCN();
        for (CongNhan cn : lst) {
            model.addElement(cn.getTenCN());
        }
        cboMaCN.setSelectedIndex(0);
    }
      public void layTenCNTheoMa()
      {
        String maCN = cboMaCN.getSelectedItem().toString();
  
       
        String listCN = pcDao.getTenCNTheoMa(maCN);
        cboTenCN.setSelectedItem(listCN);
  
        
      }
      public void layTenSPTheoMa()
      {
        String maSP = cboSanPham.getSelectedItem().toString();
  
       
        String listSP = pcDao.getTenSPTheoMa(maSP);
        cboTenSP.setSelectedItem(listSP);
  
        
      }
       public void layMaTheoTen()
      {
        String tenCN = cboTenCN.getSelectedItem().toString();
        
        String listCN = pcDao.getMaCNTheoTen(tenCN);
        cboMaCN.setSelectedItem(listCN);
  
        
      }
//      public void loadCDVaoTable(){
//          modelCD.setRowCount(0);
//          List<CongDoan> list = cdDao.LayCacTPTrongCD();
//          for (CongDoan cd : list) {
//              Object[] row = {cd.getMaCD(), cd.getTenCD(), cd.getSoLuong().getSoLuong()};
//              modelCD.addRow(row);
//          }
//      }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboMaCN = new javax.swing.JComboBox<>();
        cboMaCD = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTenCD = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhanCong = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnPhanCong = new custom_button.MyButton();
        btnXoa = new custom_button.MyButton();
        btnSua = new custom_button.MyButton();
        txtSoLuongCL = new javax.swing.JTextField();
        txtHTCD = new javax.swing.JTextField();
        cboTenCN = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCongDoan = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        lblTenSP = new javax.swing.JLabel();
        cboTenSP = new javax.swing.JComboBox<>();
        txtSoCD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Phân Công Công Nhân");
        add(jLabel1);
        jLabel1.setBounds(573, 7, 203, 21);

        jLabel3.setText("Mã Công Nhân");
        add(jLabel3);
        jLabel3.setBounds(496, 37, 81, 16);

        jLabel4.setText("Mã Công Đoạn");
        add(jLabel4);
        jLabel4.setBounds(500, 110, 80, 16);

        cboMaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaCNActionPerformed(evt);
            }
        });
        add(cboMaCN);
        cboMaCN.setBounds(680, 40, 121, 22);

        add(cboMaCD);
        cboMaCD.setBounds(682, 105, 121, 22);

        jLabel5.setText("Tên Công Nhân");
        add(jLabel5);
        jLabel5.setBounds(866, 37, 83, 16);

        jLabel6.setText("Tên Công Đoạn");
        add(jLabel6);
        jLabel6.setBounds(870, 100, 82, 16);

        jLabel7.setText("Số Lượng Cần Làm");
        add(jLabel7);
        jLabel7.setBounds(870, 150, 120, 16);

        cboTenCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenCDActionPerformed(evt);
            }
        });
        add(cboTenCD);
        cboTenCD.setBounds(1037, 99, 158, 22);
        add(jLabel8);
        jLabel8.setBounds(1201, 108, 158, 0);
        add(jLabel9);
        jLabel9.setBounds(1201, 108, 158, 0);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1);
        jSeparator1.setBounds(477, 7, 13, 313);

        jLabel10.setText("Công Đoạn: ");
        add(jLabel10);
        jLabel10.setBounds(518, 204, 66, 16);

        jLabel12.setText("Trạng Thái: ");
        add(jLabel12);
        jLabel12.setBounds(842, 203, 80, 20);

        txtTrangThai.setText("_");
        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });
        add(txtTrangThai);
        txtTrangThai.setBounds(980, 214, 160, 22);

        tblPhanCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MaCD", "MaCN", "TenCN", "TenCD", "MaSP", "TenSP"
            }
        ));
        tblPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanCongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhanCong);

        add(jScrollPane2);
        jScrollPane2.setBounds(7, 351, 1200, 396);
        add(jSeparator2);
        jSeparator2.setBounds(1, 320, 1209, 10);

        btnPhanCong.setText("Phân Công");
        btnPhanCong.setBorderColor(new java.awt.Color(102, 102, 102));
        btnPhanCong.setColorhover(new java.awt.Color(0, 255, 255));
        btnPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPhanCong.setRadius(30);
        btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanCongActionPerformed(evt);
            }
        });
        add(btnPhanCong);
        btnPhanCong.setBounds(570, 255, 110, 40);

        btnXoa.setText("Xóa");
        btnXoa.setBorderColor(new java.awt.Color(102, 102, 102));
        btnXoa.setColorhover(new java.awt.Color(0, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setRadius(30);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa);
        btnXoa.setBounds(790, 255, 110, 40);

        btnSua.setText("Sửa");
        btnSua.setBorderColor(new java.awt.Color(102, 102, 102));
        btnSua.setColorhover(new java.awt.Color(0, 255, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setRadius(30);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        add(btnSua);
        btnSua.setBounds(1010, 250, 110, 40);
        add(txtSoLuongCL);
        txtSoLuongCL.setBounds(1040, 150, 150, 22);

        txtHTCD.setText("_");
        add(txtHTCD);
        txtHTCD.setBounds(600, 205, 180, 22);

        cboTenCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenCNActionPerformed(evt);
            }
        });
        add(cboTenCN);
        cboTenCN.setBounds(1040, 40, 160, 22);

        tblCongDoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã CD", "Tên CD", "So Luong Can Lam", "Số công đoạn", "Trạng thái"
            }
        ));
        tblCongDoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCongDoanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCongDoan);

        add(jScrollPane3);
        jScrollPane3.setBounds(10, 77, 460, 230);

        jLabel11.setText("Sản Phẩm");
        add(jLabel11);
        jLabel11.setBounds(10, 27, 60, 16);

        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });
        add(cboSanPham);
        cboSanPham.setBounds(76, 24, 150, 22);
        add(lblTenSP);
        lblTenSP.setBounds(265, 24, 0, 0);

        cboTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cboTenSP);
        cboTenSP.setBounds(270, 20, 180, 30);
        add(txtSoCD);
        txtSoCD.setBounds(681, 150, 120, 22);

        jLabel2.setText("Số CD");
        add(jLabel2);
        jLabel2.setBounds(500, 150, 80, 16);
    }// </editor-fold>//GEN-END:initComponents

    private void cboMaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaCNActionPerformed
        // TODO add your handling code here:
        layTenCNTheoMa();
        
    }//GEN-LAST:event_cboMaCNActionPerformed

    private void btnPhanCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanCongActionPerformed
        // TODO add your handling code here:
        ThemPhanCong();
        
    }//GEN-LAST:event_btnPhanCongActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaPC();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cboTenCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenCNActionPerformed
        // TODO add your handling code here:
        layMaTheoTen();
        
    }//GEN-LAST:event_cboTenCNActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
        String maSP = cboSanPham.getSelectedItem().toString();
        List<CongDoan> list = cdDao.LayCacTPTrongCD(maSP);
        modelCD.setRowCount(0);
        for (CongDoan congDoan : list) {
            
            Object[] row = {congDoan.getMaCD(), congDoan.getTenCD(), congDoan.getSoLuong(), congDoan.getMaRangBuoc(), congDoan.kiemTraCongDoan()?"đã hoàn thành": "chưa hoàn thành"};
            modelCD.addRow(row);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenSP.getModel();
        model.removeAllElements();
        List<SanPham> listTen = spDao.getALLSP();
        for (SanPham sp : listTen) {
             model.addElement(sp.getTenSP());
        }
  
        cboTenSP.setSelectedIndex(0);
        layTenSPTheoMa();
 
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void tblCongDoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCongDoanMouseClicked
//        TODO add your handling code here:
        int row = tblCongDoan.getSelectedRow();
       cboMaCD.setSelectedItem(modelCD.getValueAt(row, 0).toString());
       cboTenCD.setSelectedItem(modelCD.getValueAt(row, 1).toString());
        txtSoLuongCL.setText(modelCD.getValueAt(row, 2).toString());
        txtSoCD.setText(modelCD.getValueAt(row, 3).toString());
        txtTrangThai.setText(modelCD.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tblCongDoanMouseClicked

    private void tblPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanCongMouseClicked
        // TODO add your handling code here:
        int row = tblPhanCong.getSelectedRow();
       cboMaCD.setSelectedItem(modelPC.getValueAt(row, 0).toString());
        cboMaCN.setSelectedItem(modelPC.getValueAt(row, 1).toString());
        cboTenCN.setSelectedItem(modelPC.getValueAt(row, 2).toString());
        cboTenCD.setSelectedItem(modelPC.getValueAt(row, 3).toString());
                
    }//GEN-LAST:event_tblPhanCongMouseClicked

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void cboTenCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenCDActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboTenCDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnPhanCong;
    private custom_button.MyButton btnSua;
    private custom_button.MyButton btnXoa;
    private javax.swing.JComboBox<String> cboMaCD;
    private javax.swing.JComboBox<String> cboMaCN;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboTenCD;
    private javax.swing.JComboBox<String> cboTenCN;
    private javax.swing.JComboBox<String> cboTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JTable tblCongDoan;
    private javax.swing.JTable tblPhanCong;
    private javax.swing.JTextField txtHTCD;
    private javax.swing.JTextField txtSoCD;
    private javax.swing.JTextField txtSoLuongCL;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables

    private void xoaPC() {
        int row = tblPhanCong.getSelectedRow();
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này ?", "Warning", JOptionPane.YES_NO_OPTION);
        if(ques == JOptionPane.YES_OPTION){
            String maCD = (String) tblPhanCong.getValueAt(row, 0);
            String maCN = (String) tblPhanCong.getValueAt(row, 1);
            
            pcDao.removePC(maCD, maCN);
            loadPhanCong();
    }
    }

//    private boolean rangBuocCD(CongDoan cd) {
//        
//        boolean trangthai = cd.kiemTraCongDoan();
//        if(trangthai == false && cd.getMaRangBuoc()== 1){
//            return true;
//            
//        }
//        else
//            return false;
//    }
}
