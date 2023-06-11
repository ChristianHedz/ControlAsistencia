
package com.proyectoasistencia;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

public class asistencia extends javax.swing.JFrame implements Runnable {
    Toolkit sonido = Toolkit.getDefaultToolkit();
    Thread hilo;
    
  
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    public asistencia() {
        initComponents();
        lblHora.setText(hor());
        hilo = new Thread(this);
        hilo.start();
        this.setLocationRelativeTo(null);
    }
    
        public static String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha);
    }

    public static String hor() {
        Date hora = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(hora);
    }
    
    
    public void run() {
        Thread current = Thread.currentThread();
        while (current == hilo) {
            lblHora.setText(hor());

        }
    }
    
        public void registrarAsistencia() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");        
        String fecha = fechaActual.format(formatter);

        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatterH = DateTimeFormatter.ofPattern("hh:mma");
        String hora = horaActual.format(formatterH);
        LocalTime horaEntrada = LocalTime.of(9,10);
        boolean esDespues = horaActual.isAfter(horaEntrada);
        
        if (asistenciatxt.getText().equals("")) {
            sonido.beep();
            JOptionPane.showMessageDialog(null, "INGRESA TU CLAVE DE EMPLEADO");
        } else {
            if(esDespues){
            multaRetardo();
            
        }
            try (PreparedStatement a = getConnection().prepareStatement("Insert Into asistencias(fecha_asistencia,hora_asistencia,empleado_id) VALUES (?,?,?)")){
                a.setString(1,fecha);
                a.setString(2,hora);
                a.setString(3,asistenciatxt.getText());
                a.executeUpdate();
                sonido.beep();
                JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");
                limpiar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
        
        public void multaRetardo(){
            try (PreparedStatement a = getConnection().prepareStatement("Insert Into sanciones(motivo_sancion,multa,empleado_id) VALUES (?,?,?)")){
                a.setString(1,"Retardo");
                a.setInt(2,50);
                a.setString(3,asistenciatxt.getText());
                a.executeUpdate();
                sonido.beep();
                JOptionPane.showMessageDialog(null, "Llego Tarde , Se le otorgo una multa de $50 MXN");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR , LA CLAVE NO EXISTE");
            }
        }
    
    public void limpiar(){
        asistenciatxt.setText("");
    }
    
    
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        asistenciatxt = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        asistenciabtn = new javax.swing.JButton();
        lblHora = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        asistenciatxt.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        asistenciatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asistenciatxtActionPerformed(evt);
            }
        });
        jPanel1.add(asistenciatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 260, 70));

        jButton2.setBackground(new java.awt.Color(0, 51, 153));
        jButton2.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Info.png"))); // NOI18N
        jButton2.setText("MENU PRINCIPAL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 530, 390, 110));

        asistenciabtn.setBackground(new java.awt.Color(0, 51, 153));
        asistenciabtn.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        asistenciabtn.setForeground(new java.awt.Color(255, 255, 255));
        asistenciabtn.setText("TOMAR ASISTENCIA");
        asistenciabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asistenciabtnActionPerformed(evt);
            }
        });
        jPanel1.add(asistenciabtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 320, 60));

        lblHora.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        lblHora.setForeground(new java.awt.Color(0, 0, 51));
        lblHora.setText("jLabel6");
        jPanel1.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 280, 40));

        jLabel2.setBackground(new java.awt.Color(0, 0, 153));
        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/IconAlumnos.png"))); // NOI18N
        jLabel2.setText("INGRESE SU ID DE EMPLEADO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("MAFEK");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 50, 180, 80));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/peakpx (1).jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 1060, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/girl-asistencia.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, -10, 580, 710));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/Info.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void asistenciatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asistenciatxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_asistenciatxtActionPerformed

    private void asistenciabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asistenciabtnActionPerformed

        registrarAsistencia();
    }//GEN-LAST:event_asistenciabtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Splash ob = new Splash();
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
            java.util.logging.Logger.getLogger(asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new asistencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton asistenciabtn;
    private javax.swing.JTextField asistenciatxt;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHora;
    // End of variables declaration//GEN-END:variables
}
