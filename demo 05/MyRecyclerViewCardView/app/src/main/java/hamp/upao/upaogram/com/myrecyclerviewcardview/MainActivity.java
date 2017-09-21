package hamp.upao.upaogram.com.myrecyclerviewcardview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import hamp.upao.upaogram.com.myrecyclerviewcardview.adapter.ProductAdapter;
import hamp.upao.upaogram.com.myrecyclerviewcardview.database.SqliteDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SqliteDatabase mDatabase;
    ProductAdapter mAdapter;
    List<Product> allProducts;
    RecyclerView productView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);

        productView = (RecyclerView)findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(linearLayoutManager);
        //productView.setHasFixedSize(true);


        mDatabase = new SqliteDatabase(this);
        allProducts = mDatabase.listProducts();

        //if(allProducts.size() > 0){
          //  productView.setVisibility(View.VISIBLE);
            //mAdapter = new ProductAdapter(this, allProducts);
            //productView.setAdapter(mAdapter);
            update();

        //}else {
          //  productView.setVisibility(View.GONE);
            //Toast.makeText(this, "Sin productos", Toast.LENGTH_LONG).show();
       // }

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                //ProductAdapter mAdapter = (ProductAdapter) productView.getAdapter();
                //int  value = mAdapter.listProducts.get(position).getId();
                int  value = allProducts.get(position).getId();
                mDatabase.deleteProduct(value);
                //update();
                //refresh the activity
                finish();
                startActivity(getIntent());
                Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_LONG).show();

            }
        };

        ItemTouchHelper itemTouchHelper  = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(productView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new quick task
                addTaskDialog();
            }
        });
    }

    public void update(){
        mAdapter = new ProductAdapter(this, allProducts);
        productView.setAdapter(mAdapter);
    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_product_layout, null);

        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText quantityField = (EditText)subView.findViewById(R.id.enter_quantity);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("NUEVO PRODUCTO");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("REGISTRAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final int quantity = Integer.parseInt(quantityField.getText().toString());

                if(TextUtils.isEmpty(name) || quantity <= 0){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
                else{
                    Product newProduct = new Product(name, quantity);
                    mDatabase.addProduct(newProduct);

                    //refresh the activity
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Tarea Cancelada", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }
}
