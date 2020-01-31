package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionesRoom.MetodosRoom;
import com.example.app.ConexionesRoom.MetodosUtilidadesRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;
import com.example.app.DataConexiones.Escrituras;

public class FormRegister extends AppCompatActivity {

    //declaracion de variables globales e instancia de objeto Escritura y demas objetos
    Escrituras escrituras = new Escrituras();

    Button buton,btnInsertRoom;

    TextView nombre,apellidos,correo,userNick,contrasenha,repContrasenha;

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoom metodosRoom = new MetodosRoom();

    final MetodosUtilidadesRoom metodosUtilidadesRoom = new MetodosUtilidadesRoom();

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

        btnInsertRoom = findViewById(R.id.btnBDRoom);

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

        btnInsertRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
                myDatabaseRoom = Room.databaseBuilder(getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

                if (metodosRoom.verificarFormulario(userNick.getText().toString())){

                    showMessage("EL NICK YA ESTA SIENDO UTILIZADO");

                    userNick.setText("");

                }else {

                    if (!(metodosUtilidadesRoom.validacionContrasenha(contrasenha.getText().toString(),repContrasenha.getText().toString()))){

                        showMessage("LAS CONTRASEÑAS NO COINCIDEN");

                        repContrasenha.setText("");

                    }else {

                        metodosRoom.insertarUserRoom(nombre.getText().toString(), apellidos.getText().toString(), correo.getText().toString(), userNick.getText().toString(), contrasenha.getText().toString(), repContrasenha.getText().toString());

                        showMessage("UsuarioRoom insertado en la base de datos");

                        myDatabaseRoom.close();
                    }
                }

                metodosRoom.insertRol();
            }
        });

    }

    //metodo simplifica toask y lo ejecuta en una funcion
    public void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}