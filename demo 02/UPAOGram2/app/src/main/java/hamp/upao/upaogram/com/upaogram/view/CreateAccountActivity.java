package hamp.upao.upaogram.com.upaogram.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import hamp.upao.upaogram.com.upaogram.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Para llamar un recurso getResources
        showToolbar(getResources().getString(R.string.toolbar_tittle_createaccount),true);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//Support para que tenga soporte a versiones inferiores a lolipop
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
