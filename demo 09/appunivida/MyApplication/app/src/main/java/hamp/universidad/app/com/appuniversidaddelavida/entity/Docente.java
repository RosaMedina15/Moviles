package hamp.universidad.app.com.appuniversidaddelavida.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by hamp on 08/11/2017.
 */

public class Docente {
    private Integer id;
    private Integer codeu;
    private String nombre;
    private String apellido;

    private String dato;
    private Bitmap imagen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodeu() {
        return codeu;
    }

    public void setCodeu(Integer codeu) {
        this.codeu = codeu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;

        try {
            //Decodificar dato para presentarlo como imagen
            byte[] byteCode= Base64.decode(dato, Base64.DEFAULT);

            int alto=200;
            int ancho=200;

            //Asignarle a imagen la  informacion decodificada
            Bitmap foto= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.imagen=Bitmap.createScaledBitmap(foto,alto,ancho,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
