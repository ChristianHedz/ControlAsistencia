
package com.proyectoasistencia;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InfoEmpleados extends javax.swing.JFrame {
    
    Toolkit sonido = Toolkit.getDefaultToolkit();
    
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    public InfoEmpleados() {
        initComponents();
        this.setLocationRelativeTo(null);
        mostrar();
    }
    
        
        public void mostrar()  {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO_P");
        modelo.addColumn("APELLIDO_M");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CORREO");
        modelo.addColumn("TIPO");

        tabla1.setModel(modelo);

        String sql = "Select * from empleados"; 
        
        String asistencias[] = new String[10];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery();) {

            while (rs.next()) {
                asistencias[0] = rs.getString(1);
                asistencias[1] = rs.getString(2);
                asistencias[2] = rs.getString(3);
                asistencias[3] = rs.getString(4);
                asistencias[4] = rs.getString(5);
                asistencias[5] = rs.getString(6);
                asistencias[6] = rs.getString(7);
                modelo.addRow(asistencias);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
    }
        
        public void buscarPorClave(String clave) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO_P");
        modelo.addColumn("APELLIDO_M");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CORREO");
        modelo.addColumn("TIPO");
        
        tabla1.setModel(modelo);
        String sql = "";
        if (clave.equals("")) {
            sql = "Select * from empleados"; 
        } else {
            sql = "Select * from empleados where id_empleado like'%" + clave + "%'";
        }
        String asistencias[] = new String[10];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery();){
            while (rs.next()) {
                asistencias[0] = rs.getString(1);
                asistencias[1] = rs.getString(2);
                asistencias[2] = rs.getString(3);
                asistencias[3] = rs.getString(4);
                asistencias[4] = rs.getString(5);
                asistencias[5] = rs.getString(6);
                asistencias[6] = rs.getString(7);
                modelo.addRow(asistencias);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error al consultar asistencia por clave");
            e.printStackTrace();
        }
    }
        
        public void buscarPorNombre(String nombre) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO_P");
        modelo.addColumn("APELLIDO_M");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CORREO");
        modelo.addColumn("TIPO");
        
        tabla1.setModel(modelo);
        String sql = "";
        if (nombre.equals("")) {
            sql = "Select * from empleados"; 
        } else {
            sql = "Select * from empleados where nombre like'%" + nombre + "%'";
        }
        String asistencias[] = new String[10];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery();){
            while (rs.next()) {
                asistencias[0] = rs.getString(1);
                asistencias[1] = rs.getString(2);
                asistencias[2] = rs.getString(3);
                asistencias[3] = rs.getString(4);
                asistencias[4] = rs.getString(5);
                asistencias[5] = rs.getString(6);
                asistencias[6] = rs.getString(7);
                modelo.addRow(asistencias);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error al consultar asistencia por clave");
            e.printStackTrace();
        }
    }
        
     
        public void buscarPorTipo(String tipo) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO_P");
        modelo.addColumn("APELLIDO_M");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CORREO");
        modelo.addColumn("TIPO");
        
        tabla1.setModel(modelo);
        String sql = "";
        if (tipo.equals("")) {
            sql = "Select * from empleados"; 
        } else {
            sql = "Select * from empleados where tipo_empleado like'%" + tipo + "%'";
        }
        String asistencias[] = new String[10];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery();){
            while (rs.next()) {
                asistencias[0] = rs.getString(1);
                asistencias[1] = rs.getString(2);
                asistencias[2] = rs.getString(3);
                asistencias[3] = rs.getString(4);
                asistencias[4] = rs.getString(5);
                asistencias[5] = rs.getString(6);
                asistencias[6] = rs.getString(7);
                modelo.addRow(asistencias);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error al consultar asistencia por clave");
            e.printStackTrace();
        }
    }
            
//            int fila = tabla1.getSelectedRow();
//            String codigo = tabla1.getValueAt(fila, 0).toString();
//
//            ps = conn.prepareStatement("SELECT * FROM alumnos WHERE matricula=?");
//            ps.setString(1, codigo);
        
        public void eliminar() {
        int fila = tabla1.getSelectedRow();
        String codigo = tabla1.getValueAt(fila, 0).toString();
        if (codigo == null) {
            sonido.beep();
            JOptionPane.showMessageDialog(null, "NECESITA UNA MATRICULA PARA PODER ELIMINAR UN REGISTRO");
        } else {
            sonido.beep();
            int op = JOptionPane.showConfirmDialog(null, "¿DESEA CONTINUAR CON LA ELIMINACIÓN DEL REGISTRO?", "ELIMINAR", 0);
            System.out.println(op);
            if (op == 0) {
                try(PreparedStatement ps = getConnection().prepareStatement("DELETE FROM empleados WHERE id_empleado = ?");) {
                    ps.setString(1, codigo);
                    ps.executeUpdate();
//                  limpiar();
                    mostrar();
                    sonido.beep();
                    JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
               
            }
        }
    }
        
        public void editarEmpleado() {
            int fila = tabla1.getSelectedRow();
            String codigo = tabla1.getValueAt(fila, 0).toString();
            int fila2 = tabla1.getSelectedRow();
            String codigo2 = tabla1.getValueAt(fila, 4).toString();
            int fila3 = tabla1.getSelectedRow();
            String codigo3 = tabla1.getValueAt(fila, 5).toString();
            String sql = "Update empleados set telefono = ? , correo = ? where id_empleado = ? ";
            try (PreparedStatement a = getConnection().prepareStatement(sql)){
                a.setString(1, codigo2);
                a.setString(2, codigo3);
                a.setString(3,codigo);
                a.executeUpdate();
                sonido.beep();
                JOptionPane.showMessageDialog(null, "REGISTRO EDITADO CON EXITO");
                mostrar();
            } catch (SQLException ex) {
                sonido.beep();
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "ERROR AL EDITAR" + ex);
            }
    }
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        clave = new javax.swing.JTextField();
        tipo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("MAFEK");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 70, 170, 70));

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
        tabla1.setGridColor(new java.awt.Color(0, 0, 51));
        tabla1.setSelectionBackground(new java.awt.Color(0, 0, 102));
        tabla1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 190, 900, 480));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("CONSULTAR INFORMACION DE EMPLEADOS");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 800, -1));

        jLabel7.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("Buscar por Nombre :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, 20));

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
        });
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 120, -1));

        jButton1.setBackground(new java.awt.Color(0, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, 90, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/girl-asistencia.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, 480, 550));

        jLabel2.setFont(new java.awt.Font("Raleway Black", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Clave Empleado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 153));
        jButton2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/editar-texto.png"))); // NOI18N
        jButton2.setText("EDITAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 110, 40));

        jButton3.setBackground(new java.awt.Color(0, 0, 153));
        jButton3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-24px.png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 120, 40));

        clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveActionPerformed(evt);
            }
        });
        clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                claveKeyReleased(evt);
            }
        });
        getContentPane().add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 110, -1));

        tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tipoKeyReleased(evt);
            }
        });
        getContentPane().add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, 120, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setText("Buscar por Tipo : ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, -1, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/peakpx (2).jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 690));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/android-material-design-blue.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 420, 210));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveActionPerformed
    }//GEN-LAST:event_claveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuEmpleados ob = new MenuEmpleados();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void claveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claveKeyReleased
        buscarPorClave(clave.getText());
    }//GEN-LAST:event_claveKeyReleased

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        buscarPorNombre(nombre.getText());
    }//GEN-LAST:event_nombreKeyReleased

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void tipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoKeyReleased
        buscarPorTipo(tipo.getText());
    }//GEN-LAST:event_tipoKeyReleased

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked

//        try(PreparedStatement a = getConnection().prepareStatement("SELECT * FROM empleados WHERE id_empleado = ?");
//                ResultSet rs = a.executeQuery();) {
//            a.setString(1, codigo);
//            int fila = tabla1.getSelectedRow();
//            String codigo = tabla1.getValueAt(fila, 0).toString();
//
//            
//
//            while (rs.next()) {
//                txtAuxiliar.setText(rs.getString("matricula"));
//                txtMatricula.setText(rs.getString("matricula"));
//                txtNombre.setText(rs.getString("nombre_a"));
//                txtApaterno.setText(rs.getString("a_paterno"));
//                txtAmaterno.setText(rs.getString("a_materno"));
//                txtEdad.setText(rs.getString("edad"));
//                txtFechaNacimiento.setText(rs.getString("fecha_nacimiento"));
//                txtDomicilio.setText(rs.getString("direccion"));
//                txtNumeroTelefonico.setText(rs.getString("telefono"));
//                txtCorreoElectronico.setText(rs.getString("correo"));
//                cbnGrupo.setSelectedItem(rs.getString("grupo"));
//
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    }//GEN-LAST:event_tabla1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editarEmpleado();
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
            java.util.logging.Logger.getLogger(InfoEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InfoEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField tipo;
    // End of variables declaration//GEN-END:variables
}
