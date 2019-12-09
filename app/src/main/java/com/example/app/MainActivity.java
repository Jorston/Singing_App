package com.example.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.Modelos.MformRegister;
import com.example.app.Modelos.Registro;
import com.example.app.data.GlobalUtils;
import com.example.app.data.Repositorio;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Repositorio repositorio = new Repositorio();
    //actividad principal
    TextView textuser, textpassword;
    Button button, botonRegistrate;
    Registro per = new Registro("Jorge", "1234");
    Registro per2 = new Registro("Gaston", "0000");
    ArrayList<Registro> registros = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textuser = findViewById(R.id.textUser);
        textpassword = findViewById(R.id.textPassword);
        button = findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                insertar();


            }
        });
        botonRegistrate = findViewById(R.id.btnRegistrate);
        botonRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FormRegister.class );
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void insertar() {
        registros.add(per);
        registros.add(per2);
        Registro per3 = new Registro(this.textuser.getText().toString(), this.textpassword.getText().toString());
        findUser(per3);
        repositorio.setContext(this);
        repositorio.writeUser(per3);
        boolean interruptor = true;
        for (Registro personas : registros) {
            if (interruptor){
                if (personas.getUser().equals(per3.getUser()) && personas.getPassword().equals(per3.getPassword())) {
                    GlobalUtils.setMail(per3.getUser());
                    showMessage("usuario accepted");
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    finish();
                    interruptor = false;
                }else {
                    button.setEnabled(true);
                    showMessage("usuario no registrado");
                }

            }
        }

    }
    private void findUser(Registro user){
        System.out.println(registros.contains(user));
    }

    private void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
