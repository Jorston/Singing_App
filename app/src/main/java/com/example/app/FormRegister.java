package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.Modelos.MformRegister;
import com.example.app.data.Escrituras;

import java.io.IOException;

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
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MformRegister persona = new MformRegister(nombre.getText().toString(),apellidos.getText().toString(),userNick.getText().toString(),correo.getText().toString(),contrasenha.getText().toString(),repContrasenha.getText().toString());
                    escrituras.serializadionOuput(persona);
                    Escrituras esc = new Escrituras();
                    esc.serializadionOuput(persona);
                    showMessage("Usuario Serializado");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    protected void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
