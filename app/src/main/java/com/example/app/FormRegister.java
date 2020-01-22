package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.data.ConexionSQLiteHelper;
import com.example.app.data.Escrituras;

public class FormRegister extends AppCompatActivity {
    //declaracion de variables globales e instancia de objeto Escritura
    Escrituras escrituras = new Escrituras();
    Button buton,btnInsert;
    TextView nombre,apellidos,correo,userNick,contrasenha,repContrasenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //variables enlazada con elementos por medio de id
        setContentView(R.layout.activity_form_register);
        nombre = findViewById(R.id.textnombre);
        apellidos = findViewById(R.id.textapellido);
        userNick = findViewById(R.id.userNick);
        correo = findViewById(R.id.userCorreo);
        contrasenha = findViewById(R.id.textcontrasenha);
        repContrasenha = findViewById(R.id.textRepContrasenha);
        buton = findViewById(R.id.btnFormRegister);
        btnInsert = findViewById(R.id.btnBaseDatos);
        //ponemos valor al contexto mediante del mismo Activity
        escrituras.setContext(this);
        // boton FormRegister Registro ejecuta metodo validador y escritura de archivo
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //metodo valida y escribe archivo si no existe
                if(escrituras.validadorFichero(nombre.getText().toString(),apellidos.getText().toString(),userNick.getText().toString(),correo.getText().toString(),contrasenha.getText().toString(),repContrasenha.getText().toString())){
                    showMessage("No existia el archi fue creado");
                }else{
                    //metodo serializacion de fichero ya con archivo existente
                    escrituras.serializadionOuput(nombre.getText().toString(),apellidos.getText().toString(),userNick.getText().toString(),correo.getText().toString(),contrasenha.getText().toString(),repContrasenha.getText().toString());
                    showMessage("Usuario ingresado en el archivo");
                }
            }
        });
        // boton FormRegister Insertar ejecuta metodo insercion en base de datos
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionSQLiteHelper helper = new ConexionSQLiteHelper(getApplicationContext());
                helper.abrir();
                helper.insertar(nombre.getText().toString(),apellidos.getText().toString(),correo.getText().toString(),userNick.getText().toString(),contrasenha.getText().toString(),repContrasenha.getText().toString());
                helper.cerrar();
                showMessage("usuario insertado en la base de datos");
            }
        });
    }
    //metodo simplifica toask y lo ejecuta en una funcion
    protected void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}