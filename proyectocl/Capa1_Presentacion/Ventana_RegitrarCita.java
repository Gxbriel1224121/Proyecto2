package proyecto.Capa1_Presentacion;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyecto.Capa2_Aplicacion.RegistrarCitaServicio;
import proyecto.Capa2_AplicacionAdmin.RegistrarAnalistaServicio;
import proyecto.Capa3_Dominio.Cita;
import proyecto.Capa3_DominioAdmin.Servicio;
import proyecto.Capa3_Dominio.Paciente;
import proyecto.Capa3_DominioAdmin.Horario;
import proyecto.Capa4_PersistenciaAdmin.ServicioPostgreSQL;

public class Ventana_RegitrarCita extends javax.swing.JPanel {

    Cita cita;
    RegistrarCitaServicio registrarcitaservicio;
    RegistrarAnalistaServicio registrardoctorservicio;
    Paciente paciente;
    Servicio servicio;
    Horario horario;
    ServicioPostgreSQL servicioPostgreSQL;

    public Ventana_RegitrarCita() {

        initComponents();
        InitStyles();
        FlatMaterialLighterIJTheme.setup();

    }

    public void listarfechas() throws Exception {
        registrarcitaservicio = new RegistrarCitaServicio();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha;
        String s = ((JTextField) txtfecha.getDateEditor().getUiComponent()).getText();
        if (s.equals("")) {
            throw new Exception("Seleccione  la fecha ");
        } else {
            fecha = formato.format(txtfecha.getDate());
        }

        DefaultTableModel modelo = new DefaultTableModel();
        modelo = registrarcitaservicio.listarhoras(fecha);
        tabla.setModel(modelo);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);

    }

    private void InitStyles() {
        title1.putClientProperty("FlatLaf.style", "font: 16 ");

    }

    public static void ShowJPanel(JPanel p) {
        p.setSize(750, 430);
        p.setLocation(0, 0);
    }

    public void buscarpaciente() throws Exception {
        registrarcitaservicio = new RegistrarCitaServicio();
        paciente = new Paciente();
        String dni = txtDniPaciente.getText().trim();
        paciente = registrarcitaservicio.buscarpaciente(dni);
        txtNombrePaciente.setText(paciente.getNombrepaciente());
        txtSexoPaciente.setText(paciente.getSexo());
        txtTelefonoPaciente.setText(paciente.getTelefono());
        txtidpaciente1.setText(String.valueOf(paciente.getIdpaciente()));

    }

    public void buscarservicio() throws Exception {
        registrardoctorservicio = new RegistrarAnalistaServicio();
        String nombredoctor = txtnomdoc.getText().trim();
        List<Servicio> servicios = registrardoctorservicio.buscarServicio(nombredoctor);
        CBOServicio.removeAllItems();
        txtPrecio.setText("");
        txtTiempoDemora.setText("");
        CBOServicio.addActionListener(e -> {
            String servicioSeleccionado = (String) CBOServicio.getSelectedItem();
            if (servicioSeleccionado != null) {
                for (Servicio servicio : servicios) {
                    if (servicioSeleccionado.equals(servicio.getNombreServicio())) {
                        txtPrecio.setText(String.valueOf(servicio.getPrecio()));
                        txtTiempoDemora.setText(servicio.getDemoraTiempo());
                        break;
                    }
                }
            }
        });
        for (Servicio servicio : servicios) {
            CBOServicio.addItem(servicio.getNombreServicio());
        }
    }

    public void validaringreso() {
        cita = new Cita();
        horario = new Horario();
        paciente = new Paciente();
        try {
            paciente.setIdpaciente(Integer.parseInt(txtidpaciente1.getText()));
            horario.setIdhorario(Integer.parseInt(txtidhora.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de número", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String horaStr = txthora2.getText();
        try {
            Date horaDate = dateFormat.parse(horaStr);
            horario.setHora(dateFormat.format(horaDate));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de hora", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cita.setHorario(horario);
        cita.setPacientes(paciente);
        cita.setObservacion(txtObservaciones.getText());
        cita.setServicio((String) CBOServicio.getSelectedItem());
    }

    public void guardarcita() throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(txtfecha.getDate());

        registrarcitaservicio = new RegistrarCitaServicio();
        registrarcitaservicio.guardarcita(cita, txthora2.getText(), txtnomdoc.getText(), fecha);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        CBOServicio = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtfecha = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtTiempoDemora = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtnomdoc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnregistrar = new javax.swing.JButton();
        txthora2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        content1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtDniPaciente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNombrePaciente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSexoPaciente = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTelefonoPaciente = new javax.swing.JTextField();
        title1 = new javax.swing.JLabel();
        txtiddoctor = new javax.swing.JTextField();
        txtidpaciente1 = new javax.swing.JTextField();
        txtservicio = new javax.swing.JTextField();
        btnbuscarpa2 = new javax.swing.JButton();
        txtidhora = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(964, 558));

        content.setMinimumSize(new java.awt.Dimension(0, 0));
        content.setPreferredSize(new java.awt.Dimension(964, 558));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Servicio");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 64, -1));

        CBOServicio.setToolTipText("");
        CBOServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBOServicioActionPerformed(evt);
            }
        });
        jPanel2.add(CBOServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 290, 28));

        jLabel12.setText("Precio");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 40, -1));

        txtPrecio.setEditable(false);
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 100, 30));

        jLabel13.setText("FECHA");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        txtfecha.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                txtfechaMouseDragged(evt);
            }
        });
        txtfecha.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                txtfechaMouseWheelMoved(evt);
            }
        });
        txtfecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfechaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtfechaMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtfechaMousePressed(evt);
            }
        });
        txtfecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtfechaPropertyChange(evt);
            }
        });
        txtfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechaKeyPressed(evt);
            }
        });
        jPanel2.add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 290, 30));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(952, 0, 14, 553));

        jLabel15.setText("OBSERVACION");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 90, 34));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 311, 114));

        jButton3.setText("REGISTRAR CITA");
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(734, 262, 181, 43));

        jLabel14.setText("Tiempo de demora");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        txtTiempoDemora.setEditable(false);
        txtTiempoDemora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoDemoraActionPerformed(evt);
            }
        });
        jPanel2.add(txtTiempoDemora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 120, 30));

        jLabel1.setText("Doctor");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        txtnomdoc.setEditable(false);
        jPanel2.add(txtnomdoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 290, 30));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tabla.setForeground(new java.awt.Color(51, 51, 51));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DOCTOR", "HORA"
            }
        ));
        tabla.setGridColor(new java.awt.Color(255, 255, 255));
        tabla.setSelectionBackground(new java.awt.Color(1, 198, 83));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(0).setHeaderValue("ID");
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, 160));

        btnregistrar.setText("Registrar Cita");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 110, 30));

        txthora2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthora2ActionPerformed(evt);
            }
        });
        jPanel2.add(txthora2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 290, 30));

        jLabel2.setText("Hora Cita");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 300, 20));

        content1.setBackground(new java.awt.Color(50, 71, 94));
        content1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DNI");
        content1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 99, 45, 24));

        txtDniPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniPacienteActionPerformed(evt);
            }
        });
        content1.add(txtDniPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 129, 210, 31));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("NOMBRES");
        content1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 24));

        txtNombrePaciente.setEditable(false);
        txtNombrePaciente.setForeground(new java.awt.Color(255, 255, 255));
        content1.add(txtNombrePaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 31));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SEXO");
        content1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, 24));

        txtSexoPaciente.setEditable(false);
        txtSexoPaciente.setForeground(new java.awt.Color(255, 255, 255));
        content1.add(txtSexoPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 220, 31));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("TELEFONO");
        content1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, 25));

        txtTelefonoPaciente.setEditable(false);
        txtTelefonoPaciente.setForeground(new java.awt.Color(255, 255, 255));
        content1.add(txtTelefonoPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 220, 31));

        title1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        title1.setForeground(new java.awt.Color(255, 255, 255));
        title1.setText("DATOS DEL PACIENTE");
        content1.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 176, 30));

        txtiddoctor.setEditable(false);
        txtiddoctor.setBackground(new java.awt.Color(50, 71, 94));
        txtiddoctor.setForeground(new java.awt.Color(50, 71, 94));
        txtiddoctor.setAlignmentX(0.1F);
        txtiddoctor.setAlignmentY(0.1F);
        txtiddoctor.setBorder(null);
        content1.add(txtiddoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 525, 27, -1));

        txtidpaciente1.setEditable(false);
        txtidpaciente1.setBackground(new java.awt.Color(50, 71, 94));
        txtidpaciente1.setForeground(new java.awt.Color(50, 71, 94));
        txtidpaciente1.setBorder(null);
        content1.add(txtidpaciente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 525, 43, -1));

        txtservicio.setEditable(false);
        txtservicio.setBackground(new java.awt.Color(50, 71, 94));
        txtservicio.setForeground(new java.awt.Color(50, 71, 94));
        txtservicio.setAlignmentX(0.1F);
        txtservicio.setAlignmentY(0.1F);
        txtservicio.setBorder(null);
        content1.add(txtservicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 456, 27, -1));

        btnbuscarpa2.setText("BUSCAR");
        btnbuscarpa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarpa2ActionPerformed(evt);
            }
        });
        content1.add(btnbuscarpa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        txtidhora.setEditable(false);
        txtidhora.setBackground(new java.awt.Color(50, 71, 94));
        txtidhora.setForeground(new java.awt.Color(50, 71, 94));
        txtidhora.setBorder(null);
        content1.add(txtidhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 426, -1, -1));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(content1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(content1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniPacienteActionPerformed


    }//GEN-LAST:event_txtDniPacienteActionPerformed
    public void otroMetodoDondeQuieresCargarServicios() {

    }
    private void CBOServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBOServicioActionPerformed

    }//GEN-LAST:event_CBOServicioActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed

    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtTiempoDemoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoDemoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoDemoraActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int sele = tabla.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 1) {
            txtidhora.setText(tabla.getValueAt(sele, 0).toString());
            txtnomdoc.setText(tabla.getValueAt(sele, 1).toString());
            txthora2.setText(tabla.getValueAt(sele, 2).toString());
            try {
                buscarservicio();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar servicio", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }


    }//GEN-LAST:event_tablaMouseClicked

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed

        try {
            validaringreso();
            guardarcita();
            listarfechas();
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btnbuscarpa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarpa2ActionPerformed
        try {
            buscarpaciente();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtNombrePaciente.setText("");
            txtidpaciente1.setText("");
            txtSexoPaciente.setText("");
            txtTelefonoPaciente.setText("");
            txtNombrePaciente.requestFocus();
        }
    }//GEN-LAST:event_btnbuscarpa2ActionPerformed

    private void txthora2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthora2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthora2ActionPerformed

    private void txtfechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfechaMouseClicked

    }//GEN-LAST:event_txtfechaMouseClicked

    private void txtfechaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfechaMouseDragged

    }//GEN-LAST:event_txtfechaMouseDragged

    private void txtfechaMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_txtfechaMouseWheelMoved

    }//GEN-LAST:event_txtfechaMouseWheelMoved

    private void txtfechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfechaMousePressed

    }//GEN-LAST:event_txtfechaMousePressed

    private void txtfechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfechaMouseEntered

    }//GEN-LAST:event_txtfechaMouseEntered

    private void txtfechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyPressed

    }//GEN-LAST:event_txtfechaKeyPressed

    private void txtfechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtfechaPropertyChange
        txtfecha.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    try {
                        listarfechas(); // Llama al método para cargar los datos basados en la nueva fecha seleccionada
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar datos. Por favor, seleccione una fecha.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }//GEN-LAST:event_txtfechaPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBOServicio;
    private javax.swing.JButton btnbuscarpa2;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JPanel content;
    private javax.swing.JPanel content1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel title1;
    private javax.swing.JTextField txtDniPaciente;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSexoPaciente;
    private javax.swing.JTextField txtTelefonoPaciente;
    private javax.swing.JTextField txtTiempoDemora;
    private com.toedter.calendar.JDateChooser txtfecha;
    private javax.swing.JTextField txthora2;
    private javax.swing.JTextField txtiddoctor;
    private javax.swing.JTextField txtidhora;
    private javax.swing.JTextField txtidpaciente1;
    private javax.swing.JTextField txtnomdoc;
    private javax.swing.JTextField txtservicio;
    // End of variables declaration//GEN-END:variables
}
