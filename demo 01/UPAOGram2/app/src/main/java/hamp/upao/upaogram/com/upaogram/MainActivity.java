package hamp.upao.upaogram.com.upaogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hamp.upao.upaogram.com.upaogram.view.CreateAccountActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goCreateAccount(View view){
        //Intent explicito
        Intent intent=new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
