package com.example.app.Interfaces;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.DataConexiones.Escrituras;

public class MainActivity extends AppCompatActivity {

    // declaracion variables globales e instancia objeto escritura
    TextView textuser, textpassword;
    Button button, botonRegistrate,botonGoogle;
    ProgressBar progressBar;
    Escrituras listado = new Escrituras();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //obtencion de elementos de la vista mediante id
        textuser = findViewById(R.id.textUser);
        textpassword = findViewById(R.id.textPassword);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.btnLogin);
        botonRegistrate = findViewById(R.id.btnRegistrate);
        botonGoogle = findViewById(R.id.btnGoogle);
        //MainActivity validacion login usuario
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
            button.setEnabled(false);
            //condicion de si existe el usuario le damos acceso sino no puede intrar a la aplicacion
            if (listado.lecturaArchivo(textuser.getText().toString(),textpassword.getText().toString())){
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
            }
        });
        //registro nuevo usuario FormRegister
        botonRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormRegister.class );
                startActivity(intent);
            }
        });
        //registro con google
        botonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class );
                startActivity(intent);
            }
        });
    }
    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
