
package com.proyectoasistencia;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
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
import javax.mail.*;
import javax.swing.JOptionPane;

public class RegistroEmpleados extends javax.swing.JFrame {
    private String archivoAdjunto = System.getProperty("user.home") + "/Desktop/bienvenido.pdf";
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
    private int idEmpleado;
    private String horaSalida;
    
    Toolkit sonido = Toolkit.getDefaultToolkit();
    Paragraph titulo;
    
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    public RegistroEmpleados() {
        initComponents();
                mProperties = new Properties();
        nombres_archivos = "";
    }
    
    public void registrarEmpleado() {
        if (nombreE.getText().equals("")
                || apellidoPE.getText().equals("")
                || apellidoME.getText().equals("")
                || telefonoE.getText().equals("")
                || correoE.getText().equals("")
                || tipoempleadoE.getSelectedItem().equals("Tipo_Empleado")
                || horarioE.getSelectedItem().equals("Hora_Entrada")) {
            sonido.beep();
            JOptionPane.showMessageDialog(null, "FALTAN CAMPOS POR LLENAR");
        } else {
            String sql = "Insert Into empleados(nombre,apellido_p,apellido_m,telefono,correo,tipo_empleado,horario_id) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement a = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                a.setString(1, nombreE.getText());
                a.setString(2, apellidoPE.getText());
                a.setString(3, apellidoME.getText());
                a.setString(4, telefonoE.getText());
                a.setString(5, correoE.getText());
                a.setString(6, tipoempleadoE.getSelectedItem().toString());
                a.setInt(7, idHorario());
                a.executeUpdate();
                try(ResultSet rs = a.getGeneratedKeys()){
                    if(rs.next()){
                        idEmpleado = rs.getInt(1);
                    }
                }
                sonido.beep();
                JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");
            } catch (SQLException ex) {
                ex.printStackTrace();
                sonido.beep();
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR" + ex);
            }
        }
    }
    
    public int idHorario(){
        if(horarioE.getSelectedItem().equals("08:00am")){
            horaSalida = "04:00pm";
            return 1;
        }else if(horarioE.getSelectedItem().equals("09:00am")){
            horaSalida = "05:00pm";
            return 2;
        }else if(horarioE.getSelectedItem().equals("10:00am")){
            horaSalida = "06:00pm";
            return 3;
        }
        return 1;
    }
    

    
        public void generarPDF() {
            Document documento = new Document();
        try {
            String nombreEmpleado = nombreE.getText();
            String apellidoEmpleadoP = apellidoPE.getText();
            String apellidoEmpleadoM = apellidoME.getText();
            
            LocalDate fechaNow = LocalDate.now();
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaActual = fechaNow.format(formateador);
            Month mesActual = fechaNow.getMonth();
            String nombreMes = mesActual.getDisplayName(TextStyle.FULL_STANDALONE,
                new Locale("es", "ES")
            );
            
            String reportePDF = "Bienvenido";
            if(correoE.equals("")){
                sonido.beep();
                JOptionPane.showMessageDialog(null, "ASIGNE UN CORREO");
            }else{
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/"+reportePDF+".pdf"));
            documento.open();
            
            
            titulo = new Paragraph("EMPRESA MAFER");
            titulo.setAlignment(1);//alinear el titulo a la posicion 1 que es CENTRADO
            documento.add(titulo);//agregarlo al documento

            Paragraph tex = new Paragraph("TE DAMOS LA BIENVENIDA");
            tex.setAlignment(Element.ALIGN_CENTER);//centrar el texto
            documento.add(tex);
            
            
            Paragraph texto = new Paragraph();
            texto.add(new Chunk("Fecha: " + fechaActual));
            texto.add(new Chunk("\n\nEstimado/a :  " + nombreEmpleado + " " +  apellidoEmpleadoP + " "+ apellidoEmpleadoM + ".\n\n"));
            documento.add(texto);
            
//            ConexionMongoDB.imagenesMongo();
            String rutaImagen = System.getProperty("user.home") + "/Desktop/bienvenidoMongo.jpg";
            Image imagen = Image.getInstance(rutaImagen);
            imagen.setAlignment(Image.ALIGN_CENTER);
            documento.add(imagen);
        
        Paragraph descripcion = new Paragraph();
        descripcion.add(new Chunk("¡Bienvenido/a a nuestra empresa! Estamos encantados de tenerte como parte de nuestro equipo. Queremos asegurarnos de que te sientas cómodo/a y te familiarices rápidamente con nuestra cultura y normas. A continuación, te presentaremos algunas normas importantes que debes tener en cuenta durante tu tiempo aquí:\n\n" +
        "\u2022 Horario de trabajo: Tu horario de trabajo comienza a las " + horarioE.getSelectedItem().toString() + " y termina a las " + horaSalida + ". de lunes a viernes. Es importante que llegues a tiempo.\n" +
        "\u2022 Conducta y respeto en el lugar de trabajo: Establecemos pautas sobre el comportamiento adecuado y el respeto mutuo. Beberas siempre tener una buena conducta y respeto con tus compañeros de trabajo.\n" +
        "\u2022 Libreta de trabajo: La libreta de trabajo es una herramienta esencial para tomar notas, realizar seguimientos y mantener registros importantes. Deberas traerla todos los dias \n" +
        "\u2022 Uniforme: Si nuestra empresa tiene una política de uniforme o vestimenta específica, es importante que la sigas adecuadamente.\n" +
        "\u2022 Uso del celular: Durante las horas de trabajo, es importante que te enfoques en tus responsabilidades laborales. El uso del celular o su sonido constante puede interrumpir la concentración.\n\n" +
        "En caso de incumplir con alguna de estas normas, se te aplicará una multa económica que va desde los 50 hasta los 100 pesos.\n\n" +
        "Recuerda que estas reglas y sanciones se establecen para garantizar un entorno de trabajo productivo y respetuoso. Si tienes alguna pregunta o necesitas más información sobre estas políticas, no dudes en comunicarte con Recursos Humanos o tu supervisor. Agradecemos tu comprensión y colaboración para mantener un ambiente de trabajo óptimo para todos.\n\n"));
        documento.add(descripcion);
            
            Paragraph mensajeDatos = new Paragraph();
            mensajeDatos.add(new Chunk("Tu clave y datos son : \n"));
            documento.add(mensajeDatos);

            Paragraph l2 = new Paragraph("                                                                                ");
            l2.setAlignment(Element.ALIGN_BOTTOM);
            documento.add(l2);
            
            PdfPTable tabla = new PdfPTable(6);//aqui ponemos el numero de cada columna 
            tabla.setWidthPercentage(100);//ajuste al ancho de la hoja
            ///aqui podemos poner los nombres de cada columna 
            tabla.addCell("id_empleado");
            tabla.addCell("nombre");
            tabla.addCell("apellido_p");
            tabla.addCell("apellido_m");
            tabla.addCell("telefono");
            tabla.addCell("tipo");

            String sql = "Select e.id_empleado, e.nombre, e.apellido_p, e.apellido_m, telefono,e.tipo_empleado from empleados as e where id_empleado = '" + idEmpleado + "' ";
            try(PreparedStatement pst = getConnection().prepareStatement(sql);
                ResultSet rs = pst.executeQuery();) {
                while(rs.next()){
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                    tabla.addCell(rs.getString(6));
                }
                    documento.add(tabla);
            } catch (Exception e) {
                e.printStackTrace();
            }
            documento.close();
            
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
        
        public void limpiar(){
        nombreE.setText("");
        apellidoPE.setText("");
        apellidoME.setText("");
        telefonoE.setText("");
        correoE.setText("");
        tipoempleadoE.setSelectedItem("Tipo_Empleado");
        horarioE.setSelectedItem("Hora_Entrada");
    }


    private void createEmail() {
        
        if (nombreE.getText().equals("")
                || apellidoPE.getText().equals("")
                || apellidoME.getText().equals("")
                || telefonoE.getText().equals("")
                || correoE.getText().equals("")
                || tipoempleadoE.getSelectedItem().equals("Tipo_Empleado")
                || horarioE.getSelectedItem().equals("Hora_Entrada")) {
            
        } else {
        emailTo = correoE.getText().trim();
        subject = "Registro Exitoso! - Bienvenido a MAFER!".trim();
        content = "Te enviamos este correo para darte la bienvenida e informarte de las normas de esta empresa ademas de tu clave de empleado para tu registro de asistencias".trim();
        
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
            MimeBodyPart mAdjunto = new MimeBodyPart();
            mAdjunto.setDataHandler(new DataHandler(new FileDataSource(archivoAdjunto)));
            mAdjunto.setFileName(new File(archivoAdjunto).getName());
            mElementosCorreo.addBodyPart(mAdjunto);
            
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(mElementosCorreo);
        } catch (AddressException ex) {
            Logger.getLogger(RegistroEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RegistroEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
    }

    private void sendEmail() {
        if (nombreE.getText().equals("")
                || apellidoPE.getText().equals("")
                || apellidoME.getText().equals("")
                || telefonoE.getText().equals("")
                || correoE.getText().equals("")
                || tipoempleadoE.getSelectedItem().equals("Tipo_Empleado")
                || horarioE.getSelectedItem().equals("Hora_Entrada")) {
            
        } else {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            sonido.beep();
            JOptionPane.showMessageDialog(null, "TE ENVIAMOS UN CORREO!");
            limpiar();
            nombres_archivos = "";
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RegistroEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RegistroEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        registrarEmpleado = new javax.swing.JButton();
        correoE = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nombreE = new javax.swing.JTextField();
        apellidoME = new javax.swing.JTextField();
        apellidoPE = new javax.swing.JTextField();
        telefonoE = new javax.swing.JTextField();
        tipoempleadoE = new javax.swing.JComboBox<>();
        horarioE = new javax.swing.JComboBox<>();
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
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("REGISTRO DE EMPLEADOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("CORREO :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 150, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("TIPO_E :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, -1, -1));

        registrarEmpleado.setBackground(new java.awt.Color(0, 51, 153));
        registrarEmpleado.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        registrarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        registrarEmpleado.setText("REGISTRAR");
        registrarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(registrarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 330, 40));

        correoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoEActionPerformed(evt);
            }
        });
        jPanel1.add(correoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 180, 30));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("APELLIDO M :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 160, 40));

        jLabel7.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("APELLIDO P : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("TELEFONO : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("NOMBRE :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel12.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("HORARIO :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 130, -1));

        nombreE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreEActionPerformed(evt);
            }
        });
        jPanel1.add(nombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 180, 30));

        apellidoME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoMEActionPerformed(evt);
            }
        });
        jPanel1.add(apellidoME, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 180, 30));

        apellidoPE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoPEActionPerformed(evt);
            }
        });
        jPanel1.add(apellidoPE, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 180, 30));

        telefonoE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoEKeyTyped(evt);
            }
        });
        jPanel1.add(telefonoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 180, 30));

        tipoempleadoE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo_Empleado", "Administrativo", "Novato", "Cerrador", "RH" }));
        jPanel1.add(tipoempleadoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 180, 30));

        horarioE.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        horarioE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hora_Entrada", "08:00am", "09:00am", "10:00am" }));
        horarioE.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                horarioEAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        jPanel1.add(horarioE, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 180, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/peakpx (1).jpg"))); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-130, -10, 960, 740));

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

    private void correoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoEActionPerformed

    private void nombreEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreEActionPerformed

    private void apellidoPEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoPEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoPEActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Registro ob = new Registro();
        ob.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void horarioEAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_horarioEAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_horarioEAncestorMoved

    private void registrarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarEmpleadoActionPerformed
        registrarEmpleado();
        generarPDF();
        createEmail();
        sendEmail();
    }//GEN-LAST:event_registrarEmpleadoActionPerformed

    private void apellidoMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoMEActionPerformed

    private void telefonoEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoEKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_telefonoEKeyTyped

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
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RegistroEmpleados().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoME;
    private javax.swing.JTextField apellidoPE;
    private javax.swing.JTextField correoE;
    private javax.swing.JComboBox<String> horarioE;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreE;
    private javax.swing.JButton registrarEmpleado;
    private javax.swing.JTextField telefonoE;
    private javax.swing.JComboBox<String> tipoempleadoE;
    // End of variables declaration//GEN-END:variables

}

