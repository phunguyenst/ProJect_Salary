/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

/**
 *
 * @author admin
 */
import connect.ConnectDB1;
import javax.swing.table.DefaultTableModel;
import dao.SanPhamDAO;
import entity.SanPham;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class FrmSanPham extends javax.swing.JPanel {

    DefaultTableModel modelSP;
    private  SanPhamDAO spDao;
    private String duongDanAnh;
    private String anh;
    private ArrayList<SanPham> listSP;
    /**
     * Creates new form CongDoanSanPham
     */
    
    public FrmSanPham() {
        initComponents();
        ConnectDB1.getInstance().connect();
         spDao = new SanPhamDAO();
         
        listSP = (ArrayList<SanPham>) spDao.getALLSP();
        modelSP = (DefaultTableModel) tblSanPham.getModel();
        DocSanPhamVaoTable();
//        themDuLieuVaoBang(listSP);
       
        
    }
    
     public void DocSanPhamVaoTable(){
        modelSP.setRowCount(0);
        List<SanPham> listSP = spDao.getALLSP();
        SanPham sanpham = new SanPham();
        
        
        for (SanPham sp : listSP) {
            
            
            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh(), sp.getAnh()};
            modelSP.addRow(row);
        }
    }
//     private void themDuLieuVaoBang(ArrayList<SanPham> listSP){
//        
//        for (SanPham sp : listSP) {
//            modelSP.addRow(new Object[] {
//                sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh(), sp.getAnh()              
//            });
//        }
//    }
     public void themSP(){
         txtMaSP.setText(spDao.getMaSPTuDong());
         String tenSP = txtTenSP.getText().trim();
         String thuongHieu = (String) cboThuongHieu.getSelectedItem();
         float donGia = Float.parseFloat(txtDonGia.getText().trim());
         int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
         String donViTinh = cboDonViTinh.getSelectedItem().toString();
        anh = lblAnh.getText().toString();
         List<SanPham> listSP = spDao.getALLSP();
        String maSP = txtMaSP.getText().trim(); 
         
         SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh,anh);
         
         if(listSP.contains(sp)){
                JOptionPane.showMessageDialog(this, "TR??NG M?? S???N PH???M");
        }else{
            if(spDao.createSP(sp)){
                Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh(), sp.getAnh()};
            modelSP.addRow(row);
                JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng");
            }
         }
     }
     public void xoaSP(){
         int row = tblSanPham.getSelectedRow();
         int ques = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n x??a d??ng n??y ?", "Warning", JOptionPane.YES_NO_OPTION);
            if(ques == JOptionPane.YES_OPTION){
                String maSP = (String) tblSanPham.getValueAt(row, 0);
                spDao.removeSP(maSP);
//                themDuLieuVaoBang(listSP);
                DocSanPhamVaoTable();
    
            }
            xoaTrang();
      }
     public void xoaTrang(){
         txtMaSP.setText("");
         txtTenSP.setText("");
         cboThuongHieu.setSelectedItem("");
         txtDonGia.setText("");
         txtSoLuong.setText("");
         cboDonViTinh.setSelectedItem("");
         
     }
     public void clickTable(){
         int row = tblSanPham.getSelectedRow();
         txtMaSP.setText(modelSP.getValueAt(row, 0).toString());
         txtTenSP.setText(modelSP.getValueAt(row, 1).toString());
         cboThuongHieu.setSelectedItem(modelSP.getValueAt(row, 2).toString());
         txtDonGia.setText(modelSP.getValueAt(row, 3).toString());
         txtSoLuong.setText(modelSP.getValueAt(row, 4).toString());
         cboDonViTinh.setSelectedItem(modelSP.getValueAt(row, 5).toString());
         lblAnh.setIcon(ResizeImage(String.valueOf(System.getProperty("user.dir")+modelSP.getValueAt(row, 6).toString())));
         
     }
     public void SuaSP(){
         int row = tblSanPham.getSelectedRow();
         if(row == -1){
             JOptionPane.showMessageDialog(this, "Vui l??ng ch???n d??ng c???n s???a");
         }
         if(row >=0)
         {
          String maSP = txtMaSP.getText().trim();
         String tenSP = txtTenSP.getText().trim();
         String thuongHieu = (String) cboThuongHieu.getSelectedItem();
         float donGia = Float.parseFloat(txtDonGia.getText().trim());
         int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
         String donViTinh = cboDonViTinh.getSelectedItem().toString();
         String anh = lblAnh.getText();
            
            int index = anh.lastIndexOf('\\');//l???y path 
            String name = anh.substring(index+1);
            System.out.print(name);
            String hinhAnh = "\\\\src\\\\image\\\\"+ name;  
         SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh,hinhAnh);
          
         int ques = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n s???a","Attention!",JOptionPane.YES_NO_OPTION);
         if(ques == JOptionPane.YES_OPTION){
             if(spDao.suaSanPham(sp)){
//                 themDuLieuVaoBang(listSP);
                    DocSanPhamVaoTable();
                 JOptionPane.showMessageDialog(this, "C???p nh???t th??nh c??ng");
             }
             else{
                 JOptionPane.showMessageDialog(this, "C???p nh???t th???t b???i");
             }
            }
         }
     }
//     private void suaSanPham(SanPham SanPhamNew) {
//	spDao.suaSanPham(SanPhamNew);
//       // xoaDuLieuBang();
//        ArrayList<SanPham> listDichVuNew = (ArrayList<SanPham>) spDao.getALLSP();//l???y l???i danh s??ch m???i
//        themDuLieuVaoBang(listDichVuNew);
//	JOptionPane.showMessageDialog(this,"S???a th??nh c??ng.");	
//    }
     public void xoaDuLieuBang() {
        
        modelSP.getDataVector().removeAllElements();
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
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboDonViTinh = new javax.swing.JComboBox<>();
        txtDonGia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1200, 1015));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("S???n Ph???m");

        jLabel2.setText("M?? S???n Ph???m");

        jLabel3.setText("T??n S???n Ph???m");

        jLabel4.setText("S??? L?????ng");

        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("X??a ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("S???a");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? S???n Ph???m", "T??n S???n Ph???m", "Th????ng Hi???u", "????n Gi??", "S??? L?????ng", "????n V??? T??nh", "???nh"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel5.setText("Th????ng Hi???u");

        jLabel6.setText("????n Gi??");

        jLabel7.setText("????n V??? T??nh");

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coca-Cola", "PepSi", "Sprite", "Fanta", "Sting" }));

        cboDonViTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Th??ng", "L???c", "Chai", "Lon", " " }));

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anhsp2.jpg"))); // NOI18N

        btnChonAnh.setText("Ch???n ???nh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(btnChonAnh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChonAnh)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(txtMaSP)
                            .addComponent(txtDonGia))
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboThuongHieu, 0, 127, Short.MAX_VALUE)
                            .addComponent(cboDonViTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(btnThem)
                        .addGap(58, 58, 58)
                        .addComponent(btnXoa)
                        .addGap(78, 78, 78)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(cboDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1194, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        themSP();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        clickTable();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaSP();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
           SuaSP();
          
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser f = new JFileChooser("E:\\ProJect_Salary\\src\\image");
        f.setDialogTitle("M??? file");
        f.showOpenDialog(null);
        File ftenanh = f.getSelectedFile();
        duongDanAnh = ftenanh.getAbsolutePath();
        if(duongDanAnh != null){
            lblAnh.setIcon(ResizeImage(String.valueOf(duongDanAnh)));
            System.out.println(duongDanAnh);
            
        }
        
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(this, "b???n ch??a ch???n ???nh");
            System.out.print(duongDanAnh);
        }
        
    }//GEN-LAST:event_btnChonAnhActionPerformed
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboDonViTinh;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables

   
}
