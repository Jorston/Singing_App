package com.example.app.data;

import android.content.Context;
import com.example.app.Modelos.MformRegister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Escrituras {
    private Context context;
    private String fileName = "NuevoArchivo.txt";
    File file;
    String nombre,apellidos,correos,userNick,contra,repContra;
    public void setContext(Context context) {
        this.context = context;
    }


    public void escrituraArchivoExtra(String nombre,String apellidos, String correos, String userNick, String contra, String repContra){
        file = new File(fileName);
        if (!file.exists()){
            try {
                ArrayList<MformRegister> listadoRegistros = new ArrayList<MformRegister>();
                MformRegister persona = new MformRegister(nombre, apellidos, correos, userNick, contra, repContra);
                listadoRegistros.add(persona);
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(listadoRegistros);
                System.out.println("escritura correcta");
                os.close();
                fos.close();
            } catch (Exception e) {
                System.out.println("no se pudo escribir");
                e.printStackTrace();
            }
        }else{
            System.out.println("YA EXISTEEEEEE");
        }
        MformRegister persona = new MformRegister(nombre, apellidos, correos, userNick, contra, repContra);
        ArrayList<MformRegister> listado = new ArrayList<MformRegister>();
        listado.add(persona);
        try {
            FileOutputStream fos = context.openFileOutput("ArchivoExtra.txt", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listado);
            System.out.println("ESCRITURAAAAA EXTRAAAAAAAA CORRECTA");
            os.close();
            fos.close();

        }catch (IOException e){

            System.out.println("ARCHIVOOOOOOOOOOO AUUUUUUUUUUXILIARRRR");
        }
    }
    public void serializadionOuput(String nombre, String apellidos, String correos, String userNick,String contra,String repContra) throws  IOException {
        ObjectOutputStream objectOutput = null;
        ObjectInputStream objectInput = null;
        ArrayList<MformRegister> list = new ArrayList<MformRegister>();
        try{
            objectInput = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileName));
            MformRegister nuevo = new MformRegister(nombre,apellidos,correos,userNick,contra,repContra);
            list = (ArrayList<MformRegister>) objectInput.readObject();

            MformRegister nuevos = new MformRegister(nombre,apellidos,correos,userNick,contra,repContra);
            list.add(nuevos);

            objectOutput = new ObjectOutputStream(new FileOutputStream("/data/data/com.example.app/files/"+fileName));
            objectOutput.writeObject(list);
            System.out.println("ARCHIVOOOOOOOO ESCRITOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+fileName);
        } catch (IOException e) {
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR111111111111111"+fileName);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR2222222222222222222"+fileName);
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


       /* try {
            MformRegister persona = new MformRegister(nombre, apellidos, correos, userNick, contra, repContra);
            listadoRegistros.add(persona);
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listadoRegistros);
            System.out.println("escritura correcta");
            os.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("no se pudo escribir");
            e.printStackTrace();
        }
    }*/


    public boolean lecturaArchivo(String userNick, String password){
        int contador = 0;
        boolean cambio = false;
        try{
            ObjectInputStream lectura = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileName));
            ArrayList<MformRegister> listadoRegistros = (ArrayList<MformRegister>) lectura.readObject();
            for (MformRegister list : listadoRegistros){
                System.out.println("USUARIOS: "+list.getUserNick()+"\n"+list.getContrasenha());
                if ((userNick.equals(list.getUserNick())) && (password.equals(list.getContrasenha()))){
                    System.out.println("CONTADOR ES EQUAL : "+contador);
                    contador++;
                }
                else{
                    System.out.println("CONTADOR ES NOOOEQUAL : "+contador);
                }
            }

            if (contador>0){
                System.out.println("CONTADOR VERDA ES : "+contador);
                cambio=true;
            }else{
                System.out.println("CONTADOR FALSO ES : "+contador);
                cambio=false;
            }
        System.out.println("ARCHIVOOOOOOOOOO LIEDOOOOOOOOOOOOO");
        lectura.close();
        } catch(FileNotFoundException e) {
            System.out.println("error 1");
        }catch (ClassNotFoundException e) {
            System.out.println("error 2");
        } catch (IOException e) {
            System.out.println("error 3");
        }
        return cambio;
    }
}
