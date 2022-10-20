/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;
import Connect.ConnectDB1;
import dao.CongNhanDAO;
import entity.CongNhan;
import dao.PhongBanDAO;
import entity.PhongBan;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Tuan Kiet Admin
 */
public class pnCapNhatCN extends javax.swing.JPanel {
  private CongNhanDAO cndao;
  private PhongBanDAO pbdao;
    DefaultTableModel modelCN;
    /**
     * Creates new form pnCapNhatCN
     */
    public pnCapNhatCN() {
        try{
            ConnectDB1.getInstance().connect();
        }catch(Exception e){
            e.printStackTrace();
        }
        initComponents();
        cndao = new CongNhanDAO();
        pbdao = new PhongBanDAO();
        String[] header = "Mã CN;Mã PB; Tên CN;Sđt;Địa chỉ;Giới tính;Ngày sinh".split(";");
        modelCN = new DefaultTableModel(header, 0);
        jTable1.setModel(modelCN);
        
        
        readDatabase();
        readPB();
    }
      
    public void themCN(){
          SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(DateNgaySinh.getDate()));
        String maCN = txtMa.getText().trim();
        String tenCN = txtTenCn.getText().trim();
        boolean gioiTinh = cboGT.getSelectedItem()=="Nam"?true:false;
        String sdt = txtSđt.getText().trim();
        String diaChi = txtDiachi.getText().trim();
        System.out.println(DateNgaySinh.toString());
        Date ns = Date.valueOf(df.format(DateNgaySinh.getDate()));
        String pb1 = cboPB.getSelectedItem().toString();
        PhongBan pb = new PhongBan(pb1);
        List<CongNhan> lst_check = cndao.getALLCN();
        CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ns, gioiTinh, pb);
        if(lst_check.contains(cn)){
            JOptionPane.showMessageDialog(this, "TRÙNG MÃ CÔNG NHÂN");
        }else{
            if(cndao.createNV(cn)){
                Object[] row = {cn.getMaCN(),cn.getMaPB().getMaPB(),cn.getTenCN(),cn.getSđt(),cn.getDiaChi(),
                    cn.isGioiTinh()==true?"Nam":"Nữ",date.format(cn.getNgaySinh())};
                modelCN.addRow(row);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
            else{
                JOptionPane.showMessageDialog(this, "Thêm that bai");
            }
        }
        xoaTrang();
    }
 public void xoaTrang(){
        txtMa.setText("");
        txtTenCn.setText("");
        txtDiachi.setText("");
        txtSđt.setText("");
        DateNgaySinh.setDate(null);
        cboGT.setSelectedIndex(0);
        cboPB.setSelectedIndex(0);
    }
 public void clickTable(){ 
        int row = jTable1.getSelectedRow();
        txtMa.setText(modelCN.getValueAt(row, 0).toString());
        
        txtTenCn.setText(modelCN.getValueAt(row, 2).toString());
        try {
            DateNgaySinh.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String)modelCN.getValueAt(row, 6)));
        } catch (ParseException ex) {
            Logger.getLogger(pnCapNhatNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        cboGT.setSelectedItem(modelCN.getValueAt(row, 5).toString());
        txtDiachi.setText(modelCN.getValueAt(row, 4).toString());
        txtSđt.setText(modelCN.getValueAt(row, 3).toString());
        cboPB.setSelectedItem(modelCN.getValueAt(row, 1).toString());
        txtMa.setEditable(false);
        
    }
  public void xoa(){
        int row = jTable1.getSelectedRow();
	int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?", "Warning", JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            String ma = (String) jTable1.getValueAt(row, 0);
		cndao.removeCN(ma);
		readDatabase();
		}
	xoaTrang();
    }
  public void SuaCN(){
         int row = jTable1.getSelectedRow();
        //////////////////////////////////////////////
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String maCN = txtMa.getText().trim();
        String tenCN = txtTenCn.getText().trim();
        boolean gioiTinh = cboGT.getSelectedItem()=="Nam"?true:false;
        String sdt = txtSđt.getText().trim();
        String diaChi = txtDiachi.getText().trim();
        Date ns = Date.valueOf(df.format(DateNgaySinh.getDate()));
        String pb1 = cboPB.getSelectedItem().toString();
        PhongBan pb = new PhongBan(pb1);
        CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ns, gioiTinh, pb);
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa","Attention!",JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            if(cndao.capNhat(cn)){
                readDatabase();
                JOptionPane.showMessageDialog(this, "Cap nhat thanh cong");
            }else{
                JOptionPane.showMessageDialog(this, "Cap nhat that bai");
            }
        }
        xoaTrang();
        
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
        lblMaCn = new javax.swing.JLabel();
        lblSdt = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtSđt = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JLabel();
        txtPB = new javax.swing.JLabel();
        cboPB = new javax.swing.JComboBox<>();
        DateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtTenCn = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cboGT = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setText("CÔNG NHÂN");

        lblMaCn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblMaCn.setText("Mã CN:");

        lblSdt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblSdt.setText("Sđt:");

        lblDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblDiaChi.setText("Địa Chỉ");

        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Tên CN:");

        txtNgaySinh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNgaySinh.setText("Ngày Sinh");

        txtGioiTinh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtGioiTinh.setText("Giới Tính");

        txtPB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPB.setText("Phòng Ban");

        cboPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtTenCn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCnActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoaTrang.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoaTrang.setText("Xóa Trắng");
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CN", "Mã PB", "Tên CN", "Sđt", "Địa Chỉ", "Giới Tính", "Ngày Sinh"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        cboGT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSdt)
                    .addComponent(lblMaCn)
                    .addComponent(lblDiaChi))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSđt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtNgaySinh))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenCn, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtGioiTinh)
                                .addGap(24, 24, 24)
                                .addComponent(cboGT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addGap(32, 32, 32)
                        .addComponent(txtPB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 234, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaTrang)
                .addGap(173, 173, 173))
            .addGroup(layout.createSequentialGroup()
                .addGap(535, 535, 535)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenCn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaCn)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSdt)
                        .addComponent(txtSđt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgaySinh))
                    .addComponent(DateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChi)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinh)
                    .addComponent(txtPB)
                    .addComponent(cboPB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenCnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCnActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        themCN();// TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboGTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGTActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        xoa();        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
       SuaCN();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
       xoaTrang();        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       clickTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgaySinh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cboGT;
    private javax.swing.JComboBox<String> cboPB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblMaCn;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JLabel txtGioiTinh;
    private javax.swing.JTextField txtMa;
    private javax.swing.JLabel txtNgaySinh;
    private javax.swing.JLabel txtPB;
    private javax.swing.JTextField txtSđt;
    private javax.swing.JTextField txtTenCn;
    // End of variables declaration//GEN-END:variables

    private void readDatabase() {
       modelCN.setRowCount(0);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        List<CongNhan> lst = cndao.getALLCN();
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(),cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),  cn.isGioiTinh()==true?"Nam":"Nữ",date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
        System.out.println(modelCN.getRowCount());
    }


      public void readPB(){
        DefaultComboBoxModel modelPB = (DefaultComboBoxModel) cboPB.getModel();
        modelPB.removeAllElements();
        try {
            List<PhongBan> lstpb = pbdao.getAllPB();
            for (PhongBan pb : lstpb) {
                modelPB.addElement(pb.getMaPB());
            }
            cboPB.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
