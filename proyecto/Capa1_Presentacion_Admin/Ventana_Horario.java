package proyecto.Capa1_Presentacion_Admin;

import proyecto.Capa3_DominioAdmin.Analista;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyecto.Capa2_AplicacionAdmin.RegistrarHorarioServicio;
import proyecto.Capa3_DominioAdmin.Horario;

public class Ventana_Horario extends javax.swing.JPanel {

    public RegistrarHorarioServicio registrarhorarioservicio;
    public Analista analista;
    public Horario horario;

    public Ventana_Horario() {
        initComponents();
        InitStyles();
    }

    private void InitStyles() {
        title.putClientProperty("FlatLaf.style", "font: 12 light.font");

    }

    public void buscardoctor() throws Exception {
        registrarhorarioservicio = new RegistrarHorarioServicio();
        Analista doctor = registrarhorarioservicio.buscardoctor(txtdni.getText());
        txtiddoctor.setText(String.valueOf(doctor.getIddoctor()));
        String nombreCompleto = doctor.getNombredoctor() + " " + doctor.getApellidoDoc();
        txtdoctor.setText(nombreCompleto);
    }
 
    public void capturardatoshorario() {
        registrarhorarioservicio = new RegistrarHorarioServicio();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(fechad.getDate());
        //String fecha = txtfecha.getText();
        String id = txtiddoctor.getText();
        horario = new Horario();
        analista = new Analista();
        analista.setIddoctor(Integer.parseInt(id));
        horario.setDoctor(analista);
        horario.setFechaatencion(Date.valueOf(fecha));
    }

    public void limpiarcajas() {
        txtiddoctor.setText(null);
        txtdoctor.setText(null);
        cboinicio.setSelectedIndex(0);
        cbofinal.setSelectedIndex(0);
        txtdni.setText(null);
        txtdni.requestFocus();

    }

    public void guardarhorario() throws Exception {
        String horainicio = String.valueOf(cboinicio.getSelectedItem());
        String horafinal = String.valueOf(cbofinal.getSelectedItem());

        String[] HoraMinArray = {};
        String[] HoraMinArray2 = {};
        HoraMinArray = horainicio.split(":");
        HoraMinArray2 = horafinal.split(":");
        int horainicioN = Integer.parseInt(HoraMinArray[0]);
        int horafinalN = Integer.parseInt(HoraMinArray2[0]);

        if (horafinalN > horainicioN) {
            registrarhorarioservicio.guardarHorario(horario, horainicio, horafinal);
        } else {
            throw new Exception("ERROR  EL HORARIO FINAL TIENE QUE SE MAYOR A HORARIO INICIO");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtdni = new javax.swing.JTextField();
        txtdoctor = new javax.swing.JTextField();
        fechad = new com.toedter.calendar.JDateChooser();
        cboinicio = new javax.swing.JComboBox<>();
        cbofinal = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtiddoctor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setText("DNI");
        content.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 64, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        content.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 261, 0, 280));
        content.add(txtdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 320, 30));

        txtdoctor.setEditable(false);
        content.add(txtdoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 320, 30));
        content.add(fechad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 320, 30));

        cboinicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00:00", "7:30:00", "8:00:00", "8:30:00", "9:00:00", "9:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00" }));
        content.add(cboinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, 30));

        cbofinal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00:00", "7:30:00", "8:00:00", "8:30:00", "9:00:00", "9:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00" }));
        content.add(cbofinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, -1, 30));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        content.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, 30));

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        content.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jLabel1.setText("FECHA");
        content.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel2.setText("NOMBRES Y APELLIDOS");
        content.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        txtiddoctor.setEditable(false);
        txtiddoctor.setBackground(new java.awt.Color(255, 255, 255));
        txtiddoctor.setForeground(new java.awt.Color(255, 255, 255));
        txtiddoctor.setBorder(null);
        txtiddoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtiddoctorActionPerformed(evt);
            }
        });
        content.add(txtiddoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 17, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/011100110.jpeg"))); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            buscardoctor();
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Horario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            capturardatoshorario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error de ingreso de datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            guardarhorario();
            JOptionPane.showMessageDialog(this, "Se registro Correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            limpiarcajas();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtiddoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtiddoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtiddoctorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbofinal;
    private javax.swing.JComboBox<String> cboinicio;
    private javax.swing.JPanel content;
    private com.toedter.calendar.JDateChooser fechad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtdoctor;
    private javax.swing.JTextField txtiddoctor;
    // End of variables declaration//GEN-END:variables
}
