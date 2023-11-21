/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.Capa4_PersistenciaAdmin;

import proyecto.Capa3_DominioAdmin.Analista;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AnalistaPostgreSQL {

    private final AccesoDatosJDBC accesoDatosJDBC;

    public AnalistaPostgreSQL(AccesoDatosJDBC AccesoDatosJDBC) {
        this.accesoDatosJDBC = AccesoDatosJDBC;
    }

    public Analista buscardoctor(String dni) throws Exception {
        String sql = "SELECT iddoctor, nombredoctor, apellidodoc, dni, edad, telefono, nombredoctor || ' ' || apellidodoc AS nombre_completo FROM doctor WHERE dni =?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(sql);
            sentencia.setString(1, dni);

            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Analista doctor = new Analista();
                doctor.setIddoctor(resultado.getInt("iddoctor"));
                doctor.setApellidoDoc(resultado.getString("ApellidoDoc"));
                doctor.setNombredoctor(resultado.getString("nombredoctor"));
                doctor.setDni(resultado.getString("dni"));
                doctor.setEdad(resultado.getInt("edad"));
                doctor.setTelefono(resultado.getString("telefono"));

                return doctor;
            } else {
                throw new Exception("No existe el Doctor.");
            }

        } catch (Exception e) {
            throw new Exception("Error al intentar buscar el doctor." + e);
        }

    }

    public void guardarDoctor(Analista doctor) throws Exception {
        String insertSQL = "insert into doctor(nombredoctor, apellidodoc, dni, edad, telefono) "
                + "values(?,?,?,?,?)";
        PreparedStatement sentencia;

        try {
            if (doctor.getEdad() < 18) {
                throw new Exception("La edad del doctor debe ser de 18 años o más.");
            }

            if (doctor.getTelefono().length() != 9 || !doctor.getTelefono().startsWith("9")) {
                throw new Exception("El número de teléfono del doctor debe comenzar con 9 y tener 9 dígitos.");
            }

            if (doctor.getNombredoctor().length() < 3) {
                throw new Exception("El nombre del doctor debe tener al menos 3 caracteres.");
            }

            if (doctor.getApellidoDoc().length() < 3) {
                throw new Exception("El apellido del doctor debe tener al menos 3 caracteres.");
            }

            if (doctor.getDni().length() != 8) {
                throw new Exception("El DNI del doctor debe tener 8 caracteres.");
            }

            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, doctor.getNombredoctor());
            sentencia.setString(2, doctor.getApellidoDoc());
            sentencia.setString(3, doctor.getDni());
            sentencia.setInt(4, doctor.getEdad());
            sentencia.setString(5, doctor.getTelefono());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "SE REGISTRO", "INFORMACION", JOptionPane.WARNING_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace(); // Esto imprimirá detalles sobre la excepción en la consola
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
