package com.example.app.data;

import android.content.Context;
import android.view.View;

import com.example.app.Modelos.MformRegister;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Escrituras {
    private Context context;
    private String fileName = "Register.txt";

    public void serializadionOuput(MformRegister persona) throws  IOException{
            try{
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(persona);
                os.close();
                fos.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

    }
    public void setContext(Context context) {
        this.context = context;
    }

    private static void imprimirLista(ArrayList<MformRegister> listado) {
        for (int i=0; i< listado.size();i++){
            MformRegister persona = (MformRegister) listado.get(i);
            System.out.println("nombre "+persona.getNombre()+"\napellidos;"+persona.getApellidos()+"\ncorreo: "+persona.getCorreo()
            +"\ncontraseña: "+persona.getContrasenha()+"\nrep contra: "+persona.getRepcontrasenha());
        }
    }
}
