package proyecto.Capa3_Dominio;

import java.util.Date;

public class Paciente {

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNombrepaciente() {
        return nombrepaciente;
    }

    public void setNombrepaciente(String nombrepaciente) {
        this.nombrepaciente = nombrepaciente;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    private int idpaciente;
    private String nombrepaciente;
    private Date fechanacimiento;
    private String sexo;
    private String DNI;
    private String Telefono;
    private String Correo;



    public boolean dniValido(String DNI) {
        return !DNI.isEmpty() && DNI.length() == 8;
    }

    public boolean nombreValido() {
        return !nombrepaciente.isEmpty();
    }

    public boolean telefonoValido(String Telefono) {
        if (Telefono.length() == 9) {
            return Telefono.startsWith("9");
        }
        return false;
    }

}
