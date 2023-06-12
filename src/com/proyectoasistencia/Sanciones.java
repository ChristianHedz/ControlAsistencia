
package com.proyectoasistencia;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Sanciones extends javax.swing.JFrame {
    
    
    
    Toolkit sonido = Toolkit.getDefaultToolkit();
    Paragraph titulo;
    
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    public Sanciones() {
        initComponents();
        this.setLocationRelativeTo(null);
        mostrar();
    }

        public void mostrar() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("MOTIVO");
        modelo.addColumn("MULTA");
        modelo.addColumn("CLAVE EMPLEADO");
        modelo.addColumn("NOMBRE EMPLEADO");
        modelo.addColumn("APELLIDO EMPLEADO");

        tabla1.setModel(modelo);
        
        String sql = "Select s.*, e.nombre, e.apellido_p from sanciones as s inner join empleados as e on (s.empleado_id = e.id_empleado)"; 
        
        String sanciones[] = new String[6];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery()) {
            while (rs.next()) {
                sanciones[0] = rs.getString(1);
                sanciones[1] = rs.getString(2);
                sanciones[2] = rs.getString(3);
                sanciones[3] = rs.getString(4);
                sanciones[4] = rs.getString(5);
                sanciones[5] = rs.getString(6);
                modelo.addRow(sanciones);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
    }
        
            
        public void registrarSancion() {
        if (clave.getText().equals("")
                || multa.getText().equals("")
                || motivo.getSelectedItem().equals("Motivo_Sancion")) {
            sonido.beep();
            JOptionPane.showMessageDialog(null, "FALTAN CAMPOS POR LLENAR");
        } else {
            try (PreparedStatement a = getConnection().prepareStatement("Insert Into sanciones(motivo_sancion,multa,empleado_id) VALUES (?,?,?)")){
                a.setString(1,motivo.getSelectedItem().toString());
                a.setString(2, multa.getText());
                a.setString(3, clave.getText());
                a.executeUpdate();
                getConnection().setAutoCommit(false);
                JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");
                getConnection().commit();
                limpiar();
                mostrar();
            } catch (SQLException ex) {
                try {
                    getConnection().rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Sanciones.class.getName()).log(Level.SEVERE, null, ex1);
                }
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR");
            }
        }
    }
        
        public void editarSancion() {
        
        if (clave.getText().equals("")
                || multa.getText().equals("")
                || motivo.getSelectedItem().equals("Motivo_sancion")) {
            sonido.beep();
            JOptionPane.showMessageDialog(null, "FALTAN CAMPOS POR LLENAR");
        } else {
            int fila = tabla1.getSelectedRow();
            String codigo = tabla1.getValueAt(fila, 0).toString();
            String sql = "Update sanciones set motivo_sancion = ? , multa = ? , empleado_id = ? where id_sancion = ? ";
            try (PreparedStatement a = getConnection().prepareStatement(sql)){
                a.setString(1,motivo.getSelectedItem().toString());
                a.setString(2, multa.getText());
                a.setString(3, clave.getText());
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
        
        if (clave.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "SELECCIONE EL REGISTRO A ELIMINAR");
        } else {
            int fila = tabla1.getSelectedRow();
            String codigo = tabla1.getValueAt(fila, 0).toString();
            sonido.beep();
            int op = JOptionPane.showConfirmDialog(null, "¿DESEA CONTINUAR CON LA ELIMINACIÓN DEL REGISTRO?", "ELIMINAR", 0);
            System.out.println(op);
            if (op == 0) {
                try(PreparedStatement ps = getConnection().prepareStatement("DELETE FROM sanciones WHERE id_sancion = ?");) {
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
            motivo.setSelectedItem("Motivo_sancion");
            multa.setText("");
            clave.setText("");
        }
     
        public void buscarPorClave(String clave) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("MOTIVO");
        modelo.addColumn("MULTA");
        modelo.addColumn("CLAVE EMPLEADO");
        modelo.addColumn("NOMBRE EMPLEADO");
        modelo.addColumn("APELLIDO EMPLEADO");
        
        tabla1.setModel(modelo);
        String sql = "";
        if (clave.equals("")) {
            sql = "Select s.*, e.nombre, e.apellido_p from sanciones as s inner join empleados as e on (s.empleado_id = e.id_empleado)"; 
        } else {
            sql = "Select s.*, e.nombre, e.apellido_p from sanciones as s inner join empleados as e on (s.empleado_id = e.id_empleado) where id_empleado like'%" + clave + "%'";
        }
        String sanciones[] = new String[6];

        try(PreparedStatement a = getConnection().prepareStatement(sql);
            ResultSet rs = a.executeQuery();){
            while (rs.next()) {
                sanciones[0] = rs.getString(1);
                sanciones[1] = rs.getString(2);
                sanciones[2] = rs.getString(3);
                sanciones[3] = rs.getString(4);
                sanciones[4] = rs.getString(5);
                sanciones[5] = rs.getString(6);
                modelo.addRow(sanciones);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error al consultar asistencia por clave");
            e.printStackTrace();
        }
    }
        
            public void generarPDF() {
            Document documento = new Document();

        try {
            String nombreEmpleado = tabla1.getValueAt(0, 4).toString();
            String apellidoEmpleado = tabla1.getValueAt(0, 5).toString();
            
            LocalDate fechaNow = LocalDate.now();
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaActual = fechaNow.format(formateador);
            Month mesActual = fechaNow.getMonth();
            String nombreMes = mesActual.getDisplayName(TextStyle.FULL_STANDALONE,
                new Locale("es", "ES")
            );
            

            String clavel = clavebuscar.getText();
            String reportePDF = nombrepdf.getText();
            if(reportePDF.equals("")){
                sonido.beep();
                JOptionPane.showMessageDialog(null, "ASIGNE UN NOMBRE AL REPORTE");
            }else if(clavebuscar.getText().equals("")){
                sonido.beep();
                JOptionPane.showMessageDialog(null, "COLOQUE CLAVE DEL EMPLEADO");
             }else{
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/"+reportePDF+".pdf"));
            documento.open();
            
            
            titulo = new Paragraph("EMPRESA MAFER");
            titulo.setAlignment(1);//alinear el titulo a la posicion 1 que es CENTRADO
            documento.add(titulo);//agregarlo al documento

            Paragraph tex = new Paragraph("REPORTE DE SANCIONES");
            tex.setAlignment(Element.ALIGN_CENTER);//centrar el texto
            documento.add(tex);
            
            Paragraph l = new Paragraph("                                                                                ");
            l.setAlignment(Element.ALIGN_BOTTOM);
            documento.add(l);
            documento.add(Chunk.NEWLINE);
            
            Paragraph texto = new Paragraph();
            texto.add(new Chunk("Fecha: " + fechaActual));
            texto.add(new Chunk("\n\nEstimado: " + nombreEmpleado + " " +  apellidoEmpleado + "\n\n"));
            texto.add(new Chunk("Le enviamos este reporte para informarle sobre las sanciones relacionadas con multas que se han impuesto en relación con su conducta como empleado/a de MAFER. Nuestro objetivo es asegurar que todos los miembros del equipo comprendan claramente las políticas y expectativas de la empresa, así como las consecuencias que pueden surgir de su incumplimiento.\n" +
"\n" +
"Desafortunadamente, hemos identificado ciertas acciones por su parte que violan las políticas internas establecidas por la empresa. Como resultado, se han aplicado las siguientes sanciones en forma de multas:.\n\n" + "Si tiene alguna pregunta o requiere más información sobre estas sanciones, no dude en comunicarse con el departamento de Recursos Humanos. Estamos aquí para brindarle la asistencia necesaria. \n\n"));
            texto.add(new Chunk("Detalles de Sanciones:\n\n"));
            documento.add(texto);
            
            String rutaImagen = System.getProperty("user.home") + "/Desktop/sancionroja.jpg";
            Image imagen = Image.getInstance(rutaImagen);
            imagen.setAlignment(Image.ALIGN_CENTER);
            documento.add(imagen);

            
            Paragraph l2 = new Paragraph("                                                                                ");
            l2.setAlignment(Element.ALIGN_BOTTOM);
            documento.add(l2);
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(5);//aqui ponemos el numero de cada columna 
            tabla.setWidthPercentage(100);//ajuste al ancho de la hoja
            ///aqui podemos poner los nombres de cada columna 
            tabla.addCell("MotivoSancion");
            tabla.addCell("Multa");
            tabla.addCell("ClaveEmpleado");
            tabla.addCell("Nombre");
            tabla.addCell("ApellidoP");

            String sql = "Select s.motivo_sancion, s.multa, s.empleado_id, e.nombre, e.apellido_p from sanciones as s inner join empleados as e on (s.empleado_id = e.id_empleado) where id_empleado like'%" + clavel + "%'";
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "REPORTE NO CREADO");

        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        motivo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        clavebuscar = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        nombrepdf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        generarPDF = new javax.swing.JButton();
        multa = new javax.swing.JTextField();
        clave = new javax.swing.JTextField();
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

        motivo.setBackground(new java.awt.Color(255, 255, 255));
        motivo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        motivo.setForeground(new java.awt.Color(255, 255, 255));
        motivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Motivo_Sancion", "Retardo", "Sin Uniforme", "Uso de Celular", "Sin Libreta" }));
        jPanel1.add(motivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 120, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 100, 80));

        jButton2.setBackground(new java.awt.Color(0, 0, 153));
        jButton2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/disquete.png"))); // NOI18N
        jButton2.setText("AGREGAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 140, 40));

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
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 140, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/correoicon.jpg"))); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 560, 230, 100));

        jLabel8.setBackground(new java.awt.Color(0, 0, 102));
        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setText("NombrePDF : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        clavebuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavebuscarActionPerformed(evt);
            }
        });
        clavebuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clavebuscarKeyReleased(evt);
            }
        });
        jPanel1.add(clavebuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 120, -1));

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
        jPanel1.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 120, 40));
        jPanel1.add(nombrepdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 120, 140, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 102));
        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setText("Buscar por Clave :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 120, 20));

        generarPDF.setBackground(new java.awt.Color(0, 0, 153));
        generarPDF.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        generarPDF.setForeground(new java.awt.Color(255, 255, 255));
        generarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/reservado.png"))); // NOI18N
        generarPDF.setText("GENERAR PDF");
        generarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarPDFActionPerformed(evt);
            }
        });
        jPanel1.add(generarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 270, -1));
        jPanel1.add(multa, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 140, -1));

        clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                claveKeyReleased(evt);
            }
        });
        jPanel1.add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 140, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 102));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setText("Clave Empleado : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, 20));

        jLabel5.setBackground(new java.awt.Color(0, 0, 102));
        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setText("Multa :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 60, 20));

        jLabel4.setBackground(new java.awt.Color(0, 0, 102));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setText("Motivo-Sancion :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 120, 20));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("CONSULTAR / AGREGAR  SANCIONES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 910, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/peakpx (2).jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 720));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/sanciones (2).jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, -50, 470, 750));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuEmpleados ob = new MenuEmpleados();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void claveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claveKeyReleased
        
    }//GEN-LAST:event_claveKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        registrarSancion();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        int fila = tabla1.getSelectedRow();
        String codigo = tabla1.getValueAt(fila, 0).toString();
        System.out.println(codigo);
        try(PreparedStatement a = getConnection().prepareStatement("Select s.*, e.nombre, e.apellido_p from sanciones as s inner join empleados as e on (s.empleado_id = e.id_empleado) where id_sancion = ? ");) {
            a.setString(1, codigo);
            try(ResultSet rs = a.executeQuery()){
                while (rs.next()) {
                motivo.setSelectedItem(rs.getString("motivo_sancion"));
                multa.setText(rs.getString("multa"));
                clave.setText(rs.getString("empleado_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tabla1MouseClicked

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
       editarSancion();
    }//GEN-LAST:event_editarActionPerformed

    private void clavebuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavebuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clavebuscarActionPerformed

    private void clavebuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clavebuscarKeyReleased
        buscarPorClave(clavebuscar.getText());
    }//GEN-LAST:event_clavebuscarKeyReleased

    private void generarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarPDFActionPerformed
        generarPDF();
    }//GEN-LAST:event_generarPDFActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        EnvioCorreo envio = new EnvioCorreo();
        envio.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Sanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sanciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clave;
    private javax.swing.JTextField clavebuscar;
    private javax.swing.JButton editar;
    private javax.swing.JButton generarPDF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JComboBox<String> motivo;
    private javax.swing.JTextField multa;
    private javax.swing.JTextField nombrepdf;
    private javax.swing.JTable tabla1;
    // End of variables declaration//GEN-END:variables
}
