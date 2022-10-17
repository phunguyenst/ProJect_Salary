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
import entity.NhanVien;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.ObjectOutput;
import javax.swing.JComboBox;
import entity.PhongBan;
import javax.swing.DefaultComboBoxModel;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import dao.ChamCongNVDAO;
import entity.BangCongNV;
/**
 *
 * @author ADMIN
 */
public class ChamCongNV extends javax.swing.JPanel {
    ChamCongNVDAO chamCongdao;
    DefaultTableModel modelNV;
    DefaultTableModel modelChamCong;
    /**
     * Creates new form ChamCongNV
     */
    public ChamCongNV() {
        initComponents();
        try {
          ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chamCongdao = new ChamCongNVDAO();
        modelNV = (DefaultTableModel) tblNV.getModel();
        readTableNV();
        modelChamCong = (DefaultTableModel) tblCong.getModel();
        readTableChamCong();
        //clickTableChamCong();
        
    }

    public void readTableNV(){
        modelNV.setRowCount(0);
        List<NhanVien> lst = chamCongdao.getNVChamCong();
        //SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),nv.getMaPB().getMaPB(),nv.getSdt()};
            modelNV.addRow(row);
        }
        System.out.println(modelNV.getRowCount());
    }
    
    public void clickTable(){
        int row = tblNV.getSelectedRow();
        txtMaNv.setText(modelNV.getValueAt(row, 0).toString());
        txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
        txtSDT.setText(modelNV.getValueAt(row, 3).toString());
        txtPB.setText(modelNV.getValueAt(row, 2).toString());
        
    }
    
    
    
    public void ktraChk(){
        if(chkDiLam.isSelected()==true && chkPhep.isSelected()==true){
            JOptionPane.showMessageDialog(this, "không hop le");
            chkDiLam.setSelected(false);
            chkPhep.setSelected(false);
        }
        
        if(chkPhep.isSelected()==true && chkTangCa.isSelected()==true){
            JOptionPane.showMessageDialog(this, "Không hop le");
            chkPhep.setSelected(false);
            chkTangCa.setSelected(false);
        }
        
        if(chkTangCa.isSelected()==true && chkDiLam.isSelected()==false){
            JOptionPane.showMessageDialog(this, "Không hop le");
            chkTangCa.setSelected(false);
        }
        
    }
    
    public void readTableChamCong(){
        modelChamCong.setRowCount(0);
        List<BangCongNV> lst = chamCongdao.getAllBC();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        for (BangCongNV nv : lst) {
            Object[] row = {nv.getMaCong(),nv.getMaNhanVien().getMaNV(),nv.getTenNhanVien().getHoTen(),date.format(nv.getNgayCham()),
                            nv.isDiLam(),nv.isNghiPhep(),nv.isTangCa()};
            modelChamCong.addRow(row);
        }
        System.out.println(modelChamCong.getRowCount());
    }
    
    public void clickTableChamCong(){
        int row = tblCong.getSelectedRow();
        txtMaNv.setText(modelChamCong.getValueAt(row, 1).toString());
        txtTenNV.setText(modelChamCong.getValueAt(row, 2).toString());
        try {
            DateChamCong.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String)modelChamCong.getValueAt(row, 3)));
            
        } catch (ParseException ex) {
            Logger.getLogger(pnCapNhatNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(modelChamCong.getValueAt(row, 4).toString());
        if(modelChamCong.getValueAt(row, 4).toString().equals("true")){
           chkDiLam.setSelected(true); 
        }
        else{
            chkDiLam.setSelected(false);
        }
        
        if(modelChamCong.getValueAt(row, 5).toString().equals("true")){
            chkPhep.setSelected(true);
        }
        else{
            chkPhep.setSelected(false);
        }
        if(modelChamCong.getValueAt(row, 6).toString().equals("true")){
            chkTangCa.setSelected(true);
        }
        else{
            chkTangCa.setSelected(false);
        }
        
        txtPB.setText("");
        txtSDT.setText("");
    }
    
    public void phanCong(){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(DateChamCong.getDate()));
        String maNV = txtMaNv.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        Date ns = Date.valueOf(df.format(DateChamCong.getDate()));
        boolean diLam = chkDiLam.isSelected();
        boolean nghiphep = chkPhep.isSelected();
        boolean tangca = chkTangCa.isSelected();
        NhanVien nv = new NhanVien(maNV, tenNV);
        //List<NhanVien> lst_check = nvhcdao.getALLNV();
        BangCongNV bcnv = new BangCongNV(nv, nv, ns, diLam, nghiphep, tangca);
        if(chamCongdao.chamCongNV(bcnv)){
            Object[] row = {bcnv.getMaCong(),bcnv.getMaNhanVien().getMaNV(),bcnv.getTenNhanVien().getHoTen(),date.format(bcnv.getNgayCham()),
                            bcnv.isDiLam(),bcnv.isNghiPhep(),bcnv.isTangCa()};
            modelChamCong.addRow(row);
            JOptionPane.showMessageDialog(this, "Thêm thành công");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtMaNv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPB = new javax.swing.JTextField();
        DateChamCong = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        chkDiLam = new javax.swing.JCheckBox();
        chkPhep = new javax.swing.JCheckBox();
        chkTangCa = new javax.swing.JCheckBox();
        myButton4 = new custom_button.MyButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCong = new javax.swing.JTable();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("CHẤM CÔNG NHÂN VIÊN");
        add(jLabel1);
        jLabel1.setBounds(460, 10, 307, 45);

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ Tên", "Phòng ban", "SÐT"
            }
        ));
        tblNV.setPreferredSize(new java.awt.Dimension(300, 400));
        tblNV.setRowHeight(30);
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNV);

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 60, 570, 220);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Ngày chấm");
        add(jLabel2);
        jLabel2.setBounds(900, 80, 80, 30);

        txtMaNv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMaNv.setText("NV01");
        txtMaNv.setEnabled(false);
        add(txtMaNv);
        txtMaNv.setBounds(680, 80, 140, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("Họ Tên:");
        add(jLabel3);
        jLabel3.setBounds(620, 140, 60, 30);

        txtTenNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenNV.setText("Nguyễn Anh Tuấn");
        txtTenNV.setEnabled(false);
        add(txtTenNV);
        txtTenNV.setBounds(680, 140, 200, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel4.setText("SÐT:");
        add(jLabel4);
        jLabel4.setBounds(620, 190, 60, 30);

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSDT.setText("0392887340");
        txtSDT.setEnabled(false);
        add(txtSDT);
        txtSDT.setBounds(680, 190, 200, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("PB:");
        add(jLabel5);
        jLabel5.setBounds(620, 240, 40, 30);

        txtPB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPB.setText("HC");
        txtPB.setEnabled(false);
        add(txtPB);
        txtPB.setBounds(680, 240, 60, 30);

        DateChamCong.setDateFormatString("yyyy-MM-dd");
        add(DateChamCong);
        DateChamCong.setBounds(990, 80, 138, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Mã NV:");
        add(jLabel6);
        jLabel6.setBounds(620, 80, 60, 30);

        chkDiLam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkDiLam.setText("Có mặt/Vắng mặt");
        chkDiLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiLamActionPerformed(evt);
            }
        });
        add(chkDiLam);
        chkDiLam.setBounds(1000, 140, 150, 30);

        chkPhep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkPhep.setText("Có Phép");
        chkPhep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPhepActionPerformed(evt);
            }
        });
        add(chkPhep);
        chkPhep.setBounds(1000, 180, 110, 30);

        chkTangCa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkTangCa.setText("Tăng ca");
        chkTangCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTangCaActionPerformed(evt);
            }
        });
        add(chkTangCa);
        chkTangCa.setBounds(1000, 220, 85, 24);

        myButton4.setText("Chấm công");
        myButton4.setBorderColor(new java.awt.Color(102, 102, 102));
        myButton4.setColorhover(new java.awt.Color(0, 255, 255));
        myButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        myButton4.setRadius(30);
        myButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton4ActionPerformed(evt);
            }
        });
        add(myButton4);
        myButton4.setBounds(820, 300, 120, 50);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1250, 402));

        tblCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Công", "Mã NV", "Họ Tên", "Ngày Chấm", "Có mặt", "Nghỉ phép", "Tăng ca"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCong.setPreferredSize(new java.awt.Dimension(1250, 400));
        tblCong.setRowHeight(30);
        tblCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCong);

        add(jScrollPane2);
        jScrollPane2.setBounds(0, 382, 1200, 320);
    }// </editor-fold>//GEN-END:initComponents

    private void chkDiLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiLamActionPerformed
        // TODO add your handling code here:
        ktraChk();
    }//GEN-LAST:event_chkDiLamActionPerformed

    private void myButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton4ActionPerformed
        // TODO add your handling code here:
        phanCong();
    }//GEN-LAST:event_myButton4ActionPerformed

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        // TODO add your handling code here:
        clickTable();
        
    }//GEN-LAST:event_tblNVMouseClicked

    private void chkPhepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPhepActionPerformed
        // TODO add your handling code here:
        ktraChk();
    }//GEN-LAST:event_chkPhepActionPerformed

    private void chkTangCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTangCaActionPerformed
        // TODO add your handling code here:
        ktraChk();
    }//GEN-LAST:event_chkTangCaActionPerformed

    private void tblCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCongMouseClicked
        // TODO add your handling code here:
        clickTableChamCong();
    }//GEN-LAST:event_tblCongMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChamCong;
    private javax.swing.JCheckBox chkDiLam;
    private javax.swing.JCheckBox chkPhep;
    private javax.swing.JCheckBox chkTangCa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private custom_button.MyButton myButton4;
    private javax.swing.JTable tblCong;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtMaNv;
    private javax.swing.JTextField txtPB;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
