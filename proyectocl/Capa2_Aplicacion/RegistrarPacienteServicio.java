package proyecto.Capa2_Aplicacion;

import proyecto.Capa3_Dominio.Paciente;
import proyecto.Capa4_Persistencia.PacientePostgreSQL;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyecto.Capa4_PersistenciaAdmin.AccesoDatosJDBCPostgreSQL;

public class RegistrarPacienteServicio {  

    private final PacientePostgreSQL pacientePostgreSQL;
    private final AccesoDatosJDBCPostgreSQL accesoDatosJDBC;
    private Paciente paciente;

    public RegistrarPacienteServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        pacientePostgreSQL = new PacientePostgreSQL(accesoDatosJDBC);

    }


    public DefaultTableModel listarpaciente(String dni) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = pacientePostgreSQL.ListarPaciente(dni);
        accesoDatosJDBC.terminarTransaccion();

        return modelo;
    }

    public void guardarPaciente(Paciente paciente) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        pacientePostgreSQL.guardarPaciente(paciente);
        accesoDatosJDBC.terminarTransaccion();
    }

}
