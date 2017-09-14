package hamp.upao.upaogram.com.ejemplosqllite.utilidades;

/**
 * Created by hamp on 13/09/2017.
 */


public class Utilidades {
    //Constantes campos tabla persona
    public static final String TABLA_PERSONA="persona";
    public static final String CAMPO_DNI="dni";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";

    public static final String CREAR_TABLA_PERSONA="CREATE TABLE " +
            ""+TABLA_PERSONA+" ("+CAMPO_DNI+" " +
            "INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_TELEFONO+" TEXT)";

    //Constantes campos tabla mascota
    public static final String TABLA_MASCOTA="mascota";
    public static final String CAMPO_ID_MASCOTA="id_mascota";
    public static final String CAMPO_NOMBRE_MASCOTA="nombre_mascota";
    public static final String CAMPO_RAZA_MASCOTA="raza_mascota";
    public static final String CAMPO_ID_DUENIO="id_duenio";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT,"+CAMPO_ID_DUENIO+" INTEGER)";
}
