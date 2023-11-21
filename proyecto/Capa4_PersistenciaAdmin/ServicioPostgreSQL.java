package proyecto.Capa4_PersistenciaAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import proyecto.Capa3_DominioAdmin.Servicio;

public class ServicioPostgreSQL {

    private final AccesoDatosJDBC accesoDatosJDBC;

    public ServicioPostgreSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public List<Servicio> buscarServicio(String nombreDoctor) throws Exception {
        if (nombreDoctor == null) {
            throw new IllegalArgumentException("nombreDoctor no puede ser nulo");
        }

        String consultaSQL = "SELECT s.nombreservicio, s.precio, s.tiempodemora "
                + "FROM servicios s "
                + "JOIN doctor d ON s.iddoctor = d.iddoctor "
                + "WHERE d.nombredoctor = ?;";

        PreparedStatement sentencia;

        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setString(1, nombreDoctor);
            ResultSet resultado = sentencia.executeQuery();

            List<Servicio> servicios = new ArrayList<>();

            while (resultado.next()) {
                String nombreServicio = resultado.getString("nombreservicio");
                double precio = resultado.getDouble("precio");
                String tiempoDemora = resultado.getString("tiempodemora");

                Servicio servicio = new Servicio();
                servicio.setNombreServicio(nombreServicio);
                servicio.setPrecio(precio);
                servicio.setDemoraTiempo(tiempoDemora);

                servicios.add(servicio);
            }

            if (servicios.isEmpty()) {
                throw new Exception("No existen servicios para este doctor.");
            }

            return servicios;
        } catch (Exception e) {
            throw new Exception("No existen servicios para este doctor", e);
        }
    }

    public void listarServicio(JComboBox CBOServi) throws Exception {
        String consultaSQL = "select nombreservicio from servicios";
        System.out.println("Listar");
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            ResultSet resultado = sentencia.executeQuery();
            CBOServi.addItem("Seleccione una opcion:");
            while (resultado.next()) {
                CBOServi.addItem(resultado.getString("nombreservicio"));
            }

        } catch (Exception e) {
            throw new Exception("Error al intentar al listar el  tipo de Cita", e);
        }
    }

    public double buscarPrecio(String nombreservicio) throws Exception {
        String consultaSQL = "select precio from servicios where nombreservicio = ?";
        double precio;
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setString(1, nombreservicio);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                precio = resultado.getDouble("precio");
                return precio;
            } else {
                throw new Exception("No existe el servicio.");
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar buscar el servicio", e);
        }
    }

    public void guardar(Servicio servicio) throws Exception {
        String insertSQL = "INSERT INTO servicios(nombreservicio, precio, tiempodemora, iddoctor) VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = null;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, servicio.getNombreServicio());
            sentencia.setDouble(2, servicio.getPrecio());
            sentencia.setString(3, servicio.getDemoraTiempo());
            sentencia.setInt(4, servicio.getDoctor().getIddoctor());

            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "SE REGISTRO", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR ---INGRESE DE NUEVO" + e);
        } finally {

            if (sentencia != null) {
                sentencia.close();
            }
        }
    }
}
