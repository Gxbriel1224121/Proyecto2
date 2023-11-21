package proyecto.Capa2_Aplicacion;

import proyecto.Capa3_Dominio.Cita;
import proyecto.Capa3_Dominio.Paciente;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBC;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBCPostgreSQL;
import proyecto.Capa4_Persistencia.CitaPostgreSQL;
import proyecto.Capa4_PersistenciaAdmin.HorarioPostgreSQL;
import proyecto.Capa4_Persistencia.PacientePostgreSQL;
import javax.swing.table.DefaultTableModel;
import proyecto.Capa3_DominioAdmin.Horario;

public class RegistrarCitaServicio {

    private final AccesoDatosJDBC accesoDatosJDBC;
    private final HorarioPostgreSQL horarioPostgreSQL;
    private final PacientePostgreSQL pacientePostgreSQL;
    private final CitaPostgreSQL citaPostgreSQL;

    public RegistrarCitaServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        horarioPostgreSQL = new HorarioPostgreSQL(accesoDatosJDBC);
        pacientePostgreSQL = new PacientePostgreSQL(accesoDatosJDBC);
        citaPostgreSQL = new CitaPostgreSQL(accesoDatosJDBC);

    }

    public void guardarcita(Cita cita, String horaentrada, String doctorentrada, String fechaentrada) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        int idpaciente = cita.getPacientes().getIdpaciente();
        int resultadosqlcantidad = citaPostgreSQL.maximohora(idpaciente, fechaentrada);
        accesoDatosJDBC.terminarTransaccion();
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        String ultimahorasql = citaPostgreSQL.ultimacitahora(idpaciente, fechaentrada);
        accesoDatosJDBC.terminarTransaccion();
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        String doctor = citaPostgreSQL.Consultardoc(idpaciente, fechaentrada);
        accesoDatosJDBC.terminarTransaccion();
        if (!cita.validarordenhoras(ultimahorasql, horaentrada, doctor, doctorentrada, resultadosqlcantidad)) {
            throw new Exception("Error ");
        }
        int idhorario = cita.getHorario().getIdhorario();
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        citaPostgreSQL.guardar(cita);
        accesoDatosJDBC.terminarTransaccion();
        modificarestadohorario(idhorario);
    }

    public DefaultTableModel listarhoras(String fecha) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = citaPostgreSQL.Listarhoras(fecha);
        accesoDatosJDBC.terminarTransaccion();
        return modelo;
    }

    public Paciente buscarpaciente(String dni) throws Exception {

        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Paciente paciente = pacientePostgreSQL.buscarpaciente(dni);
        accesoDatosJDBC.terminarTransaccion();
        return paciente;
    }

    public DefaultTableModel ListarReporte(Horario horario) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = citaPostgreSQL.Reportedecitas(horario);
        accesoDatosJDBC.terminarTransaccion();
        return modelo;
    }

    public void eliminarCita(Cita cita) throws Exception {
        try {
            accesoDatosJDBC.abrirConexion();
            accesoDatosJDBC.iniciarTransaccion();
            citaPostgreSQL.eliminarCita(cita);
            accesoDatosJDBC.terminarTransaccion();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (accesoDatosJDBC != null) {
            }
        }
    }

    public void modificarestadohorario(int id) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        horarioPostgreSQL.editarestadohorario(id);
        accesoDatosJDBC.terminarTransaccion();
    }

}
