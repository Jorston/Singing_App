package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionPSQL.ConexionPsql;
import com.example.app.ConexionesRoom.MetodosRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // declaracion variables globales e instancia objeto escritura
    TextView textuser, textpassword,tBotonAdmin,tBotonVideo;

    Button button, botonRegistrate,botonGoogle;

    ProgressBar progressBar;

    boolean validatorUser = false;

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoom metodosRoom = new MetodosRoom();

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //obtencion de elementos de la vista mediante id
        textuser = findViewById(R.id.textUser);

        textpassword = findViewById(R.id.textPassword);

        tBotonAdmin = findViewById(R.id.tAdmin);

        tBotonVideo = findViewById(R.id.tVideo);

        progressBar = findViewById(R.id.progressBar);

        button = findViewById(R.id.btnLogin);

        botonRegistrate = findViewById(R.id.btnRegistrate);

        botonGoogle = findViewById(R.id.btnGoogle);

        button.setOnClickListener(this);

        botonRegistrate.setOnClickListener(this);

        botonGoogle.setOnClickListener(this);

        tBotonAdmin.setOnClickListener(this);

        tBotonVideo.setOnClickListener(this);

        //metodo para insertar en BD con Room instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
        myDatabaseRoom = Room.databaseBuilder(this,MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

    } //fin oncreate
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //MAINACTIVITY
            case R.id.btnLogin:

                miHiloPsqlLogin miHiloPsqlLoginins = new miHiloPsqlLogin(textuser.getText().toString(),textpassword.getText().toString());

                miHiloPsqlLoginins.execute();

                progressBar.setVisibility(View.VISIBLE);

                progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#369C6C"), PorterDuff.Mode.SRC_IN);

                    new CountDownTimer(2000,1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                            try {

                                Thread.sleep(1000);

                            }catch(InterruptedException e) {

                                e.printStackTrace();
                            }
                        }

                        public void onFinish(){
                            progressBar.setVisibility(View.GONE);
                        }

                    }.start();

                break;

            //FORMREGISTER
            case R.id.btnRegistrate:

                Intent intentar = new Intent(getApplicationContext(), FormRegister.class );

                startActivity(intentar);

                break;

            //WEBVIEW
            case R.id.btnGoogle:

                Intent intento = new Intent(getApplicationContext(), WebViewActivity.class );

                startActivity(intento);

                break;
            //LOGINADMIN
            case R.id.tAdmin:

                Intent admin = new Intent(getApplicationContext(), AdminLogin.class);

                startActivity(admin);

                break;
            //VIDEOACTIVITY
            case R.id.tVideo:

                Intent videoActivity = new Intent(getApplicationContext(), VideoActivity.class);

                startActivity(videoActivity);

            } //fin switch

        } //fin del onclick

    //classe multitarea
    public class miHiloPsqlLogin extends AsyncTask<String,Void,String> {

        private  final String user;

        private final String password;

        public miHiloPsqlLogin(String user, String password) {

            this.user = user;

            this.password = password;
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {

            //conexion para PSQL Instanciamos objetos
            ConexionPsql conexionPsql = new ConexionPsql();

            Connection con = null;

            Statement statement;

            con = conexionPsql.conectar();

            //si coneccion insertamos en PSQL
            if (con != null) {

                PreparedStatement preparedStatement;

                String verifLogin ="select usernick,contrasenha from userspostsql where usernick = ? and contrasenha = ? ;";

                try {

                    preparedStatement = con.prepareStatement(verifLogin);

                    preparedStatement.setString(1,textuser.getText().toString());

                    preparedStatement.setString(2,textpassword.getText().toString());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if(!resultSet.next() == false){

                        Bundle bundle = new Bundle();

                        Intent intent = new Intent(getApplicationContext(), Home.class);

                        //envio de texto con el valor del usuario
                        bundle.putString("usuario",textuser.getText().toString());

                        intent.putExtras(bundle);

                        startActivity(intent);

                        validatorUser = true;

                        finish();

                    }else{

                        validatorUser = false;
                    }

                } catch (SQLException e) {

                    e.printStackTrace();
                }

            }else{
                //condicion de si existe el usuario le damos acceso sino no puede intrar a la aplicacion
                if (metodosRoom.validarUsuarios(textuser.getText().toString(),textpassword.getText().toString())){

                    media = MediaPlayer.create(getApplicationContext(), R.raw.sheran);

                    media.isLooping();

                    media.start();

                    validatorUser = true;

                    Bundle bundle = new Bundle();

                    Intent intent = new Intent(getApplicationContext(), Home.class);

                    //envio de texto con el valor del usuario
                    bundle.putString("usuario",textuser.getText().toString());

                    intent.putExtras(bundle);

                    startActivity(intent);

                    finish();

                }else{

                    validatorUser = false;

                }
            }

            return null;
        }

        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            if (validatorUser){

                showMessage("Usuario Aceptado");

            }else{

                showMessage("No registrado");
            }

        }
    }//fin clase mi hilo

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

} //fin clase
