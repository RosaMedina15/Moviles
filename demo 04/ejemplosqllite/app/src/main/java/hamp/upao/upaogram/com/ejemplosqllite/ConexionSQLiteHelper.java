package hamp.upao.upaogram.com.ejemplosqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hamp.upao.upaogram.com.ejemplosqllite.utilidades.Utilidades;

/**
 * Created by hamp on 13/09/2017.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_PERSONA);
        db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PERSONA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MASCOTA);
        onCreate(db);
    }
}
