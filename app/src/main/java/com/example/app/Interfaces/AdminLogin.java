package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionesRoom.MetodosRoomAdmin;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;

public class AdminLogin extends AppCompatActivity {

    Button btnLogin;

    TextView textuser, textpassword;

    ProgressBar progressBar;

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoomAdmin metodosRoom = new MetodosRoomAdmin();

    static MediaPlayer mediaAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //inflate fragment para el login
        setContentView(R.layout.activity_admin_login);

        myDatabaseRoom = Room.databaseBuilder(this,MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

        btnLogin = findViewById(R.id.btnLoginAdmin);

        textuser = findViewById(R.id.textUserAdmin);

        textpassword = findViewById(R.id.textPasswordAdmin);

        progressBar = findViewById(R.id.progressBar);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#369C6C"), PorterDuff.Mode.SRC_IN);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btnLogin.setEnabled(false);

                //condicion de si existe el usuario le damos acceso sino no puede intrar a la aplicacion
                if (metodosRoom.validarUsuariosAdmin(textuser.getText().toString(),textpassword.getText().toString())){

                    btnLogin.setEnabled(true);

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

                    mediaAdmin = MediaPlayer.create(getApplicationContext(), R.raw.silbido_whatsapp);

                    mediaAdmin.isLooping();

                    mediaAdmin.start();

                    Intent intent = new Intent(getApplicationContext(),AdminHome.class);

                    startActivity(intent);


                    showMessage("Usuario Administrador aceptado");

                }else{

                    btnLogin.setEnabled(true);

                    showMessage("Usted no es Administrador");

                }
            }

        });
    }

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
