
package com.proyectoasistencia;


public class Horario extends javax.swing.JFrame {


    public Horario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Clock.png"))); // NOI18N
        jLabel2.setText("ENTRADA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 150, 40));

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setForeground(new java.awt.Color(0, 102, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 0, 80, 60));

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "3 : 00 P M", "4 : 00 P M", "5 : 00 P M", "6 : 00 P M", "" }));
        jPanel1.add(jComboBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 460, 150, -1));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "3 : 00 P M", "4 : 00 P M", "5 : 00 P M", "6 : 00 P M", "" }));
        jPanel1.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, 130, -1));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "3 : 00 P M", "4 : 00 P M", "5 : 00 P M", "6 : 00 P M", "" }));
        jPanel1.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, 150, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "3 : 00 P M", "4 : 00 P M", "5 : 00 P M", "6 : 00 P M", "" }));
        jPanel1.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 460, 130, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "3 : 00 P M", "4 : 00 P M", "5 : 00 P M", "6 : 00 P M", "" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 130, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "6 : 00 A M", "7 : 00 A M", "8 : 00 A M", "9 : 00 A M" }));
        jPanel1.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 130, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "6 : 00 A M", "7 : 00 A M", "8 : 00 A M", "9 : 00 A M" }));
        jPanel1.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 150, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "6 : 00 A M", "7 : 00 A M", "8 : 00 A M", "9 : 00 A M" }));
        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 290, 130, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "6 : 00 A M", "7 : 00 A M", "8 : 00 A M", "9 : 00 A M" }));
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 290, 140, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORA", "6 : 00 A M", "7 : 00 A M", "8 : 00 A M", "9 : 00 A M" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 110, -1));

        jButton3.setBackground(new java.awt.Color(0, 51, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cancelar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 540, 110, 100));

        jButton2.setBackground(new java.awt.Color(0, 51, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/disquete.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 540, 110, 100));

        jLabel9.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/calendario.png"))); // NOI18N
        jLabel9.setText("JUEVES");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 130, 60));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/calendario.png"))); // NOI18N
        jLabel5.setText("LUNES");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 130, 60));

        jLabel8.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/calendario.png"))); // NOI18N
        jLabel8.setText("MIERCOLES");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 180, 60));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/calendario.png"))); // NOI18N
        jLabel6.setText("VIERNES");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 130, 140, 60));

        jLabel7.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/calendario.png"))); // NOI18N
        jLabel7.setText("LUNES");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 130, 60));

        jLabel4.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/calendario.png"))); // NOI18N
        jLabel4.setText("MARTES");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 130, 60));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Clock.png"))); // NOI18N
        jLabel3.setText("SALIDA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 160, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/material-design-blue-and-white.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuEmpleados ob = new MenuEmpleados();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Horario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Horario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Horario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Horario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Horario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
