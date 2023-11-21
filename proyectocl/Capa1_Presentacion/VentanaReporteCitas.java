package proyecto.Capa1_Presentacion;


import proyecto.Capa2_Aplicacion.RegistrarCitaServicio;
import proyecto.Capa3_DominioAdmin.Horario;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyecto.Capa3_Dominio.Cita;

public class VentanaReporteCitas extends javax.swing.JPanel {

    public RegistrarCitaServicio registrarcitaservicio;
    public Horario horario;

    public VentanaReporteCitas() {
        initComponents();
        fechad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
                    listar();
                } catch (Exception ex) {
                }
            }
        });
    }

    public void listar() throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha;
        String s = ((JTextField) fechad.getDateEditor().getUiComponent()).getText();
        if (s.equals("")) {
            throw new Exception("Seleccione la fecha ");
        } else {
            fecha = formato.format(fechad.getDate());
        }

        horario = new Horario();
        registrarcitaservicio = new RegistrarCitaServicio();
        horario.setFechaatencion(Date.valueOf(fecha));
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = registrarcitaservicio.ListarReporte(horario);
        tablacita.setModel(modelo);
    }
    

    private void InitStyles() {
        jLabel1.putClientProperty("FlatLaf.style", "font: 18 light.font");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        fechad = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacita = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        fechad.setToolTipText("");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tablacita.setForeground(new java.awt.Color(51, 51, 51));
        tablacita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DOCTOR", "PACIENTE", "FECHA ATENCION", "HORA", "SERVICIO", "PRECIO", "TIEMPO DEMORA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablacita.setGridColor(new java.awt.Color(255, 255, 255));
        tablacita.setSelectionBackground(new java.awt.Color(1, 198, 83));
        jScrollPane1.setViewportView(tablacita);
        if (tablacita.getColumnModel().getColumnCount() > 0) {
            tablacita.getColumnModel().getColumn(0).setHeaderValue("ID");
        }

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        btnEliminar.setText("ELIMINAR CITA");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEliminar)
                        .addGroup(contentLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(281, 281, 281)
                            .addComponent(fechad, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(284, 284, 284))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(fechad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            int filaSeleccionada = tablacita.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                DefaultTableModel modelo = (DefaultTableModel) tablacita.getModel();
                int idCita = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
                Cita citaAEliminar = new Cita();
                citaAEliminar.setIdcita(idCita);
                registrarcitaservicio.eliminarCita(citaAEliminar);
                listar();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel content;
    private com.toedter.calendar.JDateChooser fechad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablacita;
    // End of variables declaration//GEN-END:variables
}
