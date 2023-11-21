package proyecto.Capa3_DominioAdmin;

public class Servicio {
    private int idServicio;
    private String nombreServicio;
    private double precio;
    private String demoraTiempo;
 public Analista doctor;

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDemoraTiempo() {
        return demoraTiempo;
    }

    public void setDemoraTiempo(String demoraTiempo) {
        this.demoraTiempo = demoraTiempo;
    }

    public Analista getDoctor() {
        return doctor;
    }

    public void setDoctor(Analista doctor) {
        this.doctor = doctor;
    }
    

}
