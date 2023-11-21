package proyecto.Capa2_AplicacionAdmin;


import proyecto.Capa3_DominioAdmin.Analista;
import proyecto.Capa3_DominioAdmin.Servicio;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBC;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBCPostgreSQL;
import proyecto.Capa4_PersistenciaAdmin.AnalistaPostgreSQL;
import proyecto.Capa4_PersistenciaAdmin.ServicioPostgreSQL;

public class RegistrarServicio {

    private final AccesoDatosJDBC accesoDatosJDBC;
    private final AnalistaPostgreSQL doctorPostgreSQL;
    private final ServicioPostgreSQL servicioPostgreSQL;

    public RegistrarServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        doctorPostgreSQL = new AnalistaPostgreSQL(accesoDatosJDBC);
        servicioPostgreSQL = new ServicioPostgreSQL(accesoDatosJDBC);
    }

    public void guardarServicio(Servicio servicio) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();

        try {
            servicioPostgreSQL.guardar(servicio);
            
            accesoDatosJDBC.terminarTransaccion();
        } catch (Exception e) {
            accesoDatosJDBC.cancelarTransaccion();
            throw e;
        } finally {
            accesoDatosJDBC.cerrarConexion();
        }
    }

    public Analista buscardoctor(String dni) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();

        try {
            Analista doctor = doctorPostgreSQL.buscardoctor(dni);

            accesoDatosJDBC.terminarTransaccion();
            
            return doctor;
        } catch (Exception e) {
            accesoDatosJDBC.cancelarTransaccion();
            throw e;
        } finally {
            accesoDatosJDBC.cerrarConexion();
        }
    }


}
