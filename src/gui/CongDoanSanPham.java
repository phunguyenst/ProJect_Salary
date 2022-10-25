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
import entity.CongDoan;
import dao.CongDoanSPDAO;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author admin
 */
public class CongDoanSanPham extends javax.swing.JPanel {
    DefaultTableModel modelSP;
    DefaultTableModel modalCD;
    private final SanPhamDAO spDao;
    private final CongDoanSPDAO cdDao;
    
    /**
     * Creates new form CongDoanSanPham
     */
    public CongDoanSanPham() {
        initComponents();
        ConnectDB1.getInstance().connect();
        spDao = new SanPhamDAO();
        cdDao = new CongDoanSPDAO();
        
           modelSP = (DefaultTableModel) tblSanPham.getModel();
           modalCD = (DefaultTableModel) tblCongDoan.getModel();
           DocSanPhamVaoTable();
           DocCongDoanVaoTable();
          txtTrangThai.setEnabled(false);
           
          
           
    }
     
    public void DocSanPhamVaoTable(){
        modelSP.setRowCount(0);
        List<SanPham> listSP = spDao.getALLSP();
        for (SanPham sp : listSP) {
            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.soLuongTT(), sp.getDonViTinh()};
            modelSP.addRow(row);
        }
    }
    public void DocCongDoanVaoTable(){
        modalCD.setRowCount(0);
        List<CongDoan> listCD = cdDao.getCongDoan();
        for (CongDoan cd : listCD) {
            
            Object[] row = {cd.getMaCD(), cd.getMaSP().getMaSP(),cd.getTenSP().getTenSP() ,cd.getTenCD(), cd.getDonGiaCD(), cd.getSoLuong(), cd.getMaRangBuoc(), cd.kiemTraCongDoan()?"đã hoàn thành": "chưa hoàn thành"};
            modalCD.addRow(row);
        }
    }
    public void themCD(){
        String maCD = txtMaCD.getText().trim();
        String maSP = txtMaSP.getText().trim();
        String tenCD = txtTenCD.getText().trim();
        String tenSP = txtTenSP.getText().trim();
        float donGiaCD = Float.parseFloat(txtGiaCD.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        int maRangBuoc = Integer.parseInt(cboMaRangBuoc.getSelectedItem().toString());
        
         
        SanPham sp = new SanPham(maSP, tenSP);
        CongDoan cd = new CongDoan(maCD, tenCD, sp, sp, donGiaCD, soLuong, maRangBuoc);
        List<CongDoan> listCD = cdDao.getCongDoan();
         
            if(cdDao.createCD(cd)){
                Object[] row = {cd.getMaCD(), cd.getMaSP().getMaSP(), cd.getTenSP().getTenSP(),cd.getTenCD(), cd.getDonGiaCD(), cd.getSoLuong(), cd.getMaRangBuoc(), cd.kiemTraCongDoan()?"đã hoàn thành": "chưa hoàn thành"};
            modalCD.addRow(row);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
            
    }
    public void suaCD(){
        int row = tblCongDoan.getSelectedRow();
        if(row == -1){
             JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
         }
         if(row >=0)
         {
            String maCD = txtMaCD.getText().trim();
            String maSP = txtMaSP.getText().trim();
            String tenCD = txtTenCD.getText().trim();
            String tenSP = txtTenSP.getText().trim();
            float donGiaCD = Float.parseFloat(txtGiaCD.getText());
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            int maRangBuoc = Integer.parseInt(cboMaRangBuoc.getSelectedItem().toString());
            Boolean trangThai = Boolean.parseBoolean(txtTrangThai.getText());
           
            SanPham sp = new SanPham(maSP, tenSP);
            CongDoan cd = new CongDoan(maCD, tenCD, sp, sp, donGiaCD, soLuong, maRangBuoc);
            int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa","Attention!",JOptionPane.YES_NO_OPTION);
         if(ques == JOptionPane.YES_OPTION){
             if(cdDao.editCD(cd)){
                 DocCongDoanVaoTable();
                 JOptionPane.showMessageDialog(this, "Cập nhật thành công");
             }
             else{
                 JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
             }
            }
         }
    }
    public void xoaTrang(){
        txtMaCD.setText("");
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtTenCD.setText("");
        txtGiaCD.setText("");
        txtSoLuong.setText("");
        cboMaRangBuoc.setSelectedIndex(0);
    }
    public void xoaCD(){
        int row = tblCongDoan.getSelectedRow();
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này ?", "Warning", JOptionPane.YES_NO_OPTION);
        if(ques == JOptionPane.YES_OPTION){
            String maCD = (String) tblCongDoan.getValueAt(row, 0);
           
            cdDao.removeCD(maCD);
            DocCongDoanVaoTable();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        txtTenCD = new javax.swing.JTextField();
        txtGiaCD = new javax.swing.JTextField();
        cboMaRangBuoc = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCongDoan = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtMaSP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Công Đoạn Sản Xuất");

        jLabel2.setText("Mã Công Đoạn");

        jLabel3.setText("Tên Công Đoạn");

        jLabel5.setText("Mã Sản Phẩm");

        jLabel6.setText("Số Công Đoạn");

        txtMaCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCDActionPerformed(evt);
            }
        });

        cboMaRangBuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", " " }));

        jLabel9.setText("Giá Công Đoạn");

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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel4.setText("Danh Sách Sản Phẩm: ");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoaTrang.setText("Xóa Rỗng");
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        tblCongDoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CD", "Mã SP", "Tên SP", "Tên CD", "Đơn Giá CD", "Số Lượng", "Số Công Đoạn", "TrangThai"
            }
        ));
        tblCongDoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCongDoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCongDoan);

        jLabel10.setText("Tên Sản Phẩm");

        jLabel11.setText("Số Lượng");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel7.setText("TrangThai");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtGiaCD))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTenCD))
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5)))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(231, 231, 231)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(btnThem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                        .addComponent(btnXoa)
                                        .addGap(65, 65, 65)
                                        .addComponent(btnSua)))
                                .addGap(25, 25, 25)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnXoaTrang)
                                        .addGap(50, 50, 50))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cboMaRangBuoc, 0, 118, Short.MAX_VALUE)
                                                    .addComponent(txtSoLuong)
                                                    .addComponent(txtTrangThai))))
                                        .addGap(53, 53, 53)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(cboMaRangBuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtGiaCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)
                            .addComponent(btnThem)
                            .addComponent(btnXoaTrang)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCDActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themCD();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        txtMaSP.setText(modelSP.getValueAt(row, 0).toString());
        txtTenSP.setText(modelSP.getValueAt(row, 1).toString());
        txtSoLuong.setText(modelSP.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaCD();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        suaCD();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        // TODO add your handling code here:
        xoaTrang();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void tblCongDoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCongDoanMouseClicked
        // TODO add your handling code here:
        int row = tblCongDoan.getSelectedRow();
        txtMaCD.setText(modalCD.getValueAt(row, 0).toString());
        txtMaSP.setText(modalCD.getValueAt(row, 1).toString());
        txtTenSP.setText(modalCD.getValueAt(row, 2).toString());
        txtTenCD.setText(modalCD.getValueAt(row, 3).toString());
        txtGiaCD.setText(modalCD.getValueAt(row, 4).toString());
        txtSoLuong.setText(modalCD.getValueAt(row, 5).toString());
        cboMaRangBuoc.setSelectedItem(modalCD.getValueAt(row, 6).toString());
        txtTrangThai.setText(modalCD.getValueAt(row, 7).toString());
     
    }//GEN-LAST:event_tblCongDoanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cboMaRangBuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblCongDoan;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaCD;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenCD;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
