package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.Modelos.MformRegister;
import com.example.app.data.BBDD_Helper;
import com.example.app.data.Escrituras;
import com.example.app.data.Estructura_BBDD;

import java.io.IOException;

import static java.security.AccessController.getContext;

public class FormRegister extends AppCompatActivity {
    Escrituras escrituras = new Escrituras();
    Button buton;
    TextView nombre,apellidos,correo,userNick,contrasenha,repContrasenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        nombre = findViewById(R.id.textnombre);
        apellidos = findViewById(R.id.textapellido);
        userNick = findViewById(R.id.userNick);
        correo = findViewById(R.id.userCorreo);
        contrasenha = findViewById(R.id.textcontrasenha);
        repContrasenha = findViewById(R.id.textRepContrasenha);
        buton = findViewById(R.id.btnFormRegister);
        escrituras.setContext(this);
        final BBDD_Helper helper = new BBDD_Helper(this);
        final int[] primarikey = {1};
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //accion serializacion
                try {
                    MformRegister persona = new MformRegister(nombre.getText().toString(),apellidos.getText().toString(),userNick.getText().toString(),correo.getText().toString(),contrasenha.getText().toString(),repContrasenha.getText().toString());
                    escrituras.serializadionOuput(persona);
                    Escrituras esc = new Escrituras();
                    esc.serializadionOuput(persona);
                    showMessage("Usuario Serializado");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //accion insertado en base de datos
                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.NOMBRE_COLUMNA1, primarikey[0]);
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2,nombre.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA3,apellidos.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA4,userNick.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA5,correo.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA6,contrasenha.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA7,repContrasenha.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);
                primarikey[0] += 1;
                showMessage("registro exitoso en base de datos el id es: "+primarikey[0]+" "+newRowId);

            }
        });

    }
    protected void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
