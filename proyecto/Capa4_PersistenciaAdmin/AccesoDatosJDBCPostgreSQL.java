/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.Capa4_PersistenciaAdmin;

import java.sql.DriverManager;


public class AccesoDatosJDBCPostgreSQL extends AccesoDatosJDBC {

    @Override
     public void abrirConexion() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/basedatos";
            conexion = DriverManager.getConnection(url, "postgres", "123456");
        } catch (Exception e) {
            throw new Exception("Ocurrió un problema en la conexión con la base de datos." + e);
        }
    }
}
