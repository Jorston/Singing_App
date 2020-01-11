package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.data.ConexionSQLiteHelper;
import com.example.app.data.Escrituras;
import com.example.app.data.UtilidadesDB;

import java.io.IOException;

import static java.security.AccessController.getContext;

public class FormRegister extends AppCompatActivity {
    Escrituras escrituras = new Escrituras();
    Button buton,btnInsert;
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
        btnInsert = findViewById(R.id.btnBaseDatos);
        escrituras.setContext(this);
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"db_usuarios",null,1);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //accion serializacion
                try {
                    escrituras.serializadionOuput(nombre.getText().toString(),apellidos.getText().toString(),userNick.getText().toString(),correo.getText().toString(),contrasenha.getText().toString(),repContrasenha.getText().toString());
                    showMessage("Usuario Serializado");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insercion();
            }
        });
    }

    private void insercion() {
        nombre = findViewById(R.id.textnombre);
        apellidos = findViewById(R.id.textapellido);
        userNick = findViewById(R.id.userNick);
        correo = findViewById(R.id.userCorreo);
        contrasenha = findViewById(R.id.textcontrasenha);
        repContrasenha = findViewById(R.id.textRepContrasenha);
        int contador = 1;
        ConexionSQLiteHelper dbHelper = new ConexionSQLiteHelper(this,"usuariosTodos",null,1);
        System.out.println("CONEXION A LA BASE DE DATOS");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        System.out.println("CONEXION A LA BASE DE DATOS AAAAAAAAAA");
        ContentValues values = new ContentValues();
        values.put(UtilidadesDB.CAMPO_ID,contador);
        values.put(UtilidadesDB.CAMPO_NOMBRE,nombre.getText().toString());
        values.put(UtilidadesDB.CAMPO_APELLIDOS,apellidos.getText().toString());
        values.put(UtilidadesDB.CAMPO_CORREO,correo.getText().toString());
        values.put(UtilidadesDB.CAMPO_USERNICK,userNick.getText().toString());
        values.put(UtilidadesDB.CAMPO_CONTRASENHA,contrasenha.getText().toString());
        values.put(UtilidadesDB.CAMPO_REPCONTRASENHA,repContrasenha.getText().toString());
        long newRowId = db.insert(UtilidadesDB.TABLA_USUARIO, null, values);
        System.out.println("INSERCION CORRECTA "+ UtilidadesDB.CREAR_TABLA_USUARIOS+" estps campos" + UtilidadesDB.CAMPO_NOMBRE+nombre+UtilidadesDB.CAMPO_APELLIDOS+apellidos);
        Toast.makeText(getApplicationContext(),"el usuario es"+newRowId,Toast.LENGTH_SHORT).show();

    }

    protected void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}