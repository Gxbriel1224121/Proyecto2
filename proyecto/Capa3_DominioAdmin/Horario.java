package proyecto.Capa3_DominioAdmin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Horario {

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public Date getFechaatencion() {
        return fechaatencion;
    }

    public void setFechaatencion(Date fechaatencion) {
        this.fechaatencion = fechaatencion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Analista getDoctor() {
        return doctor;
    }

    public void setDoctor(Analista doctor) {
        this.doctor = doctor;
    }
    private int idhorario;
    private Date fechaatencion;
    private String hora;
    private Analista doctor;

   public String[][] Generarhorario(String timeinicio, String timefinal, Horario horario) throws ParseException {
    ArrayList<String> horas = new ArrayList<>();

    DateFormat formato = new SimpleDateFormat("HH:mm:ss");

    Date hora = formato.parse(timeinicio);
    Date hora2 = formato.parse(timefinal);
    Calendar calen = GregorianCalendar.getInstance();
    calen.setTime(hora);
    if (calen.getTime().before(hora2)) {
        horas.add(formato.format(calen.getTime()));
        while (calen.getTime().before(hora2)) {
            calen.add(Calendar.MINUTE, 30); 
            horas.add(formato.format(calen.getTime()));
        }

    }

    SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = formatofecha.format(horario.getFechaatencion());
    int cantidadtamaño = horas.size();
    String matriz[][] = new String[cantidadtamaño][2];
    int i = 0;

    if (i < matriz.length) {
        for (int e = 0; e < horas.size(); e++) {
            matriz[i][0] = horas.get(e);
            matriz[i][1] = fecha;
            i++;
        }
    }

    return matriz;
}


    public boolean validarigualdehoras(Vector horassql, String[][] listagenerada) {
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        ArrayList<Integer> listaNumeros2 = new ArrayList<>();
        for (int i = 0; i < listagenerada.length; i++) {
            String[] datos = listagenerada[i][0].toString().split(":");
            for (String dato : datos) {
                listaNumeros.add(Integer.parseInt(dato));
            }
        }

        Set<Integer> hashset = new HashSet<Integer>(listaNumeros);
        listaNumeros.clear();
        listaNumeros.addAll(hashset);
        listaNumeros.remove(new Integer(0));
        boolean validar = false;
        if (horassql == null) {
            return validar = true;
        } else {
            for (int x = 0; x < horassql.size(); x++) {
                String[] datos = horassql.get(x).toString().split(":");

                for (String dato : datos) {

                    listaNumeros2.add(Integer.parseInt(dato));

                }
            }

            Set<Integer> hashset2 = new HashSet<Integer>(listaNumeros2);
            listaNumeros2.clear();
            listaNumeros2.addAll(hashset2);
            listaNumeros2.remove(new Integer(0));

            for (int i = 0; i < listaNumeros.size(); i++) {

                for (int j = 0; j < listaNumeros2.size(); j++) {

                    if (listaNumeros.get(i).equals(listaNumeros2.get(j))) {
                        return validar = false;
                    }
                }
            }

            return validar = true;
        }

    }

}
