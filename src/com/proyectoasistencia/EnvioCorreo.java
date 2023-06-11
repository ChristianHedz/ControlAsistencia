
package com.proyectoasistencia;

import java.awt.Toolkit;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



public class EnvioCorreo extends javax.swing.JFrame {
    
    Toolkit sonido = Toolkit.getDefaultToolkit();

    private static String emailFrom = "cristianhedz777@gmail.com";
    private static String passwordFrom = "bngoycocfiwhgokv";
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    
    private File[] mArchivosAdjuntos;
    private String nombres_archivos;

    public EnvioCorreo() {
        initComponents();
        mProperties = new Properties();
        nombres_archivos = "";
    }

    private void createEmail() {
        emailTo = txtTo.getText().trim();
        subject = txtSubject.getText().trim();
        content = txtContent.getText().trim();
        
         // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user",emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
        
        
        try {
            MimeMultipart mElementosCorreo = new MimeMultipart();
            // Contenido del correo
            MimeBodyPart mContenido = new MimeBodyPart();
            mContenido.setContent(content, "text/html; charset=utf-8");
            mElementosCorreo.addBodyPart(mContenido);
            
            //Agregar archivos adjuntos.
            MimeBodyPart mAdjuntos = null;
            for (int i = 0; i < mArchivosAdjuntos.length; i++) {
                mAdjuntos = new MimeBodyPart();
                mAdjuntos.setDataHandler(new DataHandler(new FileDataSource(mArchivosAdjuntos[i].getAbsolutePath())));
                mAdjuntos.setFileName(mArchivosAdjuntos[i].getName());
                mElementosCorreo.addBodyPart(mAdjuntos);
            }
            
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(mElementosCorreo);
        } catch (AddressException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            sonido.beep();
            JOptionPane.showMessageDialog(null, "Correo enviado");
            lblAdjuntos.setText("hola");
            nombres_archivos = "";
            limpiar();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limpiar(){
        txtTo.setText("");
        txtSubject.setText("");
        txtContent.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTo = new javax.swing.JTextField();
        txtSubject = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        btnAdjuntos = new javax.swing.JButton();
        btnSendEmail = new javax.swing.JButton();
        lblAdjuntos = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtToActionPerformed(evt);
            }
        });
        getContentPane().add(txtTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 320, 40));
        getContentPane().add(txtSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 320, 40));

        txtContent.setColumns(20);
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 310, 330, 220));

        btnAdjuntos.setBackground(new java.awt.Color(0, 0, 153));
        btnAdjuntos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnAdjuntos.setForeground(new java.awt.Color(255, 255, 255));
        btnAdjuntos.setText("AGREGAR ARCHIVOS ADJUNTOS");
        btnAdjuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdjuntosActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdjuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, 340, 40));

        btnSendEmail.setBackground(new java.awt.Color(0, 0, 153));
        btnSendEmail.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSendEmail.setForeground(new java.awt.Color(255, 255, 255));
        btnSendEmail.setText("ENVIAR CORREO");
        btnSendEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendEmailActionPerformed(evt);
            }
        });
        getContentPane().add(btnSendEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 630, 340, 40));

        lblAdjuntos.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lblAdjuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, 170, 30));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("CONTENIDO : ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("ASUNTO : ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 102));
        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("ENVIO DE CORREO ELECTRONICO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 790, 60));

        jLabel4.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("ENVIAR A :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 130, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/peakpx (1).jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-290, 0, 1260, 720));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 130, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/correo-electronico.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 730, 730));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 390, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 410, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtToActionPerformed

    private void btnAdjuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdjuntosActionPerformed
       JFileChooser chooser = new JFileChooser();
       chooser.setMultiSelectionEnabled(true);
       chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
       
        if (chooser.showOpenDialog(this) != JFileChooser.CANCEL_OPTION) {
            mArchivosAdjuntos = chooser.getSelectedFiles();
            
            for (File archivo : mArchivosAdjuntos) {
                nombres_archivos += archivo.getName() + "<br>";
            }
            
            lblAdjuntos.setText("<html><p>" + nombres_archivos + "</p></html>");
        }
    }//GEN-LAST:event_btnAdjuntosActionPerformed

    private void btnSendEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendEmailActionPerformed
        createEmail();
        sendEmail();
    }//GEN-LAST:event_btnSendEmailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Administrador a = new Administrador();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(EnvioCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnvioCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnvioCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnvioCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnvioCorreo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdjuntos;
    private javax.swing.JButton btnSendEmail;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdjuntos;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}
