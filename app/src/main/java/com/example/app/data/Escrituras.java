package com.example.app.data;

import android.content.Context;
import com.example.app.Modelos.MformRegister;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Escrituras {
    private Context context;
    private String fileName = "NuevoArchivo.txt";
    String nombre,apellidos,correos,userNick,contra,repContra;
    
    public void serializadionOuput(ArrayList<MformRegister> listadoRegistros) throws  IOException{
            try{
                MformRegister persona = new MformRegister(nombre,apellidos,correos,userNick,contra,repContra);
                listadoRegistros.add(persona);
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(listadoRegistros);
                os.close();
                fos.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

    }
    public void setContext(Context context) {
        this.context = context;
    }
}
