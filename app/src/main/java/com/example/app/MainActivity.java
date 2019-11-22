package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.app.Modelos.Registro;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //actividad principal
    TextView textuser,textpassword;
    Button button;
    Registro per = new Registro("jorge",1234);
    Registro per2 = new Registro("gaston",0000);
    ArrayList<Registro> registros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textuser = findViewById(R.id.textUser);
        textpassword = findViewById(R.id.textPassword);
        button = findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

    }

    public void insertar(){
        registros.add(per);
        registros.add(per2);
        for (Registro per: registros){
            System.out.println("PERSONAS"+per.getUser()+" "+per.getPassword());
        }
    }
}
