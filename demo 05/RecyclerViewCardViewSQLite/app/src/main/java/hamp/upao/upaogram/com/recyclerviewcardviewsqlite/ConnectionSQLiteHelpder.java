package hamp.upao.upaogram.com.recyclerviewcardviewsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hamp.upao.upaogram.com.recyclerviewcardviewsqlite.util.Utilities;

/**
 * Created by hamp on 19/09/2017.
 */

public class ConnectionSQLiteHelpder extends SQLiteOpenHelper {

    public ConnectionSQLiteHelpder(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_PRODUCT);
        onCreate(db);
    }
}