
package com.proyectoasistencia;

import com.itextpdf.text.Paragraph;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InfoAdministrador extends javax.swing.JFrame {
    
    
    
    Toolkit sonido = Toolkit.getDefaultToolkit();
    Paragraph titulo;
    
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    public InfoAdministrador() {
        initComponents();
        this.setLocationRelativeTo(null);
        mostrar();
    }

        public void mostrar() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("USUARIO");
        modelo.addColumn("CONTRASEÑA");
        modelo.addColumn("CORREO");

        tabla1.setModel(modelo);
        
        String sql = "Select a.id_administrador, a.usuario, a.contrasena, a.correo from administrador as a"; 
        
        String administrador[] = new String[5];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery()) {

            while (rs.next()) {
                administrador[0] = rs.getString(1);
                administrador[1] = rs.getString(2);
                administrador[2] = rs.getString(3);
                administrador[3] = rs.getString(4);

                modelo.addRow(administrador);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
    }
        
        
        public void editar() {
        if (correo.getText().equals("")
                || contrasena.getText().equals("")
                || usuario.getText().equals("")) {
            sonido.beep();
            JOptionPane.showMessageDialog(null, "FALTAN CAMPOS POR LLENAR");
        } else {
            int fila = tabla1.getSelectedRow();
            String codigo = tabla1.getValueAt(fila, 0).toString();
            String sql = "Update administrador set usuario = ? , contrasena = ? , correo = ? where id_administrador = ? ";
            try (PreparedStatement a = getConnection().prepareStatement(sql)){
                a.setString(1, usuario.getText());
                a.setString(2, contrasena.getText());
                a.setString(3, correo.getText());
                a.setString(4,codigo);
                a.executeUpdate();
                sonido.beep();
                JOptionPane.showMessageDialog(null, "REGISTRO EDITADO CON EXITO");
                limpiar();
                mostrar();
            } catch (SQLException ex) {
                sonido.beep();
                JOptionPane.showMessageDialog(null, "ERROR AL EDITAR");
            }
        }
    }
        
        public void eliminar() {
        
        if (correo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "SELECCIONE EL REGISTRO A ELIMINAR");
        } else {
            int fila = tabla1.getSelectedRow();
            String codigo = tabla1.getValueAt(fila, 0).toString();
            sonido.beep();
            int op = JOptionPane.showConfirmDialog(null, "¿DESEA CONTINUAR CON LA ELIMINACIÓN DEL REGISTRO?", "ELIMINAR", 0);
            System.out.println(op);
            if (op == 0) {
                try(PreparedStatement ps = getConnection().prepareStatement("DELETE FROM administrador WHERE id_administrador = ?");) {
                    ps.setString(1, codigo);
                    ps.executeUpdate();
                    limpiar();
                    mostrar();
                    sonido.beep();
                    JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"ERROR AL ELIMINAR");
                }
            } else {
               
            }
        }
    }
        
        public void limpiar(){
            usuario.setText("");
            contrasena.setText("");
            correo.setText("");
        }
     
        public void buscarPorClave(String clave) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("USUARIO");
        modelo.addColumn("CONTRASEÑA");
        modelo.addColumn("CORREO");
        
        tabla1.setModel(modelo);
        String sql = "";
        if (clave.equals("")) {
            sql = "Select a.id_administrador, a.usuario, a.contrasena, a.correo from administrador as a"; 
        } else {
            sql = "Select a.id_administrador, a.usuario, a.contrasena, a.correo from administrador as a where id_administrador like'%" + clave + "%'";
        }
        String administrador[] = new String[4];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery();){
            while (rs.next()) {
                administrador[0] = rs.getString(1);
                administrador[1] = rs.getString(2);
                administrador[2] = rs.getString(3);
                administrador[3] = rs.getString(4);
                modelo.addRow(administrador);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error al consultar asistencia por clave");
            e.printStackTrace();
        }
    }
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        contrasena = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        usuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setBackground(new java.awt.Color(255, 255, 255));
        tabla1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tabla1.setForeground(new java.awt.Color(0, 0, 51));
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla1.setGridColor(new java.awt.Color(255, 255, 255));
        tabla1.setSelectionBackground(new java.awt.Color(0, 0, 102));
        tabla1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 940, 500));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 100, 80));

        jButton3.setBackground(new java.awt.Color(0, 0, 153));
        jButton3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-24px.png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 140, 40));

        editar.setBackground(new java.awt.Color(0, 0, 153));
        editar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        editar.setForeground(new java.awt.Color(255, 255, 255));
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/editar-texto.png"))); // NOI18N
        editar.setText("EDITAR");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel1.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 130, 40));
        jPanel1.add(contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 140, -1));

        correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                correoKeyReleased(evt);
            }
        });
        jPanel1.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 160, -1));
        jPanel1.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 120, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 102));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setText("Correo :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 70, 20));

        jLabel5.setBackground(new java.awt.Color(0, 0, 102));
        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setText("Contraseña :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 90, 20));

        jLabel4.setBackground(new java.awt.Color(0, 0, 102));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setText("Usuario :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 70, 20));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("INFORMACION ADMINISTRADOR");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 910, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/peakpx (2).jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 720));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/sanciones (2).jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, -50, 470, 750));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Administrador ob = new Administrador();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void correoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correoKeyReleased
        
    }//GEN-LAST:event_correoKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        int fila = tabla1.getSelectedRow();
        String codigo = tabla1.getValueAt(fila, 0).toString();
        System.out.println(codigo);
        String sql = "Select a.id_administrador, a.usuario, a.contrasena, a.correo from administrador as a where id_administrador = ?"; 
        try(PreparedStatement a = getConnection().prepareStatement(sql)) {
            a.setString(1, codigo);
            try(ResultSet rs = a.executeQuery()){
                while (rs.next()) {
                usuario.setText(rs.getString("usuario"));
                contrasena.setText(rs.getString("contrasena"));
                correo.setText(rs.getString("correo"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tabla1MouseClicked

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
       editar();
    }//GEN-LAST:event_editarActionPerformed

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
            java.util.logging.Logger.getLogger(InfoAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InfoAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField contrasena;
    private javax.swing.JTextField correo;
    private javax.swing.JButton editar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
