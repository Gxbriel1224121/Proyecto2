package proyecto.Capa3_Dominio;

import proyecto.Capa3_DominioAdmin.Horario;

public class Cita {

    private int idcita;
    public Paciente pacientes;
    public Horario horario;
    private String observacion;
    private String servicio;

    public boolean validarordenhoras(String horasql, String horaingreso, String doctorsql, String doctoringreso, int cantidad) throws Exception {
        boolean validar = false;

        if (horasql.equals("") || doctorsql.equals("")) {
            validar = true;
            if (horasql.equals("") && doctorsql.equals("")) {
                validar = true;
            }
        } else {
            String[] HoraMinArray = horasql.split(":");
            int horaanterior = Integer.parseInt(HoraMinArray[0]);
            int minutoanterior = Integer.parseInt(HoraMinArray[1]);
            String[] HoraMinArray2 = horaingreso.split(":");
            int horaposterior = Integer.parseInt(HoraMinArray2[0]);
            int minutoposterior = Integer.parseInt(HoraMinArray2[1]);
            int tiempoanterior = horaanterior * 60 + minutoanterior;
            int tiempoposterior = horaposterior * 60 + minutoposterior;
            if ((tiempoposterior - tiempoanterior) % 30 == 0) {
                if (!doctorsql.equals(doctoringreso)) {
                    validar = false;
                    throw new Exception("Se debe seleccionar el mismo doctor");
                } else if (validarcantidadhoraspordia(cantidad)) {
                    validar = true;
                } else {
                    validar = false;
                    throw new Exception("Se llego al limite de horas por cita");
                }
            }

        }

        return validar;
    }

    public boolean validarcantidadhoraspordia(int cantidad) {

        boolean validar;
        if (cantidad == 0) {
            validar = true;
        }
        if (cantidad <= 1) {
            validar = true;
        } else {
            validar = false;
        }
        return validar;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public Paciente getPacientes() {
        return pacientes;
    }

    public void setPacientes(Paciente pacientes) {
        this.pacientes = pacientes;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
