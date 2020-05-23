package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import com.example.app.Json.ConexionJson;
import com.example.app.R;

public class AdminDetallesJson extends AppCompatActivity {

    String valorDepart;

    RecyclerView recyclerJson;

    TextView tituloDepartJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detalles_json);

        if(getSupportActionBar() != null)

            getSupportActionBar().hide();

        tituloDepartJson = (TextView) findViewById(R.id.tituloDepartJson);

        recyclerJson = (RecyclerView) findViewById(R.id.recyclerDepartJson);

        Bundle extrasEnviados = getIntent().getExtras();

        if (extrasEnviados != null){

            valorDepart = extrasEnviados.getString("datoEnviado");
        }

        tituloDepartJson.setText(valorDepart);

        ConexionJson conexionJson = new ConexionJson();

        conexionJson.execute("https://appfirebaseproject-b2083.firebaseio.com/Departamentos/"+valorDepart+".json");
    }
}
