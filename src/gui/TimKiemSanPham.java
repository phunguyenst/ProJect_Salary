/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB1;
import javax.swing.table.DefaultTableModel;
import dao.SanPhamDAO;
import entity.SanPham;
import java.util.ArrayList;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class TimKiemSanPham extends javax.swing.JPanel {
    DefaultTableModel modelSP;
    private  SanPhamDAO spDao;
    /**
     * Creates new form TimKiemSanPham
     */
    public TimKiemSanPham() {
        initComponents();
         ConnectDB1.getInstance().connect();
         spDao = new SanPhamDAO();
        
        modelSP = (DefaultTableModel) tblSanPham.getModel();
        DocSanPhamVaoTable();
        loadComboBoxMaSP();
    }
     public void DocSanPhamVaoTable(){
        modelSP.setRowCount(0);
        List<SanPham> listSP = spDao.getALLSP();
        for (SanPham sp : listSP) {
            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
        }
    }
     public void locSPTheoMa(){
        if(cboMaSP.getSelectedItem().equals("Tất cả")){
            DocSanPhamVaoTable();
        }
        else if(cboMaSP.getSelectedItem().equals("")){
            modelSP.setRowCount(0);
        }
        else{
            String txtMa = cboMaSP.getSelectedItem().toString();
            List<SanPham> lst = spDao.timSanPhamTheoMa(txtMa);
         
            modelSP.setRowCount(0);
            for (SanPham sp : lst) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
            }
        }
    }
     public void locDonGia(){
         int a = 1;
         System.out.println(a);
         float txtTu = Float.parseFloat((String) cboFrom.getSelectedItem());
         float txtDen = Float.parseFloat((String) cboTo.getSelectedItem());
         
            List<SanPham> lst = spDao.timSanPhamTheoGia(txtTu, txtDen);
           
           for (SanPham sanPham : lst) {
               System.out.println(sanPham);
         }
            modelSP.setRowCount(0);
            for (SanPham sp : lst) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
            }
     }
     public void loadComboBoxMaSP(){
         DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaSP.getModel();
         model.removeAllElements();
         model.addElement("");
         model.addElement("Tất cả");
         List<SanPham> listSP = spDao.getALLSP();
         for (SanPham sp : listSP) {
             model.addElement(sp.getMaSP());
         }
         cboMaSP.setSelectedIndex(0);
     }
     public void timKiemSanPhamTheoTen(){
         String txtTenSP = this.txtTenSP.getText().toString().trim();
         List<SanPham> listSP = spDao.timSPTheoTen(txtTenSP);
         modelSP.setRowCount(0);
         for (SanPham sp : listSP) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
         }
     }
     public void timKiemSanPhamTheoThuongHieu(){
         String txtThuongHieu = this.txtThuongHieu.getText().toString().trim();
          List<SanPham> listSP = spDao.timSPTheoThuongHieu(txtThuongHieu);
         modelSP.setRowCount(0);
         for (SanPham sp : listSP) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
         }
     }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboMaSP = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        txtThuongHieu = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cboFrom = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        cboTo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1200, 1015));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tìm Kiếm Sản Phẩm");

        jLabel2.setText("Mã Sản Phẩm");

        jLabel3.setText("Tên Sản Phẩm");

        jLabel4.setText("Thương Hiệu");

        jLabel5.setText("Đơn Giá");

        cboMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", " " }));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Thương Hiệu", "Đơn Giá", "Số Lượng", "Đơn Vị Tính"
            }
        ));
        jScrollPane1.setViewportView(tblSanPham);

        cboFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5000", "9000", "11000", "13000", "15000" }));
        cboFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFromActionPerformed(evt);
            }
        });

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        cboTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8000", "10000", "12000", "14000", "16000", " " }));

        jLabel6.setText("Từ ");

        jLabel7.setText("Đến");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLoc)
                .addGap(35, 35, 35)
                .addComponent(btnTim)
                .addGap(325, 325, 325))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(17, 17, 17)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenSP)
                            .addComponent(txtThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(cboTo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTim)
                    .addComponent(btnLoc))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 310, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        if((!txtTenSP.getText().equals(""))&&(txtThuongHieu.getText().equals(""))){
            timKiemSanPhamTheoTen();
        }
        else if((!txtThuongHieu.getText().equals(""))&&(txtTenSP.getText().equals(""))){
            timKiemSanPhamTheoThuongHieu();
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void cboFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFromActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboFromActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here0:
         if(!cboMaSP.getSelectedItem().equals("")){
             locSPTheoMa();
        }
         else if((!(cboTo.getSelectedItem().equals(""))) &&(!(cboTo.getSelectedItem().equals(""))) ){
               locDonGia();
         }
           
         
    }//GEN-LAST:event_btnLocActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTim;
    private javax.swing.JComboBox<String> cboFrom;
    private javax.swing.JComboBox<String> cboMaSP;
    private javax.swing.JComboBox<String> cboTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThuongHieu;
    // End of variables declaration//GEN-END:variables
}
