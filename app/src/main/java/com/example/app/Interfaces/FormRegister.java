package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionPSQL.ConexionPsql;
import com.example.app.ConexionesRoom.MetodosRoom;
import com.example.app.ConexionesRoom.MetodosUtilidadesRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FormRegister extends AppCompatActivity {

    Button buton,btnInsertRoom;

    TextView nombre,apellidos,correo,userNick,contrasenha,repContrasenha;

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoom metodosRoom = new MetodosRoom();

    int auxUserRoom = 0;

    int auxContraseña = 0;

    int auxAceptedUser = 0;

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

        btnInsertRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ejecucion de asynctask coneccion con posgresSQL
                final miHiloPsql hilo = new miHiloPsql(nombre.getText().toString(), apellidos.getText().toString(), correo.getText().toString(), userNick.getText().toString(), contrasenha.getText().toString(), repContrasenha.getText().toString());

                //Ejecutamos el hilo de la coneccion
                hilo.execute();

            }//fin de onclick

        });//fin de setonclicklisener

    }//fin oncreate

   //classe multitarea
    public class miHiloPsql extends AsyncTask<String,Void,String> {

        private final String nombrea;

        private final String apellidosa;

        private final String usernicka;

        private final String correoa;

        private final String contrasenhaa;

        private final String repContrasenhaa;

       //constructor util para metodo insert en PSQL
        public miHiloPsql(String nombrea, String apellidosa, String usernicka, String correoa, String contrasenhaa, String repContrasenhaa) {

            this.nombrea = nombrea;

            this.apellidosa = apellidosa;

            this.usernicka = usernicka;

            this.correoa = correoa;

            this.contrasenhaa = contrasenhaa;

            this.repContrasenhaa = repContrasenhaa;
        }

        @SuppressLint("WrongThread")

        @Override
        protected String doInBackground(String... strings) {

            //conexion para PSQL Instanciamos objetos
            ConexionPsql conexionPsql = new ConexionPsql();

            Connection con = null;

            Statement statement;

            PreparedStatement preparedStatement;

            //insercion a PSQL en Asyntask
            String insertUser = "insert into userspostsql(userNick,nombre,apellidos,correo,contrasenha,repcontrasenha,rolUsuario) values (?,?,?,?,?,?,?);";

            //instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
            myDatabaseRoom = Room.databaseBuilder(getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

            con = conexionPsql.conectar();

            //si coneccion insertamos en PSQL
            if (con != null) {

                try {

                    //statement = con.createStatement();

                    preparedStatement = con.prepareStatement(insertUser);

                    preparedStatement.setString(1, userNick.getText().toString());

                    preparedStatement.setString(2, nombre.getText().toString());

                    preparedStatement.setString(3, apellidos.getText().toString());

                    preparedStatement.setString(4, correo.getText().toString());

                    preparedStatement.setString(5, contrasenha.getText().toString());

                    preparedStatement.setString(6, repContrasenha.getText().toString());

                    preparedStatement.setInt(7, 0);

                    preparedStatement.executeUpdate();

                    //ResultSet resultSet = preparedStatement.executeU();


                } catch (SQLException e) {

                    e.printStackTrace();
                }

                try {

                    con.close();

                } catch (SQLException e) {

                    e.printStackTrace();
                }

            }else{//Insertamos en Room si no hay coneccion

                if (metodosRoom.verificarFormulario(userNick.getText().toString())){

                    auxUserRoom++;

                }else {

                    if (!(metodosUtilidadesRoom.validacionContrasenha(contrasenha.getText().toString(),repContrasenha.getText().toString()))){

                        auxContraseña++;

                    }else {

                        metodosRoom.insertarUserRoom(nombre.getText().toString(), apellidos.getText().toString(), correo.getText().toString(), userNick.getText().toString(), contrasenha.getText().toString(), repContrasenha.getText().toString());

                        auxAceptedUser++;

                    }
                }

                //metodo util para insertar valor de rol Room
                metodosRoom.insertRol();

                myDatabaseRoom.close();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            if (auxUserRoom > 0){

                showMessage("el usuario ya existe");

                userNick.setText("");
            }
            if (auxContraseña > 0){

                showMessage("las contraseñas no coinciden");

                repContrasenha.setText("");
            }
            if (auxAceptedUser > 0){

                showMessage("usuario registrado");

                nombre.setText("");

                apellidos.setText("");

                userNick.setText("");

                correo.setText("");

                contrasenha.setText("");

                repContrasenha.setText("");
            }
        }
    }

    //metodo simplifica toask y lo ejecuta en una funcion
    public void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}