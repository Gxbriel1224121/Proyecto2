/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.Capa3_DominioAdmin;

/**
 *
 * @author Marcos
 */
public class Analista {
    private int iddoctor ;
    private String ApellidoDoc;
    private String nombredoctor;
    private String dni ;
    private String telefono;
    private int edad;

    
    public int getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(int iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getApellidoDoc() {
        return ApellidoDoc;
    }

    public void setApellidoDoc(String ApellidoDoc) {
        this.ApellidoDoc = ApellidoDoc;
    }

    public String getNombredoctor() {
        return nombredoctor;
    }

    public void setNombredoctor(String nombredoctor) {
        this.nombredoctor = nombredoctor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    public boolean dniValido(String DNI) {
        return !DNI.isEmpty() && DNI.length() == 8;
    }

    public boolean nombreValido() {
        return !nombredoctor.isEmpty();
    }
     public boolean apellidoValido() {
        return !ApellidoDoc.isEmpty();
    }

    public boolean telefonoValido(String Telefono) {
        if (Telefono.length() == 9) {
            return Telefono.startsWith("9");
        }
        return false;
    }

}
