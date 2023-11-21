package proyecto.Capa4_Persistencia;

import proyecto.Capa3_Dominio.Paciente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBC;

public class PacientePostgreSQL {

    private final AccesoDatosJDBC accesoDatosJDBC;

    public PacientePostgreSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

public void guardarPaciente(Paciente paciente) throws Exception {
    if (paciente.getNombrepaciente().length() < 3) {
        throw new Exception("El nombre del paciente debe tener al menos 3 caracteres.");
    }

    if (paciente.getDNI().length() != 8) {
        throw new Exception("El DNI debe tener 8 caracteres.");
    }

    if (!paciente.getCorreo().toLowerCase().endsWith("@gmail.com")) {
        throw new Exception("El correo electrónico debe ser de formato @gmail.com.");
    }

    if (paciente.getTelefono().length() != 9) {
        throw new Exception("El número de teléfono debe tener 9 caracteres.");
    }

    int edad = calcularEdad((Date) paciente.getFechanacimiento());

    String insertSQL = "insert into paciente(nombrepaciente, dni, sexo, fechanacimiento, telefono, correo) "
            + "values(?,?,?,?,?,?)";
    try {
        PreparedStatement sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
        sentencia.setString(1, paciente.getNombrepaciente());
        sentencia.setString(2, paciente.getDNI());
        sentencia.setString(3, paciente.getSexo());
        sentencia.setDate(4, (Date) paciente.getFechanacimiento()); // Utilizar setDate directamente
        sentencia.setString(5, paciente.getTelefono());
        sentencia.setString(6, paciente.getCorreo());
        sentencia.executeUpdate();

        String mensaje = (edad >= 18) ? "El paciente es mayor de edad y tiene " + edad + " años."
                                       : "El paciente es menor de edad y tiene " + edad + " años.";
        JOptionPane.showMessageDialog(null, "El paciente se registro correctamente\n" + mensaje, "INFORMACION", JOptionPane.WARNING_MESSAGE);

    } catch (Exception e) {
        throw new Exception("El registro del paciente no pudo realizarse.", e);
    }
}

public int calcularEdad(Date fechaNacimiento) {
    LocalDate fechaNacimientoLocal = fechaNacimiento.toLocalDate();
    LocalDate fechaActual = LocalDate.now();
    return Period.between(fechaNacimientoLocal, fechaActual).getYears();
}



    public Paciente buscarpaciente(String dni) throws Exception {
        String sql = "Select  idpaciente ,nombrepaciente,dni,sexo,fechanacimiento,telefono,correo  from paciente where dni=?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            sentencia.setString(1, dni);

            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdpaciente(resultado.getInt("idpaciente"));
                paciente.setNombrepaciente(resultado.getString("nombrepaciente"));
                paciente.setDNI(resultado.getString("dni"));
                paciente.setSexo(resultado.getString("sexo"));
                paciente.setFechanacimiento(resultado.getDate("fechanacimiento"));
                paciente.setTelefono(resultado.getString("telefono"));
                paciente.setCorreo(resultado.getString("Correo"));

                return paciente;
            } else {
                throw new Exception("No existe el Paciente.");
            }

        } catch (Exception e) {
            throw new Exception("Paciente no registrado");
        }

    }

    public DefaultTableModel ListarPaciente(String dni) throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "Select * from paciente";

        PreparedStatement sentencia;
        try {
            modelo.addColumn("ID");
            modelo.addColumn("APELLIDOS Y NOMBRES");
            modelo.addColumn("DNI");
            modelo.addColumn("SEXO");
            modelo.addColumn("FECHA NACIMIENTO");
            modelo.addColumn("TELEFONO");
            modelo.addColumn("CORREO");

            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            //  sentencia.setDate(1, Date.valueOf(horario.getFechaatencion().toString()));
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

}
