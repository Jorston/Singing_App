package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.app.R;

public class AdminDetallesListDepart extends AppCompatActivity {

    TextView tituloDepart;

    String valorEnviado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_detalles_list_depart);

        tituloDepart = (TextView) findViewById(R.id.textDepartSeleccionado);

        Bundle extrasEnviados = getIntent().getExtras();

        if (extrasEnviados != null){

            valorEnviado = extrasEnviados.getString("datoEnviado");

            tituloDepart.setText(valorEnviado);
        }

    }
}