/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import Connect.ConnectDB1;
import dao.CongNhanDAO;
import dao.PhongBanDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import entity.CongNhan;
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

/**
 *
 * @author Tuan Kiet Admin
 */
public class TimKiemCN extends javax.swing.JPanel {

    private CongNhanDAO cndao;
    private PhongBanDAO pbdao;
    DefaultTableModel modelCN;

    /**
     * Creates new form TimKiemCN
     */
    public TimKiemCN() {
        try {
            ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        cndao = new CongNhanDAO();
        pbdao = new PhongBanDAO();
        String[] header = "Mã Cn;Mã PB;Tên CN;Sđt;Địa Chỉ;Giới Tính;Ngày Sinh".split(";");
        modelCN = new DefaultTableModel(header, 0);
        jTable1.setModel(modelCN);
        loadComboBoxMa();
        loadComboBoxGT();
        loadComboBoxPB();
    }

    public void locCN() {
        if (cboMa.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboMa.getSelectedItem().equals("")) {
            modelCN.setRowCount(0);
        } else {
            String txtMa = cboMa.getSelectedItem().toString();
            List<CongNhan> lst = cndao.findCNbyMaCN(txtMa);
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            modelCN.setRowCount(0);
            for (CongNhan cn : lst) {
                Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                    cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                modelCN.addRow(row);
            }
        }
    }

    public void locCNbyGT() {
        if (cboGT.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboGT.getSelectedItem().equals("")) {
            modelCN.setRowCount(0);
        } else {
            if (cboGT.getSelectedItem().equals("Nam")) {
                int check = 1;
                List<CongNhan> lst = cndao.findCNbyGT(check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            } else {
                int check = 0;
                List<CongNhan> lst = cndao.findCNbyGT(check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            }
        }
    }

    public void locCNPB() {
        if (cboPB.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboPB.getSelectedItem().equals("")) {
            modelCN.setRowCount(0);
        } else {
            String txtPB = cboPB.getSelectedItem().toString();
            List<CongNhan> lst = cndao.findCNbyPB(txtPB);
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            modelCN.setRowCount(0);
            for (CongNhan cn : lst) {
                Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                    cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                modelCN.addRow(row);
            }
        }
    }

    public void locPBvsGT() {
        if (cboGT.getSelectedItem().equals("Tất cả") && cboPB.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboGT.getSelectedItem().equals("Tất cả")) {
            locCNPB();
        } else if (cboPB.getSelectedItem().equals("Tất cả")) {
            locCNbyGT();
        } else {
            String txtPB = cboPB.getSelectedItem().toString();
            if (cboGT.getSelectedItem().equals("Nam")) {
                int check = 1;
                List<CongNhan> lst = cndao.findPBvsGT(txtPB, check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            } else {
                int check = 0;
                List<CongNhan> lst = cndao.findPBvsGT(txtPB, check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            }
        }
    }

    public void findTenCN() {
        String txtTen = this.txtTen.getText().toString().trim();
        List<CongNhan> lst = cndao.findTenCN(txtTen);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelCN.setRowCount(0);
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
    }

    public void findSdt() {
        String txtsdt = txtSđt.getText().toString().trim();
        List<CongNhan> lst = cndao.findSDT(txtsdt);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelCN.setRowCount(0);
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
    }

    public void findDiaChi() {
        String txtDiachi = txtDiaChi.getText().toString().trim();
        List<CongNhan> lst = cndao.findDiaChi(txtDiachi);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelCN.setRowCount(0);
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
    }

    public void xoaRong() {
        cboMa.setSelectedIndex(0);
        cboGT.setSelectedIndex(0);
        cboPB.setSelectedIndex(0);
        txtDiaChi.setText("");
        txtTen.setText("");
        txtSđt.setText("");
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboMa = new javax.swing.JComboBox<>();
        txtSđt = new javax.swing.JTextField();
        cboGT = new javax.swing.JComboBox<>();
        txtTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        cboPB = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setText("TÌM KIẾM CÔNG NHÂN");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Mã CN:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Sđt:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Giới Tính:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Tên CN:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Địa Chỉ:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Phòng Ban:");

        cboMa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMaMouseClicked(evt);
            }
        });
        cboMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaActionPerformed(evt);
            }
        });

        txtSđt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSđtMouseClicked(evt);
            }
        });
        txtSđt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSđtActionPerformed(evt);
            }
        });

        cboGT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboGTMouseClicked(evt);
            }
        });

        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenMouseClicked(evt);
            }
        });
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtDiaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDiaChiMouseClicked(evt);
            }
        });
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        cboPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPBActionPerformed(evt);
            }
        });

        btnLoc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnTim.setText("Tìm Kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(474, 474, 474)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(cboMa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(txtSđt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboGT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                            .addComponent(txtDiaChi)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(cboPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)
                                .addComponent(btnXoaTrang)))))
                .addContainerGap(267, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(cboMa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSđt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboGT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboPB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if (!cboMa.getSelectedItem().equals("")) {
            locCN();
        } else if (!cboGT.getSelectedItem().equals("") && cboPB.getSelectedItem().equals("")) {
            locCNbyGT();
        } else if (!cboPB.getSelectedItem().equals("") && cboGT.getSelectedItem().equals("")) {
            locCNPB();
        } else if (!cboPB.getSelectedItem().equals("") && !cboGT.getSelectedItem().equals("")) {
            locPBvsGT();
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        // TODO add your handling code here:
        xoaRong();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void cboMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaActionPerformed

    private void cboMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMaMouseClicked
        // TODO add your handling code here:
        cboGT.setSelectedItem("");
        cboPB.setSelectedItem("");
    }//GEN-LAST:event_cboMaMouseClicked

    private void cboGTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboGTMouseClicked
        // TODO add your handling code here:
        cboMa.setSelectedItem("");
    }//GEN-LAST:event_cboGTMouseClicked

    private void cboPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPBActionPerformed
        // TODO add your handling code here:
        cboMa.setSelectedItem("");
    }//GEN-LAST:event_cboPBActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if ((!txtSđt.getText().equals("")) && (txtTen.getText().equals("") && txtDiaChi.getText().equals(""))) {
            findSdt();
        }
        if ((!txtTen.getText().equals("")) && (txtSđt.getText().equals("") && txtDiaChi.getText().equals(""))) {
            findTenCN();
        }
        if ((!txtDiaChi.getText().equals("")) && (txtTen.getText().equals("") && txtSđt.getText().equals(""))) {
            findDiaChi();
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtSđtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSđtActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSđtActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTenActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSđtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSđtMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtTen.setText("");
    }//GEN-LAST:event_txtSđtMouseClicked

    private void txtTenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSđt.setText("");
    }//GEN-LAST:event_txtTenMouseClicked

    private void txtDiaChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiaChiMouseClicked
        // TODO add your handling code here:
        txtTen.setText("");
        txtSđt.setText("");
    }//GEN-LAST:event_txtDiaChiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cboGT;
    private javax.swing.JComboBox<String> cboMa;
    private javax.swing.JComboBox<String> cboPB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtSđt;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    private void loadComboBoxMa() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMa.getModel();
        model.removeAllElements();
        model.addElement("");
        model.addElement("Tất cả");
        List<CongNhan> lst = cndao.getALLCN();
        for (CongNhan cn : lst) {
            model.addElement(cn.getMaCN());
        }
        cboMa.setSelectedIndex(0);
    }

    private void loadComboBoxGT() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboGT.getModel();
        model.removeAllElements();
        model.addElement("");
        model.addElement("Tất cả");
        model.addElement("Nam");
        model.addElement("Nữ");
        cboGT.setSelectedIndex(0);
    }

    private void loadComboBoxPB() {
        DefaultComboBoxModel modelPB = (DefaultComboBoxModel) cboPB.getModel();
        modelPB.removeAllElements();
        modelPB.addElement("");
        modelPB.addElement("Tất cả");
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

    public void readDatabase() {
        modelCN.setRowCount(0);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        List<CongNhan> lst = cndao.getALLCN();
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
        System.out.println(modelCN.getRowCount());
    }

}
