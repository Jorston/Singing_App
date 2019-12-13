package com.example.app.data;

import android.content.Context;
import com.example.app.Modelos.Registro;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Repositorio {
    private Context context;
    private String fileName = "datos";

    public void writeUser(Registro user) {
        try{
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(user);
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
