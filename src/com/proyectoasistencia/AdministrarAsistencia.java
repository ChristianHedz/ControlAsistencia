
package com.proyectoasistencia;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class AdministrarAsistencia extends javax.swing.JFrame {
    
    Toolkit sonido = Toolkit.getDefaultToolkit();
    Paragraph titulo;
    
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    public AdministrarAsistencia() {
        initComponents();
        this.setLocationRelativeTo(null);
        mostrar();
    }
    
        public void mostrar() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE EMPLEADO");
        modelo.addColumn("APELLIDO EMPLEADO");
        modelo.addColumn("FECHA ASISTENCIA");
        modelo.addColumn("HORA ASISTENCIA");

        tabla.setModel(modelo);
        
        String sql = "Select e.id_empleado, e.nombre, e.apellido_p,a.fecha_asistencia, a.hora_asistencia from asistencias as a inner join empleados as e on (a.empleado_id = e.id_empleado)"; 
        
        String asistencias[] = new String[10];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery();) {

            while (rs.next()) {
                asistencias[0] = rs.getString(1);
                asistencias[1] = rs.getString(2);
                asistencias[2] = rs.getString(3);
                asistencias[3] = rs.getString(4);
                asistencias[4] = rs.getString(5);
                modelo.addRow(asistencias);
            }
            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
    }
        
        public void buscarPorClave(String clave) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE EMPLEADO");
        modelo.addColumn("APELLIDO EMPLEADO");
        modelo.addColumn("FECHA ASISTENCIA");
        modelo.addColumn("HORA ASISTENCIA");
        
        tabla.setModel(modelo);
        String sql = "";
        if (clave.equals("")) {
            sql = "Select e.id_empleado, e.nombre, e.apellido_p,a.fecha_asistencia, a.hora_asistencia from asistencias as a inner join empleados as e on (a.empleado_id = e.id_empleado)"; 
        } else {
            sql = "Select e.id_empleado, e.nombre, e.apellido_p,a.fecha_asistencia, a.hora_asistencia from asistencias as a inner join empleados as e on (a.empleado_id = e.id_empleado) where id_empleado like'%" + clave + "%'";
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
                modelo.addRow(asistencias);
            }
            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error al consultar asistencia por clave");
            e.printStackTrace();
        }
    }
        
        public void buscarPorFecha(String fecha) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE EMPLEADO");
        modelo.addColumn("APELLIDO EMPLEADO");
        modelo.addColumn("FECHA ASISTENCIA");
        modelo.addColumn("HORA ASISTENCIA");
        
        tabla.setModel(modelo);
        String sql = "";
        if (fecha.equals("")) {
            sql = "Select e.id_empleado, e.nombre, e.apellido_p,a.fecha_asistencia, a.hora_asistencia from asistencias as a inner join empleados as e on (a.empleado_id = e.id_empleado)"; 
        } else {
            sql = "Select e.id_empleado, e.nombre, e.apellido_p,a.fecha_asistencia, a.hora_asistencia from asistencias as a inner join empleados as e on (a.empleado_id = e.id_empleado) where fecha_asistencia like'%" + fecha + "%'";
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
                modelo.addRow(asistencias);
            }
            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error al consultar asistencia por clave");
            e.printStackTrace();
        }
    }
        
            public void generarPDF() {
            Document documento = new Document();

        try {
            String nombreEmpleado = tabla.getValueAt(0, 1).toString();
            String apellidoEmpleado = tabla.getValueAt(0, 2).toString();
            LocalDate fechaNow = LocalDate.now();
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaActual = fechaNow.format(formateador);
            Month mesActual = fechaNow.getMonth();
            String nombreMes = mesActual.getDisplayName(TextStyle.FULL_STANDALONE,
                new Locale("es", "ES")
            );
            

            String clavel = clave.getText();
            String reportePDF = nomReporte.getText();
            if(reportePDF.equals("")){
                sonido.beep();
                JOptionPane.showMessageDialog(null, "ASIGNE UN NOMBRE AL REPORTE");
            }else if(clave.getText().equals("")){
                sonido.beep();
                JOptionPane.showMessageDialog(null, "COLOQUE CLAVE DEL EMPLEADO");
             }else{
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/"+reportePDF+".pdf"));
            documento.open();
            
            
            titulo = new Paragraph("EMPRESA MAFER");
            titulo.setAlignment(1);//alinear el titulo a la posicion 1 que es CENTRADO
            documento.add(titulo);//agregarlo al documento

            Paragraph tex = new Paragraph("REPORTE DE ASISTENCIAS");
            tex.setAlignment(Element.ALIGN_CENTER);//centrar el texto
            documento.add(tex);
            
            Paragraph l = new Paragraph("                                                                                ");
            l.setAlignment(Element.ALIGN_BOTTOM);
            documento.add(l);
            documento.add(Chunk.NEWLINE);
            
            Paragraph texto = new Paragraph();
            texto.add(new Chunk("Fecha: " + fechaActual));
            texto.add(new Chunk(fecha.getText()));
            texto.add(new Chunk("\n\nEstimado: " + nombreEmpleado + " " +  apellidoEmpleado + "\n\n"));
            texto.add(new Chunk("A continuación, se presenta el resumen de sus asistencias registradas durante el mes de Mayo del año " + "2023" + ". Agradecemos su puntualidad y compromiso con su horario de trabajo. Si tiene alguna discrepancia o consulta sobre alguna de las fechas registradas, le pedimos que se comunique con el departamento de Recursos Humanos para que podamos resolverlo de manera oportuna.\n\n"));
            texto.add(new Chunk("Detalles de Asistencias:\n\n"));
            documento.add(texto);
            
            String rutaImagen = System.getProperty("user.home") + "/Desktop/asistenciaprincipal.png";
            Image imagen = Image.getInstance(rutaImagen);
            imagen.setAlignment(Image.ALIGN_CENTER);
            documento.add(imagen);

            
            Paragraph l2 = new Paragraph("                                                                                ");
            l2.setAlignment(Element.ALIGN_BOTTOM);
            documento.add(l2);
            
            

            PdfPTable tabla = new PdfPTable(5);//aqui ponemos el numero de cada columna 
            tabla.setWidthPercentage(100);//ajuste al ancho de la hoja
            ///aqui podemos poner los nombres de cada columna 
            tabla.addCell("ClaveEmpleado");
            tabla.addCell("Nombre");
            tabla.addCell("ApellidoP");
            tabla.addCell("FechaAsistencia");
            tabla.addCell("HoraAsistencia");
            
            String sql = "SELECT e.id_empleado, e.nombre, e.apellido_p, a.fecha_asistencia, a.hora_asistencia FROM asistencias AS a INNER JOIN empleados AS e ON (a.empleado_id = e.id_empleado) WHERE id_empleado LIKE '%" + clavel + "%'";
            try(PreparedStatement pst = getConnection().prepareStatement(sql);
                ResultSet rs = pst.executeQuery();) {
                                    
                while(rs.next()){
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                }
                    documento.add(tabla);
                    
            
            } catch (Exception e) {
                e.printStackTrace();
            }
            documento.close();
            sonido.beep();
            JOptionPane.showMessageDialog(null, "REPORTE CREADO");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "REPORTE NO CREADO" + e);

        }
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nomReporte = new javax.swing.JTextField();
        generarPDF = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        clave = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("MAFEK");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 70, 170, 70));

        jButton3.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/correoicon.jpg"))); // NOI18N
        jButton3.setText("ENVIAR CORREO");
        jButton3.setBorder(null);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 560, 220, 110));

        tabla.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        tabla.setForeground(new java.awt.Color(0, 0, 51));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.setGridColor(new java.awt.Color(255, 255, 255));
        tabla.setSelectionBackground(new java.awt.Color(0, 0, 51));
        tabla.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 190, 940, 480));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("CONSULTAR REGISTRO DE ASISTENCIAS");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 960, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/girl-asistencia.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, 480, 550));

        jLabel7.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("Buscar por Fecha :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, 20));

        fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fechaKeyReleased(evt);
            }
        });
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 110, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setText("Nombre PDF : ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 100, 20));
        getContentPane().add(nomReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 110, -1));

        generarPDF.setBackground(new java.awt.Color(0, 51, 153));
        generarPDF.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        generarPDF.setForeground(new java.awt.Color(255, 255, 255));
        generarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/reservado.png"))); // NOI18N
        generarPDF.setText("GENERAR PDF");
        generarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarPDFActionPerformed(evt);
            }
        });
        getContentPane().add(generarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 250, 40));

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

        jLabel2.setFont(new java.awt.Font("Raleway Black", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Buscar por Clave :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

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
        getContentPane().add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 110, -1));

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

    private void fechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaKeyReleased
        buscarPorFecha(fecha.getText());
    }//GEN-LAST:event_fechaKeyReleased

    private void generarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarPDFActionPerformed
        generarPDF();
    }//GEN-LAST:event_generarPDFActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        EnvioCorreo ob = new EnvioCorreo();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // Cambiar el cursor a la mano
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        // Cambiar el cursor al predeterminado
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jButton3MouseExited

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
            java.util.logging.Logger.getLogger(AdministrarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarAsistencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clave;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton generarPDF;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JTextField nomReporte;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
