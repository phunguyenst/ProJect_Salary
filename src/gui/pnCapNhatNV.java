    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import Connect.ConnectDB1;
import dao.NhanVienHCDAO;
import dao.PhongBanDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Entity.NhanVien;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.ObjectOutput;
import javax.swing.JComboBox;
import Entity.PhongBan;
import javax.swing.DefaultComboBoxModel;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author ADMIN
 */
public class pnCapNhatNV extends javax.swing.JPanel {
    private NhanVienHCDAO nvhcdao;
    private PhongBanDAO pbdao;
    DefaultTableModel modelNV;
    /**
     * Creates new form pnCapNhatNV
     */
    public pnCapNhatNV() {
        try{
            ConnectDB1.getInstance().connect();
        }catch(Exception e){
            e.printStackTrace();
        }
        initComponents();
        nvhcdao = new NhanVienHCDAO();
        pbdao = new PhongBanDAO();
        String[] header = "Mã NV;Tên NV; Ngày sinh; Ngày TGCT;giới tính;địa chỉ;SĐT;Mã PB;trình độ".split(";");
        modelNV = new DefaultTableModel(header, 0);
        tableNV.setModel(modelNV);
        
        
        readDatabase();
        readPB();
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
    public void readDatabase(){
        modelNV.setRowCount(0);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        List<NhanVien> lst = nvhcdao.getALLNV();
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo()};
            modelNV.addRow(row);
        }
        System.out.println(modelNV.getRowCount());
    }
    
    public void themNV(){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(DateNgSinh.getDate()));
        String maNV = txtMaNV.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        boolean gioiTinh = cboGioiTinh.getSelectedItem()=="Nam"?true:false;
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        System.out.println(DateNgSinh.toString());
        Date ns = Date.valueOf(df.format(DateNgSinh.getDate()));
        Date nCT = Date.valueOf(df.format(DateNgCT.getDate()));
        String trinhDo = cboTrinhDo.getSelectedItem().toString();
        String pb1 = cboPB.getSelectedItem().toString();
        PhongBan pb = new PhongBan(pb1);
        List<NhanVien> lst_check = nvhcdao.getALLNV();
        NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ns, nCT, gioiTinh, pb);
        if(lst_check.contains(nv)){
            JOptionPane.showMessageDialog(this, "TRÙNG MÃ NHÂN VIÊN");
        }else{
            if(nvhcdao.createNV(nv)){
                Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                    nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo()};
                modelNV.addRow(row);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
            else{
                JOptionPane.showMessageDialog(this, "Thêm that bai");
            }
        }
        xoaTrang();
    }
    
    public void xoaTrang(){
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        DateNgSinh.setDate(null);
        DateNgCT.setDate(null);
        cboGioiTinh.setSelectedIndex(0);
        cboPB.setSelectedIndex(0);
        cboTrinhDo.setSelectedIndex(0);
        txtMaNV.setEditable(true);
        txtMaNV.setEnabled(true);
    }
    
    public void clickTable(){ 
        int row = tableNV.getSelectedRow();
        txtMaNV.setText(modelNV.getValueAt(row, 0).toString());
        txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
        try {
            DateNgSinh.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String)modelNV.getValueAt(row, 2)));
            DateNgCT.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String)modelNV.getValueAt(row, 3)));
        } catch (ParseException ex) {
            Logger.getLogger(pnCapNhatNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        cboGioiTinh.setSelectedItem(modelNV.getValueAt(row, 4).toString());
        txtDiaChi.setText(modelNV.getValueAt(row, 5).toString());
        txtSDT.setText(modelNV.getValueAt(row, 6).toString());
        cboPB.setSelectedItem(modelNV.getValueAt(row, 7).toString());
        cboTrinhDo.setSelectedItem(modelNV.getValueAt(row, 8).toString());
        txtMaNV.setEditable(false);
        txtMaNV.setEditable(false);
        
    }
    
    public void xoa(){
        int row = tableNV.getSelectedRow();
	int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?", "Warning", JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            String ma = (String) tableNV.getValueAt(row, 0);
		nvhcdao.removeNV(ma);
		readDatabase();
		}
	xoaTrang();
    }
    
    public void SuaNV(){
        int row = tableNV.getSelectedRow();
        //////////////////////////////////////////////
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String maNV = txtMaNV.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        boolean gioiTinh = cboGioiTinh.getSelectedItem()=="Nam"?true:false;
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        System.out.println(DateNgSinh.toString());
        Date ns = Date.valueOf(df.format(DateNgSinh.getDate()));
        Date nCT = Date.valueOf(df.format(DateNgCT.getDate()));
        String trinhDo = cboTrinhDo.getSelectedItem().toString();
        String pb1 = cboPB.getSelectedItem().toString();
        PhongBan pb = new PhongBan(pb1);
        NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ns, nCT, gioiTinh, pb);
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa","Attention!",JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            if(nvhcdao.capNhat(nv)){
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
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboPB = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        DateNgSinh = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        DateNgCT = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboTrinhDo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();
        btnSua = new custom_button.MyButton();
        btnThem = new custom_button.MyButton();
        btnXoa = new custom_button.MyButton();
        jLabel10 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setText("NHÂN VIÊN HÀNH CHÍNH");
        add(jLabel1);
        jLabel1.setBounds(500, 26, 310, 26);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Mã NV:");
        add(jLabel2);
        jLabel2.setBounds(280, 110, 50, 17);

        txtMaNV.setText("NV01");
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        add(txtMaNV);
        txtMaNV.setBounds(350, 100, 190, 30);

        txtTenNV.setText("Nguyễn Anh Tuấn");
        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });
        add(txtTenNV);
        txtTenNV.setBounds(670, 100, 290, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Họ tên:");
        add(jLabel3);
        jLabel3.setBounds(610, 110, 48, 17);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Giới tính:");
        add(jLabel4);
        jLabel4.setBounds(280, 150, 60, 17);

        cboPB.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cboPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QL", "HC" }));
        cboPB.setPreferredSize(new java.awt.Dimension(64, 21));
        add(cboPB);
        cboPB.setBounds(860, 220, 100, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ:");
        add(jLabel5);
        jLabel5.setBounds(613, 151, 48, 17);

        txtDiaChi.setText("Gò Vâp, TP HCM");
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        add(txtDiaChi);
        txtDiaChi.setBounds(673, 140, 290, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Ngày sinh:");
        add(jLabel6);
        jLabel6.setBounds(280, 200, 80, 17);

        DateNgSinh.setDateFormatString("yyyy-MM-dd");
        add(DateNgSinh);
        DateNgSinh.setBounds(350, 190, 138, 30);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Ngày tham gia CT:");
        add(jLabel7);
        jLabel7.setBounds(280, 240, 120, 17);

        DateNgCT.setDateFormatString("yyyy-MM-dd");
        add(DateNgCT);
        DateNgCT.setBounds(399, 229, 138, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("SÐT:");
        add(jLabel8);
        jLabel8.setBounds(613, 192, 48, 17);

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        add(txtSDT);
        txtSDT.setBounds(673, 181, 97, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Phòng ban:");
        add(jLabel9);
        jLabel9.setBounds(780, 230, 70, 17);

        cboTrinhDo.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trung cấp", "Cao đẳng", "Đại học" }));
        add(cboTrinhDo);
        cboTrinhDo.setBounds(675, 221, 95, 30);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1200, 502));

        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên NV ", "Ngày sinh", "Ngày TGCT", "Giới tính", "Địa chỉ", "SÐT", "Mã PB", "Trình độ"
            }
        ));
        tableNV.setPreferredSize(new java.awt.Dimension(1250, 480));
        tableNV.setRowHeight(40);
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableNV);

        add(jScrollPane2);
        jScrollPane2.setBounds(0, 362, 1200, 440);

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
        btnSua.setBounds(780, 300, 110, 40);

        btnThem.setText("Thêm");
        btnThem.setBorderColor(new java.awt.Color(102, 102, 102));
        btnThem.setColorhover(new java.awt.Color(0, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setRadius(30);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem);
        btnThem.setBounds(340, 300, 110, 40);

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
        btnXoa.setBounds(560, 300, 110, 40);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Trình độ");
        add(jLabel10);
        jLabel10.setBounds(613, 231, 60, 17);

        cboGioiTinh.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setPreferredSize(new java.awt.Dimension(64, 21));
        add(cboGioiTinh);
        cboGioiTinh.setBounds(350, 140, 88, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        SuaNV();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themNV();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoa();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        // TODO add your handling code here:
        clickTable();
    }//GEN-LAST:event_tableNVMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgCT;
    private com.toedter.calendar.JDateChooser DateNgSinh;
    private custom_button.MyButton btnSua;
    private custom_button.MyButton btnThem;
    private custom_button.MyButton btnXoa;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboPB;
    private javax.swing.JComboBox<String> cboTrinhDo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
