package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.example.app.R;

public class PortadaPrincipal extends AppCompatActivity {

    Thread thread;

    ProgressBar progressBarPortada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_portada_principal);

        progressBarPortada = findViewById(R.id.progresBarPortada);

        progressBarPortada.getIndeterminateDrawable().setColorFilter(Color.parseColor("#369C6C"), PorterDuff.Mode.SRC_IN);

         thread = new Thread(new Runnable() {
             @Override
             public void run() {

                 try {

                     Thread.sleep(3 * 1000);

                     progressBarPortada.setVisibility(View.VISIBLE);

                     Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                     startActivity(intent);

                 }catch (Exception e){

                     Log.e("PORTADA","ERROR AL INFLAR PORTADA");
                 }
             }
         });

         thread.start();
    }
}
