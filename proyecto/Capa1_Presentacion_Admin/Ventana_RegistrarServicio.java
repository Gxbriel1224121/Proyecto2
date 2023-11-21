package proyecto.Capa1_Presentacion_Admin;

import javax.swing.JOptionPane;

import proyecto.Capa2_AplicacionAdmin.RegistrarServicio;
import proyecto.Capa3_DominioAdmin.Servicio;
import proyecto.Capa3_DominioAdmin.Analista;

public class Ventana_RegistrarServicio extends javax.swing.JPanel {

    RegistrarServicio registrarServicio;
    Servicio servicio;
    Analista doctor;

    public Ventana_RegistrarServicio() {
        initComponents();
        
        NuevoServicio();
        servicio = new Servicio();
        doctor = new Analista();
    }


    private void NuevoServicio() {
        registrarServicio = new RegistrarServicio();
        txtNuevoServicio.requestFocus();
        txtNuevoServicio.setText("");
        txtPrecio.setText("");
        txfTiempoDemora.setText("");
        iddoctor.setText("");
    }


    private void guardarServicio() {
        try {
            registrarServicio.guardarServicio(servicio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void capturarDatosServicio() throws Exception {
     servicio = new Servicio();
    doctor = new Analista();
    servicio.setNombreServicio(txtNuevoServicio.getText());
    servicio.setPrecio(Double.parseDouble(txtPrecio.getText()));
    servicio.setDemoraTiempo(txfTiempoDemora.getText());
    doctor.setIddoctor(Integer.parseInt(iddoctor.getText()));

    servicio.setDoctor(doctor);
}

    public void buscardoctor() {
        try {
            registrarServicio = new RegistrarServicio();
            doctor = new Analista();
            String dni = txtDniDoctor.getText().trim();
            doctor = registrarServicio.buscardoctor(dni);
            String nombreCompleto = doctor.getNombredoctor() + " " + doctor.getApellidoDoc();
            txtNombreDoctor.setText(nombreCompleto);
            iddoctor.setText(String.valueOf(doctor.getIddoctor()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtNombreDoctor.setText("");
            iddoctor.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDniDoctor = new javax.swing.JTextField();
        iddoctor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnBuscarDoctor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNuevoServicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNombreDoctor = new javax.swing.JTextField();
        txfTiempoDemora = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        title1.setForeground(new java.awt.Color(255, 255, 255));
        title1.setText("Nuevo servicio");
        content.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel20.setText("DNI");
        content.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        txtDniDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniDoctorActionPerformed(evt);
            }
        });
        content.add(txtDniDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 260, 30));

        iddoctor.setEditable(false);
        iddoctor.setBackground(new java.awt.Color(255, 255, 255));
        iddoctor.setForeground(new java.awt.Color(255, 255, 255));
        iddoctor.setBorder(null);
        content.add(iddoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 50, 30));

        jLabel21.setText("NOMBRE Y APELLIDO");
        content.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 140, -1));

        btnBuscarDoctor.setText("BUSCAR");
        btnBuscarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDoctorActionPerformed(evt);
            }
        });
        content.add(btnBuscarDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, 30));

        jLabel1.setText("Nuevo servicio");
        content.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 120, -1));
        content.add(txtNuevoServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 300, 30));

        jLabel2.setText("Precio");
        content.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));
        content.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 100, 30));

        btnGuardar.setText("Registrar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        content.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 100, 30));

        jLabel4.setText("Tiempo de demora");
        content.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        txtNombreDoctor.setEditable(false);
        content.add(txtNombreDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 300, 30));

        txfTiempoDemora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfTiempoDemoraActionPerformed(evt);
            }
        });
        content.add(txfTiempoDemora, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/011100110.jpeg"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        content.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 0, 360, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniDoctorActionPerformed

    private void btnBuscarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDoctorActionPerformed
        try {
            buscardoctor();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtNombreDoctor.setText("");
            iddoctor.setText("");

        }

    }//GEN-LAST:event_btnBuscarDoctorActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
   try {
        capturarDatosServicio();
        if (servicio != null && doctor != null) {
            guardarServicio();
            NuevoServicio();
        } else {
            JOptionPane.showMessageDialog(this, "El objeto servicio o el objeto doctor no están inicializados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al guardar el servicio: " + e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txfTiempoDemoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfTiempoDemoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfTiempoDemoraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarDoctor;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JPanel content;
    private javax.swing.JTextField iddoctor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel title1;
    private javax.swing.JTextField txfTiempoDemora;
    private javax.swing.JTextField txtDniDoctor;
    private javax.swing.JTextField txtNombreDoctor;
    private javax.swing.JTextField txtNuevoServicio;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
