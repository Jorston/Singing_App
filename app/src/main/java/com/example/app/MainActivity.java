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
import com.example.app.data.Escrituras;

public class MainActivity extends AppCompatActivity {
    Escrituras listado = new Escrituras();
    //actividad principal
    TextView textuser, textpassword;
    Button button, botonRegistrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textuser = findViewById(R.id.textUser);
        textpassword = findViewById(R.id.textPassword);
        button = findViewById(R.id.btnLogin);
        botonRegistrate = findViewById(R.id.btnRegistrate);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                listado.lecturaArchivo(textuser.getText().toString(),textpassword.getText().toString());
                showMessage("usuario new accepted");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
                button.setEnabled(true);
                showMessage("usuario no registrado");
            }
        });
        botonRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FormRegister.class );
                startActivity(intent);
            }
        });
    }
    protected void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
