package com.example.app.data;

import android.content.Context;

import com.example.app.Modelos.Fichaje;
import com.example.app.Modelos.FichajeHora;
import com.example.app.Modelos.MformRegister;
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
    File file;
    private Context context;
    private String fileNameFichaje = "FichajesHora.txt";
    private String fileName = "NuevoArchivo.txt";

    public void setContext(Context context) {
        this.context = context;
    }

    public void escrituraFichajes(String user, Date horaEntrada, Date horaSalida, Date fechaEntrada, Date fechaSalida){
        file = new File(fileNameFichaje);
        ObjectOutputStream objectOutput = null;
        ObjectInputStream objectInput = null;
        ArrayList<FichajeHora> listaMarcaje = new ArrayList<FichajeHora>();

            /*try {
                FichajeHora nuevoMarcaje = new FichajeHora(user, horaEntrada, horaSalida, fechaEntrada, fechaSalida);
                listaMarcaje.add(nuevoMarcaje);
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(listaMarcaje);
                System.out.println("escritura correcta");
                os.close();
                fos.close();
            } catch (Exception e) {
                System.out.println("no se pudo escribir");
                e.printStackTrace();
            }*/

            try {
                objectInput = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileNameFichaje));
                FichajeHora nuevoMarcaje = new FichajeHora(user, horaEntrada, horaSalida, fechaEntrada, fechaSalida);
                listaMarcaje = (ArrayList<FichajeHora>) objectInput.readObject();
                listaMarcaje.add(nuevoMarcaje);

                objectOutput = new ObjectOutputStream(new FileOutputStream("/data/data/com.example.app/files/"+fileNameFichaje));
                objectOutput.writeObject(listaMarcaje);
                objectInput.close();
                objectOutput.close();
                System.out.println("ARCHIVOOOOOOOO ESCRITOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+fileNameFichaje);
            } catch (FileNotFoundException e) {
                System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR111111111111111"+fileNameFichaje);
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR2222222222222222222"+fileNameFichaje);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR3333333333333333333"+fileNameFichaje);
                e.printStackTrace();
            } finally {
                if (objectOutput != null) {
                    try {
                        objectOutput.close();
                    } catch (IOException ignored) {
                    }
                }
            }
    }


    public void lecturaFichajes(){
        int contador = 0;
        boolean cambio = false;
        try {
            ObjectInputStream lectura = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileNameFichaje));
            ObjectInputStream lecturaUsuarios = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileName));
            ArrayList<FichajeHora> listadoRegistros = (ArrayList<FichajeHora>) lectura.readObject();
            ArrayList<MformRegister> listadoUsuarios = (ArrayList<MformRegister>) lecturaUsuarios.readObject();
            for (FichajeHora fichajes : listadoRegistros){

                System.out.println("FICHAJE ES: "+fichajes.getUser()+" "+fichajes.getHoraEntrada()+" "+fichajes.getHoraSalida()+" "+fichajes.getFechaEntrada()+" "+fichajes.getFechaSalida());

            }
            for (MformRegister list : listadoUsuarios){
                System.out.println("USUARIOS: "+list.getUserNick()+" "+list.getContrasenha());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
