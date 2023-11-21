package proyecto.Capa2_AplicacionAdmin;

import java.util.List;
import proyecto.Capa3_DominioAdmin.Analista;
import proyecto.Capa3_DominioAdmin.Servicio;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBC;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBCPostgreSQL;
import proyecto.Capa4_PersistenciaAdmin.AnalistaPostgreSQL;
import proyecto.Capa4_PersistenciaAdmin.ServicioPostgreSQL;

public class RegistrarAnalistaServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private AnalistaPostgreSQL doctorPostgreSQL;
    private ServicioPostgreSQL servicioPostgreSQL;

    public RegistrarAnalistaServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        doctorPostgreSQL = new AnalistaPostgreSQL(accesoDatosJDBC);
        servicioPostgreSQL = new ServicioPostgreSQL(accesoDatosJDBC);
    }

    public void guardarDoctor(Analista doctor) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        doctorPostgreSQL.guardarDoctor(doctor);
        accesoDatosJDBC.terminarTransaccion();
    }

    public List<Servicio> buscarServicio(String nombreDoctor) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Servicio> servicios = servicioPostgreSQL.buscarServicio(nombreDoctor);
        accesoDatosJDBC.terminarTransaccion();
        return servicios;
    }

}
