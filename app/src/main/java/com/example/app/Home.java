package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button boton1,boton2,boton3,boton4,boton5;

    private final int[] botones = {R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
