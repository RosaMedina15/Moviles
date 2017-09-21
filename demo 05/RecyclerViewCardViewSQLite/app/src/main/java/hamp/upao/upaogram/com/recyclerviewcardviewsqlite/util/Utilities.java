package hamp.upao.upaogram.com.recyclerviewcardviewsqlite.util;

/**
 * Created by hamp on 19/09/2017.
 */

public class Utilities {

    //Constantes campos tabla usuario
    public static final String TABLE_PRODUCT="mascota";
    public static final String COLUMN_ID_PRODUCT="id_product";
    public static final String COLUMN_NAME="name_product";
    public static final String COLUMN_QUANTITY="quantity_product";


    public static final String CREATE_TABLE_PRODUCT="CREATE TABLE " +
            ""+TABLE_PRODUCT+" ("+COLUMN_ID_PRODUCT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_NAME+" TEXT, "+COLUMN_QUANTITY+" INTEGER)";
}
