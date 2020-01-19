package com.example.app.data;

import android.content.Context;

import com.example.app.Modelos.FichajeHora;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EscrituraFichaje {
    //variables globales
    private Context context;
    private boolean cambio = false;
    private String fileNameFichaje = "FichajesHora.txt";

    //metodo devuelve el mismo contexto en el que esta
    public void setContext(Context context) {
        this.context = context;
    }

    public boolean validadorFichero(String user, Date fechaEntrada, Date horaEntrada,String tipoMarcaje){
        File af = new File("/data/data/com.example.app/files/"+fileNameFichaje);
        if (!af.exists()){
            try{
                ArrayList<FichajeHora> listadoRegistros = new ArrayList<FichajeHora>();
                FileOutputStream fos = context.getApplicationContext().openFileOutput(fileNameFichaje, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                FichajeHora persona = new FichajeHora(user,fechaEntrada,horaEntrada,tipoMarcaje);
                listadoRegistros.add(persona);
                os.writeObject(listadoRegistros);
                os.close();
                fos.close();
                cambio = true;
                System.out.println("escritura correcta");
                System.out.println("NO EXISTIA LO ESTAMOS ESCRIBIENDO"+cambio);
            }catch (Exception e) {
                System.out.println("no se pudo escribir");
                e.printStackTrace();
            }

        }else{
            cambio = false;
            System.out.println("YAAAAAAAAAA EXISTE"+cambio);
        }
    return  cambio;
    }
    //escritura fichero Fichajes
    public void escrituraFichajes(String user,Date fechaEntrada,Date horaEntrada,String tipoMarcaje){
        ObjectOutputStream objectOutput = null;
        ObjectInputStream objectInput = null;
        ArrayList<FichajeHora> listaMarcaje = new ArrayList<FichajeHora>();

        try {
            objectInput = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileNameFichaje));
            FichajeHora nuevoMarcaje = new FichajeHora(user,fechaEntrada,horaEntrada,tipoMarcaje);
            listaMarcaje = (ArrayList<FichajeHora>) objectInput.readObject();
            listaMarcaje.add(nuevoMarcaje);

            objectOutput = new ObjectOutputStream(new FileOutputStream("/data/data/com.example.app/files/"+fileNameFichaje));
            objectOutput.writeObject(listaMarcaje);
            objectInput.close();
            objectOutput.close();
            System.out.println("ARCHIVOOOOOOOO ESCRITOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+fileNameFichaje);
            }catch (FileNotFoundException e) {
                System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR111111111111111"+fileNameFichaje);
                e.printStackTrace();
            }catch (IOException e) {
                System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR2222222222222222222"+fileNameFichaje);
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR3333333333333333333"+fileNameFichaje);
                e.printStackTrace();
            }finally {
                if(objectOutput != null) {
                    try {
                        objectOutput.close();
                    }catch (IOException ignored) {
                        System.out.println("NO SE PUDO ABRIR EL ARCHIVO DE FICHAJES");
                    }
                }
            }
    }

    public void lecturaFichajes(){
        int contador = 0;
        boolean cambio = false;
        try {
            ObjectInputStream lectura = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileNameFichaje));
            ArrayList<FichajeHora> listadoRegistros = (ArrayList<FichajeHora>) lectura.readObject();
            for (FichajeHora fichajes : listadoRegistros){

                System.out.println("FICHAJE ES: "+fichajes.getUser()+" "+fichajes.getFechaEntrada()+" "+fichajes.getHoraEntrada()+fichajes.getTipoMarcado());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
