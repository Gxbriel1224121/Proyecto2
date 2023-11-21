package proyecto.Capa4_Persistencia;

import proyecto.Capa3_Dominio.Cita;
import proyecto.Capa3_DominioAdmin.Horario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBC;

public class CitaPostgreSQL {

    private final AccesoDatosJDBC accesoDatosJDBC;

    public CitaPostgreSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;

    }

    public void guardar(Cita cita) {
        String insertSQL = "insert into citas(idpaciente,idhorario,observacion, servicio) "
                + "values(?,?,?,?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setInt(1, cita.getPacientes().getIdpaciente());
            sentencia.setInt(2, cita.getHorario().getIdhorario());
            String observacion = (cita.getObservacion() == null || cita.getObservacion().isEmpty()) ? "Ninguno" : cita.getObservacion();
            sentencia.setString(3, observacion);
            sentencia.setString(4, cita.getServicio());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se registró correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void eliminarCita(Cita cita) throws Exception {
        try {

            String deleteSQL = "WITH deleted_cita AS (DELETE FROM Citas WHERE idcita = ? RETURNING idhorario) UPDATE Horarios SET estado = false FROM deleted_cita WHERE Horarios.idhorario = deleted_cita.idhorario;";
            PreparedStatement sentencia = accesoDatosJDBC.prepararSentencia(deleteSQL);
            sentencia.setInt(1, cita.getIdcita());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public DefaultTableModel Listarhoras(String fecha) throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "SELECT  horarios.idhorario,doctor.nombredoctor,horarios.hora FROM horarios inner join doctor on doctor.iddoctor =horarios.id_doctor\n"
                + "where  cast(horarios.fechaatencion as varchar ) like '%" + fecha + "%' and horarios.estado =false order by horarios.fechaatencion";

        PreparedStatement sentencia;
        try {
            modelo.addColumn("ID");
            modelo.addColumn("DOCTOR");
            modelo.addColumn("HORA");

            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            ResultSet resultado = sentencia.executeQuery();
            ResultSetMetaData d = resultado.getMetaData();
            int ca = d.getColumnCount();
            while (resultado.next()) {
                Object[] guardar = new Object[ca];
                for (int i = 0; i < ca; i++) {
                    guardar[i] = resultado.getObject(i + 1);
                }
                modelo.addRow(guardar);

            }
            return modelo;
        } catch (SQLException e) {
            throw new Exception("Error al intentar consultar" + e.toString());
        }

    }

    public DefaultTableModel Reportedecitas(Horario horario) throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "SELECT citas.idcita, doctor.nombredoctor, paciente.nombrepaciente, horarios.fechaatencion, horarios.hora, citas.servicio as nombreservicio, (SELECT precio FROM servicios WHERE nombreservicio = citas.servicio LIMIT 1) AS precio, (SELECT tiempodemora FROM servicios WHERE nombreservicio = citas.servicio LIMIT 1) AS tiempodemora FROM Citas INNER JOIN Horarios ON Citas.idhorario = Horarios.idhorario INNER JOIN doctor ON Horarios.id_doctor = doctor.iddoctor INNER JOIN paciente ON Citas.idpaciente = paciente.idpaciente WHERE horarios.fechaatencion = ?;";

        PreparedStatement sentencia;
        try {
            modelo.addColumn("ID");
            modelo.addColumn("DOCTOR");
            modelo.addColumn("PACIENTE");
            modelo.addColumn("FECHA ATENCION");
            modelo.addColumn("HORA");
            modelo.addColumn("SERVICIO");
            modelo.addColumn("PRECIO");
            modelo.addColumn("TIEMPO DEMORA");

            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            sentencia.setDate(1, Date.valueOf(horario.getFechaatencion().toString()));
            ResultSet resultado = sentencia.executeQuery();
            ResultSetMetaData d = resultado.getMetaData();
            int ca = d.getColumnCount();
            while (resultado.next()) {
                Object[] guardar = new Object[ca];
                for (int i = 0; i < ca; i++) {
                    guardar[i] = resultado.getObject(i + 1);
                }
                modelo.addRow(guardar);

            }
            return modelo;
        } catch (SQLException e) {
            throw new Exception("Error al intentar consultar" + e.toString());
        }
    }

    public String Consultardoc(int idpaciente, String fecha) throws Exception {
        String resul = "";
        String sql = "SELECT MAX(doctor.nombredoctor) AS max_nombre_doctor FROM horarios INNER JOIN citas ON horarios.idhorario = citas.idhorario INNER JOIN doctor ON doctor.iddoctor = horarios.id_doctor WHERE citas.idpaciente = ? AND horarios.fechaatencion = ? GROUP BY citas.idpaciente;";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            sentencia.setInt(1, idpaciente);
            sentencia.setDate(2, Date.valueOf(fecha));
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {

                resul = resultado.getString(1);
            }

            return resul;
        } catch (SQLException e) {
            throw new Exception("Error" + e, e);
        }
    }

    public String ultimacitahora(int idpaciente, String fecha) throws Exception {
        String resul = "";
        String sql = "SELECT MAX(horarios.hora) AS max_hora FROM citas INNER JOIN horarios ON horarios.idhorario = citas.idhorario WHERE citas.idpaciente = ? AND horarios.fechaatencion = ? GROUP BY citas.idpaciente;";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            sentencia.setInt(1, idpaciente);
            sentencia.setDate(2, Date.valueOf(fecha));
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                resul = resultado.getString(1);

            }

            return resul;
        } catch (SQLException e) {
            throw new Exception("Error al intentar consultar.", e);
        }

    }

    public int maximohora(int id, String fecha) throws Exception {
        int resul = 0;
        String sql = "SELECT COUNT(horarios.idhorario) AS cantidad_horarios FROM citas INNER JOIN horarios ON horarios.idhorario = citas.idhorario WHERE citas.idpaciente = ? AND horarios.fechaatencion = ?;";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            sentencia.setInt(1, id);
            sentencia.setDate(2, Date.valueOf(fecha));
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                resul = resultado.getInt(1);
            }

            return resul;
        } catch (SQLException e) {
            throw new Exception("Error al intentar consultar.", e);
        }
    }

}
