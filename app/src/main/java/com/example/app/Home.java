package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener, Primero.OnFragmentInteractionListener,Segundo.OnFragmentInteractionListener,Tercero.OnFragmentInteractionListener,Cuarto.OnFragmentInteractionListener,Quinto.OnFragmentInteractionListener {
    Button boton1,boton2,boton3,boton4,boton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        boton1 = findViewById(R.id.btn1);
        boton2 = findViewById(R.id.btn2);
        boton3 = findViewById(R.id.btn3);
        boton4 = findViewById(R.id.btn4);
        boton5 = findViewById(R.id.btn5);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
        boton5.setOnClickListener(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Fragment primero = new Primero();
                FragmentTransaction transactionuno = getSupportFragmentManager().beginTransaction();
                transactionuno.replace(R.id.contenedor_general,primero);
                transactionuno.commit();
                break;
            case R.id.btn2:
                Fragment segundo = new Segundo();
                FragmentTransaction transactiondos = getSupportFragmentManager().beginTransaction();
                transactiondos.replace(R.id.contenedor_general,segundo);
                transactiondos.commit();
                break;
            case R.id.btn3:
                Fragment tercero = new Tercero();
                FragmentTransaction transactiontres = getSupportFragmentManager().beginTransaction();
                transactiontres.replace(R.id.contenedor_general,tercero);
                transactiontres.commit();
                break;
            case R.id.btn4:
                Fragment cuarto = new Cuarto();
                FragmentTransaction transactioncuatro = getSupportFragmentManager().beginTransaction();
                transactioncuatro.replace(R.id.contenedor_general,cuarto);
                transactioncuatro.commit();
                break;
            case R.id.btn5:
                Fragment quinto = new Quinto();
                FragmentTransaction transactioncinco = getSupportFragmentManager().beginTransaction();
                transactioncinco.replace(R.id.contenedor_general,quinto);
                transactioncinco.commit();
                break;
        }
    }
}
