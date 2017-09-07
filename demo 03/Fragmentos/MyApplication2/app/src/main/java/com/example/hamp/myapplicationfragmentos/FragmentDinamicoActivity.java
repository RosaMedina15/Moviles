package com.example.hamp.myapplicationfragmentos;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FragmentDinamicoActivity extends AppCompatActivity implements
            FragmentVerde.OnFragmentInteractionListener,
            FragmentRojo.OnFragmentInteractionListener{

    FragmentRojo fragmentRojo;
    FragmentVerde fragmentVerde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dinamico);

        fragmentRojo=new FragmentRojo();
        fragmentVerde=new FragmentVerde();


        //TODO: Indicamos el fragmento que inicia
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,fragmentRojo).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClick(View view) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.btnRojo:
                fragmentTransaction.replace(R.id.contenedorFragments,fragmentRojo);
                break;
            case R.id.btnVerde:
                fragmentTransaction.replace(R.id.contenedorFragments,fragmentVerde);
                break;
        }
        fragmentTransaction.commit();
    }
}
