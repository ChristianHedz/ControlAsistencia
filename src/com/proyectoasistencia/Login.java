
package com.proyectoasistencia;

import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Login extends javax.swing.JFrame {
    
    Toolkit sonido = Toolkit.getDefaultToolkit();
    
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }
    
    public Login() {
        initComponents();
    }
    
public void validarLogin() throws SQLException {
    if (usuario.getText().isEmpty() || password.getText().isEmpty()) {
        sonido.beep();
        JOptionPane.showMessageDialog(null, "FALTAN CAMPOS POR LLENAR");
    } else {
        String query = "SELECT usuario, contrasena FROM administrador WHERE usuario = ? AND contrasena = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, usuario.getText());
            statement.setString(2, password.getText());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    sonido.beep();
                    JOptionPane.showMessageDialog(null, "INICIO EXITOSO!");
                    MenuEmpleados ob = new MenuEmpleados();
                    ob.setVisible(true);
                    dispose();
                } else {
                    sonido.beep();
                    JOptionPane.showMessageDialog(null, "DATOS NO VALIDOS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        usuario = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 51, 153));
        jButton1.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("INGRESAR");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 270, 60));

        jButton2.setBackground(new java.awt.Color(0, 51, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 110, 100));
        jPanel1.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 310, 30));
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 372, 310, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/material-design-blue-and-white.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 830, 100));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/material-design-blue-and-white.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 620, 830, 90));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/material-design-blue-and-white.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 710));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/material-design-blue-and-white.jpg"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 310, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/LOGIN.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            validarLogin();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Inicio ob = new Inicio();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
