package com.example.hamp.myapplicationfragmentos;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//TODO:https://developer.android.com/guide/components/fragments.html?hl=es-419
public class MainActivity extends AppCompatActivity implements
        FragmentRojo.OnFragmentInteractionListener,
        FragmentVerde.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
