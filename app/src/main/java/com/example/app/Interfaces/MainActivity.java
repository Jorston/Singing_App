package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionesRoom.MetodosRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // declaracion variables globales e instancia objeto escritura
    TextView textuser, textpassword,tBotonAdmin;

    Button button, botonRegistrate,botonGoogle;

    ProgressBar progressBar;

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoom metodosRoom = new MetodosRoom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //obtencion de elementos de la vista mediante id
        textuser = findViewById(R.id.textUser);

        textpassword = findViewById(R.id.textPassword);

        tBotonAdmin = findViewById(R.id.tAdmin);

        progressBar = findViewById(R.id.progressBar);

        button = findViewById(R.id.btnLogin);

        botonRegistrate = findViewById(R.id.btnRegistrate);

        botonGoogle = findViewById(R.id.btnGoogle);

        button.setOnClickListener(this);

        botonRegistrate.setOnClickListener(this);

        botonGoogle.setOnClickListener(this);

        tBotonAdmin.setOnClickListener(this);

        //metodo para insertar en BD con Room instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
        myDatabaseRoom = Room.databaseBuilder(this,MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

    } //fin oncreate
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //MAINACTIVITY
            case R.id.btnLogin:

                button.setEnabled(false);

                //condicion de si existe el usuario le damos acceso sino no puede intrar a la aplicacion
                if (metodosRoom.validarUsuarios(textuser.getText().toString(),textpassword.getText().toString())){

                    progressBar.setVisibility(View.VISIBLE);

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

                    showMessage("usuario new accepted");

                    Bundle bundle = new Bundle();

                    Intent intent = new Intent(getApplicationContext(), Home.class);

                    //envio de texto con el valor del usuario
                    bundle.putString("usuario",textuser.getText().toString());

                    intent.putExtras(bundle);

                    startActivity(intent);

                    finish();

                }else{

                    button.setEnabled(true);

                    showMessage("usuario no registrado");

                }
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

            } //fin switch

        } //fin del onclick

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

} //fin clase
