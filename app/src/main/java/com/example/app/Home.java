package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements Primero.OnFragmentInteractionListener {
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

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment pri = new Primero();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_general,pri);
                transaction.commit();

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
