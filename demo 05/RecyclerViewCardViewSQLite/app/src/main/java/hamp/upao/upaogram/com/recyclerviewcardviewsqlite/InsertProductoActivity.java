package hamp.upao.upaogram.com.recyclerviewcardviewsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hamp.upao.upaogram.com.recyclerviewcardviewsqlite.util.Utilities;

public class InsertProductoActivity extends AppCompatActivity {

    EditText Id,Name,Quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_producto);


        Name= (EditText) findViewById(R.id.name);
        Quantity= (EditText) findViewById(R.id.quantity);

    }

    public void onClick(View view) {
        insertProduct();
        Clear();
    }

    private void insertProduct() {
        ConnectionSQLiteHelpder conn=new ConnectionSQLiteHelpder(this,"dbmercado",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();

        if( !Name.getText().toString().equals("") && !Quantity.getText().toString().equals("")){
            values.put(Utilities.COLUMN_NAME,Name.getText().toString());
            values.put(Utilities.COLUMN_QUANTITY,Quantity.getText().toString());

            Long idResult=db.insert(Utilities.TABLE_PRODUCT,Utilities.COLUMN_ID_PRODUCT,values);

            Toast.makeText(getApplicationContext(),"Id Product: "+idResult,Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Empty Form",Toast.LENGTH_SHORT).show();
        }


        db.close();
    }

    public void Clear(){
        Name.setText("");
        Quantity.setText("");
    }
}
