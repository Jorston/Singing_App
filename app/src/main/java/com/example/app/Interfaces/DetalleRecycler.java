package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.app.R;

public class DetalleRecycler extends AppCompatActivity {

    TextView textoEjempo;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalle_recycler);

        if(getSupportActionBar() != null)

            getSupportActionBar().hide();

        textoEjempo = (TextView) findViewById(R.id.textDescripcion);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            value = extras.getString("envio");
        }

        textoEjempo.setText(value);
    }
}
