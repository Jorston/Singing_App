package com.example.app.data;

import android.content.Context;

import com.example.app.Modelos.MformRegister;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Escrituras {
    private Context context;
    private String fileName = "Register.txt";
    
    public void serializadionOuput(MformRegister persona) throws  IOException{
            try{
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(register);
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
