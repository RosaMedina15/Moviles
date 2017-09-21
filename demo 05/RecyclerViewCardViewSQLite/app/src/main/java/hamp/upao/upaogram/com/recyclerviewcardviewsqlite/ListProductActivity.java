package hamp.upao.upaogram.com.recyclerviewcardviewsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import hamp.upao.upaogram.com.recyclerviewcardviewsqlite.adapter.ListProductAdapter;
import hamp.upao.upaogram.com.recyclerviewcardviewsqlite.entity.Product;
import hamp.upao.upaogram.com.recyclerviewcardviewsqlite.util.Utilities;

public class ListProductActivity extends AppCompatActivity {

    ArrayList<Product> listProducts;
    RecyclerView recyclerViewProducts;
    ConnectionSQLiteHelpder conn;
    ListProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        conn=new ConnectionSQLiteHelpder(this,"dbmercado",null,1);

        listProducts=new ArrayList<>();

        recyclerViewProducts= (RecyclerView) findViewById(R.id.recyclerProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        consultListProducts();

        adapter=new ListProductAdapter(listProducts);
        recyclerViewProducts.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                int  id = listProducts.get(position).getId();
                deleteProduct(id);
                finish();
                startActivity(getIntent());

            }
        };

        ItemTouchHelper itemTouchHelper  = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewProducts);
    }


    private void consultListProducts() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Product product=null;

        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilities.TABLE_PRODUCT,null);

        while (cursor.moveToNext()){
            product=new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));

            listProducts.add(product);
        }
    }

    public void deleteProduct(int id){
       String[] whereArgs = new String[] {String.valueOf(id)};
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();

        db.delete(Utilities.TABLE_PRODUCT,"id_product=? ",whereArgs);

        db.close();

    }
}
