package com.example.app.data;

import android.content.Context;
import android.view.View;

import com.example.app.Modelos.MformRegister;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import com.example.app.Modelos.Fichaje;

public class Escrituras {
    private Context context;
    private String fileName = "archivo.txt";

    public void serializadionOuput(Fichaje persona){
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
}
