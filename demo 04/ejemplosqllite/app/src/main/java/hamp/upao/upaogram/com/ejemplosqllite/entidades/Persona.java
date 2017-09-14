package hamp.upao.upaogram.com.ejemplosqllite.entidades;

import java.io.Serializable;

/**
 * Created by hamp on 13/09/2017.
 */

public class Persona implements Serializable {

    private Integer dni ;
    private String nombre;
    private String telefono;

    public Persona(Integer dni, String nombre, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Persona(){

    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
