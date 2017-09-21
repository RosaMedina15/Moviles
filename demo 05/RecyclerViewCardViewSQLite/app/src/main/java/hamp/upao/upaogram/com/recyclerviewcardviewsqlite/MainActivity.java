package hamp.upao.upaogram.com.recyclerviewcardviewsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()) {
            case R.id.btnCreateProduct:
                miIntent = new Intent(MainActivity.this, InsertProductoActivity.class);
                break;

            case R.id.btnListProduct:
                miIntent = new Intent(MainActivity.this, ListProductActivity.class);
                break;
        }

        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}
