package com.example.app.DataConexiones;

import android.content.Context;
import com.example.app.ModelosAdaptadores.MformRegister;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Escrituras {
    private Context context;
    private String fileName = "NuevoArchivo.txt";
    private boolean interructor = false;
    public void setContext(Context context) {
        this.context = context;
    }
    //metodo valida si no existe el archivo lo crea y si existe no hace nada
    public boolean validadorFichero(String nombre, String apellidos,String correos ,String userNick, String contra,String repContra){
        File af = new File("/data/data/com.example.app/files/"+fileName);
        if (!af.exists()){
            try {
                ArrayList<MformRegister> listadoRegistros = new ArrayList<MformRegister>();
                FileOutputStream fos = context.getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                MformRegister persona = new MformRegister(nombre, apellidos, correos, userNick, contra, repContra);
                listadoRegistros.add(persona);
                os.writeObject(listadoRegistros);
                os.close();
                fos.close();
                interructor = true;
                System.out.println("NO EXISTIA LO ESTAMOS ESCRIBIENDO"+interructor);
            } catch (Exception e) {
                System.out.println("no se pudo escribir");
                e.printStackTrace();
            }
        }else{
            interructor = false;
            System.out.println("YAAAAAAAAAA EXISTE"+interructor);
        }
        return  interructor;
    }
    //metodo serializa usuario ya cuando el archivo existe
    public void serializadionOuput(String nombre, String apellidos, String correos, String userNick,String contra,String repContra){
        ObjectOutputStream objectOutput = null;
        ObjectInputStream objectInput = null;
        ArrayList<MformRegister> list = new ArrayList<MformRegister>();
        try{
            objectInput = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileName));
            list = (ArrayList<MformRegister>) objectInput.readObject();
            MformRegister nuevos = new MformRegister(nombre,apellidos,correos,userNick,contra,repContra);
            list.add(nuevos);

            objectOutput = new ObjectOutputStream(new FileOutputStream("/data/data/com.example.app/files/"+fileName));
            objectOutput.writeObject(list);
            System.out.println("ARCHIVOOOOOOOO ESCRITOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+fileName);
        }catch (IOException e){
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR111111111111111"+fileName);
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOR2222222222222222222"+fileName);
            e.printStackTrace();
        }finally {
            if(objectOutput != null){
                try {
                    objectOutput.close();
                }catch (IOException ignored) {
                    System.out.println("NO SE PUDO ABRIR PARA SERIALIZAR");
                }
            }
        }
    }
    //metodo lectura de archivo serializado
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
                }else{
                    System.out.println("CONTADOR ES NOOOEQUAL : "+contador);
                }
            }
            //metodo auxiliar para validar el usuario que hara login 
            if (contador>0){
                System.out.println("CONTADOR VERDA ES : "+contador);
                cambio=true;
            }else{
                System.out.println("CONTADOR FALSO ES : "+contador);
                cambio=false;
            }
        System.out.println("ARCHIVOOOOOOOOOO LIEDOOOOOOOOOOOOO");
        lectura.close();
        }catch(FileNotFoundException e) {
            System.out.println("error 1");
        }catch (ClassNotFoundException e) {
            System.out.println("error 2");
        }catch (IOException e) {
            System.out.println("error 3");
        }
        return cambio;
    }
}
