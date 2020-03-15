package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.app.R;

public class AdminHome extends AppCompatActivity implements View.OnClickListener, AdminHomeFragment.OnFragmentInteractionListener,ListadoDepartamentos.OnFragmentInteractionListener {

    Button boton1,boton2,boton3,boton4,boton5,boton6;

    String recuperamos_variable_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_home);

        recuperamos_variable_string = getIntent().getStringExtra("usuario");

        Bundle bundle = new Bundle();

        Intent intent = new Intent(getApplicationContext(), Primero.class);

        bundle.putString("usuarioLogin",recuperamos_variable_string);

        intent.putExtras(bundle);

        boton1 = findViewById(R.id.btn1admin);

        boton2 = findViewById(R.id.btn2admin);

        boton3 = findViewById(R.id.btn3admin);

        boton4 = findViewById(R.id.btn4admin);

        boton5 = findViewById(R.id.btn5admin);

        boton6 = findViewById(R.id.btn6admin);

        boton1.setOnClickListener(this);

        boton2.setOnClickListener(this);

        boton3.setOnClickListener(this);

        boton4.setOnClickListener(this);

        boton5.setOnClickListener(this);

        boton6.setOnClickListener(this);

        boton1.setBackgroundColor(Color.parseColor("#cfcfcf"));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn1admin:

                cambioColor("boton1");

                Fragment primero = new AdminHomeFragment();

                FragmentTransaction transactionuno = getSupportFragmentManager().beginTransaction();

                transactionuno.replace(R.id.contenedor_general,primero);

                transactionuno.commit();

                break;

            case R.id.btn2admin:

                cambioColor("boton2");

                Fragment tercero = new AdminCreateDepart();

                FragmentTransaction transactiontres = getSupportFragmentManager().beginTransaction();

                transactiontres.replace(R.id.contenedor_general,tercero);

                transactiontres.commit();

                break;

            case R.id.btn3admin:

                cambioColor("boton3");

                Fragment terceroAdmin = new FragmentCreateEmpleFirebase();

                FragmentTransaction transactiontresAmin = getSupportFragmentManager().beginTransaction();

                transactiontresAmin.replace(R.id.contenedor_general,terceroAdmin);

                transactiontresAmin.commit();

                break;

            case R.id.btn4admin:

                cambioColor("boton4");

                Fragment cuartoAdmin = new ListadoDepartamentos();

                FragmentTransaction transactioncuatroAdmin = getSupportFragmentManager().beginTransaction();

                transactioncuatroAdmin.replace(R.id.contenedor_general,cuartoAdmin);

                transactioncuatroAdmin.commit();

                break;

            case R.id.btn5admin:

                cambioColor("boton5");

                Fragment quinto = new Quinto();

                FragmentTransaction transactioncinco = getSupportFragmentManager().beginTransaction();

                transactioncinco.replace(R.id.contenedor_general,quinto);

                transactioncinco.commit();

                break;

            case R.id.btn6admin:

                cambioColor("boton6");

                Fragment sexto = new Sexto();

                FragmentTransaction transactionsexto = getSupportFragmentManager().beginTransaction();

                transactionsexto.replace(R.id.contenedor_general,sexto);

                transactionsexto.commit();

                break;
        }
    }
    public void cambioColor(String cambio){

        switch (cambio){
            case"boton1":
                boton1.setBackgroundColor(Color.parseColor("#cfcfcf"));
                boton3.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton2.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton4.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton5.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton6.setBackgroundColor(Color.parseColor("#f5f5f5"));
                break;

            case"boton2":
                boton1.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton2.setBackgroundColor(Color.parseColor("#cfcfcf"));
                boton3.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton4.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton5.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton6.setBackgroundColor(Color.parseColor("#f5f5f5"));
                break;

            case"boton3":
                boton1.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton2.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton3.setBackgroundColor(Color.parseColor("#cfcfcf"));
                boton4.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton5.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton6.setBackgroundColor(Color.parseColor("#f5f5f5"));

                break;

            case"boton4":
                boton1.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton2.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton3.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton4.setBackgroundColor(Color.parseColor("#cfcfcf"));
                boton5.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton6.setBackgroundColor(Color.parseColor("#f5f5f5"));
                break;

            case"boton5":
                boton1.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton2.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton3.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton4.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton5.setBackgroundColor(Color.parseColor("#cfcfcf"));
                boton6.setBackgroundColor(Color.parseColor("#f5f5f5"));
                break;

            case"boton6":
                boton1.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton2.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton3.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton4.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton5.setBackgroundColor(Color.parseColor("#f5f5f5"));
                boton6.setBackgroundColor(Color.parseColor("#cfcfcf"));
                break;
            }
    }
}
