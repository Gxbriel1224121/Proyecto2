package proyecto.Capa2_AplicacionAdmin;

import proyecto.Capa3_DominioAdmin.Horario;
import proyecto.Capa3_DominioAdmin.Analista;
import proyecto.Capa4_PersistenciaAdmin.HorarioPostgreSQL;
import java.util.Vector;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBC;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBCPostgreSQL;
import proyecto.Capa4_PersistenciaAdmin.AnalistaPostgreSQL;

public class RegistrarHorarioServicio {

    private final AccesoDatosJDBC accesoDatosJDBC;
    private final AnalistaPostgreSQL doctorPostgreSQL;
    private final HorarioPostgreSQL horarioPostgreSQL;

    public RegistrarHorarioServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        doctorPostgreSQL = new AnalistaPostgreSQL(accesoDatosJDBC);
        horarioPostgreSQL = new HorarioPostgreSQL(accesoDatosJDBC);
    }

    public void guardarHorario(Horario horario, String hinicio, String hfinal) throws Exception {

        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        String[][] listagenerar;
        listagenerar = horario.Generarhorario(hinicio, hfinal, horario);
        Vector listasql;
        listasql = horarioPostgreSQL.ConsultarLista(horario);
        accesoDatosJDBC.terminarTransaccion();

        if (!horario.validarigualdehoras(listasql, listagenerar)) {
            throw new Exception("Error, no se puede sobreescribir horarios");
        }
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        horarioPostgreSQL.guardarhorario2(horario, hinicio, hfinal, listagenerar);
        accesoDatosJDBC.terminarTransaccion();

    }

    public Analista buscardoctor(String dni) throws Exception {
        accesoDatosJDBC.abrirConexion();
        Analista doctor = doctorPostgreSQL.buscardoctor(dni);
        accesoDatosJDBC.cerrarConexion();
        return doctor;
    }

}
