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
import com.example.app.Modelos.Registro;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //actividad principal
    TextView textuser,textpassword;
    Button button;
    Registro per = new Registro("jorge","1234");
    Registro per2 = new Registro("gaston","0000");
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
                insertar();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void insertar(){
        String user = this.textuser.getText().toString();
        String password = this.textpassword.getText().toString();
        registros.add(per);
        registros.add(per2);
        Registro per3 = new Registro(user,password);
        boolean interructor= true;
        for (Registro personas: registros){
            if (interructor){
                if (personas.getUser().equals(per3.getUser()) && personas.getPassword().equals(per3.getPassword())){
                    Toast.makeText(getApplicationContext(),"usuario accepted",Toast.LENGTH_SHORT).show();
                    System.out.println("CHIVATO DE LA PERSONA VERDA"+per3);
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    interructor = false;

                }else {
                    Toast.makeText(getApplicationContext(),"usuario no registrado",Toast.LENGTH_SHORT).show();
                    System.out.println("CHIVATO DE LA PERSONA FALSO" + per3.getUser() + " " + per3.getPassword());
                }

            }

        }
    }
}
