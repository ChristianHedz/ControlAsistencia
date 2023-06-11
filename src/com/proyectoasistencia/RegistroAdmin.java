
package com.proyectoasistencia;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;
public class RegistroAdmin extends javax.swing.JFrame {
    
    Toolkit sonido = Toolkit.getDefaultToolkit();
    
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    public RegistroAdmin() {
        initComponents();
    }
    
        public void registrarAdmin() {
        if (correoA.getText().equals("")
                || usuarioA.getText().equals("")
                || passwordA.getText().equals("")) {
            sonido.beep();
            JOptionPane.showMessageDialog(null, "FALTAN CAMPOS POR LLENAR");
        } else {
            try (PreparedStatement a = getConnection().prepareStatement("Insert Into administrador(correo,usuario,contrasena) VALUES (?,?,?)")){
                a.setString(1,correoA.getText());
                a.setString(2, usuarioA.getText());
                a.setString(3, passwordA.getText());
                a.executeUpdate();
                sonido.beep();
                JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");
                limpiar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR");
            }
        }
    }
    
    
        public void limpiar(){
            correoA.setText("");
            usuarioA.setText("");
            passwordA.setText("");
        }   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        passwordA = new javax.swing.JPasswordField();
        usuarioA = new javax.swing.JTextField();
        correoA = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 255));
        jLabel4.setText("MAFEK");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 190, 120));

        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("REGISTRO DE ADMINISTRADOR");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("CONTRASEÑA :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 180, -1));

        jButton2.setBackground(new java.awt.Color(0, 51, 153));
        jButton2.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("REGISTRAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 600, 330, 40));

        jLabel8.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("CORREO : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, -1, -1));

        jLabel12.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("USUARIO:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 130, -1));
        jPanel1.add(passwordA, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 200, 30));

        usuarioA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioAActionPerformed(evt);
            }
        });
        jPanel1.add(usuarioA, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 200, 30));
        jPanel1.add(correoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 480, 200, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/peakpx (1).jpg"))); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-240, -10, 1070, 740));

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, 90, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/registro.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 530, 740));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Info.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1356, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Registro ob = new Registro();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        registrarAdmin();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void usuarioAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioAActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RegistroAdmin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField correoA;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordA;
    private javax.swing.JTextField usuarioA;
    // End of variables declaration//GEN-END:variables

}

